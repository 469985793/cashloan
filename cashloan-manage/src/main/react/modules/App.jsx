import React from 'react';
import ReactDOM from 'react-dom';
import Top from './frame/Top';
import Sider from './frame/Sider';
import NavTab from './frame/NavTab';
 
var TabListStore = require('./frame/stores/TabListStore'); 

const App = React.createClass({ 
  getInitialState() {
    return {
        activeKey:'工作台',
        fold:false
    }
  },
  toggleMenu(){
      var me = this;
      this.setState({
        fold:!me.state.fold
      });
  },
  roleName:null,
  getRoleName(){
    return this.roleName;
  },
  setRoleName(roleName){
    this.roleName=roleName;
  },
  onWindowResize() { 
    this.forceUpdate();
  },
  componentDidMount() {
      Utils.ajaxData({
          url: '/modules/manage/system/dict/list.htm',
          callback: (data) => {
            if (data.code == 200) {
              window.dic = data
            } else {
              Modal.error({
                title: "字典查询错误"
              });
            }
          }
        });  
    window.addEventListener('resize', this.onWindowResize);
  },
  render() {
    var fold = this.state.fold ;
     var height = document.body.clientHeight;
    height = height-50;
    var cName= fold?'leftSide':'leftSide leftSideOverflow';
    return (
        <div className={`${ fold ? 'app-aside-folded' : ''}`}>
          <Top setRoleName={this.setRoleName} toggleMenu={this.toggleMenu} fold={ fold}/>
          <div className="clearfix">
            <div className={cName} ref="leftSide" style={{height:height}}>
              <Sider fold={ fold} toggleMenu={this.toggleMenu}/>
            </div>
            <div className='rightSide' ref="rightSide">
              <div className="app-content-body ">
                <NavTab getRoleName={this.getRoleName} />
              </div>
            </div>
          </div>
        </div>)
  }
});

export default App;
