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


var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {},
        };
    },
    componentWillReceiveProps(nextProps, nextState) {
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
        var url;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            var user = {};
            user = {
                oldPassword:values.oldPassword,
                newPassword:values.newPassword,
                newPassword2:values.newPassword2,
            }
           var params = {
                user : JSON.stringify(user),
            }
            //console.log("values",user)
            Utils.ajaxData({
                url: "/modules/system/modifyPassword.htm",
                data: params,
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
        })
    },

    componentDidMount() {

    },
    validatePwd(rule, value, callback) {
        const { validateFields } = this.props.form;
        if (value) {
            validateFields(['newPassword2'], { force: true });
        }
        callback();
    },
    validateAgainPwd(rule, value, callback) {
        const { getFieldValue } = this.props.form;
        if (value && value !== getFieldValue('newPassword')) {
            callback('两次密码输入不一致!');
        } else {
            callback();
        }
    },
    
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
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
        var businessEdit = props.title == "新增" && typeof(props.form.getFieldValue('tppName')) =="undefined" ?true:false;
        //console.log("timeState",this.state.timeState)
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500"  footer={modalBtns} maskClosable={false}>
                <Form horizontal  form={this.props.form}>
                 <Input {...getFieldProps('id') } type="hidden" autoComplete="off" />
                 <Row>
                    <Col span="24">
                        <FormItem  {...formItemLayout} label="旧密码：">
                        <Input  {...getFieldProps('oldPassword',{ rules: [{required:true,message:'必填'}]})}  type="password" />
                        </FormItem>
                    </Col>
                </Row> 
                <Row>
                    <Col span="24">
                        <FormItem  {...formItemLayout} label="新密码：">
                        <Input {...getFieldProps('newPassword',{ rules: [{required:true,message:'必填'},{ validator: this.validatePwd }]})} type="password"  />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem  {...formItemLayout} label="确认密码：">
                        <Input {...getFieldProps('newPassword2',{ rules: [{required:true,message:'必填'},{ validator: this.validateAgainPwd }]})} type="password"  />
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
