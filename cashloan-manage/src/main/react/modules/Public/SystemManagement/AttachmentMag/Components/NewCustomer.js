import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;
var reqwest = require('reqwest');
var Reflux = require('reflux');
var FormStore = require('../stores/FormStore');
var Actions = require('../actions/CustomerActions');
function cx(classNames) {
  if (typeof classNames === 'object') {
    return Object.keys(classNames).filter(function(className) {
      return classNames[className];
    }).join(' ');
  } else {
    return Array.prototype.join.call(arguments, ' ');
  }
}
export default React.createClass({
  mixins: [
    Validation.FieldMixin,
    Reflux.connect(FormStore, 'data')
  ],
  getInitialState() {
    return {
      formData: {},
      linkData: {},
      status: {
        rd_remark: {},
        rd_ptype: {}
      }
    };
  },
  changeValue(e) {
    var newValue = this.state.formData;
    var name = e.target.name;
    newValue[name] = e.target.value;
    this.setState({formData: newValue});
  },
  checkPass(rule, value, callback) {
    var formData = this.state.formData;
    reqwest({
      url: '/modules/common/action/PlPprofileAction/isBTypeExists.htm'
      , method: 'get'
      , data: {
        btype: formData.rd_ptype,
        id: formData.id
      }
      , type: 'json'
      , success: (result) => {
      }
    }).then(function(resp) {
      //console.log(resp)
      if (resp.exists == true) {
        callback('标示符已存在！');
      } else {
        callback();
      }
    })
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
    var validation = this.refs.validation;
    validation.validate((valid) => {
      if (!valid) {
        //console.log('error in form');
        return;
      } else {
        var postData = {};
        var selectRecord = this.props.selectRecord;
        var formData = this.state.formData;
        if (this.props.title == "编辑") {
          postData.id = String(formData.id);
        }
        postData.rd_parent_id = formData.rd_parent_id;
        postData.rd_remark = formData.rd_remark;
        postData.rd_ptype = formData.rd_ptype;
        postData.rd_remark2 = formData.rd_remark2;
        reqwest({
          url: '/modules/common/action/PlPprofileAction/saveOrUpdateProFile.htm',
          method: 'post',
          data: {
            json: JSON.stringify(postData),
          },
          type: 'json',
          success: (result) => {
            if (result.success == true) {
              Modal.success({
                title: "操作成功"
              });
              Actions.initStore();
              this.props.hideAddModal();
            }
            else if (result.success == false) {
              Modal.error({
                title: result.msg
              });
              this.setState({
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
  },
  handleReset() {
    this.refs.validation.reset();
    this.setState(this.getInitialState());
  },
  
  queryFormData(selectRecord, title){
    var formData = this.state.formData;
    var me = this;
    if (selectRecord && title == "编辑") {
      var id = selectRecord.id;
      reqwest({
        url: '/modules/common/action/PlPprofileAction/getProfileById.htm'
        , method: 'get'
        , data: {
          id: id,
        }
        , type: 'json'
        , success: (result) => {
          var newValue = result;
          me.setState({formData: newValue});
        }
      });
    } else {
      var newValue = formData;
      Object.keys(newValue).forEach(name=> {
        newValue[name] = "";
      });
      me.setState({formData: newValue});
    }
  },
  componentWillReceiveProps(nextProps) {
    if (nextProps.selectRecord)
      this.queryFormData(nextProps.selectRecord, nextProps.title);
  },
  componentDidMount() {
    if (this.props.selectRecord)
      this.queryFormData(this.props.selectRecord, this.props.title);
  },
  render() {
    var me = this;
    var state = this.state;
    var status = state.status;
    var props = this.props;
    var formData = state.formData;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
        提 交
      </button>
    ];
    return (
      <Modal title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="500"
             footer={modalBtns}>
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}>
            <div className="ant-form-item">
              <label className="col-5" htmlFor="rd_remark" required>类型名称：</label>
              <div className="col-8">
                <div className={this.renderValidateStyle('rd_remark')}>
                  <Validator rules={[{required: true, message: '必填'}]}>
                    <input className="ant-input" type="text" name="rd_remark" value={formData.rd_remark}
                           onChange={this.changeValue}/>
                  </Validator>
                  {status.rd_remark.errors ?
                    <div className="ant-form-explain">{status.rd_remark.errors.join(',')}</div> : null}
                </div>
              </div>
            </div>

            <div className="ant-form-item">
              <label className="col-5" htmlFor="rd_ptype" required>附件类型标示：</label>
              <div className="col-8">
                <div className={this.renderValidateStyle('rd_ptype')}>
                  <Validator rules={[{required: true,message: '必填'},{validator: this.checkPass}]}>
                    <input className="ant-input" type="text" name="rd_ptype" value={formData.rd_ptype}
                           onChange={this.changeValue}/>
                  </Validator>
                  {status.rd_ptype.errors ?
                    <div className="ant-form-explain">{status.rd_ptype.errors.join(',')}</div> : null}
                </div>
              </div>
            </div>
            <div className="ant-form-item">
              <label className="col-5" htmlFor="rd_remark2">类型说明：</label>
              <div className="col-19">
                <input className="ant-input" type="text" id="age" name="rd_remark2" value={formData.rd_remark2}
                       onChange={this.changeValue}/>
              </div>
            </div>
          </Validation>
        </form>
      </Modal>
    )
  }
});
