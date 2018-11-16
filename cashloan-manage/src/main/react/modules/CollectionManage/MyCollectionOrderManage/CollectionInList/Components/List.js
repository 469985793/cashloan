import React from 'react'
import {
  Table,
  Modal
} from 'antd';
var confirm = Modal.confirm;
import AddWin from './AddWin'
import AdjustCreditDetial from './AdjustCreditDetial'
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRows: [],
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      visibleAc: false,
      dataRecord: [],
      button: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visibleAc: false
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    var me = this;
    if (title == '新增催收反馈') {
      me.setState({
        canEdit: canEdit,
        visible: true,
        title: title,
        record: record
      }, () => {
        switch (record.state) {
          case "10":
            record.state = "未分配";
            break;
          case "11":
            record.state = "待催收";
            break;
          case "20":
            record.state = "催收中";
            break;
          case "30":
            record.state = "承诺还款";
            break;
          case "40":
            record.state = "催收成功";
            break;
          case "50":
            record.state = "坏账";
            break;
        }
        me.refs.AddWin.setFieldsValue(record);
      }
      );
    } else {
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
            dataRecord: result.data.logs
          });
        }
      });
    }
  },
  //发送
  send(title, record, canEdit) {
    var me = this;
    var ids = me.state.selectedRowKeys.toString();
    Utils.ajaxData({
      url: '/api/smsBatch.htm',
      data: {
        ids: ids
      },
      method: 'post',
      callback: (result) => {
        if (result.code == 200) {
          Modal.success({
            title: result.msg,
          });
        } else {
          Modal.error({
            title: result.msg,
          });
        }
        me.refreshList();
      }
    });

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
        searchParams: JSON.stringify({ state: "20" }),
      }
    }
    if(!params.searchParams){
            params.searchParams= JSON.stringify({state: '20'})
        }
    Utils.ajaxData({
      url: '/modules/manage/borrow/repay/urge/collection/list.htm',
      data: params,
      method: 'post',
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
      selectedRows: [],
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      // searchParams: JSON.stringify({ state: "20" }),
    });
    this.fetch(params);
  },
  changeStatus(title, record) {
    var me = this;
    var selectedRowKeys = me.state.selectedRowKeys;
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
      onOk: function () {
        Utils.ajaxData({
          url: trueurl,
          data: {
            id: id,
            state: status
          },
          method: 'post',
          callback: (result) => {
            if (result.code == 200) {
              Modal.success({
                title: result.msg,
              });
            } else {
              Modal.error({
                title: result.msg,
              });
            }
            me.refreshList();
          }
        });
      },
      onCancel: function () { }
    });
  },
  componentDidMount() {
    this.fetch();
  },
  onRowClick(record) {
    //console.log(record);
    var button = this.state.button;
    var id = record.id;
    var selectedRows = this.state.selectedRows;
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selectedRowKeys.indexOf(id) < 0) {
      selectedRowKeys.push(id);
      selectedRows.push(record);
    } else {
      selectedRowKeys.remove(id);
      selectedRows.remove(record);
    }
    //console.log(selectedRowKeys);
    if (selectedRowKeys[0]) {
      button = true;
    } else {
      //console.log(11111111);
      button = false;
    }
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
      button: button
    });
  },
  onSelectAll(selected, selectedRows, changeRows) {
    //console.log('1111111');
    //console.log(selectedRows);
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selected) {
      for (var i = 0; i < selectedRows.length; i++) {
        selectedRowKeys.push(selectedRows[i].id);
      }
    } else {
      selectedRowKeys = [];
    }
    //console.log(selectedRowKeys);
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
      button: selected,
    })
  },

  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onSelectAll: this.onSelectAll
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
      render: (text, record) => {
        switch (text) {
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
    }, {
      title: '操作',
      render: (text, record) => {
        return <div>
          <a href="#" onClick={me.showModal.bind(me, '新增催收反馈', record, false)}>新增催收反馈</a>
          <span className="ant-divider"></span>
          <a href="#" onClick={me.showModal.bind(me, '查看催收反馈', record, true)}>查看催收反馈</a>
        </div>
      }
    }];
    var state = this.state;
    return (
        <div className="block-panel">
            <div className="actionBtns" style={{ marginBottom: 16 }}>
              <button disabled={!state.button} onClick={me.send.bind(me, '发送')} className="ant-btn">
                批量发送催收短信
                  </button>
            </div>
            <Table columns={columns} rowKey={this.rowKey}
                rowSelection={rowSelection}
                onRowClick={this.onRowClick}
                dataSource={this.state.data}
                pagination={this.state.pagination}
                loading={this.state.loading}
                onChange={this.handleTableChange} />
            <AdjustCreditDetial ref="AdjustCreditDetial" visible={state.visibleAc} title={state.title} hideModal={me.hideModal} record={state.selectedrecord} dataRecord={state.dataRecord} pagination={state.pagination} canEdit={state.canEdit} />
            <AddWin ref="AddWin" record={state.record} rowKeys={state.selectedRowKeys} visible={state.visible} title={state.title} canEdit={state.canEdit} hideModal={me.hideModal} />
        </div>
    );
  }
})