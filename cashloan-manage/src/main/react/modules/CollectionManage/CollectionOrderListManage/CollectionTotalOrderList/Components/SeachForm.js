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
  handleOut() {
        var params = this.props.form.getFieldsValue();
        var json = JSON.stringify(params);
        window.open("/modules/manage/urgeRepayOrder/export.htm?searchParams="+encodeURI(json));

    },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    return (
      <Form inline >
        <FormItem label="借款人姓名：">
          <Input  {...getFieldProps('borrowName') } />
        </FormItem>
        <FormItem label="订单号：">
          <Input  {...getFieldProps('orderNo') } />
        </FormItem>
        <FormItem label="手机号码：">
          <Input  {...getFieldProps('phone') } />
        </FormItem>
        <FormItem label="催收状态:">
          <Select style={{ width: 100 }} {...getFieldProps('state',{initialValue: ''})} placeholder='请选择...'>
              <Option value="">全部</Option>
              <Option value="20">催收中</Option>
              <Option value="30">承诺还款</Option>
              <Option value="40">催收成功</Option>
              <Option value="50">坏账</Option>
          </Select>
        </FormItem>
        <FormItem label="逾期等级:">
          <Select style={{ width: 100 }} {...getFieldProps('level',{initialValue: ''})} placeholder='请选择...'>
              <Option value="">全部</Option>
              <Option value="M1">M1</Option>
              <Option value="M2">M2</Option>
              <Option value="M3">M3</Option>
          </Select>
        </FormItem>
        <FormItem label="分配状态:">
          <Select style={{ width: 100 }} {...getFieldProps('isDestribute',{initialValue: ''})} placeholder='请选择...'>
              <Option value="">全部</Option>
              <Option value="10">已分配</Option>
              <Option value="20">未分配</Option>
          </Select>
        </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
        <FormItem><Button onClick={this.handleOut}>导出</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;