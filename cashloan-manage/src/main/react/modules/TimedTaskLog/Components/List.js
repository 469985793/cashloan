import React from 'react'
import {
    Table,
    Modal
} from 'antd';
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
            dataName:[],
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
            dataName:[],
            dataRecord:[],
            record:""
        });
     this.refreshList();
    },
    //新增跟编辑弹窗
    showModal(title, record, canEdit) {
       ////console.log("99999",record)
       var record = record;
       var me = this;
        this.setState({
            canEdit: canEdit,
            visible: true,
            title: title,
            record: record
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
    fetch(params) {
        this.setState({
            loading: true
        });
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 10,
                current: 1,
                search:JSON.stringify({name: ''})
            }
        }
        if(!params.search){
            params.search= JSON.stringify({name:''});
        }
        Utils.ajaxData({
            url: '/modules/quartzLog/page.htm',
            data:params,
            method: 'post',
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
                // search:JSON.stringify({name: ''})
        });
        this.fetch(params);
    },

    componentDidMount() {
        var pagination = this.state.pagination;
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize
        });
        this.fetch(params);
    },
    onRowClick(record) {
        var id = record.id;
        this.setState({
            selectedRowKeys: [id],
            record: record
        });
    },
   
    render() {
        var me = this;
        const {
            loading,
            selectedRowKeys
        } = this.state;
        const hasSelected = selectedRowKeys.length > 0;
        var columns = [{
            title: '任务名',
            dataIndex: "name"
        }, {
            title: '执行结果',
            dataIndex: 'resultStr'
        },{
            title: '执行时间',
            dataIndex: "startTime"
        },{
            title: '执行结果',
            dataIndex: "remark"
        },{
            title: '用时(毫秒)',
            dataIndex: "time"
        }];
       
        var state = this.state;
        var record = state.record;
        return (
            <div className="block-panel">
                {/**
                    <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button className="ant-btn" onClick={this.showModal.bind(this, '新增', record, true)}>
                        新增
                    </button>    
                </div> */}
                <Table columns={columns} rowKey={this.rowKey} size="middle"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    loading={this.state.loading}
                    onChange={this.handleTableChange} />
            </div>
        );
    }
})