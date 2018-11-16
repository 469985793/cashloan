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
const RangePicker = DatePicker.RangePicker;
const Option = Select.Option;

let SeachForm = React.createClass({
  getInitialState() {
        return {
            roleList: []
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    var json = {afterTime:'',beforeTime:''};
     //console.log(params);
     if(params.time[0]){
        json.afterTime=(DateFormat.formatDate(params.time[1])).substring(0,10);
        json.beforeTime=(DateFormat.formatDate(params.time[0])).substring(0,10);
        //console.log(d);
      }
    this.props.passParams({
      searchParams : JSON.stringify(json),
    });
  },
  handleReset() {
    this.props.form.resetFields();
    this.props.passParams({});
  },
 disabledDate(startValue){
   var today = new Date();
   return startValue.getTime() > today.getTime();
 },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    return (
      <Form inline >
        <FormItem label="日期:">
          <RangePicker disabledDate={this.disabledDate} {...getFieldProps('time', { initialValue: '' }) } />
        </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;