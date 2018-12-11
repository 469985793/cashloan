import React from 'react';
import { Button, Form, Input, Select, Message, DatePicker } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RangePicker = DatePicker.RangePicker;

let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        params.beforeTime=params.registTime[0];
        params.afterTime=params.registTime[1];
        console.log(params);
        this.props.passParams({
            searchParams: JSON.stringify(params),
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
        console.log(params);
        params.beforeTime=params.registTime[0];
        params.afterTime=params.registTime[1]; 
        var json = JSON.stringify(params);
        // console.log(json);
        window.open("/modules/manage/borrow/export.htm?searchParams=" + encodeURI(json));

    },
    disabledDate(startValue){
        var today = new Date();
        return startValue.getTime() > today.getTime();
        // return current && current < moment().endOf('day');
    },
    render() {

        const { getFieldProps } = this.props.form;
        var date = new Date();

        return (
            <Form inline>
                <FormItem label="Actual Name:">
                    <Input  {...getFieldProps('lastName', { initialValue: '' })} />
                </FormItem>
                <FormItem label="Phone:">
                    <Input  {...getFieldProps('mobile', { initialValue: '' })} />
                </FormItem>
                <FormItem label="Order Number:">
                    <Input  {...getFieldProps('indentNo', { initialValue: '' })} />
                </FormItem>
                <FormItem label="Date：">
                    <RangePicker disabledDate={this.disabledDate} style={{ width: "310" }} {...getFieldProps('registTime', { initialValue: [date,date] })} />
                </FormItem>
                <FormItem label="Order Status:">
                    <Select style={{ width: 350 }} {...getFieldProps('status', { initialValue: '' })}>
                        <Option value="">All</Option>
                        <Option value="1">In the application, pending risk control review</Option>
                        <Option value="2">Wind control audit passed, pending review</Option>
                        <Option value="3">Review and approval, pending payment</Option>
                        <Option value="4">In the lending</Option>
                        <Option value="5">Loaned, pending payment</Option>
                        <Option value="6">Normal reimbursement</Option>
                        <Option value="21">Overdue</Option>
                        <Option value="22">Overdue payment</Option>
                        <Option value="31">Risk control audit did not pass</Option>
                        <Option value="32">Review not approved,Reapply In 15 Days</Option>
                        <Option value="33">Review not approved,Reapply Immediately</Option>
                        <Option value="34">Review not approved,Blacklist</Option>
                        <Option value="41">Loan failure</Option>
                        <Option value="42">Lending was rejected</Option>
                        <Option value="51">Bad debt</Option>
                    </Select>
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>Search</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>Reset</Button></FormItem>
                <FormItem><Button onClick={this.handleOut}>Export</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;