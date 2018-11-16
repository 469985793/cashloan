//主表
import React from 'react'
import {
  Table,
  Modal,
  Popover
} from 'antd';
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
    };
  },
  rowKey(record) {
    return record.code;
  },
  componentDidMount() {
    this.fetch();
  },
  fetch() {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/manage/system/config/testConfiguration.htm',
      callback: (result) => {

        this.setState({
          loading: false,
          data: result.data,
        });
      }
    });
  },
  render() {
    var me = this;
    const {
      loading,
    } = this.state;
    var columns = [{
      title: '配置标识',
      dataIndex: 'code',
      width: '30%'
    }, {
      title: '状态',
      dataIndex: "status",
      width: '30%'
    },{
      title: '参数详情',
      dataIndex: "value",
      width: '60%'
    }];
    var state = this.state;
    return (
      <div className="block-panel">
        <Table columns={columns} rowKey={this.rowKey}
          dataSource={this.state.data}
          loading={this.state.loading}
          onChange={this.handleTableChange} />
      </div>
    );
  }
})
