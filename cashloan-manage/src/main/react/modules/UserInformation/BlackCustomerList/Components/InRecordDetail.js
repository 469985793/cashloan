import React from 'react'
import {
  Table,
  Modal
} from 'antd';

const objectAssign = require('object-assign');
var InRecordDetail = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
    };
  },

  handleCancel() {
    this.props.hideModal();
  },
  hideModal() {
    this.setState({
      visible: false,
    });
  },
  //新增跟编辑弹窗
  
  rowKey(record) {
    return record.id;
  },




  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '期数',
      dataIndex: 'na2me'
    },{
      title: '利息(元)',
      dataIndex: 'name'
    }, {
      title: '本金(元)',
      dataIndex: "userName"
    }, {
      title: '应还总计(元)',
      dataIndex: 'number'
    }, {
      title: '已还总计(元)',
      dataIndex: "offi5ceName"
    }, {
      title: '应还时间',
      dataIndex: "offi3ceName"
    }, {
      title: '本期状态',
      dataIndex: "state"
    }];
    var state = this.state;
    var props = this.props;
     var modalBtns  = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
    return (
       <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}closable={false} width="900"  footer={modalBtns} >
           <Table columns={columns} rowKey={this.rowKey}  size="middle" 
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />   
         </Modal>
    );
  }
});
export default InRecordDetail;