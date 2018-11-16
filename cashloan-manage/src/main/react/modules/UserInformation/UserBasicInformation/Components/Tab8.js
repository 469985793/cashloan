import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab2 = React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
      pagination: {},
    };
  },
  rowKey(record) {
    return record.id;
  },
  componentWillReceiveProps(nextProps){
    if(nextProps.records && nextProps.activeKey == '7'){
      this.setState({
        inviteId: nextProps.records.inviteId
      },()=>{
        this.fetch();
      })
    }
  },
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    pager.userId = this.props.records.inviteId,
    this.setState({
      pagination: pager,
    });
    this.fetch(pager);
  },
  fetch(params = {}) {
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {
        pageSize: 5,
        current: 1,
        userId: this.props.records.inviteId,
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/borrow/listBorrowLog.htm',
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
          data: result.data.list,
          pagination
        });
      }
    });
  },
  render() {
    var columns = [{
      title: '订单号',
      dataIndex: "orderNo",
    }, {
      title: '借款所在地',
      dataIndex: "address",
    }, {
      title: '借款金额（元）',
      dataIndex: "amount",
    }, {
      title: '逾期罚金（元）',
      dataIndex: "penaltyAmount",
    }, {
      title: '逾期天数',
      dataIndex: "penaltyDay",
    }, {
      title: '还款金额（元）',
      dataIndex: "repayAmount",
    }, {
      title: '预计还款时间',
      dataIndex: "repayTime",
    }, {
      title: '实际还款时间',
      dataIndex: "realTime",
    }, {
      title: '还款状态',
      dataIndex: "stateStr",
    }];
    return (<div className="block-panel">
              <Table columns={columns} rowKey={this.rowKey}  
              dataSource={this.state.data}
              pagination={this.state.pagination}
              loading={this.state.loading}
              onChange={this.handleTableChange}  />
          </div>
    );
  }
});
export default Tab2;