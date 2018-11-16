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
import Lookdetails from './Lookdetails';
import Tab1 from './Tab1';
import Tab2 from './Tab2';
import Tab3 from './Tab3';
import Tab5 from './Tab5';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const TabPane = Tabs.TabPane;
var confirm = Modal.confirm;
var Check = React.createClass({
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
    if (nextProps.visible) {
      this.setState({
        record: nextProps.record
      })
    }
  },
  onChange(time, timeString) {
    //console.log(time, timeString);
    this.setState({
      timeString: timeString,
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
      var tips = '是否确定提交';
      confirm({
        title: tips,
        onOk: function () {
          Utils.ajaxData({
            url: '/modules/manage/borrow/auditBorrowLoan.htm',
            data: { borrowId: record.id, state: params.state1, remark: params.remark },
            callback: (result) => {
              if (result.code == 200) {
                me.handleCancel();
              };
              let resType = result.code == 200 ? 'success' : 'error';
              Modal[resType]({
                title: result.msg,
              });
            }
          });
        },
        onCancel: function () { }
      })

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
            <RuleReport ref='RuleReport' record={this.props.record} visible={props.visible} />
          </TabPane>
          <TabPane tab="详细信息" key='2'>
            <Lookdetails dataRecord={props.dataRecord} canEdit={props.canEdit} record={this.props.record} visible={props.visible} />
          </TabPane>
          <TabPane tab="基本信息" key="3">
            <Tab1 record={props.record} dataForm={props.dataForm} ref="Tab1" canEdit={props.canEdit} visible={props.visible} recordSoure={props.recordSoure} />
          </TabPane>
          <TabPane tab="通讯录" key="4">
            <Tab2 record={props.record} ref="Tab2" canEdit={props.canEdit} visible={props.visible} />
          </TabPane>
          <TabPane tab="通话记录" key="5">
            <Tab3 record={props.record} ref="Tab3" canEdit={props.canEdit} visible={props.visible} />
          </TabPane>
          <TabPane tab="短信记录" key="6">
            <Tab5 record={props.record} ref="Tab5" canEdit={props.canEdit} visible={props.visible} />
          </TabPane>
        </Tabs>
       {props.title == '审核' ?  <Form horizontal form={this.props.form}>
          <Row>
            <Col span="24">
              <FormItem  {...formItemLayout} label="放款审核:">
                  <Select style={{width:'200px'}}  {...getFieldProps('state1', { initialValue: "" }) }>
                    <Option value="302">审核通过</Option>
                    <Option value="303">审核不通过</Option>
                  </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="24">
              <FormItem  {...formItemLayout} label="审核备注:">
                <Input type="textarea" placeholder="" rows={4} style={{ width: "500px", height: "40px" }}   {...getFieldProps('remark', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
        </Form> : <Form horizontal form={this.props.form}><Row></Row></Form>}
      </Modal>
    );
  }
});
Check = createForm()(Check);
export default Check;