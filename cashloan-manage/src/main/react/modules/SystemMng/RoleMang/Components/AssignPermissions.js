import React from 'react'; 
import { Tree,Modal } from 'antd';
const TreeNode = Tree.TreeNode; 
export default React.createClass({
  mixins: [   
    ], 
  getInitialState() {
    return { 
      treeData:[],
      checkedKeys:[],
      expandedKeys:[], 
      allCheckedKey:[],
      allExpandedKeys:[],
      menus:[],
      btns:[]
    };
  },   
  handleOk() {
    var me = this;
    var selectRecord= me.props.selectRecord;
    var roleId = selectRecord.id;
    var checkedKeys = me.state.checkedKeys;
    let menus = [];
    let btns = [];
    let check = [];
     //console.log('menus',menus);
     //console.log('btns',checkKeys);
    checkedKeys.forEach((item) => {
      let arr = item.split('-')[1];
      item.indexOf('menu') > -1 ? menus.push(arr) : btns.push(arr);
      check.push(arr);
    })
    Utils.ajaxData({
      url: '/modules/manage/system/menu/save.htm',
      method: 'post',
      type: 'json',
      data: {
        roleId: roleId,
        checkedkey: JSON.stringify(btns),
        menus: JSON.stringify(menus)
      },
      callback: (result) => {
        if (result.msg == "操作成功") {

          Modal.success({
            title: result.msg
          });

          me.props.hideModal();

        }
        else {
          Modal.error({
            title: result.msg
          });
        }
      }
    });  
  },
  handleCancel() {
    this.props.hideModal();
  }, 
  getCheckedKeys(data){

    let checkedKeys = [];
    let loop = data => data.forEach((item)=>{
      if(item.checked === 1){
        checkedKeys.push(`menu-${item.value}`);
      }
      if (item.isPerm) {
        checkedKeys.push(`btn-${item.id}`);
      }

      if(item.children){
        loop(item.children)
      }
      // if(item.isPerm){
      //   checkedKeys.push(item.id);
      // }
      // if(item.children || item.menuPerms){
      //   //  item.children.forEach((item1) => {
          
      //   //    if(item1.menuPerms){
      //   //      checkedKeys.push(item1.id);  
      //   //    }
      //   //  })
      //   //  checkedKeys=checkedKeys.concat(this.getCheckedKeys(item.children || item.menuPerms));
      // }
    })
    loop(data)
    //console.log('checkedKeys',checkedKeys)
    return checkedKeys;
  },
  getExpandedKeys(data){
    var expandedKeys = [];
    data.map((item,index)=>{
      if(item.expanded){
        expandedKeys.push(item.value);
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
    Utils.ajaxData({
            url: '/modules/manage/system/roleMenu/fetch.htm',
            method: 'get', 
            type: 'json',
            data:{
              roleId:roleId,
              node:'root'
            },
            callback: (result) => {
               var checkedKeys = me.getCheckedKeys(result.data);
               var expandedKeys = me.getExpandedKeys(result.data); 
               var allCheckedKey = me.getAllCheckedKeys(result.data);
               var allExpandedKeys = me.getAllExpandedKeys(result.data);
               me.setState({
                 treeData :result.data,
                 checkedKeys:checkedKeys,
                 expandedKeys:expandedKeys, 
                 allCheckedKey:allCheckedKey,
                 allExpandedKeys:allExpandedKeys,
               })
            }
          });
  }, 
  handleCheck (checkedKeys, e){
    let menus = [];
    let btns = [];
    //console.log("checkedKeys",checkedKeys);
    checkedKeys.forEach((item)=> {
       let arr = item.split('-')[1];
       return item.indexOf('menu') > -1 ? menus.push(arr) : btns.push(arr)
   })
    e.halfCheckedKeys.forEach((item)=>{
      return menus.push(item.split("-")[1]);

    });
    //console.log('e.halfCheckedKeys',e.halfCheckedKeys);
    //console.log('menus',menus);
    //console.log('btns',btns);
    this.setState({
          checkedKeys:checkedKeys,
          menus:menus,
          btns:btns,
        });  
  },
  getAllCheckedKeys(data){
    var checkedKeys = [];
      data.map((item,index)=>{ 
        checkedKeys.push(item.value?`menu-${item.value}`:`btn-${item.id}`); 
        if(item.children){
          checkedKeys=checkedKeys.concat(this.getAllCheckedKeys(item.children));
        }
    })
    
    return checkedKeys; 
  },
  getAllExpandedKeys(data){
    var expandedKeys = [];
      data.map((item,index)=>{ 
         expandedKeys.push(item.value?`menu-${item.value}`:`btn-${item.id}`);
        if(item.children){ 
          expandedKeys=expandedKeys.concat(this.getAllCheckedKeys(item.children));
          }
       
       
    })
    return expandedKeys; 
  },
  checkedAll (){
      var allCheckedKey = this.state.allCheckedKey; 
       this.setState({
          checkedKeys:allCheckedKey
        });
  },
  uncheckedAll (){
      this.setState({
          checkedKeys:[]
        });
  },
  expandedAll (){
      var allExpandedKeys = this.state.allExpandedKeys; 
      this.setState({
        expandedKeys:allExpandedKeys
      });
  },
  collapseAll (){ 
      this.setState({
        expandedKeys:[]
      });
  },
  handleExpand(expandedKeys){
     this.setState({
        expandedKeys:expandedKeys
      });
  },
  render() { 
    var state = this. state;  
    var status = state.status;
    var props = this.props;
    const loop = data => data.map((item) => {
      if (item.children ) {
        return <TreeNode title={item.label?item.label:item.name} key={item.id ? `btn-${item.id}` : `menu-${item.value}`}>{loop(item.children)}</TreeNode>; 
      }
      return <TreeNode title={item.label?item.label:item.name} key={item.id ? `btn-${item.id}` : `menu-${item.value}`} isLeaf={item.leaf} />;
    });
    const treeNodes = loop(this.state.treeData); 
    return(
    <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="800"
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
        <div style={{height:'450px',overflowY: 'scroll'}} >
            <Tree 
            // multiple 
            checkable  
            defaultExpandAll  
            onCheck={this.handleCheck} 
            checkedKeys={this.state.checkedKeys} 
            onExpand={this.handleExpand}   
            expandedKeys={this.state.expandedKeys}
             > 
                  {treeNodes}
            </Tree>
        </div> 
    </Modal> 
    )
  }
});