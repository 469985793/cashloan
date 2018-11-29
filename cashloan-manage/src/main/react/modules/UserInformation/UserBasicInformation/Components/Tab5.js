import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab5 = React.createClass({
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
    if(nextProps.activeKey == '5'){
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
          url: '/modules/manage/msg/listMessages.htm',
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
      title: 'date',
      dataIndex: "date",
      width: 30
    }, {
      title: 'phone number',
      dataIndex: "phone",
      width: 30
    }, {
      title: 'name',
      dataIndex: "name",
      width: 20
    },{
      title: 'type',
      width: 20,
      render(text, record) {
        if (record.type == '1') {
            return (
                <p>receive</p>
            )
        } else if(record.status == '2') {
            return (
                <p>send</p>
            )
        }
    }
    },{
      title: 'sms content',
      dataIndex: "smsContent",
      width: 150
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
export default Tab5;