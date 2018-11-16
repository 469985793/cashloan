import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Checkbox = antd.Checkbox ; 
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var reqwest = require('reqwest');
var  FormStore = require('../stores/FormStore');
var  Actions = require('../actions/Actions');
var CheckboxList = require('./CheckboxList')
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
      Reflux.connect(FormStore, 'formData')
    ], 
  getInitialState() {
    return { 
        status: { 
          scode:{},
          source:{}
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
    var status = 'create',me = this,
        validation = this.refs.validation;    
        validation.validate((valid) => {
          if (!valid) {
            //console.log('error in form');
            return;
          } else {
             if(this.props.title=="新增")
              { 
                status = 'create';
              }
              else if(this.props.title=="修改")
              {
                status = 'update';
              }
             var selectRecord= this.props.selectRecord; 
             var postData={};
             var formData=this.state.formData; 
             postData.sname=formData.sname;
             postData.scode=formData.scode;
             postData.slength= me.slength;
             postData.id=status=='update'?formData.id:null;
               reqwest({
                        url: '/modules/system/action/addRules.htm',
                        method: 'post', 
                        data:{
                          rules:JSON.stringify(postData),
                          status:status, 
                          checkRules: me.checkboxList  
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
  checkboxListChange(data,sum){
    this.checkboxList = data.join(',')+',';
    this.slength =sum;
  },
  render() {   
    var state = this.state; 
    var formData = state.formData; 
  
    var status = state.status;
    var props = this.props;
    var canEdit = !props.canEdit||(props.title=="修改"?true:false);

    var checkboxList = [];
    if(formData.scontent){
       formData.scontent.split(',').forEach((v,i)=>{
        if(v){
         checkboxList.push(Number(v)) ;
        }
      });  
    }
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
                      <label className="col-4"  htmlFor="sname" required>编码名称：</label>
                      <div className="col-8">
                          <input className="ant-input" type="text" disabled={!props.canEdit}  name="sname" value={formData.sname} onChange={this.changeValue}/>
                      </div> 
                      <label className="col-4"  htmlFor="scode" required>编号代码：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('scode', false)}>
                          <Validator rules={[{required: true, message: '必填'}]}>
                            <input className="ant-input" type="text" disabled={!props.canEdit}  name="scode" value={formData.scode} onChange={this.changeValue}/>
                          </Validator>
                              {status.scode.errors ? <div className="ant-form-explain">{status.scode.errors.join(',')}</div> : null}
                        </div>
                      </div>
                </div>  
                <div className="ant-form-item">
                  <label className="col-4"  htmlFor="sname" required>字符含义：</label>
                  <CheckboxList checkboxListChange={this.checkboxListChange} checkboxList={checkboxList}/>

                  <label className="col-4"  htmlFor="slength" required>字符数：</label>
                  <div className="col-8">
                      <input className="ant-input" type="text" disabled={true}  name="slength" value={formData.slength} onChange={this.changeValue}/>
                  </div> 
                </div>
          </Validation>
        </form> 
    </Modal> 
    )
  }
});
