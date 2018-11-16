var React = require('react');
import antd from 'antd';  
var Select = antd.Select;
var Option = Select.Option; 
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;
import TreeSelect from 'rc-tree-select';
var reqwest = require('reqwest');
import 'rc-tree-select/assets/index.css';
import 'rc-tree/assets/index.css'; 
var  Actions = require('../actions/Actions');
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
export default React.createClass({ 
    getInitialState() {
        return { 
          formData:{
            number:"",
            name: "",
            status: "",
            roleId:"" 
          },
          roleList:[] 
        };
    },
    resetData (){
      var newValue = {
          number:"",
          name: "",
          status:undefined,
          roleId:undefined
      };
      this.setState({
        formData:newValue
      });
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
    postMessage() {  
        var searchData = this.state.formData ; 
        Actions.postMessage(searchData);  
    },
    render() { 
        var formData = this.state.formData;
        const overlayOfficeId = (<div style={{height:'450px',overflowY: 'scroll'}}><Tree defaultExpandAll={true} 
              onSelect={this.handleSelectOfficeId}>
          {treeNodes}
        </Tree></div>);
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="name">模板名称：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="name"  value={formData.name} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                   <label htmlFor="parentId">模板类型：</label>
                         <TreeSelect trigger={['click']}  
                            closeOnSelect={true}  visible={this.state.visible} onVisibleChange={this.onVisibleChange}
                            overlay={overlayOfficeId} animation="slide-up">
                           <input key={Date.now()} className="ant-input" defaultValue={formData.parent_text} readOnly/>
                         </TreeSelect>
               </div>                  
               <div className="ant-form-item">
                  <input type="button" className="ant-btn ant-btn-primary" value="查 询" onClick={this.postMessage}/>
               </div>
               <div className="ant-form-item">
                  <input type="reset" className="ant-btn ant-btn-primary btn-reset" value="重 置" onClick={this.resetData}/>
               </div>
             </form>
        );
    }
});
 
