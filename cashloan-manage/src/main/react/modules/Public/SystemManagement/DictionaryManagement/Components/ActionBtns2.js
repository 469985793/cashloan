var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
var reqwest = require('reqwest');
import NewCustomer2 from './NewCustomer2'; 
var  CustomerActions = require('../actions/CustomerActions');
var  SelectRecordStore2 = require('../stores/SelectRecordStore2');
var  SelectRecordStore = require('../stores/SelectRecordStore');

export default React.createClass({ 
    mixins: [ 
      Reflux.connect(SelectRecordStore, 'selectData'),
      Reflux.connect(SelectRecordStore2, 'selectData2')
    ], 
    getInitialState() {
        return {
          selectData2:{
                isSelectRecord:false,
                record:null
              },
          selectData:{
                isSelectRecord:false,
                record:null
              },        
          visible: false
       };
    }, 
    hideAddModal() {
      this.setState({
            visible:false
      });
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
       var me = this,
            selectRecord= this.state.selectData2.record;
            var id=this.state.selectData.record.id;
      confirm({
         title: '您是否确认要删除这项内容', 
         onOk: function() {
            reqwest({
                        url: '/modules/manage/system/dict/detail/delete.htm',
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
                           CustomerActions.initDetailStore(id);
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
       //console.log(this.state.selectData2.record)
        if( this.state.selectData2.isSelectRecord )
         { Buttons= (<span>
               <button className="ant-btn" onClick={this.showAddModal.bind(this,'字项新增',true)}>
                 <i className="anticon anticon-plus"></i>
                 字项新增
               </button> 
               <button className="ant-btn" onClick={this.showAddModal.bind(this,'字项修改',true)}>
                  <i className="anticon anticon-search"></i>字项修改 
               </button>
               <button className="ant-btn" onClick={this.delete}>
                 <i className="anticon anticon-delete"></i>
                 字项删除
               </button>
               </span>
               ) 
         }  
         else { 
           Buttons= (<span>
               <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'字项新增',true)}>
                 <i className="anticon anticon-plus"></i>
                 字项新增
               </button>
               <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'字项修改',true)}>
                 <i className="anticon anticon-search"></i>
                 字项修改
               </button>
               <button className="ant-btn" disabled onClick={this.delete}>
                 <i className="anticon anticon-delete"></i>
                 字项删除
               </button>
               </span>
             )   
         }  
         return (
             <div className="actionBtns">
               {Buttons}  
               <NewCustomer2 visible={this.state.visible} selectRecord={this.state.selectData2.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
            </div> 
           )
        }
     });
