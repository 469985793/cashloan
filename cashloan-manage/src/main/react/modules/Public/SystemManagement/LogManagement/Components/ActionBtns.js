var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import NewCustomer from './NewCustomer';
var reqwest = require('reqwest'); 
var  Actions = require('../actions/Actions');
var  FormStore = require('../stores/FormStore');
var  ClassNameMixin = require('../../../../utils/ClassNameMixin');

export default React.createClass({ 
    mixins: [
      ClassNameMixin, 
      Reflux.connect(FormStore, 'selectData')
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
      Actions.initStore();
      var obj = document.getElementsByClassName("selectedRow");
      if(obj.length){ 
              this.removeClass(obj[0],"selectedRow");
            }
      this.setState(this.getInitialState()); 
    },
    showAddModal(title,canEdit){
      Actions.setFormData(title);
      this.setState({
            visible:true,
            title:title,
            canEdit:canEdit
      });
    },
    download(){
       var me = this;
          var state = this.state;
          var selectRecord =state.selectData.record; 
          var fileName = selectRecord.fileName;                   
          location.href='/modules/system/download.htm?fileName='+fileName 
    },

    render() { 
        var Buttons;
         if( this.state.selectData.isSelectRecord )
          { Buttons= (<span>
                <button className="ant-btn" onClick={this.download}>
                  下载
                </button>
                </span>
                ) 
          }  
          else { 
            Buttons= (<span>
                <button className="ant-btn" disabled onClick={this.download}>
                  下载
                </button>
                </span>
              )   
          }
        return (
            <div className="actionBtns">
              {Buttons}  
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 