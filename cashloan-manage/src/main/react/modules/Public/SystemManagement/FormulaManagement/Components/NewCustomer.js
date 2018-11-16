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
var typeIdList=[];
reqwest({
        url: '/modules/fel/FeltypeAction/SelectAll.htm',
        method: 'get', 
        type: 'json',
        success: (result) => {
          var items  = result.data.map((item)=>{
              return (<Option key={item.value} value={Number(item.value)}> {item.text}</Option>);
            });
           typeIdList=items;
        }
      });

var tagOptions = []; 
reqwest({
        url: '/modules/fel/FelparamAction/formulaQuery.htm',
        method: 'get', 
        type: 'json',
        success: (result) => {
           tagOptions = result;
        }
      });
export default React.createClass({
  mixins: [ 
      Reflux.connect(FormStore, 'formData'),
      Validation.FieldMixin
    ], 
  getInitialState() {
    return { 
        tags: [],  
        status: { 
          state:{},
          dataSource:{},
          nestedState:{},
          typeId:{}
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
  onChange(value, selectedOptions) { 
    var newValue = this.state.tags; 
    var tag = selectedOptions[1]; 
    if(selectedOptions[0].label=='运算符' || selectedOptions[0].label=='数字'){
      tag.isOperator=1;
    }
    else tag.isOperator=0;
    newValue.push(tag); 
    this.setState({
      tags: newValue,
    });
  }, 
  handleClose(key) {
    const tags = [...this.state.tags].filter((tag,index) => (index!== key) && tag);
    //console.log(tags);
    this.setState({ tags });
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
    var me =this;
    var validation = this.refs.validation;    
        validation.validate((valid) => {
          if (!valid) {
            return;
          } else {
             var postData={};
             var selectRecord= this.props.selectRecord;
             var formData=this.state.formData;
             var title=this.props.title;
             if(this.props.title=="修改"){
             postData.id=String(formData.id);
             }
             postData.state=formData.state;
             postData.chineseName=formData.chineseName;
             postData.unit=formData.unit;
             postData.englishName=formData.englishName;
             postData.nestedState=formData.nestedState;
             postData.dataSource=formData.dataSource;
             postData.note=formData.note;
             postData.paramId="1";
             postData.typeId=formData.typeId;
             //console.log(me.state.tags)
             if(me.state.tags!=""){
               reqwest({
                        url: title=='新增公式'?'/modules/fel/FelformulaAction/Insert.htm':'/modules/fel/FelformulaAction/Update.htm',
                        method: 'post', 
                        data:{
                          json:JSON.stringify(postData),
                          tags:JSON.stringify(me.state.tags)
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
                  }else{
                     Modal.error({
                              title: "请填写公式"
                            });
                  } 
          } 
        }); 
  },
  handleCancel() { 
    this.setState({tags:[]});
    this.props.hideAddModal();
  },
  componentWillReceiveProps(nextProp){ 
    var selectRecord = nextProp.selectRecord;
      if(selectRecord){//console.log(JSON.parse(selectRecord.formulaJson));
        this.setState({
          tags:selectRecord.formulaJson&&nextProp.title!='新增公式'?JSON.parse(selectRecord.formulaJson):[]
        });
      }
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
  },
  render() {  
    var me = this; 
    var state = this.state; 
    var status = state.status;
    var formData = state.formData;
    var props = this.props;
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
          <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
            提 交
          </button>
    ];
    return(
    <Modal title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="600"  
       footer={modalBtns} >    
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}> 
            
                <div className="ant-form-item">
                      <label className="col-4"  htmlFor="id">序号：</label>
                      <div className="col-8">
                          <input className="ant-input" type="text" disabled={true}  name="id" value={formData.id} onChange={this.changeValue}/>
                      </div> 
                      <label className="col-4"  htmlFor="state" required>是否启用：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('state', false)}>
                          <Validator rules={[{required: true,type:'number'}]}>
                            <Select size="large"  disabled={!props.canEdit} style={{width:"100%"}} name="state" value={formData.state} onSelect={this.selectChange.bind(this, 'state')}>
                                <Option value={1}>是</Option> 
                                <Option value={0}>否</Option>
                            </Select> 
                          </Validator>
                        </div>
                      </div>
                </div> 

                <div className="ant-form-item">
                    <label className="col-4"  htmlFor="chineseName">公式名称：</label>
                    <div className="col-8">
                        <input className="ant-input" type="text" disabled={!props.canEdit}  name="chineseName" value={formData.chineseName} onChange={this.changeValue}/>
                    </div> 
                    <label className="col-4"  htmlFor="unit">计算单位：</label>
                    <div className="col-8">
                        <input className="ant-input" type="text" disabled={!props.canEdit}  name="unit" value={formData.unit} onChange={this.changeValue}/>
                    </div> 
                </div>  
                <div className="ant-form-item">
                      <label className="col-4"  htmlFor="englishName" >参数名称：</label>
                      <div className="col-8">
                          <input className="ant-input" type="text" disabled={!props.canEdit}  name="englishName" value={formData.englishName} onChange={this.changeValue}/>
                      </div> 
                      <label className="col-4"  htmlFor="dataSource" required>数据来源：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('dataSource', false)}>
                          <Validator rules={[{required: true}]}>
                            <Select size="large"  disabled={!props.canEdit} style={{width:"100%"}} name="dataSource" value={formData.dataSource} onSelect={this.selectChange.bind(this, 'dataSource')}>
                                <Option value="1">系统计算</Option> 
                                <Option value="2">人工录入</Option>
                            </Select> 
                          </Validator>
                        </div>
                      </div>
                </div>
                <div className="ant-form-item">
                      <label className="col-4"  htmlFor="nestedState" required>是否内嵌公式：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('nestedState', false)}>
                          <Validator rules={[{required: true, message: '必填',type:'number'}]}>
                            <Select size="large"  disabled={!props.canEdit} style={{width:"100%"}} name="nestedState" value={formData.nestedState} onSelect={this.selectChange.bind(this, 'nestedState')}>
                                <Option value={1}>是</Option> 
                                <Option value={0}>否</Option>
                            </Select> 
                          </Validator>
                        </div>
                      </div>
                      <label className="col-4"  htmlFor="typeId" required>类别：</label>
                      <div className="col-8">
                        <div className={this.renderValidateStyle('typeId', false)}>
                          <Validator rules={[{required: true,type:'number'}]}>
                            <Select size="large"  disabled={!props.canEdit} style={{width:"100%"}} name="typeId" value={formData.typeId} onSelect={this.selectChange.bind(this, 'typeId')}>
                                {typeIdList}
                            </Select> 
                          </Validator>
                          
                        </div>
                      </div>
                </div>
                <div className="ant-form-item">
                    <label className="col-4"  htmlFor="formula">计算公式：</label>
                    <div className="col-20">
                        <span style={{paddingTop:'5px',paddingBottom:'7px',display:'block'}} className="myTag">  
                          {this.state.tags.length?this.state.tags.map((tag,index) =>
                                    <Tag key={index+1} closable onClose={this.handleClose.bind(this,index)}>{tag.label}</Tag>
                                  ):null}
                          <span style={{border:'1px solid #aaa',marginLeft:'10px',borderRadius: '9px',padding:'2px'}}>
                            <Cascader size="large" options={tagOptions} onChange={this.onChange} expandTrigger="hover">
                            <Icon type="plus" />
                            </Cascader>
                          </span>
                        </span>
                    </div> 
                </div>   
                <div className="ant-form-item">
                      <label  className="col-4"  htmlFor="note">备注：</label>
                      <div className="col-20">
                         <textarea className="ant-input" type="text" disabled={!props.canEdit} style={{height:80,resize:"none"}} name="note" value={formData.note} onChange={this.changeValue}/>
                      </div>
                </div>
          </Validation>
        </form> 
    </Modal> 
    )
  }
});
