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
const RangePicker = DatePicker.RangePicker;

let SeachForm = React.createClass({
  getInitialState() {
        return {
            
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    var json = {endTime:'',startTime:'',phone:params.phone,state:params.state};
    if(params.registTime[0]){
      json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
      json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
    }
    this.props.passParams({
      searchParams : JSON.stringify(json),
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
  disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    return (
      <Form inline >
        <FormItem label="手机号码：">
          <Input type='text'  {...getFieldProps('phone', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="意见提交时间：">
          <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('registTime', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="状态：">
          <Select style={{ width: 100, textAlign: 'center' }} {...getFieldProps('state', { initialValue: '' }) } >
            <Option value=''>全部</Option>
            <Option value='10'>待确认</Option>
            <Option value='20'>已确认</Option>
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