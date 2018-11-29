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
    return record.uid;
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
    pager.userId = this.props.record.uid,
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
        userId: this.props.record.uid,
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/msg/listContacts.htm',
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
      title: 'Name',
      dataIndex: "name",
    }, {
      title: 'Phone',
      dataIndex: "mobile",
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