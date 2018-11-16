import React from 'react';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  Row,
  Col,
  Select,
  Checkbox,
  Radio,
  message,
  DatePicker,

} from 'antd';

const CheckboxGroup = Checkbox.Group
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Lookdetails = React.createClass({
  getInitialState() {
    return {
      checked: true,
      disabled: false,
      data: "",
      timeString: "",
      record: ""
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


    const formItemLayout = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayoutone = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 6
      },
      wrapperCol: {
        span: 18
      },
    };
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={[<button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>]} maskClosable={false} >
        <Form horizontal form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="用户id:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('userId', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="新增时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('createTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="用户意见:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('feedback', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="系统用户:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('sys_user', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="审核时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('confirmTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="备注:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('remark', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="确认状态:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('state', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
});
Lookdetails = createForm()(Lookdetails);
export default Lookdetails;