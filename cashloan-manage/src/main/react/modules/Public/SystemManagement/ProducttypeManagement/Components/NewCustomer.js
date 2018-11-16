import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var reqwest = require('reqwest');
var  FormStore = require('../stores/FormStore');
var  Actions = require('../actions/CustomerActions');
var IncreaseInspection = require("./IncreaseInspection");
var InspectionModal = require("./InspectionModal");

export default React.createClass({
  mixins: [ 
      Reflux.connect(FormStore, 'selectData'),
      Validation.FieldMixin
    ], 
  getInitialState() {
    return {
        selectData:{
                isSelectRecord:false,
                selectedRows:null,
                record:null
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
      //console.log(newValue)
      this.setState({formData:newValue});
  }, 
  selectChange(name,value) { 
    var newValue = this.state.formData; 
      newValue[name] = value; 
    this.setState({formData:newValue});
  }, 
  handleOk() {
     var validation = this.refs.IncreaseInspection.refs.validation;
     validation.validate((valid) => {
          if (!valid) {
            return;
          } else {
     var state = this.state;   
     var props = this.props;
     var selectRecord = props.selectRecord2;
     var formData = this.refs.IncreaseInspection.state.formData;
     var title=this.props.title;
     if(this.props.title=="新增公式"||this.props.title=="修改"){
               reqwest({
                        url: title=='新增公式'?'/modules/fel/FelproductAction/Insert.htm':'/modules/fel/FelproductAction/Update.htm',
                        method: 'post', 
                        data:{
                          json:JSON.stringify(formData)
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
                          else if(result.code==400)
                          {
                             Modal.error({
                              title: result.msg
                            }); 
                            this.setState({
                              loading:false
                            });
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
  }
  })
  },
  handleOk2() {
     var validation = this.refs.validation;
     var state = this.state;   
     var props = this.props;
     var selectRecord2 = props.selectRecord2;
     var rowsRecord= state.selectData.selectedRows; 
     var formulaId= selectRecord2.formulaId;
       var length=rowsRecord?rowsRecord.length:0;
       var ids=[];
       for(var i=0;i<length;i++){ 
          var id=rowsRecord[i].id;
          ids.push(String(id))
       }  
       var postDta={};
       postDta.formulaId=String(ids);
       //console.log(postDta.formulaId)
       postDta.id=selectRecord2.id;
       if(rowsRecord===undefined){
           postDta.formulaId = formulaId
       }
       if(rowsRecord!=undefined&&length==0){
         Modal.error({
           title: "请选择一条记录"
         }); 
       } 
     else{
               reqwest({
                        url:'/modules/fel/FelproductAction/Update.htm',
                        method: 'post', 
                        data:{
                            json: JSON.stringify(postDta),
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg
                            });
                           Actions.initDetailStore(String(ids));
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
  },
  handleCancel() {
    this.props.hideAddModal();
  },
  handleReset() {
    this.refs.validation.reset();
    this.setState(this.getInitialState()); 
  },
  render() {  
    var me = this; 
    var state = this.state; 
    var status = state.status;
    var formData = state.formData;
    var props = this.props;
    var form ;
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
          <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={props.title=="新增公式"||props.title=="修改"?this.handleOk:this.handleOk2}>
            提 交
          </button>
    ];
    if(props.title=="批量添加")
    {
        
        form = <InspectionModal selectRecord = {props.selectRecord2} /> 
    }
      else {
        form = <IncreaseInspection ref="IncreaseInspection" selectRecord = {props.selectRecord2} />
      }
    return(
    <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="1000"  
       footer={modalBtns} >
        {form} 
    </Modal> 
    )
  }
});