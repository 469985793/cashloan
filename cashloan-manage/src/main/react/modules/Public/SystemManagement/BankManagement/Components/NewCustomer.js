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
          bank_code:{},
          open_net:{},
          open_name:{},
          is_loan:{},
          is_repayment:{},
          account:{}
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
             postData.bank_code=formData.bank_code;
             postData.open_net=formData.open_net;
             postData.open_name=formData.open_name;
             postData.account=formData.account;
             postData.is_loan=formData.is_loan;
             postData.is_repayment=formData.is_repayment;
             postData.remark=formData.remark;
               reqwest({
                        url: '/modules/system/sysBankAction/saveOrUpdate.htm',
                        method: 'post', 
                        data:{
                          json:JSON.stringify(postData),
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.success==true)
                          {
                            Modal.success({
                              title: result.msg
                            });
                           Actions.initStore();
                           this.props.hideAddModal();
                          }
                          else if(result.success==false)
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
                      <label  className="col-3"  htmlFor="bank_code" required>开户行：</label>
                      <div className="col-8">
                                <Select size="large" disabled={!props.canEdit} style={{width:"100%"}} name="bank_code" value={formData.bank_code} onChange={this.selectChange.bind(this, 'bank_code')}>
                                    {state.roleList}
                                </Select> 
                      </div> 
                      <label className="col-4"  htmlFor="open_net" required>网点：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('open_net')}>
                              <Validator rules={[{required: true, min: 2, message: '用户名至少为 2 个字符'}]}>
                              <input className="ant-input" type="text" disabled={!props.canEdit}  name="open_net" value={formData.open_net} onChange={this.changeValue}/>
                              </Validator> 
                              {status.open_net.errors ? <div className="ant-form-explain">{status.open_net.errors.join(',')}</div> : null}
                        </div>
                      </div> 
                </div> 
            
                <div className="ant-form-item">
                    <label  className="col-3"  htmlFor="open_name" required>户名：</label>
                    <div className="col-8">
                        <div className={this.renderValidateStyle('open_name')}>
                          <Validator rules={[{required: true, min: 1, message: '用户名至少为 1 个字符'}]}> 
                          <input className="ant-input" type="text" disabled={!props.canEdit}  name="open_name" value={formData.open_name} onChange={this.changeValue}/> 
                          </Validator> 
                        {status.open_name.errors ? <div className="ant-form-explain">{status.open_name.errors.join(',')}</div> : null}
                      </div>
                    </div> 
                 
                    <label  className="col-4"  htmlFor="account" required>账号：</label>
                    <div className="col-8">
                        <div className={this.renderValidateStyle('account')}>
                          <Validator rules={[{required: true, min: 1, message: '用户名至少为 1 个字符'}]}> 
                          <input className="ant-input" type="text" disabled={!props.canEdit}  name="account" value={formData.account} onChange={this.changeValue}/> 
                          </Validator> 
                        {status.account.errors ? <div className="ant-form-explain">{status.account.errors.join(',')}</div> : null}
                      </div>
                    </div> 
                </div>    
              
              <div className="ant-form-item"> 
                    <label className="col-4"  htmlFor="is_loan" required>是否放款使用：</label>
                    <div className="col-8">
                      <div className={this.renderValidateStyle('is_loan', false)}>
                        <Validator rules={[{required: true, message: '请选择所属部门'}]}>
                          <Select size="large"  disabled={!props.canEdit} style={{width:"87%"}} name="is_loan" value={formData.is_loan} onSelect={this.selectChange.bind(this, 'is_loan')}>
                              <Option value="1">启用</Option> 
                              <Option value="0">禁用</Option>
                          </Select> 
                        </Validator>
                            {status.is_loan.errors ? <div className="ant-form-explain">{status.is_loan.errors.join(',')}</div> : null}
                      </div>
                    </div> 

                    <label className="col-4"  htmlFor="is_repayment" required>是否回款使用：</label>
                    <div className="col-8">
                      <div className={this.renderValidateStyle('is_repayment', false)}>
                        <Validator rules={[{required: true, message: '请选择所属部门'}]}>
                          <Select size="large"  disabled={!props.canEdit} style={{width:"87%"}} name="is_repayment" value={formData.is_repayment} onSelect={this.selectChange.bind(this, 'is_repayment')}>
                              <Option value="1">启用</Option> 
                              <Option value="0">禁用</Option> 
                          </Select> 
                        </Validator>
                            {status.is_repayment.errors ? <div className="ant-form-explain">{status.is_repayment.errors.join(',')}</div> : null}
                      </div>
                    </div> 
                </div>  


                <div className="ant-form-item">
                      <label  className="col-3"  htmlFor="remark" required>备注：</label>
                      <div className="col-20">
                         <textarea className="ant-input" type="text" disabled={!props.canEdit} style={{height:80,resize:"none"}} name="remark" value={formData.remark} onChange={this.changeValue}/>
                      </div>
                      <div >
                         <input className="ant-input" type="hidden" name="rid" value={formData.rid} />
                         <input className="ant-input" type="hidden" name="id" value={formData.id} />
                      </div>
                </div>
          </Validation>
        </form> 
    </Modal> 
    )
  }
});