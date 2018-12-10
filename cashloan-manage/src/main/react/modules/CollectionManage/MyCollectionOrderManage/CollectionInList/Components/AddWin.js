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

var confirm = Modal.confirm;
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

var AddWin = React.createClass({
    getInitialState() {
        return {
            loading: false,
            canEdit: true,
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
        this.setState({
            promiseState: false
        })
    },
    disabledStartDate(startValue) {
        var today = new Date();
        var loanDay = new Date(this.props.record.repayTime);
        loanDay = DateFormat.formatDate(loanDay).substring(0, 10);
        loanDay = new Date(loanDay);
        return startValue.getTime() > today.getTime() || startValue.getTime() < loanDay.getTime();
    },
    disabledEndDate(endValue) {
        var today = new Date();
        today = new Date(today.getFullYear(),today.getMonth(),today.getDate());
        return endValue.getTime() < today.getTime();
    },
    handleOk() {
        var me = this;
        var url = "/modules/manage/borrow/repay/urge/saveOrderInfo.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            var msg = "增加成功";
            var tips = "您是否确定增加反馈";
            var d = new Date(values.createTime1);
            var youWant = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
            if (values.state1 == 30) {
                // var d = new Date(values.promiseTime);  
                // var youWant2=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
                var youWant2 = typeof (values.promiseTime) == 'undefined' ? "" : DateFormat.formatDate(values.promiseTime);
                var params = {
                    createTime: youWant,
                    dueId: me.props.record.id,
                    promiseTime: youWant2,
                    remark: values.remark,
                    state: values.state1,
                    way: values.way
                }
            } else {
                var params = {
                    createTime: youWant,
                    dueId: me.props.record.id,
                    remark: values.remark,
                    state: values.state1,
                    way: values.way
                }
            }
            //console.log(params)
            confirm({
                title: tips,
                onOk: function () {
                    Utils.ajaxData({
                        url: url,
                        data: params,
                        method: 'post',
                        callback: (result) => {
                            if (result.code == 200) {
                                me.handleCancel();
                                Modal.success({
                                    title: msg,
                                });
                            } else {
                                Modal.error({
                                    title: result.msg,
                                });
                            }
                        }
                    });
                },
                onCancel: function () { }
            });
        })
    },

    componentDidMount() {
        var params = {
            pageSize: 10,
            current: 1,
            code: "URGE_WAY"
        }
        Utils.ajaxData({
            url: '/modules/manage/system/dict/findByTypeCode.htm',
            data: params,
            callback: (result) => {
                var data = result.data;
                var urgeArray = [];
                data.forEach(item => {
                    //console.log(item.itemCode)
                    urgeArray.push(<Option key={item.itemCode}>{item.itemValue}</Option>)
                })
                this.setState({
                    urgeArray: urgeArray
                });
            }
        });
    },

    onResultChange(value) {
        //console.log("r", value)
        var promiseState = false;
        if (value == 30) {
            promiseState = true;
        } else {
            promiseState = false;
        }
        this.setState({
            promiseState: promiseState
        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
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
                                <FormItem  {...formItemLayout} label="手机号码:">
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
                                <FormItem  {...formItemLayout} label="逾期等级:">
                                    <Input type="text" disabled={true} {...getFieldProps('level') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收人:">
                                    <Input type="text" disabled={true} {...getFieldProps('userName') } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="订单状态:">
                                    <Input type="text" disabled={true} {...getFieldProps('state') } />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收时间:">
                                    <DatePicker disabled={false} style={{ width: '100%' }} disabledDate={this.disabledStartDate} onChange={this.onStartChange} type="text" format="yyyy-MM-dd HH:mm:ss" {...getFieldProps('createTime1', { rules: [{ required: true, message: '必填', type: 'date' }] }) } />
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收方式:">
                                    <Select style={{ width: '100%' }} disabled={false} placeholder="请选择"  {...getFieldProps('way', { rules: [{ required: true, message: '必填', type: 'string' }] }) }>
                                        {this.state.urgeArray}
                                    </Select>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="催收结果:">
                                    <Select style={{ width: '100%' }} disabled={false} placeholder="请选择"  {...getFieldProps('state1', { onChange: this.onResultChange, rules: [{ required: true, message: '必填' }] }) }>
                                        <Option value="30">承诺还款</Option>
                                        <Option value="20">催收中</Option>
                                    </Select>
                                </FormItem>
                            </Col>
                            {this.state.promiseState == true ?
                                <Col span="12">
                                    <FormItem  {...formItemLayout} label="承诺还款时间:">
                                        <DatePicker disabled={false} style={{ width: '100%' }} disabledDate={this.disabledEndDate} onChange={this.onEndChange} type="text" format="yyyy-MM-dd" {...getFieldProps('promiseTime', { rules: [{ required: true, message: '必填', type: 'date' }] }) } />
                                    </FormItem>
                                </Col> : null}
                        </Row>
                        <Row>
                            <Col span="24">
                                <FormItem labelCol={{ span: 4 }} wrapperCol={{ span: 16 }} label="催收反馈:">
                                    <Input type="textarea" disabled={false} {...getFieldProps('remark', { rules: [{ max: 500, message: '不能超过500字' }] }) } />
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