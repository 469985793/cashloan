import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;
var Datepicker = antd.Datepicker;  
var Select = antd.Select;
var Option = Select.Option; 
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;
import TreeSelect from 'rc-tree-select';
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var  FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
var  Actions = require('../actions/Actions');
import 'rc-tree-select/assets/index.css';
import 'rc-tree/assets/index.css';
var formatDate = require("../../../../common/dateFormat");
var  roleList=[];
 reqwest({
            url: '/modules/manage/system/role/list.htm',
            method: 'get', 
            type: 'json',
            success: (result) => {
               roleList  = result.roles.map((item)=>{
                  return (<Option key={item.id} value={item.id}>{item.name}</Option>);
                }); 
            }
          });
 const loop = (data) => {
       return data.map((item) => {
         if (item.children) {
           return <TreeNode title={item.text} key={String(item.id)}>{loop(item.children)}</TreeNode>;
         } else {
           return <TreeNode title={item.text} key={String(item.id)} />;
         }
       });
     };
const loopData =(data) =>{ 
    var list = [];
     data.forEach((item,i)=>{  

          var v={};
          v[item.id]= item.text;
          list.push(v);
          if(item.children) {
           return list = list.concat(loopData(item.children));
          } 
      });
    return list; 
};
const parseTreeNode = data => loop(data);
const getTreeKeyValue = data =>{
  var arr = loopData(data);
  var objarr={}
  arr.forEach(obj=>{
    var key = Object.keys(obj)[0];
    objarr[key] = obj[key];
  })
  return objarr;
};
var  treeNodes=[];
var treeData={};
 reqwest({
            url: '/modules/system/queryWholeOfficeTreeWidthChebox.htm',
            method: 'get', 
            type: 'json',
            success: (result) => {  
                treeData = getTreeKeyValue(result);//console.log("treeData",treeData);
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
Date.prototype.format =function(format)
{
var o = {
"M+" : this.getMonth()+1, 
"d+" : this.getDate(),
"h+" : this.getHours(), 
"m+" : this.getMinutes(), 
"s+" : this.getSeconds(), 
"q+" : Math.floor((this.getMonth()+3)/3), 
"S" : this.getMilliseconds()
}
if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
(this.getFullYear()+"").substr(4- RegExp.$1.length));
for(var k in o)if(new RegExp("("+ k +")").test(format))
format = format.replace(RegExp.$1,
RegExp.$1.length==1? o[k] :
("00"+ o[k]).substr((""+ o[k]).length));
return format;
}
export default React.createClass({
  mixins: [ 
      formatDate,
      Reflux.connect(FormStore, 'formData'),
      Validation.FieldMixin
    ], 
  getInitialState() {
    return { 
        visible: false,      
        status: { 
          roleId:{},
          number:{},
          name:{},
          officeId:{},
          userName:{}
        }, 
        formData:{} 
      };
  }, 
  changeValue(e) { 
      var newValue = this.state.formData;
      var name = e.target.name;
      newValue[name] = e.target.value;
      this.setState({formData:newValue});
  }, 
  selectChange(name,value) { 
    var newValue = this.state.formData; 
    if(name =="send_time"){
          value = value.format('yyyy-MM-dd hh:mm:ss')
     }
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
            var formData =this.state.formData;
            var postData={};
            postData.id=formData.id;
            postData.office_id=formData.office_id;
            postData.title=formData.title;
            postData.content=formData.content;
            postData.send_time=formData.send_time;
               reqwest({
                        url: '/modules/credit/action/NoticeAction/saveOrUpdate.htm',
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
                          else 
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
    this.refs.validation.reset();
    this.setState(this.getInitialState());
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
  handleCheck(info) {
      
      var officeOverNameArry = [];
      officeOverNameArry = info.checkedKeys.map((v,i)=>{ 
        return treeData[v];
      }); 
      officeOverNameArry.reverse();
      var officeNames = officeOverNameArry.join(',');
      var officeOverId = info.checkedKeys.join(','); 
      var newValue=this.state.formData;
      newValue.office_id=officeOverId;
      newValue.officeNames=officeNames;
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
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        <button key="button" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
          提 交
        </button>
    ];
    if(this.props.title == '信息查看')
    {
       modalBtns = [
          <button key="button" className="ant-btn" onClick={this.handleCancel}>关闭</button> 
        ]
    }
    const overlay = (<Tree defaultExpandAll={true} checkable={true} onCheck={this.handleCheck} >
          {treeNodes}
        </Tree>);
    return(
    <Modal title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="600"
      footer={modalBtns} >  
        <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}> 
                <div className="ant-form-item">
                      <label  className="col-3"  htmlFor="office_id" >接收人：</label>
                      <div className="col-18">
                          <TreeSelect trigger={['click']}  
                             closeOnSelect={false}
                             overlay={overlay} animation="slide-up">
                            <input key={Date.now()} className="ant-input" defaultValue={formData.officeNames}  readOnly />
                          </TreeSelect>
                      </div>
                </div> 
                <div className="ant-form-item">
                      <label  className="col-3"  htmlFor="title" >标题公告：</label>
                      <div className="col-18">
                         <input className="ant-input" type="text" disabled={!props.canEdit} name="title" value={formData.title} onChange={this.changeValue}/>
                      </div>
                </div>      
                <div className="ant-form-item">      
                      <label  className="col-3"  htmlFor="content" >公告内容：</label>
                      <div className="col-20">
                         <textarea className="ant-input" style={{height:"60px"}} type="text" disabled={!props.canEdit} name="content" value={formData.content} onChange={this.changeValue}/>
                      </div>
                </div>
                <div className="ant-form-item"> 
                      <label className="col-3" htmlFor="send_time">发布时间：</label>
                      <div className="col-10"> 
                          <Datepicker disabled={!this.props.canEdit} style={{width:"100%"}} name="send_time" value={formData.send_time} onChange={this.selectChange.bind(this, 'send_time')}/>
                      </div>
                </div>
          </Validation>
        </form> 
    </Modal> 
    )
  }
});
