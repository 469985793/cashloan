var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
var reqwest = require('reqwest');
import NewCustomer from './NewCustomer'; 
var  Actions = require('../actions/Actions');
var ClassNameMixin = require('../../../../utils/ClassNameMixin');
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
    delete(){
       var me = this,
            selectRecord= this.state.selectData.record;
      confirm({
         title: '您是否确认要删除这项内容', 
         onOk: function() {
          var data={};
          data.id=selectRecord.id;
          data.is_delete=1;
            reqwest({
                        url: '/modules/system/productAction/deleteProduct.htm',
                        method: 'post', 
                        data:{
                          data:JSON.stringify(data)
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.success==true)
                          {
                            Modal.success({
                              title: result.msg
                            });
                           Actions.initStore();
                          }
                          else if(result.success==false)
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
              <button className="ant-btn" onClick={this.showAddModal.bind(this,'新建车贷产品',true)}>
                <i className="anticon anticon-plus"></i>
                新建车贷产品
              </button>
              {Buttons}  
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 