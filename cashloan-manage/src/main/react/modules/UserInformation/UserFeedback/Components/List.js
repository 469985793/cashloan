import React from 'react'
import {
  Table,
  Modal,
  Popover
} from 'antd';
var confirm = Modal.confirm;
import Lookdetails from "./Lookdetails";
import AddWin from "./AddWin";
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
      visibleAdd: false,
      dataRecord: ''
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visibleAdd: false
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
    }, () => {
      Utils.ajaxData({
        url: '/modules/manage/mine/opinion/view.htm',
        data: {
          id: record.id
        },
        callback: (result) => {
          result.data.state = result.data.state == 20 ? '已确认' : '待确认';
          this.refs.Lookdetails.setFieldsValue(result.data);
        }

      })
    });
  },
  //新增
  addModal(title, record, canEdit) {
    this.setState({
      visibleAdd: true,
      title: title,
      dataRecord: record
    })

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
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/mine/opinion/page.htm',
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
          data: result.data.list,
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
      dataIndex: 'userRealName',
    }, {
      title: '手机号码',
      dataIndex: 'phone',
    }, {
      title: '意见',
      dataIndex: 'opinion',
      render: (text, record) => {
        if (text&&text.length >= 15) {
          return <Popover content={text} overlayStyle={{ width: "200px" }}>
            <span>{text.substring(0, 15)}...</span>
          </Popover>
        } else {
          return <span>{text}</span>
        }
      }
    }, {
      title: '意见提交时间',
      dataIndex: "createTime",
    }, {
      title: '状态',
      dataIndex: "stateStr",
    }, {
      title: '管理员',
      dataIndex: "sysUserRealName",
    }, {
      title: '反馈',
      dataIndex: 'feedback',
      render: (text, record) => {
        if (text&&text.length >= 15) {
          return <Popover content={text} overlayStyle={{ width: "200px" }}>
            <span>{text.substring(0, 15)}...</span>
          </Popover>
        } else {
          return <span>{text}</span>
        }
      }
    }, {
      title: '反馈时间',
      dataIndex: "confirmTime",
    }, {
      title: '操作',
      dataIndex: '',
      render: (text, record) => {
        if (record.stateStr == '已确认') {
          return "-"
        } else {
          return <div><a href="#" onClick={me.addModal.bind(me, '处理', record, true)}>处理</a></div>
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
          onChange={this.handleTableChange} />
        <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit} />
        <AddWin ref="AddWin" dataRecord={state.dataRecord} visible={state.visibleAdd} hideModal={me.hideModal} title={state.title} />
      </div>
    );
  }
})