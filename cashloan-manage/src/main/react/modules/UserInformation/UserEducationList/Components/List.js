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
      record.matriculationTime = record.matriculationTime+'a';
      record.graduationTime = record.graduationTime+'b';
      this.refs.Lookdetails.setFieldsValue(record);
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
      url: '/modules/manage/user/educationList.htm',
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
      title: '手机号码',
      dataIndex: 'phone',
    }, {
      title: '学位',
      dataIndex: 'educationBackground',
    }, {
      title: '教育类别',
      dataIndex: 'educationType',
    }, {
      title: '教育情况',
      dataIndex: "graduationConclusion",
    }, {
      title: '就读院校',
      dataIndex: "graduateSchool",
    }, {
      title: '就读专业',
      dataIndex: "profession",
    }, {
      title: '入学时间',
      dataIndex: 'matriculationTime',
    }, {
      title: '毕业时间',
      dataIndex: "graduationTime",
    }, {
      title: '匹配状态',
      dataIndex: "stateStr",
    }, {
        title: '操作',
        dataIndex: '',
        render: (text,record) => <a href="#" onClick={me.showModal.bind(me,'编辑',record,true)} >编辑</a>
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