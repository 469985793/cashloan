import React from 'react';
import {Button, Form, Input, Select,Message,DatePicker } from 'antd';
const RangePicker = DatePicker.RangePicker;
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
         var json = {startTime:'',endTime:'',user_name:params.user_name};
     //console.log(params);
     if(params.time){
        json.endTime=(DateFormat.formatDate(params.time[1])).substring(0,10);
        json.startTime=(DateFormat.formatDate(params.time[0])).substring(0,10);
      }
        
        //console.log(params);
        this.props.passParams({
            search: JSON.stringify(json),
            pageSize: 10,
            current: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            search: JSON.stringify({type:'20,40'}),
            pageSize: 10,
            current: 1,
        });
    },
    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
            <input type="hidden" {...getFieldProps('type',{initialValue: '20,40'})}/>
             <FormItem label="创建时间:">
                  <RangePicker disabledDate={this.disabledDate} {...getFieldProps('time', { initialValue: '' }) } />
             </FormItem>
             <FormItem label="用户名:">
                  <Input  {...getFieldProps('user_name',{initialValue: ''})} />
             </FormItem>
             <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
             <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;