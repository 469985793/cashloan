import React from 'react'
import {Table, Modal, Icon} from 'antd';
var confirm = Modal.confirm;
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
                userName: " ",
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/sysProfit/findAgentList.htm',
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
    showModal(title,record, canEdit) {
      
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title: title
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

    SetAgent(record, title){
        var me = this;
        if(title=="设置为一级代理商"){
            confirm({
                title: "是否设置为一级代理商",
                onOk: function() {
                Utils.ajaxData({
                    url: "/modules/manage/profit/saveAgent.htm",
                    data: {
                        inviteId: record.userId,
                        level: 1,
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
                onCancel: function() {}
            });
        }else if(title=="取消一级代理"){
            confirm({
                title: "是否取消一级代理",
                onOk: function() {
                Utils.ajaxData({
                    url: "/modules/manage/profit/freezeAgent.htm",
                    data: {
                        userId: record.userId,
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
                onCancel: function() {}
            });
        }
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
            title: '手机号码',
            dataIndex: 'userName',
        }, {
            title: '代理等级',
            dataIndex: "level",
            render: (text) => {
                switch(text){
                    case 1: 
                        return "一级";
                    case 2: 
                        return "二级";
                    case 3: 
                        return "三级";
                }
            }
        }, {
            title: '分润率(%)',
            dataIndex: "rate",
            render: (text) => {
                if(text){
                    return text.toFixed(2);
                }
            }
        }, {
            title: '添加时间',
            dataIndex: 'createTime',
        },{
            title: '更新时间',
            dataIndex: 'updateTime',
        },{
            title: '操作',
            render: (value, record) => {
                if(record.level!=1){
                    return(
                    <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.SetAgent.bind(this, record, "设置为一级代理商")}>设置为一级代理商</a>
                    </div>  
                    )
                }else{
                    return(
                    <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.SetAgent.bind(this, record, "取消一级代理")}>取消一级代理</a>
                    </div>  
                    )
                }
            } 
        }]

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
            </div>
        );
    }
})
