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
var confirm = Modal.confirm;
const Option = Select.Option;
const objectAssign = require('object-assign');
var AddWin = React.createClass({
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
  handleOk() {
    var me = this;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      //console.log(me.props.dataRecord[me.state.index].cardId+','+me.props.dataRecord[me.state.index].userId);
      Utils.ajaxData({
        url: '/modules/manage/mine/opinion/confirm.htm',
        data: {
          id: me.props.dataRecord.id,
          feedback: values.feedback
        },
        method: 'post',
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
  componentWillReceiveProps(nextProps) {
    this.setState({
      record: nextProps.record
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
        span: 4
      },
      wrapperCol: {
        span: 16
      },
    };
    var modalBtns = [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
      <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
        提 交
            </Button>
    ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="400" footer={modalBtns} maskClosable={false} >
        <Form horizontal form={this.props.form}>
          <Row>
            <Col span="24">
              <FormItem {...formItemLayout} label="反馈:">
                <Input type="textarea" disabled={false} {...getFieldProps('feedback', { initialValue: '', rules: [{ required: true, message: '不能为空且不能超过400',max: 400 }] }) } />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
});
AddWin = createForm()(AddWin);
export default AddWin;