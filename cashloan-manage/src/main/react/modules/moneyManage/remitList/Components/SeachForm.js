import React from 'react';
import {Button, Form, Input, Select,Message,DatePicker } from 'antd';
const createForm = Form.create;
const RangePicker = DatePicker.RangePicker;
const FormItem = Form.Item;
const Option = Select.Option;

let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
     handleQuery() {
        var params = this.props.form.getFieldsValue();
         var json = {startTime:'',endTime:'',state:params.state,scenes:params.scenes,realName:params.realName,phone:params.phone};
     //console.log(params);
     if(params.time){
        json.endTime=(DateFormat.formatDate(params.time[1])).substring(0,10);
        json.startTime=(DateFormat.formatDate(params.time[0])).substring(0,10);
      }
        this.props.passParams({
            search: JSON.stringify(json),
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
        window.open("/modules/manage/payLog/export.htm?searchParams="+ encodeURI(json));

    },
    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
             <FormItem label="打款时间:">
                  <RangePicker disabledDate={this.disabledDate}  {...getFieldProps('time', { initialValue: '' }) } />
             </FormItem>
             <FormItem label="收款人姓名:">
                  <Input  {...getFieldProps('realName',{initialValue: ''})} />
             </FormItem>
             <FormItem label="手机号码:">
                  <Input  {...getFieldProps('phone',{initialValue: ''})} />
             </FormItem>
             <FormItem label="状态:">
             <Select style={{ width: 100 }} {...getFieldProps('state',{initialValue: ''})} placeholder='请选择...'>
                 <Option value="">全部</Option>
                 <Option value="10">待支付</Option>
                 <Option value="40">支付成功</Option>
                 <Option value="50">支付失败</Option>
                 <Option value="20">审核通过</Option>
                 <Option value="30">审核不通过</Option>
                 <Option value="15">待审核</Option>
             </Select>
             </FormItem>
            <FormItem label="业务:">
                <Select style={{ width: 100 }} {...getFieldProps('scenes',{initialValue: ''})} placeholder='请选择...'>
                    <Option value="">全部</Option>
                    <Option value="10">放款</Option>
                    <Option value="11">奖励</Option>
                    <Option value="12">退还</Option>
                    <Option value="20">还款</Option>
                    <Option value="21">补扣</Option>
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