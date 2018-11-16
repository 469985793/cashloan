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
  handleOk(){
   
     let _me=this;
     let params = this.props.form.getFieldsValue();
     let record=this.state.record;
         this.props.form.validateFields((errors, values) => {      
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
      }
       Utils.ajaxData({
              url: '/modules/manage/borrow/verifyBorrow.htm',
              data:{borrowId:record.id,state:params.state,remark:params.remark},
              callback: (result) => {
                 if(result.code=="200"){
                    Modal.success({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }else{
                     Modal.error({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }
            }
          });
      
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
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>,
      <button key="sure" className="ant-btn" onClick={this.handleOk}>确定</button>
    ];
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
        span: 4
      },
      wrapperCol: {
        span: 20
      },
    };
 
 
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={props.title=="查看"?[modalBtnstwo]:[modalBtns]} maskClosable={false} >
        <ReviewRulesList ref="ReviewRulesList" record={props.record}/> 
         <Form horizontal  form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
           <Row>
            <Col span="24">
              <FormItem  {...formItemLayout} label="审批意见:">
              {props.title !="查看"?(
                <Select  {...getFieldProps('state',{initialValue:"221"})} disabled={!props.canEdit}>
                    <Option value="221">人工复审通过</Option>
                    <Option value="222">人工复审拒绝</Option>
                </Select>):(<Input type="text" disabled={!props.canEdit} {...getFieldProps('stateStr')}/>)}
              </FormItem>
            </Col>
          </Row>
         <Row>
            <Col span="24">
              <FormItem  {...formItemLayouttwo} label="备注说明:">
                 <Input disabled={!props.canEdit}  type="textarea" placeholder="" rows={4}   style={{width:"500px",height:"100px"}}   {...getFieldProps('remark', { initialValue: '' }) }/>
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