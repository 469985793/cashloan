import React from 'react'
import {
    Table,
    Modal,
    Icon,
    Select
} from 'antd';

import AddRole from './AddRole';

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
            idData:""   
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
       // params.id=2;
        Utils.ajaxData({
            url: '/modules/manage/cr/rank/getRankList.htm',
            data: params,
            callback: (result) => {
                //console.log("dddddddddd",result)
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current) {
                    pagination.current = 1
                };
                var data=result.data;
                this.setState({
                    loading: false,
                    data: result.data,
                    pagination
                });
            }
        });
    },
//新增编辑查看弹窗
   showModal(title, record,canEdit) {
    
        if (title == "新增评分等级") {
            this.setState({
                visible: true,
                title: title,
                canEdit:canEdit
            });
        } else {
            var params = {
                            pageSize: 10,
                            current: 1,

                        }   
             params.search=JSON.stringify({rankId:record.id}); 
            Utils.ajaxData({
                url: '/modules/manage/cr/rankDetail/page.htm',
                data: params,
                callback: (result) => {
                   // //console.log("llll",result);
                    var data = result.data;
                 //   //console.log("result.data77777",data);
                    //console.log("5555",data);
                 this.refs.AddRole.setFieldsValue(record);
                    this.setState({
                        rankData: data,
                        canEdit:canEdit,
                        visible: true,
                        title: title,
                        idData:record.id
                    });
                }
            });
        }
    },

   //隐藏窗口

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
            current: pagination.current,
            pageSize: pagination.pageSize
        });
        this.fetch(params);
    },

    //行点击事件
    onRowClick(record) {
        
        this.setState({
            selectedRowKeys: [record.id],
            visibleDetail:true,
            record:record
        });
       
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
        const hasSelected = selectedRowKeys.length > 0;
        var me = this;
        var state = this.state;
        var props = this.props;
        var columns = [{
            title: '评分等级名称',
            dataIndex: 'rankName'
        },{
            title:"区间数量",
            dataIndex:"num"
        }, {
            title: '操作',
            dataIndex: '',
            render: (text, record) => {
                return  <div>
                           <a onClick={me.showModal.bind(me,'额度维护',record,true)}>额度维护</a>
                               <span className="ant-divider"></span>
                           <a onClick={me.showModal.bind(me,'查看',record,false)}>查看</a>
                        </div>
            }
        }];

        //console.log('state.rankData',state.rankData)
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button className="ant-btn" onClick={me.showModal.bind(me,'新增评分等级',true)}>
                        新增评分等级
                    </button>    
                </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                       loading={this.state.loading}
                       onRowClick={this.onRowClick}
                />    
                <AddRole  ref="AddRole" visible={state.visible} title={state.title} hideModal={me.hideModal}  rankData={state.rankData} idData={state.idData} canEdit={state.canEdit} />         
            </div>
        );
    }
})