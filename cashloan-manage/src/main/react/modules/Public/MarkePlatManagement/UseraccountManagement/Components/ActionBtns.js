var React = require('react');
import { Modal} from 'antd';  
var confirm = Modal.confirm;
var Reflux = require('reflux');
var reqwest = require('reqwest');
var  Actions = require('../actions/Actions');
var ClassNameMixin = require('../../../../utils/ClassNameMixin'); 
var  SelectRecordStore = require('../stores/SelectRecordStore');
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
            visible:false
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
                        url : '/modules/cms/manager/action/userAccountAction/userAccountDelete.htm',
                        method: 'post', 
                        data:{
                          uuids : JSON.stringify([selectRecord.uuid]),
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
    handleClick(title){
       var me = this;
       var selectRecord:[];
         selectRecord= this.state.selectData.record;
         var status="";
         var msg="";
         var tips="";
           if(title=="冻结")
              { 
                status = 'lock';
                msg = '用户锁定成功';
                
                tips = '是否冻结此客户'
              }
              else if(title=="解冻")
              {
                status = 'unlock';
                msg = '用户解冻成功';
                tips = '是否解冻此客户'
              }
              else if(title=="重置密码")
              {
                status = 'editpassword';
                msg = '已重置密码为(abc12345)';
                tips = '是否重置用户密码'
              }
       confirm({    
        title:tips,
         onOk: function() {
             
            reqwest({
                        url: '/modules/cms/manager/action/userAccountAction/userEditPage.htm',
                        method: 'post', 
                        data:{
                          uuids : JSON.stringify([selectRecord.uuid]),
                          status:status
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.code==200)
                          {
                            Modal.success({
                              title:msg
                            });
                           Actions.initStore();
                          }
                          else 
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
        var Buttons,isDisabled =1;
         if( this.state.selectData.isSelectRecord )
        {
          isDisabled = 0
        }
         Buttons= (<span>
                <button className="ant-btn" disabled={isDisabled} onClick={this.handleClick.bind(this,'重置密码',true)}>
                  <i className="anticon "></i>
                  重置密码
                </button> 
                <button className="ant-btn" disabled={isDisabled} onClick={this.handleClick.bind(this,'冻结',false)}>
                  <i className="anticon "></i>
                  冻结
                </button> 
                <button className="ant-btn" disabled={isDisabled} onClick={this.handleClick.bind(this,'解冻',false)}>
                  <i className="anticon "></i>
                  解冻
                </button> 
                <button className="ant-btn" disabled={isDisabled} onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  删除
                </button>  
                </span>
                ) 
        return (
            <div className="actionBtns">
              {Buttons}  
           </div> 
           )
        }
     });
 