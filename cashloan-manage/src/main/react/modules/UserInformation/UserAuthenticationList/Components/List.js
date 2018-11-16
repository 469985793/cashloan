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
      pagination: {},
      canEdit: true,
      visible: false,
      visibleAdd:false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visibleAdd:false
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    },()=>{
      this.refs.CustomerWin.setFieldsValue(record);
    });
  },
  //新增
  addModal(title, record, canEdit){
      this.setState({
        visibleAdd:true,
        title:title,  
      })

  },
  rowKey(record) {
    return record.userId;
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
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/cl/cluser/authlist.htm',
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
    });
    this.fetch(params);
  },
  changeStatus(title,record) {
    var me = this;
    var selectedRowKeys =me.state.selectedRowKeys;
    var id = record.id;
    var status;
    var msg = "";
    var tips = "";
    var trueurl = "";
      if (title == "加入黑名单") {
        msg = '加入黑名单';
        status = '20';
        tips = '您是否确定加入黑名单';
        trueurl = "/modules/manage/user/updateState.htm"
      } else if (title == "解除黑名单") {
        msg = '解除黑名单成功';
        status = '10';
        tips = '您是否确定解除黑名单';
        trueurl = "/modules/manage/user/updateState.htm"
      }
      confirm({
        title: tips,
        onOk: function() {
          Utils.ajaxData({
            url: trueurl,
            data: {     
              id: id, 
              state:status
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
      dataIndex: 'realName',
    }, {
      title: '手机号码',
      dataIndex: "phone",
    }, {
      title: '银行卡状态',
      dataIndex: 'bankCardState',
      render: (text, record)=>{
        if(record.bankCardState==10){
          return "未认证"
        }else if(record.bankCardState==20){
          return "认证中"
        }else if(record.bankCardState==30){
          return "已认证"
        }else{
          return "-"
        }
      }
    }, {
      title: '紧急联系人状态',
      dataIndex: 'contactState',
      render: (text, record)=>{
        if(record.contactState==10){
          return "未完善"
        }else if(record.contactState==20){
          return "完善中"
        }else if(record.contactState==30){
          return "已完善"
        }else{
          return "-"
        }
      }
    }, {
      title: '身份认证状态',
      dataIndex: "idState",
      render: (text, record)=>{
        if(record.idState==10){
          return "未认证"
        }else if(record.idState==20){
          return "认证中"
        }else if(record.idState==30){
          return "已认证"
        }else{
          return "-"
        }
      }
    }, {
      title: '手机运营商认证状态',
      dataIndex: "phoneState",
      render: (text, record)=>{
        if(record.phoneState==10){
          return "未认证"
        }else if(record.phoneState==20){
          return "认证中"
        }else if(record.phoneState==30){
          return "已认证"
        }else{
          return "-"
        }
      }
    }, {
      title: '芝麻授信状态',
      dataIndex: 'zhimaState',
      render: (text, record)=>{
        if(record.zhimaState==10){
          return "未授信"
        }else if(record.zhimaState==20){
          return "授信中"
        }else if(record.zhimaState==30){
          return "已授信"
        }else{
          return "-"
        }
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
         </div>
    );
  }
})