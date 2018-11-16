import React from 'react'
import {
  Table,
  Modal,
  Form,
  Row,
  Col,
  Input,
} from 'antd';
import AdjustCreditLine from "./AdjustCreditLine";
import AdjustCreditDetial from "./AdjustCreditDetial"
import ExaminationAndApproval from "./ExaminationAndApproval"
var confirm = Modal.confirm;
const createForm = Form.create;
const FormItem = Form.Item;
const objectAssign = require('object-assign');
var AdjustRecord = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      visibleAc:false,
      visibleEa:false,
      manualUnuse:""
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch({},nextProps.record);
  },
  hideModal() {
    var me=this;
    me.setState({
      visible: false,
      visibleAc:false,
      visibleEa:false,
    });
    me.refreshList();
  },
  handleCancel() {
    this.props.hideModal();
    
  },
  rowKey(record) {
    return record.id;
  },
  // 查看详情
   showModal(title, record, canEdit) {
    var record = record;
    let search ={"consumerNo":record.consumerNo};
    let paramsData = '';
    paramsData={
            current:1,
            pageSize:10,
            "search":JSON.stringify(search)
      };
    Utils.ajaxData({
        url: '/modules/manage/user/creditLog/page.htm',
        data: paramsData,
        callback: (result) => {
          this.refs.AdjustCreditDetial.setFieldsValue(result.data[0]);
        }
      });
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    });
  },
  //手工调整
  showModalAc(title, record, canEdit) {
    var record = record;
    this.setState({
      canEdit: canEdit,
      visibleAc: true,
      title: title,
      record: record,
      manualUnuse:record.unuse
    });
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },
  fetch(params = {}, record) {
    var record= record;
    this.setState({
      loading: true
    });

    if (!params.pageSize && record) {
      var searchdata = {};
      searchdata = {
        consumerNo: record.consumerNo
      }
      var params = {};
      params = {
        search : JSON.stringify(searchdata),
        pageSize: 5, 
        current: 1,
      }
    }
    if (record) {
      Utils.ajaxData({
      url: '/modules/manage/user/credit/findByConsumerNo.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination
        });
      }
    });
    }
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var record= this.props.record;
    var searchdata = {};
      searchdata = {
        consumerNo: record.consumerNo
    }
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      search : JSON.stringify(searchdata),
    });
    this.fetch(params, {});
  },

   changeStatus (record,title,canEdit) {
        var record=record;
        var me = this;
        var status;
        var msg = "";
        var tips = "";
        var trueurl = "";
        if (title == '解冻') {
              msg = '解冻成功';
              status = "10";
              tips = '您是否解冻';
              trueurl = "/modules/manage/user/credit/updateState.htm";
              confirm({
                title: tips,
                onOk: function() {
                    Utils.ajaxData({
                        url: trueurl,
                        data: {
                            id: record.id,
                            state:status
                        },
                        method: 'post',
                        callback: (result) => {
                            if (result.code == 200) {
                                Modal.success({
                                    title: result.msg,
                                });
                                me.refreshList();
                            } else {
                              Modal.error({
                                    title: result.msg,
                                });
                            }
                          
                        }
                    });
                },
                onCancel: function() { }
            });
        } else if (title == '冻结'){
              msg = '冻结成功';
              status = "20";
              tips = '您是否冻结';
              trueurl = "/modules/manage/user/credit/updateState.htm"
              confirm({
                title: tips,
                onOk: function() {
                    Utils.ajaxData({
                        url: trueurl,
                        data: {
                            id: record.id,
                            state:status
                        },
                        method: 'post',
                        callback: (result) => {
                            if (result.code == 200) {
                                Modal.success({
                                    title: result.msg,
                                });
                                me.refreshList();
                            } else {
                              Modal.error({
                                    title: result.msg,
                                });
                            }
                          
                        }
                    });
                },
                onCancel: function() { }
            });
        }
        else{
             status = "30";
             tips = '您确定要审批授信额度吗？';
             this.ExaminationAndApproval(record,canEdit,title);
        }
         
  },

    //审批
    ExaminationAndApproval (record,canEdit,title) {
      var record = record;
        var me = this;
          this.setState({
              canEdit: canEdit,
              visibleEa:true,
              title: title,
              record: record
          }, () => {
            this.refs.AdjustCreditLine.setFieldsValue(this.state.record);
          });

    },

  render() {
    var me = this;
    const {
            getFieldProps
        } = me.props.form;
    var props = me.props;
    var state = me.state;
    const {
      loading,
      selectedRowKeys
    } = state;
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '额度类型',
      dataIndex: 'creditName'
    }, {
      title: '评分',
      dataIndex: "grade"
    }, {
      title: '授信额度(元)',
      dataIndex: 'total'
    }, {
      title: '当前可用',
      dataIndex: 'unuse'
    },{
      title: '额度状态',
      dataIndex: 'state',
      render(text,record){
        if(text ==10){
         return <span>正常</span>
        }else if(text == 20){
          return <span>冻结</span>
        }else if(text == 30){
          return <span>待审批</span>
        }else if(text == 40){
          return <span>审批不通过</span>
        }
      }
    }, {
      title: '操作',
      dataIndex: '',
      render(text,record){
    	  if (record.state == 30){
              return <span><a href="#" onClick={me.changeStatus.bind(me ,record,'审批')}>审批</a></span>  
            }else if (record.state == 40){
               return <span><a href="javascript:;"  onClick={me.showModal.bind(me,'人工干预记录', record, true) } >人工干预记录</a></span>
            }else {
              return (
                 <span>
                    <a href="javascript:;"  onClick={me.showModal.bind(me,'人工干预记录', record, true) } >人工干预记录</a>
                    <span className="ant-divider"></span>
                    <a href="javascript:;" onClick={me.showModalAc.bind(me,'手动调整额度', record, true) }>手动调整</a>
                    <span className="ant-divider"></span>
                    {record.state== 20 ?
                            <a href="#" onClick={me.changeStatus.bind(me ,record,'解冻')}>解冻</a>:
                            <a href="#" onClick={me.changeStatus.bind(me,record,'冻结')}>冻结</a> } 
                </span>
              )
            }
      }
    }];
    var modalBtns  = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
    return (
      <Modal title={this.props.title} visible={this.props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
         <div>
           <Table columns={columns} rowKey={this.rowKey}  size="middle" 
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />   

             <AdjustCreditLine ref="AdjustCreditLine"  visible={state.visibleAc} canEdit={state.canEdit}   title={state.title} hideModal={me.hideModal} record={state.record} manualUnuse={state.manualUnuse}/>
             <AdjustCreditDetial  ref="AdjustCreditDetial"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit} />
             <ExaminationAndApproval ref="ExaminationAndApproval"   visible={state.visibleEa} title={state.title} hideModal={me.hideModal} record={state.record}
                canEdit={state.canEdit}  dataRecord={state.dataRecord}/>
         </div> 
         </Modal>
        
    );
  }
});
AdjustRecord = createForm()(AdjustRecord);
export default AdjustRecord;