var React = require('react');
import antd from 'antd'; 
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
var reqwest = require('reqwest');  
var  SelectRecordStore = require('../../stores/SelectRecordStore');
var  Actions = require('../../actions/CustomerActions');
var  ProductInfoModal = require('../ProductInfoModal');
var  NewCustomer = require('../NewCustomer');
var  ClassNameMixin = require('../../../../../utils/ClassNameMixin');
export default React.createClass({ 
    mixins: [ 
            ClassNameMixin,
            Reflux.connect(SelectRecordStore, 'selectData')
    ], 
    getInitialState() {
        return {
          selectData:{
                isSelectRecord:false,
                record:null
              }, 
          pInfovVisible:false, 
          visible: false ,
          title:''
       };
    }, 
    hideAddModal() {
     if(this.title!="查看")
      {
        Actions.initStore();
      }  
      var obj = document.getElementsByClassName("selectedRow");
      if(obj.length){ 
              this.removeClass(obj[0],"selectedRow");
      }
      this.setState(this.getInitialState());
    },
    showAddModal(title,canEdit){  
      this.setState({
            visible:true,
            title:title,
            canEdit:canEdit
      });
    }, 
    showpInfovModal(title,canEdit){  
      this.setState({
            pInfovVisible:true,
            title:title,
            canEdit:canEdit
      });
    },
    render() {  
        var isDisabled =1;
         if( this.state.selectData.isSelectRecord )
        {
          isDisabled = 0
        }
           
        return (
            <div className="actionBtns">
              <button className="ant-btn" disabled={isDisabled} onClick={this.showpInfovModal.bind(this,'上架',true)}>
                <i className="anticon anticon-plus"></i>
                上架
              </button>
              <button className="ant-btn" disabled={isDisabled} onClick={this.showAddModal.bind(this,'查看',false)}>
                  <i className="anticon anticon-search"></i>
                  查看
              </button>   
              <ProductInfoModal visible={this.state.pInfovVisible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 