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
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;

const objectAssign = require('object-assign');


var CollectionReg = React.createClass({
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
        var url = "/modules/manage/borrow/borrowPress/insert.htm";
        this.props.form.validateFields((errors, values) => {
            var searchdata = {};
            var time=DateFormat.formatDate(values.pressDate);
            searchdata = {
                borrowId: record.id,
                orderNo:record.orderNo,  
                type:values.type,
                pressDate:time,
                content:values.content
            }
            var params = objectAssign({}, this.props.params, {
                form : JSON.stringify(searchdata),
            });
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            else {
                Utils.ajaxData({
                    url: url,
                    data: params,
                    contentType: 'application/json',
                    callback: (result) => {
                        if (result.code == 200) {
                            Modal.success({
                                title: result.msg,
                                onOk: () => {
                                    props.hideModal();
                                    props.form.resetFields();
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
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} >
                <Form horizontal form={this.props.form}>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="催收方式：">
                                <Select style={{ width: 150 }} placeholder="请选择"  {...getFieldProps('type',{ rules: [{ required: true, message: '必填' }] })}>
                                    <Option value="1">电话</Option>
                                    <Option value="2">邮件</Option>
                                    <Option value="3">短信</Option>
                                    <Option value="4">现场沟通</Option>
                                    <Option value="5">其他</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem {...formItemLayout} label="催收时间：">
                                <DatePicker placeholder="催收时间" format="YYYY-MM-DD HH:mm:ss" {...getFieldProps('pressDate', {
                                rules: [
                                    {required : true, type: 'date', message : '催收时间不能为空'},
                                ]})} style={{width: 150}} disabled={!props.canEdit} />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="催收备注：">
                                <Input disabled={!props.canEdit} type="textarea" {...getFieldProps('content', { rules: [{ required: true, message: '必填' }] }) }  />
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Modal>
        );
    }
});
CollectionReg = createForm()(CollectionReg);
export default CollectionReg;
