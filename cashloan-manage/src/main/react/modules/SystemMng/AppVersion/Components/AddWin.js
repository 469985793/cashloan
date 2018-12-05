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
let treeData = [];

var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {}
        };
    },
    componentWillReceiveProps(nextProps) {
        this.fetch();
        this.setState({
          record: nextProps.record
        });
      },
    componentDidMount(){
        this.fetch();
    },

    fetch(params = {}) {
        this.setState({
          loading: true
        });
        if (!params.pageSize) {
          var params = {};
          params = {
            pageSize: 10,
            current: 1,
            id:this.state.record?this.state.record.id:''
          }
        }
        Utils.ajaxData({
          url: '/modules/manage/user/appVersion/find.htm',
          data: params,
          method: 'post',
          callback: (result) => {
              console.log(result);
            // const pagination = this.state.pagination;
            // pagination.current = params.current;
            // pagination.total = result.page.total;
            // if (!pagination.current) {
            //   pagination.current = 1
            // };
            this.setState({
              loading: false,
              data: result.data,
              // pagination,
            });
          }
        });
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
        //console.log(record)
        
        var url = "/modules/manage/user/appVersion/save.htm";
        this.props.form.validateFields((errors, values) => {
            console.log(errors);
                console.log(values);
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            
            var trueText = values.versionText.replace(/\r\n/g, '<br/>').replace(/\n/g, '<br/>').replace(/\s/g, ' ');
        console.log(this.state.record);
            Utils.ajaxData({
                url: '/modules/manage/user/appVersion/save.htm',
                method: 'post',
                data: {
                    appCode: values.appCode,
                    appName: values.appName,
                    appType: values.appType,
                    appClassification:values.appClassification,
                    versionCode:values.versionCode,
                    versionName:values.versionName,
                    versionText:trueText,
                    forceFlag:values.forceFlag,
                    downUrl:values.downUrl,
                    googleDownUrl:values.googleDownUrl,
                    spreadUrl:values.spreadUrl,
                    publishUid:values.publishUid,
                    status:values.status,
                    id:this.state.record.id
                },
                callback: (result) => {
                    console.log(result);
                    Modal.success({
                        title: result.msg,
                    });
                    me.handleCancel();
                }
            })

        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        // props.canEdit = true;
        // console.log(props.canEdit);
        if(state.data){
            var state_data = state.data;
        }

        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
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
        var optionItem = [];
        for (let i = 0; i < props.condition.length; i++) {
            optionItem.push(<Option value={props.condition[i].id}>{props.condition[i].userName}</Option>)
        }
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} >
                <Form horizontal form={this.props.form}>
                    <Input  {...getFieldProps('id', { initialValue: '' })} type="hidden" />
                    {/*app编号*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="App code：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('appCode',  { initialValue: state_data ? state_data.appCode : '' },{ rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*app名称*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="App name:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('appName', { initialValue: state_data ? state_data.appName: '' },{ rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*app类型*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="App type:">
                                <Select disabled={!props.canEdit}  {...getFieldProps('appType', { initialValue: state_data ? state_data.appType+'': '' }, { rules: [{ required: true, message: '必填' }] })} >
                                    <Option value='10'>Android</Option>
                                    <Option value='11'>Android Pad</Option>
                                    <Option value='20'>IOS</Option>
                                    <Option value='21'>IOS Pad</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    {/*app类别*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="App classification:">
                                <Select disabled={!props.canEdit}  {...getFieldProps('appClassification', { initialValue: state_data ? state_data.appClassification+'': '' }, { rules: [{ required: true, message: '必填' }] })} >
                                    <Option value='1'>Jumbopesa</Option>
                                    <Option value='2'>Pesaplus</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    {/*版本号*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Version code:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('versionCode', { initialValue: state_data ? state_data.versionCode: '' },  { rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*版本名称*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Version name:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('versionName', { initialValue: state_data ? state_data.versionName: '' }, { rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*是否强制变更*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Force flag:">
                                <Select disabled={!props.canEdit}  {...getFieldProps('forceFlag', { initialValue: state_data ? state_data.forceFlag+'': '' }, { rules: [{ required: true, message: '必填' }] })} >
                                    <Option value='0'>不强制</Option>
                                    <Option value='1'>强制</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    {/*APP下载地址*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Down url:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('downUrl', { initialValue: state_data ? state_data.downUrl: '' }, { rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*Google play下载地址*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Google down url:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('googleDownUrl', { initialValue: state_data ? state_data.googleDownUrl: '' }, { rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*推广主页*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Spread url:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('spreadUrl',  { initialValue: state_data ? state_data.spreadUrl: '' },{ rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*发布人id*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Publish uid:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('publishUid',  { initialValue: state_data ? state_data.publishUid: '' },{ rules: [{ required: true, message: '不能为空,且不超过百位', max: 100 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*发布时间*/}
                    {/* <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Publish time:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('publishTime', { rules: [{ required: true, message: '不能为空,且不超过十位', max: 10 }] })} type="text" />
                            </FormItem>
                        </Col>
                    </Row> */}
                    {/* http://www.jumbopesa.co/img/851e5afc.contact-us.jpg */}
                    {/*版本描述*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Version text:">
                                <Input disabled={!props.canEdit}  {...getFieldProps('versionText', { initialValue: state_data ? state_data.versionText: '' }, { rules: [{ required: true, message: '不能为空,且不超过千位', max: 1000 }] })} type="textarea" />
                            </FormItem>
                        </Col>
                    </Row>
                    {/*状态*/}
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="Status:">
                                <Select disabled={!props.canEdit}  {...getFieldProps('status', { initialValue: state_data ? state_data.status: '' }, { rules: [{ required: true, message: '必填' }] })} >
                                    <Option value='1'>正常</Option>
                                    <Option value='-1'>删除</Option>
                                </Select>
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
