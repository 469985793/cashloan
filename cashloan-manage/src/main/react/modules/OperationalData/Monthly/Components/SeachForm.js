import React from 'react';
import {Button, Form, Input, Select,Message,DatePicker } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const MonthPicker  = DatePicker.MonthPicker ;
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
     if(params.time){
        var d = new Date(params.time);  
        var d1 = new Date(params.time1); 
        var m1,m2; 
        if((d.getMonth() + 1) < 10){
            m1 = '0'+(d.getMonth() + 1)
        }else{
            m1 = (d.getMonth() + 1)
        } 
        if((d1.getMonth() + 1) < 10){
            m2 = '0'+(d1.getMonth() + 1)
        }else{
            m2 = (d1.getMonth() + 1)
        } 
         json.startDate=d.getFullYear() + '-' + m1;
        json.endDate=d1.getFullYear() + '-' + m2;
      }
        this.props.passParams({
            search: JSON.stringify(json),
            pageSize: 10,
            current: 1,
        });
    },
    disabledDate(startValue){
        var date = new Date();
        return startValue.getTime() > date.getTime();
    },
    disabledDate1(startValue){
        var date = new Date();
        return startValue.getTime() > date.getTime();
    },
    render() {

        const {getFieldProps} = this.props.form;
        var year, month;
        var year1, month1;
        var date = new Date();
        year1 =  date.getFullYear();
        month1 = date.getMonth()+1;
        if(month1>=10){
            year = year1;
            month = '0'+(month1-9);
        }else{
            year = year1-1;
            month = month1<7?'0'+(month1+3):month1+3;
        }
        month1 = month1<10 ? '0'+month1 : month1;
        return (
            <Form inline>
             <FormItem label="月份:">
                  <MonthPicker disabledDate={this.disabledDate}  {...getFieldProps('time', { initialValue: year+'-'+month }) } />
                  <span>至:</span>
                  <MonthPicker disabledDate={this.disabledDate1}  {...getFieldProps('time1', { initialValue: year1+'-'+month1 }) } />
             </FormItem>
             
             <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;