import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var  FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
var  Actions = require('../actions/Actions');

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
      Reflux.connect(FormStore, 'formData'),
      Validation.FieldMixin
    ], 
  getInitialState() {
    return {
        roleList:{},
        status: { 
          name:{},
          nid:{},
          remark:{},
          isDelete:{}
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
    this.setState({formData:newValue});
  }, 
  handleOk() {
    var validation = this.refs.validation,
        status = 'create';    

        validation.validate((valid) => {
          if (!valid) {
            //console.log('error in form');
            return;
          } else {
              if(this.props.title=="新增用户")
              { 
                status = 'create';
              }
              else if(this.props.title=="修改")
              {
                status = 'update';
              }
              var formData = this.state.formData;
              var postData={};
              postData.id= formData.id;
              postData.name= formData.name;
              postData.nid= formData.nid;
              postData.remark= formData.remark;
              postData.isDelete= formData.isDelete;
               reqwest({
                        url: '/modules/manage/system/role/save.htm',
                        method: 'post', 
                        data:{
                          form:JSON.stringify(postData),
                          status:status
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.success)
                          {
                            Modal.success({
                              title: result.message
                            });
                           Actions.initStore();
                           this.props.hideAddModal();
                          }
                          else if(result.code==400)
                          {
                            Modal.error({
                              title: result.msg
                            }); 
                            this.setState({
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
              url: '/modules/manage/system/role/list.htm',
              method: 'get', 
              type: 'json',
              success: (result) => {
                 children  = result.roles.map((item)=>{
                    return (<Option key={item.id} value={item.id}>{item.name}</Option>);
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
    var roleId = formData.roleId&&formData.roleId.length?String(formData.roleId):undefined;
    if(roleId)
    {   
      roleId = roleId.split(",");
      roleId = roleId.map(value =>{
        return Number(value);
      });
    }
    var officeOver = formData.officeOver;
    if(formData.officeOver =="null" ||formData.officeOver ==null)
      officeOver = undefined;
    else officeOver = String(officeOver);
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
                      <label  className="col-3"  htmlFor="name" required>角色名称：</label>
                      <div className="col-8">
                            <div className={this.renderValidateStyle('name')}>
                              <Validator rules={[{required: true, min: 1, message: '用户名至少为 1 个字符'}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="name" value={formData.name} onChange={this.changeValue}/>
                              </Validator> 
                            {status.name.errors ? <div className="ant-form-explain">{status.name.errors.join(',')}</div> : null}
                          </div>
                      </div> 
                      <label  className="col-5"  htmlFor="nid" required>角色唯一标示：</label>
                      <div className="col-8">
                            <div className={this.renderValidateStyle('nid')}>
                              <Validator rules={[{required: true, min: 1, message: '用户名至少为 1 个字符'}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="nid" value={formData.nid} onChange={this.changeValue}/>
                              </Validator> 
                            {status.nid.errors ? <div className="ant-form-explain">{status.nid.errors.join(',')}</div> : null}
                          </div>
                      </div> 
                </div> 
            
                <div className="ant-form-item">
                    <label  className="col-3"  htmlFor="name" required>备注：</label>
                      <div className="col-21">
                         <textarea className="ant-input" type="text" disabled={!props.canEdit} style={{height:80,resize:"none"}} name="remark" value={formData.remark} onChange={this.changeValue}/>
                      </div>
                </div>  
                <div className="ant-form-item">    
                    <label className="col-3"  htmlFor="isDelete" required>是否启用：</label>
                    <div className="col-8">
                      <div className={this.renderValidateStyle('isDelete', false)}>
                        <Validator rules={[{required: true, message: '请选择所属部门'}]}>
                          <Select size="large"  disabled={!props.canEdit} style={{width:"100%"}} name="isDelete" value={formData.isDelete} onSelect={this.selectChange.bind(this, 'officeId')}>
                              <Option value="0">是</Option> 
                              <Option value="1">否</Option>
                          </Select> 
                        </Validator>
                            {status.isDelete.errors ? <div className="ant-form-explain">{status.isDelete.errors.join(',')}</div> : null}
                      </div>
                    </div> 
                </div>  
          </Validation>
        </form> 
    </Modal> 
    )
  }
});