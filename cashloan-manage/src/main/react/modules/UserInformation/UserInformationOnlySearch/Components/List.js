import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import ReviewWin from './ReviewWin';
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
      recordSoure: null,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record,
    }, () => {
      Utils.ajaxData({
        url: '/modules/manage/cl/cluser/detail.htm',
        data: {
          userId: record.id
        },
        callback: (result) => {
          if (result.code == 200) {
            var dataForm = {};
            dataForm.realName = result.data.userbase.realName;
            dataForm.sex = result.data.userbase.sex;
            dataForm.idNo = result.data.userbase.idNo;
            dataForm.age = result.data.userbase.age;
            dataForm.cardNo = result.data.userbase.cardNo;
            dataForm.bank = result.data.userbase.bank;
            dataForm.bankPhone = result.data.userbase.bankPhone;
            dataForm.phone = result.data.userbase.phone;
            dataForm.liveAddr = result.data.userbase.liveAddr;
            dataForm.registTime = result.data.userbase.registTime;
            dataForm.registerAddr = result.data.userbase.registerAddr;
            dataForm.registerClient = result.data.userbase.registerClient;
            dataForm.channelName = result.data.userbase.channelName;
            dataForm.score = result.data.userbase.score;
            dataForm.education = result.data.userbase.education;
            dataForm.companyName = result.data.userbase.companyName;
            dataForm.companyPhone = result.data.userbase.companyPhone;
            dataForm.salary = result.data.userbase.salary;
            dataForm.workingYears = result.data.userbase.workingYears;
            dataForm.companyAddr = result.data.userbase.companyAddr;
            dataForm.taobao = result.data.userOtherInfo != null ? result.data.userOtherInfo.taobao : '';
            dataForm.email = result.data.userOtherInfo != null ? result.data.userOtherInfo.email : '';
            dataForm.qq = result.data.userOtherInfo != null ? result.data.userOtherInfo.qq : '';
            dataForm.wechat = result.data.userOtherInfo != null ? result.data.userOtherInfo.wechat : '';
            dataForm.registerCoordinate = result.data.userbase.registerCoordinate;
            if (result.data.userContactInfo.length > 0) {
              if (result.data.userContactInfo[0].type == "10") {
                dataForm.urgentName = result.data.userContactInfo[0].name;
                dataForm.urgentPhone = result.data.userContactInfo[0].phone;
                dataForm.urgentRelation = result.data.userContactInfo[0].relation;
              } else {
                dataForm.otherName = result.data.userContactInfo[0].name;
                dataForm.otherPhone = result.data.userContactInfo[0].phone;
                dataForm.otherRelation = result.data.userContactInfo[0].relation;
              }
              if (result.data.userContactInfo[1].type == "20") {
                dataForm.otherName = result.data.userContactInfo[1].name;
                dataForm.otherPhone = result.data.userContactInfo[1].phone;
                dataForm.otherRelation = result.data.userContactInfo[1].relation;
              } else {
                dataForm.urgentName = result.data.userContactInfo[1].name;
                dataForm.urgentPhone = result.data.userContactInfo[1].phone;
                dataForm.urgentRelation = result.data.userContactInfo[1].relation;
              }
            }
            if (result.data.userAuth) {
              if (result.data.userAuth.bankCardState == 10) {
                dataForm.bankCardState = "未认证"
              } else if (result.data.userAuth.bankCardState == 20) {
                dataForm.bankCardState = "认证中"
              } else if (result.data.userAuth.bankCardState == 30) {
                dataForm.bankCardState = "已认证"
              }
              if (result.data.userAuth.idState == 10) {
                dataForm.idState = "未认证"
              } else if (result.data.userAuth.idState == 20) {
                dataForm.idState = "认证中"
              } else if (result.data.userAuth.idState == 30) {
                dataForm.idState = "已认证"
              }
              if (result.data.userAuth.phoneState == 10) {
                dataForm.phoneState = "未认证"
              } else if (result.data.userAuth.phoneState == 20) {
                dataForm.phoneState = "认证中"
              } else if (result.data.userAuth.phoneState == 30) {
                dataForm.phoneState = "已认证"
              }
              if (result.data.userAuth.zhimaState == 10) {
                dataForm.zhimaState = "未认证"
              } else if (result.data.userAuth.zhimaState == 20) {
                dataForm.zhimaState = "认证中"
              } else if (result.data.userAuth.zhimaState == 30) {
                dataForm.zhimaState = "已认证"
              }
            }
            //console.log(result.data);
            this.refs.ReviewWin.refs.Tab1.setFieldsValue(dataForm);
            this.setState({
              recordSoure: result.data
            })
          }
        }
      });
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
        current: 1,
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/cl/cluser/listOnlySearch.htm',
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
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
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
  changeStatus(title, record) {
    var me = this;
    var selectedRowKeys = me.state.selectedRowKeys;
    var id = record.id;
    var msg = "添加黑名单";
    var tips = "您是否确定加入黑名单";
    if (title == "添加黑名单") {
      var state = "10";
      var msg = title;
      var tips = "您是否确定加入黑名单";
    } else {
      var state = "20";
      var msg = title;
      var tips = "您是否确定解除黑名单";
    }
    confirm({
      title: tips,
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/user/updateState.htm",
          data: {
            id: id,
            state: state
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
            me.refreshList();
          }
        });
      },
      onCancel: function () { }
    });
  },
  componentDidMount() {
    this.fetch();
  },

  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id],
      selectedrecord: record
    });
  },

  SetAgent(record, title) {
    var me = this;
    if (title == "设置为一级代理商") {
      confirm({
        title: "是否设置为一级代理商",
        onOk: function () {
          Utils.ajaxData({
            url: "/modules/manage/profit/saveAgent.htm",
            data: {
              inviteId: record.inviteId,
              level: 1,
              userId: record.userId,
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
        onCancel: function () { }
      });
    } else if (title == "取消一级代理") {
      confirm({
        title: "是否取消一级代理",
        onOk: function () {
          Utils.ajaxData({
            url: "/modules/manage/profit/saveAgent.htm",
            data: {
              inviteId: record.inviteId,
              level: 3,
              userId: record.userId,
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
        onCancel: function () { }
      });
    }
  },
  seach(title, record) {
    let me = this;
    confirm({
      title: "是否确定查询",
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/user/educationRequest.htm",
          data: {
            idCard: record.idNo,
            name: record.loginName,
          },
          method: 'post',
          callback: (result) => {
            if (result.code == 200) {
              Modal.success({
                title: result.msg,
              });
            }else{
              Modal.error({
                title: result.msg,
              });
            }

            me.refreshList();
          }
        });
      },
      onCancel: function () { }
    });
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
      title: '手机号码',
      dataIndex: "mobile",
    }, {
      title: '真实姓名',
      dataIndex: "realName",
    }, {
      title: '身份证号码',
      dataIndex: 'nationalId'
    }, /*{
      title: '公司名称',
      dataIndex: 'companyName'
    }, */{
      title: '学历',
      dataIndex: "education1"
    }, {
      title: '注册时间',
      dataIndex: "registTime",
    }, {
      title: '注册客户端',
      dataIndex: 'registerClient',
    }, /*{
	  title: '注册渠道',
	  dataIndex: 'channelName',
	}, {
      title: '月薪范围',
      dataIndex: 'salary',
    }, {
      title: '工作年限',
      dataIndex: 'workingYears',
    }, */{
      title: '状态',
      dataIndex: 'state',
      render: (text, record) => {
        if (text == 10) {
          return "黑名单"
        } else {
          return "正常"
        }
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
        <Table columns={columns} rowKey={this.rowKey}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />
        <ReviewWin ref="ReviewWin" visible={state.visible} recordSoure={state.recordSoure} title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit} />       
      </div>
    );
  }
})