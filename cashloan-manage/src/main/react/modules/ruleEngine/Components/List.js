import React from 'react'
import {
    Table,
    Modal,
    Icon
} from 'antd';
import AddRole from './AddRole'
import Drop from './Drop'


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
                currentPage: 1
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/rule/list.htm',
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.currentPage;
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

    //新增弹窗
    showModal(title, record) {
        if (title == "新增") {
            this.setState({
                visible: true,
                title: title
            });
        } else {
            Utils.ajaxData({
                url: '/modules/manage/rule/getRuleConfig.htm',
                data: {
                    id: record.id
                },
                callback: (result) => {
                    var data = result.data;
                    //console.log("result.data77777",result.data);
                    this.refs.AddRole.setFieldsValue(data.rule);
                    this.setState({
                        visible: true,
                        title: title,
                        ruleData: data
                    });
                }
            });
        }

    },


    //隐藏弹窗
    hideModal() {
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
            currentPage: pagination.current,
            pageSize: pagination.pageSize
        });
        this.fetch(params);
    },

    //行点击事件
    onRowClick(record) {
        //console.log(record)
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
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },
    //修改规则状态,删除操作
    changeStaus(title, record) {
        if (title != '删除') {
            var params = {
                id: record.id
            }
            if (title == '禁用') {
                params.state = 20;
            } else {
                params.state = 10;
            }
            Utils.ajaxData({
                url: '/modules/manage/rule/modifyState.htm',
                data: params,
                callback: (result) => {
                    let resType = result.code == 200 ? 'success' : 'warning';
                    // Utils.openNotification({
                    //     type: resType,
                    //     message: result.msg,
                    //     duration: 2
                    // });
                    if(result.code==200){
                         Modal.success({
                             title: result.msg,
                            });
                        this.refreshList();
                    }else{
                         Modal.error({
                                title: result.msg,
                            });
                    }  
                }
            });
        }
    },

    expandedRowRender(record){
        return <Drop record={record} />
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
            title: '规则名称',
            dataIndex: 'name'
        }, {
            title: '规则配置数量',
            dataIndex: 'configCount'
        }, {
            title: '模式',
            dataIndex: 'type',
            render:(text) => {
                return text == '10' ? '评分模式' : '结果模式'
            }
        },
        // }, {
        //     title: '添加ip地址',
        //     dataIndex: 'addIp'
        // }, {
        //     title: '添加时间',
        //     dataIndex: 'addTime'
        // },
         {
            title: '状态',
            dataIndex: 'state',
            render: (text, record) => {
                if (text == 10) {
                    return <span>启用</span>
                } else {
                    return <span>禁用</span>
                }
            }
        }, {
            title: '操作',
            key: 'operation',
            render: (text, record) => {
                if (record.state == 10) {
                    return <span >
                     <a onClick={me.showModal.bind(me,'编辑',record)}>编辑</a>
                     <span className="ant-divider"></span>
                     <a onClick={me.changeStaus.bind(me,'禁用',record)}>禁用</a>
                   </span>
                } else {
                    return <span >
                      <a onClick={me.showModal.bind(me,'编辑',record)}>编辑</a>
                      <span className="ant-divider"></span>
                      <a onClick={me.changeStaus.bind(me,'启用',record)}>启用</a>
                    </span>
                }
            }
        }];
        return (
            <div className="block-panel">
            <div className="actionBtns" style={{ marginBottom: 16 }}>
              <button onClick={me.showModal.bind(me,'新增')} className="ant-btn"> 
                新增
              </button>
            </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                       loading={this.state.loading}
                />
                <AddRole ref="AddRole"  visible={state.visible} ruleData={state.ruleData}  fetch={me.fetch} title={state.title} hideModal={me.hideModal} record={state.record} />
            </div>
        );
    }
})