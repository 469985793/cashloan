//主表
import React from 'react'
import {
  Table,
  Modal,
  Tabs,
} from 'antd';
import AddWin from './AddWin';
import Tab1 from './Tab1';
import './panelCss.css';
var confirm = Modal.confirm;
const TabPane = Tabs.TabPane;
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
      condition: []
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    var keyPanes = this.state.keyPanes?this.state.keyPanes:"";
    var params = nextProps.params;
    var btnParams = nextProps.btnParams?nextProps.btnParams:{};
    if(params.searchParams){
      var searchValue = JSON.parse(params.searchParams);
      searchValue.type = keyPanes;
      params.searchParams = JSON.stringify(searchValue)
    }else{
      params.searchParams = JSON.stringify({type: keyPanes })
    }
    if(btnParams.addBtn == true){
      this.showModal('新增', null, true)
    }
    if(btnParams.refreshBtn == true){
      this.refresh( '刷新', null, true)
    }
    this.fetch(params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    this.handleRefreshList();
    var pagination = this.state.pagination;
    this.props.btnParamsFun({
      addBtn:false,
      refreshBtn:false,
    });

  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;

    if (title == '修改') {
      var record = record;
      record.type = String(record.type);
      this.refs.AddWin.setFieldsValue(record);
    } else if (title == '新增') {
      record = null
    }
    Utils.ajaxData({
                url: '/modules/manage/system/config/list.htm',
                data: {
                    pageSize: 15,
                    current: 1
                },
                callback: (result) => {
                    this.setState({
                      canEdit: canEdit,
                      title: title,
                      record: record,
                      visible: true,
                      condition: result.dicData
                    });
                }
    });
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
    this.handleRefreshList();
  },
  handleRefreshList() {
    var pagination = this.state.pagination;
    var keyPanes = this.state.keyPanes;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      searchParams: JSON.stringify({type: keyPanes }),
    });
    this.fetch(params);
  },
  componentDidMount() {
    var params = this.props.params;
    var paramsCode ={
           code:"SYSTEM_TYPE"
            }
    Utils.ajaxData({
      url: '/modules/manage/system/dict/listByTypeCode.htm',
      data: paramsCode,
      callback: (result) => {
        params.searchParams = JSON.stringify({type: result.data[0].itemCode }),
        this.fetch(params);
        this.setState({
          tabData:result.data,
          keyPanes:result.data[0].itemCode,
        });
      }
    });
    
  },
  fetch(params = { pageSize: 10, current: 1 }) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/manage/system/config/list.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination,
        });
        this.clearList();
      }
    });
  },
  clearList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  changeStatus(title,record) {
    var me = this;
    var data ={};
    if(title == "禁用"){
      data = {
        id: record.id,
        status: 0
      }
    }else{
      data = {
        id: record.id,
        status: 1
      }
    }
    confirm({
      title: '是否确认',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/system/config/delete.htm",
          data: data,
          method: 'post',
          callback: (result) => {
            Modal.success({
              title: result.msg,
            });
            me.handleRefreshList();
          }
        });
      },
      onCancel: function () { }
    });
  },
  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id]
    });
  },
  refresh(){
    var me = this;
    confirm({
      title: '是否确认刷新',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/system/config/reload.htm",
          method: 'post',
          callback: (result) => {
            Modal.success({
              title: result.msg,
              onOk: () => {
                    me.hideModal();
                  }
            });
            me.handleRefreshList();
          }
        });
      },
      onCancel: function () { 
        me.hideModal();
      }
    });
  },
  changePanes(key){
    var params = objectAssign({}, this.props.params, {
            current: 1,
            pageSize: 10,
            searchParams: JSON.stringify({type: key }),
        });
    Utils.ajaxData({
      url: '/modules/manage/system/config/list.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          keyPanes:key,
          pagination,
        });
        //this.clearList();
      }
    });
  },
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      // type: 'radio',
      selectedRowKeys,
      //onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var state = this.state;
    let subTabs = [];
    let tabData = this.state.tabData?this.state.tabData:[];
    tabData.map((item)=>{
      let TabPanes = (
          <TabPane tab={item.itemValue} key={item.itemCode} >
            <Tab1 record={state.data} keyPanes={state.keyPanes} 
                  pagination={this.state.pagination} handleTableChange={this.handleTableChange}
                  changeStatus={this.changeStatus} showModal={this.showModal}/>
          </TabPane>)
          subTabs.push(TabPanes);
    })
    return (
      <div className="block-panel">
        <Tabs onChange={this.changePanes} className="contrationPanel" type="card">{subTabs}</Tabs>
        <AddWin ref="AddWin"  visible={state.visible} condition={state.condition}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>
      </div>
    );
  }
})
