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
            record: "",
            dataRecord: "",
            dataEdit: "",
            dataName: [],
            title: "",
            defaultTitle:"",
            borrowIdDefList:[],
            checkDefault:[],
            rankArray:[],
            rankmateArray:[],
            borrowIdAddList:[],
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
            title: "",
            dataName: [],
            dataRecord: "",
            dataEdit: "",
            record: "",
            defaultTitle:"",
            borrowIdDefList:[],
            checkDefault:[],
            rankArray:[],
            rankmateArray:[],
            borrowIdAddList:[],
        });
        this.refreshList();
    },
    //新增跟编辑弹窗
    showModal(title, record, canEdit) {
        var search = {
            id: record.id
        }
         var paramsCode =title =="新增额度类型"? {
                code:"CREDIT_TYPE"
            }:{
                code:"CREDIT_TYPE",
                id: record.creditTypeId
            }
         var creditTypeUrl = title =="新增额度类型"?'/modules/manage/system/dict/listByTypeCode.htm':'/modules/manage/system/dict/listUpdateCode.htm'
        Utils.ajaxData({
            url: creditTypeUrl,
            data: paramsCode,
            callback: (result) => {
                var data=result.data;
                var rankArray=[];
                var rankmateArray=[];
                data.forEach(item=>{
                  rankArray.push(<Option key={item.id} value={item.id}>{item.itemValue}</Option>)
                   return rankArray
                });
                data.forEach(item=>{
                  rankmateArray.push({id:item.id,itemValue:item.itemValue})
                })
                this.setState({    
                    rankArray:rankArray,
                    rankmateArray:rankmateArray
                });
            }
        });
        Utils.ajaxData({
            url: '/modules/manage/cr/creditType/findUnUsedBorrowType.htm',
            method:"post",
            callback: (result) => {
                let borrowIdAddList = [];
                var defaultTitle = this.state.defaultTitle;
                result.data.map((item) => {
                    borrowIdAddList.push({ id: Number(item.id), "borrowId": item.itemValue,title:defaultTitle});
                    return borrowIdAddList
                })
                this.setState({
                    borrowIdAddList: borrowIdAddList
                });
            }
        });
        this.setState({
            canEdit: canEdit,
            visible: true,
            title: title,
            record: record
        }, () => {
            if (title == "编辑" || title == "查看") {
                Utils.ajaxData({
                    url: '/modules/manage/cr/creditType/findDetail.htm',
                    data: search,
                    callback: (result) => {
                        result.data.cardId = Number(result.data.cardId);
                        result.data.rankId = Number(result.data.rankId);
                        result.data.creditTypeId = Number(result.data.creditTypeId);
                        result.data.itemValue = result.data.name;
                        result.data.borrowTypeId = result.data.borrowTypeId.split(",");
                        result.data.borrowTypeName = result.data.borrowTypeName.split(",");
                        this.refs.AddWin.setFieldsValue(result.data);
                        var dataName = [];
                        this.setState({
                            dataRecord: result.data,
                            dataName: dataName
                        });
                    }
                });
                Utils.ajaxData({
                    url: '/modules/manage/cr/creditType/editBorrowType.htm',
                    data: search,
                    callback: (result) => {
                        var defaultTitle = title;
                        var borrowIdDefList = [];
                        var checkDefault = [];
                        var dataEdit = [];
                        result.data.map((item) => {
                            if(title == "查看"){
                                item.type !=""?(dataEdit.push({ id: Number(item.borrowTypeId), "borrowId": item.borrowTypeName,title:defaultTitle,type:item.type })):dataEdit;
                            }else{
                                dataEdit.push({ id: Number(item.borrowTypeId), "borrowId": item.borrowTypeName,title:defaultTitle,type:item.type });
                            }
                            return dataEdit
                        });
                        result.data.map((item) => {
                            borrowIdDefList.push(Number(item.borrowTypeId))
                            return borrowIdDefList
                        });
                        result.data.map((item) => {
                            item.type !=""?(checkDefault.push(Number(item.borrowTypeId))):checkDefault;
                            return checkDefault
                        });
                        this.setState({
                            dataEdit: dataEdit,
                            defaultTitle:defaultTitle,
                            checkDefault:checkDefault
                        });
                    }
                });
            }
        });
    },
    rowKey(record) {
        return record.id;
    },
    fetch(params = {}) {
        this.setState({
            loading: true
        });
        Utils.ajaxData({
            url: '/modules/manage/cr/creditType/creditTypeList.htm',
            data: params,
            callback: (result) => {
                this.setState({
                    loading: false,
                    data: result.data
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
        var params = objectAssign({}, this.props.params
        );
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
            title: '额度类型名称',
            dataIndex: "name"
        }, {
            title: '关联评分卡',
            dataIndex: "cardName"
        }, {
            title: '关联额度等级',
            dataIndex: "rankName"
        }, {
            title: '关联借款类型',
            dataIndex: "borrowTypeName"
        }, {
            title: "操作",
            width: 100,
            dataIndex: "",
            render(text, record) {
                return (
                    <div style={{ textAlign: "left" }}>
                        <a href="#" onClick={me.showModal.bind(me, '编辑', record, true)}>编辑</a>
                        <span className="ant-divider"></span>
                        <a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>查看</a>
                    </div>
                )
            }
        }];

        var state = this.state;
        var record = state.record;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button className="ant-btn" onClick={this.showModal.bind(this, '新增额度类型', record, true)}>
                        新增额度类型
                    </button>
                </div>
                <Table columns={columns} rowKey={this.rowKey} size="middle"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    loading={this.state.loading}
                    onChange={this.handleTableChange} />
                <AddWin ref="AddWin" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record} canEdit={state.canEdit} dataRecord={state.dataRecord} 
                rankArray={state.rankArray} borrowIdAddList={state.borrowIdAddList} rankmateArray={state.rankmateArray} checkDefault={state.checkDefault} dataEdit={state.dataEdit} defaultTitle={state.defaultTitle} dataName={state.dataName} />
            </div>
        );
    }
})