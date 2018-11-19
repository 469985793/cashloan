import React from 'react';
import {
    Button,
    Form,
    Input,
    Select,
    Message
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
            search: JSON.stringify(params),
            pageSize: 10,
            currentPage: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            pageSize: 10,
            currentPage: 1,
        });
    },
    render() {

        const {
            getFieldProps
        } = this.props.form;

        return (
            <Form inline>
             <FormItem label="Rule Name:">{/*规则名称*/}
                    <Input type="text" {...getFieldProps('name') } />
                </FormItem>
                <FormItem label="Status:">{/*状态*/}
                    <Select style={{ width: 100 }}  placeholder="Please Choose" {...getFieldProps('state',{initialValue:""}) }>{/*请选择*/}
                        <Option value={""}>All</Option>{/*全部*/}
                        <Option value={"10"}>Enable</Option>{/*启用*/}
                        <Option value={"20"}>Disable</Option>{/*禁用*/}
                    </Select>
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>Search</Button></FormItem>{/*查询*/}
                <FormItem><Button type="reset" onClick={this.handleReset}>Reset</Button></FormItem>{/*重置*/}
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;