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
    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {

        const {getFieldProps} = this.props.form;
        var year, month, day;
        var year1, month1, day1;
        var date = new Date();
        if(date.getDate() < 10 ){
            year1 =  date.getFullYear();
            month1 = date.getMonth()+1;
            day1 = date.getDate();
            switch(month1){
                case 1:
                    year = year1-1;
                    month = 12;
                    day = day1+22;
                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                case 9:
                case 11:
                    year = year1;
                    month = month1<10 ? '0'+(month1-1) : (month1-1);
                    day = day1+22;
                    break;
                case 3:
                    year = year1;
                    month = '0'+(month1-1);
                    day = year&4==0&&year%100!=0||year%400==0 ? day1+20 : day1+19;
                    break;
                case 5:
                case 7:
                case 10:
                case 12:
                    year = year1;
                    month = month1<=10 ? '0'+(month1-1) : (month1-1);
                    day = day1+21;
                    break;
            }
            month1 = month1<10?'0'+month1 : month1;
            day1 = day1<10?'0'+day1 : day1;
        }else{
            year1 =  date.getFullYear();
            month1 = date.getMonth()+1<10 ? '0'+(date.getMonth()+1) : date.getMonth()+1;
            day1 = date.getDate();
            year = year1;
            month = month1;
            day = day1 < 20 ? '0'+(day1-9) : day1-9;
        }
        var date1 = new Date(year+'-'+month+'-'+day);
        var date2 = new Date(year1+'-'+month1+'-'+day1)
        return (
            <Form inline>
             <FormItem label="日期:">
                  <RangePicker disabledDate={this.disabledDate} {...getFieldProps('time', { initialValue: [date1,date2] }) } />
             </FormItem>
             
             <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;