import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab6 = React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
      pagination: {},
    };
  },
  rowKey(record) {
    return record.inviteId;
  },
  componentWillReceiveProps(nextProps){
    if(nextProps.activeKey == '6'){
      this.fetch();
    }
  },
  componentDidMount(){
    this.fetch();
  },
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    pager.userId = this.props.record.id,
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
        userId: this.props.record.id,
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
      title: '借款金额（元）',
      dataIndex: "amount",
    }, {
      title: '借款期限（天）',
      dataIndex: "timeLimit",
    }, {
      title: '借款时间',
      dataIndex: "createTime",
    }, {
      title: '综合费用（元）',
      dataIndex: "fee",
    }, {
      title: '实际到账金额（元）',
      dataIndex: "realAmount",
    }, {
      title: '实际还款金额（元）',
      dataIndex: "repayAmount",
    }, {
      title: '状态',
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
export default Tab6;