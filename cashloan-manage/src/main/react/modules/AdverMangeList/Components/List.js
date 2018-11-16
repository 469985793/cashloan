import React from 'react'
import {
    Table,
    Modal
} from 'antd';
import AddWin from './AddWin';
import LookPir from './LookPir'
import ModifyModal from "./ModifyModal";
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
                current:1
            },
            canEdit: true,
            visible: false,
            visiblePir:false,
            record:"",
            dataRecord:[],
            dataName:[],
            title:""
        };
    },
    componentWillReceiveProps(nextProps, nextState) {
        this.clearSelectedList();
        var pagination = this.state.pagination;
        nextProps.params.pageSize = pagination.pageSize;
        this.fetch(nextProps.params);
    },
    hideModal() {
        this.setState({
            visible: false,
            visibleAdd: false,
            visiblePir:false,
            title:"",
            dataName:[],
            dataRecord:[],
            record:""
        });
     this.refreshList();
    },
    
    rowKey(record) {
        return record.id;
    },
    //分页
    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        pager.pageSize = pagination.pageSize;
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
                pageSize: this.state.pagination.pageSize,
                current: 1
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/adver/page.htm',
            data:params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current || result.page.current == 1) {
                pagination.current = 1
                } else {
                pagination.current = result.page.current
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
    //修改弹窗
    modifyModal(title, record, canEdit) {
       var record = record;
       var me = this;
        this.setState({
            canEdit: canEdit,
            visible: true,
            visibleAdd:false,
            visiblePir:false,
            title: title,
            record: record
        }, () => {  
            me.refs.ModifyModal.setFieldsValue(me.state.record);      
        });
    },
    //新增弹窗
    addModal(title, record, canEdit) {
       var record = record;
       var me = this;
        this.setState({
            canEdit: canEdit,
            visible: false,
            visibleAdd:true,
            visiblePir:false,
            title: title,
            record: record
        }, () => {  
            me.refs.AddWin.setFieldsValue(me.state.record);      
        });
    },
  //查看图片
    LookPir(title,record,canEdit){
        var record = record;
        var me = this;
            this.setState({
                canEdit: canEdit,
                visible: false,
                visibleAdd:false,
                visiblePir:true,
                title: title,
                record: record
            }, () => {  
                me.refs.LookPir.setFieldsValue(me.state.record);      
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
                trueurl = "/modules/manage/adver/onOrOff.htm"
            } else if (title == '禁用') {
                msg = '禁用成功';
                status = "20";
                tips = '您是否禁用';
                trueurl = "/modules/manage/adver/onOrOff.htm"
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
          title: '标题',
          dataIndex: 'title'
        }, {
          title: '链接地址',
          dataIndex: 'link'
        },{
          title: '排序号',
          dataIndex: 'sort'
        }, {
          title: '路径',
          dataIndex: 'path',
           render: (text, record)=>{
                return  <a href="#" title='点击可查看图片' onClick={me.LookPir.bind(me ,'查看图片',record,true)}>{record.path}</a>
            }
        }, {
          title: '启用状态',
          dataIndex: "state",
          render: (text, record)=>{
                if(record.state==10){
                    return "启用"
                }else if(record.state==20){
                    return "禁用"
                }else{
                    return "-"
                }
         }
       }, {
          title: '操作',
          dataIndex: "",
          render: (text,record) => {
                return  (
                  <div style={{ textAlign: "left" }}>
                  {/*<a href="#" onClick={this.modifyModal.bind(me, '查看',record, false)}>查看</a>*/}
                        <span>
                            <a href="#" onClick={me.modifyModal.bind(me ,'修改',record,true)}>修改</a>
                            <span className="ant-divider"></span> 
                            {record.state =='10'?
                                <a href="#" onClick={me.changeStatus.bind(me ,record,'禁用')}>禁用</a>:
                                <a href="#" onClick={me.changeStatus.bind(me ,record,'启用')}>启用</a> 
                            }     
                        </span>   
                  </div>
              )
           } 
        }];
       
        var state = this.state;
        var record = state.record;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                     <button  className="ant-btn" onClick={this.addModal.bind(this, '新增',record)}>
                        新增
                    </button> 
                </div>
                <Table columns={columns} rowKey={this.rowKey} size="middle" 
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    loading={this.state.loading}
                    onChange={this.handleTableChange} />

             <ModifyModal ref="ModifyModal" visible={state.visible}  title={state.title} hideModal={me.hideModal} record={state.record}
                canEdit={state.canEdit}  dataRecord={state.dataRecord}/>
             <LookPir ref="LookPir" visible={state.visiblePir}  title={state.title} hideModal={me.hideModal} record={state.record}
                canEdit={state.canEdit}  dataRecord={state.dataRecord}/>
             <AddWin ref="AddWin" value={state.value} visible={state.visibleAdd} title={state.title} hideModal={me.hideModal} record={state.selectedrecord}
                canEdit={state.canEdit} />

            </div>
        );
    }
})