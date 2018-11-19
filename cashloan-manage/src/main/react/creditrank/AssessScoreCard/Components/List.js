import React from 'react'
import {
    Table,
    Modal,
    Icon
} from 'antd';
import SetScorParams from './SetScorParams'
import AddScorecard from './AddScorecard'

import ScoretemList from './ScoretemList'
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
            visibleScorecard: false,
            visibleProjName: false,
            visibleDetail: false,
            pictureData: [],
            creditReportData: [],
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
            url: '/modules/manage/cr/card/page.htm',
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

    //新增弹窗
    showModal(title, record,canEdit) {
        this.setState({
            visible: true,
            title: title,
            canEdit: canEdit,
        });
    },
    //新增评分卡弹窗
    showModalScorecard(title, record) {
        var search={
            id:record.id
        }
        if(title=='Edit'){//编辑
          Utils.ajaxData({
            url: '/modules/manage/cr/card/page.htm',
            data: {
                pageSize: 10,
                current: 1,
                search:JSON.stringify(search)    
            },
            callback: (result) => {
                this.refs.AddScorecard.setFieldsValue(result.data[0]);
            }
          });
        }
        this.setState({
            visibleScorecard: true,
            title: title
        });
    },
   
    //隐藏弹窗
    hideModal() {
        this.setState({
            visible: false,
            visibleScorecard:false,
            visibleProjName:false,
            visibleDetail:false,
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
    goNextTab(record){
      localStorage.record=JSON.stringify(record);
      this.props.setTabClick('2')
    },
    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },
    //修改规则状态,删除操作
    changeStaus(title, record) {
        if (title != 'Delete') {//删除
            var params = {
                id: record.id
            }
            if (title == 'Disable') {//禁用
                params.state = 20;
            } else {
                params.state = 10;
            }
            Utils.ajaxData({
                url: '/modules/manage/cr/card/updateState.htm',
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
                        this.hideModal();
                    }else{
                         Modal.error({
                                title: result.msg,
                            });
                    }  
                }
            });
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
        const hasSelected = selectedRowKeys.length > 0;
        var me = this;
        var state = this.state;
        var props = this.props;
        var columns = [{
            title: 'Score card name',//评分卡名称
            dataIndex: 'cardName'
        }, {
            title: 'Status',//状态
            render: (text, record) => {
            	return  record.state == '10' ? 'Enable':'Disable';//启用 禁用
            }
        }, {
            title: 'Score',//分值
            dataIndex: 'score'
        }, {
            title: 'Operating',//操作
            key: '',
            render: (text, record) => {
                return  <div>
                             
                            { record.state == '10' ?
                               <span>
                                 <a onClick={me.showModalScorecard.bind(me,'Edit',record,false)}>{/*编辑*/}Edit</a>
                                 <span className="ant-divider"></span>
                                 <a onClick={me.goNextTab.bind(me,record)}>View rating items{/*查看评分项目*/}</a>
                                 &nbsp;<span className="ant-divider"></span>&nbsp;
                                 <a onClick={me.changeStaus.bind(me, 'Disable', record) }>{/*禁用*/}Disable</a>
                               </span>
                              :<span>
                                 <a onClick={me.showModalScorecard.bind(me,'Edit',record,false)}>{/*编辑*/}Edit</a>
                                 <span className="ant-divider"></span>
                                 <a onClick={me.goNextTab.bind(me,record) }>{/*查看评分项目*/}View rating items</a>
                                 &nbsp;<span className="ant-divider"></span>&nbsp;
                                 <a onClick={me.changeStaus.bind(me, 'Enable', record) }>{/*启用*/}Enable</a>
                               </span>
                            }
                        </div>
            }
        }];
        return (
            <div className="block-panel">
            <div className="actionBtns" style={{ marginBottom: 16 }}>
              <button onClick={me.showModalScorecard.bind(me,'Add a scorecard',false)} className="ant-btn"> 
                {/*新增评分卡*/}Add a scorecard
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
                <SetScorParams ref="SetScorParams"  visible={state.visible} canEdit={state.canEdit} ruleData={state.ruleData}  fetch={me.fetch} title={state.title} hideModal={me.hideModal} record={state.record} />
                <AddScorecard ref="AddScorecard"  visible={state.visibleScorecard}  title={state.title} hideModal={me.hideModal} record={state.record} />
              
            </div>
        );
    }
})