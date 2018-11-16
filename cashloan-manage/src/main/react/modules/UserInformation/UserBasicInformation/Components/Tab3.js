import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab3 = React.createClass({
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
    if(nextProps.activeKey == '3'){
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
        pageSize: 10,
        current: 1,
        userId: this.props.record.id,
      }
    }
      Utils.ajaxData({
          url: '/modules/manage/msg/listRecords.htm',
          data: params,
          callback: (result) => {
              this.setState({
                  loading: false,
                  data: result.data.list
              });
          }
      });
  },
  render() {
    var columns = [{
        title: '对方号码',
        dataIndex: "callRecords",
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
export default Tab3;