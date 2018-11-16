import React from 'react';
import antd from 'antd';   
var Select = antd.Select;
var Option = Select.Option;
import { Tree } from 'antd';
import { Validation} from 'antd';  
var Validator = Validation.Validator; 
const TreeNode = Tree.TreeNode;
import TreeSelect from 'rc-tree-select';
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
import 'rc-tree-select/assets/index.css';
import 'rc-tree/assets/index.css';
var reqwest = require('reqwest');
var  CustomerActions = require('../actions/CustomerActions');
function cx(classNames) {
  if (typeof classNames === 'object') {
    return Object.keys(classNames).filter(function(className) {
      return classNames[className];
    }).join(' ');
  } else {
    return Array.prototype.join.call(arguments, ' ');
  }
}
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
            url: '/modules/system/checkboxoffices.htm',
            method: 'get', 
            type: 'json',
            success: (result) => {   
                treeNodes = parseTreeNode(result);
            }
          });
export default React.createClass({
  mixins: [ 
      Validation.FieldMixin
    ], 
  getInitialState() {
    var props = this.props;
    var cityData = props.cityData; 
    var selectCityData = cityData.selectData; 
      var areaIdleve1  = cityData.areaIdleve1&&cityData.areaIdleve1.map((item)=>{
                       return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                     });
      var areaIdleve2  = cityData.areaIdleve2&&cityData.areaIdleve2.map((item)=>{
                       return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                     });
      var areaIdleve3  = cityData.areaIdleve3&&cityData.areaIdleve3.map((item)=>{
                       return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                     });
    return {
        roleList:{},
        status: {
          Level0id:{},
          address:{}
        }, 
        formData:{
          name:"",
          parentId:"",
          sort:"",
          type:"",
          officeNumber:"",
          officeCard:"",
          address:"",
          area:"",
          phone:"",
          officeBank:"",
          officeBankCard:"",
          isDelete:"",
          remark:"",
        },
        cityData:this.props.cityData,
        areaIdleve1:areaIdleve1?areaIdleve1:"",
        areaIdleve2:areaIdleve2?areaIdleve2:"",
        areaIdleve3:areaIdleve3?areaIdleve3:"",
        Level0id:selectCityData?JSON.stringify(selectCityData.Level0id):"",
        Level1id:selectCityData?JSON.stringify(selectCityData.Level1id):"",
        Level2id:selectCityData?JSON.stringify(selectCityData.Level2id):"", 
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
  citySelectChange(name,value){
    var newValue = this.state;
    newValue[name] = value;
     this.setState(newValue);
     if(name =='Level0id')
       {
        this.areaIdleveData('areaIdleve2',value); 
       } 
     else if(name =='Level1id')
        this.areaIdleveData('areaIdleve3',value);
  }, 
  selectChange(name,value) { 
    var newValue = this.state.formData;
      newValue[name] = value;
    if(name =="Level2id"){
      newValue['liveareaid'] = value;
      this.setState({Level2id:value});
    }
    this.setState({formData:newValue}); 
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
  handleOk() {
    var validation = this.refs.validation;
     validation.validate((valid) => {
      if (!valid) {
              return;
      } 
      else {
        status = 'create';    
        var formData = this.state.formData;
        formData.city=this.state.Level2id;
   
              if(this.props.title=="增加机构")
              { 
                status = 'create';
              }
              else if(this.props.title=="修改机构")
              {
                status = 'update';
              }
               reqwest({
                        url: '/modules/system/addoffice.htm',
                        method: 'post', 
                        data:{
                          office:JSON.stringify(formData),
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
      }
    })  
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
  areaIdleveData(areaIdleve,Levelid){
      var me = this;
      reqwest({
              url: '/modules/common/action/PlAreaAction/getAreaListByParentLeave.htm',
              method: 'get', 
              data: {parentid:Levelid},
              type: 'json',
              success: (result) => { 
                var newValue = {};
                var items  = result.data.map((item)=>{
                        return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                               }); 
                newValue[areaIdleve] = items;
                 
                if (areaIdleve=='areaIdleve2') { 
                  me.setState({areaIdleve2:items});
                  me.setState({
                    Level1id:JSON.stringify(result.data[0].id)
                  });
                  me.areaIdleveData('areaIdleve3',result.data[0].id);
                }
                else if(areaIdleve=='areaIdleve3') { 
                  me.setState({areaIdleve3:items});
                  var newValue = this.state.formData ;
                  newValue["liveareaid"] = result.data[0].id;
                  me.setState({newValue});
                  me.setState({
                    Level2id:JSON.stringify(result.data[0].id)
                  }); 
                };
              }
           });
  },
  queryFormData(selectRecord,visible,title){
    var me = this; 
    if(visible==true&&selectRecord&&title=="修改机构"){
      var id = selectRecord.id;
      reqwest({
                url: '/modules/system/getOfficeInfoById.htm'
                , method: 'get'
                ,data: {
                     id:id,
                  }
                , type: 'json' 
                , success: (result) => { 
                   var newValue = result.data; 
                   newValue.isDelete = JSON.stringify(newValue.isDelete);
                   newValue.type = JSON.stringify(newValue.type); 
                   me.setState({formData:newValue});  
                } 
            });
    }
    else if(title=="增加机构"&&selectRecord){
      var formData = this.state.formData;
      var newValue = formData;
      Object.keys(newValue).forEach(name=>{  
          newValue[name]=""; 
      });
      newValue.parentId =selectRecord.id;
      newValue.parent_text =selectRecord.name;
      this.setState({formData:newValue});
    }
  },
  componentWillReceiveProps(nextProps) {
    var cityData = nextProps.cityData; 
    var selectCityData = cityData.selectData; 
    
    var areaIdleve1  = cityData.areaIdleve1&&cityData.areaIdleve1.map((item)=>{
                     return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                   });
    var areaIdleve2  = cityData.areaIdleve2&&cityData.areaIdleve2.map((item)=>{
                     return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                   });
    var areaIdleve3  = cityData.areaIdleve3&&cityData.areaIdleve3.map((item)=>{
                     return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                   }); 
    this.setState({
      areaIdleve1:areaIdleve1?areaIdleve1:"",
      areaIdleve2:areaIdleve2?areaIdleve2:"",
      areaIdleve3:areaIdleve3?areaIdleve3:"",
      Level0id:selectCityData?JSON.stringify(selectCityData.Level0id):"",
      Level1id:selectCityData?JSON.stringify(selectCityData.Level1id):"",
      Level2id:selectCityData?JSON.stringify(selectCityData.Level2id):"",
    })                 
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
    const overlayOfficeId = (<div style={{height:'450px',overflowY: 'scroll'}}><Tree defaultExpandAll={true} 
              onSelect={this.handleSelectOfficeId}>
          {treeNodes}
        </Tree></div>);
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="800">  
        <form className="ant-form-horizontal" ref="myform">  
          <Validation ref="validation" onValidate={this.handleValidate} >            
            <div className="ant-form-item">    
                <label className="col-3"  htmlFor="name" required>机构名称：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="name" value={formData.name} onChange={this.changeValue}/> 
                </div>    
                <label className="col-4"  htmlFor="parentId">上级：</label>
                <div className="col-8">
                      <TreeSelect trigger={['click']}  
                         closeOnSelect={true}  visible={this.state.visible} onVisibleChange={this.onVisibleChange}
                         overlay={overlayOfficeId} animation="slide-up">
                        <input key={Date.now()} className="ant-input" value={formData.parent_text} readOnly/>
                      </TreeSelect>
                </div> 
            </div> 
            <div className="ant-form-item">    
                <label className="col-3"  htmlFor="sort" required>排序：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="sort" value={formData.sort} onChange={this.changeValue}/> 
                </div>   
                <label className="col-4"  htmlFor="type" required>类型：</label>
                <div className="col-8">
                    <Select size="large"  disabled={this.props.canEdit} style={{width:"100%"}} name="type" value={formData.type} onSelect={this.selectChange.bind(this, 'type')}>
                        <Option value="0">职能部门</Option> 
                        <Option value="1">公司</Option>
                        <Option value="3">销售部门</Option>
                        <Option value="2">分公司</Option>
                    </Select> 
                </div>  
            </div>
            <div className="ant-form-item">    
                <label className="col-3"  htmlFor="officeNumber">机构序号：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="officeNumber" value={formData.officeNumber} onChange={this.changeValue}/> 
                </div> 
                <label className="col-4"  htmlFor="officeCard">机构代码证号：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="officeCard" value={formData.officeCard} onChange={this.changeValue}/> 
                </div>
            </div> 
            <div className="ant-form-item" >
                  <label  className="col-3"  htmlFor="Level0id" required>机构地址：</label> 
                  <div className="form-input" style={{marginRight: "3px",width:'430px'}}>
                     <div className={this.renderValidateStyle('Level0id', false)}> 
                       <Validator rules={[{required: true}]}>
                        <Select size="large" style={{marginRight: "3px",width:"140px"}} name="Level0id" value={state.Level0id} onChange={this.citySelectChange.bind(this, 'Level0id')}>
                           {this.state.areaIdleve1?this.state.areaIdleve1:null} 
                        </Select>
                        </Validator>
                        <Select size="large" style={{marginRight: "3px",width:"140px"}} name="Level1id" value={state.Level1id} onChange={this.citySelectChange.bind(this, 'Level1id')}>
                           {this.state.areaIdleve2?this.state.areaIdleve2:null}
                        </Select>
                        <Select size="large" style={{marginRight: "3px",width:"140px"}} name="Level2id" value={state.Level2id} onChange={this.selectChange.bind(this, 'Level2id')}>
                           {this.state.areaIdleve3?this.state.areaIdleve3:null}
                        </Select>
                     </div>    
                  </div>
            </div> 
            <div className="ant-form-item">    
                <label className="col-3"  htmlFor="area">区号：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="area" value={formData.area} onChange={this.changeValue}/> 
                </div> 
                <label className="col-4"  htmlFor="phone">机构电话：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="phone" value={formData.phone} onChange={this.changeValue}/> 
                </div>
            </div> 
            <div className="ant-form-item">    
                <label className="col-3"  htmlFor="officeBank">银行：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="officeBank" value={formData.officeBank} onChange={this.changeValue}/> 
                </div> 
                <label className="col-4"  htmlFor="officeBankCard">银行卡号：</label>
                <div className="col-8">
                    <input className="ant-input" type="text" disabled={this.props.canEdit}  name="officeBankCard" value={formData.officeBankCard} onChange={this.changeValue}/> 
                </div>
            </div> 
            <div className="ant-form-item">    
                <label className="col-3"  htmlFor="isDelete" required>状态：</label>
                <div className="col-8">
                    <Select size="large"  disabled={this.props.canEdit} style={{width:"100%"}} name="isDelete" value={formData.isDelete} onSelect={this.selectChange.bind(this, 'isDelete')}>
                        <Option value="0">正常</Option> 
                        <Option value="1">禁用</Option>
                    </Select> 
                </div> 
            </div>
            <div className="ant-form-item"> 
               <label  className="col-3"  htmlFor="remark">备注：</label>
               <div className="col-20">
                  <textarea className="ant-input" type="text" disabled={this.props.canEdit} style={{height:80,resize:"none"}} name="remark" value={formData.remark} onChange={this.changeValue}/>
               </div>
            </div> 
          </Validation>    
        </form> 
    </Modal> 
    )
  }
});