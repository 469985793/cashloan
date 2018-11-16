import React from 'react'
import {
  Table,
  Modal
} from 'antd';
const objectAssign = require('object-assign');
var ReviewRulesList = React.createClass({
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
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });

  },
  handleCancel() {
    this.props.hideModal();
    
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
    var record= this.props.record;
    this.setState({
      loading: true
    });

   var record=this.props.record;
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 10,
                currentPage: 1,
                borrowId:record.id
            } 
        }

      Utils.ajaxData({
      url: '/modules/manage/borrow/borrowRuleResult.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.currentPage;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination
        } );
        }
    });
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var record= this.props.record;
    var searchdata = {};
      searchdata = {
        consumerNo: record.consumerNo
    }
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      currentPage: pagination.current,
      pageSize: pagination.pageSize,
      search : JSON.stringify(searchdata),
    });
    this.fetch(params);
  },
 
  componentDidMount() {  
    this.fetch();
  },
 
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const hasSelected = selectedRowKeys.length > 0;
     var columns = [{
            title: '列中文名称',
            dataIndex: 'colName'
        }, {
            title: '匹配结果',
            dataIndex: 'result'
        }, {
            title: '匹配规则',
            dataIndex: "rule"
        }, {
            title: '表中文名称',
            dataIndex: 'tbName'
        },{
            title: '对比值',
            dataIndex: 'value'
        }];
    
    var state = this.state;
    return (
           <Table columns={columns} rowKey={this.rowKey}  size="middle" 
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />   
        
        
    );
  }
});
export default ReviewRulesList;