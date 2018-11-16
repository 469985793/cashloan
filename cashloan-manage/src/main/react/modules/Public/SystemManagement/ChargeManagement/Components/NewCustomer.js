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
        typeList:[],
        status: { 
          officeId:{},
          parkingFee:{},
          gpsInstallFee:{}
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
    var status = 'create',
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
             var postData={};
             var selectRecord= this.props.selectRecord;
             var formData=this.state.formData;
             if(this.props.title=="修改"){
             postData.id=String(formData.id);
             }
             postData.officeId=formData.officeId;
             postData.parkingFee=formData.parkingFee;
             postData.gpsInstallFee=formData.gpsInstallFee;
             postData.remark=formData.remark;
               reqwest({
                        url: '/modules/system/action/addOfficeFeeConfig.htm',
                        method: 'post', 
                        data:{
                          configs:JSON.stringify(postData),
                          status:status
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
                          else if(result.code==400)
                          {
                            Modal.error({
                              title: result.msg
                            }); 
                            this.setState({
                              loading:false
                            });
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
      var me = this ;
      reqwest({
              url: '/modules/system/action/getBusinessDepartment.htm?type=2',
              method: 'get', 
              type: 'json',
              success: (result) => {
                var items  = result.datas.map((item)=>{
                    return (<Option key={item.id} value={String(item.id)}>{item.name}</Option>);
                  });
                 me.setState({typeList:items});
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
    <Modal title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="700"
      footer={[
          <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
          <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
            提 交
          </button>
        ]} >  
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}> 
            
                <div className="ant-form-item">
                      <label className="col-3"  htmlFor="officeId" required>营业厅：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('officeId', false)}>
                          <Validator rules={[{required: true, message: '必填'}]}>
                            <Select size="large"  disabled={!props.canEdit} style={{width:"100%"}} name="officeId" value={formData.officeId} onSelect={this.selectChange.bind(this, 'officeId')}>
                                {state.typeList}
                            </Select> 
                          </Validator>
                              {status.officeId.errors ? <div className="ant-form-explain">{status.officeId.errors.join(',')}</div> : null}
                        </div>
                      </div>
                      <label className="col-4"  htmlFor="parkingFee" required>停车费元/月：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('parkingFee', false)}>
                          <Validator rules={[{required: true, message: '必填'}]}>
                             <input className="ant-input" type="text" disabled={!props.canEdit}  name="parkingFee" value={formData.parkingFee} onChange={this.changeValue}/>
                          </Validator>
                                {status.parkingFee.errors ? <div className="ant-form-explain">{status.parkingFee.errors.join(',')}</div> : null}
                        </div> 
                      </div>    
                </div>  
                <div className="ant-form-item">
                      <label className="col-4"  htmlFor="gpsInstallFee" required>GPS安装费元/次：</label>
                      <div className="col-7">
                        <div className={this.renderValidateStyle('gpsInstallFee', false)}>
                          <Validator rules={[{required: true, message: '必填'}]}>
                            <input className="ant-input" type="text" disabled={!props.canEdit}  name="gpsInstallFee" value={formData.gpsInstallFee} onChange={this.changeValue}/>
                          </Validator>
                                {status.gpsInstallFee.errors ? <div className="ant-form-explain">{status.gpsInstallFee.errors.join(',')}</div> : null}
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