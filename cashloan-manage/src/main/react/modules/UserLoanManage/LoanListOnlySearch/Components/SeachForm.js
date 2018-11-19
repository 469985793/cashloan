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
        params.type = "repay";
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
            searchParams: JSON.stringify({type:"repay"}),
        });
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
             <Input type="hidden" {...getFieldProps('state',{initialValue: '30'})} />
             <FormItem label="Real Name:">{/*真实姓名*/}
                  <Input  {...getFieldProps('realName')} />
             </FormItem>
             <FormItem label="Phone:">{/*手机号*/}
                  <Input  {...getFieldProps('phone')} />
             </FormItem>
             <FormItem label="Order Number:">{/**/}
                  <Input  {...getFieldProps('orderNo')} />
             </FormItem>
             <FormItem label="Order Status:">{/*订单状态*/}
             <Select style={{ width: 170 }} {...getFieldProps('state',{initialValue: ''})} placeholder='请选择...'>
		             <Option value="">All</Option>{/*全部*/}
		             <Option value="301">Pending loan review</Option>{/*待放款审核*/}
		             <Option value="302">Lending approval</Option>{/*放款审核通过*/}
		             <Option value="303">Lending review failed</Option>{/*放款审核不通过*/}
		             <Option value="31">Lending failure</Option>{/*放款失败*/}
		             <Option value="32">Loan/Already released</Option>{/*贷款中/已放款*/}
		             <Option value="33">Continue to borrow</Option>{/*转续借*/}
		             <Option value="33-34">Renewal</Option>{/*续借中*/}
		             <Option value="33-40">Renewal has been paid off</Option>{/*续借已还清*/}
		             <Option value="33-50">Renewal of overdue payment</Option>{/*续借逾期未还清*/}
		             <Option value="33-41">Renewed overdue has been paid off</Option>{/*续借逾期已还清*/}
		             <Option value="35">Renewal processing</Option>{/*续借处理中*/}
		             <Option value="40">Normally pay off</Option>{/*正常还清*/}
		             <Option value="41">Overdue has been paid off</Option>{/*逾期已还清*/}
		             <Option value="42">Overdue has been paid off - amount relief</Option>{/*逾期已还清-金额减免*/}
		             <Option value="43">Repayment processing</Option>{/*还款处理中*/}
		             <Option value="50">Overdue</Option>{/*逾期*/}
		             <Option value="90">Bad debt</Option>{/*坏账*/}
             </Select>
             </FormItem>
             <FormItem><Button type="primary" onClick={this.handleQuery}>Search</Button></FormItem>{/*查询*/}
             <FormItem><Button type="reset" onClick={this.handleReset}>Reset</Button></FormItem>{/*重置*/}
            </Form>
        );
    }
});
SeachForm = createForm()(SeachForm);
export default SeachForm;