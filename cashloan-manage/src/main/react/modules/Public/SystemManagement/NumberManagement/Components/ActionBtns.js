var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import NewCustomer from './NewCustomer';
var reqwest = require('reqwest'); 
var  Actions = require('../actions/Actions');
var  SelectRecordStore = require('../stores/SelectRecordStore');
var  ClassNameMixin = require('../../../../utils/ClassNameMixin');

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
      Actions.setFormData(this.state.selectData.record,title);
      this.setState({
            visible:true,
            title:title,
            canEdit:canEdit
      });
    },
    delete(){
       var me = this,
            selectRecord= this.state.selectData.record;
      confirm({
         title: '您是否确认要删除这项内容', 
         onOk: function() {
            reqwest({
                        url: '/modules/system/action/delete.htm',
                        method: 'post', 
                        data:{
                          id:selectRecord.id
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg
                            });
                           Actions.initStore();
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
         },
         onCancel: function() {}
       });
    },

    render() { 
        var Buttons;
         if( this.state.selectData.isSelectRecord )
          { Buttons= (<span>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'修改',true)}>
                   <i className="anticon anticon-search"></i>修改 
                </button>
                <button className="ant-btn" onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  删除
                </button>
                </span>
                ) 
          }  
          else { 
            Buttons= (<span>
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'修改',true)}>
                  <i className="anticon anticon-search"></i>
                  修改
                </button>
                <button className="ant-btn" disabled onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  删除
                </button>
                </span>
              )   
          }
        return (
            <div className="actionBtns">
              <button className="ant-btn" onClick={this.showAddModal.bind(this,'新增',true)}>
                <i className="anticon anticon-plus"></i>
                新增
              </button>
              {Buttons}  
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 