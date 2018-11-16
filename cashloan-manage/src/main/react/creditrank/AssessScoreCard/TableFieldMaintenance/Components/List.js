import React from 'react'
import {
    Table,
    Modal
} from 'antd';
import AddWin from './AddWin'
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
            dataRecord:"",
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
            dataRecord:"",
            record:""
        });
     this.refreshList();
    },
    //新增跟编辑弹窗
    showModal(title, record, canEdit) {
      var search={
           id: record.id
       }
        this.setState({
            canEdit: canEdit,
            visible: true,
            title: title,
            record: record
        }, () => {
            if (title =="编辑") {
                 Utils.ajaxData({
                url: '/modules/manage/cr/info/infoPage.htm',
                data: {
                   pageSize: 10,
                    current: 1,
                    secreditrankh:JSON.stringify(search)
                },
                callback: (result) => {
                    //console.log("000000000000000",result)
                result.data.detail=JSON.parse(result.data[0].detail)
                 // this.refs.AddWin.setFieldsValue(result.data);
                 var dataName=[];
                  result.data.detail.forEach(item=>{
                         dataName.push(item.name)
                   })
                    //console.log("7777777",dataName);
                    this.setState({
                         dataRecord:result.data[0],
                         dataName:dataName
                    });
                }
            });
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
                current: 1
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/cr/info/infoPage.htm',
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
            pageSize: pagination.pageSize
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
        var status;
        var msg = "";
        var tips = "";
        var trueurl = "";
        if (title == '启用') {
            msg = '启用成功';
            status = "10";
            tips = '您是否启用';
            trueurl = "/modules/manage/cr/info/updateState.htm"
        } else if (title == '禁用') {
            msg = '禁用成功';
            status = "20";
            tips = '您是否禁用';
            trueurl = "/modules/manage/cr/info/updateState.htm"
        }
        confirm({
            title: tips,
            onOk: function() {
                Utils.ajaxData({
                    url: trueurl,
                    data: {
                        id: record.id,
                        state:status
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
            // type: 'checkbox',
            selectedRowKeys,
            // onSelectAll: this.onSelectAll,
        };
        const hasSelected = selectedRowKeys.length > 0;
        var columns = [{
            title: '表名',
            width:200,
            dataIndex: "tbNid"
        }, {
            title: '表中文名称',
             width:200,
            dataIndex: 'tbName'
        },{
            title: '字段详情',
            dataIndex: "detail",
            render(text,record){ 
                var record=JSON.parse(record.detail);
                   return(
                        record.map((item,i)=>{
                        return (
                            `${item.name}${i<record.length-1?',':''}`
                            )     
                     })) 
            }
        },{
            title:"状态",
            dataIndex:"state",
           render: (text, record) => {
                if (text == 10) {
                    return <span>启用</span>
                } else {
                    return <span>禁用</span>
                }
            }
        },{
            title:"操作",
            width:100,
            dataIndex:"",
            render(text,record){
                return  (
                    <div style={{ textAlign: "left" }}>
                        <a href="#" onClick={me.showModal.bind(me, '编辑',record, false)}>编辑</a>
                         <span className="ant-divider"></span>       
                         {record.state=="20"?<a href="#" onClick={me.changeStatus.bind(me ,record,'启用')}>启用</a>:<a href="#" onClick={me.changeStatus.bind(me,record,'禁用')}>禁用</a>}  
                   </div>
                )
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
                <Table columns={columns} rowKey={this.rowKey} size="middle"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    loading={this.state.loading}
                    onChange={this.handleTableChange} />
             <AddWin ref="AddWin" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record} canEdit={state.canEdit} dataRecord={state.dataRecord} dataName={state.dataName} />
            </div>
        );
    }
})