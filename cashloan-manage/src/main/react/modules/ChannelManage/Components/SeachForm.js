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
                <FormItem label="渠道编码:">
                    <Input type="text" placeholder='请输入渠道编码' {...getFieldProps('code') } />
                </FormItem>
                <FormItem label="联系人姓名:">
                    <Input type="text" placeholder='请输入联系人姓名' {...getFieldProps('linker') } />
                </FormItem>
                <FormItem label="渠道名称:">
                    <Input type="text" placeholder='请输入渠道名称' {...getFieldProps('name') } />
                </FormItem>
                <FormItem label="联系方式:">
                    <Input type="text" placeholder='请输入联系方式'{...getFieldProps('phone') } />
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
