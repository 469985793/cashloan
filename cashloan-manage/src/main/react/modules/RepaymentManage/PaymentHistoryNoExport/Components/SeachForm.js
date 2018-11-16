import React from 'react';
import { Button, Modal, Form, Input, Select, Message } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const confirm = Modal.confirm;

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
        window.open("/modules/manage/borrowRepayLog/export.htm?searchParams="+encodeURI(json));

    },
    render() {

        const { getFieldProps } = this.props.form;

        return (
            <Form inline>
                <FormItem label="订单号:">
                    <Input  {...getFieldProps('orderNo', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="流水号:">
                    <Input  {...getFieldProps('serialNumber', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="还款账号:">
                    <Input  {...getFieldProps('repayAccount', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="是否金额减免:">
                    <Select style={{ width: 100 }} {...getFieldProps('type', { initialValue: '' }) }>
                        <Option value="">全部</Option>
                        <Option value="10">是</Option>
                        <Option value="20">不是</Option>
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