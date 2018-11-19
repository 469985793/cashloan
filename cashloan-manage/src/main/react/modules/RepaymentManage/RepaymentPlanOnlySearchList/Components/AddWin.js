import React from 'react';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  Row,
  Col,
  Select,
  Checkbox,
  Radio,
  message,
  DatePicker,
  Upload,
  Popover

} from 'antd';
import './button.css';
const CheckboxGroup = Checkbox.Group
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const confirm = Modal.confirm;
const objectAssign = require('object-assign');
const Dragger = Upload.Dragger;
var AddWin = React.createClass({
  getInitialState() {
    return {
      checked: true,
      disabled: false,
      data: "",
      timeString: "",
      record: "",
      fileName: ''
    };
  },
  handleCancel() {
    this.state.fileName = '';
    this.props.form.resetFields();
    this.props.hideModal();
  },
  handleOk() {
    var me = this;
    let filea = '';
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      filea = me.state.filea;
      filea.append('type', values.type);
      confirm({
        title: 'Are you sure to upload?',//你是否确定上传
        onOk: function () {
          let req = new XMLHttpRequest();
          req.open('POST', `/modules/manage/borrow/repay/fileBatchRepay.htm`, true);
          req.onload = function (event) {
            let result = JSON.parse(req.responseText);
            if (req.status === 200) {
              if (result.code == 200) {
                window.open(result.data);
                me.handleCancel();
              } else {
                Modal.error({
                  title: result.msg
                })
              }
            } else {
              Modal.error({
                title: 'Request failed'//请求失败
              })
            }
          };
          req.send(filea);
        },
        onCancel: function(){}
      })
      // console.log(me.state.filea);
      // //console.log("00000000000",values)
      //  Utils.sendAjax({
      //   url: "/modules/manage/borrow/repay/fileBatchRepay.htm",
      //   data:{
      //     type: values.type,
      //     repayFile:me.state.filea
      //   },
      //   contentType:'multipart/form-data',
      //   callback: (result) => {
      //     if (result.code == 200) {
      //       this.handleCancel();
      //     };
      //   let resType = result.code == 200 ? 'success' : 'warning';
      //    Modal[resType]({
      //         title: result.msg,
      //       });
      //   }
      // });

    })
  },
  componentWillReceiveProps(nextProps) {
    this.setState({
      record: nextProps.record
    })
  },
  handleFileChange(files) {
    let fileObj = document.getElementById('file').files[0];
    let data = new FormData();
    data.append('repayFile', fileObj);
    this.setState({
      filea: data,
      fileName: fileObj.name
    })
  },



  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;


    const formItemLayout = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayoutone = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 6
      },
      wrapperCol: {
        span: 18
      },
    };
    var modalBtns = [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>{/*返 回*/}Back</Button>,
      <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
        {/*提 交*/}Submit
            </Button>
    ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
        <Form horizontal form={this.props.form}>
          <Row>
            <Col span="20">
              <FormItem  {...formItemLayout} label="Repayment:">
                <Select style={{ width: 100 }} {...getFieldProps('type', { initialValue: 'alpay' }) }>{/*还款方式*/}
                  <Option value="bank">{/*银行卡账单*/}Bank card bill</Option>
                  <Option value="alpay">{/*支付宝账单*/}Alipay bill</Option>
                </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="20">
              <FormItem  {...formItemLayout} label="File:">{/*文件*/}
                <a href="#" className="a-upload"><input id="file" onChange={this.handleFileChange} type="file" />{/*点击这里上传文件*/}Click here to upload a file</a>
                <div title={state.fileName?state.fileName:''} id="file1">{state.fileName?(state.fileName.length > 20 ? state.fileName.substring(0,20)+'...' : state.fileName) :''}</div>
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