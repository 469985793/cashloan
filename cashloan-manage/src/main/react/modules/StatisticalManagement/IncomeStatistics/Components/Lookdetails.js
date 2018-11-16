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
       data:"",
       timeString:"",
       record:""
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
  
  onChange(time, timeString){
   
    //console.log(time, timeString);
    this.setState({
      timeString:timeString,
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
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={[<button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>]} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
          <Row>
            <Col span="12">
               <FormItem {...formItemLayout} label="商品名称:">
                {/*<Input disabled={!props.canEdit}  type="text" {...getFieldProps('goodsName', { initialValue: '' }) }/>*/}
                <p style={{width:"500px"}}>{this.state.record.goodsName}</p>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="订单号:">
                 <Input disabled={!props.canEdit}  type="text" {...getFieldProps('orderNo', { initialValue: '' }) }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="用户名:">
                <Input disabled={!props.canEdit}  type="text" {...getFieldProps('userName', { initialValue: '' }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="手机号码:">
                 <Input disabled={!props.canEdit}  type="text" {...getFieldProps('phone', { initialValue: '' }) }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem  {...formItemLayout} label="分期金额(元):">
                 <Input disabled={!props.canEdit}  type="text" {...getFieldProps('amount', { initialValue: '' }) }/>
               </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="利率(%):">
                <Input disabled={!props.canEdit}  type="text" {...getFieldProps('apr', { initialValue: '' }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem  {...formItemLayout} label="期数:">
                 <Input disabled={!props.canEdit}  type="text" {...getFieldProps('period', { initialValue: '' }) }/>
               </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
               <FormItem {...formItemLayout} label="还款方式:">
                <Input disabled={!props.canEdit}  type="text" {...getFieldProps('repaymentTypeStr', { initialValue: '' }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem  {...formItemLayout} label="渠道:">
                 <Input disabled={!props.canEdit}  type="text" {...getFieldProps('channelName', { initialValue: '' }) }/>
               </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
               <FormItem {...formItemLayout} label="当前状态:">
                <Input disabled={!props.canEdit}  type="text" {...getFieldProps('stateStr', { initialValue: '' }) }/>
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