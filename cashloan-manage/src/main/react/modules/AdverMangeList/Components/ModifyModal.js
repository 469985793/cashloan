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
    Message,
    Upload,
    Icon,
    message
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const confirm = Modal.confirm;
const objectAssign = require('object-assign');
const textSty ={
    position: 'absolute',
    left: '-68px',
    color: '#f50',
     opacity: '1'
};

var ModifyModal = React.createClass({

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

    // 验证排序号
    isNumber(rule, value, callback) {
        if (!value) {
            value = '';
        }
        if (!!value && (/^\+?[1-9][0-9]*$/g)) {
            callback();
        } else {
            callback('请输入大于0的整数');
        }
    },

    componentWillReceiveProps(nextProps, nextState) {
        
    },

    componentDidMount() {

    },

   handleOk1() {
    var me = this;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      let filea = me.state.filea || new FormData() ;
      filea.append('sort', values.sort);
      filea.append('state', values.state);
      filea.append('title', values.title);
      filea.append('link', values.link);
      filea.append('id', values.id);

      let req = new XMLHttpRequest();
          req.open('POST', `/modules/manage/adver/update.htm`, true);
          req.onload = function (event) {
            let result = JSON.parse(req.responseText);
            if (req.status === 200) {
              if (result.code == 200) {
                  Modal.success({
                      title:result.msg
                  })
                me.handleCancel();
              } else {
                Modal.error({
                  title: result.msg
                })
              }
            } else {
              Modal.error({
                title: '请求失败'
              })
            }
          };
          req.send(filea);
    })
  }, 
  
    handleFileChange(files) {
        let fileObj = document.getElementById('file').files[0];
        let data = new FormData();
        data.append('bannerFile', fileObj);
        this.setState({
            filea:data,
            fileName: fileObj.name
        })
  },

          
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        let formData = this.props.form.getFieldsValue();
        var modalBtns = [
            <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk1}>
                确定
            </button>,
            <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>
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
             <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={props.title =='查看'?modalBtnsLook:modalBtns} maskClosable={false} >
                <Form horizontal form={this.props.form} >
                 <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" /> 
                 <Input  {...getFieldProps('filea', { initialValue: '' }) } type="hidden" /> 
                      <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="标题:">
                                    <Input type="text"  disabled={!props.canEdit}  {...getFieldProps('title', { rules: [{ required: true, max: 30, message: '必填,且不得超过30个字符' }] }) } />                                 
                                </FormItem>
                            </Col>
                        </Row>  
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="链接地址:">
                                    <Input type="text"  disabled={!props.canEdit}  {...getFieldProps('link', { initialValue: '' }) } />                                 
                                </FormItem>
                            </Col>
                        </Row>      
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="排序号:">
                                    <Input type="text"  disabled={!props.canEdit}  {...getFieldProps('sort', {
                                            rules: [
                                                { required: true, message: '必填' },
                                                { validator: this.isNumber },
                                            ] 
                                         })} style={{ width: 80 }}/>                                 
                                </FormItem>
                            </Col>
                        </Row>                        
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="启用状态:">
                                   <Select disabled={!props.canEdit}   {...getFieldProps('state', { rules: [{ required: true, message: '必填', }] } ) } style={{ width: 80 }} >
                                        <Option value="10"> 启用</Option>
                                        <Option value="20"> 禁用</Option>
                                    </Select>                              
                                </FormItem>
                            </Col>
                        </Row>   
                         <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="图片文件:">
                                    <span id='text' style={textSty}>*</span>
                                    <a className="a-upload">
                                        <Input id="file" onChange={this.handleFileChange} type="file"/>点击这里修改图片
                                    </a>
                                </FormItem>
                            </Col>
                        </Row>        
                </Form>
            </Modal>
        );
    }
});
ModifyModal = createForm()(ModifyModal);
export default ModifyModal;
