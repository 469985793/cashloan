import React from 'react';
import { Button, Form, Input, Modal ,Row, Col,Select,TreeSelect} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
var reqwest = require('reqwest');
var roleList = [];
reqwest({
  url: '/modules/manage/system/role/list.htm',
  method: 'get',
  type: 'json',
  success: (result) => {
    roleList = result.roles.map((item) => {
      return (<Option key={item.id} value={item.id}>{item.name}</Option>);
    });
  }
});
var UserWin = React.createClass({ 
  getInitialState() {
    return { visible: false };
  }, 
  handleCancel() { 
    this.props.hideAddModal();
  },
  handleOk() {
    this.props.form.validateFields((errors, values) => {
       if (!!errors) { 
         return;
       } 
       //console.log(values);
     }); 
  }, 
  render() {
    const { getFieldProps } = this.props.form; 

    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 15 },
    };
    var props = this.props;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
          提 交
        </button>
    ];
    if (this.props.title == '信息查看') {
      modalBtns = [
        <button key="button" className="ant-btn" onClick={this.handleCancel}>关闭</button>
      ]
    }
    return <Modal   title={props.title} visible={props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="600" footer={modalBtns} >
        <Form  form={this.props.form}>
        <Row>
          <Col span="12">
            <FormItem {...formItemLayout} label="工号：">
              <Input {...getFieldProps('number', { rules: [{required:true,message: '必填'}]})} type="text" autoComplete="off" disabled={!props.canEdit} />
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem {...formItemLayout} label="真实姓名：">
              <Input {...getFieldProps('name', { rules: [{required:true,message: '必填'}]})} type="text" autoComplete="off" disabled={!props.canEdit}/>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <FormItem {...formItemLayout} label="用户名称：">
              <Input {...getFieldProps('userName', { rules: [{required:true,message: '必填'}]})} type="text" autoComplete="off" disabled={!props.canEdit}/>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem {...formItemLayout} label="电子邮件：">
              <Input {...getFieldProps('email', {})} type="text" autoComplete="off" disabled={!props.canEdit} />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
             <FormItem  {...formItemLayout} label="用户角色："  >
               <Select disabled={!props.canEdit} style={{ width: '100%' }} multiple
                 {...getFieldProps('roleId', { rules: [{required:true,message: '必填',type: 'array'}]})}>
                  {roleList}
               </Select>
             </FormItem>
          </Col>
          <Col span="12">
            <FormItem  {...formItemLayout} label="所属部门：">
              <Input {...getFieldProps('officeName', {})} type="text" autoComplete="off" disabled={!props.canEdit}/>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
             <FormItem  {...formItemLayout} label="管辖机构"  >
                <Input {...getFieldProps('officeName', {})} type="text" autoComplete="off" disabled={!props.canEdit}/>
             </FormItem>
          </Col>
          <Col span="12">
             <FormItem  {...formItemLayout} label="职位："  >
               <Select disabled={!props.canEdit} style={{ width: '100%' }}
                 {...getFieldProps('position', { initialValue: ''})}>
                  <Option key="0" value={0}>普通职员</Option>
                  <Option key="1" value={1}>主管</Option>
                  <Option key="2" value={2}>部门经理</Option> 
               </Select>
             </FormItem>
          </Col>
          
        </Row>
        </Form>
      </Modal> 
  }
});
UserWin = createForm()(UserWin);
export default UserWin;