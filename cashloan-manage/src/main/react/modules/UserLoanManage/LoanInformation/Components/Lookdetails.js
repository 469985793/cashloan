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
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('indentNo', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款人姓名:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('lastName', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="手机号码:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('mobile', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款期限(天):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('cycle', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="费率:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('feePercent', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="借款金额:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('balance', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="综合费用:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('totalFee', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="逾期费率:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('overduePercent', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="产品名称:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('name', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款利息:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('profit', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="实际到账金额(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('actualBalance', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('createdTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="放款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('arriveTime', { initialValue: '' }) } />
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
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('overdueFee', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="实际还款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('lastbackTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
    {/*<Row>
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
        </Row>*/}
        </Form>
      </Modal>
    );
  }
});
Lookdetails = createForm()(Lookdetails);
export default Lookdetails;