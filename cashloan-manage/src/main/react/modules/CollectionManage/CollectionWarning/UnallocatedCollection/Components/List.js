import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import CollectionReg from './CollectionReg'
import AdjustCreditDetial from './AdjustCreditDetial'
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
      dataRecord:"",
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
      record: record,
    });
  },
  //新增跟编辑弹窗
  showModalAc(title, record, canEdit) {
    var record = record;
    if(title=='分配催收员'){
     Utils.ajaxData({
        url: '/modules/manage/borrow/repay/urge/sysUserlist.htm',
        method: 'get',
        callback: (result) => {
          this.refs.AdjustCreditDetial.setFieldsValue(result.data);
          this.setState({
            canEdit: canEdit,
            visibleAc: true,
            title: title,
            record: record,
            dataRecord:result.data,
          });
        }
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
        current: 1,
        searchParams: JSON.stringify({state:"10"}),
      }
    }
    if(!params.searchParams){
            params.searchParams= JSON.stringify({state: '10'})
        }
    Utils.ajaxData({
      url: '/modules/manage/borrow/repay/urge/list.htm', 
      data: params,
      method: 'post',
      callback: (result) => {
        //console.log("params", params)
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current||result.page.current==1) {
          pagination.current = 1
        }else{
          pagination.current=result.page.current
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
      // searchParams: JSON.stringify({state:"10"}),
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
      dataIndex: "borrowName"
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
      dataIndex: 'borrowTime'
    }, {
      title: '预计还款时间',
      dataIndex: "repayTime"
    },{
      title: '逾期天数',
      dataIndex: 'penaltyDay'
    }, {
      title: '逾期等级',
      dataIndex: 'level'
    }, {
      title: '罚息',
      dataIndex: "penaltyAmout"
    }, {
      title: '操作',
      render(text, record) {
        return <div ><a href="#" onClick={me.showModalAc.bind(me,"分配催收员",record)}>分配催收员</a></div>
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
             <AdjustCreditDetial ref="AdjustCreditDetial"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal} 
             record={state.selectedrecord} dataRecord={state.dataRecord} pagination={state.pagination} canEdit={state.canEdit} /> 
            
         </div>
    );
  }
})