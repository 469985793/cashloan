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
              <FormItem  {...formItemLayout} label="订单号:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('orderNo', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款人姓名:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('realName', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="手机号码:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('phone', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款期限(天):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('timeLimit', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款所在地:">
                <Input style={{ width: '600px'}} disabled={!props.canEdit} type="text" {...getFieldProps('address', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款所在经纬度:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('coordinate', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="借款金额(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('amount', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="综合费用(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('fee', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="服务费(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('serviceFee', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="信息认证费(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('infoAuthFee', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款利息(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('interest', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="实际到账金额(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('realAmount', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('createTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="放款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('loanTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="逾期天数(天):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('penaltyDay', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="逾期金额(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('penaltyAmout', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="实际还款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('repayTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
          <Col span="12">
            <FormItem {...formItemLayout} label="应还总额(元):">
              <Input disabled={!props.canEdit} type="text" {...getFieldProps('repayTotal', { initialValue: '' }) } />
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem {...formItemLayout} label="已还总额(元):">
              <Input disabled={!props.canEdit} type="text" {...getFieldProps('repayYesTotal', { initialValue: '' }) } />
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