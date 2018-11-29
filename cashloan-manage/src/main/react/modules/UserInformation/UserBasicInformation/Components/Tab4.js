import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab4 = React.createClass({
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
    if(nextProps.activeKey == '4'){
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
      url: '/modules/manage/msg/listRecords.htm',
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
        title: '用户号码',
        dataIndex: "date",
    }, {
        title: '对方号码',
        dataIndex: "duration",
    }, {
        title: '通话时间',
        dataIndex: "formatted_number",
    }, {
        title: '通话时长(秒)',
        dataIndex: "location",
    }, {
        title: '通话地',
        dataIndex: "matched_number",
    }, {
        title: '通话状态',
        dataIndex: "name",
    }, {
        title: '通话类型',
        dataIndex: "type",
    }];
    return (<div className="block-panel">
              <Table columns={columns} rowKey={this.rowKey}  
              dataSource={this.state.data}
              pagination={this.state.pagination}
              loading={this.state.loading}
              onChange={this.handleTableChange}

              />
          </div>
    );
  }
});
export default Tab4;