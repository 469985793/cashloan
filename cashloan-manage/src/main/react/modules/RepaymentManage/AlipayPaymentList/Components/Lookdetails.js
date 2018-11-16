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
         record:nextProps.record
       })
  }, 
  handleOk(){
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
       Utils.ajaxData({
        url: "/modules/manage/borrow/repay/confirmRepay.htm",
        data: {
          id: values.id,
          repayTime: DateFormat.formatDate(values.repayTimes),
        },
        callback: (result) => {
          if (result.code == 200) {
            Modal.success({
              title: result.msg,
              onOk: () => {
                  this.handleCancel();
              }
            });
          }else{
            Modal.error({
              title: result.msg,
            });
          }
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
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
          <Row>
            <Col span="12">
               <FormItem {...formItemLayout} label="还款时间:">
                <DatePicker {...getFieldProps('repayTimes', { initialValue: '',rules: [{required:true,message:'必填',type:'date'}] }) } />
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