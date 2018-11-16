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
  Icon
  
} from 'antd';
import ReviewRulesList from "./ReviewRulesList";

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
       record:"",
       show: false
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
  show(){
    this.setState({
      show: !this.state.show
    })
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
   var modalBtnstwo= [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
    ];
    const formItemLayout = {
      labelCol: {
        span: 4
      },
      wrapperCol: {
        span: 6
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 16
      },
    };
 
 
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtnstwo} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
           <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="真实姓名:">
                {state.record.realName}
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="手机号码:">
                {state.record.phone}
              </FormItem>
            </Col>
         </Row>
         <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="借款订单号:">
                {state.record.borrowNo}
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="借款金额:">
                {state.record.amount}
              </FormItem>
            </Col>
          </Row>
         <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="风险报告编码:">
                {state.record.reportId}
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="申请状态:">
                {state.record.stateStr}
              </FormItem>
            </Col>
          </Row>
         <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="提交审核报告结果编码:">
                {state.record.submitCode}
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="提交审核报告时间:">
                {state.record.createTime}
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="提交审核返回信息:">
                <div style={{width:'600px',wordWrap:'break-word',wordBreak:'break-all' }}>{state.record.submitParams}</div>
              </FormItem>
            </Col>
         </Row>
         <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="风险积分:">
                {state.record.rsScore}
              </FormItem>
            </Col>
         
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="风险结果:">
                {state.record.rsState}
              </FormItem>
            </Col>
          </Row>
         <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="查询审核报告时间:">
                {state.record.updateTime}
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="查询审核报告结果编码:">
                {state.record.queryCode}
              </FormItem>
            </Col>
         </Row>
         <Row>
            <Col span="12">
              <FormItem  {...formItemLayouttwo} label="查询审核报告信息:" >
                {state.record.queryParams?(state.show? <a href="#" onClick={this.show}><Icon type="minus-square" /></a> : <a href="#" onClick={this.show}><Icon type="plus-square" /></a>):''}
                <div style={{width:'600px',wordWrap:'break-word',wordBreak:'break-all' }}>{state.record.queryParams? (state.show ? state.record.queryParams :state.record.queryParams.substring(0,250)+'...') :""}</div>
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