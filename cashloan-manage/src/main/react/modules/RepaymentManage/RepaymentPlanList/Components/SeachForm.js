import React from 'react';
import { Button, Form, Input, Select, Message } from 'antd';
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
        window.open("/modules/manage/repaymentPlanList/export.htm?searchParams="+encodeURI(json));

    },
    render() {

        const { getFieldProps } = this.props.form;

        return (
            <Form inline>
                <FormItem label="Real Name:">{/*真实姓名*/}
                    <Input  {...getFieldProps('realName') } />
                </FormItem>
                <FormItem label="Phone:">{/*手机号码*/}
                    <Input  {...getFieldProps('phone') } />
                </FormItem>
                <FormItem label="Order Number:">{/*订单号*/}
                    <Input  {...getFieldProps('orderNo') } />
                </FormItem>
                <FormItem label="Repayment Status:">{/*还款状态*/}
                    <Select style={{ width: 150 }} {...getFieldProps('state', { initialValue: '' }) }>
                        <Option value="">All</Option>{/*全部*/}
                        <Option value="6">Repaid</Option>{/*已还款*/}
                        <Option value="5">Unpaid</Option>{/*未还款*/}
                        <Option value="21">Overdue</Option>{/*逾期*/}
                        <Option value="22">Overdue payment</Option>{/*逾期还款*/}
                        <Option value="51">Bad debts</Option>{/*坏账*/}
                    </Select>
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>Search{/*查询*/}</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>Reset{/*重置*/}</Button></FormItem>
                <FormItem><Button onClick={this.handleOut}>导出</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;