var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
var reqwest = require('reqwest');
import NewCustomer from './NewCustomer'; 
var  CustomerActions = require('../actions/CustomerActions');
var  SelectRecordStore2 = require('../stores/SelectRecordStore2');
var  ClassNameMixin = require('../../../../utils/ClassNameMixin');

export default React.createClass({ 
    mixins: [ 
      ClassNameMixin,
      Reflux.connect(SelectRecordStore2, 'selectData2')
    ], 
    getInitialState() {
        return {
          selectData2:{
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
       var me = this,
            selectRecord= this.state.selectData2.record;
      confirm({
         title: '您是否确认要删除这项内容', 
         onOk: function() {
            reqwest({
                        url: '/modules/fel/FelproductAction/Delete.htm',
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
                           CustomerActions.initStore();
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
        var props = this.props;
        var title = this.props.title;
        if( this.state.selectData2.isSelectRecord )
          { Buttons= (<span>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'修改',true)}>
                   <i className="anticon anticon-search"></i>
                   修改 
                </button>
                <button className="ant-btn" onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  删除
                </button>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'批量添加',true)}>
                  <i className="anticon anticon-search"></i>
                  批量添加
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
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'批量添加',true)}>
                  <i className="anticon anticon-search"></i>
                  批量添加
                </button>
                </span>
              )   
          }  
         return (
             <div className="actionBtns">
              <button className="ant-btn" onClick={this.showAddModal.bind(this,'新增产品类型',true)}>
                <i className="anticon anticon-plus"></i>
                新增产品类型
              </button>
              {Buttons}
              <NewCustomer visible={this.state.visible} selectRecord2={this.state.selectData2.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
            </div> 
           )
        }
     });
