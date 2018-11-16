import React from 'react';
import {
  Button,
  Form,
  Input,
  Select,
  DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;

let SeachForm = React.createClass({
  getInitialState() {
        return {
            
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    if(params.realName){
      params.realName = params.realName.replace(/\s+/g, ""); 
    }
    this.props.passParams({
      searchParams : JSON.stringify(params),
      pageSize: 10,
      current: 1,
    });
  },
  handleReset() {
    this.props.form.resetFields();
    this.props.passParams({
      pageSize: 10,
      current: 1,
    });
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    return (
      <Form inline >
        <FormItem label="真实姓名：">
          <Input  {...getFieldProps('realName') } />
        </FormItem>
        <FormItem label="手机号码：">
          <Input  {...getFieldProps('phone') } />
        </FormItem>
        <FormItem label="银行卡状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('bankCardState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未认证</Option>
                        <Option value="20">认证中</Option>
                        <Option value="30">已认证</Option>
                    </Select>
        </FormItem>
        <FormItem label="紧急联系人状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('contactState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未完善</Option>
                        <Option value="20">完善中</Option>
                        <Option value="30">已完善</Option>
                    </Select>
        </FormItem>
        <FormItem label="身份证认证状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('idState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未认证</Option>
                        <Option value="20">认证中</Option>
                        <Option value="30">已认证</Option>
                    </Select>
        </FormItem>
        <FormItem label="手机运营商认证状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('phoneState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未认证</Option>
                        <Option value="20">认证中</Option>
                        <Option value="30">已认证</Option>
                    </Select>
        </FormItem>
        <FormItem label="芝麻授信状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('zhimaState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未授信</Option>
                        <Option value="20">授信中</Option>
                        <Option value="30">已授信</Option>
                    </Select>
        </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;