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
            dataForm.realName = result.data.userbase.firstName + ' '+result.data.userbase.lastName;
            if(result.data.userbase.gender == 1){
            dataForm.sex = "Men";
            }else{
              dataForm.sex = "Women";
            }
            dataForm.idNo = result.data.userbase.idNo;
            dataForm.age = result.data.userbase.age;
            dataForm.cardNo = result.data.userbase.nationalId;
            dataForm.bank = result.data.userbase.bank;
            dataForm.bankPhone = result.data.userbase.bankPhone;
            dataForm.phone = result.data.userbase.mobile;
            dataForm.liveAddr = result.data.userbase.liveAddress;
            dataForm.registTime = result.data.userbase.createdTime;
            dataForm.registerAddr = result.data.userbase.registerAddr;
            dataForm.registerClient = result.data.userbase.registerClient;
            if(result.data.userbase.channelCode == 10000){
              dataForm.channelName = "android";
            }else{
            dataForm.channelName = "PC";
            }
            dataForm.score = result.data.userbase.score;
            dataForm.livingImg = result.data.userbase.iconImgCode;
            if (result.data.userbase.education1 == 1) {
              dataForm.education = "Primary School";
            } else if(result.data.userbase.education1 ==2){
              dataForm.education = "High School";
            }else if(result.data.userbase.education1 ==3){
              dataForm.education = "College";
            }else if (result.data.userbase.education1 ==4){
              dataForm.education = "Master";
            }else if(result.data.userbase.education1 ==5){
              dataForm.education = "Others";
            }else{
              dataForm.education = "";
            }
			if(result.data.userbase.jobStatus == 1){
              dataForm.jobStatus = "Full Time";
            }else if(result.data.userbase.jobStatus == 2){
              dataForm.jobStatus = "Contact";
            }else if(result.data.userbase.jobStatus == 3){
              dataForm.jobStatus = "Self Employed";
            }else if(result.data.userbase.jobStatus == 4){
              dataForm.jobStatus = "Part Time";
            }else if(result.data.userbase.jobStatus == 5){
              dataForm.jobStatus = "Student";
            }else if(result.data.userbase.jobStatus == 6){
              dataForm.jobStatus = "Retired";
            }else if(result.data.userbase.jobStatus == 7){
              dataForm.jobStatus = "Other";
            }else if(result.data.userbase.jobStatus == 8){
              dataForm.jobStatus = "Commission Earner";
            }else if(result.data.userbase.jobStatus == 9){
              dataForm.jobStatus = "Homemaker";
            }else if(result.data.userbase.jobStatus == 10){
              dataForm.jobStatus = "Unemployed";
            }else {
              dataForm.jobStatus = "--";
            }
            if(result.data.userbase.jobMonthIncome == 1){
              dataForm.jobMonthIncome = "Under ksh15000";
            }else if(result.data.userbase.jobMonthIncome == 2){
              dataForm.jobMonthIncome = "ksh15000-ksh25000";
            }else if(result.data.userbase.jobMonthIncome == 3){
              dataForm.jobMonthIncome = "ksh25001-ksh37500";
            }else if(result.data.userbase.jobMonthIncome == 4){
              dataForm.jobMonthIncome = "ksh37501-ksh52500";
            }else if(result.data.userbase.jobMonthIncome == 5){
              dataForm.jobMonthIncome = "ksh52501-ksh75000";
            }else if(result.data.userbase.jobMonthIncome == 6){
              dataForm.jobMonthIncome = "Above ksh75000";
            }else {
              dataForm.jobMonthIncome = "--";
            }
            if(result.data.userbase.marryStatus == 1){
              dataForm.marryStatus = "Marited";
            }else if(result.data.userbase.marryStatus == 2){
              dataForm.marryStatus = "Single";
            }else if(result.data.userbase.marryStatus == 3){
              dataForm.marryStatus = "Divorced";
            }else if(result.data.userbase.marryStatus == 4){
              dataForm.marryStatus = "Seperated";
            }else if(result.data.userbase.marryStatus == 5){
              dataForm.marryStatus = "Widowed";
            }else {
              dataForm.marryStatus = "--";
            }
			if(result.data.userbase.childrenNumber == 1){
              dataForm.childrenNumber = "1";
            }else if(result.data.userbase.childrenNumber == 2){
              dataForm.childrenNumber = "2";
            }else if(result.data.userbase.childrenNumber == 3){
              dataForm.childrenNumber = "3";
            }else if(result.data.userbase.childrenNumber == 4){
              dataForm.childrenNumber = "4";
            }else if(result.data.userbase.childrenNumber == 5){
              dataForm.childrenNumber = "5";
            }else if(result.data.userbase.childrenNumber == 6){
              dataForm.childrenNumber = "6";
            }else {
              dataForm.childrenNumber = "0";
            }
			dataForm.birthday = result.data.userbase.birthday;
			dataForm.liveAddress = result.data.userbase.liveAddress;
			dataForm.liveCity = result.data.userbase.liveCity;
			dataForm.rentYear = result.data.userbase.rentYear;
			dataForm.liveTime = result.data.userbase.liveTime;
            dataForm.companyName = result.data.userbase.companyName;
            dataForm.companyPhone = result.data.userbase.companyEmail;
            dataForm.companyAddr = result.data.userbase.officeAddress;
            dataForm.salary = result.data.userbase.salary;
            dataForm.workingYears = result.data.userbase.workingYears;
            dataForm.otherName = result.data.userbase.otherContactName;
            dataForm.otherPhone = result.data.userbase.otherContactMobile;
            if(result.data.userbase.otheContact == 1){
              dataForm.otherRelation = "farther";
            }else if(result.data.userbase.otheContact == 2){
              dataForm.otherRelation = "mother";
            }else if(result.data.userbase.otheContact == 3){
              dataForm.otherRelation = "borther";
            }else if(result.data.userbase.otheContact == 4){
              dataForm.otherRelation = "sister";
            }else if(result.data.userbase.otheContact == 5){
              dataForm.otherRelation = "husband";
            }else if(result.data.userbase.otheContact == 6){
              dataForm.otherRelation = "wife";
            }else if(result.data.userbase.otheContact == 7){
              dataForm.otherRelation = "son";
            }else if(result.data.userbase.otheContact == 8){
              dataForm.otherRelation = "daughter";
            }else {
              dataForm.otherRelation = "--";
            }
            if(result.data.userbase.familyMember == 1){
              dataForm.urgentRelation = "farther";
            }else if(result.data.userbase.familyMember == 2){
              dataForm.urgentRelation = "mother";
            }else if(result.data.userbase.familyMember == 3){
              dataForm.urgentRelation = "borther";
            }else if(result.data.userbase.familyMember == 4){
              dataForm.urgentRelation = "sister";
            }else if(result.data.userbase.familyMember == 5){
              dataForm.urgentRelation = "husband";
            }else if(result.data.userbase.familyMember == 6){
              dataForm.urgentRelation = "wife";
            }else if(result.data.userbase.familyMember == 7){
              dataForm.urgentRelation = "son";
            }else if(result.data.userbase.familyMember == 8){
              dataForm.urgentRelation = "daughter";
            }else {
              dataForm.urgentRelation = "--";
            }
            dataForm.urgentName = result.data.userbase.familyMemberName;
            dataForm.urgentPhone = result.data.userbase.familyMobilePhone;
            dataForm.taobao = result.data.userOtherInfo != null ? result.data.userOtherInfo.taobao : '';
            dataForm.email = result.data.userbase.email;
            dataForm.qq = result.data.userOtherInfo != null ? result.data.userOtherInfo.qq : '';
            dataForm.wechat = result.data.userOtherInfo != null ? result.data.userOtherInfo.wechat : '';
            dataForm.registerCoordinate = result.data.userbase.registerCoordinate;
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
      url: '/modules/manage/cl/cluser/list.htm',
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
      var state = "1";
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
      title: 'Phone',
      dataIndex: "mobile",
    }, {
      title: 'LastName',
      dataIndex: "lastName"
    }, {
      title: 'IDCard',
      dataIndex: 'nationalId'
    }, /*{
      title: '公司名称',
      dataIndex: 'companyName'
    }, */{
      title: 'Education',
      dataIndex: "education1",
      render: (text, record) => {
      if (text == 1) {
      return "Primary School"
    } else if(text ==2){
      return "High School"
    }else if(text ==3){
      return "College"
    }else if (text ==4){
      return "Master"
    }else if(text ==5){
      return "Others"
    }else{
      return ""
    }
  }
    }, {
      title: 'RegistrationTime',
      dataIndex: "createdTime",
    },/* {
      title: '注册渠道',
      dataIndex: 'registerClient',
    }, */{
	  title: 'RegistrationClient',
	  dataIndex: 'channelCode',
      render: (text, record) => {
      if (text == 10000) {
      return "android"
    } else {
      return "pc"
    }
  }
	}, /*{
      title: '月薪范围',
      dataIndex: 'salary',
    }, {
      title: '工作年限',
      dataIndex: 'workingYears',
    }, */{
      title: 'Status',
      dataIndex: 'status',
      render: (text, record) => {
        if (text == 10) {
          return "blacklist"//"黑名单"
        } else {
          return "normal"//"正常""
        }
      }
    }, {
      title: 'Operating',//操作
      render: (text, record) => {
        return <div>
          <a href="javascript:;" onClick={me.showModal.bind(me, '查看详情', record, true)}>View</a>
          <span className="ant-divider"></span>
          {record.status == "10" ? (
            <a href="javascript:;" onClick={me.changeStatus.bind(me, '解除黑名单', record)}>Lift blacklist</a>
          ) : (
              <a href="javascript:;" onClick={me.changeStatus.bind(me, '添加黑名单', record)}>Add blacklist</a>
            )}
          
          {/*<span className="ant-divider"></span>{record.education ? (
            <a href="javascript:;" onClick={me.seach.bind(me, '查询', record)}>学信查询</a>
          ) : (
              "-"
            )}*/}
        </div>
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
         // canEdit={state.canEdit}
          />
      </div>
    );
  }
})