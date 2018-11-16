import React from 'react'
import {
  Table,
  Modal
} from 'antd';
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        current:1,
        pageSize:10
      },
      canEdit: true,
      visible: false,
      visibleAc: false,
      dataRecord:"",
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  
  rowKey(record) {
    return record.key;
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
  },
  fetch(params = {}) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/manage/borrow/repay/urge/collection/urgeCount.htm', 
      data: params,
      callback: (result) => {
        for(var i = 0; i < result.data.length; i++){
          result.data[i].key = i;
        }
        this.setState({
          loading: false,
          data: result.data,
          pagination:{
            current:1,
            pageSize:10
          }
        });
      }
    });
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  
  componentDidMount() {
    this.fetch();
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
      title: '日期',
      dataIndex: "createTime",
      render:(text) => text.substring(0,10)
    },{
      title: '订单数',
      dataIndex: 'orderCount'
    },{
      title: '成功数',
      dataIndex: "successCount"
    },{
    //   title: '坏账数',
    //   dataIndex: 'failCount'
    // }, {
      title: '催回率(%)',
      dataIndex: "backRate",
      render:(text) => text.toFixed(2)
    },{
      title: '催收次数',
      dataIndex: 'count'
    }];
    var state = this.state;
    return (
      <div className="block-panel">
           <Table columns={columns} rowKey={this.rowKey}  
             onRowClick={this.onRowClick}  params ={this.props.params}
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            
         </div>
    );
  }
})