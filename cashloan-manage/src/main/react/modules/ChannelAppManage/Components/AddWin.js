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
    DatePicker
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

var AddWin = React.createClass({
    getInitialState() {
        return {
            loading: false,
            record: "",
            data: [],
            canEdit: true,
            dataRecord: [],
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
        this.setState({
            canEdit: true,
            dataRecord: [],
        })
    },
    componentWillReceiveProps(nextProps, nextState) {
        this.setState({
            dataRecord: nextProps.dataRecord
        })
    },
    componentDidMount() {
        Utils.ajaxData({
            url: '/modules/manage/channelApp/channelNamelist.htm',
            callback: (result) => {
                if (result.code == 200) {
                    this.setState({
                        data: result.data
                    })
                }
            }
        })
    },
    handleOk() {
        var me = this;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            if (this.props.title == "新增") {
                var url = '/modules/manage/channelApp/save.htm';
                delete values.id;
            }
            else {
                var url = '/modules/manage/channelApp/update.htm';
            }
            Utils.ajaxData({
                url: url,
                data: values,
                method: 'post',
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
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
                提 交
            </Button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
        var channelId = [];
        if (state.data) {
            state.data.map((item) => {
                channelId.push(<Option key={item.name} value={item.id}>{item.name}</Option>)
            })
        }
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
                <Form horizontal form={this.props.form} style={{ marginTop: "20px" }}>
                    <Input type='hidden' {...getFieldProps('id') } />
                    {props.title == '新增' ? <div><Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="渠道:">
                                <Select placeholder="请选择..." {...getFieldProps('channelId', { rules: [{ required: true, message: '必填' }] }) } >
                                    {channelId}
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="应用类型:">
                                    <Select placeholder="请选择..." {...getFieldProps('appType') }>
                                        <Option key='android' value='10'>android</Option>
                                        <Option key='ios' value='20'>ios</Option>
                                    </Select>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="最新版本:">
                                    <Input type="text" placeholder="请输入最新版本" {...getFieldProps('latestVersion') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="最低兼容版本:">
                                    <Input type="text" placeholder="请输入最低兼容版本" {...getFieldProps('minVersion') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="下载地址:">
                                    <Input type="text" placeholder="请输入下载地址" {...getFieldProps('downloadUrl', { rules: [{ required: true, message: '必填' }] }) } />
                                </FormItem>
                            </Col>
                        </Row></div> : <div>
                            <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="应用类型:">
                                    <Select placeholder="请选择..." {...getFieldProps('appType') }>
                                        <Option key='android' value='10'>android</Option>
                                        <Option key='ios' value='20'>ios</Option>
                                    </Select>
                                </FormItem>
                            </Col>
                        </Row>
                            <Row>
                                <Col span="24">
                                    <FormItem  {...formItemLayout} label="最新版本:">
                                        <Input type="text" placeholder="请输入最新版本" {...getFieldProps('latestVersion') } />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span="24">
                                    <FormItem  {...formItemLayout} label="最低兼容版本:">
                                        <Input type="text" placeholder="请输入最低兼容版本" {...getFieldProps('minVersion') } />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span="24">
                                    <FormItem  {...formItemLayout} label="下载地址:">
                                        <Input type="text" placeholder="请输入下载地址" {...getFieldProps('downloadUrl', { rules: [{ required: true, message: '必填' }] }) } />
                                    </FormItem>
                                </Col>
                            </Row></div>}
                </Form>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
