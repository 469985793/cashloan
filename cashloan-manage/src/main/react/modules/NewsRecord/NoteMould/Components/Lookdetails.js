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
  handleOk(title){
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      if (this.props.title == '编辑'){
           var url = "/modules/manage/smstpl/update.htm";
           var params = {};
           var params={
                id: this.props.record.id,
                number: values.number,
                state:values.state,
                tpl: values.tpl,
                type: values.type,
                typeName: values.typeName
            };
      }else{
           var url = "/modules/manage/smstpl/save.htm";
           var params = {};
           var params={
                number: values.number,
                tpl: values.tpl,
                type: values.type,
                typeName: values.typeName
           };
      }
       Utils.ajaxData({
        url: url,
        data: params,
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
        span: 8
      },
      wrapperCol: {
        span: 12
      },
    };
    var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
                提 交
            </Button>
        ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
         <Input type='hidden' {...getFieldProps('id', { initialValue: '' }) }    />
         <Row>
            <Col span="24">
               <FormItem {...formItemLayout} label="模板编号:">
                <Input disabled={!props.canEdit} {...getFieldProps('number', { initialValue: '',rules: [{required:true,max: 64,message:'必填，不得超过64个字符'}] }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="24">
               <FormItem {...formItemLayout} label="模板类型:">
                  <Select  {...getFieldProps('state')}>
                    <Option  value={"10"}>启用</Option>
                    <Option  value={"20"}>禁用</Option>
                  </Select>
                </FormItem>
            </Col>
          </Row> 
          <Row>
            <Col span="24">
               <FormItem {...formItemLayout} label="类型名称:">
                <Input disabled={!props.canEdit} {...getFieldProps('typeName', { initialValue: '',rules: [{required:true,message:'必填'}] }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="24">
               <FormItem {...formItemLayout} label="短信类型:">
                <Input disabled={!props.canEdit} {...getFieldProps('type', { initialValue: '',rules: [{required:true,message:'必填'}] }) } />
              </FormItem>
            </Col>
           </Row>
           <Row>
            <Col span="24">
               <FormItem {...formItemLayout} label="短信模板:">
                <Input disabled={!props.canEdit} type="textarea" rows={4} {...getFieldProps('tpl', { initialValue: '',rules: [{required:true,message:'必填',}] }) } />
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