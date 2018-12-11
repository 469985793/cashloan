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
    var json = {afterTime:'',beforeTime:'',name:params.name};
    if(params.registTime[0]){
      json.beforeTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
      json.afterTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
    }
    this.props.passParams({
      searchParams : JSON.stringify(json),
      pageSize: 10,
      current: 1,
    });
  },
  handleReset() {
    var date = new Date();
    date = DateFormat.formatDate(date);
    this.props.form.resetFields();
    this.props.passParams({
      searchParams: JSON.stringify({beforeTime:date.substring(0,10),afterTime:date.substring(0,10)}),
      pageSize: 10,
      current: 1,
    });
  },
  disabledDate(startValue){
   var today = new Date();
   return startValue.getTime() > today.getTime();
 },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var date = new Date();
    return (
      <Form inline >
        <FormItem label="日期：">
            <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('registTime', {initialValue: ""}) } />
        </FormItem>
        <FormItem label="渠道名称：">
          <Input  {...getFieldProps('name') } />
        </FormItem>       
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;