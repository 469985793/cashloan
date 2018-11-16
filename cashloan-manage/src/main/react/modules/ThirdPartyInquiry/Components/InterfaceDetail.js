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


var SceneWin = React.createClass({
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
        var url ="";
        this.props.form.validateFields((errors, values) => {
            var params = values;
            if(title == "新增"){
                url = "/modules/manage/creditdata/tppBusiness/save.htm";
            }else{
                url = "/modules/manage/creditdata/tppBusiness/update.htm";
                params.id = record.id;
            }
            
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
                    <Input {...getFieldProps('tppId') } type="hidden" autoComplete="off" />
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="接口名称:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('name',{ rules: [{required:true,message:'必填,且不超过32位',max:32}]})} type="text"  /> 
                            </FormItem>  
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="简称:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('nid',{ rules: [{required:true,message:'必填,且不超过32位',max:32}]})} type="text"  /> 
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="扩展参数:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('extend',{ rules: [{max:1024,message:'不超过1024位'}]})} type="text"  /> 
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="请求地址:"> 
                                <Input disabled={!props.canEdit}  {...getFieldProps('url',{ rules: [{max:256,message:'不超过256位'}]})} type="text"  /> 
                            </FormItem> 
                        </Col>
                    </Row>
                </Form>
            </Modal>
        );
    }
});
SceneWin = createForm()(SceneWin);
export default SceneWin;
