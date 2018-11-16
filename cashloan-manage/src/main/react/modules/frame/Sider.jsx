import React from 'react';
import { Menu,Modal,Tooltip2 } from 'antd';
var SubMenu = Menu.SubMenu;
var Reflux = require('reflux');
var AppActions = require('./actions/AppActions');
var MenuDataStore = require('./stores/MenuDataStore'); 
const Sider = React.createClass({
  mixins: [ 
    Reflux.connect(MenuDataStore, "menuData")
  ], 
  getInitialState() {
    return {
      current: "workbench",
      menuData: {},
      openKeys:['0'],
    }
  },
  componentDidMount() {
    AppActions.initStore();
    //AppActions.setTabActiveKey('人工复审','LoanApplicationManage');
  },
  getMenuByData(){
    var me = this;
    var menuData = this.state.menuData;
    var SubMenuNodes = [];
    if (menuData&&menuData.length) {

      SubMenuNodes = menuData.map((item, key)=> {
        if(item.children){
           let itemNoades = item.children&&item.children.map((child, i)=> {
          if (!child.children) {
            return ( <Menu.Item key={child.value} tabName={child.label} tabId={child.scriptid}>{child.label}</Menu.Item>)
          }
          else {
            var childChild = child.children;
            let childrenNodes = childChild.map((son, j)=> {
              return ( <Menu.Item key={son.scriptid} tabName={son.label} tabId={son.scriptid}>{son.label}</Menu.Item>)
            });
            return (<SubMenu key={child.value}
                             title={<span><i className={`iconfont ${child.iconCls}`}></i><span className="menu-text" >{child.label}</span></span>}>
              { childrenNodes }
            </SubMenu> );
          }
        });
        return (
          <SubMenu key={item.value}
                   title={<span><i className={`iconfont ${item.iconCls}`}></i><span className="menu-text">{item.label}</span></span>}>
            { itemNoades }
          </SubMenu> );
        }
       else {
         return (
            <Menu.Item key={item.value} tabName={item.label} tabId={item.scriptid}>
                   <i className={`iconfont ${item.iconCls}`}></i><span className="menu-text">{item.label}</span>
            </Menu.Item> );
       }
      });

    }
    return SubMenuNodes;
  },
  handleClick(e){
    this.setState({
      current: e.key
    });
    AppActions.setTabActiveKey(e.item.props.tabName,e.item.props.tabId);
  },
  handleOpenKeys(e){
    this.setState({
      openKeys: e.openKeys
    });
  },
  handleCloseKeys(e) {
    this.setState({
      openKeys: e.openKeys
    });
  },
  
  toggleMenu(){
    var me = this;
    this.setState({
      openKeys: null
    });
    this.props.toggleMenu();
  },
  onToggle(info) {
    this.setState({
      openKeys: info.open ? info.keyPath : info.keyPath.slice(1),
    });
  },
  render() {
    var SubMenuNodes = this.getMenuByData();
    var fold = this.props.fold;
    var state = this.state;
    var openKeys = state.openKeys;
    return (
      <div>  
          {fold ?(<Menu onClick={this.handleClick} style={{width:'100%'}} mode='vertical'>
            {SubMenuNodes}
          </Menu>):(<Menu onClick={this.handleClick} style={{width:'100%'}} mode='inline' onOpen={this.onToggle} 
            openKeys={openKeys?openKeys:[]}
            onClose={this.onToggle}>
            {SubMenuNodes}
          </Menu>)} 
      </div>
    )
  }
});

export default Sider;
