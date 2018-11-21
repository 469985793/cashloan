import React from 'react';
import {Button, Form, Input, Select,Message } from 'antd';
const createForm = Form.create;
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
        this.props.passParams({
            searchParams: JSON.stringify(params),
            pageSize: 10,
            current: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            searchParams: JSON.stringify({state:'2'}),
            pageSize: 10,
            current: 1,
        });
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
             <FormItem label="Indent No:">
                  <Input  {...getFieldProps('orderNo', {initialValue: ''})} />
             </FormItem>
             <FormItem label="Name:">
                  <Input  {...getFieldProps('realName', {initialValue: ''})} />
             </FormItem>
             <FormItem label="Phone:">
                  <Input  {...getFieldProps('phone', {initialValue: ''})} />
             </FormItem>
             <FormItem label="Order State:">
                 <Select style={{ width: 170 }} {...getFieldProps('state', { initialValue: '2' }) }>
                     <Option value="2">Pending</Option>
                     <Option value="3">Approved</Option>
                     <Option value="33">Rejected & Reapply Immediately</Option>
                     <Option value="32">Rejected & Reapply In 15 Days</Option>
                     <Option value="34">Rejected & Blacklist</Option>
                 </Select>
             </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>Search</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>Reset</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;