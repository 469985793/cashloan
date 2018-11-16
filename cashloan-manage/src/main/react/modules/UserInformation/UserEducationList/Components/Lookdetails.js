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
      data: "",
      timeString: "",
      record: ""
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
      values.matriculationTime = parseInt(values.matriculationTime);
      values.graduationTime = parseInt(values.graduationTime);
      if(values.matriculationTime >= values.graduationTime){
        Modal.error({
          title: '选择的毕业年份或入学年份有误'
        })
        return;
      }
      Utils.ajaxData({
        url: '/modules/manage/user/updateEducation.htm',
        data: values,
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
        span: 15
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
      <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
        提 交
            </Button>
    ];
    let matriculationTime = [];
    let graduationTime = [];
    let data = new Date();
    let year = data.getFullYear();
    for (let i = 1980; i <　year+1; i++){
      matriculationTime.push(<Option key={i+'a'}>{i}</Option>);
      graduationTime.push(<Option key={i+'b'}>{i}</Option>);
    }
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} >
        <Form horizontal form={this.props.form}>
          <Input  {...getFieldProps('id') } type="hidden" />
          <Input  {...getFieldProps('userId') } type="hidden" />
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="学位:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('educationBackground', { rules: [{ required: true, message: '必填' }] }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="教育类别:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('educationType', { rules: [{ required: true, message: '必填' }] }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="就读学校:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('graduateSchool', { rules: [{ required: true, message: '必填' }] }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="教育情况:">
                <Select {...getFieldProps('graduationConclusion', { rules: [{ required: true, message: '必填'}] }) } >
                  <Option key={'毕业'}>毕业</Option>
                  <Option key={'结业'}>结业</Option>
                </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="入学时间:">
                <Select {...getFieldProps('matriculationTime', { rules: [{ required: true, message: '必填'}] }) } >
                  {matriculationTime}
                </Select>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="毕业时间:">
                <Select {...getFieldProps('graduationTime', { rules: [{ required: true, message: '必填'}] }) } >
                  {graduationTime}
                </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="状态:">
                <Select {...getFieldProps('state', { rules: [{ required: true, message: '必填'}] }) } >
                  <Option key={'10'}>匹配</Option>
                  <Option key={'20'}>不匹配</Option>
                </Select>
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