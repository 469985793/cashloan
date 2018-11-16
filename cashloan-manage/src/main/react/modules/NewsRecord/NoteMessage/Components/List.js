import React from 'react'
import {
    Table,
    Modal,
    Icon
} from 'antd';

const objectAssign = require('object-assign');
export default React.createClass({
    getInitialState() {
        return {
            selectedRowKeys: [], // 这里配置默认勾选列
            loading: false,
            data: [],
            pagination: {
                // pageSize: 10,
                // current: 1
            },
            canEdit: true,
            visible: false,
            pictureData: [],
            creditReportData: [],
            rowRecord: [],

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
                current: 1
            }
        }
        
        Utils.ajaxData({
            url: '/modules/manage/sms/list.htm',
            method:  'get',
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
                    data: result.data.list,
                    pagination
                });
            }
        });
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
        if (this.state.selectedRowKeys.indexOf(record.id) == -1) {
            this.setState({
                selectedRowKeys: [record.id],
                selectedRow: record,
                rowRecord: record
            });
        } else {
            this.setState({
                selectedRowKeys: [],
                selectedRow: {},
                rowRecord: {}
            });
        }
    },

    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        return (record.id == selected && selected !== '') ? 'selectRow' : '';
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
        var me = this;
        var state = this.state;

        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '订单号',
            dataIndex: 'orderNo'
        }, {
            title: '手机号码',
            dataIndex: 'phone'
        }, {
            title: '验证码',
            dataIndex: 'code'
        }, {
            title: '响应信息',
            dataIndex: 'resp'
        }, {
            title: '响应时间',
            dataIndex: 'respTime'
        }, {
            title: '发送时间',
            dataIndex: 'sendTime'
        }, {
            title: '短信类型',
            dataIndex: 'smsType'
        }, {
            title: '短信状态',
            dataIndex: 'state',
            render: (text, record) => {
                if (text == 10) {
                    return <span>发送成功</span>
                } else if(text == 20) {
                    return <span>发送失败</span>
                }else{
                    return <span>发送中</span>
                }
            }
        }, {
            title: '短信验证次数',
            dataIndex: 'verifyTime'
        }];
        
        return (
            <div className="block-panel">
            <div className="actionBtns" style={{ marginBottom: 16 }}>
            </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                       loading={this.state.loading}
                />  
            </div>
        );
    }
})