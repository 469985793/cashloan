import React from 'react'
import {
  Table,
  Modal,
  Select
} from 'antd';
import AddWin from './AddWin'
var confirm = Modal.confirm;
const Option = Select.Option;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      PortListInit:[],
      pagination: {},
      canEdit: true,
      visible: false,
      value: ''
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      value: '',
      title: '',
      PortListInit:[],
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if (title == '编辑') {
      this.portChange(record.tppId,record.type,record.businessId);
      this.onTimeChange(record.getWay);
      this.portListInit(record.tppId);
      record.businessId = String(record.businessId);
      if(record.period != null && record.period != undefined && record.period != ''){
        record.period = String(record.period)
      }     
      this.refs.AddWin.setFieldsValue(record);
    } else {
      this.clearSelectedList();
    }
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record,
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
      url: '/modules/manage/creditdata/sceneBusiness/page.htm',
      data: params,
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
      selectedrecord: null
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
    var PortList = [];
    var ThirdPatryList = [];
    var InterfaceList = [];
    Utils.ajaxData({
      url: '/modules/manage/creditdata/tpp/listAll.htm',
      method: 'get',
      type: 'json',
      callback: (result) => {
        ThirdPatryList = result.data.map((item, index) => {
            return <Option key={item.name} value={item.id} >{item.name}</Option>
        })
        InterfaceList = result.data.map((item, index) => {
          var macthName = {};
            macthName[item.id] = item.businessList.map((item, index) => {
                return <Option key={item.name+item.id} value={String(item.id)} >{item.name}</Option>
            })
          
          return macthName
        })
        this.setState({
          ThirdPatryList: ThirdPatryList,
          InterfaceList: InterfaceList,
          PortList: PortList,
        })
      }
    });
  },

  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id],
      selectedrecord: record
    });
  },

  changeStaus(title, record) {
    var params = {
      id: record.id
    }
    var urlStr = title == "启用" ? "/modules/manage/creditdata/sceneBusiness/enable.htm" :　"/modules/manage/creditdata/sceneBusiness/disable.htm";
    Utils.ajaxData({
      url: urlStr,
      data: params,
      callback: (result) => {
        if (result.code == 200) {
          Modal.success({
            title: result.msg
          });
          this.hideModal();
        } else {
          Modal.error({
            title: result.msg,
          })
        }
      }
    })
  },

  portChange(value, value1,businessId) {
    var PortData = [];
    this.state.InterfaceList.forEach((item) => {
      for (var key in item) {
        if (key == value) {
          PortData = item[value]
        }
      }
    })
    this.setState({
      PortList: PortData,
      value: value1,
      value2: businessId,
    })
  },
  portListInit(value){
    var SceneListInit = [];
        Utils.ajaxData({
            url: '/modules/manage/rcdata/databusiness/listByDataId.htm',
            data: {
                statisticsId: value
            },
            callback: (result) => {
                var data = result.data;
                
                data.forEach(item => {
                    SceneListInit.push(<Option key={item.name} value={`${item.id}`}>{item.name}</Option>)
                })
                this.setState({
                  PortListInit: SceneListInit
                });
            }
        });
  },

  onTimeChange(value) {
    var timeState = false;
    if (value == "20") {
      this.setState({
        timeState: true
      })
    } else {
      this.setState({
        timeState: false
      })
    }
  },

  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys,

    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '场景名称',
      dataIndex: 'sceneName'
    }, {
      title: '关联接口',
      dataIndex: 'businessName'
    }, {
      title: '接口所属',
      dataIndex: 'tppName'
    }, {
      title: '数据获取方式',
      dataIndex: "getWayStr"
    }, {
      title: '间隔时间（天）',
      dataIndex: 'period'
    }, {
      title: '状态',
      dataIndex: "state",
      render: (text) => {
        return text == '10' ? '启用' : '禁用'
      }
    }, {
      title: '添加时间',
      dataIndex: "addTime",
    }, {
      title: '操作',
      dataIndex: "",
      render(text, record) {
        return <div ><a href="#" onClick={me.showModal.bind(me, '编辑', record, true)}>编辑 </a>&nbsp;
                       <span className="ant-divider"></span>&nbsp;
                       {record.state == "20" ? (
            <a href="#" onClick={me.changeStaus.bind(me, "启用", record)}>启用</a>
          ) : (
              <a href="#" onClick={me.changeStaus.bind(me, "禁用", record)}>禁用</a>)}
        </div>
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>

          <button className="ant-btn" onClick={this.showModal.bind(this, '新增')}>
            新增
               </button>


        </div>
        <Table columns={columns} rowKey={this.rowKey}
          onRowClick={this.onRowClick} params={this.props.params}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />

        <AddWin ref="AddWin" value={state.value} visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.selectedrecord}
          canEdit={state.canEdit} PortList={state.PortList} ThirdPatryList={state.ThirdPatryList} InterfaceList={state.InterfaceList}
          portChange={me.portChange} timeState={state.timeState} onTimeChange={me.onTimeChange} PortListInit={state.PortListInit} portListInit={me.portListInit}/>

      </div>
    );
  }
})