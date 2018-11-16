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


var AddScorecard = React.createClass({
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
        var url;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            if(title=="编辑"){
             url = "/modules/manage/cr/card/update.htm";
             var data = {
                   cardName: values.cardName,
                   id: record.id,
                 } 
            }else{
                url = "/modules/manage/cr/card/save.htm";
                 var data = {
                   cardName: values.cardName,
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
                 <Input {...getFieldProps('ruleId') } type="hidden" autoComplete="off" />
            <Row>
              <Col span="24">
                <FormItem  {...formItemLayout} label="评分卡名称：">
                  <Input   {...getFieldProps('cardName',{ rules: [{required:true,message:'必填'}]})} type="text"  />
                </FormItem> 
              </Col>
            </Row>              
          </Form>
      </Modal>
        );
    }
});
AddScorecard = createForm()(AddScorecard);
export default AddScorecard;
