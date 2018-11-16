import React from 'react'
import { Table, Modal, Icon } from 'antd';
import Lookdetails from "./Lookdetails"
const confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
    getInitialState() {
        return {
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
            rowRecord: []

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
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/borrow/tongdun/list.htm',
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
        });
        this.fetch(params);
    },

    //行点击事件
    onRowClick(record) {
        //console.log(record)
        this.setState({
            selectedRowKeys: [record.id],
            selectedRow: record,
            rowRecord: record
        });
    },

    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },

    //选择
    onSelectAll(selected, selectedRowKeys, selectedRows, changeRows) {
        if (selected) {
            this.setState({
                selectedRowKeys
            })
        } else {
            this.setState({
                selectedRowKeys: []
            })
        }
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
            title: '真实姓名',
            dataIndex: 'realName'
        }, {
            title: '手机号码',
            dataIndex: 'phone'
        }, {
            title: '订单号',
            dataIndex: "borrowNo",
        }, {
            title: '借款金额',
            dataIndex: "amount"
        }, {
            title: '风险报告编码',
            dataIndex: "reportId"
        }, {
            title: '提交审核报告结果编码',
            dataIndex: "submitCode"
        }, {
            title: '提交审核报告时间',
            dataIndex: "createTime"
        }, {
            title: '风险积分',
            dataIndex: 'rsScore'
        }, {
            title: '风险结果',
            dataIndex: 'rsState'
        }, {
            title: '查询审核报告时间',
            dataIndex: "updateTime",
        }, {
            title: '操作',
            render: (text,record) => <a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>查看</a>
        /*}, {
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
            }*/

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
                <Table columns={columns} rowKey={this.rowKey} ref="table"
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
