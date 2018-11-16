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
const Option = Select.Option;

const objectAssign = require('object-assign');


var AddRole = React.createClass({
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
        var url = title =="新增"?"/modules/manage/creditdata/tpp/save.htm":"/modules/manage/creditdata/tpp/update.htm";
        this.props.form.validateFields((errors, values) => {
            var params = values;
            params.id = record.id
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
                            <FormItem  {...formItemLayout} label="第三方名称:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('name',{ rules: [{required:true,message:'必填,且不超过32位',max:32}]})} type="text"  />  
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="第三方标识:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('nid',{ rules: [{required:true,message:'必填,且不超过32位',max:32}]})} type="text"  /> 
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="商户号:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('merNo',{ rules: [{max:128,message:'不超过128位'}]})} type="text"  /> 
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="加密方式:"> 
                                <Select disabled={!props.canEdit} {...getFieldProps('signType',{rules:[{required:true,message:'必填'}]})}>
                                    <Option value={'RSA'}>RSA</Option>
                                    <Option value={'MD5'}>MD5</Option>
                                </Select>
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="key:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('key',{ rules: [{required:true,message:'必填,且不超过2048位',max:2048}]})} type="text"  /> 
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="扩展字段:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('extend',{ rules: [{max:128,message:'不超过128位'}]})} type="text"  /> 
                            </FormItem> 
                        </Col>
                    </Row>
                </Form>
            </Modal>
        );
    }
});
AddRole = createForm()(AddRole);
export default AddRole;
