import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;
import TreeSelect from 'rc-tree-select';
var reqwest = require('reqwest');
import 'rc-tree-select/assets/index.css';
import 'rc-tree/assets/index.css'; 
var  FormStore = require('../stores/FormStore');
var  Actions = require('../actions/Actions');
var Attachment = require("./Attachment");
const loop = (data) => {
       return data.map((item) => {
         if (item.children) {
           return <TreeNode title={item.text} key={String(item.id)}>{loop(item.children)}</TreeNode>;
         } else {
           return <TreeNode title={item.text} key={String(item.id)} />;
         }
       });
     }; 
const parseTreeNode = data => loop(data);
 
var  treeNodes=[]; 
 reqwest({
            url: '/modules/common/action/PlPprofileAction/queryProfileWholeTreeSingle.htm',
            method: 'get', 
            type: 'json',
            success: (result) => {   
                treeNodes = parseTreeNode(result);
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
        roleList:{},
        status: { 
          name:{},
          type:{}
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
    handleSelectOfficeId(info) {
    
    var type =  info.node.props.eventKey;
    var newValue = this.state.formData;
    newValue.type= type;
    newValue.type_text=info.node.props.title;
    this.setState({ 
      visible: false,
      formData: newValue,
    });
  }, 
  onVisibleChange(visible) {
    this.setState({
      visible: visible
    });
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
    this.refs.validation.reset();
    this.setState(this.getInitialState()); 
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
    const overlayOfficeId = (<div style={{height:'450px',overflowY: 'scroll'}}><Tree defaultExpandAll={true} 
          onSelect={this.handleSelectOfficeId}>
      {treeNodes}
    </Tree></div>);
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
                      <label className="col-4" htmlFor="type" required>模板类型：</label>
                      <div className="col-10">
                              <TreeSelect trigger={['click']}  
                                closeOnSelect={true}  visible={this.state.visible} onVisibleChange={this.onVisibleChange}
                                  overlay={overlayOfficeId} animation="slide-up">
                                    <input key={Date.now()} className="ant-input" defaultValue={formData.type_text} readOnly/>
                               </TreeSelect>
                      </div>
                </div>
                <div className="ant-form-item">             
                      <label className="col-4"  htmlFor="name" required>模板名称：</label>
                      <div className="col-10">
                        <div className={this.renderValidateStyle('name')}>
                              <Validator rules={[{required: true,message: '必填'}]}>
                              <input className="ant-input" type="text" disabled={!props.canEdit}  name="name" value={formData.name} onChange={this.changeValue}/>
                              </Validator> 
                              {status.name.errors ? <div className="ant-form-explain">{status.name.errors.join(',')}</div> : null}
                        </div>
                      </div> 
                </div>   
                <div className="ant-form-item">
                 
                </div>
                <div className="ant-form-item">
                      <label  className="col-4"  htmlFor="remark">模板说明：</label>
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