import React from 'react';
import {
    Button,
    Form,
    Input,
    Select,
    Message,
    DatePicker
} from 'antd';
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
        var json = { sendTime: '', phone: params.phone, state: params.state};
        if (params.sendTime !== undefined) {
            json.sendTime = (DateFormat.formatDate(params.sendTime)).substring(0,10);
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
             current: 1,
        });
    },
    render() {

        const {
            getFieldProps
        } = this.props.form;

        return (
            <Form inline>
                <FormItem label="手机号:">
                    <Input type="text" {...getFieldProps('phone') } />
                </FormItem>
                <FormItem label="发送时间:">
                     <DatePicker  type="text" format="yyyy-MM-dd" {...getFieldProps('sendTime') } />
                </FormItem>
                <FormItem label="发送状态:">
                    <Select style={{ width: 100 }}  placeholder="请选择" {...getFieldProps('state',{initialValue:""}) }>
                        <Option value={""}>全部</Option>
                        <Option value={"10"}>发送成功</Option>
                        <Option value={"20"}>发送失败</Option>
                        <Option value={"30"}>发送中</Option>
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