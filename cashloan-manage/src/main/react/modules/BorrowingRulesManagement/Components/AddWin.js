import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Row,
    Col,
    Radio,
    Cascader,
    InputNumber
} from 'antd';
const RadioGroup = Radio.Group;
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const custType = [];
var AddWin = React.createClass({
    getInitialState() {
        return {
            keys: [0],
            selectVlaue: 2,
            keys2: [],
            num: 1,
            canSee: [false]
        };
    },
    handleCancel() {
        this.props.hideModal();
        this.setState({
            keys: [0],
            canSee: [false],
            keys2: [],
            selectVlaue: 2,
        });
        this.state.num = 1;
        this.props.form.resetFields();
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;
        var tableInfo = this.state.tableInfo;
        // //console.log("tableInfo44444444444",tableInfo);
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            var data = values;
            var params = {};
            var req = [];
            var paramsData = {};
            params.borrowType = data.borrowType;
            params.adaptedId = data.adaptedId;
            me.state.keys.forEach(item => {
                let a = item;
                let b = me.state.keys2[a];
                let obj = { rule: {}, configList: [] };
                obj.rule.ruleId = data[a + 'ruleId'];
                obj.rule.ruleSort = data[a + 'ruleSort'];
                me.state.data.rules[b].configList.map((item, index) => {
                    if (data[b + (index + 'configId')] != null && data[b + (index + 'configId')] != undefined && data[b + (index + 'configId')] != '') {
                        obj.configList.push({  configId: data[b + (index + 'configId')], configSort: data[b + (index + 'configSort')] });
                    }
                })
                req.push(obj)
            });
            params.ruleConfigList = req;
            paramsData = { "borrowType": params.borrowType, "adaptedId": params.adaptedId, "ruleConfigList": JSON.stringify(params.ruleConfigList) }
            Utils.ajaxData({
                url: '/modules/manage/borrow/borrowRuleEngine/updateRuleConfig.htm',
                data: paramsData,
                method: "post",
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
        });
    },
    remove(k) {
        const {
      form
    } = this.props;
        let keys = this.state.keys;
        keys = keys.filter((key) => {
            return key !== k;
        });
        this.setState({
            keys: keys
        });
    },
    add() {
        const {
      form
    } = this.props;
        let keys = this.state.keys;
        this.state.canSee.push(false);
        keys = keys.concat(this.state.selectVlaue++);
        this.setState({
            keys: keys
        });
    },
    componentDidMount() {
        var me = this;
        Utils.ajaxData({
            url: '/modules/manage/borrow/borrowRuleEngine/findRuleList.htm',
            callback: (result) => {
                if (result.code == 200) {
                    me.setState({
                        data: result.data
                    });
                }
            }
        });
    },
    change(k, value) {
        this.state.data.rules.map((item, index) => {
            let i = index;
            if (item.rule.id == value) {
                var canSee = this.state.canSee;
                var keys2 = this.state.keys2;
                canSee[k] = true
                keys2[k] = i;
                this.setState({
                    keys2: keys2,
                    canSee: canSee
                })
            }
        })
    },
    getRuleConfig(_this, k) {
        const {
      getFieldProps
    } = this.props.form;
        var ruleConfig = [];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        var state = _this.state;
        {
            (state.data ? state.data.rules[k].configList.map((item, index) => {
                let i = index;
                ruleConfig.push(<Row key={k+''+i}>
                    <Col span="11">
                        <FormItem  {...formItemLayout} label="规则配置:">
                            {item.tableComment + '/' + item.columnComment + item.formula + item.cvalue}
                        </FormItem>
                    </Col>
                    <Col span="13">
                        <FormItem  {...formItemLayout} label="执行优先级:">
                            <InputNumber min={0} max={2147483647}  {...getFieldProps(`${k}${i}configSort`,{initialValue:0}) } />
                        </FormItem>
                    </Col>
                    <Input type="hidden" {...getFieldProps(`${k}${i}configId`, { initialValue: item.id ? item.id : '' }) } />
                </Row>)
            }) : '')
        }
        return ruleConfig;
    },
    render() {
        const {
      getFieldProps
    } = this.props.form;
        getFieldProps('keys', {
            initialValue: [],
        });
        var me = this;

        var props = this.props;
        var state = this.state;

        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
                提 交
      </button>
        ];

        var borrow = [];
        {
            state.data ? state.data.borrows.forEach((item) => {
                borrow.push(<Option key={item.borrowTypeName} value={item.borrowType}>{item.borrowTypeName}</Option>)
            }) : ''
        }
        var adapted = [];
        {
            state.data ? state.data.adaptedList.forEach((item, index) => {
                adapted.push(<Option key={item.adaptedName} value={item.adaptedId}>{item.adaptedName}</Option>)
            }) : ''
        }
        var rule = [];
        {
            state.data ? state.data.rules.forEach((item, index) => {
                rule.push(<Option key={item.rule.name} value={item.rule.id}>{item.rule.name}</Option>)
            }) : ''
        }
        const formItems = state.keys.map((k) => {
            return (<div key={k}>
                <Row>
                    <Col span="11" className='col_h'>
                        <FormItem  {...{
                            labelCol: {
                                span: 5
                            },
                            wrapperCol: {
                                span: 18
                            },
                        }}
                            label="选择规则:">
                            <Select style={{ width: '200px' }}  {...getFieldProps(`${k}ruleId`, { onChange: me.change.bind(me, k), rules: [{ required: true, message: '必选' }] }) }>
                                {rule}
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="11" className='col_h'>
                        <FormItem  {...{
                            labelCol: {
                                span: 10
                            },
                            wrapperCol: {
                                span: 14
                            },
                        }} label="执行优先级:">
                            <InputNumber min={0} max={2147483647}  {...getFieldProps(`${k}ruleSort`,{initialValue:0}) } />
                        </FormItem>
                    </Col>
                    <Col span="2">
                        <Button className="ant-btn" icon="plus" onClick={this.add}></Button>
                        {state.keys.length == 1 ? "" : <Button className="ant-btn" shape="circle" icon="minus" onClick={this.remove.bind(this, k)}></Button>}
                    </Col>
                </Row>
                <div style={{ display: state.canSee[k] ? 'block' : 'none', marginLeft: '30px' }}>
                    {state.keys2[k] !== null && state.keys2[k] !== undefined && state.keys2[k] !== "" ? this.getRuleConfig(this, state.keys2[k]) : ''}
                </div>
            </div>);
        });
        return (
            <Modal maskClosable={false} title={props.title} visible={props.visible} onCancel={this.handleCancel} width={1200} footer={modalBtns} >
                <Form horizontal form={this.props.form} >
                    <Row>
                        <Col span='11' className='col_h'>
                            <FormItem  {...{ labelCol: { span: 8 }, wrapperCol: { span: 16 } }}
                                label="借款类型:">
                                {
                                    <Select style={{ width: '200px' }} {...getFieldProps('borrowType', { rules: [{ required: true, message: '必选' }] }) }>
                                        {borrow}
                                    </Select>
                                }
                            </FormItem>
                        </Col>
                        <Col span='11' className='col_h'>
                            <FormItem  {...{ labelCol: { span: 8 }, wrapperCol: { span: 16 } }}
                                label="规则适用场景:">
                                {

                                    <Select style={{ width: '200px' }} {...getFieldProps('adaptedId', { rules: [{ required: true, message: '必选' }] }) }>
                                        {/*{adapted}*/}
                                        <Option key={"10"}>贷前</Option>
                                    </Select>
                                }
                            </FormItem>
                        </Col>
                    </Row>

                    {/*-----------------------------------------------------------------------------------------------------------------------------------------*/}


                    {formItems}
                </Form>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
