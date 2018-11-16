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
            pageSize: 10,
            current: 1,
        });
    },
    handleOut() {
        var params = this.props.form.getFieldsValue();
        var json = JSON.stringify(params);
        window.open("/modules/manage/borrow/export.htm?searchParams="+encodeURI(json));

    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
             <FormItem label="真实姓名:">
                  <Input  {...getFieldProps('realName',{initialValue: ''})} />
             </FormItem>
             <FormItem label="手机号码:">
                  <Input  {...getFieldProps('phone',{initialValue: ''})} />
             </FormItem>
             <FormItem label="订单号:">
                  <Input  {...getFieldProps('orderNo',{initialValue: ''})} />
             </FormItem>
             <FormItem label="订单状态:">
             <Select style={{ width: 170 }} {...getFieldProps('state',{initialValue: ''})}>
			             <Option value="">全部</Option>
			             <Option value="1">申请,待风控审核</Option>
			             <Option value="2">风控审核通过,待复审</Option>
			             <Option value="3">复审通过,待放款</Option>
			             <Option value="4">放款中</Option>
			             <Option value="5">已放款,待还款</Option>
			             <Option value="6">正常还款</Option>
			             <Option value="21">已逾期</Option>
			             <Option value="22">逾期还款</Option>
			             <Option value="31">风控审核不通过</Option>
			             <Option value="32">复审不通过</Option>
			             <Option value="41">放款失败</Option>
			             <Option value="42">放款被拒</Option>
			             <Option value="51">坏账</Option>
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