import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import InterfaceDetail from './InterfaceDetail';
const objectAssign = require('object-assign');
var Lookdetails = React.createClass({
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
    var searchdata = {};
    var record=nextProps.record;
    searchdata = {
        tppId:record.id

    }
    var params=objectAssign({},{pageSize: 10, current: 1,}, {search:JSON.stringify(searchdata)  })
    this.fetch(params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    this.fetch();
  },
  handleCancel() {
    this.props.hideModal();
    
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
            var recordName = {};
            recordName = {
              tppId:record.id
            }
            params = {
                pageSize: 10,
                current: 1,
                search:JSON.stringify(recordName)
            } 
        }
      Utils.ajaxData({
      url: ' /modules/manage/creditdata/tppBusiness/page.htm',
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
        } );
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
    var searchdata = {};
      searchdata = {
        tppId:record.id
    }
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      search : JSON.stringify(searchdata),
    });
    this.fetch(params);
  },
 
  componentDidMount() {  
    //this.fetch();
  },

  showModal(title,record,canEdit){
    record.tppId = this.props.record.id
    this.setState({
      visible:true,
      canEdit:canEdit,
      record:record,
      title:title,
    },()=>{
      this.refs.InterfaceDetail.setFieldsValue(record);
    })
  },

  changeStatus(record,title) {
        var params = {
                id: record.id
            }
            var url = title == "启用"?"/modules/manage/creditdata/tppBusiness/enable.htm":"/modules/manage/creditdata/tppBusiness/disable.htm";
            Utils.getData({
                url: url,
                data: params,
                callback: (result) => {
                    if(result.code==200){
                         Modal.success({
                             title: result.msg,
                            });
                        this.refreshList();
                    }else{
                         Modal.error({
                                title: result.msg,
                            });
                    }  
                }
            });
    },
 
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    var props = this.props;
    var state = this.state;
   var modalBtns= [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
    ];
    const hasSelected = selectedRowKeys.length > 0;
     var columns = [{
            title: '接口名称',
            dataIndex: 'name'
        }, {
            title: '简称',
            dataIndex: 'nid'
        }, {
            title: '状态',
            dataIndex: "state",
            render: (text) => {
              return text == '10' ? '启用' : '禁用'
            }
        }, {
            title: '添加时间',
            dataIndex: 'addTime'
        }, {
          title:'操作',
          dataIndex:'',
          render:function(text,record){
            return <div style={{textAlign:"left"}}>
                      <a href="#" onClick={me.showModal.bind(me,'编辑',record,true)}>编辑</a>
                      <span className="ant-divider"></span>
                      {record.state =="20"?(<a href="#" onClick={me.changeStatus.bind(me,record,"启用")}>启用</a>):(<a href="#" onClick={me.changeStatus.bind(me,record,'禁用')}>禁用</a>)}
                   </div>
          }
        }];

    return (
        <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} > 
            <div className="block-panel">
              <div className="actionBtns" style={{ marginBottom: 16 }}>
                <button className="ant-btn" onClick={me.showModal.bind(me,'新增')}> 
                  新增
                </button>
              </div>
              <Table columns={columns} rowKey={this.rowKey}  size="middle" 
                dataSource={this.state.data}
                pagination={this.state.pagination}
                loading={this.state.loading}
                onChange={this.handleTableChange}  /> 
            </div> 
            <InterfaceDetail ref="InterfaceDetail" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
                canEdit={state.canEdit} />
        </Modal>
    );
  }
});
export default Lookdetails;