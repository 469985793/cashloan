import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Row,
  Col,
  Select,
  Tabs,
} from 'antd';

import RuleReport from './RuleReport';
import Tab1 from './Tab1';
import Tab2 from './Tab2';
import Tab3 from './Tab3';
import Tab4 from './Tab4';
import Tab5 from './Tab5';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const TabPane = Tabs.TabPane;
var confirm = Modal.confirm;
var Lookdetails = React.createClass({
  getInitialState() {
    return {
      checked: true,
      disabled: false,
      data: "",
      timeString: "",
      record: "",
    };
  },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal();
  },
  componentWillReceiveProps(nextProps) {
    this.setState({
      record: nextProps.record
    })
  },
  handleOk() {

    let me = this;
    let params = this.props.form.getFieldsValue();
    let record = this.state.record;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      var tips = "是否确定提交";
      confirm({
        title: tips,
        onOk: function () {
          Utils.ajaxData({
            url: '/modules/manage/borrow/verifyBorrow.htm',
            data: { borrowId: record.id, state: params.state, remark: params.remark },
            callback: (result) => {
              if (result.code == 200) {
                me.handleCancel();
              };
              let resType = result.code == 200 ? 'success' : 'warning';
              Modal[resType]({
                title: result.msg,
              });

            }
          });
        },
        onCancel: function(){}
      })


    })

  },
  onChange(time, timeString) {
    //console.log(time, timeString);
    this.setState({
      timeString: timeString,
    })
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>,
      <button key="sure" className="ant-btn ant-btn-primary" onClick={this.handleOk}>确定</button>
    ];
    var modalBtnstwo = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
    ];
    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 12
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 4
      },
      wrapperCol: {
        span: 20
      },
    };

    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="1200" footer={props.title == "查看" ? [modalBtnstwo] : [modalBtns]} maskClosable={false} >

        <Tabs defaultActiveKey="1"  >
          <TabPane tab="规则报告" key='1'>
            <RuleReport record={this.props.record} visible={props.visible} />
          </TabPane>
          <TabPane tab="基本信息" key="2">
            <Tab1 record={props.record} dataForm={props.dataForm} ref="Tab1" canEdit={props.canEdit} visible={props.visible} recordSoure={props.recordSoure} />
          </TabPane>
          <TabPane tab="通讯录" key="3">
            <Tab2 record={props.record} ref="Tab2" canEdit={props.canEdit} visible={props.visible} />
          </TabPane>
          <TabPane tab="通话记录" key="4">
            <Tab3 record={props.record} ref="Tab3" canEdit={props.canEdit} visible={props.visible} />
          </TabPane>
         { /*<TabPane tab="运营商通话记录" key="5">
            <Tab4 record={props.record} ref="Tab4" canEdit={props.canEdit} visible={props.visible} />
          </TabPane>*/}
          <TabPane tab="短信记录" key="6">
            <Tab5 record={props.record} ref="Tab5" canEdit={props.canEdit} visible={props.visible} />
          </TabPane>
        </Tabs>
      </Modal>
    );
  }
});
Lookdetails = createForm()(Lookdetails);
export default Lookdetails;