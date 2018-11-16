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
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading}  onClick={this.handleOk}>
                提 交
            </Button>
        ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="借款金额:">
                 <Input  type="text" {...getFieldProps('amount', { initialValue: '1000.00' ,rules: [{required:true,message:'必填'}] }) }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="利率:">
                <Input   type="text" {...getFieldProps('apr', { initialValue: '10' ,rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="银行卡号:">
                 <Input  type="text" {...getFieldProps('bankCard', { initialValue: '62258858667454',rules: [{required:true,message:'必填'}] }) }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem  {...formItemLayout} label="银行编码:">
                 <Input  type="text" {...getFieldProps('bankCode', { initialValue: '102',rules: [{required:true,message:'必填'}] }) }/>
               </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="标的标识:">
                <Input  type="text" {...getFieldProps('borrowNo', { initialValue: '',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="渠道名称:">
                <Input  type="text" {...getFieldProps('channelName', { initialValue: '51返呗',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>  
           <Col span="12">
               <FormItem {...formItemLayout} label="渠道标识:">
                <Input  type="text" {...getFieldProps('channelNo', { initialValue: '10',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col> 
            <Col span="12">
               <FormItem {...formItemLayout} label="商品名称:">
                <Input  type="text" {...getFieldProps('goodsName', { initialValue: 'Nokia',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="身份证号码:">
                <Input  type="text" {...getFieldProps('certNo', { initialValue: '411328189401015564',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="订单号:">
                <Input  type="text" {...getFieldProps('orderNo', { initialValue: '',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="期数:">
                <Input  type="text" {...getFieldProps('period', { initialValue: '1',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="手机号码:">
                <Input  type="text" {...getFieldProps('phone', { initialValue: '18064215865',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="真实姓名:">
                <Input  type="text" {...getFieldProps('realName', { initialValue: '张三',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="放款时收款方银行卡号:">
                <Input  type="text" {...getFieldProps('recvBankCard', { initialValue: '6225885822334455',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="放款时收款方户名:">
                <Input  type="text" {...getFieldProps('recvRealName', { initialValue: '李四',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="还款方式:">
                <Input  type="text" {...getFieldProps('repaymentType', { initialValue: '01',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
           <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="借款类型:">
                <Input  type="text" {...getFieldProps('borrowType', { initialValue: '01',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="用户名:">
                <Input  type="text" {...getFieldProps('userName', { initialValue: 'test1',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
          </Row>
          <Row>
	          <Col span="12">
	              <FormItem  {...formItemLayout} label="标的期限:">
	                  <Input {...getFieldProps('timeLimit', {  initialValue: '30',rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
	              </FormItem>
	          </Col>
	          <Col span="12">
	              <FormItem  {...formItemLayout} label="标的期限类型:">
	                  <Select {...getFieldProps('timeLimitType', {initialValue:"10", rules: [{ required: true, message: '必填'}] }) } >
	                      <Option value={"10"}>天</Option>
	                      <Option value={"20"}>月</Option>
	                      <Option value={"30"}>年</Option>
	                  </Select>             
	              </FormItem>
	          </Col>
	      </Row>
          <Row>   
           <Col span="12">
               <FormItem {...formItemLayout} label="用户标识:">
                <Input  type="text" {...getFieldProps('consumerNo', { initialValue: '',rules: [{required:true,message:'必填'}] }) }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="域名:">
                <Input  type="text" {...getFieldProps('url', { initialValue: 'http://arc.edushi.erongyun.net',rules: [{required:true,message:'必填'}] }) }/>
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