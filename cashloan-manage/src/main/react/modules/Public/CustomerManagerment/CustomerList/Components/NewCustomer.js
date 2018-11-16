import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;  
var Datepicker = antd.Datepicker; 
var FormStore = require('../stores/FormStore');
var formatDate = require("../../../../common/dateFormat"); 
var IdCardValidate = require("../../../../utils/IdCardValidate"); 
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');
var NavLine = require("../../../../common/NavLine");
var reqwest = require('reqwest');
function cx(classNames) {
  if (typeof classNames === 'object') {
    return Object.keys(classNames).filter(function(className) {
      return classNames[className];
    }).join(' ');
  } else {
    return Array.prototype.join.call(arguments, ' ');
  }
}
var customerManager={
  id:"",
  name:""
}
reqwest({
          url: '/modules/common/action/ComboAction/querySysUser.htm'
          , method: 'get'
          , type: 'json' 
          , success: (result) => {  
            var newValue ={};
            newValue.id =result.data.id;
            newValue.name =result.data.name;
            customerManager = newValue;
                
        }
      }) ;
var sexList=[];
reqwest({
        url: '/modules/manage/system/dict/cache/list.htm?type=SEX',
        method: 'get', 
        type: 'json',
        success: (result) => {
          var items  = result.SEX.map((item)=>{
              return (<Option key={item.value} value={item.value}>{item.text}</Option>);
            });
           sexList = items;
        }
      });
var certTypeChildren=[];
  reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=ID_TYPE',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.ID_TYPE.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   certTypeChildren=items;
                }
              });
var statusChildren=[];
        reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=CUSTOMER_STATUS',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.CUSTOMER_STATUS.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   statusChildren=items;
                }
              });
var marryStatusList=[];
        reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=MARRY_STATUS',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.MARRY_STATUS.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   marryStatusList=items;
                }
              });
var educationList=[];
        reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=EDUCATION',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.EDUCATION.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   educationList=items;
                }
              });
var liveStateList=[];
        reqwest({
                url: '/modules/common/action/ComboAction/queryDic.htm?where.dic.type_code=COMBO_HOUSE',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   liveStateList=items;
                }
              });
var yearsIncomeList=[]; 
        reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=YEAR_INCOME',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.YEAR_INCOME.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   yearsIncomeList=items;
                }
              });
var companyProList=[];
        reqwest({
                url: '/modules/common/action/ComboAction/queryDic.htm?where.dic.type_code=CORPORATION_TYPE',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   companyProList=items;
                }
              });
var healthyList=[];
        reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=HEALTHY',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.HEALTHY.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   healthyList=items;
                }
              });
           
