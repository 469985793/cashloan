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
    Radio
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RadioGroup = Radio.Group;
const objectAssign = require('object-assign');


var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {},
            value: '10',
            value1: '',
            value2: '',
            first: true
        };
    },
    componentWillReceiveProps(nextProps, nextState) {
        if (this.state.first && nextProps.title && nextProps.title == '编辑') {
            this.setState({
                ThirdPatryList: nextProps.ThirdPatryList,
                InterfaceList: nextProps.InterfaceList,
                PortList1:nextProps.PortListInit,
                PortList: nextProps.PortList,
                businessEdit: nextProps.businessEdit,
                timeState: nextProps.timeState,
                value: nextProps.record.type,
                value1: nextProps.record.tppId,
                value2: nextProps.record.businessId,
                first: false,
            })
            if(nextProps.record.type == '20'){
                this.setState({
                    PortList1:nextProps.PortListInit,
                })
            }
        }else{
            this.setState({
                ThirdPatryList: nextProps.ThirdPatryList,
                InterfaceList: nextProps.InterfaceList,
                PortList: nextProps.PortList,
                businessEdit: nextProps.businessEdit,
                timeState: nextProps.timeState,
                value: nextProps.value ? nextProps.value : '10',
            })
        }
    },
    handleCancel() {
        this.state.first = true;
        this.state.value1 = '';
        this.state.value2 = '';
        this.state.PortList = [];
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
                console.log('Errors in form!!!');
                return;
            }
            if (title == "新增") {
                url = "/modules/manage/creditdata/sceneBusiness/save.htm";
                var data = values;
                data.type = me.state.value;
                data.tppName = me.state.value1;
                data.businessId = me.state.value2;
            } else if (title == "编辑") {
                url = "/modules/manage/creditdata/sceneBusiness/update.htm";
                var data = values;
                data.type = me.state.value;
                data.tppName = me.state.value1;
                data.businessId = me.state.value2;
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
                            title: result.msg
                        });
                    }
                }
            });
        })
    },
    componentDidMount() {
        var SceneList = [];
        var SceneList1 = [];
        //场景接口
        Utils.ajaxData({
            url: '/modules/manage/system/dict/findByTypeCode.htm',
            data: {
                pageSize: 10,
                current: 1,
                code: "THIRD_DATA_SCENE"
            },
            callback: (result) => {
                var data = result.data;
                data.forEach(item => {
                    SceneList.push(<Option key={item.itemCode}>{item.itemValue}</Option>)
                })
                this.setState({
                    data: result.data,
                    SceneList: SceneList
                });
            }
        });
        Utils.ajaxData({
            url: '/modules/manage/rcdata/statistics/listAll.htm',
            callback: (result) => {
                var data = result.data;
                data.forEach(item => {
                    SceneList1.push(<Option key={item.name} value={item.id} >{item.name}</Option>)
                })
                this.setState({
                    SceneList1: SceneList1
                });
            }
        });
    },
    fetch(value){
        var SceneList1 = [];
        Utils.ajaxData({
            url: '/modules/manage/rcdata/databusiness/listByDataId.htm',
            data: {
                statisticsId: value
            },
            callback: (result) => {
                var data = result.data;
                data.forEach(item => {
                    SceneList1.push(<Option key={item.name} value={String(item.id)}>{item.name}</Option>)
                })
                this.setState({
                    PortList1: SceneList1
                });
            }
        });
    },
    onChange(value) {
        var me = this;
        this.props.portChange(value, me.state.value,me.state.value2);
        if(this.state.value == '20'){
            this.props.portListInit(value);
        }
        
        this.setState({
            value1: value,
            value2: '',
        });
    },
    TimeChange(value) {
        this.props.onTimeChange(value);
    },
    onChange2(value) {
        this.setState({
            value2: value,
        });
    },
    onChange1(value) {
        this.setState({
            value: value,
            value1: '',
            value2: '',
            PortList: [],
            PortList1:[]
        });
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
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false}>
                <Form horizontal form={this.props.form}>
                    <Input {...getFieldProps('id') } type="hidden" autoComplete="off" />
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="场景:">
                                <Select disabled={!props.canEdit} {...getFieldProps('scene', { rules: [{ required: true, message: '必填' }] }) } >
                                    {/*{this.state.SceneList}*/}
                                    <Option key={"10"}>贷前</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="接口选择:">
                                <Select onChange={this.onChange1} value={this.state.value}>
                                    <Option key={'10'}>第三方</Option>
                                    <Option key={'20'}>系统</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label={this.state.value == '10' ? '第三方' : '系统'}>
                                <Select disabled={!props.canEdit} value={this.state.value1} onChange={this.onChange} >
                                    {this.state.value == '10' ? this.state.ThirdPatryList : this.state.SceneList1}
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="接口:">
                                <Select disabled={!this.state.value1} value={this.state.value2} onChange={this.onChange2} >
                                    {this.state.value == '10' ? this.state.PortList : this.props.PortListInit}
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="数据获取方式:">
                                <Select disabled={!props.canEdit} {...getFieldProps('getWay', { onChange: this.TimeChange, rules: [{ required: true, message: '必填' }] }) } >
                                    <Option key="00">获取一次</Option>
                                    <Option key="10">每次获取</Option>
                                    <Option key="20">固定周期获取</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    {state.timeState == true ?
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="间隔时间（天）:">
                                    <Input disabled={!props.canEdit}  {...getFieldProps('period', { rules: [{ required: true, message: '必填' }] }) } type="text" />
                                </FormItem>
                            </Col>
                        </Row> : null}


                </Form>
            </Modal >
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;