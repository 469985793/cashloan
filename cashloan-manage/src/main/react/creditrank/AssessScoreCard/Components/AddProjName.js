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


var AddProjName = React.createClass({
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
        var cardRecord=JSON.parse(localStorage.record);
        var title = props.title;
        var url;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            if(title=='新增评分项目'){
                url = "/modules/manage/cr/item/save.htm";
                var data = {
                    cardId: cardRecord.id,
                    cardName: cardRecord.cardName,
                    itemName: values.itemName
                }
            }else{
                url = "/modules/manage/cr/item/update.htm";
                var data = {
                    cardId: record.cardId,
                    id: record.id,
                    cardName: record.cardName,
                    itemName: values.itemName
                }
            }
             
            Utils.ajaxData({
                url: url,
                data: data,
                callback: (result) => {
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {
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
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500"  footer={modalBtns} >
                <Form horizontal  form={this.props.form}>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="项目名称：">
                            <Input   {...getFieldProps('itemName',{ rules: [{required:true,message:'必填'}]})} type="text"  />
                            </FormItem> 
                        </Col>    
                    </Row>             
                </Form>
      </Modal>
        );
    }
});
AddProjName = createForm()(AddProjName);
export default AddProjName;
