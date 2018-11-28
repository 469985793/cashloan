import React from 'react'
import {Table, Modal, Icon} from 'antd';
import Lookdetails from "./Lookdetails";
var repaymentTypeText={'10':'Pending Review', '20': 'Under Review' ,'30': 'Pass','40' :'Rejected' ,'50': 'Repayment', '60' :'Repayment Completed', '70': 'Overdue'} //{'10':'待审核', '20': '审核中' ,'30': '通过','40' :'已拒绝' ,'50': '还款中', '60' :'还款完成', '70': '逾期'}
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
            rowRecord:[],
            record:"",
            visibleAdd:false,

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
        Utils.ajaxData({
            url: '/modules/manage/borrow/repay/list.htm',
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
    showModal(title,record, canEdit) {
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title: title,
        },()=>{
            this.refs.Lookdetails.setFieldsValue(record);
        })
    },
    //新增
    addModal(title, record, canEdit){
        this.setState({
            visibleAdd:true,
            title:title,  
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
            visibleAdd:false
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
            rowRecord:record
        },()=>{
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
    download(){
        window.open('/modules/manage/borrow/repay/downRepayByFile.htm');
    },
    render() {
        const {
            loading,
            selectedRowKeys
            } = this.state;
        const rowSelection = {
            //type: 'checkbox',
            selectedRowKeys,
            //onSelectAll: this.onSelectAll,
        };
        let me=this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: 'Real Name',//真实姓名
            dataIndex: 'realName'
        }, {
            title: 'Phone',//手机号码
            dataIndex: "phone",
        }, {
            title: 'Order Number',//订单号
            dataIndex: 'orderNo'
        }, {
            title: 'Loan Amount(KSH)',//借款金额(元)
            dataIndex: 'borrowAmount'
        }, {
            title: 'Overdue Fine(KSH)',//逾期罚金(元)
            dataIndex: 'penaltyAmout'
        },{
            title: 'Days Overdue',//逾期天数
            dataIndex: "penaltyDay"
        }, {
            title: 'Repayment Amount(KSH)',//应还款金额(元)
            dataIndex: 'repayAmount'
        }, {
            title: 'Total Amount Of Repayment(KSH)',//应还款总额(元)
            dataIndex: 'repayTotal'
        }, {
            title: 'Repayment Date',//应还款日期
            dataIndex: 'repayTime'
        }, {
            title: 'Repayment Status',//还款状态
            dataIndex: "state",
            render: (text, record)=>{
                if(record.state==10){
                    return "Repaid"//已还款
                }else if(record.state==20){
                    return "Unpaid"//未还款
                }else{
                    return "-"
                }
            }
        }]

        var state = this.state;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button onClick={me.addModal.bind(me,'Batch')} className="ant-btn"> 
                        {/*批量还款*/}Batch Repayment
                    </button>
                    <button onClick={me.download.bind(me,'Download')} className="ant-btn"> 
                    Download Template
                    </button>
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
                <AddWin ref="AddWin"  visible={state.visibleAdd} hideModal={me.hideModal} title={state.title}/>
            </div>
        );
    }
})
