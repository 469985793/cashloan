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
let treeData = {};
var AddDictionary = React.createClass({
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

        var url = "/modules/manage/system/dict/detail/save.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            //console.log("values", values)
            var data = objectAssign({}, {
                form: JSON.stringify(objectAssign({}, values, {}, ))
            }, {
                status: 'create'
            });
            if (title == "修改") {
                var data = objectAssign({}, {
                    form: JSON.stringify(objectAssign({}, values, {}, ))
                }, {
                    status: 'update'
                });
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
                            title: result.msg,
                        });
                    }
                }
            });
        });
    },
    getRoleList() {
        return roleList.map((item, index) => {
            return <Option key={item.id} >{item.name}</Option>
        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
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
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
          <Form horizontal  form={this.props.form}>
                <Input  {...getFieldProps('id',  {initialValue:''})} type="hidden"   />
              <Col span="12">
                <FormItem  {...formItemLayout} label="父节点">
                  <Input disabled={true}  {...getFieldProps('parentId' )} type="text" autoComplete="off" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="字典名称">
                  <Input disabled={!props.canEdit}  {...getFieldProps('itemValue', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                </FormItem> 
               </Col>

              <Col span="12">
                <FormItem  {...formItemLayout} label="字典代码">
                  <Input disabled={!props.canEdit}  {...getFieldProps('itemCode', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                </FormItem> 
              </Col>            
          </Form>
      </Modal>
        );
    }
});
AddDictionary = createForm()(AddDictionary);
export default AddDictionary;