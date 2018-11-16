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
            searchParams: JSON.stringify({state:"10"}),
            pageSize: 10,
            current: 1,
        });
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
            <Input type="hidden" {...getFieldProps('state', {initialValue: '10'})} />
             <FormItem label="订单号:">
                  <Input  {...getFieldProps('orderNo', {initialValue: ''})} />
             </FormItem>
             <FormItem label="姓名:">
                  <Input  {...getFieldProps('realName', {initialValue: ''})} />
             </FormItem>
             <FormItem label="手机号码:">
                  <Input  {...getFieldProps('phone', {initialValue: ''})} />
             </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;