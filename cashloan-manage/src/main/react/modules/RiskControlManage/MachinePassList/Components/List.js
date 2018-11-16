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
                searchParams: JSON.stringify({ state: "211" }),
            }
        }
        if(!params.searchParams){
            params.searchParams= JSON.stringify({state:'211'})
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
        var record = record;
        var me = this;
        this.setState({
            visible: true,
            canEdit: canEdit,
            rowRecord: record,
            title: title,
            keyModal: Date.now(),
        }, () => {
            Utils.ajaxData({
                url: '/modules/manage/cl/cluser/detail.htm',
                data: {
                    userId: record.userId
                },
                callback: (result) => {
                    if (result.code == 200) {
                        var dataForm = {};
                        dataForm.realName = result.data.userbase.realName;
                        dataForm.sex = result.data.userbase.sex;
                        dataForm.idNo = result.data.userbase.idNo;                   
                        dataForm.age = result.data.userbase.age;
                        dataForm.cardNo = result.data.userbase.cardNo;
                        dataForm.bank = result.data.userbase.bank;
                        dataForm.bankPhone = result.data.userbase.bankPhone;
                        dataForm.phone = result.data.userbase.phone;
                        dataForm.liveAddr = result.data.userbase.liveAddr;
                        dataForm.registTime = result.data.userbase.registTime;
                        dataForm.registerAddr = result.data.userbase.registerAddr;
                        dataForm.registerClient = result.data.userbase.registerClient;
                        dataForm.education = result.data.userbase.education;
                        dataForm.companyName = result.data.userbase.companyName;
                        dataForm.companyPhone = result.data.userbase.companyPhone;
                        dataForm.salary = result.data.userbase.salary;
                        dataForm.workingYears = result.data.userbase.workingYears;
                        dataForm.companyAddr = result.data.userbase.companyAddr;
                        dataForm.taobao = result.data.userOtherInfo != null ? result.data.userOtherInfo.taobao : '';
                        dataForm.email = result.data.userOtherInfo != null ? result.data.userOtherInfo.email : '';
                        dataForm.qq = result.data.userOtherInfo != null ? result.data.userOtherInfo.qq : '';
                        dataForm.wechat = result.data.userOtherInfo != null ? result.data.userOtherInfo.wechat : '';
                        dataForm.registerCoordinate = result.data.userbase.registerCoordinate;
                        if (result.data.userContactInfo.length > 0) {
                            if (result.data.userContactInfo[0].type == "10") {
                                dataForm.urgentName = result.data.userContactInfo[0].name;
                                dataForm.urgentPhone = result.data.userContactInfo[0].phone;
                                dataForm.urgentRelation = result.data.userContactInfo[0].relation;
                            } else {
                                dataForm.otherName = result.data.userContactInfo[0].name;
                                dataForm.otherPhone = result.data.userContactInfo[0].phone;
                                dataForm.otherRelation = result.data.userContactInfo[0].relation;
                            }
                            if (result.data.userContactInfo[1].type == "20") {
                                dataForm.otherName = result.data.userContactInfo[1].name;
                                dataForm.otherPhone = result.data.userContactInfo[1].phone;
                                dataForm.otherRelation = result.data.userContactInfo[1].relation;
                            } else {
                                dataForm.urgentName = result.data.userContactInfo[1].name;
                                dataForm.urgentPhone = result.data.userContactInfo[1].phone;
                                dataForm.urgentRelation = result.data.userContactInfo[1].relation;
                            }
                        }
                        if (result.data.userAuth) {
                            if (result.data.userAuth.bankCardState == 10) {
                                dataForm.bankCardState = "未认证"
                            } else if (result.data.userAuth.bankCardState == 20) {
                                dataForm.bankCardState = "认证中"
                            } else if (result.data.userAuth.bankCardState == 30) {
                                dataForm.bankCardState = "已认证"
                            }
                            if (result.data.userAuth.idState == 10) {
                                dataForm.idState = "未认证"
                            } else if (result.data.userAuth.idState == 20) {
                                dataForm.idState = "认证中"
                            } else if (result.data.userAuth.idState == 30) {
                                dataForm.idState = "已认证"
                            }
                            if (result.data.userAuth.phoneState == 10) {
                                dataForm.phoneState = "未认证"
                            } else if (result.data.userAuth.phoneState == 20) {
                                dataForm.phoneState = "认证中"
                            } else if (result.data.userAuth.phoneState == 30) {
                                dataForm.phoneState = "已认证"
                            }
                            if (result.data.userAuth.zhimaState == 10) {
                                dataForm.zhimaState = "未认证"
                            } else if (result.data.userAuth.zhimaState == 20) {
                                dataForm.zhimaState = "认证中"
                            } else if (result.data.userAuth.zhimaState == 30) {
                                dataForm.zhimaState = "已认证"
                            }
                        }
                        me.setState({
                            recordSoure: result.data,
                            dataForm:dataForm
                        })
                    }
                }
            });
            //console.log(record);
            record.state = title == "人工复审" ? "221" : record.state;
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
            // searchParams: JSON.stringify({ state: "20" }),
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
            title: '订单号',
            dataIndex: 'orderNo'
        }, {
            title: '真实姓名',
            dataIndex: 'realName'
        }, {
            title: '手机号码',
            dataIndex: "phone"
        }, {
            title: '订单生成时间',
            dataIndex: 'createTime'
        }, {
            title: '借款金额(元)',
            dataIndex: "amount"
        }, {
            title: '借款期限(天)',
            dataIndex: "timeLimit"
        }, {
            title: '实际到账金额(元)',
            dataIndex: "realAmount"
        }, {
            title: '利息(元)',
            dataIndex: "interest"
        }, {
            title: '信息认证费(元)',
            dataIndex: "infoAuthFee",
        }, {
            title: '服务费(元)',
            dataIndex: "serviceFee",
        }, {
            title: '费用(元)',
            dataIndex: 'fee'
        }, {
            title: '操作',
            render(text,record){
            return <a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>查看</a>
            }
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
                <Lookdetails ref="Lookdetails" dataForm={state.dataForm} key={state.keyModal} recordSoure={state.recordSoure} visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.rowRecord}
                    canEdit={state.canEdit} />
            </div >
        );
    }
})
