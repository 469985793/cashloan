import React from 'react';
import antd from 'antd';
var Modal = antd.Modal;
var Reflux = require('reflux');
var Actions = require('../actions/Actions');
var reqwest = require('reqwest');
function filefujianChange() {
  var oFiles = document.querySelector("#fileId");
  document.querySelector("#imgPath").value = oFiles.value;
}
export default React.createClass({
  getInitialState() {
    return {
      formData: {},
      status: {
        filePath: {}
      },
      certTypeChildren: []
    };
  },
  handleOk() {
    var me = this;
    var oFiles = document.querySelector("#fileId");
    var formData = new FormData();
    var index = oFiles.value.lastIndexOf(".");
    var type = oFiles.value.substring(index);
    formData.append("upload", oFiles.files[0]);
    if (type == ".xls") {
      reqwest({
        url: '/modules/common/action/BlackCustomerAction/importBlackCustomer.htm',
        method: 'post',
        contentType: false,
        processData: false,
        data: formData,
        success: (result) => {
          if (result.code == 200) {
            Modal.success({
              title: result.msg
            });
            Actions.initStore();
            this.props.hideAddModal();
          } else {
            Modal.error({
              title: result.msg
            });
            me.setState({
              loading: false
            });
          }
        }
      });
    } else {
      Modal.error({
        title: "只能上传.xls文件"
      });
    }
  },
  handleCancel() {
    this.props.hideAddModal();
  },
  componentDidUpdate(prevProps, prevStat) {
    var validation = this.refs.validation;
    setTimeout(() => {
      if (prevProps.visible) {
        validation && validation.validate((valid) => {
          if (!valid) {
            //console.log('error in form');

          } else {
            //console.log('submit');
          }

        });
      }
    }, 400);
  },
  render() {
    var me = this;

    var state = this.state;
    var status = state.status;
    var formData = state.formData;
    var props = this.props;
    var isDisabletwo = (props.title == "新增用户" ? false : true);
    var isDisable = !props.canEdit || (props.title == "修改" ? true : false);
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
        提 交
      </button>
    ];
    return (
      <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel}
             width="800"
             footer={modalBtns}>
        <form className="ant-form-horizontal" ref="myform" method="post">
          <div className="ant-form-item">
            <label className="col-4" htmlFor="upload">文件：</label>
            <div className="col-10"><input id="imgPath" className="ant-input" type="text"/></div>
            <div className="col-5">
              <a href="javascript:;" className="a-upload ant-btn-primary">
                <input id="fileId" type="file" accept=".xls" name="upload" onChange={filefujianChange}/>浏览
              </a>
            </div>
          </div>
        </form>
      </Modal>
    )
  }
});