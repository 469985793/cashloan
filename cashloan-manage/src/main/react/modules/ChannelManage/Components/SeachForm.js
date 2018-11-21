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
let SeachForm = React.createClass({
    getInitialState() {
        return {
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        this.props.passParams({
            searchParams:JSON.stringify(params),
            pageSize: 10,
            current: 1
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            searchParams:JSON.stringify({code:'',linker:'',name: '',phone:''}),
            pageSize: 10,
            current: 1
        });
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        return (
            <Form inline>
                <FormItem label="Channel code:">
                    <Input type="text" placeholder='Please enter the Channel code' {...getFieldProps('code') } />
                </FormItem>
                <FormItem label="Channel supplier:">
                    <Input type="text" placeholder='Please enter the Channel supplier' {...getFieldProps('linker') } />
                </FormItem>
                <FormItem label="Channel Name:">
                    <Input type="text" placeholder='Please enter the Channel Name' {...getFieldProps('name') } />
                </FormItem>
                <FormItem label="Phone:">
                    <Input type="text" placeholder='Please enter the Phone'{...getFieldProps('phone') } />
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>Search</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>Reset</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;/**
 * Created by WIN10 on 2016/10/12.
 */
