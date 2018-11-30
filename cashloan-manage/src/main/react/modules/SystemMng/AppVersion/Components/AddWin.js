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
                    <Input  {...getFieldProps('id', { initialValue: '' })} type="hidden" />
                    {/*app编号*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="app_code：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('app_name', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*app名称*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="app_name:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('app_name', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*app类型*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="app_type:">
                                <Select disabled={!props.canEdit}  {...getFieldProps('app_type', { rules: [{ required: true, message: '必填' }] })} >
                                    <Option value='10'>Android</Option>
                                    <Option value='11'>Android Pad</Option>
                                    <Option value='20'>IOS</Option>
                                    <Option value='21'>IOS Pad</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    {/*版本号*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="version_code:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('version_code', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*版本名称*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="version_name:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('version_name', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*是否强制变更*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="force_flag:">
                                <Select disabled={!props.canEdit}  {...getFieldProps('force_flag', { rules: [{ required: true, message: '必填' }] })} >
                                    <Option value='0'>不强制</Option>
                                    <Option value='1'>强制</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    {/*APP下载地址*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="down_url:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('down_url', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*APP下载地址*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="google_down_url:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('google_down_url', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*Google play下载地址*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="google_down_url:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('google_down_url', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*推广主页*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="spread_url:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('spread_url', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*发布人id*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="publish_uid:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('publish_uid', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*发布时间*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="publish_time:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('publish_time', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*状态*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="status:">
                                <Select disabled={!props.canEdit}  {...getFieldProps('force_flag', { rules: [{ required: true, message: '必填' }] })} >
                                    <Option value='1'>正常</Option>
                                    <Option value='-1'>删除</Option>
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
