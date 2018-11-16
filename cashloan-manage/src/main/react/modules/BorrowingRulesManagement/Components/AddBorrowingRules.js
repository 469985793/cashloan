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
var AddBorrowingRules = React.createClass({
    getInitialState() {
        return {
            keys: [0],
            selectVlaue: 2,
            num: 1,
            canSee: [false],
            Id: []
        };
    },
    handleCancel() {
        this.props.hideModal();
        this.setState({
            keys: [0],
            canSee: [false],
            selectVlaue: 2,
            Id: []
        });
        this.formData = {};
        this.state.num = 1;
        this.props.form.resetFields();
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var state = me.state;
        var record = props.record;
        var title = props.title;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            var data = values;
            var params = {};
            var req = [];
            var paramsData = {};
            params.id = data.id;
            params.borrowType = data.borrowType;
            params.adaptedId = data.adaptedId;
            let j;
            data.keys.forEach(item => {
                let a = item;
                let obj = { rule: {}, configList: [] };
                obj.rule.ruleId = data[a + 'ruleId'];
                obj.rule.ruleSort = data[a + 'ruleSort'];
                for (var i = 0; i < state.data.rules.length; i++) {
                    if (state.data.rules[i].rule.id == data[a + 'ruleId']) {
                        j = i;
                    }
                }
                state.data.rules[j].configList.forEach((item, index) => {
                    if (data[j + (index + 'configId')] != null && data[j + (index + 'configId')] != undefined && data[j + (index + 'configId')] != '') {
                        obj.configList.push({ id: data[j + (index + 'id')], configId: data[j + (index + 'configId')], configSort: data[j + (index + 'configSort')] });
                    }

                })
                req.push(obj)
            });
            params.ruleConfigList = req;
            paramsData = { "id": params.id, "borrowType": params.borrowType, "adaptedId": params.adaptedId, "ruleConfigList": JSON.stringify(params.ruleConfigList) }
            Utils.ajaxData({
                url: '/modules/manage/borrow/borrowRuleEngine/updateRuleConfig.htm',
                data: paramsData,
                method: "post",
                callback: (result) => {
                    if (result.code == 200) {
                        me.fetch(state.record);
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
        let keys = form.getFieldValue('keys');
        keys = keys.filter((key) => {
            return key !== k;
        });
        this.state.keys = keys;
        form.setFieldsValue({
            keys,
        });
    },
    add() {
        const {
      form
    } = this.props;
        let keys = form.getFieldValue('keys');
        this.state.canSee.push(false);
        keys = keys.concat(this.state.selectVlaue++);
        this.state.keys = keys;
        form.setFieldsValue({
            keys
        });
    },
    formData: {},
    transformeData(data) {
        var formData = {};
        var keys = [];
        var canSee = [];
        var Id = [];
        data.forEach((item, index) => {
            let k = index;
            keys.push(k);
            canSee.push(true);
            Id.push(item.rule.ruleId)
            formData[`${k}ruleId`] = item.rule.ruleId;
            formData[`${k}ruleSort`] = item.rule.ruleSort;
            formData[`${k}children`] = item.configList;
        })

        formData["keys"] = keys;
        formData["canSee"] = canSee;
        formData["Id"] = Id;
        return formData;
    },
    fetch(record) {
        Utils.ajaxData({
            url: '/modules/manage/borrow/borrowRuleEngine/detail.htm',
            data: {
                id: record.id
            },
            callback: (result) => {
                var data = result.data.borrowRuleConfig;
                var data2 = result.data.borrowRule
                this.formData = this.transformeData(data);
                this.props.form.setFieldsValue({
                    keys: this.formData.keys,

                });

                this.setState({
                    selectVlaue: this.formData.keys.length,
                    keys: this.formData.keys,
                    canSee: this.formData.canSee,
                    data2: data2,
                    Id: this.formData.Id
                });
            }
        })
    },
    componentWillReceiveProps(nextProps) {
        if (nextProps.visible) {
            if (this.state.num == 1) {
                this.setState({
                    num: 2,
                    record: nextProps.record
                })
                this.fetch(nextProps.record)
            }

        };
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
        var data = this.state.data;
        for (var i = 0; i < data.rules.length; i++) {
            if (data.rules[i].rule.id == value) {
                var canSee = this.state.canSee;
                var Id = this.state.Id;
                Id[k] = value;
                canSee[k] = true
                this.setState({
                    canSee: canSee,
                    Id: Id
                })
            }
        }
    },
    getRuleConfig(k) {
        const {
                    getFieldProps
                } = this.props.form;
        var ruleConfig = [];
        var props = this.props;
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        let j;
        var sort = [];
        var ids = [];
        if(this.state.data){
        	for (var i = 0; i < this.state.data.rules.length; i++) {
                if (this.state.data.rules[i].rule.id == this.state.Id[k]) {
                    j = i;
                }
            }
        }
        
        if (this.formData[`${k}ruleId`] && this.formData[`${k}ruleId`] == this.state.Id[k]) {
            this.formData[`${k}children`].forEach((item) => {
                sort.push(item.configSort);
                ids.push(item.id);
            })
        }
        {
            j != null && j != undefined && j != '' || j == 0 ? this.state.data.rules[j].configList.map((item, index) => {
                let i = index;
                ruleConfig.push(<Row key={j + '' + i}>
                    <Col span="11">
                        <FormItem  {...formItemLayout} label="规则配置:">
                            {item.tableComment + '/' + item.columnComment + item.formula + item.cvalue}
                        </FormItem>
                    </Col>
                    <Col span="13">
                        <FormItem  {...formItemLayout} label="执行优先级:">
                            <InputNumber min={0} max={2147483647} disabled={!props.canEdit} {...getFieldProps(`${j}${i}configSort`, { initialValue: sort[0] ? sort[i] : 0 }) } />
                        </FormItem>
                    </Col>
                    <Input type="hidden" {...getFieldProps(`${j}${i}id`, { initialValue: ids[0] ? ids[i] : '0' }) } />
                    <Input type="hidden" {...getFieldProps(`${j}${i}configId`, { initialValue: item.id ? item.id : '' }) } />
                </Row>)
            }) : ''
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
                保存
      </button>
        ];
        var modalBtns2 = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>
        ]
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
                            <Select disabled={!props.canEdit} style={{ width: '200px' }}  {...getFieldProps(`${k}ruleId`, { initialValue: this.formData && this.formData[`${k}ruleId`] ? this.formData[`${k}ruleId`] : '', onChange: me.change.bind(me, k), rules: [{ required: true, message: '必选' }] }) }>
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
                            <InputNumber min={0} max={2147483647} disabled={!props.canEdit}  {...getFieldProps(`${k}ruleSort`, { initialValue: this.formData && this.formData[`${k}ruleSort`] ? this.formData[`${k}ruleSort`] : '0' }) }  />
                        </FormItem>
                    </Col>
                    {props.title == '编辑' ? <Col span="2">
                        <Button className="ant-btn" icon="plus" onClick={this.add}></Button>
                        {state.keys.length == 1 ? "" : <Button className="ant-btn" shape="circle" icon="minus" onClick={this.remove.bind(this, k)}></Button>}
                    </Col> : <Col span="2"></Col>}
                </Row>
                <div style={{ display: state.canSee[k] ? 'block' : 'none', marginLeft: '30px' }}>
                    {this.getRuleConfig(k)}
                </div>
            </div>);
        });
        return (
            <Modal maskClosable={false} title={props.title} visible={props.visible} onCancel={this.handleCancel} width={1200} footer={props.title == '编辑' ? modalBtns : modalBtns2} >
                <Form horizontal form={this.props.form} >
                    <Input type="hidden" {...getFieldProps('id', { initialValue: state.data2 ? state.data2.id : '' }) } />
                    <Input type="hidden" {...getFieldProps('borrowType', { initialValue: state.data2 ? state.data2.borrowType : '' }) } />
                    <Input type="hidden" {...getFieldProps('adaptedId', { initialValue: state.data2 ? state.data2.adaptedId : '' }) } />
                    <Row>
                        <Col span='11' className='col_h'>
                            <FormItem  {...{ labelCol: { span: 8 }, wrapperCol: { span: 16 } }}
                                label="借款类型:">
                                {
                                    state.data2 ? state.data2.borrowTypeName : ''
                                }
                            </FormItem>
                        </Col>
                        <Col span='11' className='col_h'>
                            <FormItem  {...{ labelCol: { span: 8 }, wrapperCol: { span: 16 } }}
                                label="规则适用场景:">
                                {
                                    state.data2 ? state.data2.adaptedName : ''
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
AddBorrowingRules = createForm()(AddBorrowingRules);
export default AddBorrowingRules;
