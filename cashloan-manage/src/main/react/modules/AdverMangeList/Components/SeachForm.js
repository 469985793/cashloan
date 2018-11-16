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
            current: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            current: 1,
        });
    },

    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
                <FormItem label="标题:">
                    <Input  {...getFieldProps('title')} />
                </FormItem>
                <FormItem label="状态:">
                    <Select style={{ width: 150 }} {...getFieldProps('state', {initialValue: ''})}>
                        <Option value="">全部</Option>
                        <Option value="20">禁用</Option>
                        <Option value="10">启用</Option>
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