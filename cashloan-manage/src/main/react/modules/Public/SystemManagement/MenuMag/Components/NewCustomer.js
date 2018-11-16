import React from 'react';
import antd from 'antd';   
var Select = antd.Select;
var Option = Select.Option;
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;
import TreeSelect from 'rc-tree-select';
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
import 'rc-tree-select/assets/index.css';
import 'rc-tree/assets/index.css';
var reqwest = require('reqwest');
var  CustomerActions = require('../actions/CustomerActions');
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
            url: '/modules/manage/system/menu/combo/find.htm',
            method: 'get', 
            type: 'json',
            success: (result) => {   
                treeNodes = parseTreeNode(result);
            }
          });
export default React.createClass({
  mixins: [ 
   
    ], 
  getInitialState() {
    return {
        roleList:{},
        status: {}, 
        formData:{
          scriptid:null,
          text:null
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
    this.setState({formData:newValue});
  }, 
  handleOk() {

        status = 'create';    
        var formData = this.state.formData;
        var postData={}; 
   
              if(this.props.title=="添加")
              { 
                status = 'create';
              }
              else if(this.props.title=="修改")
              {
                postData.id= formData.id; 
                status = 'update';
              }
              
              postData.scriptid= formData.scriptid;
              postData.text= formData.text;
              postData.parentId= formData.parentId;
              postData.iconCls= formData.iconCls;
              postData.remark= formData.remark;
              postData.sort= formData.sort;
              postData.isDelete= formData.isDelete;
              postData.isMenu= formData.isMenu;
              postData.leaf= true;
              postData.level= formData.level;
               reqwest({
                        url: '/modules/manage/system/menu/update.htm',
                        method: 'post', 
                        data:{
                          menu:JSON.stringify(postData),
                          status:status
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg
                            });
                           CustomerActions.initStore();
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

  },
  handleCancel() {
    this.props.hideAddModal();
  }, 
  handleSelectOfficeId(info) {
    
    var parentId =  info.node.props.eventKey;
    var newValue = this.state.formData;
    newValue.parentId= parentId;
    newValue.parent_text=info.node.props.title;
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
  queryFormData(selectRecord,visible,title){
    var me = this; 
    //console.log(selectRecord)
    if(visible==true&&selectRecord&&title=="修改"){
      var id = selectRecord.id;
      reqwest({
                url: '/modules/manage/system/menu/find.htm'
                , method: 'get'
                ,data: {
                     id:id,
                  }
                , type: 'json' 
                , success: (result) => { 
                   var newValue = result.data; 
                   newValue.type = JSON.stringify(newValue.type); 
                   me.setState({formData:newValue});  
                } 
            });
    }
    else if(title=="添加"&&selectRecord){
      var formData = this.state.formData;
      var newValue = formData;
      Object.keys(newValue).forEach(name=>{  
          newValue[name]=""; 
      });
      //console.log(selectRecord)
      newValue.parentId =selectRecord.id;
      newValue.parent_text =selectRecord.text;
      this.setState({formData:newValue});
    }
  },
  componentWillReceiveProps(nextProps) {  
    this.queryFormData(nextProps.selectRecord,nextProps.visible,nextProps.title);
  },
  componentDidMount(){ 
      this.queryFormData(this.props.selectRecord,this.props.visible,this.props.title);
  },
  render() { 
    var state = this. state; 
    var formData = state.formData;
    var status = state.status;
    var props = this.props; 
    var title = props.title;
    const overlayOfficeId = (<div style={{height:'450px',overflowY: 'scroll'}}><Tree defaultExpandAll={false} 
              onSelect={this.handleSelectOfficeId}>
          {treeNodes}
        </Tree></div>);
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel}>  
        <form className="ant-form-horizontal" ref="myform">             
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="scriptid">脚本名称：</label>
                <div className="col-18">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="scriptid" value={formData.scriptid} onChange={this.changeValue}/> 
                </div> 
            </div> 
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="text">菜单名称：</label>
                <div className="col-18">
                    <input className="ant-input" type="text" disabled={this.props.canEdit} name="text" value={formData.text} onChange={this.changeValue}/> 
                </div> 
            </div> 
            <div className="ant-form-item">
                <label className="col-4"  htmlFor="parentId">父节点：</label>
                <div className="col-18">
                      <TreeSelect trigger={['click']}  
                         closeOnSelect={true}  visible={this.state.visible} onVisibleChange={this.onVisibleChange}
                         overlay={overlayOfficeId} animation="slide-up">
                        <input key={Date.now()} className="ant-input" defaultValue={formData.parent_text} readOnly/>
                      </TreeSelect>
                </div> 
            </div>
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="iconCls">图标：</label>
                <div className="col-18">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="iconCls" value={formData.iconCls} onChange={this.changeValue}/> 
                </div> 
            </div>
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="remark">菜单说明：</label>
                <div className="col-18">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="remark" value={formData.remark} onChange={this.changeValue}/> 
                </div> 
            </div>
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="sort">排序：</label>
                <div className="col-18">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="sort" value={formData.sort} onChange={this.changeValue}/> 
                </div> 
            </div>
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="isDelete">是否删除：</label>
                <div className="col-18">
                    <Select size="large"  disabled={this.props.canEdit} style={{width:"100%"}} name="isDelete" value={formData.isDelete} onSelect={this.selectChange.bind(this, 'isDelete')}>
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                    </Select> 
                </div> 
            </div>
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="isMenu">是否菜单：</label>
                <div className="col-18">
                    <Select size="large"  disabled={this.props.canEdit} style={{width:"100%"}} name="isMenu" value={formData.isMenu} onSelect={this.selectChange.bind(this, 'isMenu')}>
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                    </Select> 
                </div> 
            </div>  
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="leaf">是否叶节点：</label>
                <div className="col-18">
                    <Select size="large"  disabled={this.props.canEdit} style={{width:"100%"}} name="leaf" value={formData.leaf||title=="添加"?formData.leaf:"否"} onSelect={this.selectChange.bind(this, 'leaf')}>
                        <Option value={true}>是</Option>
                        <Option value={false}>否</Option> 
                    </Select> 
                </div> 
            </div> 
            <div className="ant-form-item">    
                <label className="col-4"  htmlFor="level">菜单级数：</label>
                <div className="col-18">
                    <Select size="large"  disabled={this.props.canEdit} style={{width:"100%"}} name="level" value={formData.level} onSelect={this.selectChange.bind(this, 'level')}>
                        <Option value={1}>第一级</Option> 
                        <Option value={2}>第二级</Option>
                        <Option value={3}>第三级</Option>
                        <Option value={4}>第四级</Option>
                    </Select> 
                </div> 
            </div> 
        </form> 
    </Modal> 
    )
  }
});