import React from 'react'
import { Table, Modal, Icon } from 'antd';
import Lookdetails from "./Lookdetails"
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
            rowRecord: [],
            dataRecord: '',
            recordSoure: '',
            dataForm: '',

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch(this.props.params);
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
                searchParams: JSON.stringify({ state: '2' }),
            }
        }
        if (!params.searchParams) {
            params.searchParams = JSON.stringify({ state: '2' })
        }
        Utils.ajaxData({
            url: '/modules/manage/borrow/reviewList.htm',
            method: "post",
            data: params,
            callback: (result) => {
                // console.log(result);
                if (result.data) {
                    for (var i = 0; i < result.data.length; i++) {
                        result.data[i].accountManage=result.data[i].accountManage/100;
                        result.data[i].balance=(result.data[i].balance*10000/1000000).toFixed(2);
                        result.data[i].cuoheFee=result.data[i].cuoheFee/100;
                        result.data[i].fee=result.data[i].fee/100;
                        result.data[i].profit=result.data[i].profit/100;
                        // console.log(result.data[0].balance);
                    }
                }
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;

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
                    userId: record.uid
                },
                callback: (result) => {
                    if (result.code == 200) {
                        var dataForm = {};
                        dataForm.realName = result.data.userbase.firstName + ' ' + result.data.userbase.lastName;
                        if (result.data.userbase.gender == 1) {
                            dataForm.sex = "Men";
                        } else {
                            dataForm.sex = "Women";
                        }
                        dataForm.idNo = result.data.userbase.idNo;
                        dataForm.age = result.data.userbase.age;
                        dataForm.cardNo = result.data.userbase.nationalId;
                        dataForm.bank = result.data.userbase.bank;
                        dataForm.bankPhone = result.data.userbase.bankPhone;
                        dataForm.phone = result.data.userbase.mobile;
                        dataForm.liveAddr = result.data.userbase.liveAddress;
                        dataForm.registTime = result.data.userbase.createdTime;
                        dataForm.registerAddr = result.data.userbase.registerAddr;
                        dataForm.registerClient = result.data.userbase.registerClient;
                        if (result.data.userbase.channelCode == 10000) {
                            dataForm.channelName = "android";
                        } else {
                            dataForm.channelName = "PC";
                        }
                        dataForm.score = result.data.userbase.score;
                        dataForm.livingImg = result.data.userbase.iconImgCode;
                        if (result.data.userbase.education1 == 1) {
                            dataForm.education = "Primary School";
                        } else if (result.data.userbase.education1 == 2) {
                            dataForm.education = "High School";
                        } else if (result.data.userbase.education1 == 3) {
                            dataForm.education = "College";
                        } else if (result.data.userbase.education1 == 4) {
                            dataForm.education = "Master";
                        } else if (result.data.userbase.education1 == 5) {
                            dataForm.education = "Others";
                        } else {
                            dataForm.education = "";
                        }
                        if (result.data.userbase.jobStatus == 1) {
                            dataForm.jobStatus = "Full Time";
                        } else if (result.data.userbase.jobStatus == 2) {
                            dataForm.jobStatus = "Contact";
                        } else if (result.data.userbase.jobStatus == 3) {
                            dataForm.jobStatus = "Self Employed";
                        } else if (result.data.userbase.jobStatus == 4) {
                            dataForm.jobStatus = "Part Time";
                        } else if (result.data.userbase.jobStatus == 5) {
                            dataForm.jobStatus = "Student";
                        } else if (result.data.userbase.jobStatus == 6) {
                            dataForm.jobStatus = "Retired";
                        } else if (result.data.userbase.jobStatus == 7) {
                            dataForm.jobStatus = "Other";
                        } else if (result.data.userbase.jobStatus == 8) {
                            dataForm.jobStatus = "Commission Earner";
                        } else if (result.data.userbase.jobStatus == 9) {
                            dataForm.jobStatus = "Homemaker";
                        } else if (result.data.userbase.jobStatus == 10) {
                            dataForm.jobStatus = "Unemployed";
                        } else {
                            dataForm.jobStatus = "--";
                        }
                        if (result.data.userbase.jobMonthIncome == 1) {
                            dataForm.jobMonthIncome = "Under ksh15000";
                        } else if (result.data.userbase.jobMonthIncome == 2) {
                            dataForm.jobMonthIncome = "ksh15000-ksh25000";
                        } else if (result.data.userbase.jobMonthIncome == 3) {
                            dataForm.jobMonthIncome = "ksh25001-ksh37500";
                        } else if (result.data.userbase.jobMonthIncome == 4) {
                            dataForm.jobMonthIncome = "ksh37501-ksh52500";
                        } else if (result.data.userbase.jobMonthIncome == 5) {
                            dataForm.jobMonthIncome = "ksh52501-ksh75000";
                        } else if (result.data.userbase.jobMonthIncome == 6) {
                            dataForm.jobMonthIncome = "Above ksh75000";
                        } else {
                            dataForm.jobMonthIncome = "--";
                        }
                        if (result.data.userbase.marryStatus == 1) {
                            dataForm.marryStatus = "Maried";
                        } else if (result.data.userbase.marryStatus == 2) {
                            dataForm.marryStatus = "Single";
                        } else if (result.data.userbase.marryStatus == 3) {
                            dataForm.marryStatus = "Divorced";
                        } else if (result.data.userbase.marryStatus == 4) {
                            dataForm.marryStatus = "Seperated";
                        } else if (result.data.userbase.marryStatus == 5) {
                            dataForm.marryStatus = "Widowed";
                        } else {
                            dataForm.marryStatus = "--";
                        }
                        if (result.data.userbase.childrenNumber == 1) {
                            dataForm.childrenNumber = "1";
                        } else if (result.data.userbase.childrenNumber == 2) {
                            dataForm.childrenNumber = "2";
                        } else if (result.data.userbase.childrenNumber == 3) {
                            dataForm.childrenNumber = "3";
                        } else if (result.data.userbase.childrenNumber == 4) {
                            dataForm.childrenNumber = "4";
                        } else if (result.data.userbase.childrenNumber == 5) {
                            dataForm.childrenNumber = "5";
                        } else if (result.data.userbase.childrenNumber == 6) {
                            dataForm.childrenNumber = "6";
                        } else {
                            dataForm.childrenNumber = "0";
                        }
                        dataForm.birthday = result.data.userbase.birthday;
                        dataForm.liveAddress = result.data.userbase.liveAddress;
                        dataForm.liveCity = result.data.userbase.liveCity;
                        dataForm.rentYear = result.data.userbase.rentYear;
                        dataForm.liveTime = result.data.userbase.liveTime;
                        if(result.data.userbase.liveBelong ==1){
                            dataForm.typeOfResidence='Living with parents';
                        }else if(result.data.userbase.liveBelong ==2){
                            dataForm.typeOfResidence='Tenant';
                        }else if(result.data.userbase.liveBelong ==3){
                            dataForm.typeOfResidence='Owner';
                        }else if(result.data.userbase.liveBelong ==4){
                            dataForm.typeOfResidence='Other';
                        }else if(result.data.userbase.liveBelong ==5){
                            dataForm.typeOfResidence='Boarder';
                        }
                        dataForm.companyName = result.data.userbase.companyName;
                        dataForm.companyPhone = result.data.userbase.companyEmail;
                        dataForm.companyAddr = result.data.userbase.officeAddress;
                        dataForm.salary = result.data.userbase.salary;
                        dataForm.workingYears = result.data.userbase.workingYears;
                        dataForm.otherName = result.data.userbase.otherContactName;
                        dataForm.otherPhone = result.data.userbase.otherContactMobile;
                        if (result.data.userbase.otheContact == 1) {
                            dataForm.otherRelation = "friend";
                        } else if (result.data.userbase.otheContact == 2) {
                            dataForm.otherRelation = "colleague";
                        } else if (result.data.userbase.otheContact == 3) {
                            dataForm.otherRelation = "schoolmate";
                        } else if (result.data.userbase.otheContact == 4) {
                            dataForm.otherRelation = "sister";
                        } else if (result.data.userbase.otheContact == 5) {
                            dataForm.otherRelation = "husband";
                        } else if (result.data.userbase.otheContact == 6) {
                            dataForm.otherRelation = "wife";
                        } else if (result.data.userbase.otheContact == 7) {
                            dataForm.otherRelation = "son";
                        } else if (result.data.userbase.otheContact == 8) {
                            dataForm.otherRelation = "daughter";
                        } else {
                            dataForm.otherRelation = "--";
                        }
                        if (result.data.userbase.familyMember == 1) {
                            dataForm.urgentRelation = "farther";
                        } else if (result.data.userbase.familyMember == 2) {
                            dataForm.urgentRelation = "mother";
                        } else if (result.data.userbase.familyMember == 3) {
                            dataForm.urgentRelation = "borther";
                        } else if (result.data.userbase.familyMember == 4) {
                            dataForm.urgentRelation = "sister";
                        } else if (result.data.userbase.familyMember == 5) {
                            dataForm.urgentRelation = "husband";
                        } else if (result.data.userbase.familyMember == 6) {
                            dataForm.urgentRelation = "wife";
                        } else if (result.data.userbase.familyMember == 7) {
                            dataForm.urgentRelation = "son";
                        } else if (result.data.userbase.familyMember == 8) {
                            dataForm.urgentRelation = "daughter";
                        } else {
                            dataForm.urgentRelation = "--";
                        }
                        dataForm.urgentName = result.data.userbase.familyMemberName;
                        dataForm.urgentPhone = result.data.userbase.familyMobilePhone;
                        dataForm.taobao = result.data.userOtherInfo != null ? result.data.userOtherInfo.taobao : '';
                        dataForm.email = result.data.userbase.email;
                        dataForm.qq = result.data.userOtherInfo != null ? result.data.userOtherInfo.qq : '';
                        dataForm.wechat = result.data.userOtherInfo != null ? result.data.userOtherInfo.wechat : '';
                        dataForm.registerCoordinate = result.data.userbase.registerCoordinate;

                        me.setState({
                            recordSoure: result.data,
                            dataForm: dataForm
                        })
                    }
                }
            });
            //console.log(record);
            record.state1 = title == "人工复审" ? "221" : record.state;
            this.refs.Lookdetails.setFieldsValue(record);
        })
    },

    //隐藏弹窗
    hideModal(state) {
        this.setState({
            visible: false,
            selectedIndex: '',
            selectedRowKeys: [],
            dataRecord: '',
            recordSoure: '',
            dataForm: '',
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
        var pagination = this.state.pagination; //console.log(pagination)
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize,
            // searchParams: JSON.stringify({ state: "22" }),
        });
        this.fetch(params);
    },


    render() {
        const {
            loading,
            selectedRowKeys
        } = this.state;

        let me = this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: 'Indent No',
            dataIndex: 'indentNo'
        }, {
            title: 'Last Name',
            dataIndex: 'lastName'
        }, {
            title: 'Phone',
            dataIndex: "mobile"
        }, {
            title: 'RegistrationTime',
            dataIndex: 'createdTime'
        }, {
            title: 'Borrow Money',
            dataIndex: "balance"
        }, {
            title: 'Borrow Term',
            dataIndex: "cycle"
        }, {
            title: 'Actual Arrival Amount',
            dataIndex: "actualBalance"
        }, {
            title: 'Profit',
            dataIndex: "profit"
        }, {
            title: 'Service Fee',
            dataIndex: "accountManage",
        }, {
            title: 'Total Fee',
            dataIndex: 'fee'
        }, {
            title: 'Status',
            render(text, record) {
                if (record.status == '2') {
                    return (
                        <p>Pending</p>
                    )
                } else if(record.status == '3') {
                    return (
                        <p>Approved</p>
                    )
                } else if(record.status == '32') {
                    return (
                        <p>Rejected & Reapply Immediately</p>
                    )
                } else if(record.status == '33') {
                    return (
                        <p>Rejected & Reapply In 15 Days</p>
                    )
                } else if(record.status == '34') {
                    return (
                        <p>Rejected & Blacklist</p>
                    )
                }
            }
        }, {
            title: 'Operation',
            render(text, record) {
                if (record.status == '2') {
                    return (
                        <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.showModal.bind(me, '人工复审', record, true)}>Artificial Retrial</a>
                        </div>
                    )
                } else {
                    return (
                        <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>Select</a>
                        </div>
                    )
                }
            }
        }];

        var state = this.state;
        return (
            <div className="block-panel">

                <Table columns={columns} rowKey={this.rowKey} ref="table"
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" dataForm={state.dataForm} key={state.keyModal} recordSoure={state.recordSoure} visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.rowRecord}
                    canEdit={state.canEdit} />
            </div>
        );
    }
})
