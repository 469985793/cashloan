var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import NewCustomer from './NewCustomer'; 
var ClassNameMixin = require('../../../../utils/ClassNameMixin');
var  CustomerActions = require('../actions/CustomerActions');
var  SelectRecordStore = require('../stores/SelectRecordStore'); 

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
    delete(){
      confirm({
         title: '您是否确认要删除这项内容', 
         onOk: function() {
           Modal.success({
               title: '成功删除' 
             }); 
         },
         onCancel: function() {}
       });
    },
    expandedAll(){
      this.props.expandedAll();
    },
    collapseAll(){
      this.props.collapseAll();
    },
    render() { 
        var Buttons;
         if( this.state.selectData.isSelectRecord )
             Buttons= (<span>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'新增',true)}>
                   <i className="anticon anticon-search"></i>新增 
                </button>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'编辑',false)}>
                  <i className="anticon anticon-edit"></i>
                  编辑
                </button>
                <button className="ant-btn" onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  删除
                </button>
                </span>
                ) 
              else Buttons= (<span>
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'新增',true)}>
                  <i className="anticon anticon-search"></i>
                  新增
                </button>
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'编辑',false)}>
                  <i className="anticon anticon-edit"></i>
                  编辑
                </button>
                <button className="ant-btn" disabled onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  删除
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
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record}
               title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 
