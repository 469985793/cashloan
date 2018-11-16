import React from 'react'
import {
    Table,
    Modal,
    Icon
} from 'antd';

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
            rowRecord: []

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        var searchdata = {};
        var record=JSON.parse(localStorage.record);
        searchdata = {
            id: record.id
        }
        var params=objectAssign({},{pageSize: 2, current: 1,}, {search:JSON.stringify(searchdata)  })
        this.fetch(params);
    },
    componentDidMount() {
        var record=JSON.parse(localStorage.record);
        var searchdata = {};
        searchdata = {
            id: record.id
        }
        var params=objectAssign({}, {pageSize: 2, current: 1,}, {search:JSON.stringify(searchdata) })
        this.fetch(params);
    },
    fetch(params = {}) {
         var record=JSON.parse(localStorage.record);
        this.setState({
            loading: true
        });
        
        if(record){
            Utils.ajaxData({
            url: '/modules/manage/cr/factor/page.htm',
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
        }
    },

    //新增弹窗
    showModal(title, record,canEdit) {   
        Utils.getData({
            url: '/modules/manage/rule/getRuleConfig.htm',
            data: {
                id: record.id
            },
            callback: (result) => {
                var data = result.data;
                this.refs.SetScorParams.setFieldsValue(data.rule);
                this.setState({
                    visible: true,
                    title: title,
                    canEdit:canEdit,
                    ruleData: data
                });
            }
        });
    },
    //隐藏弹窗
    hideModal() {
        this.setState({
            visible: false,
            visibleProjName:false,
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
    onRowClick(record) {
        
        this.setState({
            selectedRowKeys: [record.id],
            selectedrecord: record
        });
        
    },
    goNextTab(){
     this.props.setTabClick('3')
    },
    refreshList() {
        var pagination = this.state.pagination;
        var record=JSON.parse(localStorage.record);
        var searchdata = {};
        searchdata = {
            id: record.id
        }
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize,
            search:JSON.stringify(searchdata)
        });
        this.fetch(params);
    },
   //新增项目名称弹窗
    showModalProjName(title, record) {
        this.setState({
            visibleProjName: true,
            title: title,
            record:record
        }); 
    },
    //修改规则状态,评分参数
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
            Utils.getData({
                url: '/modules/manage/cr/factorParam/updateState.htm',
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
    //修改规则状态,评分因子
    changeStausZong(title, record) {
        var params = {
                id: record.id
            }
            if (title == '禁用') {
                params.state = 20;
            } else {
                params.state = 10;
            }
            Utils.getData({
                url: ' /modules/manage/cr/factor/updateState.htm',
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
    },
    render() {
        const {
            loading,
            selectedRowKeys
        } = this.state;
        const rowSelection = {
            selectedRowKeys
        };
        var me = this;
        var state = this.state;

        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '评分因子',
            dataIndex: 'factorName'
        }, {
            title: '值',
            dataIndex: 'cvalue'
        }, {
            title: '表达式',
            dataIndex: 'formula'
        }, {
            title: '分值',
            dataIndex: 'paramScore'
        }, {
            title: '设置评分参数',
            dataIndex: 'paramScore',
            render: (text, record) => {
                return  <div>  
                          <a onClick={me.showModal.bind(me,'添加评分因子',record,false)}>编辑</a> 
                        </div>
            }
        }, {
            title: '操作',
            key: 'operation',
            render: (text, record) => {
                if(record.cardId){
return  <div>
                            { record.state == '10' ?
                                <a onClick={me.changeStausZong.bind(me, '禁用', record) }>禁用z</a> : <a onClick={me.changeStausZong.bind(me, '启用', record) }>启用z</a>
                            }
                        </div>
                }
                else{
                    return  <div>
                            { record.state == '10' ?
                                <a onClick={me.changeStaus.bind(me, '禁用', record) }>禁用</a> : <a onClick={me.changeStaus.bind(me, '启用', record) }>启用</a>
                            }
                        </div>
                }
            }
        }];
        return (
            <div className="block-panel">
            
                <Table columns={columns} rowKey={this.rowKey} 
                       dataSource={this.state.data}
                       rowSelection={rowSelection}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                       loading={this.state.loading}
                       />    
              <SetScorParams ref="SetScorParams"  visible={state.visible} ruleData={state.ruleData}  fetch={me.fetch} canEdit={state.canEdit} title={state.title} hideModal={me.hideModal} record={state.record} />

            </div>
        );
    }
})