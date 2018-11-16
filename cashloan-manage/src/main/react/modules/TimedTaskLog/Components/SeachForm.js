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
    componentDidMount(){
        Utils.ajaxData({
            url: '/modules/quartz/page.htm',
            data:{
                pageSize: 10,
                current: 1,
                search:JSON.stringify({name: ''})
            },
            method: 'post',
            callback: (result) => {
                var classNameList = [];
                
                classNameList = result.data.map((item)=>{
                    return <Option key={item.id} value={item.id}>{item.name}</Option>
                })
                classNameList.unshift(<Option key={'d'} value="">全部</Option>)
                this.setState({
                    classNameList:classNameList
                });
            }
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
                    <Select style={{ width: 200 }} {...getFieldProps('quartzId', {initialValue: ''})}>
                        {this.state.classNameList}
                    </Select>
                </FormItem>
                <FormItem label="执行结果">
                    <Select style={{width:200}} {...getFieldProps('result',{initialValue:""})}>
                        <Option key={'a'} value="">全部</Option>
                        <Option key={'b'} value="10">执行成功</Option>
                        <Option key={'c'} value="20">执行失败</Option>
                    </Select>
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
