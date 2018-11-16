//主表
import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import DictionaryList from './DictionaryList'
import AddDic from './AddDic'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        pageSize: 5,
        current: 1
      },
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    this.refreshList();
    var pagination = this.state.pagination;

  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;

    if (title == '修改') {
      var record = record;
      this.refs.AddDic.setFieldsValue(record);
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
  //选择
  onSelectChange(selectedRowKeys) {
    this.setState({
      selectedRowKeys
    });
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
    //console.log(pagination)
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },
  componentDidMount() {
    this.fetch(this.props.params);
  },
  fetch(params = { pageSize: 5, current: 1 }) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/manage/system/dict/page.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.total = result.page.total;
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
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize
    });
    this.fetch(params);
  },
  delete(record) {
    //console.log(record)
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/system/dict/delete.htm",
          data: {
            id: record.id,
          },
          method: 'post',
          callback: (result) => {
            var type = result.code == '200' ? 'success' : 'error';
            Modal[type]({
              title: result.msg,
            });
            me.refreshList();
          }
        });
      },
      onCancel: function () { }
    });
  },
  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id]
    });
  },
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      type: 'radio',
      selectedRowKeys,
      //onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '字典类型',
      dataIndex: 'name'
    }, {
        title: '类型代码',
        dataIndex: "code"
      }, {
        title: '备注',
        dataIndex: 'remark'
      }, {
        title: '排序',
        dataIndex: "sort"
      }, {
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div style={{ textAlign: "left" }}><a href="#" onClick={me.showModal.bind(me, '修改', record, true) }>修改 </a><a href="#" onClick={me.delete.bind(me, record) }>删除 </a></div>
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
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params} rowSelection={rowSelection}
          dataSource={this.state.data} onRowClick={this.onRowClick}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <DictionaryList parentId={selectedRowKeys[0]}/>
        <AddDic ref="AddDic"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>
      </div>
    );
  }
})
