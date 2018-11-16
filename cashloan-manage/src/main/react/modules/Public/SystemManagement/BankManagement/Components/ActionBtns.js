var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import NewCustomer from './NewCustomer';
var reqwest = require('reqwest'); 
var  Actions = require('../actions/Actions');
var  FormStore = require('../stores/FormStore');

export default React.createClass({ 
    mixins: [ 
      Reflux.connect(FormStore, 'selectData')
    ], 
    getInitialState() {
        return {
          selectData:{
                isSelectRecord:false,
                selectedRows:null,
                record:null
              },   
          visible: false
       };
    }, 
    hideAddModal() {
      this.setState({
            visible:false,    
      });
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
       var me = this;
       var rowsRecord= this.state.selectData.selectedRows; 
       var length=rowsRecord.length;
       var ids=[];
       for(var i=0;i<length;i++){ 
          var id=rowsRecord[i].id;
          //console.log(id)
          ids.push(String(id))
       }              
      confirm({
         title: '您是否确认要删除这项内容', 
         onOk: function() {
            reqwest({
                        url: '/modules/system/sysBankAction/deleteByIds.htm',
                        method: 'post', 
                        data:{
                          ids:String(ids)
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
        var rowsRecord= this.state.selectData.selectedRows;
        //console.log(this.state.selectData)
        if(rowsRecord){
          //console.log(rowsRecord.length)
        }
        
         if( this.state.selectData.isSelectRecord&&rowsRecord&&rowsRecord.length>0 )
          { var button;
            if(rowsRecord&&rowsRecord.length==1){
               button=(<button className="ant-btn" onClick={this.showAddModal.bind(this,'修改',true)}>
                   <i className="anticon anticon-search"></i>修改 
                </button>) 
              }else{
               button=( <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'修改',true)}>
                   <i className="anticon anticon-search"></i>修改 
                </button>)
              }
            Buttons= (<span>
                {button}
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
              <button className="ant-btn" onClick={this.showAddModal.bind(this,'添加',true)}>
                <i className="anticon anticon-plus"></i>
                添加
              </button>
              {Buttons}  
              <NewCustomer visible={this.state.visible} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 