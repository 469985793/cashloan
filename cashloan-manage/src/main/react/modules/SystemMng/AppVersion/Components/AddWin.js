import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
let treeData = [];

var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {}
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;
        //console.log(record)
        var url = "/modules/manage/user/accessCode/save.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            Utils.ajaxData({
                url: '/modules/manage/user/accessCode/save.htm',
                method: 'post',
                data: {
                    code: values.code,
                    sysUserId: values.sysUserId,
                    time: values.time
                },
                callback: (result) => {
                    Modal.success({
                        title: result.msg,
                    });
                    me.handleCancel();
                }
            })

        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
                提 交
            </button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        var optionItem = [];
        for (let i = 0; i < props.condition.length; i++) {
            optionItem.push(<Option value={props.condition[i].id}>{props.condition[i].userName}</Option>)
        }
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} >
                <Form horizontal form={this.props.form}>
                    <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="访问码：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('code', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10  }] }) } type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="绑定管理员：">
                                <Select disabled={!props.canEdit}  {...getFieldProps('sysUserId', { rules: [{ required: true, message: '必填', type: 'number' }] }) }>
                                    {optionItem}
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="有效时间：">
                                <Select disabled={!props.canEdit}  {...getFieldProps('time', { rules: [{ required: true, message: '必填' }] }) } >
                                    <Option value='01'>两小时</Option>
                                    <Option value='02'>十二小时</Option>
                                    <Option value='03'>一天</Option>
                                    <Option value='04'>两天</Option>
                                    <Option value='05'>七天</Option>
                                    <Option value='06'>一个月</Option>
                                    <Option value='07'>三个月</Option>
                                    <Option value='08'>六个月</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>

                </Form>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
