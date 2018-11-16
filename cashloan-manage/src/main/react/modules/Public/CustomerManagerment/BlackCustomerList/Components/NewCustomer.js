import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;
var Reflux = require('reflux');
var Actions = require('../actions/Actions');
var FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
function cx(classNames) {
  if (typeof classNames === 'object') {
    return Object.keys(classNames).filter(function(className) {
      return classNames[className];
    }).join(' ');
  } else {
    return Array.prototype.join.call(arguments, ' ');
  }
}
var certTypeChildren = [];
reqwest({
  url: '/modules/manage/system/dict/cache/list.htm?type=ID_TYPE',
  method: 'get',
  type: 'json',
  success: (result) => {
    var items = result.ID_TYPE.map((item)=> {
      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
    });
    certTypeChildren = items
  }
});

export default React.createClass({
  mixins: [
    Reflux.connect(FormStore, 'formData'),
    Validation.FieldMixin
  ],
  getInitialState() {
    return {
      formData: {},
      status: {
        certNumber: {},
        customerName: {},
        certType: {}
      },
      certTypeChildren: []
    };
  },
  changeValue(e) {
    var newValue = this.state.formData;
    var name = e.target.name;
    newValue[name] = e.target.value;
    this.setState({formData: newValue});
  },
  renderValidateStyle(item, hasFeedback = true) {
    var formData = this.state.formData;
    var status = this.state.status;

    var classes = cx({
      'has-feedback': hasFeedback,
      'has-error': status[item].errors,
      'is-validating': status[item].isValidating,
      'has-success': formData[item] && !status[item].errors && !status[item].isValidating
    });

    return classes;
  },
  selectChange(name, value) {
    var newValue = this.state.formData;
    newValue[name] = value;
    this.setState({formData: newValue});
  },
  handleOk() {
    var me = this
    var validation = this.refs.validation,
      status = 'create';
    validation.validate((valid) => {
      if (!valid) {
        return;
      } else {
        if (this.props.title == "新增") {
          status = 'create';
        }
        else if (this.props.title == "修改") {
          status = 'update';
        }

        reqwest({
          url: '/modules/common/action/BlackCustomerAction/save.htm',
          method: 'post',
          data: {
            customer: JSON.stringify(this.state.formData),
            flag: status,
            id: this.state.formData.id
          },
          type: 'json',
          success: (result) => {
            if (result.code == 200) {
              Modal.success({
                title: result.msg
              });
              Actions.initStore();
              me.props.hideAddModal();
            }
            else if (result.code == 500) {
              Modal.error({
                title: result.msg
              });
              me.setState({
                loading: false
              });
            }
          }
        });
      }
    });
    
  },
  handleCancel() {
    this.props.hideAddModal();
    this.refs.validation.reset();
    this.setState(this.getInitialState());
  },
  componentDidUpdate(prevProps, prevStat) {
    var validation = this.refs.validation;
    setTimeout(() => {
      if (prevProps.visible) {
        validation && validation.validate((valid) => {
          if (!valid) {
            //console.log('error in form');

          } else {
            //console.log('submit');
          }

        });
      }
    }, 400);
  },
  render() {
    var me = this;

    var state = this.state;
    var status = state.status;
    var formData = state.formData;
    var props = this.props;
    var isDisabletwo = (props.title == "新增用户" ? false : true);
    var isDisable = !props.canEdit || (props.title == "修改" ? true : false);
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
        提 交
      </button>
    ];
    if (this.props.title == '信息查看') {
      modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
      ]
    }
    return (
      <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel}
             width="800"
             footer={modalBtns}>
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}>
            <div className="ant-form-item">
              <label className="col-4" htmlFor="id">客户ID：</label>
              <div className="col-8">
                <input className="ant-input" type="text" disabled={true} name="id" value={formData.id}
                       onChange={this.changeValue}/>
              </div>
              <label className="col-3" htmlFor="customerName" required>客户姓名：</label>
              <div className="col-8">
                <div className={this.renderValidateStyle('customerName')}>
                  <Validator rules={[{required: true}]}>
                    <input className="ant-input" type="text" disabled={!this.props.canEdit} name="customerName"
                           value={formData.customerName} onChange={this.changeValue}/>
                  </Validator>
                </div>
              </div>
            </div>
            <div className="ant-form-item">
              <label className="col-4" htmlFor="certType" required>身份证件类型：</label>
              <div className="col-8">
                <div className={this.renderValidateStyle('certType')}>
                  <Validator rules={[{required: true}]}>
                    <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="certType"
                            value={formData.certType} onChange={this.selectChange.bind(this, 'certType')}>
                      {certTypeChildren}
                    </Select>
                  </Validator>
                </div>
              </div>
              <label className="col-3" htmlFor="certNumber" required>证件号码：</label>
              <div className="col-8">
                <div className={this.renderValidateStyle('certNumber')}>
                  <Validator rules={[{required: true, type:'IdCard', message: '请输入正确的证件号码'}]}>
                    <input className="ant-input" type="text" disabled={!this.props.canEdit} name="certNumber"
                           value={formData.certNumber} onChange={this.changeValue}/>
                  </Validator>
                </div>
              </div>
            </div>
            <div className="ant-form-item">
              <label className="col-4" htmlFor="remark">备注：</label>
              <div className="col-19">
                <textarea className="ant-input" disabled={!this.props.canEdit} style={{height:80,resize:"none"}}
                          name="remark" value={formData.remark} onChange={this.changeValue}/>
              </div>
            </div>
          </Validation>
        </form>
      </Modal>
    )
  }
});