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
       data:"",
       timeString:"",
       record:"",
       index:'',
       index:-1,
       phoneItem: [],
       first: true,
       defaultValue:''
    };
  },
  handleCancel() {
    this.setState({
      first: true,
      phoneItem: [],
      index: -1,
      defaultValue: ''
    })
    this.props.form.resetFields();
    this.props.hideModal();
  },
  handleOk(){
    var me = this;
      this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      //console.log(me.props.dataRecord[me.state.index].cardId+','+me.props.dataRecord[me.state.index].userId);
     var tips = "您是否确定测试借款";
     confirm({
        title: tips,
        onOk: function() {
            Utils.ajaxData({
                url:  '/modules/manage/borrow/apply.htm',
                data: {
                  amount:values.amount,
                  cardId:me.props.dataRecord[me.state.index].cardId,
                  timeLimit:values.timeLimit,
                  userId:me.props.dataRecord[me.state.index].userId
                },
                method: 'post',
                callback: (result) => {
                    if(result.code==200){
                        Modal.success({
                            title: result.msg,
                        });     
                    }else{
                        Modal.error({
                            title:  result.msg,
                        });
                    }
                    me.handleCancel();
                }
            });
          },
        onCancel: function() {}
     });
      
      })
  },
  componentWillReceiveProps(nextProps) {
       this.setState({
         record:nextProps.record
       })
  }, 
  
   change(value){
    var me = this;
    for(var i = 0; i < me.props.dataRecord.length; i++){
        if(me.props.dataRecord[i].phone == value){
          me.state.index = i;
          me.state.defaultValue = value;
          me.forceUpdate();
        }
      }
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
         if(props.dataRecord && this.state.first){
        for(var i = 0; i < props.dataRecord.length; i++){
          this.state.phoneItem.push(<Option value={props.dataRecord[i].phone}>{props.dataRecord[i].phone}</Option>);
        }
        this.state.first = false;
        }
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="400" footer={modalBtns} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
          <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="手机号码:">
                  <Select style={{ width: 150 }} onChange={this.change} value={state.defaultValue}>
                    {this.state.phoneItem}
                  </Select>
               </FormItem>
            </Col>
            </Row>
            <Row>
            <Col span="12">
               <FormItem {...formItemLayout} label="卡号:">
                 <Input type="text" disabled={true} value={state.index != -1 ? props.dataRecord[state.index].cardNo : ''} />
              </FormItem>
            </Col>
            </Row>
            <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="借款天数:">
                  <Select style={{ width: 100 }} {...getFieldProps('timeLimit',{initialValue: ''})} >
                  <Option value='1'>1</Option>
                 <Option value='7'>7</Option>
                 <Option value='14'>14</Option>
             </Select>
               </FormItem>
            </Col>
            </Row>
            <Row>
            <Col span="12">
               <FormItem  {...formItemLayout} label="金额:">
                  <Select style={{ width: 100 }} {...getFieldProps('amount',{initialValue: ''})} >
                 <Option value='100'>100</Option>
                 <Option value='200'>200</Option>
                 <Option value='300'>300</Option>
                 <Option value='400'>400</Option>
                 <Option value='500'>500</Option>
                 <Option value='600'>600</Option>
                 <Option value='700'>700</Option>
                 <Option value='800'>800</Option>
                 <Option value='900'>900</Option>
                 <Option value='1000'>1000</Option>
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