import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Datepicker = antd.Datepicker;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var Actions = require('../actions/CustomerActions');
var FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
var NavLine = require("../../../../common/NavLine"); 
var CheckTable = require('./CheckTable');
var formatDate = require("../../../../common/dateFormat"); 

let business = [];
reqwest({
    url : '/modules/manage/system/dict/cache/list.htm?type=BUSINESS_TYPE',
    method : 'get',
    type : 'json',
    success : (result) => {
        business = result.BUSINESS_TYPE.map((item) => {
            return (<Option key={item.value} value={Number(item.value)}>{item.text}</Option>);
        })
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
function filefujianChange() { 
  var oFiles = document.querySelector("#fileId"); 
  document.querySelector("#imgPath").value = oFiles.value;
}
export default React.createClass({
  mixins: [  
      Validation.FieldMixin,
      formatDate
    ], 
  getInitialState() {
    return { 
          formData:{
            name:'',
            ptype:2,
            loanFrom:null,
            loanEnd:null,
            loanAuditingCycle:'',
            applyCondition:'',
            costListing:'',
            loanDesc:'',
            applyMaterialDesc:'', 
            features:"" 
          },
          status:{ 
            name:{}
          } 
      };
  },  
  componentWillReceiveProps(nextProp){ 
    var title = nextProp.title ; 
    if(title=='上架'){
      this.setState(this.getInitialState());
    }
    var selectRecord = nextProp.selectRecord;
    if(selectRecord){
       if(title=='查看'||title=='编辑'){
        this.setState({
          formData:selectRecord
        })
      }
    }
  }, 
  changeValue(e) { 
      var newValue = this.state.formData;
      var name = e.target.name;
      newValue[name] = e.target.value;
      this.setState({formData:newValue});
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
  selectChange(name,value) { 
    var newValue = this.state.formData;
     if(name=="putawayTime"){
      value = this.formatTime("yyyy-MM-dd hh:mm:ss",value);
     }  
     newValue[name] = value;
    this.setState({formData:newValue});
  }, 
  handleOk() {
    var me = this; 
    var validation = this.refs.validation ; 
        validation.validate((valid) => {
          if (!valid) {
            return;
          } else {
              var newFormData = this.state.formData; 
                newFormData.productId = me.productId ;
                newFormData.publishStatus = 1 ; 
                newFormData.imagesPath =  document.querySelector("#imgPath").value; 
                newFormData.loanFrom = Number(newFormData.loanFrom);
                newFormData.loanEnd = Number(newFormData.loanEnd);
                delete newFormData.ptype; 
              var oFiles = document.querySelector("#fileId");  ;
              var formData = new FormData(); 
              formData.append("json",JSON.stringify(newFormData));
              formData.append("imagesPath", oFiles.files[0]); 

              var url =me.props.title=="上架"?'/modules/cms/manager/action/ProductPutawayAction/insertProductPutawayUploading.htm'
              :'/modules/cms/manager/action/ProductPutawayAction/updateProductPutawayUploading.htm';
               reqwest({
                        url: url,
                        method: 'post', 
                        contentType:false,  
                        processData:false,
                        data:formData,
                        success: (result) => {
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg,
                              onOk: function () {
                                    me.handleCancel();
                                  }
                            }); 
                          }
                          else if(result.code==500)
                          {
                            Modal.error({
                              title: result.msg
                            }); 
                            me.setState({
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
  }, 
  navLineData:{
      "产品信息":"#s1",
      "发布内容":"#s2",
  },
  _isInView(el,target) {
    var container = target;
    var winH = container.clientHeight,
          scrollTop = container.scrollTop,
          scrollBottom = scrollTop + winH,
          elTop = el.offsetTop,
          elBottom = elTop + el.offsetHeight - 10;

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
  productId:"",
  setProductID(value){
    this.productId =  value;
  },
  render() {  
    var me = this;  
    var state = this. state; 
    var status = state.status;
    var formData = state.formData;
    //console.log(formData)
    var props = this.props;  
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          提 交
        </button>
    ];
    var inputFile = (<div className="ant-form-item">
                      <label className="col-4"  htmlFor="filePath">图片：</label> 
                      <div className="col-10"><input id="imgPath" className="ant-input" type="text" /></div>
                      <div className="col-5">  
                            <a href="javascript:;" className="a-upload ant-btn-primary" >
                              <input id="fileId" type="file" name="imagesPath" onChange={filefujianChange}/>浏览
                            </a>
                      </div> 
                </div>); 
    if(this.props.title == '查看')
    {
       modalBtns = [
          <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button> 
        ]
        inputFile=<div> 
                  <img src={formData.imagesPath} />
                </div>
    } 
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="1000"  
        footer={modalBtns} >
          <div style={{position: "relative"}}>
            <div className="navLine-wrap" onScroll={this._handleSpy}>
              <div className="col-21 navLine-wrap-left" >
                <form className="ant-form-horizontal" id="form1">
                    <Validation ref="validation" onValidate={this.handleValidate}>
                      <div id="s1">
                        <h2>产品信息</h2>
                        <div className="ant-form-item">
                          <label className="col-4"  htmlFor="ptype" >业务类型：</label> 
                          <div className="col-8">
                              <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="ptype" value={formData.ptype} onChange={this.selectChange.bind(this, 'ptype')}>
                                    {business}
                              </Select>
                          </div> 
                        </div> 
                        <CheckTable selectRecord={props.selectRecord} canEdit={this.props.canEdit} ptype={formData.ptype} setProductID={this.setProductID}/>
                      </div>
                      <div id="s2">
                        <h2>发布内容</h2> 
                        <div className="ant-form-item">
                              <label className="col-4"  htmlFor="name" required>上架名称：</label>
                              <div className="col-8">
                                <div className={this.renderValidateStyle('name')}>
                                  <Validator rules={[{required: true}]}>
                                       <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="name" value={formData.name} onChange={this.changeValue}/> 
                                  </Validator>    
                                  </div>
                              </div>
                        </div> 
                        {inputFile}
                        <div className="ant-form-item">
                              <label className="col-4"  htmlFor="features" >产品特色：</label>
                              <div className="col-8">
                                  <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="features" value={formData.features} onChange={this.changeValue}/> 
                              </div>
                        </div>    
                        
                        <div className="ant-form-item">
                              <label className="col-4"  htmlFor="loanFrom" >放款区间(万元)：</label> 
                              <div className="col-4">
                                  <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="loanFrom" value={formData.loanFrom} onChange={this.changeValue}/> 
                              </div>
                              <label className="col-1"  htmlFor="loanEnd" >至：</label> 
                              <div className="col-4">
                                  <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="loanEnd" value={formData.loanEnd} onChange={this.changeValue}/> 
                              </div>  
                              <label className="col-3"  htmlFor="loanAuditingCycle" >放款审核周期：</label> 
                              <div className="col-7">
                                  <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="loanAuditingCycle" value={formData.loanAuditingCycle} onChange={this.changeValue}/> 
                              </div> 
                        </div>
                        <div className="ant-form-item"> 
                              <label className="col-4"  htmlFor="applyCondition">申请条件：</label>
                              <div className="col-19">
                                   <textarea className="ant-input" value={formData.applyCondition} name="applyCondition" style={{height:'100px'}}  disabled={!this.props.canEdit} onChange={this.changeValue}/> 
                              </div>  
                        </div>
                        <div className="ant-form-item"> 
                              <label className="col-4"  htmlFor="loanDesc">贷款说明：</label>
                              <div className="col-19">
                                   <textarea className="ant-input" value={formData.loanDesc} name="loanDesc" style={{height:'100px'}} disabled={!this.props.canEdit} onChange={this.changeValue}/> 
                              </div>  
                        </div>
                        <div className="ant-form-item"> 
                              <label className="col-4"  htmlFor="applyMaterialDesc">申请材料说明：</label>
                              <div className="col-19">
                                   <textarea className="ant-input" value={formData.applyMaterialDesc} name="applyMaterialDesc" style={{height:'100px'}} disabled={!this.props.canEdit} onChange={this.changeValue}/> 
                              </div>  
                        </div>
                        <div className="ant-form-item"> 
                              <label className="col-4"  htmlFor="costListing">费用清单：</label>
                              <div className="col-19">
                                   <textarea className="ant-input" value={formData.costListing} name="costListing" style={{height:'100px'}} disabled={!this.props.canEdit} onChange={this.changeValue}/> 
                              </div>  
                        </div>
                        <div className="ant-form-item">
                              <label className="col-4" htmlFor="putawayTime">上架时间：</label>
                              <div className="col-9"> 
                                   <Datepicker style={{width:"100%"}} name="putawayTime" format="yyyy-MM-dd HH:mm:ss" value={formData.putawayTime} onChange={this.selectChange.bind(this, 'putawayTime')}/>
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