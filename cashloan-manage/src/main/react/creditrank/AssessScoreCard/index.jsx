/* 信用等级管理--评分卡 */
import React from 'react';
import List from './Components/List';
import ScoretemList from './Components/ScoretemList';
import ScorefactorList from './Components/ScorefactorList';
import SeachForm from './Components/SeachForm';
import {
  Tabs,
} from 'antd';
const TabPane = Tabs.TabPane;
export default React.createClass({

  getInitialState() {
    return {
      params: {
        pageSize: 5,
        current: 1
      },
      
      activekey: "1",
    }
  },
  passParams(params) {
    this.setState({
      params: params
    });
  },
  handleTabClick(key){
   this.setState({
     activekey:key
   })
  },
  render() {
    return <div>
    <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey} animation={null}>
        <TabPane tab="评分卡" key="1">
           <SeachForm passParams={this.passParams}/>
           <List setTabClick={this.handleTabClick} activekey={'1'} params={this.state.params}/>
        </TabPane>
        <TabPane tab="评分项目" key="2" disabled  >
           <ScoretemList setTabClick={this.handleTabClick}  activekey={'2'}/>
        </TabPane>
        
      </Tabs>
      {/*<div className="block-panel">
        <SeachForm passParams={this.passParams}/>
      </div>
      <List params={this.state.params}/>*/}
    </div>
  }
});
