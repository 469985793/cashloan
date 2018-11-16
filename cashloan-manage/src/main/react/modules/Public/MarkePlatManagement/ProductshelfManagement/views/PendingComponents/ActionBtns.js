var React = require('react');
import antd from 'antd'; 
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
var reqwest = require('reqwest');  
var  SelectRecordStore = require('../../stores/SelectRecordStore');
var  Actions = require('../../actions/CustomerActions');
var  NewCustomer = require('../NewCustomer');
var  ProductInfoModal = require('../ProductInfoModal');
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
        var Buttons,isDisabled =1;
         if( this.state.selectData.isSelectRecord )
        {
          isDisabled = 0
        }
        Buttons= (<span>
                <button className="ant-btn" disabled={isDisabled} onClick={this.showpInfovModal.bind(this,'下架',false)}>
                   <i className="anticon anticon-search"></i>
                   下架 
                </button>
                <button className="ant-btn" disabled={isDisabled} onClick={this.showAddModal.bind(this,'查看',false)}>
                  <i className="anticon anticon-edit"></i>
                  查看
                </button>
                <button className="ant-btn" disabled={isDisabled} onClick={this.showAddModal.bind(this,'编辑',true)}>
                  <i className="anticon anticon-edit"></i>
                  编辑
                </button>
                </span>
                )  
        return (
            <div className="actionBtns">
              <button className="ant-btn" onClick={this.showAddModal.bind(this,'上架',true)}>
                <i className="anticon anticon-plus"></i>
                上架
              </button>
              {Buttons}  
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
              <ProductInfoModal visible={this.state.pInfovVisible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 