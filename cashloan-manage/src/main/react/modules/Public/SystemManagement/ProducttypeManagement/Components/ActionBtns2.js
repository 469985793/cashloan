var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
var reqwest = require('reqwest');
import NewCustomer2 from './NewCustomer2'; 
var  CustomerActions = require('../actions/CustomerActions');
var  SelectRecordStore = require('../stores/SelectRecordStore');
var  ClassNameMixin = require('../../../../utils/ClassNameMixin');
export default React.createClass({ 
    mixins: [ 
      ClassNameMixin,
      Reflux.connect(SelectRecordStore, 'selectData'),
    ], 
    getInitialState() {
        return {
          selectData:{
                isSelectRecord:false,
                record:null
              },        
          visible: false
       };
    }, 
    hideAddModal() {
      this.setState({
            visible:false,    
      });
      CustomerActions.initStore();
      var obj = document.getElementsByClassName("selectedRow");
      if(obj.length){ 
              this.removeClass(obj[0],"selectedRow");
            }
      this.setState(this.getInitialState()); 
    }, 
    showAddModal(title,canEdit){ 
    CustomerActions.setFormData(title); 
      this.setState({
            visible:true,
            title:title,
            canEdit:canEdit
      });
    },
    render() {  
       var Buttons;
       //console.log(this.state.selectData.isSelectRecord)
        if( this.state.selectData.isSelectRecord )
         { Buttons= (<span>
               <button className="ant-btn" onClick={this.showAddModal.bind(this,'公式验证',true)}>
                 <i className="anticon anticon-delete"></i>
                 公式验证
               </button>
               </span>
               ) 
         }  
         else { 
           Buttons= (<span>
               <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'公式验证',true)}>
                 <i className="anticon anticon-delete"></i>
                 公式验证
               </button>
               </span>
             )   
         }  
         return (
             <div className="actionBtns">
               {Buttons}  
               <NewCustomer2 visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
            </div> 
           )
        }
     });
