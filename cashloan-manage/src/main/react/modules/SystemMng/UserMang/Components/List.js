import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddUserWin from './AddUserWin'
import Reset from './Reset'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRows: [],
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        pageSize: 10,
      },
      canEdit: true,
      visible: false,   
      visible1: false
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visible1: false
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if (title == '编辑' || title == '查看') {
      var record = record;
      if(record.officeOver){
        record.officeOver = record.officeOver.split(',');
      }
      record.roleId = record.roleId.split(',');
      this.refs.AddUserWin.setFieldsValue(record);
    } else if (title == '新增') {
      record = null
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
    pager.pageSize = pagination.pageSize;
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
        pageSize: this.state.pagination.pageSize,
        current: 1
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/system/user/list.htm',
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
      selectedRows: [],
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize
    });
    this.fetch(params);
  },
  changeStatus(title,record) {
    var me = this;
    var arr = [record.id];
    var status;
    var msg = "";
    var tips = "";
    var trueurl = "";
      if (title == "锁定") {
        msg = '锁定成功';
        status = 'lock';
        tips = '您是否确定锁定';
        trueurl = "/modules/manage/system/user/update.htm"
      } else if (title == "解锁") {
        msg = '解锁成功';
        status = 'unlock';
        tips = '您是否确定解锁';
        trueurl = "/modules/manage/system/user/update.htm"
      }
      confirm({
        title: tips,
        onOk: function() {
          Utils.ajaxData({
            url: trueurl,
            data: {
              ids: arr,
              status: status
            },
            method: 'post',
            callback: (result) => {
              Modal.success({
                title: result.msg,
              });
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
    var id = record.id;
    var selectedRows = this.state.selectedRows;
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selectedRowKeys.indexOf(id) < 0) {
      selectedRowKeys.push(id);
      selectedRows.push(record);
    } else {
      selectedRowKeys.remove(id);
      selectedRows.remove(record);
    }
    //console.log(selectedRowKeys);
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
    });
  },
  onSelectAll(selected, selectedRows, changeRows) {
    //console.log('1111111');
    //console.log(selectedRows);
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selected) {
      for (var i = 0; i < selectedRows.length; i++) {
        selectedRowKeys.push(selectedRows[i].id);
      }
    } else {
      selectedRowKeys = [];
    }
    //console.log(selectedRowKeys);
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
    })
  },
  reset(title,record){
    this.setState({
      record:record,
      title:title,
      visible1: true
    })
  },
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onSelectAll: me.onSelectAll,
    }; 
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '真实姓名',
      dataIndex: 'name'
    }, {
      title: '用户名',
      dataIndex: "userName"
    }, {
      title: '工号',
      dataIndex: 'jobNumber'
    }, {
      title: '角色',
      dataIndex: "roleName"
    }, {
      title: '状态',
      dataIndex: 'status',
      render: (text, record) => {
        if (text == 0) {
          return <span>正常</span>
        } else if (text == 1) {
          return <span>锁定</span>
        } else {
          return <span></span>
        }
      }
    }, {
      title: '操作',
      dataIndex: "",
      render(text, record) {
        return <div style={{textAlign: "left"}}>
        <a href="#" onClick={me.showModal.bind(me,'编辑',record,true)}>编辑 </a> 
        <span className="ant-divider"></span>
        <a href="#" onClick={me.reset.bind(me,'重置密码',record)}>重置密码 </a>
        <span className="ant-divider"></span>
        {record.status == '1' ?<a href="#" onClick={me.changeStatus.bind(me,'解锁',record)}>解锁 </a>
        :<a href="#" onClick={me.changeStatus.bind(me,'锁定',record)}>锁定 </a>}
        </div>
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
           <div className="actionBtns" style={{ marginBottom: 16 }}> 
               <button className="ant-btn" onClick={this.showModal.bind(this,'新增',null,true)}>
                 新增
               </button>
           </div>
           <Table columns={columns} rowKey={this.rowKey}  size="middle" 
             onRowClick={this.onRowClick}
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <AddUserWin ref="AddUserWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
             <Reset ref="Reset"  visible={state.visible1}    title={state.title} hideModal={me.hideModal} record={state.record}/> 
         </div>
    );
  }
})