import React from 'react';
import {Button, Form, Input, Select,Message,DatePicker } from 'antd';
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
         var json = {startDate:'',endDate:'',operatingFeeStatus:params.operatingFeeStatus,orderNo:params.orderNo};
     if(params.time){
        json.endDate=(DateFormat.formatDate(params.time[1])).substring(0,10);
        json.startDate=(DateFormat.formatDate(params.time[0])).substring(0,10);
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
            pageSize: 10,
            current: 1,
        });
    },
    handleOut() {
        var params = this.props.form.getFieldsValue();
        var json = JSON.stringify(params);
        window.open("/modules/manage/payLog/export.htm?searchParams="+encodeURI(json));

    },
    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
             <FormItem label="时间:">
                  <RangePicker disabledDate={this.disabledDate}  {...getFieldProps('time', { initialValue: '' }) } />
             </FormItem>
             <FormItem label="借款订单:">
                  <Input  {...getFieldProps('orderNo',{initialValue: ''})} />
             </FormItem>
             <FormItem label="状态:">
             <Select style={{ width: 100 }} {...getFieldProps('operatingFeeStatus',{initialValue: ''})} placeholder='请选择...'>
                 <Option value="">全部</Option>
                 <Option value="10">处理中</Option>
                 <Option value="20">成功</Option>
                 <Option value="30">失败</Option>
             </Select>
             </FormItem>
             <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
             <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
             {/*<FormItem><Button onClick={this.handleOut}>导出</Button></FormItem>*/}
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;