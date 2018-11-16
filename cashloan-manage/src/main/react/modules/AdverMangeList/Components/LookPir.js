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
const img = {
  textAlign: 'center',
}

var Audit = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {},
        };
    },


    componentWillReceiveProps(nextProps, nextState) {   
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
  
    componentDidMount() {
    },

    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var viewPath = this.props.record.viewPath
       
        var modalBtns = [
    
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
        ];
        var modalBtnsLook = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 6
            },
            wrapperCol: {
                span: 16
            },
        };
      
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtnsLook} maskClosable={false}>
                <Form horizontal form={this.props.form}>
                    <div style={img}>
                        {viewPath ? <a href={viewPath} target="_blank"><img src={viewPath} /></a> : <span>暂无</span>}
                    </div>
                </Form>
            </Modal >
        );
    }
});
Audit = createForm()(Audit);
export default Audit;
