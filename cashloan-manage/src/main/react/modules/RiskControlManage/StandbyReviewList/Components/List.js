import React from 'react'
import { Table, Modal, Icon } from 'antd';
import Lookdetails from "./Lookdetails"
const confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
    getInitialState() {
        return {
            selectedRows:[],
            selectedRowKeys: [], // 这里配置默认勾选列
            loading: false,
            data: [],
            pagination: {
                pageSize: 10,
                current: 1
            },
            canEdit: true,
            visible: false,
            visible1: false,
            visible2: false,
            pictureData: [],
            creditReportData: [],
            rowRecord: [],
            button: false

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch();
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
                searchParams: JSON.stringify({ state: "10" }),
            }
        }
        if(!params.searchParams){
            params.searchParams= JSON.stringify({state:'10'})
        }
        Utils.ajaxData({
            url: ' /modules/manage/borrow/borrowList.htm',
            method: "post",
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current) {
                    pagination.current = 1
                }
                ;
                this.setState({
                    loading: false,
                    data: result.data,
                    pagination
                });
            }
        });
    },

    //查看弹窗
    showModal(title, record, canEdit) {
        // var record = this.state.selectedRow;
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title: title
        }, () => {
            record.state = title == "复审" ? "221" : record.state;
            this.refs.Lookdetails.setFieldsValue(record);
        })
    },

    //隐藏弹窗
    hideModal(state) {
        this.setState({
            visible: false,
            selectedIndex: '',
            selectedRowKeys: [],
        });
        this.refreshList();
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
        const pager = this.state.pagination;
        pager.current = pagination.current;
        this.setState({
            pagination: pager,
        });
        this.refreshList();
    },

    refreshList() {
        var pagination = this.state.pagination;
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize,
            // searchParams: JSON.stringify({ state: "10" }),
        });
        this.fetch(params);
    },

    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },

    reRejected(title, record) {
        var record = record;
        var me = this;
        confirm({
            title: "是否确定重新审核",
            onOk: function () {
                Utils.ajaxData({
                    url: '/modules/manage/borrow/reVerifyBorrowData.htm',
                    method: "post",
                    data: {
                        borrowId:record.id
                    },
                    callback:(result) => {
                        if(result.code == 200){
                            Modal.success({
                                title:result.msg,
                            }),
                              me.refreshList();
                        }else{
                            Modal.error({
                                title:result.msg
                            })
                        }
                    }
                })
            },
            onCancel: function () { }
        })
    },
    allReRejected(title) {
        var me = this;
        var ids = me.state.selectedRowKeys.toString();
        confirm({
            title: "是否确定重新审核",
            onOk: function () {
                Utils.ajaxData({
                    url: '/modules/manage/borrow/reVerifyBorrowData.htm',
                    method: "post",
                    data: {
                        borrowId: ids
                    },
                    callback:(result) => {
                        if(result.code == 200){
                            Modal.success({
                                title:result.msg,
                            }),
                           me.refreshList();
                        }else{
                            Modal.error({
                                title:result.msg,
                            })
                        }
                    }
                })
            },
            onCancel: function () { }
        })
    },
    onRowClick(record) {
    //console.log(record);
    var button = this.state.button;
    var id = record.id;
    var selectedRows = this.state.selectedRows;
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selectedRowKeys.indexOf(id) < 0) {
      selectedRowKeys.push(id);
      selectedRows.push(record);
    } else {
      selectedRowKeys.remove(id);
      selectedRows.remove(record);
    }
    //console.log(selectedRowKeys);
    if (selectedRowKeys[0]) {
      button = true;
    } else {
      //console.log(11111111);
      button = false;
    }
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
      button: button
    });
  },
  onSelectAll(selected, selectedRows, changeRows) {
    //console.log('1111111');
    //console.log(selectedRows);
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selected) {
      for (var i = 0; i < selectedRows.length; i++) {
        selectedRowKeys.push(selectedRows[i].id);
      }
    } else {
      selectedRowKeys = [];
    }
    //console.log(selectedRowKeys);
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
      button: selected,
    })
  },
    render() {
        const {
            loading,
            selectedRowKeys
        } = this.state;
        const rowSelection = {
            type: 'checkbox',
            selectedRowKeys,
            onSelectAll: this.onSelectAll,
        };
        let me = this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '订单号',
            dataIndex: 'orderNo'
        }, {
            title: '真实姓名',
            dataIndex: 'realName'
        }, {
            title: '手机号码',
            dataIndex: "phone"
        }, {
            title: '借款金额(元)',
            dataIndex: "amount"
        }, {
            title: '借款期限(天)',
            dataIndex: "timeLimit"
        }, {
            title: '利息(元)',
            dataIndex: "interest"
        }, {
            title: '实际到账金额(元)',
            dataIndex: "realAmount"
        }, {
            title: '订单生成时间',
            dataIndex: 'createTime'
        }, {
            title: '费用(元)',
            dataIndex: 'fee'
        }, {
            title: '信息认证费(元)',
            dataIndex: "infoAuthFee",
        }, {
            title: '服务费(元)',
            dataIndex: "serviceFee",
        }, {
            title: '状态',
            dataIndex: "stateStr",
            render: (text, record) => {
                return (
                    <div>
                        <span>{text}</span>
                        <div>
                            <a href="#" onClick={me.reRejected.bind(me, '重新审核', record)}>重新审核</a>
                        </div>
                    </div>
                )
            }

            // },{
            //     title: '操作',
            //     render(text,record){
            //         if(record.state=='22'){
            //             return (
            //             <div style={{ textAlign: "left" }}>
            //                 <a href="#" onClick={me.showModal.bind(me, '复审', record, true)}>复审</a>
            //             </div>
            //             )
            //         }else{
            //             return(
            //             <div style={{ textAlign: "left" }}>
            //                 <a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>查看</a>
            //             </div>
            //             )
            //         } 
            //     }
        }];

        var state = this.state;
        return (
            <div className="block-panel" >
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button disabled={!state.button} onClick={me.allReRejected.bind(me, '重新审核')} className="ant-btn">
                        批量重新审核
              </button>
                </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                    rowSelection={rowSelection}
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    rowClassName={this.rowClassName}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.rowRecord}
                    canEdit={state.canEdit} />
            </div >
        );
    }
})
