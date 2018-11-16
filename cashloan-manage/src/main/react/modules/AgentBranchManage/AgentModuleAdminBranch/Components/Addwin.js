import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Row,
    Col,
    message,

} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
var AddWin = React.createClass({
    getInitialState() {
        return {
            checked: true,
            disabled: false,
            data: "",
            timeString: "",
            record: ""
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    handleOk() {
        var me = this;
        var re = /^[0-9]*[1-9][0-9]*$/;
        var t1,t2;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            if (!re.test(values.rate)) {
                clearTimeout(t1);
                me.refs.span.style.display = 'block';
                t1 = setTimeout(function () {
                    me.refs.span.style.display = 'none';
                }, 3000)
                return;
            } else if (values.rate <= 5 || values.rate >= 20) {
                clearTimeout(t2);
                me.refs.span1.style.display = 'block';
                t2 = setTimeout(function () {
                    me.refs.span1.style.display = 'none';
                }, 3000)
                return;
            }
            Utils.ajaxData({
                url: '/modules/manage/profit/saveAgent.htm',
                method: 'post',
                data: {
                    inviteId: me.props.record.inviteId,
                    level: 2,
                    rate: values.rate,
                    userId: me.props.record.userId
                },
                callback: (result) => {
                    if (result.code == 200) {
                        me.handleCancel();
                    };
                    let resType = result.code == 200 ? 'success' : 'warning';
                    Modal[resType]({
                        title: result.msg,
                    });
                }
            });

        })
    },
    componentWillReceiveProps(nextProps) {
        this.setState({
            record: nextProps.record
        })
    },



    render() {
        const {
      getFieldProps
    } = this.props.form;
        var props = this.props;
        var state = this.state;


        const formItemLayout = {
            labelCol: {
                span: 9
            },
            wrapperCol: {
                span: 15
            },
        };
        var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
                提 交
            </Button>
        ];
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
                <Form horizontal form={this.props.form}>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="分润率:">
                                <Input type="text" {...getFieldProps('rate', { rules: [{ required: true, message: '必填' }] }) } />
                                <span ref='span' style={{ display: 'none', color: 'red' }}>必须填纯数字</span>
                                <span ref='span1' style={{ display: 'none', color: 'red' }}>必须范围在6到19之间</span>
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