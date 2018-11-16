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
    Col,
    Radio
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RadioGroup = Radio.Group;
const objectAssign = require('object-assign');


var AddWin = React.createClass({
    getInitialState() {
        return {
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    handleOk() {
        var me = this;
        var props = me.props;
        var record = props.record;
        var arr = [record.id];
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            Utils.ajaxData({
                url: '/modules/manage/system/user/update.htm',
                data: {
                    ids:arr,
                    status:'editpassword',
                    password: values.password
                },
                callback: (result) => {
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {

                                me.handleCancel();
                            }
                        });
                    } else {
                        Modal.error({
                            title: result.msg
                        });
                    }
                }
            });
        })
    },
    

    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
                提交
            </button>,
            <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false}>
                <Form horizontal form={this.props.form}>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="密码:">
                                <Input type='text' {...getFieldProps('password',{rules:[{required: true, message:'必填'}]}) } />
                            </FormItem>
                        </Col>
                    </Row>

                </Form>
            </Modal >
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;