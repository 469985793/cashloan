import React from 'react'
import {
    Table,
    Modal
} from 'antd';
import Lookdetails from './Lookdetails'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
    getInitialState() {
        return {
            selectedRowKeys: [], // 这里配置默认勾选列
            loading: false,
            data: [],
            pagination: {},
            canEdit: true,
            visible: false,
            record:"",
            dataRecord:[],
            title:""
        };
    },
    componentWillReceiveProps(nextProps, nextState) {
        this.clearSelectedList();
        this.fetch(nextProps.params);
    },
    hideModal() {
        this.setState({
            visible: false,
            visibleDetails: false,
            title:"",
            dataRecord:[],
            record:""
        });
     this.refreshList();
    },
    //新增跟编辑弹窗
    showModal(title, record, canEdit) {
       var record = record;
       var me = this;
        this.setState({
            canEdit: canEdit,
            visible: true,
            title: title,
            record: record
        }, () => {
            if(title == '编辑'){
                me.refs.Lookdetails.setFieldsValue(me.state.record);
            }
        });
    },
    rowKey(record) {
        return record.id;
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
    fetch(params = {}) {
        this.setState({
            loading: true
        });

        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 10,
                current: 1,  
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/smstpl/list.htm',
            data:params,
            method: 'get',
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
    clearSelectedList() {
        this.setState({
            selectedRowKeys: [],
        });
    },
    refreshList() {
        var pagination = this.state.pagination;
        var params = objectAssign({}, this.props.params, {
                current: pagination.current,
                pageSize: pagination.pageSize,
        });
        this.fetch(params);
    },

    componentDidMount() {
        this.fetch();
    },
    onRowClick(record) {
        var id = record.id;
        this.setState({
            selectedRowKeys: [id],
            record: record
        });
    },
    changeStatus(record,title) {
        var record=record;
        var me = this;
        var msg = "";
        var tips = "";
        var state = "";
       
        if (title == '启用') {
            msg = '启用成功';
            tips = '您是否启用';
            state = 10;
        } else if (title == '禁用') {
            msg = '禁用成功';
            tips = '您是否禁用';
            state = 20;
        }
        var trueurl = state == '20' ? "/modules/manage/smstpl/disable.htm": "/modules/manage/smstpl/enable.htm";
        
        confirm({
            title: tips,
            onOk: function() {
                Utils.ajaxData({
                    url: trueurl,
                    data: {
                        id:record.id,
                    },
                    method: 'post',
                    callback: (result) => {
                        if (result.code == 200) {
                            Modal.success({
                                title: result.msg,
                            });
                            me.refreshList();
                        } else {
                           Modal.error({
                                title: result.msg,
                            });
                        }
                        
                    }
                });
            },
            onCancel: function() { }
        });
    },
   
    render() {
        var me = this;
        const {
            loading,
            selectedRowKeys
        } = this.state;
        const rowSelection = {
            selectedRowKeys,
        };              
        const hasSelected = selectedRowKeys.length > 0;
        
        var columns = [{
            title: '模板编号',
            dataIndex: 'number'
        }, {
            title: '类型名称',
            dataIndex: 'typeName'
        }, {
            title: '短信类型',
            dataIndex: 'type'
        }, {
            title: '短信模板',
            dataIndex: 'tpl'
        }, {
            title: '短信模板状态',
            dataIndex: 'state',
            render:(text) => {
                return text == '10' ? '启用' : '禁用'
            }
        }, {
            title: '操作',
            render: (text, record) => {
                return <div style={{textAlign:"left"}}>
                      <a href="#" onClick={me.showModal.bind(me,'编辑',record,true)}>编辑</a>
                      <span className="ant-divider"></span>
                   
                      {record.state =="20"?(<a href="#" onClick={me.changeStatus.bind(me,record,"启用")}>启用</a>):(<a href="#" onClick={me.changeStatus.bind(me,record,'禁用')}>禁用</a>)}
                   </div>
            }
        }];
       
        var state = this.state;
        var record = state.record;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button className="ant-btn" onClick={this.showModal.bind(this, '新增', record, true)}>
                        新增
                    </button>    
                </div>
                <Table columns={columns} rowKey={this.rowKey}
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    loading={this.state.loading}
                    onChange={this.handleTableChange} />
             <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record} canEdit={state.canEdit} dataRecord={state.dataRecord} />
            </div>
        );
    }
})