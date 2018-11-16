var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import NewCustomer from './NewCustomer'; 
var ClassNameMixin = require('../../../../utils/ClassNameMixin');
var  CustomerActions = require('../actions/CustomerActions');
var  SelectRecordStore = require('../stores/SelectRecordStore');
var  FormStore = require('../stores/FormStore');

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
    expandedAll(){
      this.props.expandedAll();
    },
    collapseAll(){
      this.props.collapseAll();
    },
    refresh(){
      CustomerActions.initStore();
    },

    render() { 
        var Buttons;
         if( this.state.selectData.isSelectRecord )
             Buttons= (<span>
                <button className="ant-btn" onClick={this.refresh}>
                  刷新 
                </button>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'修改',false)}>
                  <i className="anticon anticon-edit"></i>
                  修改
                </button>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'添加',false)}>
                  <i className="anticon anticon-plus"></i>
                  添加
                </button>
                </span>
                ) 
              else Buttons= (<span>
                <button className="ant-btn" onClick={this.refresh}>
                  刷新
                </button>
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'修改',false)}>
                  <i className="anticon anticon-edit"></i>
                  修改
                </button>
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'添加',false)}>
                  <i className="anticon anticon-plus"></i>
                  添加
                </button>
                </span>
              )  
        return (
            <div className="actionBtns">
              <button className="ant-btn" onClick={this.expandedAll}> 
                展开所有
              </button>
              <button className="ant-btn" onClick={this.collapseAll}> 
                收缩所有
              </button>
              {Buttons}
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 