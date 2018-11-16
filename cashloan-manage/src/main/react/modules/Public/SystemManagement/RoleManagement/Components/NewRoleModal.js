import React from 'react';
import antd from 'antd';
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;
var Modal = antd.Modal;   
var reqwest = require('reqwest');
var  Actions = require('../actions/Actions');
 
export default React.createClass({
  mixins: [   
    ], 
  getInitialState() {
    return { 
      treeData:[]
      };
  },   
  handleOk() {
    var me = this;
    var selectRecord= this.props.selectRecord;
    var roleId = selectRecord.id;
    var checkedKeys =  this.state.checkedKeys;
    checkedKeys = checkedKeys.map((v,i)=>{
        return Number(v);
    });
        reqwest({
                url: '/modules/manage/system/menu/save.htm',
                method: 'get', 
                type: 'json',
                data:{
                  roleId:roleId,
                  checkedkey:JSON.stringify(checkedKeys)
                },
                success: (result) => { 
                   if(result.success)
                   {
                     Modal.success({
                       title: result.msg
                     }); 
                     Actions.initStore();
                     me.props.hideAddModal();
                   }
                   else  
                   {
                     Modal.error({
                       title: result.msg
                     });  
                   }
                }
              });  
  },
  handleCancel() {
    this.props.hideAddModal();
  }, 
  getCheckedKeys(data){
    var checkedKeys = [];
    data.map((item,index)=>{
      if(item.checked){
        checkedKeys.push(String(item.id));
      }
      if(item.children){
        checkedKeys=checkedKeys.concat(this.getCheckedKeys(item.children));
      }
    })
    return checkedKeys;
  },
  getExpandedKeys(data){
    var expandedKeys = [];
    data.map((item,index)=>{
      if(item.expanded){
        expandedKeys.push(String(item.id));
      }
      if(item.children){
        expandedKeys=expandedKeys.concat(this.getExpandedKeys(item.children));
      }
    })
    return expandedKeys;
  },
  componentWillReceiveProps(nextProp){
    if(nextProp.selectRecord){
      this.queryTreeData(nextProp.selectRecord);
    }
  },
  queryTreeData(selectRecord){
    var selectRecord= selectRecord;
    var roleId = selectRecord.id;
    var me = this;
    reqwest({
            url: '/modules/manage/system/roleMenu/fetch.htm',
            method: 'get', 
            type: 'json',
            data:{
              roleId:roleId,
              node:'root'
            },
            success: (result) => {
               var checkedKeys = me.getCheckedKeys(result);
               var expandedKeys = me.getExpandedKeys(result);
               me.setState({
                 treeData :result,
                 checkedKeys:checkedKeys,
                 expandedKeys:expandedKeys
               })
            }
          });
  },
  componentDidMount() {   
    var selectRecord= this.props.selectRecord; 
    if(selectRecord){ 
      this.queryTreeData(selectRecord);  
    } 
  },
  handleCheck (info){
     var eventKey = info.node.props.eventKey;
     var checkedKeys = this.state.checkedKeys;
     var newCheckedKeys = checkedKeys.filter((v,i)=>{
        var bool = true;
          if(v==eventKey)
          {
            bool = false;
          }
        return bool
     });
     if(newCheckedKeys.length < checkedKeys.length)
     {
        this.setState({
          checkedKeys:newCheckedKeys
        });
     }
     else{
        checkedKeys.push(eventKey);
        this.setState({
          checkedKeys:checkedKeys
        });
     }  
  },
  getAllCheckedKeys(data){
    var checkedKeys = [];
      data.map((item,index)=>{ 
        checkedKeys.push(String(item.id)); 
        if(item.children){
          checkedKeys=checkedKeys.concat(this.getAllCheckedKeys(item.children));
        }
    })
    return checkedKeys; 
  },
  getAllExpandedKeys(data){
    var expandedKeys = [];
      data.map((item,index)=>{ 
        if(item.children){
          if(item.expanded){
            expandedKeys.push(String(item.id)); 
          }
          expandedKeys=expandedKeys.concat(this.getAllCheckedKeys(item.children));
        }
    })
    return expandedKeys; 
  },
  checkedAll (){
      var treeData = this.state.treeData;
      var checkedKeys = this.getAllCheckedKeys(treeData);
       this.setState({
          checkedKeys:checkedKeys
        });
  },
  uncheckedAll (){
      this.setState({
          checkedKeys:[]
        });
  },
  expandedAll (){
      var treeData = this.state.treeData;
      var expandedKeys = this.getAllExpandedKeys(treeData); 
      this.setState({
        expandedKeys:expandedKeys
      });
  },
  collapseAll (){ 
      this.setState({
        expandedKeys:["0"]
      });
  },
  render() {  
    var state = this. state; 
    var formData = state.formData;
    var status = state.status;
    var props = this.props;
    
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
    let treeNodes = parseTreeNode(this.state.treeData);
    
    return(
    <Modal title={props.title} visible={props.RoleModal} onOk={this.handleOk} onCancel={this.handleCancel} width="600"
      footer={[
          <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
            确 定
          </button>,
          <button key="back" className="ant-btn" onClick={this.handleCancel}>取 消</button> 
        ]} >  
        <div className="actionBtns">
           <button className="ant-btn" onClick={this.checkedAll}> 
             全选
           </button>
           <button className="ant-btn" onClick={this.uncheckedAll}> 
             全不选
           </button>
           <button className="ant-btn" onClick={this.expandedAll}> 
             展开所有
           </button>
           <button className="ant-btn" onClick={this.collapseAll}> 
             收缩所有
           </button> 
        </div>
        <div style={{height:'450px',overflowY: 'scroll'}}>
            <Tree  checkable onCheck={this.handleCheck} checkedKeys={this.state.checkedKeys} expandedKeys={this.state.expandedKeys}  >
              {treeNodes}
            </Tree>
        </div> 
    </Modal> 
    )
  }
});