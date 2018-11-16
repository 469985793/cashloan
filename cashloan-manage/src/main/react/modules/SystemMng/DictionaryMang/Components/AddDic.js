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
    Col
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
let treeData = {};
var AddDic = React.createClass({
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

        var url = "/modules/manage/system/dict/save.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            //console.log("values", values)
            var data = objectAssign({}, {
                form: JSON.stringify(values)
            }, {
                    status: 'create'
                });
            if (title == "修改") {
                var data = objectAssign({}, {
                    form: JSON.stringify(values)
                }, {
                        status: 'update'
                    });
            }
            Utils.ajaxData({
                url: url,
                data: data,
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
                            title: result.msg,
                        });
                    }
                }
            });
        });
    },
    getRoleList() {
        return roleList.map((item, index) => {
            return <Option key={item.id} >{item.name}</Option>
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
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
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
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
                <Form horizontal  form={this.props.form}>
                    <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="字典类型：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="类型代码：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('code', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem  {...formItemLayout} label="排序：">
                            <InputNumber disabled={!props.canEdit} style={{ width: '100%' }} {...getFieldProps('sort', { rules: [{ required: true, message: '必填', type: 'number' }] }) }   autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="备注：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('remark', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Form>
            </Modal>
        );
    }
});
AddDic = createForm()(AddDic);
export default AddDic;
