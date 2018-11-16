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
    var me = this;
    var re1 = /^(\d|[1-9]\d+)(\.\d+)?$/;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      if (!re1.test(values.amount)) {
        Modal.error({
          title: "请输入正确的金额",
        });
        return;
      }
      var url = this.props.title == '补扣' ? '/modules/manage/borrow/repayLog/deduction.htm' : '/modules/manage/borrow/repayLog/refund.htm';
      Utils.ajaxData({
        url: url,
        data: {
          id: me.props.record.id,
          amount: values.amount,
        },
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
    var modalBtns = [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
      <Button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
        提 交
            </Button>
    ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="400" footer={modalBtns} maskClosable={false} >
        <Form horizontal form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
          <Row>
            <Col span="15">
              <FormItem {...formItemLayout} label={props.title == "补扣" ? '补扣金额:' : '退还金额:'}>
                <Input type="text" {...getFieldProps('amount', { initialValue: '', rules: [{ required: true, message: '必填' }] }) } />
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