import React from 'react'
import { Table, Modal, Icon } from 'antd';
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
                current: 1,
                search: JSON.stringify({ state: '15', type: '10' })
            }
        }
        if (!params.search) {
            params.search = JSON.stringify({ state: '15', type: '10' })
        }
        Utils.ajaxData({
            url: '/modules/manage/pay/log/page.htm',
            method: "post",
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if(result.data){

                    for(let i = 0 ; i < result.data.length;i++){
                        result.data[i].balance = result.data[i].balance / 100;
                    }
                }
                if (!pagination.current) {
                    pagination.current = 1
                }
                ;
                // console.log(result.data);
                // console.log(result.data);
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
            visibleAdd: true,
            canEdit: canEdit,
            record: record,
            title: title
        }, () => {

            this.refs.AddWin.setFieldsValue(record);
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
            pageSize: pagination.pageSize,
            // search: JSON.stringify({state: '15',type: '10'})
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
            title: 'LastName',//姓
            dataIndex: 'lastName'
        }, {
            title: 'Phone',//手机号
            dataIndex: 'mobile'
        }, {
            title: 'Amount',//金额
            dataIndex: 'balance'
        }, {
            title: 'Borrow Time',//借款时间
            dataIndex: 'createdTime'
        }, {
            title: 'cycle',//期限
            dataIndex: "cycle"
        }, {
            title: 'scenes',//背景
            dataIndex: "scenesStr",
            render: (text, record) => {
                if (text == 10) {
                    return "Blacklist"//黑名单
                } else {
                    return "Under line"
                }
            }
        }, {
            title: 'status',
            dataIndex: 'status'
        }, {
            title: 'operation',
            dataIndex: "",
            render: (value, record) => {
                return (
                    <div style={{ textAlign: "left" }}>
                        <a href="#" onClick={me.showModal.bind(me, '审核', record, true)}>Review{/*审核*/}</a>
                    </div>
                )
            }
        }];

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
                <AddWin ref="AddWin" record={state.record} visible={state.visibleAdd} hideModal={me.hideModal} title={state.title} />
            </div>
        );
    }
})
