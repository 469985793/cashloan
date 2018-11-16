import React from 'react';
import {Button, Form, Input, Select,Message,DatePicker } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const RangePicker = DatePicker.RangePicker;
const Option = Select.Option;

let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
     handleQuery() {
        var params = this.props.form.getFieldsValue();
         var json = {startDate:'',endDate:''};
     //console.log(params);
     if(params.time[0]){
        json.startDate=(DateFormat.formatDate(params.time[0])).substring(0,10);
        json.endDate=(DateFormat.formatDate(params.time[1])).substring(0,10);
      }
        this.props.passParams({
            search: JSON.stringify(json),
            pageSize: 10,
            current: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            search: JSON.stringify({startDate:'',endDate:''}),
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
             <FormItem label="日期:">
                  <RangePicker disabledDate={this.disabledDate} {...getFieldProps('time', { initialValue: '' }) } />
             </FormItem>
             
             <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;