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
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
             <FormItem label="Rating level name:">{/*评分等级名称*/}
                  <Input  {...getFieldProps('rankName', {initialValue: ''})} />
             </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>{/*查询*/}Search</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>{/*重置*/}Reset</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;