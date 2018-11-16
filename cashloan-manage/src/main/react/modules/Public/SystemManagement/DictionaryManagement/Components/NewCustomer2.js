import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var reqwest = require('reqwest');
var  FormStore = require('../stores/FormStore');
var  SelectRecordStore = require('../stores/SelectRecordStore');
var  CustomerActions = require('../actions/CustomerActions');

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
      Reflux.connect(SelectRecordStore, 'selectData'),
      Reflux.connect(FormStore, 'formData'),
      Validation.FieldMixin
    ], 
  getInitialState() {
    return {
        roleList:{},
        status: { 
          parentId:{},
          itemValue:{},
          itemCode:{}
        }, 
        selectData:{
                isSelectRecord:false,
                record:null
              },
        formData:{} 
      };
  },
  getDefaultProps(){
    return 
    {
      title:this.props.title;
    }
  },  
  changeValue(e) { 
      var newValue = this.state.formData;
      var name = e.target.name;
      newValue[name] = e.target.value;
      this.setState({formData:newValue});
  }, 
  selectChange(name,value) { 
    var newValue = this.state.formData; 
      newValue[name] = value; 
      //console.log(value);
    this.setState({formData:newValue});
  }, 
  handleOk() {
    var me = this;
    var validation = this.refs.validation,
        status = 'create'; 
        validation.validate((valid) => {
          if (!valid) {
            return;
          } else {
              if(this.props.title=="字项新增")
              { 
                status = 'create';
              }
              else if(this.props.title=="字项修改")
              {
                status = 'update';
              }
              var postData={};
              var selectRecord= this.props.selectRecord;
              var formData=this.state.formData;
              
              if(this.props.title=="字项修改"){
              postData.id=String(formData.id);
              }
              var id=this.state.selectData.record.id;
              //console.log(id)
              postData.parentId=formData.parentId;
              postData.itemValue=formData.itemValue;
              postData.itemCode=formData.itemCode;
               reqwest({
                        url: '/modules/manage/system/dict/detail/save.htm',
                        method: 'post', 
                        data:{
                          form:JSON.stringify(postData),
                          status:status
                        },
                        type: 'json',
                        success: (result) => { 
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg
                            });
                           CustomerActions.initDetailStore(id);
                           me.props.hideAddModal(); 
                          }
                          else
                          {
                            Modal.error({
                              title: result.msg
                            }); 
                            me.setState({
                              loading:false
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
  renderValidateStyle(item, hasFeedback=true) {
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
  componentDidMount() { 
      var children = [];
      reqwest({
              url: '/modules/common/action/ComboAction/queryDicBanks.htm',
              method: 'get', 
              type: 'json',
              success: (result) => {
                 children  = result.map((item)=>{
                    return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                  });
                 this.setState({
                   roleList :children
                 })
              }
            }); 
  },
  render() {  
    var state = this. state; 
    var formData = state.formData;
    
    var status = state.status;
    var props = this.props;
    var canEdit = !props.canEdit||(props.title=="修改"?true:false);
    return(
    <Modal title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="600"
      footer={[
          <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
          <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
            提 交
          </button>
        ]} >  
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}> 
            
                <div className="ant-form-item">
                      <label className="col-4"  htmlFor="parentId" required>父节点：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('parentId')}>
                              <Validator rules={[{required: true, type:"number", message: '必填'}]}>
                              <input className="ant-input" type="text" disabled={!props.canEdit}  name="parentId" value={formData.parentId} onChange={this.changeValue}/>
                              </Validator> 
                              {status.parentId.errors ? <div className="ant-form-explain">{status.parentId.errors.join(',')}</div> : null}
                        </div>
                      </div>
                      <label className="col-4"  htmlFor="itemValue" required>字典名称：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('itemValue')}>
                              <Validator rules={[{required: true, message: '必填'}]}>
                              <input className="ant-input" type="text" disabled={!props.canEdit}  name="itemValue" value={formData.itemValue} onChange={this.changeValue}/>
                              </Validator> 
                              {status.itemValue.errors ? <div className="ant-form-explain">{status.itemValue.errors.join(',')}</div> : null}
                        </div>
                      </div> 
                </div> 
            
                <div className="ant-form-item">
                    <label  className="col-3"  htmlFor="itemCode" required>字典代码：</label>
                    <div className="col-8">
                        <div className={this.renderValidateStyle('itemCode')}>
                          <Validator rules={[{required: true, message: '必填'}]}> 
                          <input className="ant-input" type="text" disabled={!props.canEdit}  name="itemCode" value={formData.itemCode} onChange={this.changeValue}/> 
                          </Validator> 
                        {status.itemCode.errors ? <div className="ant-form-explain">{status.itemCode.errors.join(',')}</div> : null}
                      </div>
                    </div> 
                </div>    
          </Validation>
        </form> 
    </Modal> 
    )
  }
});