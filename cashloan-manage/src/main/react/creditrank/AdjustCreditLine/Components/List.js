import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AdjustRecord from './AdjustRecord'
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
      visibleAc: false,
      visibleRecord: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    var me=this;
    me.setState({
      visible: false,
      visibleAc: false,
      visibleRecord: false,
    });
    me.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;

    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    });
  },
  showModalAc(title, record, canEdit) {
    this.setState({
      canEdit: canEdit,
      visibleAc: true,
      title: title,
      record: record
    });
  },
  showModalRecord(title, record, canEdit) {

    this.setState({
      canEdit: canEdit,
      visibleRecord: true,
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
      url: '/modules/manage/user/credit/page.htm', 
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
      title: '姓名',
      dataIndex: 'realName'
    }, {
      title: '手机号码',
      dataIndex: "phone"
    }, {
      title: '身份证号码',
      dataIndex: 'idNo'
    }, {
      title: '授信总额度(元)',
      dataIndex: "total",
       render(text,record){
        return Utils.number(text);
      }
    },{
      title: '已使用额度(元)',
      dataIndex: "totalUnuse",
       render(text,record){
        return Utils.number(record.total-record.unuse);
      }
    }, {
      title: '剩余额度(元)',
      dataIndex: "unuse",
       render(text,record){
        return Utils.number(text);
      }
    }, {
        title: '额度状态',
        dataIndex: "stateStr"
    }, {
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return  (
               <div >
                <a href="javascript:;" onClick={me.showModalAc.bind(me,'查看详情', record, true) }>查看详情</a>
               </div>
          )
        }
      }];
    var state = this.state;
    return (
      <div className="block-panel"> 
           <Table columns={columns} rowKey={this.rowKey}  
             onRowClick={this.onRowClick}  params ={this.props.params}
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
  
          <AdjustRecord ref="AdjustRecord"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/>
         </div>
    );
  }
})