var NewCustomer = React.createClass({
  mixins: [  
      Reflux.connect(FormStore, 'formData'),
      formatDate,
      IdCardValidate,
      Validation.FieldMixin
    ], 
  componentWillReceiveProps(nextProp){ 
      
      if(nextProp.title =="新增用户")
      { 
        var cityData = nextProp.cityDataOnlyAdd; 
        var areaIdleve1  = cityData.areaIdleve1&&cityData.areaIdleve1.map((item)=>{
                         return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
                       });
        var areaIdleve1  =  areaIdleve1; 
        var areaIdleve2  =  null; 
        var areaIdleve3  = null; 
        this.setState({ 
                        areaIdleve1:areaIdleve1,
                        areaIdleve2:areaIdleve2,
                        areaIdleve3:areaIdleve3,
                        Level0id:undefined,
                        Level1id:undefined,
                        Level2id:undefined 
                      }); 
        return;
      }
      else {
        var cityData = nextProp.cityData;
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
                      areaIdleve1:areaIdleve1,
                      areaIdleve2:areaIdleve2,
                      areaIdleve3:areaIdleve3,
                      Level0id:JSON.stringify(selectCityData.Level0id),
                      Level1id:JSON.stringify(selectCityData.Level1id),
                      Level2id:JSON.stringify(selectCityData.Level2id)
                    }); 
       }
    },
  getInitialState() {
    return { 
          formData:{},
          postData:{},
          customerManager:{},
          status:{ 
            id:{},
            status:{},
            name:{},
            certType:{},
            mobile:{},
            email:{},
            fixedPhone:{},
            householdZipCode:{},
            liveZipCode:{},
            companyZipCode:{},
            companyPhone:{},
            certNumber:{},
            Level0id:{}, 
            liveAddress:{},
            sex:{},
            sesx:{},
            marryStatus:{},
            isLocalHouse:{},
            otherCase:{},
            education:{},
            healthy:{},
            householdAddress:{},
            spouseIskown:{},
            emergencyContactNumber:{},
            isHaveChildren:{}
          }, 
          ListSource:[],
          certTypeChildren:[], 
          statusChildren:[],
          sexList:[], 
          healthyList:[], 
          marryStatusList:[], 
          liveStateList:[],
          yearsIncomeList:[],
          companyProList:[],
          areaIdlevel:[],
          areaIdleve2:[],
          areaIdleve3:[],
          
      };
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
    if(name =="Level2id"){
      newValue['areaId'] = value;
      this.setState({Level2id:value}); 
    }
    if(name =="birthday"||name =="inTime"){
         value = this.formatDate(value);
     }
    newValue[name] = value;
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

  handleOk() {
    var me = this;
    var validation = this.refs.validation,
        status = 'create'; 
        var formData =this.state.formData; 
        formData.certType= "0";
        formData.customerManager = customerManager.id;
        formData.nationality= "中国";
        if(formData.isSelfSupport=="null"){
          formData.isSelfSupport="";
        }
        formData.certNumber = formData.certNumber?formData.certNumber.trim():""; 
        delete formData.Level0id;
        delete formData.Level2id;
        delete formData.sesx;
        validation.validate((valid) => {
          if (!valid) {
            return;
          } else {
              if(this.props.title=="新增用户")
              { 
                status = 'create';
              }
              else if(this.props.title=="修改")
              {
                delete formData.sesx;
                status = 'update';
              }
               reqwest({
                        url: '/modules/common/action/CustomerAction/saveOrUpdate.htm',
                        method: 'post', 
                        data:{
                          json:JSON.stringify(formData),
                        },
                        type: 'json',
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
    this.refs.validation.reset();
    this.setState(this.getInitialState());
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
                     if (areaIdleve=='areaIdleve1') { 
                        me.setState({areaIdleve1:items}); 
                      }
                      if (areaIdleve=='areaIdleve2') { 
                        me.setState({areaIdleve2:items});
                        this.setState({
                          Level1id:JSON.stringify(result.data[0].id)
                        });
                        me.areaIdleveData('areaIdleve3',result.data[0].id);
                      }
                      else if(areaIdleve=='areaIdleve3') { 
                        me.setState({areaIdleve3:items});
                        var newValue = this.state.formData ;
                        newValue["areaId"] = result.data[0].id;
                        this.setState({newValue});
                        this.setState({
                          Level2id:JSON.stringify(result.data[0].id)
                        }); 
                      };
                    }
                 });
  },
  idcardCheck(rule, value, callback) { 
   var me =this; 
     if(value&&this.IdCardValidate(value))
    {  
        value = value.trim();
       if (value.length == 18) {
           var sex = Number(value.charAt(16)) % 2 == 0 ? 2 : 1; 
           var birth = value.substr(6, 8).replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
          
       } else if (value.length == 15) {
           var sex = Number(value.charAt(14)) % 2 == 0 ? 2 : 1; 
           var birth = '19' + value.substr(6, 6);
           birth = birth.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3'); 
       } 
       var newForm = me.state.formData;
       newForm.sesx =JSON.stringify(sex);
       newForm.birthday = birth;
       me.setState({
         formData:newForm
       });
       callback();  
    }
    else callback([new Error('证件号码输入错误')]);
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
  ZipCode(rule, value, callback) {
    let errors;
    if (value &&!(/^\d{6}$/.test(value))){
      callback([new Error('邮编格式有误')]);
    } 
    else callback();
  },
  navLineData:{
      "基本信息":"#s1",
      "职业信息":"#s2",
      "其他":"#s3",
  },
   _isInView(el,target) {
    var container = target;
    var winH = container.clientHeight,
          scrollTop = container.scrollTop,
          scrollBottom = scrollTop + winH,
          elTop = el.offsetTop,
          elBottom = elTop + el.offsetHeight - 100;

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
  /*componentDidUpdate(prevProps, prevStat) {  
    var validation = this.refs.validation;
    setTimeout(() => {
      if(prevProps.visible){
            validation&&validation.validate((valid) => {
               if (!valid) {
                 //console.log('error in form');
                 
               } else {
                 //console.log('submit');
               } 
                
             });
          } 
    }, 400);    
  },*/
  
  render() { 
    var me = this; 
    var state = me.state; 
    var status = state.status;
    var citydisabe =true;
    if(me.state.Level0id){
      if(this.props.title=="信息查看"){

        citydisabe= true
      }else{
        citydisabe=false
      }
    }
    var formData = state.formData; 
    if(formData.sesx){formData.sex=formData.sesx}
    var props = this.props;
    var isDisabletwo = (props.title=="新增用户"?false:true);
    var isDisable = !props.canEdit||(props.title=="修改"?true:false); 
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        <button key="button" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
          提 交
        </button>
    ];
    var bisSmartDevice = this.bisSmartDevice();
    var width="1200";
    if(bisSmartDevice){
      width = "875";
    }
    if(this.props.title == '信息查看')
    {
       modalBtns = [
          <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button> 
        ]
    }
    var height=document.body.clientHeight;
    height=height*0.62;
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width={width} footer={modalBtns} >  
          <div style={{position: "relative"}}>
            <div className="navLine-wrap" onScroll={this._handleSpy} style={{height:height}}>
              <div className="col-21 navLine-wrap-left" >
                <form className="ant-form-horizontal" ref="myform"> 
                  <Validation ref="validation" onValidate={this.handleValidate}> 
                    <div id="s1">
                          <h2>基本信息</h2> 
                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="id" required>客户编号：</label>
                                <div className="form-input">
                                    <input className="ant-input" type="text" disabled={true}  name="id" value={formData.id} onChange={this.changeValue}/>
                                </div> 
                          </div> 

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="name" required>客户名称：</label>
                                  <div className="form-input">
                                    <div className={this.renderValidateStyle('name')}>
                                          <Validator rules={[{required: true }]}>
                                           <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="name" value={formData.name} onChange={this.changeValue}/>
                                          </Validator>
                                    </div>
                                  </div>    
                          </div> 

                          <div className="form-line">
                                <label className="form-label"  htmlFor="sex">性别：</label> 
                                <div className="form-input"> 
                                <div className={this.renderValidateStyle('sex')}>
                                      <Validator rules={[{required: true }]}>
                                        <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="sex" value={formData.sex} onChange={this.selectChange.bind(this, 'sex')}>
                                            {sexList}
                                        </Select>
                                      </Validator>
                                    </div>    
                                </div>
                          </div> 

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="certNumber" required>身份证号：</label> 
                                <div className="form-input" style={{width:"160px"}}>
                                    <div className={this.renderValidateStyle('certNumber')}>
                                       <Validator rules={[{required: true}, {validator:this.idcardCheck}]}>  
                                           <input className="ant-input" type="text" disabled={isDisabletwo}  name="certNumber" value={formData.certNumber} onChange={this.changeValue}/> 
                                       </Validator>  
                                    </div>
                                </div>      
                          </div>  

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="marryStatus" required>婚姻状况：</label>
                                <div className="form-input">
                                    <div className={this.renderValidateStyle('marryStatus',false)}>
                                        <Validator rules={[{required: true }]}>
                                        <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="marryStatus" value={formData.marryStatus} onChange={this.selectChange.bind(this, 'marryStatus')}>
                                           {marryStatusList} 
                                        </Select>
                                        </Validator>  
                                    </div>    
                                </div>
                          </div> 

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="education" required>学历：</label>
                                <div className="form-input">
                                    <div className={this.renderValidateStyle('education',false)}>
                                        <Validator rules={[{required: true }]}> 
                                        <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="education" value={formData.education} onChange={this.selectChange.bind(this, 'education')}>
                                           {educationList} 
                                        </Select>
                                        </Validator>  
                                    </div>    
                                </div>       
                          </div> 

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="isHaveChildren" style={{width: "132px"}} required>有无子女：</label>
                                <div className="form-input" style={{width: "97px"}}>
                                    <div className={this.renderValidateStyle('isHaveChildren',false)}>
                                        <Validator rules={[{required: true }]}> 
                                        <Select size="large" style={{width: "100%"}} disabled={!this.props.canEdit} name="isHaveChildren" value={formData.isHaveChildren} onChange={this.selectChange.bind(this, 'isHaveChildren')}>
                                           <Option value="0">有</Option>
                                           <Option value="1">无</Option> 
                                        </Select>
                                        </Validator>  
                                    </div>     
                                </div>
                          </div> 

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="emergencyContactNumber" required style={{width: "94px"}} >紧急联系电话：</label> 
                                <div className="form-input" style={{width: "135px"}} >
                                    <div className={this.renderValidateStyle('emergencyContactNumber')}>
                                        <Validator rules={[{required: true }]}>
                                        <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="emergencyContactNumber" value={formData.emergencyContactNumber} onChange={this.changeValue}/> 
                                        </Validator>  
                                    </div>
                                </div>
                          </div>

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="healthy" required>健康状况：</label>
                                <div className="form-input">
                                  <div className={this.renderValidateStyle('healthy', false)}>
                                        <Validator rules={[{required: true }]}>
                                          <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="healthy" value={formData.healthy} onChange={this.selectChange.bind(this, 'healthy')}>
                                             {healthyList} 
                                          </Select>
                                         </Validator>  
                                  </div>
                                </div>
                          </div> 

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="mobile" required>手机号码：</label>
                                  <div className="form-input">
                                    <div className={this.renderValidateStyle('mobile')}>
                                        <Validator rules={[{required: true, type:'Mobile', message: '请输入正确的手机号'}]}>
                                            <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="mobile" value={formData.mobile} onChange={this.changeValue}/>
                                        </Validator>  
                                    </div> 
                                  </div>    
                          </div> 
                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="customerManager" required>销售员：</label>
                                  <div className="form-input">
                                           <input className="ant-input" type="text" disabled={true}  name="customerManager"  value={customerManager.name} />
                                  </div>    
                          </div> 

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="Level0id" required>现住地址：</label>
                                 <div className="form-input" style={{marginRight: "3px",width:'430px'}}> 
                                    <div className={this.renderValidateStyle('Level0id', false)}> 
                                      <Validator rules={[{required: true}]}> 
                                        <Select size="large" style={{width:"140px",marginRight: "3px"}} disabled={!this.props.canEdit} name="Level0id" value={state.Level0id} onChange={this.citySelectChange.bind(this, 'Level0id')}>
                                           {this.state.areaIdleve1} 
                                        </Select>
                                      </Validator> 
                                        <Select size="large" style={{width:"140px",marginRight: "3px"}} disabled={citydisabe} name="Level1id" value={state.Level1id} onChange={this.citySelectChange.bind(this, 'Level1id')}>
                                           {this.state.areaIdleve2} 
                                        </Select>   
                                        <Select size="large" style={{width:"140px",marginRight: "3px"}} disabled={citydisabe} name="areaId" value={state.Level2id} onChange={this.selectChange.bind(this, 'Level2id')}>
                                           {this.state.areaIdleve3} 
                                        </Select> 
                                    </div>
                                </div> 
                                <div className="form-input" style={{marginRight: "3px",width:"188px"}}> 
                                  <span className={this.renderValidateStyle('liveAddress', false)}> 
                                    <Validator rules={[{required: true}]}>   
                                      <input className="ant-input" style={{width:"100%"}} type="text" disabled={!this.props.canEdit}  name="liveAddress" value={formData.liveAddress} onChange={this.changeValue}/>  
                                    </Validator>
                                  </span> 
                                </div>  
                          </div>              

                          <div className="form-line">     
                                <label className="form-label"  htmlFor="householdAddress" required>家庭地址：</label>
                                <div className="form-input" style={{width:"620px"}}>
                                    <div className={this.renderValidateStyle('householdAddress')}>
                                        <Validator rules={[{required: true }]}>
                                        <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="householdAddress" value={formData.householdAddress} onChange={this.changeValue}/>
                                        </Validator>  
                                    </div> 
                                </div>
                          </div>   

                          <div className="form-line"> 
                                <label className="form-label"  htmlFor="spouseIskown" style={{width: "166px"}} required>家人是否知晓您的此次借款：</label>
                                <div className="form-input" style={{width: "63px"}}>
                                    <div className={this.renderValidateStyle('spouseIskown',false)}>
                                        <Validator rules={[{required: true }]}>
                                        <Select size="large" style={{width: "100%"}} disabled={!this.props.canEdit} name="spouseIskown" value={formData.spouseIskown} onChange={this.selectChange.bind(this, 'spouseIskown')}>
                                           <Option value="0">是</Option>
                                           <Option value="1">否</Option> 
                                        </Select>
                                        </Validator>  
                                    </div>     
                                </div>
                          </div> 

                    </div> 
                    <div id="s2">
                        <h2>职业信息</h2>
                        <div className="form-line"> 
                              <label className="form-label"  htmlFor="company">工作单位：</label>
                              <div className="form-input">
                                  <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="company" value={formData.company} onChange={this.changeValue}/>
                              </div>
                        </div> 

                        <div className="form-line"> 
                              <label className="form-label"  htmlFor="department">所在部门：</label>
                              <div className="form-input">
                                  <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="department" value={formData.department} onChange={this.changeValue}/>
                              </div>
                        </div> 

                        <div className="form-line"> 
                                <label className="form-label"  htmlFor="position ">职位：</label>
                                <div className="form-input">
                                        <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="position" value={formData.position} onChange={this.changeValue}/>
                                </div>
                          </div>  

                        <div className="form-line">     
                                <label className="form-label"  htmlFor="companyAddr">公司地址：</label>
                                <div className="form-input" style={{width:"620px"}}>
                                    <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="companyAddr" value={formData.companyAddr} onChange={this.changeValue}/>
                                </div>
                          </div>  

                        <div className="form-line">
                                <label className="form-label"  htmlFor="companyPhone">公司电话：</label>
                                <div className="form-input">
                                    <div className={this.renderValidateStyle('companyPhone')}>
                                        <Validator rules={[{pattern:/^[0-9]*$/}]}>
                                          <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="companyPhone" value={formData.companyPhone} onChange={this.changeValue}/>
                                        </Validator>
                                    </div>
                                </div>
                        </div>  

                        <div className="form-line"> 
                              <label className="form-label"  htmlFor="isSelfSupport ">是否自营：</label>
                                <div className="form-input">
                                        <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="isSelfSupport " value={Number(formData.isSelfSupport)} onChange={this.selectChange.bind(this, 'isSelfSupport')}>
                                            <Option value={0}>是</Option>
                                            <Option value={1}>否</Option> 
                                        </Select>
                                </div> 
                        </div>
                    </div>
                    <div id="s3" className="mb315">
                          <h2>其他</h2>
                          <div  style={{marginRight:"20px"}}> 
                             <div className="ant-form-item"> 
                                  <label className="form-label"  htmlFor="remark">备注：</label>
                                  <div className="col-21"> 
                                      <textarea className="ant-input" disabled={!this.props.canEdit} style={{height:80,resize:"none"}} name="remark" value={formData.remark} onChange={this.changeValue}/>
                                  </div>  
                             </div>
                          </div>  
                    </div>
                  </Validation>
                </form>
              </div>
              <div className="navLine-wrap-right">
                <NavLine navLineData={this.navLineData} height={height} activeItem="#s1" ref="NavLine"/>
              </div>
            </div>
          </div> 
    </Modal> 
    )
  }
}); 
export default NewCustomer;
