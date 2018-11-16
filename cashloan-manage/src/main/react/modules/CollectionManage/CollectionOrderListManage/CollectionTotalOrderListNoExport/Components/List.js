import React from 'react'
import {
  Table,
  Modal,
  Button
} from 'antd';
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
import AdjustCreditDetial from './AdjustCreditDetial'
export default React.createClass({
  getInitialState() {
    return {
      selectedRows:[],
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      visibleAdd: false,
      visibleAc:false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.clearSelectedList();
    this.setState({
      visible: false,
      visibleAdd:false,
      visibleAc:false
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
      url: '/modules/manage/borrow/repay/urge/list.htm',
      data: params,
      method: "get",
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
  componentDidMount() {
    this.fetch();
  },

  onRowClick(record) {
    var record = record;
    var state = record.state;
    var id = record.id;
    var selectedRows = this.state.selectedRows;
    var selectedRowKeys = this.state.selectedRowKeys;

    if (state == 40){
        this.setState({
            visibleAc: false,})
    }
    else if (selectedRowKeys.indexOf(id) < 0) {
      selectedRowKeys.push(id);
      selectedRows.push(record);
    } else {
      selectedRowKeys.remove(id);
      selectedRows.remove(record);
    }
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
    });
  },
  onSelectAll(selected, selectedRows, changeRows) {
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selected) {
      for (var i = 0; i < selectedRows.length; i++) {
        selectedRowKeys.push(selectedRows[i].id);
      }
    } else {
      selectedRowKeys = [];
    }
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
    })
  },

  showModalAc(title) {
    if(title=='分配催收员'){
     Utils.ajaxData({
        url: '/modules/manage/borrow/repay/urge/sysUserlist.htm',
        method: 'get',
        callback: (result) => {
          this.setState({
            visibleAc: true,
            title: title,
            selectedRowKeys1: this.state.selectedRowKeys,
            dataRecord:result.data,
          });
        }
      });
    }  
  },
 
  render() {
    var me = this;
    
    const {
      loading,
      selectedRowKeys
    } = this.state;
    
    let hasSelected = selectedRowKeys.length > 0;
    const rowSelection = {
       getCheckboxProps: record => ({
             disabled: record.state === "40" ,    // 配置无法勾选的列
        }),
       onSelectAll:this.onSelectAll
    }; 
    
    
    var columns = [{
      title: '真实姓名',
      dataIndex: 'borrowName',
    }, {
      title: '订单号',
      dataIndex: 'orderNo',
    }, {
      title: '手机号码',
      dataIndex: "phone",
    }, {
      title: '金额',
      dataIndex: 'amount'
    }, {
      title: '借款时间',
      dataIndex: 'borrowTime',
    }, {
      title: '预计还款时间',
      dataIndex: 'repayTime',
    }, {
      title: '逾期天数',
      dataIndex: "penaltyDay",
    }, {
      title: '逾期等级',
      dataIndex: 'level'
    }, {
      title: '罚息',
      dataIndex: "penaltyAmout",
    }, {
      title: '催收人',
      dataIndex: 'userName',
    }, {
      title: '订单状态',
      dataIndex: 'state',
      render:(text,record) =>  {
        switch(text){
          case "10": 
                    return "未分配";
          case "11":
                    return "待催收";
          case "20":
                    return "催收中";
          case "30":
                    return "承诺还款";
          case "40":
                    return "催收成功";
          case "50":
                    return "坏账";
        }
      }
    }, {
      title: '操作',
      render(text, record) {
        if(record.state == "10"){
          return <div ><a href="#" onClick={me.showModalAc.bind(me,"分配催收员")}>分配催收员</a></div>
        }else if(record.state == "40"){
           return <div ></div>
        }
        else{
          return <div ><a href="#" onClick={me.showModalAc.bind(me,"分配催收员")}>重新分配催收员</a></div>
        }
        
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
          <div className="actionBtns" style={{ marginBottom: 16 }}>
            <Button disabled={!hasSelected} onClick={me.showModalAc.bind(me, '分配催收员')}>
              批量分配催收员
            </Button>
          </div>
           <Table columns={columns} rowKey={this.rowKey}  
             rowSelection={rowSelection}
             onRowClick={this.onRowClick}  
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
             <AdjustCreditDetial ref="AdjustCreditDetial"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal} 
             record={state.selectedrecord} dataRecord={state.dataRecord}  canEdit={state.canEdit} selectedRowKeys1={state.selectedRowKeys1} /> 
         </div>
    );
  }
})