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
const TreeNode = Tree.TreeNode;
const Option = Select.Option;

let treeData = [];
Utils.ajaxData({
  url: '/modules/manage/system/menu/combo/find.htm',
  method: 'post',
  type: 'json',
  callback: (result) => {
    let data = result.data;
    treeData = data;
  }
});

var AddMenu = React.createClass({
  getInitialState() {
    return {
      visible: false,
      status: {

      },
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
    //console.log(record)
    var url = "/modules/manage/system/menu/update.htm";
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      var data = values;
      let status = "create";
      if (title == "修改") {
        status = "update";
      }
      Utils.ajaxData({
        url: url,
        data: {
          menu: JSON.stringify(data),
          status: status

        },
        callback: (result) => {
          if (result.code == 200) {
            Modal.success({
              title: result.msg,
              onOk: () => {
                me.props.form.resetFields();
                props.hideModal();
              }
            });
          }
        }
      });
    });
  },
  componentDidMount() {
    var record = {};
    if (this.props.record) {
      var record = this.props.record;
    }
    this.props.form.setFieldsValue(record);
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    var formData = state.formData;
    treeData = this.props.officeData;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          确 定
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
                <FormItem  {...formItemLayout} label="脚本名称：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('scriptid', { rules: [{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="菜单名称：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('text', { rules: [{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
               </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="父节点：">
                <div  className="clearfix">
                  <TreeSelect  dropdownStyle={{ maxHeight: 400, overflow: 'auto' }} treeData={treeData} 
                  {...getFieldProps('parentId')}
                  placeholder="请选择" treeDefaultExpandAll />
                  </div>
                </FormItem> 
              </Col>                
              <Col span="12">
                <FormItem  {...formItemLayout} label="图标：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('iconCls')} type="text"  />
                </FormItem> 
              </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout} label="菜单说明：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('remark', { rules: [{required:true,message:'必填'}]})} type="text" /> 
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="排序：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('sort', { rules: [{required:true,message:'必填'}]})} type="text" /> 
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout}  label="是否删除：">
                  <Select id="select" size="large"  disabled={!props.canEdit} {...getFieldProps('isDelete', { initialValue: 0 })} >
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                  </Select> 
                </FormItem>
              </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout}  label="是否菜单：">
                  <Select id="select" size="large" disabled={!props.canEdit} {...getFieldProps('isMenu', { initialValue: 1 })} >
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                  </Select> 
                </FormItem>
              </Col> 
          </Form>
      </Modal>
    );
  }
});
AddMenu = createForm()(AddMenu);
export default AddMenu;