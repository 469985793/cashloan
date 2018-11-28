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
    DatePicker 
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

var AddWin = React.createClass({
    getInitialState() {
        return {
            loading: false,
            record:"",
            data:"",
            canEdit:true,
            dataRecord:[],
        };
    },
  handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
          this.setState({
            canEdit:true,
            dataRecord:"",  
        })  
    },
    componentWillReceiveProps(nextProps, nextState) {
        this.setState({
            dataRecord:nextProps.dataRecord
        })
  },

  handleOk() {
        var me = this;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
          if(this.props.title=="新增"){       
            var url= '/modules/manage/promotion/channel/save.htm';
            var params={
                linker: values.linker,
                code:values.code,
                phone: values.phone,
                name: values.name
            };
          }   
         if(this.props.title=="编辑"){
            var url= '/modules/manage/promotion/channel/update.htm';
            var params={
                id: me.props.record.id,
                linker: values.linker,
                code:values.code,
                phone: values.phone,
                name: values.name
            };
         }  
            Utils.ajaxData({
              url: url,
              data:params,
              method: 'post',
              callback: (result) => {
                 if(result.code=="200"){
                    Modal.success({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }else{
                     Modal.error({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }
            }
          });     
        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading}  onClick={this.handleOk}>
                提 交
            </Button>
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
             <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
                <Form horizontal form={this.props.form} style={{ marginTop: "20px" }}>
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="渠道编码:">
                                    <Input type="text" placeholder="请输入渠道编码" disabled={false} {...getFieldProps('code',{rules:[{ required: true, message: '未填或长度过长', max: '16' }]})} />                                 
                                </FormItem>
                            </Col>
                            </Row>
                            <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="渠道名称:">
                                    <Input type="text" placeholder="请输入渠道名称" disabled={!props.canEdit} {...getFieldProps('name',{rules:[{ required: true, message: '未填或长度过长', max: '16' }]})} />                                 
                                </FormItem>
                            </Col>
                            </Row>
                            <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="联系人:">
                                    <Input type="text" placeholder="请输入联系人" disabled={!props.canEdit} {...getFieldProps('linker',{rules:[{ required: true, message: '未填或长度过长', max: '16' }]})} />                                 
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>                         
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="联系方式:">
                                    <Input type="text" placeholder="请输入联系方式" disabled={false} {...getFieldProps('phone',{rules:[{ required: true, message: '未填或长度过长', max: '16' }]})} />                                 
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
