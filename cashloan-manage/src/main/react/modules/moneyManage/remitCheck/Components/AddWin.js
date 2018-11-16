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

var confirm = Modal.confirm;
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
    var me =this;
      this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      var msg = '提交成功';
      var tips = '您是否确认提交?'
      confirm({
        title: tips,
        onOk:function(){
              Utils.ajaxData({
                url: "/modules/manage/pay/log/auditPay.htm",
                data:{
                    id: values.id,
                    state: values.state1,
                    remark:values.remark
                },
                method: 'post',
                callback: (result) => {
                  if (result.code == 200) {
                      me.handleCancel();
                      Modal.success({
                        title: msg,
                    });
                  }else{
                     Modal.error({
                            title:  result.msg,
                        });
                  }
                  
                }
            });
          },
        onCancel:function(){}
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
      <Modal style={{ left: '-100px'}} title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="姓名:">
                 <Input  type="text" disabled={true} {...getFieldProps('lastName') }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="手机号码:">
                <Input   type="text" disabled={true} {...getFieldProps('mobile') }/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="银行卡号:">
                 <Input  type="text" disabled={true} {...getFieldProps('mobile') }/>
               </FormItem>
            </Col>
            <Col span="12">
               <FormItem  {...formItemLayout} label="借款时间:">
                 <Input  type="text" disabled={true} {...getFieldProps('createdTime') }/>
               </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="支付流水:">
                <Input  type="text" disabled={false} {...getFieldProps('remark') }/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="支付方式:">
                <Input  type="text" disabled={true} value= "放款"/>
              </FormItem>
            </Col>
          </Row>
          <Row>  
           <Col span="12">
               <FormItem {...formItemLayout} label="金额:">
                <Input  type="text" disabled={true} {...getFieldProps('balance') }/>
              </FormItem>
            </Col> 
            <Col span="12">
               <FormItem {...formItemLayout} label="订单号:">
                <Input  type="text" disabled={true} {...getFieldProps('indentNo') }/>
              </FormItem>
            </Col>
          </Row>
          <Row>   
            <Col span="12">
               <FormItem {...formItemLayout} label="资金来源:">
                <Input  type="text" disabled={true} value="线下打款"/>
              </FormItem>
            </Col>
            <Col span="12">
               <FormItem {...formItemLayout} label="审核:">
                        <Select type="text" {...getFieldProps('state1') } >
                              <Option value="5">审核通过</Option>
                              <Option value="42">审核不通过</Option>
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