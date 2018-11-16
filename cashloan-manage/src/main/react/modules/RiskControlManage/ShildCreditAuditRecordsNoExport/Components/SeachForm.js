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
    var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,reportId:params.reportId,borrowNo:params.borrowNo,rsState:params.rsState};
    if(params.registTime){
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
  handleOut() {
        var params = this.props.form.getFieldsValue();
        var json = JSON.stringify(params);
        window.open("/modules/manage/borrow/tongdun/export.htm?searchParams="+encodeURI(json));

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
        <FormItem label="真实姓名：">
          <Input  {...getFieldProps('realName', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="手机号码：">
          <Input  {...getFieldProps('phone', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="订单号：">
          <Input  {...getFieldProps('borrowNo', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="风险报告编码：">
          <Input  {...getFieldProps('reportId', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="提交审核报告时间：">
            <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('registTime', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="风险结果：">
        <Select style={{ width: 100 }} {...getFieldProps('rsState', { initialValue: '' }) }>
          <Option value=''>全部</Option>
          <Option value="建议通过">建议通过</Option>
          <Option value="建议审核">建议审核</Option>
          <Option value="建议拒绝">建议拒绝</Option>
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