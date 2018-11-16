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
var AddWin = React.createClass({
  getInitialState() {
    return {
       checked: true,
       disabled: false,
       data:"",
       timeString:"",
       record:""
    };
  },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal();
  },
  handleOk(){
      this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      //console.log("00000000000",values)
       Utils.ajaxData({
        url: "/test/api/borrow/apply.htm",
        data:values,
        callback: (result) => {
          if (result.code == 200) {
            this.handleCancel();
          };
          let resType = result.code == 200 ? 'success' : 'warning';
         Modal.success({
              title: "添加成功",
            });
        }
      });
      
      })
  },
  componentWillReceiveProps(nextProps) {
       this.setState({
         record:nextProps.record
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
        span:15
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
     var modalBtns = [
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading}  onClick={this.handleOk}>
                提 交
            </Button>
        ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
          <Input  {...getFieldProps('id') } type="hidden"   />
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="姓名:">
                 <Input  type="text" disabled={false} {...getFieldProps('realName') }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="手机号码:">
                <Input   type="text" disabled={false} {...getFieldProps('phone') }/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="银行卡号:">
                 <Input  type="text" disabled={false} {...getFieldProps('cardNo') }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem  {...formItemLayout} label="借款时间:">
                 <Input  type="text" disabled={false} {...getFieldProps('loanTime') }/>
               </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="打款时间:">
                <Input  type="text" disabled={false} {...getFieldProps('updateTime') }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="支付方式:">
                <Input  type="text" disabled={false} {...getFieldProps('typeStr') }/>
              </FormItem>
            </Col>
          </Row>
          <Row>  
           <Col span="12">
               <FormItem {...formItemLayout} label="金额:">
                <Input  type="text" disabled={false} {...getFieldProps('amount') }/>
              </FormItem>
            </Col> 
            <Col span="12">
               <FormItem {...formItemLayout} label="订单号:">
                <Input  type="text" disabled={false} {...getFieldProps('orderNo') }/>
              </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="资金来源:">
                <Input  type="text" disabled={false} {...getFieldProps('sourceStr') }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="审核:">
                        <Select type="text" {...getFieldProps('state1') } >
                              <Option value="20">审核通过</Option>
                              <Option value="30">审核不通过</Option>
                        </Select>
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