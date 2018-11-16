import React from 'react'
import {
  Table,
  Modal
} from 'antd';
var confirm = Modal.confirm;
import AdjustCreditDetial from './AdjustCreditDetial'
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      dataRecord:[],
      visibleAc: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visibleAc:false
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
     var me = this;
    Utils.ajaxData({
      url: '/modules/manage/borrow/repay/urge/listDetail.htm',
      data: {
        id: record.id
      },
      method: 'get',
      callback: (result) => {
        //console.log(result);
        if(result.data.logs && result.data.logs.length >= 2){
            for(var i = 0; i < result.data.logs.length-1; i++){
              for(var j = i+1; j < result.data.logs.length; j++){
                if(result.data.logs[i].createTime < result.data.logs[j].createTime){
                    var z = result.data.logs[j];
                    result.data.logs[j] = result.data.logs[i];
                    result.data.logs[i] = z;
                }
              }
            }
          }
        me.setState({
          canEdit: canEdit,
          visibleAc: true,
          title: title,
          record: result.data.logs,
          dataRecord:result.data.logs
    });
    }
    });
  },
  //新增
  addModal(title, record, canEdit){
      this.setState({
        visibleAdd:true,
        title:title,  
      })

  },
  rowKey(record) {
    return record.id;
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
  fetch(params = {}) {
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {
        pageSize: 10,
        current: 1,
        searchParams: JSON.stringify({state:"30"}),
      }
    }
    if(!params.searchParams){
            params.searchParams= JSON.stringify({state: '30'})
        }
    Utils.ajaxData({
      url: '/modules/manage/borrow/repay/urge/collection/list.htm',
      data: params,
      method: 'get',
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
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      // searchParams: JSON.stringify({state:"30"}),
    });
    this.fetch(params);
  },
  changeStatus(title,record) {
    var me = this;
    var selectedRowKeys =me.state.selectedRowKeys;
    var id = record.id;
    var status;
    var msg = "";
    var tips = "";
    var trueurl = "";
      if (title == "加入黑名单") {
        msg = '加入黑名单';
        status = '20';
        tips = '您是否确定加入黑名单';
        trueurl = "/modules/manage/user/updateState.htm"
      } else if (title == "解除黑名单") {
        msg = '解除黑名单成功';
        status = '10';
        tips = '您是否确定解除黑名单';
        trueurl = "/modules/manage/user/updateState.htm"
      }
      confirm({
        title: tips,
        onOk: function() {
          Utils.ajaxData({
            url: trueurl,
            data: {     
              id: id, 
              state:status
            },
            method: 'post',
            callback: (result) => {
              if(result.code==200){
                Modal.success({
                 title: result.msg,
                });     
              }else{
                Modal.error({
                  title:  result.msg,
                });
              }
              me.refreshList();
            }
          });
        },
        onCancel: function() {}
      });
  },
  componentDidMount() {
    this.fetch();
  },

  CollectionStatu(record){
    var record=record;
        var me = this;
        var msg = "催收成功";
        confirm({
            title: "您是否重新开始催收",
            onOk: function() {
                Utils.ajaxData({
                    url: "/modules/manage/borrow/repay/urge/updateOrderState.htm",
                    data: {
                        id: record.id,
                        state:20
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
  },

  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id],
      selectedrecord: record
    });
  },
 
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys,
    }; 
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '借款人姓名',
      dataIndex: 'borrowName',
    }, {
      title: '订单号',
      dataIndex: "orderNo",
    }, {
      title: '手机号码',
      dataIndex: "phone",
    }, {
      title: '金额',
      dataIndex: 'amount'
    }, {
      title: '借款时间',
      dataIndex: 'borrowTime',
    }, {
      title: '预计还款时间',
      dataIndex: 'repayTime',
    }, {
      title: '逾期天数',
      dataIndex: "penaltyDay",
    }, {
      title: '逾期等级',
      dataIndex: 'level'
    }, {
      title: '罚息',
      dataIndex: "penaltyAmout",
    }, {
      title: '催收人',
      dataIndex: 'userName',
    }, {
      title: '订单状态',
      dataIndex: 'state',
      render:(text,record) =>  {
        switch(text){
          case "10": 
                    return "未分配";
          case "11":
                    return "待催收";
          case "20":
                    return "催收中";
          case "30":
                    return "承诺还款";
          case "40":
                    return "催收成功";
          case "50":
                    return "坏账";
        }
      }
    },{
      title:'操作',
      render:(text,record) => {
          return <div>
                    <a href="#" onClick={me.showModal.bind(me, '查看催收反馈', record, false)}>查看催收反馈</a>
                    <span className="ant-divider"></span>
                    <a href="#" onClick={me.CollectionStatu.bind(me, record)}>重新催收</a>
                 </div>
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
           <Table columns={columns} rowKey={this.rowKey}  
             onRowClick={this.onRowClick}  
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
             <AdjustCreditDetial ref="AdjustCreditDetial"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal} 
             record={state.record} dataRecord={state.dataRecord} pagination={state.pagination} canEdit={state.canEdit}/> 
         </div>
    );
  }
})