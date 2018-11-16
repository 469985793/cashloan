import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import CustomerWin from './CustomerWin'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
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
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if (record) {
      title = record.realName + '详情';
    }
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    });
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
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {
        pageSize: 10,
        current: 1,
        searchParams: JSON.stringify({state:"10"})
      }
    }
    if(!params.searchParams){
            params.searchParams= JSON.stringify({state:"10"});
        }
    Utils.ajaxData({
      url: '/modules/manage/cl/cluser/credit/list.htm',
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
          data: result.data,
          pagination
        });
      }
    });
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      // searchParams: JSON.stringify({state:"10"})
    });
    this.fetch(params);
  },
  changeStatus(title,record) {
    var me = this;
    var selectedRowKeys =me.state.selectedRowKeys;
    var id = record.id;
    var msg = "解除黑名单成功";
    var tips = "您是否确定解除黑名单";
      confirm({
        title: tips,
        onOk: function() {
          Utils.ajaxData({
            url: "/modules/manage/user/updateState.htm",
            data: {     
              id: id,
              state: "20"
            },
            method: 'post',
            callback: (result) => {
              if(result.code==200){
                Modal.success({
                 title: result.msg,
                });     
              }else{
                Modal.error({
                  title:  result.msg,
                });
              }
              me.refreshList();
            }
          });
        },
        onCancel: function() {}
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
      title: '真实姓名',
      dataIndex: 'realName'
    }, {
      title: '手机号码',
      dataIndex: "phone"
    }, {
      title: '身份证号码',
      dataIndex: 'idNo'
    }, {
      title: '信用额度(元)',
      dataIndex: "total",
       render(text,record){
         if(text){
           return Utils.number(text);
         }else{
          return text=0
        }
      }
    }, {
      title: '已使用额度(元)',
      dataIndex: "used",
      render(text,record){
        if(text){
          return Utils.number(text);
        }else{
          return text=0
        }
      }
    }, {
      title: '未使用额度(元)',
      dataIndex: "unuse",
       render(text,record){
         if(text){
           return Utils.number(text);
         }else{
          return text=0
        }
      }
    },{
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div>
                    <a href="javascript:;" onClick={me.changeStatus.bind(me,'解除黑名单',record)}>解除黑名单</a>
                 </div>
        }
      }];
    var state = this.state;
    return (
      <div className="block-panel">
          
           <Table columns={columns} rowKey={this.rowKey}  
             onRowClick={this.onRowClick}  
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <CustomerWin ref="CustomerWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.selectedrecord}
             canEdit={state.canEdit}/> 
         </div>
    );
  }
})