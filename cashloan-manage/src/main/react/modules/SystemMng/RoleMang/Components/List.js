import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddRole from './AddRole'
import AssignPermissions from './AssignPermissions'
var confirm = Modal.confirm;
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      assignVisible: false,
    };
  },
  hideModal() {
    this.setState({
      visible: false,
      assignVisible: false
    });
    var pagination = this.state.pagination;
    this.fetch();
  },
  //新增跟修改弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if (title == '修改') {
      var record = record;
      this.refs.AddRole.setFieldsValue(record);
      //console.log(record);
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
  //打开分配弹窗
  showAssignModal(title, record) {
    this.setState({
      assignVisible: true,
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
    this.fetch({
      pageSize: pagination.pageSize,
      current: pagination.current
    });
  },
  fetch(params = {
    pageSize: 10,
    current: 1
  }) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/manage/system/role/list.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.total = result.totalCount;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination,
        });
        this.clearList();
      }
    });
  },
  clearList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    this.fetch();
  },
  delete(record) {
    //console.log(record)
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/system/role/delete.htm",
          data: {
            key: record.id,
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
      onCancel: function () { }
    });
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
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '角色名称',
      dataIndex: 'name'
    }, {
        title: '角色唯一标示',
        dataIndex: 'nid'
      }, {
        title: '是否启用',
        dataIndex: 'isDelete',
        render: function (value) {
          if (value == 0)
            return "是";
          else return "否";
        }
      }, {
        title: '创建时间',
        dataIndex: "addTime"
      }, {
        title: '创建人',
        dataIndex: "addUser"
      }, {
        title: '修改时间',
        dataIndex: "updateTime"
      }, {
        title: '修改人',
        dataIndex: 'updateUser',
      }, {
        title: '备注',
        dataIndex: 'remark',
      }, {
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div style={{ textAlign: "left" }}><a href="#" onClick={me.showModal.bind(null, '修改', record, true) }>修改 </a>
            <span className="ant-divider"></span>
            <a href="#" onClick={me.delete.bind(null, record) }>删除</a>
            <span className="ant-divider"></span>
            <a href="#" onClick={me.showAssignModal.bind(null, '分配权限', record) }>分配权限</a>
          </div>
        }
      }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn" onClick={this.showModal.bind(this, '新增', null, true) }>
            新增
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <AddRole ref="AddRole"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>
        <AssignPermissions ref="AssignPermissions"  visible={state.assignVisible}    title={state.title} hideModal={me.hideModal} selectRecord={state.record}
          canEdit={state.canEdit}/>
      </div>
    );
  }
})