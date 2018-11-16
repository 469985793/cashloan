import React from 'react'
import {Table, Modal, Icon} from 'antd';
import Lookdetails from "./Lookdetails";
var repaymentTypeText={'10':'待审核', '20': '审核中' ,'30': '通过','40' :'已拒绝' ,'50': '还款中', '60' :'还款完成', '70': '逾期'}
const objectAssign = require('object-assign');
import AddWin from "./AddWin";
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
    componentDidMount(){
        this.fetch();
    },
    fetch(params = {}) {
        this.setState({
            loading: true
        });
        var year, month;
        var year1, month1;
        var date = new Date();
        year1 =  date.getFullYear();
        month1 = date.getMonth()+1;
        if(month1>=10){
            year = year1;
            month = '0'+(month1-9);
        }else{
            year = year1-1;
            month = month1<7?'0'+(month1+3):month1+3;
        }
        month1 = month1<10 ? '0'+month1 : month1;
        if (!params.search) {
            params = {
                search: JSON.stringify({startDate:year+'-'+month,endDate:year1+'-'+month1}),
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/sysanalysis/monthRepay.htm',
            method: "post",
            data: params,
            callback: (result) => {
                this.setState({
                    loading: false,
                    data: result.data,
                    pagination: {
                        pageSize: 10,
                        current: 1
                    },
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
        return record.date;
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
            title: '月份',
            dataIndex: 'date'
        }, {
            title: '还款笔数',
            dataIndex: 'repayCount'
        }, {
            title: '逾期还款笔数',
            dataIndex: 'overdueCount'
        }, {
            title: '逾期还款笔数占比(%)',
            dataIndex: 'apr'
        }, {
            title: '还款金额',
            dataIndex: "repayAmt",
        },{
            title: '逾期还款金额',
            dataIndex: "penaltyRepayAmt"
        },{
            title: '逾期还款金额占比(%)',
            dataIndex: "amountApr"
        }]
        // },{
        //     title: '操作',
        //     dataIndex: "",
        //     render: (value,record) => {
        //         return(
        //            <div style={{ textAlign: "left" }}>
        //                 <a href="#" onClick={me.showModal.bind(me, '查看详情',record, false)}>查看详情</a>
        //            </div>  
        //         )
        //     } }]; 

        var state = this.state;
        return (
            <div className="block-panel">
                <Table columns={columns} rowKey={this.rowKey} ref="table" 
                       onRowClick={this.onRowClick}
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
                canEdit={state.canEdit} />
                <AddWin ref="AddWin"  visible={state.visibleAdd} hideModal={me.hideModal} title={state.title}/>
            </div>
        );
    }
})
