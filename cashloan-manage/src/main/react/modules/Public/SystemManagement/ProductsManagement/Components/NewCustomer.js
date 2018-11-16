import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option; 
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;
import TreeSelect from 'rc-tree-select';
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var reqwest = require('reqwest');
var HandleData = require("../../../../common/HandleData");
var Actions = require('../actions/Actions');
var NavLine = require("../../../../common/NavLine");
import 'rc-tree-select/assets/index.css';
import 'rc-tree/assets/index.css';
/*var  product_typeList=[];
 reqwest({
            url: '/modules/common/action/ComboAction/queryDic.htm?where.dic.type_code=PRODUCT_TYPE',
            method: 'get', 
            type: 'json',
            success: (result) => {
               product_typeList  = result.map((item)=>{
                  return (<Option key={item.value} value={Number(item.value)}>{item.text}</Option>);
                }); 
            }
          });*/
var  product_typeList=[];
 reqwest({
            url: '/modules/fel/FelproductAction/Select.htm',
            method: 'get', 
            data:{
               start:0,
               limit:100
            },
            type: 'json',
            success: (result) => {
               product_typeList  = result.data.map((item)=>{
                  return (<Option key={item.id} value={Number(item.id)}>{item.productType}</Option>);
                }); 
            }
          });
var  periodList=[];
 reqwest({
        url: '/modules/manage/system/dict/cache/list.htm?type=LOAN_PERIOD_CAR',
        method: 'get', 
        type: 'json',
        success: (result) => {
          var items  = result.LOAN_PERIOD_CAR.map((item)=>{
              return (<Option key={item.value} value={Number(item.value)}>{item.text}</Option>);
            });
           periodList = items;
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
            url: '/modules/system/checkboxoffices.htm',
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
export default React.createClass({
  mixins: [ 
      HandleData,
      Validation.FieldMixin
    ], 
  getInitialState() {
    return { 
        visible: false,      
        status: { 
          repayment_type:{},
          minmlimit:{},
          maxmlimit:{},
          delay:{},
          product_type:{},
          collateral_type:{},
          mortgage_type:{},
          name:{},
          period:{},
          gps_use_fee:{},
          repayment_rate:{},
          borrow_rate:{},
          overdue_penalty_rate:{},
          letter_on_fee:{},
          overdue_rate:{},
          overdue_period:{},
          consult_fee:{},
          survey_fee:{},
          service_fee:{},
          isopen:{}, 
          office_ids:{},
          margin_fee:{},
          notarial_fee:{},
          vehicle_valuation_fee:{},
          illegal_disposal_fee:{},
          parking_fee:{},
          management_fee:{},
          other_fee:{},
          gps_install_fee:{},
          no_illegal_fee:{},
          no_gps_fee:{},
          no_margin_fee:{},
          remark:{},
          one_prepayment:{},
          two_prepayment:{},
          three_prepayment:{},
          four_prepayment:{},
          five_prepayment:{},
          six_prepayment:{}
        }, 
        formData:{} 
      };
  }, 
  changeValue(e) { 
      var newValue = this.state.formData;
      var name = e.target.name;
      newValue[name] = Number(e.target.value);
      this.setState({formData:newValue});
  }, 
  selectChange(name,value) { 
    var newValue = this.state.formData; 
      newValue[name] = value; 
    this.setState({formData:newValue});
  },  
  handleOk() {
    var me = this;
    var url ='/modules/system/productAction/saveCarProduct.htm';
    var Actions = require('../actions/Actions');
    this.submit(me,url,Actions)
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
    //console.log(111)
      var officeOverNameArry = [];
      officeOverNameArry = info.checkedKeys.map((v,i)=>{ 
        return treeData[v];
      }); 
      officeOverNameArry.reverse();
      var officeOverName = officeOverNameArry.join(',');
      var officeOverId = info.checkedKeys.join(','); 
      var newFormData = this.state.formData;
      newFormData.officeOverName = officeOverName;
      newFormData.officeOverId = officeOverId;
      this.setState({
        visible: false,
        formData: newFormData
      }); 
      this.mechanism("", officeOverName,"")
      
  }, 
  onVisibleChange(visible) {
    this.setState({
      visible: visible
    });
  },
  queryFormData(selectRecord,visible,title){
    var me = this; 
    if(visible==true&&selectRecord&&title=="修改"){
      var id = selectRecord.id;
      reqwest({
                url: '/modules/system/productAction/loadProduct.htm'
                , method: 'get'
                ,data: {
                     id:id,
                  }
                , type: 'json' 
                , success: (result) => { 
                   var newValue = result;
                   newValue.maxmlimit= newValue.maxmlimit/10000;
                   newValue.minmlimit= newValue.minmlimit/10000;
                   newValue.repayment_rate= this.accMul(newValue.repayment_rate,100);
                   newValue.borrow_rate = this.accMul(newValue.borrow_rate,100);
                   newValue.consult_fee= this.accMul(newValue.consult_fee,100);
                   newValue.service_fee= this.accMul(newValue.service_fee,100);
                   newValue.overdue_rate= this.accMul(newValue.overdue_rate,100);
                   newValue.overdue_penalty_rate= this.accMul(newValue.overdue_penalty_rate,100);
                   newValue.one_prepayment= this.accMul(newValue.one_prepayment,100);
                   newValue.two_prepayment= this.accMul(newValue.two_prepayment,100);
                   newValue.three_prepayment= this.accMul(newValue.three_prepayment,100);
                   newValue.officeOverName= newValue.office_idsText;
                   me.setState({formData:newValue});  
                } 
            });
    }
    else if(title=="新建车贷产品"){
      var formData = this.state.formData;
      var newValue = formData;
      Object.keys(newValue).forEach(name=>{  
          newValue[name]=""; 
      });
      this.setState({formData:newValue});
    }
  },
  componentWillReceiveProps(nextProps) {  
    this.queryFormData(nextProps.selectRecord,nextProps.visible,nextProps.title);
  },
  componentDidMount(){ 
    this.queryFormData(this.props.selectRecord,this.props.visible,this.props.title);
  },
  navLineData:{
       "产品基本信息":"#s1",
       "提前还款服务费比例":"#s2",
  },
   _isInView(el,target) {
    var container = target;
    var winH = container.clientHeight,
          scrollTop = container.scrollTop,
          scrollBottom = scrollTop + winH,
          elTop = el.offsetTop,
          elBottom = elTop + el.offsetHeight - 20;

        return (elTop < scrollBottom) && (elBottom > scrollTop); 
  }, 
  _handleSpy(e){ 
    var navLineData =  this.navLineData;
    var items = Object.keys(navLineData).map((key,i)=>{
      return navLineData[key].substring(1);
    });
    var targetItems = items.map(function (item) {
      return document.getElementById(item);
    });
    var hasInViewAlready = false;
    targetItems.forEach((el,index)=>{
      if(!hasInViewAlready){
          if(this._isInView(el,e.target)){
          this.refs.NavLine.handleClickItem('#'+items[index]);
          hasInViewAlready = true;
        }
      } 
    });
  },
  bisSmartDevice() {
    var sUserAgent = navigator.userAgent.toLowerCase();    
      var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";    
      var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";    
      var bIsMidp = sUserAgent.match(/midp/i) == "midp";    
      var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";    
      var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";    
      var bIsAndroid = sUserAgent.match(/android/i) == "android";    
      var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";    
      var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";   
      if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM ){    
          return true
      }  
      else return false;
  }, 
  mechanism(rule, value, callback) { 
   var me =this; 
   var formData = me.state.formData;
   var officeOverName = formData.officeOverName;
   if(officeOverName){
      if(callback){
        callback()
      }else{
        if(me.refs.validation.forceValidate(['office_ids'])){me.refs.validation.forceValidate(['office_ids']).reset()}
      }
   }
   else{
    if(callback){
       callback([new Error('必填项')]);
    }else{
      if(me.refs.validation.forceValidate(['office_ids'])){me.refs.validation.forceValidate(['office_ids'])}
    }
   } 

  },
  render() {  
    var state = this. state; 
    var formData = state.formData;
    var status = state.status; 
    var props = this.props;
    var canEdit = !props.canEdit||(props.title=="修改"?true:false); 
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        <button key="button" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
          提 交
        </button>
    ];
    var bisSmartDevice = this.bisSmartDevice();
    var width="1200"; 
    if(bisSmartDevice){
      width = "950";  
    } 
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
    <Modal title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width={width} 
      footer={modalBtns} >  
      <div style={{position: "relative"}}>
        <div className="navLine-wrap" onScroll={this._handleSpy}>
          <div className="col-20 navLine-wrap-left" >  
          <form className="ant-form-horizontal" ref="myform">
            <Validation ref="validation" onValidate={this.handleValidate}> 
              <div id="s1">
                  <h2>产品基本信息</h2>  
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="product_type" required>产品类型：</label>
                        <div className="form-input">
                              <div className={this.renderValidateStyle('product_type')}>
                                <Validator rules={[{type:"all"}]}>
                                  <Select size="large" style={{width:"100%"}}  disabled={!props.canEdit} name="product_type" value={formData.product_type} onSelect={this.selectChange.bind(this, 'product_type')}>
                                      {product_typeList} 
                                  </Select>
                                </Validator>  
                            </div>
                        </div>
                  </div>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="mortgage_type" required>抵押类型：</label>
                        <div className="form-input">
                              <div className={this.renderValidateStyle('mortgage_type')}>
                                <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                  <Select size="large" style={{width:"100%"}}  disabled={!props.canEdit} name="mortgage_type" value={formData.mortgage_type} onSelect={this.selectChange.bind(this, 'mortgage_type')}>
                                      <Option key="1" value={1}>移交</Option>
                                      <Option key="2" value={2}>GPS</Option> 
                                  </Select>
                                </Validator>  
                            </div>
                        </div>
                  </div>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="name" required>产品名称：</label>
                        <div className="form-input">
                          <div className={this.renderValidateStyle('name')}>
                                <Validator rules={[{required: true}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="name" value={formData.name} onChange={this.changeValue}/>
                                </Validator> 
                          </div>
                        </div> 
                  </div> 
                  
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="period" required>借款期数：</label>
                        <div className="form-input">
                          <div className={this.renderValidateStyle('period')}>
                             <Validator rules={[{type:"all"}]}>
                                <Select size="large" style={{width:"100%"}}  disabled={!props.canEdit} name="period" value={formData.period} onSelect={this.selectChange.bind(this, 'period')}>
                                      {periodList} 
                                </Select>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="repayment_type" required>还款方式：</label>
                        <div className="form-input">
                              <div className={this.renderValidateStyle('repayment_type')}>
                                <Validator rules={[{type:"all"}]}>
                                  <Select size="large" style={{width:"100%"}}  disabled={!props.canEdit} name="repayment_type" value={formData.repayment_type} onSelect={this.selectChange.bind(this, 'repayment_type')}>
                                      <Option key="1" value={1}>先息后本</Option>
                                      <Option key="2" value={2}>等额本息</Option> 
                                  </Select>
                                </Validator>  
                            </div>
                        </div>
                  </div>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="repayment_rate" required style={{width:"104px"}}>月费率(%/月)：</label>
                        <div className="form-input" style={{width:"125px"}}>
                          <div className={this.renderValidateStyle('repayment_rate')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="repayment_rate" value={formData.repayment_rate} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="borrow_rate" required style={{width:"104px"}}>月利率(%/月)：</label>
                        <div className="form-input" style={{width:"125px"}}>
                          <div className={this.renderValidateStyle('borrow_rate')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="borrow_rate" value={formData.borrow_rate} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="parking_fee" required  style={{width:"93px"}}>停车费(元/月)：</label>
                        <div className="form-input"  style={{width:"136px"}}>
                          <div className={this.renderValidateStyle('parking_fee')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="parking_fee" value={formData.parking_fee} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="management_fee" required  style={{width:"93px"}}>平台服务费(元/月)：</label>
                        <div className="form-input"  style={{width:"136px"}}>
                          <div className={this.renderValidateStyle('management_fee')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="management_fee" value={formData.management_fee} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="other_fee" required  style={{width:"93px"}}>其他费用(元)：</label>
                        <div className="form-input"  style={{width:"136px"}}>
                          <div className={this.renderValidateStyle('other_fee')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="other_fee" value={formData.other_fee} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="delay" required>是否可以展期：</label>
                        <div className="form-input">
                              <div className={this.renderValidateStyle('delay')}>
                                <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                  <Select size="large" style={{width:"100%"}}  disabled={!props.canEdit} name="delay" value={formData.delay} onSelect={this.selectChange.bind(this, 'delay')}>
                                      <Option key="1" value={1}>是</Option>
                                      <Option key="2" value={0}>否</Option> 
                                  </Select>
                                </Validator>  
                            </div>
                        </div>
                  </div>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="consult_fee" required style={{width:"113px"}}>咨询费(%/月)：</label>
                        <div className="form-input" style={{width:"116px"}}>
                          <div className={this.renderValidateStyle('consult_fee')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="consult_fee" value={formData.consult_fee} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div> 
                  </div>
                  <div className="form-line">
                        <label className="form-label"  htmlFor="service_fee" required style={{width:"101px"}}>服务费(%/月)：</label>
                        <div className="form-input" style={{width:"128px"}}>
                          <div className={this.renderValidateStyle('service_fee')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="service_fee" value={formData.service_fee} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  <div className="form-line">
                        <label className="form-label"  htmlFor="overdue_rate" required style={{width:"125px"}}>逾期违约金利率(%/月)：</label>
                        <div className="form-input" style={{width:"104px"}}>
                          <div className={this.renderValidateStyle('overdue_rate')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="overdue_rate" value={formData.overdue_rate} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="overdue_penalty_rate" required style={{width:"104px"}}>罚息利率(%/日)：</label>
                        <div className="form-input" style={{width:"125px"}}>
                          <div className={this.renderValidateStyle('overdue_penalty_rate')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="overdue_penalty_rate" value={formData.overdue_penalty_rate} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div> 
                  </div> 
                  <div className="form-line">
                        <label className="form-label"  htmlFor="maxmlimit" required style={{width:"118px"}}>最高贷款金额(万元)：</label>
                        <div className="form-input" style={{width:"111px"}}>
                          <div className={this.renderValidateStyle('maxmlimit')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="maxmlimit" value={formData.maxmlimit} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  <div className="form-line">
                        <label className="form-label"  htmlFor="minmlimit" required style={{width:"118px"}}>最低贷款金额(万元)：</label>
                        <div className="form-input" style={{width:"111px"}}>
                          <div className={this.renderValidateStyle('minmlimit')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="minmlimit" value={formData.minmlimit} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  <div className="ant-form-item">
                          <label className="form-label"  htmlFor="office_ids" required>适用机构：</label>
                          <div className="form-input" style={{width:"625px"}}>
                              <TreeSelect trigger={['click']}  
                                 closeOnSelect={false}
                                 overlay={overlay} animation="slide-up">
                             <div className={this.renderValidateStyle('office_ids')}>
                             <Validator rules={[{validator:this.mechanism}]}>
                                <input key={Date.now()} className="ant-input" value={formData.officeOverName} name="office_ids" readOnly />
                               </Validator> 
                            </div>
                              </TreeSelect>
                          </div>
                    </div> 
              </div> 
              <div id="s2" style={{marginBottom: "400px"}}>
                  <h2>提前还款服务费比例</h2>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="one_prepayment" required style={{width:"108px"}}>提前还款A(%)：</label>
                        <div className="form-input" style={{width:"121px"}}>
                          <div className={this.renderValidateStyle('one_prepayment')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="one_prepayment" value={formData.one_prepayment} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  <div className="form-line">
                        <label className="form-label"  htmlFor="two_prepayment" required style={{width:"108px"}}>提前还款B(%)：</label>
                        <div className="form-input" style={{width:"121px"}}>
                          <div className={this.renderValidateStyle('two_prepayment')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="two_prepayment" value={formData.two_prepayment} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>
                  <div className="form-line"> 
                        <label className="form-label"  htmlFor="three_prepayment" required style={{width:"108px"}}>提前还款C(%)：</label>
                        <div className="form-input" style={{width:"121px"}}>
                          <div className={this.renderValidateStyle('three_prepayment')}>
                             <Validator rules={[{required: true,pattern: /^(\d*\.)?\d+$/}]}>
                                <input className="ant-input" type="text" disabled={!props.canEdit}  name="three_prepayment" value={formData.three_prepayment} onChange={this.changeValue}/>
                             </Validator> 
                          </div>
                        </div>
                  </div>  
              </div>    
            </Validation>
          </form> 
          </div>
          <div className="navLine-wrap-right" >
              <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
          </div>
        </div>
      </div>  
    </Modal> 
    )
  }
});
