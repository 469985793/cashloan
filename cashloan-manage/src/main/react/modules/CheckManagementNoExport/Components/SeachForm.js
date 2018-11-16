import React from 'react';
import {
    Button,
    Form,
    Input,
    Select,
    DatePicker,
    Radio
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
        var json = { endTime: '', startTime: '', orderNo: params.orderNo, processResult: params.processResult,type:params.type,state:params.state};
        if (params.registTime) {
            json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
        }
        this.props.passParams({
            searchParams: JSON.stringify(json),
            pageSize: 10,
            current: 1
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            pageSize: 10,
            current: 1
        });
    },
    handleOut() {
        var params = this.props.form.getFieldsValue();
        var json = { endTime: '', startTime: '', orderNo: params.orderNo, processResult: params.processResult,type:params.type,state:params.state};
        if (params.registTime) {
            json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
        }
        json = JSON.stringify(json);
        window.open("/modules/manage/payCheck/export.htm?searchParams="+encodeURI(json));

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
            <Form inline>
                <FormItem label="订单号:">
                    <Input type="text" {...getFieldProps('orderNo', { initialValue: '' }) } />
                </FormItem>
            <FormItem label="错误类型:">
                <Select style={{ width: 100 }} {...getFieldProps('type', { initialValue: '' }) }>
                <Option value="">全部</Option>
                <Option value="10">金额不匹配</Option>
                <Option value="20">我方单边账</Option>
                <Option value="30">支付方单边</Option>
                </Select>
            </FormItem>
            <FormItem label="交易状态:">
                <Select style={{ width: 100 }} {...getFieldProps('state', { initialValue: '' }) }>
                <Option value="">全部</Option>
                <Option value="10">交易成功</Option>
                <Option value="20">退款</Option>
                </Select>
            </FormItem>
                <FormItem label="对账时间:">
                    <RangePicker disabledDate={this.disabledDate}{...getFieldProps('registTime', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="处理结果:">
                    <Select style={{ width: 100 }} {...getFieldProps('processResult', { initialValue: '' }) }>
                        <Option value="">全部</Option>
                        <Option value="10">未处理</Option>
                        <Option value="20">已处理</Option>
                    </Select>
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;/**
 * Created by WIN10 on 2016/10/12.
 */
