import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddBorrowingRules from './AddBorrowingRules'
import AddWin from './AddWin'
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
      visibleAdd: false
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
    var title = title;
    var canEdit = canEdit;



    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record,
    })




  },
  addModal(title, record, canEdit) {
    var record = record;
    this.setState({
      canEdit: canEdit,
      visibleAdd: true,
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
        current: 1
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/borrow/borrowRuleEngine/page.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current || result.page.current == 1) {
          pagination.current = 1
        } else {
          pagination.current = result.page.current
        };
        for (let i = 0; i < result.data.length; i++) {
          result.data[i].ruleName = 2;
        }
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
  delete(record) {
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/borrow/borrowRuleEngine/delete.htm",
          data: {
            id: record.id,
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
      title: '借款类型',
      dataIndex: 'borrowTypeName'
    }, {
      title: '适用场景',
      dataIndex: "adaptedName"
    }, {
      title: '规则数目',
      dataIndex: "ruleCount"
    }, {
      title: '操作',
      dataIndex: "",
      render(text, record) {
        return <div ><a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>查看规则树 </a>&nbsp;<span className="ant-divider"></span>&nbsp;<a href="#" onClick={me.showModal.bind(me, '编辑', record, true)}>编辑规则树 </a>&nbsp;<span className="ant-divider"></span>&nbsp;<a href="#" onClick={me.delete.bind(me, record)}>删除 </a></div>
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>

          <button className="ant-btn" onClick={this.addModal.bind(this, '新增', null, true)}>
            新增
               </button>


        </div>
        <Table columns={columns} rowKey={this.rowKey}
          onRowClick={this.onRowClick} params={this.props.params}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />

        <AddBorrowingRules ref="AddBorrowingRules" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit} />
        <AddWin ref="AddWin" visible={state.visibleAdd} title={state.title} hideModal={me.hideModal} record={state.selectedrecord}
          canEdit={state.canEdit} />

      </div>
    );
  }
})