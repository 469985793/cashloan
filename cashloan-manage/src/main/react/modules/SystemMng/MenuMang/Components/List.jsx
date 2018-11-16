import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddMenu from './AddMenu'
var confirm = Modal.confirm;
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      expandedRowKeys: [],
      canEdit: true,
      visible: false,
      num:1
    };
  },
  componentWillReceiveProps(){

  },
  componentDidMount() {

    this.fetch();
  },
  hideModal() {
    this.setState({
      visible: false,
      detailWin: false,
      selectedRowKeys: [],
    });

    this.fetch();
  },
  //详情弹窗
  showDtail(title, record, canEdit) {
    this.setState({
      canEdit: canEdit,
      detailWin: true,
      title: title,
      record: record
    });
  },
  rowKey(record) {
    return record.value;
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;

    this.fetch({
      pageSize: pagination.pageSize,
      current: pagination.current
    });
  },
  fetch(params = {
    pageSize: 10,
    current: 1
  }) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/manage/system/perm/find.htm',
      data: {
        node: 0
      },
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.total = 1;
        if (!pagination.current) {
          pagination.current = 1
        };
        var data = result.data;
        let root = [{
          label:'菜单目录',
          value:"0",
          isMenu:1,
          children:data
        }];
        this.setState({
          loading: false,
          data: root,
          pagination,
          selectedRowKeys: []
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
  }, 
  refreshList() {
    this.fetch();
  },

  showAddModal(title, canEdit) { 
    var me = this;
    var record = me.state.selectedRows[0];
    record.text = record.label;
    //console.log(record)
    if (title == '修改') {
      record.parentId=record.parentId+'';
      record.id=record.value;
      this.refs.AddMenu.setFieldsValue(record);
    } else if (title == '添加') {
      this.refs.AddMenu.setFieldsValue({
        parentId: record.value
      });
    }
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title
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
  collapseAll() {
    this.setState({
      expandedRowKeys: []
    });
  },
  onExpandedRowsChange(expandedRowKeys) {
    this.setState({
      expandedRowKeys: expandedRowKeys
    });
  },
  delete(title) {
    var me = this;
    var ids = me.state.selectedRowKeys;
    var status;
    var msg = "";
    var tips = "";
    var trueurl = "";
    if (title == "删除") {
      msg = '删除成功';
      status = '';
      tips = '是否缺认要删除这项内容';
      trueurl = "/modules/system/general/officeDelete.htm";
      confirm({
        title: tips,
        onOk: function() {
          Utils.ajaxData({
            url: trueurl,
            data: {
              ids: ids,
              status: status
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
  onRowClick(record) { 
    this.setState({
      selectedRowKeys: [record.value],
      selectedRows:[record]
    });
  },
  rowClassName(record, index){
    //console.log("record",record);
    //console.log("index",index);
    

  },
  render() {
    var me = this;
    var state = this.state;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {  
      selectedRowKeys
    };
    const hasSelected = selectedRowKeys.length > 0;
    let openEdit = true;
    if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
       openEdit =false;
    }
    var columns = [{
      title: '菜单名称',
      dataIndex: 'label'
    }, {
      title: '图标',
      dataIndex: 'iconCls'
    }, {
      title: '备注',
      dataIndex: "remark"
    }, {
      title: '排序',
      dataIndex: "sort"
    },{
        title: '是否是菜单',
        dataIndex: 'isMenu',
        render: function(value) {
          if(value==1)
            return "是";
          else return "否";
        }
      }];
    return (
      <div className="block-panel"> 
           <div className="actionBtns" style={{ marginBottom: 16 }}>
             <button className="ant-btn" onClick={this.expandedAll.bind(this,'展开所有',false)}>
                 展开所有
               </button>
               <button className="ant-btn" onClick={this.collapseAll.bind(this,'收缩所有',false)}>
                 收缩所有
               </button>
               <button className="ant-btn" onClick={this.refreshList.bind(this,'刷新',false)}>
                 刷新
               </button>
               <button className="ant-btn" disabled={openEdit} onClick={this.showAddModal.bind(this,'修改',true)}>
                 修改
               </button>
               <button className="ant-btn" disabled={!hasSelected} onClick={this.showAddModal.bind(this,'添加',true)}>
                 添加                   
               </button> 
             
           </div>   
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  ref="table" 
             rowSelection={rowSelection}
             onRowClick={this.onRowClick}
             dataSource={this.state.data} 
             expandedRowKeys={this.state.expandedRowKeys} 
             onExpandedRowsChange={this.onExpandedRowsChange}
             loading={this.state.loading} pagination={false} 
             scroll={{ x: true, y: 700}} />
             
            <AddMenu ref="AddMenu"  visible={state.visible}  officeData={this.state.data}  title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
         </div>
    );
  }
})
