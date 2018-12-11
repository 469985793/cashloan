import React from 'react';
import { Button, Form, Input, Select, Message, DatePicker } from 'antd';
const createForm = Form.create;
const RangePicker = DatePicker.RangePicker;
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
        var json = { afterTime: '', beforeTime: '', realName: params.realName, phone: params.phone };
        // var json = { afterTime: '', type: '10', beforeTime: '', state: params.state, realName: params.realName, phone: params.phone };
        //console.log(params);
        if (params.registTime[0]) {
            json.beforeTime = (DateFormat.formatDate(params.registTime[0])).substring(0, 10);
            json.afterTime = (DateFormat.formatDate(params.registTime[1])).substring(0, 10);
        }
        console.log(params);
        this.props.passParams({
            search: JSON.stringify(json),
            pageSize: 10,
            current: 1,
        });
    },
    handleReset() {
        // console.log(this.props);
        var date = new Date();
        date=DateFormat.formatDate(date);
        this.props.form.resetFields();
        this.props.passParams({
            search: JSON.stringify({
                beforeTime:date.substring(0,10),
                afterTime:date.substring(0,10),
                // state: '15', 
                // type: '10',
                realName: '',
                phone:''
            }),
            pageSize: 10,
            current: 1,
        });
    },
    handleOut() {
        var params = this.props.form.getFieldsValue();
        var jsonParams = { afterTime: '',beforeTime: '', realName: params.realName, phone: params.phone };
        if (params.registTime[0]) {
        // console.log(params.registTime);
            jsonParams.beforeTime = (DateFormat.formatDate(params.registTime[0])).substring(0, 10);
            jsonParams.afterTime = (DateFormat.formatDate(params.registTime[1])).substring(0, 10);
        }
        jsonParams = JSON.stringify(jsonParams);
        window.open("/modules/manage/remitCheckLog/export.htm?searchParams="+encodeURI(jsonParams));
    },
    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {

        const { getFieldProps } = this.props.form;
        var date = new Date();
        return (
            <Form inline>
                <Input type="hidden" {...getFieldProps('state', { initialValue: '15' })} />
                <FormItem label="Date:">{/*收款人姓名*/}
                    <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('registTime', {initialValue: ""}) } />
                </FormItem>
                <FormItem label="Payee Name:">{/*收款人姓名*/}
                    <Input  {...getFieldProps('realName', { initialValue: '' })} />
                </FormItem>
                <FormItem label="Phone:">{/*手机号码*/}
                    <Input  {...getFieldProps('phone', { initialValue: '' })} />
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>{/*查询*/}Search</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>{/*重置*/}Reset</Button></FormItem>
                <FormItem><Button onClick={this.handleOut}>导出</Button></FormItem>            
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;