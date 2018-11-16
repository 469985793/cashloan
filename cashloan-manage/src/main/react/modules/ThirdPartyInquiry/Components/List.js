import React from 'react'
import {Table, Modal, Icon} from 'antd';
import Lookdetails from "./Lookdetails";
import AddRole from "./AddRole";
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
            visibleAc: false,
            rowRecord:[]

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
            url: '/modules/manage/creditdata/tpp/page.htm',
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
       // var record = this.state.selectedRow;
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title:title
        })
    },
    

    showModalAc(title,record,canEdit){
        this.setState({
            visibleAc: true,
            canEdit: canEdit,
            record: record,
            title:title
        },()=>{
            this.refs.AddRole.setFieldsValue(record);
        })
    },

    changeStaus(title,record){
        var params = {
            id: record.id
        }
        var urlStr = title == "禁用"?"/modules/manage/creditdata/tpp/disable.htm":"/modules/manage/creditdata/tpp/enalbe.htm";
        Utils.ajaxData({
            url:urlStr,
            data:params,
            callback:(result)=>{
                if(result.code == 200){
                    Modal.success({
                        title:result.msg,
                    });
                    this.hideModal();
                }else{
                    Modal.error({
                        title:result.msg,
                    })
                }
            }
        })
    },

    //隐藏弹窗
    hideModal(state) {
        this.setState({
            visible: false,
            visibleAc: false,
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
            pageSize: pagination.pageSize
        });
        this.fetch(params);
    },

    //行点击事件
    onRowClick(record) {
        this.setState({
            selectedRowKeys: [record.id],
            selectedRow: record,
            rowRecord:record
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
        let me=this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '第三方名称',
            dataIndex: 'name'
        }, {
            title: '第三方标识',
            dataIndex: 'nid'
        }, {
            title: '状态',
            dataIndex: "state",
            render: (text) => {
                return text == '10' ? '启用' : '禁用'
            }
        }, {
            title: '添加时间',
            dataIndex: "addTime"
        }, {
            title: '操作',
            dataIndex: "",
            render(text,record){
                return <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.showModal.bind(me, '接口详情', record, false)}>接口详情</a>
                            <span className="ant-divider"></span>
                            <a href="#" onClick={me.showModalAc.bind(me, '编辑', record, true)}>编辑</a>
                            <span className="ant-divider"></span>
                            { record.state == '10' ?
                                <a onClick={me.changeStaus.bind(me, '禁用', record) }>禁用</a> 
                                : 
                                <a onClick={me.changeStaus.bind(me, '启用', record) }>启用</a>
                            }
                        </div>
            }
        }];

        var state = this.state;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button className="ant-btn" onClick={me.showModalAc.bind(me,'新增')}> 
                    新增
                    </button>
                </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table" 
                       onRowClick={this.onRowClick}
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal.bind(me,state)} record={state.rowRecord}
                canEdit={state.canEdit} />
                <AddRole ref="AddRole" visible={state.visibleAc} title={state.title} hideModal={me.hideModal.bind(me,state)} record={state.rowRecord}
                canEdit={state.canEdit} />
            </div>
        );
    }
})
