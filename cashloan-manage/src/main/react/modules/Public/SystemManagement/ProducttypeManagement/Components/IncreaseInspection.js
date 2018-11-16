import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator; 
var Datepicker = antd.Datepicker;
var InputNumber = antd.InputNumber;  
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
var Actions = require('../actions/CustomerActions');

let business = [];
reqwest({
    url : '/modules/manage/system/dict/cache/list.htm?type=BUSINESS_TYPE',
    method : 'get',
    type : 'json',
    success : (result) => {
        business = result.BUSINESS_TYPE.map((item) => {
            return (<Option key={item.value} value={Number(item.value)}>{item.text}</Option>);
        })
    }
});

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
        status: {
          ptype:{},
          productType:{},
          state:{}
        }, 
        formData:{
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
      //console.log(newValue)
      this.setState({formData:newValue});
  }, 
  selectChange(name,value) { 
    var newValue = this.state.formData; 
      newValue[name] = value; 
    this.setState({formData:newValue});
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

  render() { 
    var me = this;
    var props = this.props; 
    var state = this. state;
    var status = state.status; 
    var formData = state.formData;   
    var canEdit = !props.canEdit; 
    return( 
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}> 
            
                <div className="ant-form-item">
                      <label className="col-4"  htmlFor="productType" required>类别名称：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('productType', false)}>
                          <Validator rules={[{required: true}]}> 
                            <input className="ant-input" type="text" name="productType" value={formData.productType} onChange={this.changeValue}/>
                          </Validator>
                        </div>
                      </div> 
                      <label className="col-4"  htmlFor="ptype" required>贷款类型：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('ptype', false)}>
                          <Validator rules={[{required: true,type:'number'}]}> 
                            <Select size="large"  style={{width:"100%"}} name="ptype" value={formData.ptype} onSelect={this.selectChange.bind(this, 'ptype')}>
                                {business}
                            </Select> 
                          </Validator>
                        </div>  
                      </div>
                </div> 

                <div className="ant-form-item">
                    <label className="col-4"  htmlFor="state" required>是否启用：</label>
                    <div className="col-8">
                      <div className={this.renderValidateStyle('state', false)}>
                          <Validator rules={[{required: true,type:'number'}]}>
                            <Select size="large" style={{width:"100%"}} name="state" value={formData.state} onSelect={this.selectChange.bind(this, 'state')}>
                                  <Option value={0}>是</Option> 
                                  <Option value={1}>否</Option>
                            </Select>
                          </Validator>
                        </div> 
                    </div>
                </div>  
          
                <div className="ant-form-item">
                      <label  className="col-4"  htmlFor="note">备注：</label>
                      <div className="col-20">
                         <textarea className="ant-input" type="text"  style={{height:80,resize:"none"}} name="note" value={formData.note} onChange={this.changeValue}/>
                      </div>
                </div>
          </Validation>
        </form>
    )
  }
});
