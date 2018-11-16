import 'rc-cascader/assets/index.css';
import Cascader from 'rc-cascader';
import React from 'react';
import ReactDOM from 'react-dom';
import antd from 'antd';
import {Icon,Tag} from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var reqwest = require('reqwest');
var  SelectRecordStore = require('../stores/SelectRecordStore');
var  Actions = require('../actions/CustomerActions');
var OpenList = require('./OpenList')

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
      Validation.FieldMixin
    ], 
  getInitialState() {
    return {   
        status: {}, 
        formData:{},
        result:[],
        selectData:{
                isSelectRecord:false,
                record:null
              },
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
     var state = this.state;   
     var props = this.props;
     var SelectRecord = this.state.selectData.record;
     var formData=this.state.formData;
               reqwest({
                        url:'/modules/fel/FelformulaAction/calculation.htm',
                        method: 'post', 
                        data:{
                            json: JSON.stringify(formData),
                            id:SelectRecord.id
                        },
                        type: 'json',
                        success: (result) => {
                          this.setState({result : result})
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg
                            });
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
  },
  handleCancel() {
    this.setState({tags:[]});
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
    var state = this.state; 
    var status = state.status;
    var result=state.result;
    var SelectRecord = this.state.selectData.record;
    var props = this.props;
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>关 闭</button>,
    ];
    return(
    <Modal title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="600"  
       footer={modalBtns} >    
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}> 
                <OpenList SelectRecord={SelectRecord} changeValue={this.changeValue}/> 
                <div className="ant-form-item">
                    <label className="col-4"  htmlFor="button"> </label>
                    <div className="col-8">
                        <input className="ant-btn" type="button" disabled={!props.canEdit}  name="button" value={"运算"} onClick={this.handleOk}/>
                    </div> 
                </div> 
                
                <div className="ant-form-item">
                      <label  className="col-4"  htmlFor="result">结果：</label>
                      <div className="col-20">
                         <textarea className="ant-input" type="text" name="result" readOnly={true} value={result?result:null} style={{height:80,resize:"none"}} />
                      </div>
                </div>
          </Validation>
        </form> 
    </Modal> 
    )
  }
});
