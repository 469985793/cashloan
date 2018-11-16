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
          code:{},
          value:{}
        }, 
        formData:{
          is_loan:"1",
          is_repayment:"1"
        } 
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
    var validation = this.refs.validation;    
        validation.validate((valid) => {
          if (!valid) {
            //console.log('error in form');
            return;
          } else {
             var postData={};
             var selectRecord= this.props.selectRecord;
             var formData=this.state.formData;
             if(this.props.title=="修改"){
             postData.id=String(formData.id);
             }
             postData.code=formData.code;
             postData.name=formData.name;
             postData.value=formData.value;
             postData.type=1;
             postData.remark=formData.remark;
               reqwest({
                        url: '/modules/system/action/SysConfigAction/addOrUpdateSysConfig.htm',
                        method: 'post', 
                        data:{
                          formData:JSON.stringify(postData),
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg
                            });
                           Actions.initStore();
                           this.props.hideAddModal();
                          }
                          else if(result.code==500)
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
                      <label className="col-3"  htmlFor="code" required>参数编号：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('code')}>
                              <Validator rules={[{required: true,message: '必填'}]}>
                              <input className="ant-input" type="text" disabled={!props.canEdit}  name="code" value={formData.code} onChange={this.changeValue}/>
                              </Validator> 
                              {status.code.errors ? <div className="ant-form-explain">{status.code.errors.join(',')}</div> : null}
                        </div>
                      </div> 
                      <label className="col-4"  htmlFor="name" required>参数名称：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('name')}>
                              <Validator rules={[{required: true,message: '必填'}]}>
                              <input className="ant-input" type="text" disabled={!props.canEdit}  name="name" value={formData.name} onChange={this.changeValue}/>
                              </Validator> 
                              {status.name.errors ? <div className="ant-form-explain">{status.name.errors.join(',')}</div> : null}
                        </div>
                      </div> 
                </div> 

                <div className="ant-form-item">
                      <label  className="col-3"  htmlFor="value" required>参数值：</label>
                      <div className="col-20">
                        <div className={this.renderValidateStyle('value')}>
                          <Validator rules={[{required: true,message: '必填'}]}>
                             <textarea className="ant-input" type="text" disabled={!props.canEdit} style={{height:80,resize:"none"}} name="value" value={formData.value} onChange={this.changeValue}/>
                          </Validator> 
                          {status.value.errors ? <div className="ant-form-explain">{status.value.errors.join(',')}</div> : null}
                        </div>
                      </div>
                </div>   

                <div className="ant-form-item">
                      <label  className="col-3"  htmlFor="remark">备注：</label>
                      <div className="col-20">
                         <textarea className="ant-input" type="text" disabled={!props.canEdit} style={{height:80,resize:"none"}} name="remark" value={formData.remark} onChange={this.changeValue}/>
                      </div>
                </div>
          </Validation>
        </form> 
    </Modal> 
    )
  }
});