import React from 'react';
import { Icon, Modal, Tooltip } from "antd";
var Reflux = require('reflux');
var reqwest = require('reqwest');
var AppActions = require('./actions/AppActions');
var UserMessageStore = require('./stores/UserMessageStore');
var MenuDataStore = require('./stores/MenuDataStore');

import AddWin from '../forgetPwd'
const Top = React.createClass({
  mixins: [
    Reflux.connect(UserMessageStore, "userMessage"),
    Reflux.connect(MenuDataStore,"menuData")
  ],
  getInitialState() {
    return {
      userMessage: {},
      formData: {},
      menuData:{}
    };
  },
  toggleMenu() {
    this.props.toggleMenu();
  },
  handleClick(e) {
    reqwest({
      url: '/system/user/switch/role.htm',
      method: 'get',
      data: {
        role: e
      },
      type: 'json',
      success: (result) => {
        location.reload();
      }
    });
  },
  signOut(e) {
    let req = new XMLHttpRequest();
    req.open('POST', `/system/user/logout.htm`, true);
    req.onload = function (event) {
      let result = JSON.parse(req.responseText);
      if (req.status === 200) {
        if (result.code == 200) {
          localStorage.clear();
          location.reload();
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
    req.send();
  },
  showModal() {
    this.setState({
      visible: true,
      title: "修改密码",
    });
  },
  handleOk() {
    var me = this;
    reqwest({
      url: '/modules/system/modifyPassword.htm',
      method: 'post',
      data: {
        user: JSON.stringify(me.state.formData)
      },
      type: 'json',
      success: (result) => {
        if (result.code == 200) {
          Modal.success({
            title: result.msg,
            onOk: function () {
              me.setState({
                visible: false
              });
              window.location.href = "/j_spring_security_logout";
            }
          });
        }
        else {
          Modal.error({
            title: result.msg
          });
        }
      }
    });
  },
  handleCancel() {
    this.setState({
      visible: false
    });
  },
  changeValue(e) {
    var newValue = this.state.formData;
    var name = e.target.name;
    newValue[name] = e.target.value;
    this.setState({ formData: newValue });
  },
  componentDidMount() {
    AppActions.initUserMessageStore();
    
  },
  hideModal() {
    this.setState({
      visible: false,
      title: "",
    });
  },
  render() {
    var fold = this.props.fold;
    var me = this;
    var formData = this.state.formData;
    this.props.setRoleName(this.state.userMessage.rolename);
    var modalBtns = [
      <button key="back" type="button" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" type="button" className="ant-btn ant-btn-primary" loading={this.state.loading}
        onClick={this.handleOk}>
        提 交
      </button>
    ];

    var userMessage = this.state.userMessage;
    var roleList;
    if (userMessage.roleList) {
      roleList = userMessage.roleList.map((role) => {
        return <a key={role.id} onClick={this.handleClick.bind(this, role.id)}>{role.name}</a>
      });
    }
    var toggleRole = (<div> {roleList} </div>);

    return (
      <div className="main-header">
        <div className="logo">
          <span className="logo-mini"></span>
          <span className="logo-lg"></span>
        </div>
        <div className="navbar navbar-static-top">
          <a href="#" className="sidebar-toggle" onClick={this.toggleMenu}>
            <Icon type={`${fold ? 'menu-unfold' : 'menu-fold'}`} /><span className="system-name">Background management system</span>{/*后台管理系统*/}
          </a>
          <div className="navbar-custom-menu">
            <div className="fn-right right-block">
              Welcome,{userMessage.name}{/*欢迎您，{userMessage.name}*/}
              <a onClick={this.signOut}>
                <Icon type="logout" /> Logout{/*注销*/}
              </a>
              <i className="anticon anticon-edit"></i><a style={{ display: "inline-block", marginLeft: '5px' }}
                onClick={this.showModal.bind(this, userMessage.id)}>change Password{/*修改密码*/}</a>
              <i className="anticon anticon-edit"></i><a style={{ display: "inline-block", marginLeft: '5px' }}
                onClick={this.changeLanguage}>change Language</a>
              <Tooltip placement="bottom" title={toggleRole}>
                <a className="">[Switch roles{/*切换角色*/}]</a>
              </Tooltip>
            </div>

          </div>
        </div>

        <AddWin ref="AddWin" visible={this.state.visible} title={this.state.title} hideModal={me.hideModal} />
      </div>
    )
  }
});
export default Top;