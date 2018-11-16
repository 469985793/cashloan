import React from 'react';
import {
    Button,
    Form,
    Input,
    Select,
    DatePicker,
    Radio
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
//import ComboData from '../../../utils/ComboData';
//var ProductTypeList = ComboData.getCombo('PRODUCT_TYPE');
//var borrowTypeList = ComboData.getCombo('BORROW_TYPE');
let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        this.props.passParams({
            search:JSON.stringify(params),
            pageSize: 10,
            current: 1
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            search:JSON.stringify({name: ''}),
            pageSize: 10,
            current: 1
        });
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        return (
            <Form inline>
                <FormItem label="任务名:">
                    <Input type="text" {...getFieldProps('name') } />
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;/**
 * Created by WIN10 on 2016/10/12.
 */
