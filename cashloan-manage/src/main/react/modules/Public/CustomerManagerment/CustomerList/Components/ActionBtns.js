var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import NewCustomer from './NewCustomer'; 
var NewConsulting = require('../../../../CarLoan/form/NewConsulting'); 
var reqwest = require('reqwest');
var  Actions = require('../actions/Actions');
var  ClassNameMixin = require('../../../../utils/ClassNameMixin'); 
var  SelectRecordStore = require('../stores/SelectRecordStore');
var  CityStore = require('../stores/CityStore'); 
 
export default React.createClass({ 
    mixins: [ 
      ClassNameMixin,
      Reflux.connect(SelectRecordStore, 'selectData'), 
      Reflux.connect(CityStore, 'cityData')
    ], 
   
    getInitialState() {
        return {
          selectData:{
                isSelectRecord:false,
                record:null
              }, 
          ConsultingVisible:false,  
          visible: false ,
          cityData: { 
            areaIdleve1:[],
            areaIdleve2:[],
            areaIdleve3:[],
            selectData:[]
          },
          loanData:{}
       };
    }, 
    hideAddModal() {
      this.setState({
            visible:false,
            visible2:false,
            consultingVisible:false    
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
    showConsultingModal(title,canEdit){ 
      this.setState({
            consultingVisible:true,
            title:title,
            canEdit:canEdit
      });
    }, 
   
    addBlack(){
      var me = this, selectRecord= this.state.selectData.record;
      confirm({
         title: '您是否将此客户加入黑名单', 
         onOk: function() {
            reqwest({
                        url: '/modules/common/action/CustomerAction/updatBlack.htm',
                        method: 'post', 
                        data:{
                          id:selectRecord.id,
                          status:1
                        },
                        type: 'json',
                        success: (result) => {
                          //console.log(result);
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

    consult(title,canEdit){
      var selectRecord= this.state.selectData.record;
      var me = this;
      confirm({
         title: '是否确认申请贷款?', 
         onOk: function() {
            reqwest({
                        url: '/modules/common/action/plConsultAction/addCustomerConsult.htm',
                        method: 'post', 
                        type: 'json',
                        data:{
                          id:selectRecord.id,
                          consultid:selectRecord.consultId
                        },
                        success: (result) => {
                          if(result.code==200)
                          {
                            me.setState({
                              loanData:result.data
                            });
                            me.showConsultingModal(title,canEdit,result)
                          }
                          else if(result.code==500)
                          {

                            Modal.error({
                              title: result.msg
                            }); 
                          }
                        }
                      });
         },
         onCancel: function() {}
       });
    },  
    componentDidMount(){
      var me = this;
        reqwest({
          url: '/modules/common/action/PlAreaAction/getAreaListByParentLeave.htm',
          method: 'get', 
          data: {parentid:0},
          type: 'json',
          success: (result) => {
            var newValue= me.state.cityData;
                newValue["areaIdleve1"] = result.data;
                me.setState({cityDataOnlyAdd:newValue}); 
          }
        });
        
    },
    render() {  
        var Buttons; 
        var button1;
        var button2;
        var button3;
        if(this.props.roleName=="销售员"){
           var button1=(
               <button className="ant-btn" onClick={this.showAddModal.bind(this,'新增用户',true)}>
                 <i className="anticon anticon-plus"></i>
                 新增
               </button>
            );
           if( this.state.selectData.isSelectRecord ){
            if (this.state.selectData.record.status==0) {
              var button2=(
              <span>
                 <button className="ant-btn" onClick={this.addBlack}>
                   <i className="anticon anticon-plus"></i>
                   加入黑名单
                 </button>
                 <button className="ant-btn" onClick={this.consult.bind(this,'贷款申请',true)}>
                   <i className="anticon anticon-logout"></i>
                   贷款申请 
                 </button>
              </span>
            );
            }else{
              var button2=(
              <span>
                 <button className="ant-btn" disabled onClick={this.addBlack}>
                   <i className="anticon anticon-plus"></i>
                   加入黑名单
                 </button>
                 <button className="ant-btn" disabled onClick={this.consult.bind(this,'贷款申请',true)}>
                   <i className="anticon anticon-logout"></i>
                   贷款申请
                 </button>
              </span>
            );
            }
           }else{
            var button2=(
              <span>
                   <button className="ant-btn" disabled onClick={this.addBlack}>
                     <i className="anticon anticon-plus"></i>
                     加入黑名单
                   </button>
                   <button className="ant-btn" disabled onClick={this.consult.bind(this,'贷款申请',true)}>
                     <i className="anticon anticon-logout"></i>
                     贷款申请
                   </button>
              </span>
            );
           }
        }else{
           var button1=(
               <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'新增用户',true)}>
                 <i className="anticon anticon-plus"></i>
                 新增
               </button>
            );
            var button2=(
              <span>
                  <button className="ant-btn" disabled onClick={this.addBlack}>
                    <i className="anticon anticon-plus"></i>
                    加入黑名单
                  </button>
                  <button className="ant-btn" disabled onClick={this.consult.bind(this,'贷款申请',true)}>
                   <i className="anticon anticon-logout"></i>
                   贷款申请
                  </button>
              </span>
             );      
        }
        
         if( this.state.selectData.isSelectRecord )
          { Buttons= (
            <span>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'修改',true)}>
                   <i className="anticon anticon-edit"></i>修改 
                </button>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'信息查看',false)}>
                  <i className="anticon anticon-search"></i>
                  信息查看
                </button> 
                {button2}
            </span>
                ) 
          }  
          else { 
            Buttons= (
              <span>
                  <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'修改',true)}>
                    <i className="anticon anticon-edit"></i>
                    修改
                  </button>
                  <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'信息查看',false)}>
                    <i className="anticon anticon-search"></i>
                    信息查看
                  </button> 
                  {button2}
              </span>
              )   
          } 
        return (
            <div className="actionBtns">
              {button1}
              {Buttons}
              <NewConsulting  consultingVisible={this.state.consultingVisible} loanData={this.state.loanData} selectRecord={this.state.selectData.record} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}
               cityData={this.state.cityData} />   
              <NewCustomer visible={this.state.visible} title={this.state.title} hideAddModal={this.hideAddModal} canEdit={this.state.canEdit}
               cityData={this.state.cityData} cityDataOnlyAdd={this.state.cityDataOnlyAdd}/> 
           </div> 
           )
        }
     });
 

