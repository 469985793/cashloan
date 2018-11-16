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
            search: JSON.stringify(params),
            pageSize: 10,
            currentPage: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            pageSize: 10,
            currentPage: 1,
        });
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
             <FormItem label="订单号:">
                  <Input  {...getFieldProps('orderNo', {initialValue: ''})} />
             </FormItem>
             <FormItem label="姓名:">
                  <Input  {...getFieldProps('realName', {initialValue: ''})} />
             </FormItem>
             <FormItem label="手机号码:">
                  <Input  {...getFieldProps('phone', {initialValue: ''})} />
             </FormItem>
             <FormItem label="平台选择:">
                <Select style={{ width: 100 }} {...getFieldProps('channelId', {initialValue: ''})}>
                    <Option value="">平台选择</Option>
                    <Option value="10">淘宝</Option>
                    <Option value="20">京东</Option>
                    <Option value="30">苏宁易购</Option>
                    <Option value="15">国美</Option>
                    <Option value="40">蘑菇街</Option>
                </Select>
            </FormItem>
            <FormItem label="全部:">
                <Select style={{ width: 100 }} {...getFieldProps('approveType', {initialValue: ''})}>
                    <Option value="">全部</Option>
                    <Option value="10">系统操作</Option>
                    <Option value="20">人工操作</Option>
                </Select>
            </FormItem>
            <FormItem label="状态:">
                <Select style={{ width: 100 }} {...getFieldProps('state', {initialValue: ''})}>
                    <Option value="">全部</Option>
                    <Option value="20">待复审</Option>
                    <Option value="30">已通过</Option>
                    <Option value="40">已拒绝</Option>
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