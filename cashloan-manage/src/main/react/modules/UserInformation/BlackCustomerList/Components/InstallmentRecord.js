import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import InRecordDetail from './InRecordDetail'
const objectAssign = require('object-assign');
var InstallmentRecord = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });

  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if ( title == '查看') {
      var record = record;
      this.setState({
        canEdit: canEdit,
        visible: true,
        title: title,
        record: record
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
    var record= this.props.record;
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {
        channelId: record.channelId,
        consumerNo: record.consumerNo,
        pageSize: 5,
        current: 1,
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/user/findBorrowList.htm',
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
    var record= this.props.record;
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      channelId: record.channelId,
      consumerNo: record.consumerNo,
    });
    this.fetch(params);
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
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '分期订单号',
      dataIndex: 'orderNo'
    }, {
      title: '分期金额(元)',
      dataIndex: "amount",
      render(text, record) {

      //   var num = text;
      //    num = num.toString().replace(/\$|\,/g,'');
      //     if(isNaN(num))
      //     num = "0";
      //     var sign = (num == (num = Math.abs(num)));
      //     num = Math.floor(num*100+0.50000000001);
      //     var cents = num%100;
      //     num = Math.floor(num/100).toString();
      //     if(cents<10)
      //     cents = "0" + cents;
      //     for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
      //     num = num.substring(0,num.length-(4*i+3))+','+
      //     num.substring(num.length-(4*i+3));
      //     return (((sign)?'':'-') + num + '.' + cents); 
        return Utils.number(text);
      }
    }, {
      title: '分期时间',
      dataIndex: 'addTime'
    }, {
      title: '当前状态',
      dataIndex: 'stateStr',
      render:function(value){
        return '人工复审拒绝'
      }
    }, {
      title: '详情',
      dataIndex: "",
      render(text, record) {
        return <div style={{textAlign: "left"}}><a href="javascript:;" style={{color: "#999",cursor: 'text'}}>查看 </a></div>
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
           <Table columns={columns} rowKey={this.rowKey}  size="middle" 
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />   
            <InRecordDetail ref="InRecordDetail"  visible={state.visible}   hideModal={me.hideModal}  /> 
         </div>
        
    );
  }
});
export default InstallmentRecord;