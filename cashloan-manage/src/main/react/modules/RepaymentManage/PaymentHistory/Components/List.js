import React from 'react'
import { Table, Modal, Icon } from 'antd';
import Lookdetails from "./Lookdetails";
var reqwest = require('reqwest');
var repaymentTypeText = { '10': '待审核', '20': '审核中', '30': '通过', '40': '已拒绝', '50': '还款中', '60': '还款完成', '70': '逾期' }
const objectAssign = require('object-assign');
import AddWin from "./AddWin";
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
            rowRecord: [],
            record: "",
            visibleAdd: false,

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch();
        []
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
        reqwest({
            url: '/modules/manage/borrow/repay/log/list.htm',
            method: 'post',
            data: params,
            success: (result) => {
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
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title: title,
        })
    },
    //新增
    addModal(title, record, canEdit) {
        this.setState({
            visibleAdd: true,
            title: title,
        })
    },
    //隐藏弹窗
    hideModal() {
        this.setState({
            visible: false,
            visible1: false,
            visible2: false,
            selectedIndex: '',
            selectedRowKeys: [],
            visibleAdd: false
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
            pageSize: pagination.pageSize
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
        }, () => {
            this
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
            title: 'Real Name',//真实姓名
            dataIndex: 'realName'
        }, {
            title: '手机号码',//手机号码
            dataIndex: 'phone'
        },　{
            title: '订单号',
            dataIndex: 'orderNo'
        }, { 
            title: '借款金额(元)',
            dataIndex: 'borrowAmount'
        }, { 
            title: '综合费用(元)',
            dataIndex: 'fee'
        },{
            title: '应还金额(元)',
            dataIndex: 'repayAmount'
        },{
            title: '逾期天数',
            dataIndex: "penaltyDay"
        },{
            title: '应还逾期罚金(元)',
            dataIndex: 'repayPenalty'
        },{
            title: '应还总额(元)',
            dataIndex: 'repayTotal'
        },{
            title: '实还金额(元)',
            dataIndex: "repayLogAmount",
        },{
            title: '实还逾期罚金(元)',
            dataIndex: 'penaltyAmout'
        },{
            title: '实还总金额(元)',
            dataIndex: "repayYesTotal",
        },{
            title: '还款账号',
            dataIndex: 'repayAccount',
        }, {
            title: '流水号',
            width: 150,
            dataIndex: 'serialNumber'
        }, {
            title: '还款方式',
            dataIndex: 'repayWay',
            render: (text, record) => {
                if (record.repayWay == 10) {
                    return "代扣"
                } else if (record.repayWay == 20) {
                    return "银行卡转账"
                } else if (record.repayWay == 30) {
                    return "支付宝转账"
                } else {
                    return "-"
                }
            }
        }, {
            title: '应还款日期',
            width: 80,
            dataIndex: 'repayPlanTime'
        }, {
            title: '实还款日期',
            width: 80,
            dataIndex: 'repayTime'
        }, {
            title: '操作',
            width: 40,
            dataIndex: "",
            render: (text, record) => {
                return (
                    <div style={{ textAlign: "left" }}>
                        <a href="#" onClick={me.showModal.bind(me, '补扣', record, true)}>补扣</a>
                        <br/>
                        <a href="#" onClick={me.showModal.bind(me, '退还', record, true)}>退还</a>
                    </div>
                )
            }
        }]

        var state = this.state;
        return (
            <div className="block-panel">
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    rowClassName={this.rowClassName}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
                    canEdit={state.canEdit} />
                <AddWin ref="AddWin" visible={state.visibleAdd} hideModal={me.hideModal} title={state.title} />
            </div>
        );
    }
})
