import React from 'react';
import antd from 'antd';    
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var Actions = require('../actions/CustomerActions');
var FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
var Datepicker = antd.Datepicker; 
var formatDate = require("../../../../common/dateFormat");
export default React.createClass({
  mixins: [ 
      formatDate,
      Reflux.connect(FormStore, 'formData') 
    ], 
  getInitialState() { 
    return { 
          formData:{  
            name:"",
            soldoutTime:''
          }
      };
  },    
  selectChange(name,value) { 
    var newValue = this.state.formData; 
      newValue[name] = this.formatTime("yyyy-MM-dd hh:mm:ss",value);
    this.setState({formData:newValue});
  }, 
  handleOk() {    
            var me = this;
            var newValue = this.state.formData;
            newValue.consultid = 0 ; 
            newValue.id = this.props.selectRecord.id; 
            var url = this.props.title=='下架'?'/modules/cms/manager/action/ProductPutawayAction/updateProductPutaway.htm?publishStatus=2':'/modules/cms/manager/action/ProductPutawayAction/updateProductPutaway.htm?publishStatus=1';
            reqwest({
              url : url,  
              method: 'post',  
              data:{json: JSON.stringify(newValue)},
              success: (result) => {
                if(result.code==200)
                {
                  Modal.success({
                    title: result.msg
                  });
                 Actions.initStore();
                 this.props.hideAddModal();
                }else{
                          Modal.error({
                            title: result.msg
                          });
                          me.setState({
                            loading:false
                          });
                        }
              }
            });  
       
  },
  handleCancel() {
    this.props.hideAddModal(); 
  },
  componentWillReceiveProps(nextProp){ 
    if(nextProp.selectRecord){
      var newValue = this.state.formData;
      var selectRecord = nextProp.selectRecord;
      newValue.id = selectRecord.id;
      newValue.name = selectRecord.name; 
      newValue.putawayTime = selectRecord.putawayTime;   
      newValue.soldoutTime = selectRecord.soldoutTime;
      this.setState({
            formData:newValue
          })
    }    
  }, 
  render() {  
    var me = this; 
   
    var state = this. state; 
    var status = state.status;
    var formData = state.formData;
    var props = this.props;
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          提 交
        </button>
    ];
    var timeName = this.props.title=="上架"?"soldoutTime":"putawayTime";
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="800"  
        footer={modalBtns} >  
      <form className="ant-form-horizontal" ref="myform" method="post" >   
        <div className="ant-form-item">
                  <label className="col-4"  htmlFor="name">产品名称：</label> 
                  <div className="col-9">
                      <input className="ant-input" type="text" disabled={true}  name="name" value={formData.name} /> 
                  </div> 
            </div>
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="soldoutTime" >{this.props.title=="上架"?'上架时间':'下架时间'}：</label> 
                  <div className="col-9">
                    <Datepicker style={{width:"100%"}} name={timeName} format="yyyy-MM-dd HH:mm:ss" value={formData[timeName]} onChange={this.selectChange.bind(this, timeName)}/>
              
                    </div> 
            </div>   
      </form>  
    </Modal> 
    )
  }
});