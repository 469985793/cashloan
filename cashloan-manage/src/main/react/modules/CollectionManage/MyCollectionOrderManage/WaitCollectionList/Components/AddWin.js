import React from 'react';
import {
    Button,
    Form,
    Input,
    InputNumber,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col,
    DatePicker,
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

var AddWin = React.createClass({
    getInitialState() {
        return {
            loading: false,
            canEdit: true,
            stateStr: ""
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },

    handleOk() {
        var me = this;
        var url = '/modules/manage/borrow/repay/urge/saveOrderInfo.htm';
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            //console.log(me.props.record.id);
            var d = new Date(values.createTime1);  
            var youWant=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
            var d = new Date(values.promiseTime);  
            var youWant2=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();  
            Utils.ajaxData({
                url: url,
                data: {
                    createTime: youWant,
                    dueId: me.props.record.id, 
                    promiseTime: youWant2,
                    remark: values.remark,
                    state: values.state1,
                    way: values.way
                },
                callback: (result) => {
                    if (result.code == "200") {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                    } else {
                        Modal.error({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
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
        var me = this;
        var modalBtns = [
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
                提 交
            </Button>

        ];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 14
            },
        };
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} >
                <div style={{ position: "relative" }}>
                    <Form horizontal form={this.props.form} style={{ marginTop: "20px" }}>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="真实姓名:">
                                    <Input type="text" disabled={true} {...getFieldProps('borrowName') } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="手机号:">
                                    <Input type="text" disabled={true} {...getFieldProps('phone') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="金额:">
                                    <Input type="text" disabled={true} {...getFieldProps('amount') } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="借款时间:">
                                    <Input type="text" disabled={true} {...getFieldProps('borrowTime') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="预计还款时间:">
                                    <Input type="text" disabled={true} {...getFieldProps('repayTime') } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="逾期天数:">
                                    <Input type="text" disabled={true} {...getFieldProps('penaltyDay') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="罚息:">
                                    <Input type="text" disabled={true} {...getFieldProps('penaltyAmout') } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收人:">
                                    <Input type="text" disabled={true} {...getFieldProps('userName') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="订单状态:">
                                    <Input type="text" disabled={true} {...getFieldProps("state") } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收时间:">
                                    <DatePicker disabled={true} style={{width:'100%'}} type="text" format="yyyy-MM-dd HH:mm:ss" {...getFieldProps('createTime1', { rules: [{required:true,message:'必填',type:'date'}] }) } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收结果:">
                                    <Select style={{ width: '100%' }} disabled={props.canEdit} placeholder="请选择"  {...getFieldProps('state1',{ rules: [{ required: true, message: '必填' }] })}>}                                        
                                        <Option value="30">承诺还款</Option>
                                        <Option value="50">坏账</Option>
                                        <Option value="20">催收中</Option>
                                        <Option value="40">坏账</Option>
                                    </Select>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="承诺还款时间:">
                                    <DatePicker disabled={true} style={{width:'100%'}} type="text" format="yyyy-MM-dd HH:mm:ss" {...getFieldProps('promiseTime', { rules: [{required:true,message:'必填',type:'date'}] }) } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收方法:">
                                    <Select style={{ width: '100%' }} disabled={props.canEdit} placeholder="请选择"  {...getFieldProps('way',{ rules: [{ required: true, message: '必填' }] })}>
                                        <Option value="10">电话</Option>
                                        <Option value="20">邮件</Option>
                                        <Option value="30">短信</Option>
                                        <Option value="40">现场沟通</Option>
                                        <Option value="50">其他</Option>
                                    </Select>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="24">
                                <FormItem  labelCol={{span: 4}} wrapperCol={{span:16}} label="催收反馈:">
                                    <Input type="textarea" disabled={props.canEdit} {...getFieldProps('remark') } />
                                </FormItem>
                            </Col>
                        </Row>
                    </Form>
                </div>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
