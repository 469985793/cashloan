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

const objectAssign = require('object-assign');


var AdjustCreditLine = React.createClass({
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
        var url = "/modules/manage/user/credit/updateTotal.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            else {
                Utils.ajaxData({
                    url: url,
                    data: {
                        unuse: values.total,
                        id: record.id,
                        remark:"手工调整"
                    },
                    callback: (result) => {
                        if (result.code == 200) {
                            Modal.success({
                                title: result.msg,
                                onOk: () => {
                                    props.form.resetFields();
                                    props.hideModal();
                                }
                            });
                        } else {
                            Modal.error({
                                title: result.msg
                            });
                        }
                    }
                });
            }
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
                确定
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
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="600" footer={modalBtns} >
                <Form horizontal form={this.props.form}>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="当前可用额度(元)：">
                                <Input disabled={true}  {...getFieldProps('unuse', { initialValue: this.props.manualUnuse }) } type="text" />
                            </FormItem>
                        </Col>

                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="调整当前可用额度(元)：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('total', { rules: [{ required: true, message: '必填' }] }) } type="text" />
                            </FormItem>
                        </Col>

                    </Row>

                </Form>
            </Modal>
        );
    }
});
AdjustCreditLine = createForm()(AdjustCreditLine);
export default AdjustCreditLine;
