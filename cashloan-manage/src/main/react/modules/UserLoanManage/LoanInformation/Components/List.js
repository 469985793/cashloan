import React from 'react'
import { Table, Modal, Icon, Button } from 'antd';
import Lookdetails from "./Lookdetails";
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
            dataRecord: '',
            record: "",
            visibleAdd: false,

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch();
        [];
        // console.log(123);
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
            url: '/modules/manage/borrow/borrowRepayList.htm',
            method: "post",
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                // console.log(result);
                if(result.data){
                    for (var i = 0; i < result.data.length; i++) {
                        result.data[i].balance = result.data[i].balance / 100;
                        result.data[i].totalFee = result.data[i].totalFee / 100;
                        result.data[i].actualBalance = result.data[i].actualBalance / 100;
                        result.data[i].actualbackAmt = result.data[i].actualbackAmt / 100;
                        // console.log(result.data[0].balance);
                    }
                }
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
        // console.log(record);
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title: title
        }, () => {
            // console.log(record);
            Utils.ajaxData({
                url: '/modules/manage/borrow/borrowRepayContent.htm',
                data: {
                    borrowId: record.indentNo
                },
                method: "post",
                callback: (result) => {
                    // console.log(result);
                    if (result.data) {
                        result.data.actualBalance = result.data.actualBalance / 100;
                        result.data.balance = result.data.balance / 100;
                        result.data.overdueFee = result.data.overdueFee / 100;
                        result.data.totalFee = result.data.totalFee / 100;
                        result.data.profit = result.data.profit / 100;
                        // result.data[0].totalFee = result.data[0].totalFee / 100;
                        // result.data[0].actualBalance = result.data[0].actualBalance / 100;
                        // result.data[0].actualbackAmt = result.data[0].actualbackAmt / 100;
                    }
                    
                    this.refs.Lookdetails.setFieldsValue(result.data);
                    this.setState({
                        dataRecord: result.data,
                    });
                }
            });
        })
    },
    //新增
    addModal(title) {
        Utils.ajaxData({
            url: '/modules/manage/user/list.htm',
            method: "post",
            callback: (result) => {
                this.setState({
                    dataRecord: result.data.list,
                    visibleAdd: true,
                    title: title,
                });
            }
        });

    },
    //隐藏弹窗
    hideModal() {
        this.setState({
            visible: false,
            visible1: false,
            visible2: false,
            selectedIndex: '',
            selectedRowKeys: [],
            visibleAdd: false,
            dataRecord: ''
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
        // console.log(record);
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
            title: 'Actual Name',//真实姓名
            dataIndex: 'lastName'
        }, {
            title: 'Phone',//手机号码
            dataIndex: 'mobile'
        }, {
            title: 'Order Number',//订单号
            dataIndex: 'indentNo'
        }, {
            title: 'Loan Amount',//借款金额
            dataIndex: 'balance'
        }, {
            title: 'Borrowing Period(Days)',//借款期限
            dataIndex: "cycle",
        }, {
            title: 'Order Generation Time',//订单生成时间
            dataIndex: 'createdTime'
        }, {
            title: 'Comprehensive Cost',//综合费用
            dataIndex: "totalFee"
        }, {
            title: 'Actual Arrival Amount',//实际到账金额
            dataIndex: 'actualBalance'
        }, {
            title: 'Actual Repayment Amount',//实际还款金额
            dataIndex: 'actualbackAmt'
        }, {
            title: 'Order Status',//订单状态
            dataIndex: "status",
            render: (text, record) => {
                if (text == 1) {
                    return "In the application, pending risk control review"//申请中,待风控审核
                } else if (text == 2) {
                    return "Wind control audit passed, pending review"//风控审核通过,待复审
                } else if (text == 3) {
                    return "Review and approval, pending payment"//复审通过,待放款
                } else if (text == 4) {
                    return "In the lending"//放款中
                } else if (text == 5) {
                    return "Loaned, pending payment"//已放款,待还款
                } else if (text == 6) {
                    return "Normal reimbursement"//正常还款
                } else if (text == 21) {
                    return "Overdue"//已逾期
                } else if (text == 22) {
                    return "Overdue payment"//逾期还款
                } else if (text == 31) {
                    return "Risk control audit did not pass"//风控审核不通过
                } else if (text == 32) {
                    return "Review not approved,Reapply In 15 Days"//复审不通过,15天后可申请
                } else if (text == 33) {
                    return "Review not approved,Reapply Immediately"//复审不通过，可立即申请
                } else if (text == 34) {
                    return "Review not approved,Blacklist"//复审不通过，并加入黑名单
                } else if (text == 41) {
                    return "Loan failure"//放款失败
                } else if (text == 42) {
                    return "Lending was rejected"//放款被拒
                } else if (text == 51) {
                    return "Bad debt"//坏账
                } else {
                    return " "
                }
            }
        }, {
            title: 'Channel Name',//渠道名称
            dataIndex: 'applyTerminal',
            render: (text, record) => {
                if (text == 1) {
                    return "android"
                } else if (text == 2) {
                    return "IOS"
                } else {
                    return "pc"
                }
            }
        }, {
            title: 'Operating',//操作
            dataIndex: "",
            render: (value, record) => {
                return (
                    <div style={{ textAlign: "left" }}>
                        <a href="#" onClick={me.showModal.bind(me, '查看详情', record, false)}>View</a>{/*查看详情*/}
                    </div>
                )
            }
        }];

        var state = this.state;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    {/*<Button onClick={me.addModal.bind(me, '新增')}>
                        新增(测试)
                    </Button>*/}
                </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    rowClassName={this.rowClassName}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
                    canEdit={state.canEdit} />
                <AddWin ref="AddWin" dataRecord={state.dataRecord} visible={state.visibleAdd} hideModal={me.hideModal} title={state.title} />
            </div>
        );
    }
})
