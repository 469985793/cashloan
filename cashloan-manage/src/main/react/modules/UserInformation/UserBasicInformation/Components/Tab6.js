import React from 'react';
import {
    Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab6 = React.createClass({
    getInitialState() {
        return {
            loading: false,
            data: [],
            pagination: {},
        };
    },
    rowKey(record) {
        return record.inviteId;
    },
    componentWillReceiveProps(nextProps){
        if(nextProps.activeKey == '6'){
            this.fetch();
        }
    },
    componentDidMount(){
        this.fetch();
    },
    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        pager.userId = this.props.record.id,
            this.setState({
                pagination: pager,
            });
        this.fetch(pager);
    },
    fetch(params = {}) {
        this.setState({
            loading: true
        });
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 5,
                current: 1,
                userId: this.props.record.id,
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/borrow/listBorrowLog.htm',
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
    render() {
        var columns = [{
            title: 'IndentNo',//订单号
            dataIndex: "indentNo",
            width:150
        }, {
            title: 'LoanAmount(KES)',//借款金额(元)
            dataIndex: "balance",
            width:130,
            render(index,text){
                return index/100
            }
        }, {
            title: 'Cycle(DAY)',//借款期限(天)
            dataIndex: "cycle",
            width:90
        }, {
            title: 'ApplyTime',//借款时间
            dataIndex: "createdTime",
        }, {
            title: 'PreFee(KES)',//综合费用(元)
            dataIndex: "fee",
            width:100,
            render(index,text){
                return index/100
            }

        }, {
            title: 'ReceiveAmount(KES)',//实际到账金额(元)
            dataIndex: "actualBalance",
            width:140,
            render(index,text){
                return index/100
            }
        }, {
            title: 'ActualBackAmount(KES)',//实际还款金额（元）
            dataIndex: "actualbackAmt",
            width:160,
            render(index,text){
                return index/100
            }
        }, {
            title: 'LoanReason',//借款原因
            dataIndex: "loanReason",
            width:100,
            render:(text,record)=>{
            if (record.status == '1') {
            return 'Housing related'//住宅相关
        }
    else if (record.status == '2') {
            return 'Vehicle related'//车辆相关
        }
        else if (record.status == '3') {
            return 'Education related'//教育相关
        }
        else if (record.status == '4') {
            return 'Debt consolidation'//债务合并
        }
        else if (record.status == '5') {
            return 'Personal consumption'//个人消费
        }
        else if (record.status == '6') {
            return 'Medical first aid'//医疗急救
        }
        else if (record.status == '7') {
            return 'Funeral'//葬礼
        }
        else if (record.status == '8') {
            return 'Micro enterprise'//微型企业
        }
        else if (record.status == '9') {
            return 'Special events'//特殊事件
        }
        else if (record.status == '10') {
            return 'Traditional ritual'//传统仪式
        }
        else if (record.status == '11') {
            return 'Other'//其他
        }
    }
    }, {
            title: 'Status',//状态
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
        }];
        return (<div className="block-panel">
            <Table
        columns={columns} rowKey={this.rowKey}
        dataSource={this.state.data}
        pagination={this.state.pagination}
        loading={this.state.loading}
        onChange={this.handleTableChange}  />
        </div>
    );
    }
});
export default Tab6;