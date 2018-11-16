import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import CollectionReg from './CollectionReg'
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
      dataRecord:[],
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visibleAc: false,
      dataRecord:""
    });
    this.refreshList();
  },
  //编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    });
  },
  //新增跟编辑弹窗
  showModalAc(title, record, canEdit) {
    var record = record;
    var me = this;
    if(title=='加入催收订单'){
     var msg = "加入成功";
     var tips = "您是否确定加入催收";
     confirm({
        title: tips,
        onOk: function() {
            Utils.ajaxData({
                url:  '/modules/manage/borrow/repay/urge/addOrder.htm',
                data: {
                  id: record.id
                },
                contentType: 'application/json',
                method: 'post',
                callback: (result) => {
                    if(result.code==200){
                        Modal.success({
                            title: msg,
                        });     
                    }else{
                        Modal.error({
                            title:  result.msg,
                        });
                    }
                    me.hideModal();
                }
            });
          },
        onCancel: function() {}
     });
    }  
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
      url: '/modules/manage/borrow/repay/urge/borrowlist.htm', 
      data: params,
      method: 'get',
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current||result.page.current==1) {
          pagination.current = 1
        }else{
          pagination.current=result.page.current
        };
        //console.log(result.data);
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
      title: '借款人',
      dataIndex: "realName"
    },{
      title: '订单号',
      dataIndex: "orderNo",
    },{
      title: '手机号码',
      dataIndex: 'phone'
    },{
      title: '借款金额(元)',
      dataIndex: "amount"
    },{
      title: '借款时间',
      dataIndex: 'createTime'
    }, {
      title: '预计还款时间',
      dataIndex: "repayTime"
    },{
      title: '逾期天数',
      dataIndex: 'penaltyDay'
    }, {
      title: '罚息',
      dataIndex: "penaltyAmout"
    },{
        title: '操作',
        render(text, record) {
          return <div >
          <a href="#" onClick={me.showModalAc.bind(me,'加入催收订单',record,true)}>加入催收订单</a></div>
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
           
             <CollectionReg ref="CollectionReg"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.selectedrecord}
             canEdit={state.canEdit}/> 
            
  
         </div>
    );
  }
})