import React from 'react'
import {
    Table,
    Modal,
    Icon,
    Select
} from 'antd';
import SetScorParams from './SetScorParams'
import AddProjName from './AddProjName'
import AddScoreFactor from './AddScoreFactor'
const Option = Select.Option;
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
            scoreTotal:"",
            canEdit: true,
            visible: false,
            visibleScorecard: false,
            visibleProjName: false,
            visibleScoreFactor: false,
            pictureData: [],
            creditReportData: [],
            rowRecord: [],
            expandedRowKeys:[],
            num:1

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        var searchdata = {};
        var record = JSON.parse(localStorage.record);
        searchdata = {
            cardId: record.id
        }
        var params = objectAssign({}, { pageSize: 10, current: 1, }, { search: JSON.stringify(searchdata) })
        this.fetch(params);
    },
    componentDidMount() {
        var record = JSON.parse(localStorage.record);
        var searchdata = {};
        searchdata = {
            cardId: record.id
        }
        var params = objectAssign({}, { pageSize: 10, current: 1, }, { search: JSON.stringify(searchdata) })
        this.fetch(params);
    },
    fetch(params = {}) {
        var record = JSON.parse(localStorage.record);
        this.setState({
            loading: true
        });

        if (record) {
            Utils.ajaxData({
                url: '/modules/manage/cr/item/page.htm',
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
                    },()=>{
                        if(this.state.num==1){
                            this.expandedAll();
                        }
                       this.setState({
                           num:2
                       })
                    });
                }
            });
        }
    },

    //编辑弹窗
    showModal(title, record, canEdit) {
        var search = {
            id: record.id
        }
        Utils.ajaxData({
            url: '/modules/manage/cr/factor/page.htm',
            data: {
                pageSize: 10,
                current: 1,
                secreditrankh: JSON.stringify(search)
            },
            callback: (result) => {
                var data = result.data;
                localStorage.itemId = data[0].itemId;
                var tabString = data[0].tab;
                var tabArray = tabString.split(',');
                data[0].table = tabArray;
                data[0].cardname = data[0].cardName;
                data[0].itemName = data[0].itemName;
                this.refs.SetScorParams.setFieldsValue(data[0]);
                localStorage.factorName = data[0].factorName;
                localStorage.id = data[0].id;
                this.setState({
                    visible: true,
                    title: title,
                    canEdit: canEdit,
                    ruleData: data,

                });
            }
        });
        var projectList = [];
        var cardRecord = JSON.parse(localStorage.record);
        Utils.ajaxData({
            url: '/modules/manage/cr/item/list.htm',
            data: { cardId: cardRecord.id },
            callback: (result) => {
                var itemIdData = result.data;
                projectList = result.data.map((item) => {
                    return (<Option key={item.itemName} value={item.itemName}>{item.itemName}</Option>);
                });
                this.setState({
                    projectList: projectList,
                    itemIdData: itemIdData
                })
            }
        })
    },
    //隐藏弹窗
    hideModal() {
        this.setState({
            visible: false,
            visibleProjName: false,
            visibleScoreFactor: false,
            selectedIndex: '',
            selectedRowKeys: [],
        });
        this.refreshList();
    },


    rowKey(record) {
        return record.nid;
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
            record: record
        });

    },
    goNextTab(record) {
        localStorage.record = JSON.stringify(record);
        this.props.setTabClick('3')
    },
    refreshList() {
        var pagination = this.state.pagination;
        var record = JSON.parse(localStorage.record);
        var searchdata = {};
        searchdata = {
            cardId: record.id
        }
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize,
            search: JSON.stringify(searchdata)
        });
        this.fetch(params);
    },
    //新增项目名称弹窗
    showModalProjName(title, record) {
        var search = {
            id: record.id
        }
        if (title == '修改项目名称') {
            Utils.ajaxData({
                url: '/modules/manage/cr/item/page.htm',
                data: {
                    pageSize: 10,
                    current: 1,
                    search: JSON.stringify(search)
                },
                callback: (result) => {
                    this.refs.AddProjName.setFieldsValue(result.data[0]);
                }
            });
        }
        this.setState({
            visibleProjName: true,
            title: title,
            record: record
        });
    },
    //新增评分因子名称弹窗
    showModalScoreFactor(title) {
        var projectList = [];
        var cardRecord = JSON.parse(localStorage.record);
        Utils.ajaxData({
            url: '/modules/manage/cr/item/list.htm',
            data: { cardId: cardRecord.id },
            callback: (result) => {
                var itemIdData = result.data;
                projectList = result.data.map((item) => {
                    return (<Option key={item.itemName} value={item.itemName}>{item.itemName}</Option>);
                });
                this.setState({
                    projectList: projectList,
                    itemIdData: itemIdData
                })
            }
        })
        this.setState({
            visibleScoreFactor: true,
            title: title,
        });
    },
    //删除评分项目
    deleteProject(record) {
        var me = this;
        confirm({
        title: '是否确认要删除这项内容',
        onOk: function () {
            Utils.ajaxData({
            url: "/modules/manage/cr/item/delete.htm",
            data: {
                id: record.id,
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
        });
    },
    //删除因子
    deleteFactor(record) {
        var me = this;
        confirm({
        title: '是否确认要删除这项内容',
        onOk: function () {
            Utils.ajaxData({
            url: "/modules/manage/cr/factor/delete.htm",
            data: {
                id: record.id,
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
        });
    },
    //修改规则状态,评分项目
    changeStausItem(title, record) {
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
                url: '/modules/manage/cr/item/updateState.htm',
                data: params,
                callback: (result) => {
                    let resType = result.code == 200 ? 'success' : 'warning';
                    // Utils.openNotification({
                    //     type: resType,
                    //     message: result.msg,
                    //     duration: 2
                    // });
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                        });
                        this.refreshList();
                    } else {
                        Modal.error({
                            title: result.msg,
                        });
                    }
                }
            });
        }
    },
    //修改规则状态,评分参数
    changeStausParam(title, record) {
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
                url: '/modules/manage/cr/factorParam/updateState.htm',
                data: params,
                callback: (result) => {
                    let resType = result.code == 200 ? 'success' : 'warning';
                    // Utils.openNotification({
                    //     type: resType,
                    //     message: result.msg,
                    //     duration: 2
                    // });
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                        });
                        this.refreshList();
                    } else {
                        Modal.error({
                            title: result.msg,
                        });
                    }
                }
            });
        }
    },
    //修改规则状态,评分因子
    changeStausFactor(title, record) {
        var params = {
            id: record.id
        }
        if (title == '禁用') {
            params.state = 20;
        } else {
            params.state = 10;
        }
        Utils.ajaxData({
            url: ' /modules/manage/cr/factor/updateState.htm',
            data: params,
            callback: (result) => {
                let resType = result.code == 200 ? 'success' : 'warning';
                // Utils.openNotification({
                //     type: resType,
                //     message: result.msg,
                //     duration: 2
                // });
                if (result.code == 200) {
                    Modal.success({
                        title: result.msg,
                    });
                    this.refreshList();
                } else {
                    Modal.error({
                        title: result.msg,
                    });
                }
            }
        });
    },
    getExpandeRowKeys(data) {
    var me = this;
    var expandedRowKeys = [];

    data.length && data.forEach(function(record, i) { 
      if (record.children) {
        expandedRowKeys.push(me.rowKey(record));
        expandedRowKeys = expandedRowKeys.concat(me.getExpandeRowKeys(record.children));
      }
    });

    return expandedRowKeys;
  },
  expandedAll() {
    var data = this.state.data;
    var expandedRowKeys = this.getExpandeRowKeys(data);

    function sortNumber(a, b) {
      return a - b
    }
    this.setState({
      expandedRowKeys: expandedRowKeys.sort(sortNumber).reverse()
    });
    //console.log(expandedRowKeys.sort(sortNumber).reverse());
  },
   onExpandedRowsChange(expandedRowKeys) {
    this.setState({
      expandedRowKeys: expandedRowKeys
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
            title: '项目名称',
            dataIndex: 'itemName',
            render: (text, record) => {
                if (record.score >= 0) {
                    return record.itemName
                } else if (record.cvalue) {
                    return record.formula + record.cvalue
                } else {
                    return record.factorName
                }
            }
        }, {
            title: '分值',
            dataIndex: 'score',
            render: (text, record) => {
                if (record.score >= 0) {
                    return record.score
                } else if (record.factorScore) {
                    return record.factorScore
                } else {
                    return record.paramScore
                }
            }
        }, {
            title: '权重',
            dataIndex: 'sc5ore',
            render: (text, record) => {
                if (record.score >= 0) {
                    let scoreTotal =0;
                    this.state.data.forEach(item=>{
                            scoreTotal += parseInt(item.score)
                    })
                    return (scoreTotal !="0"?(record.score* 100 / scoreTotal).toFixed(2) +"%":"0%")
                } else if (record.factorScore) {
                    let scoreChildTotal = 0;
                    this.state.data.forEach(item=>{
                        if(item.article == "items" && item.id == record.itemId){
                            scoreChildTotal =item.score != 0 ?(record.factorScore* 100 /item.score).toFixed(2)  + "%":"0%";
                         }
                    })
                    return scoreChildTotal
                } else {
                    return record.paramScore
                }
            }
        },{
            title: '操作',
            key: '',
            render: (text, record) => {
                if (record.score >= 0) {
                    return <div>
                            <span>
                                 <a href="#" onClick={me.deleteProject.bind(me, record) }>删除 </a>
                            </span>
                    </div>
                } else if (record.itemId) {
                    return <div>
                            <span>
                                <a href="#" onClick={me.deleteFactor.bind(me, record) }>删除</a>
                                &nbsp;<span className="ant-divider"></span>&nbsp;
                                    <a onClick={me.showModal.bind(me, '编辑评分因子', record, false)}>编辑</a>
                            </span>
                    </div>
                }

            }
        }
        ];
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button onClick={me.showModalProjName.bind(me, '新增评分项目', false)} className="ant-btn">
                        新增评分项目
              </button>
                    <button onClick={me.showModalScoreFactor.bind(me, '新增评分因子', false)} className="ant-btn">
                        新增评分因子
              </button>
                </div>
                <Table columns={columns} rowKey={this.rowKey}
                    dataSource={this.state.data}
                    rowClassName={this.rowClassName}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                    loading={this.state.loading}
                    onRowClick={this.onRowClick}
                    expandedRowKeys={this.state.expandedRowKeys}
                    onExpandedRowsChange={this.onExpandedRowsChange}
                    />
                <AddScoreFactor ref="AddScoreFactor" visible={state.visibleScoreFactor} title={state.title} projectList={state.projectList} itemIdData={state.itemIdData} hideModal={me.hideModal} record={state.record} />
                <AddProjName ref="AddProjName" visible={state.visibleProjName} title={state.title} hideModal={me.hideModal} record={state.record} />
                <SetScorParams ref="SetScorParams" visible={state.visible} ruleData={state.ruleData} projectList={state.projectList} itemIdData={state.itemIdData} canEdit={state.canEdit} title={state.title} hideModal={me.hideModal} record={state.record} />
            </div>
        );
    }
})