import React from 'react'
import { Table, Modal, Icon } from 'antd';
var confirm = Modal.confirm;
import Addwin from "./Addwin";
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
            record: "",
            visibleAdd: false,

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
                userName: "",
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/profit/findAgent.htm',
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
                    data: result.data.list,
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
            title: title
        }, () => {

            this.refs.Lookdetails.setFieldsValue(record);
        })
    },
    //新增
    addModal(title, record, canEdit) {
        this.setState({
            visibleAdd: true,
            title: title,
            record:record
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

    SetAgent(record, title) {
        var me = this;
        var record = record;
        //console.log(11111);
        //console.log(record);
        confirm({
            title: "Whether to cancel the secondary agent",//是否取消二级代理
            onOk: function () {
                Utils.ajaxData({
                    url: "/modules/manage/profit/freezeAgent.htm",
                    data: {
                        userId: record.inviteId,
                    },
                    method: 'post',
                    callback: (result) => {
                        Modal.success({
                            title: result.msg,
                        });
                        me.refreshList();
                    }
                });
            },
            onCancel: function () { }
        });
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
        let me = this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: 'Agent(Phone)',//代理商(手机号码)
            dataIndex: 'userName',
        }, 
        {
            title: 'Invitees(Phone)',//被邀请人(手机号码)
            dataIndex: 'inviteName',
        }, {
            title: 'Is it a secondary agent',//是否为二级代理商
            dataIndex: 'level',
            render: (text, record) => {
                if (record.level == 2) {
                    return "Yes"
                } else {
                    return "No"
                }
            }
        }, {
            title: 'Separation rate(%)',//分润率(%)
            dataIndex: "rate"
        }, {
            title: 'Invitation time',//邀请时间
            dataIndex: 'addTime',
        }, {
            title: 'Operating',//操作
            render: (value, record) => {
                if (record.level != 2) {
                    return (
                        <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.addModal.bind(this, "Set as a secondary agent", record)}>Set as a secondary agent{/*设置为二级代理商*/}</a>
                        </div>
                    )
                } else {
                    return (
                        <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.SetAgent.bind(this, record, "Cancel secondary agent")}>Cancel secondary agent{/*取消二级代理*/}</a>
                        </div>
                    )
                }
            }
        }];

        var state = this.state;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    rowClassName={this.rowClassName}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                />
                <Addwin ref="AddWin" record={state.record} visible={state.visibleAdd} hideModal={me.hideModal} title={state.title} />
            </div>
        );
    }
})
