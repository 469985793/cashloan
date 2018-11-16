import React from 'react';
import {
  Button,
  Form,
  Input,
  Select,
  DatePicker
} from 'antd';
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
      searchParams : JSON.stringify(params),
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
  componentDidMount(){
  }, 
  add() {
    this.props.btnParamsFun({
      addBtn:true,
    });
  },
  refresh() {
    this.props.btnParamsFun({
      refreshBtn:true,
    });
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    return (
      <Form inline >
          <FormItem label=" 参数编号：">
            <Input  {...getFieldProps('code', { initialValue: ''})} />
          </FormItem>
          <FormItem label="参数名称：">
            <Input  {...getFieldProps('name', { initialValue: ''})} />
          </FormItem>
           <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
           <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem> 
           <FormItem><Button onClick={this.add}>新增</Button></FormItem>
           <FormItem><Button onClick={this.refresh}>刷新缓存</Button></FormItem> 
        </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;