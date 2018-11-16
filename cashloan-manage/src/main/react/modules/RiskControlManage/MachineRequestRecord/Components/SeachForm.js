import React from 'react';
import { Button, Form, Input, Select, Message, DatePicker } from 'antd';
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
        var json = { startTime: '', endTime: '', realName: params.realName, phone: params.phone, orderNo: params.orderNo, state: params.state };
        if (params.time[0]) {
            json.endTime=(DateFormat.formatDate(params.time[1])).substring(0,10);
            json.startTime=(DateFormat.formatDate(params.time[0])).substring(0,10);
        }
        this.props.passParams({
            searchParams: JSON.stringify(json),
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

        const { getFieldProps } = this.props.form;

        return (
            <Form inline>
                <FormItem label="订单号:">
                    <Input  {...getFieldProps('orderNo', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="姓名:">
                    <Input  {...getFieldProps('realName', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="手机号码:">
                    <Input  {...getFieldProps('phone', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="创建时间:">
                    <RangePicker disabledDate={this.disabledDate} {...getFieldProps('time', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('state', { initialValue: '' }) }>
                        <Option value="">全部</Option>
                        <Option value="10">提交申请</Option>
                        <Option value="20">审核通过</Option>
                        <Option value="30">审核不通过</Option>
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