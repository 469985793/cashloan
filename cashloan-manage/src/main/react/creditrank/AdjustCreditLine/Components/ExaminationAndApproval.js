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
             canEdit: true,
             visible: false,
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
      
        var url = "/modules/manage/user/credit/approval.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            else {
                Utils.ajaxData({
                    url: url,
                    data: {
                        id: record.id,
                        total: values.total,
                        state: values.state,
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
        var record = this.props.record;
        var isTotal = record ? record.total :"0";
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
                  <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />     
                    <Row>
                        <Col>
                            <FormItem  {...formItemLayout} label="总额度：">
                                <Input disabled={!props.canEdit} {...getFieldProps('total', { initialValue: isTotal }) } type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                         <FormItem  {...formItemLayout} label="状态：">
                              <Select disabled={!props.canEdit}  {...getFieldProps('state', {initialValue: '10'})}style={{ width: 120 }}>
                                  <Option value="10">审批通过</Option>
                                  <Option value="40">审批不通过</Option>
                              </Select>
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
