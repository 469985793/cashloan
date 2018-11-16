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
/*Utils.ajaxData({
  url: '/modules/system/checkboxoffices.htm',
  method: 'get',
  type: 'json',
  callback: (result) => {
    let data = result.data;
    treeData = data;
  }
});*/

var roleList = [];
Utils.ajaxData({
  url: '/modules/manage/system/role/list.htm',
  method: 'post',
  type: 'json',
  callback: (result) => {
    roleList = result.data;
  }
});

var AddUserWin = React.createClass({
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

    var url = "/modules/manage/system/user/save.htm";
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      //console.log("values", values)
      if(values.officeOver){
        var officeOver=values.officeOver.join(',')
      } else {
        var officeOver=""
      }
      var data = objectAssign({}, {
        user: JSON.stringify(objectAssign({}, values, {
          officeOver: officeOver
        }, {
          roleId: values.roleId.join(',')
        }))
      }, {
        status: 'create'
      });
      if (title == "编辑") {
        var data = objectAssign({}, {
          user: JSON.stringify(objectAssign({}, values, {
            officeOver: officeOver
          }, {
            roleId: values.roleId.join(',')
          }))
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
      <button key="button" className="ant-btn ant-btn-primary" disabled={!props.canEdit}  onClick={this.handleOk}>
          提 交
      </button>
    ];
    if (!props.canEdit) {
        modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
        }
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
            <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="工号:">
                  <Input disabled={!props.canEdit}  {...getFieldProps('jobNumber', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="真实姓名:">
                  <Input disabled={!props.canEdit}  {...getFieldProps('name', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                </FormItem> 
               </Col>
            </Row>
            <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="用户名:">
                  <Input disabled={!props.canEdit}  {...getFieldProps('userName', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="角色:"> 
                  <Select disabled={!props.canEdit}  multiple  {...getFieldProps('roleId', { rules: [{required:true,message:'必填',type:'array'}]})} > 
                    {this.getRoleList()}
                  </Select> 
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
	            <FormItem  {...formItemLayout}  label="手机号码:">
	              <Input disabled={!props.canEdit} {...getFieldProps('mobile')} type="text"/>
	            </FormItem>
	          </Col>
              <Col span="12">
                <FormItem  {...formItemLayout}  label="固定电话:">
                  <Input disabled={!props.canEdit} {...getFieldProps('phone')} type="text" />
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
	            <FormItem  {...formItemLayout}  label="邮箱:">
	              <Input disabled={!props.canEdit} {...getFieldProps('email')} type="text"/>
	            </FormItem>
	          </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout} label="备注:">
                  <Input disabled={!props.canEdit}  {...getFieldProps('remark')} />
                </FormItem> 
              </Col>
            </Row>
          </Form>
      </Modal>
    );
  }
});
AddUserWin = createForm()(AddUserWin);
export default AddUserWin;