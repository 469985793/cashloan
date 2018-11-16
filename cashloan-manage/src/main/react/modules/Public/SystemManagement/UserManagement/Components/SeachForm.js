var React = require('react');
import { Button, Form, Input, Select} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;

let SeachForm = React.createClass({ 
  handleSubmit() { 
    var params = this.props.form.getFieldsValue();
    this.props.passParams({
        user:JSON.stringify(params),
        start:0,
        limit:25,
        page:1,
    });  
  }, 
  render() {
    const { getFieldProps } = this.props.form;
    var roles=[{ "id": 1,  "name": "系统管理员"} ]
    var roleList = roles.map((item)=>{
                      return (<Option key={item.id} value={String(item.id)}>{item.name}</Option>);
                    });
    return (
        <Form inline >
          <FormItem
            label="员工编号：">
            <Input  {...getFieldProps('number', { initialValue: ''})} />
          </FormItem>
          <FormItem
            label="员工姓名：">
            <Input  {...getFieldProps('name', { initialValue: ''})} />
          </FormItem>
          <FormItem
             label="用户状态：" 
             >
             <Select style={{ width: 100 }}
               {...getFieldProps('status', { initialValue: ''})}>
               <Option value="0">正常</Option>
               <Option value="1">禁用</Option> 
             </Select>
           </FormItem>
           <FormItem
             label="用户角色：" 
             >
             <Select style={{ width: 100 }}
               {...getFieldProps('roleId', { initialValue: ''})}>
               { roleList }
             </Select>
           </FormItem> 
           <FormItem><Button type="primary" onClick={this.handleSubmit}>查询</Button></FormItem> 
        </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;