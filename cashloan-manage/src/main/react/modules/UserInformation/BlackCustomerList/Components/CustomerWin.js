import React from 'react';
import {
  Modal,
  Tabs
} from 'antd';
const TabPane = Tabs.TabPane;
const objectAssign = require('object-assign');
var BaseInformation = require("./BaseInformation");
var InstallmentRecord = require("./InstallmentRecord");
var CustomerWin = React.createClass({
  getInitialState() {
    return {
      status: {},
      activekey: "1",
    };
  },
  handleCancel() {
    this.props.hideModal();
    this.changeTabState();
  },
  handleTabClick(key) {
        this.setState({
            activekey: key
        })
  },
  changeTabState() {
        this.setState({
            activekey: 1,
        })
  },
  
  render() {
    var props = this.props;
    var state = this.state;
    var modalBtns  = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
           <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>  
                    <TabPane tab="基本信息" key="1">
                        <BaseInformation ref="BaseInformation" canEdit={props.canEdit} selectRecord={props.record} title={props.title}/>
                    </TabPane>
                    <TabPane tab="分期记录" key="2">
                        <InstallmentRecord ref="InstallmentRecord" canEdit={props.canEdit} record={props.record}/>
                    </TabPane>
                </Tabs>
      </Modal>
    );
  }
});
export default CustomerWin;