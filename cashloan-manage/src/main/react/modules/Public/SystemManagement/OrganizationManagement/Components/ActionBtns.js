var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import NewCustomer from './NewCustomer'; 
var reqwest = require('reqwest');
var ClassNameMixin = require('../../../../utils/ClassNameMixin');
var  CustomerActions = require('../actions/CustomerActions');
var  SelectRecordStore = require('../stores/SelectRecordStore');
var CityStore = require('../stores/CityStore');
var  FormStore = require('../stores/FormStore');

export default React.createClass({ 
    mixins: [ 
      ClassNameMixin,
      Reflux.connect(SelectRecordStore, 'selectData'),
      Reflux.connect(CityStore, 'cityData') ,
    ], 
    getInitialState() {
        return {
          selectData:{
                isSelectRecord:false,
                record:null
              },   
          visible: false,
          cityData: {
            areaIdleve1:"",
            areaIdleve2:"",
            areaIdleve3:"",
            selectData:""
          } 
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
  showAddModal(title, canEdit) {
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
    delete(){
      var me = this,
            selectRecord= this.state.selectData.record;
      confirm({
         title: '您是否确认要删除这项内容', 
         onOk: function() {
            reqwest({
                        url: '/modules/system/general/officeDelete.htm',
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
        var disabled = this.state.selectData.isSelectRecord ? 0 : 1;
          Buttons= (<span>
              <button className="ant-btn" onClick={this.expandedAll}> 
                展开所有
              </button>
              <button className="ant-btn" onClick={this.collapseAll}> 
                收缩所有
              </button>
              <button className="ant-btn" onClick={this.refresh}>
                刷新
              </button>
              <button className="ant-btn"  disabled ={disabled} onClick={this.showAddModal.bind(this,'修改机构',false)}>
                <i className="anticon anticon-edit"></i>
                修改机构
              </button>
              <button className="ant-btn"  disabled ={disabled} onClick={this.showAddModal.bind(this,'增加机构',false)}>
                <i className="anticon anticon-plus"></i>
                增加机构
              </button>
              <button className="ant-btn"  disabled ={disabled} onClick={this.delete}>
                <i className="anticon anticon-delete"></i>
                删除
              </button>
            </span>)
                 
        return (
            <div className="actionBtns"> 
              {Buttons}
              <NewCustomer visible={this.state.visible} cityData={this.state.cityData} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}/> 
           </div> 
           )
        }
     });
 