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
        window.open("?searchParams="+encodeURI(json));

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
			             <Option value="10">申请成功待审核</Option>
			             <Option value="211">自动审核通过</Option>
			             <Option value="212">自动审核不通过</Option>
			             <Option value="220">自动审核未决待人工复审</Option>
			             <Option value="221">人工复审通过</Option>
			             <Option value="222">人工复审不通过</Option>
			             <Option value="301">待放款审核</Option>
			             <Option value="302">放款审核通过</Option>
			             <Option value="303">放款审核不通过</Option>
			             <Option value="31">放款失败</Option>
			             <Option value="32">贷款中 /已放款</Option>
			             <Option value="230">初审待审核</Option>
			             <Option value="231">初审审核通过</Option>
			             <Option value="232">初审审核不通过</Option>
			             <Option value="240">复审待审核</Option>
			             <Option value="241">复审审核通过</Option>
			             <Option value="242">复审审核不通过</Option>
			             <Option value="33">转续借</Option>
			             <Option value="33-34">续借中</Option>
			             <Option value="33-40">续借已还清</Option>
			             <Option value="33-50">续借逾期未还清</Option>
			             <Option value="33-41">续借逾期已还清</Option>
			             <Option value="35">续借处理中</Option>
			             <Option value="40">正常还清</Option>
			             <Option value="41">逾期已还清</Option>
			             <Option value="42">逾期已还清-金额减免</Option>
			             <Option value="43">还款处理中</Option>
			             <Option value="50">逾期</Option>
			             <Option value="90">坏账</Option>
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