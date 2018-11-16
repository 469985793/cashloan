import React from 'react';
import { Tabs} from 'antd';
var TabPane = Tabs.TabPane;
// var Workbench = require('../ChannelInformationStatisticsGAP/index');
var Workbench = require('../BlankPage');

/*加载默认页*/
import Reflux from 'reflux'; 
var AppActions = require('./actions/AppActions');
var TabListStore = require('./stores/TabListStore');
 
var NavTab = React.createClass({
  mixins: [ 
    Reflux.listenTo(TabListStore, 'onMatch')
  ],
  onMatch(data) { 
        this.setState({
            activeId:data.activeId,
            tablist: data.tablist
        });
  },
  getInitialState() {
    return {
      tablist: [{'key': 'workbench', 'tabName': '工作台',"tabId":'workbench',tabContent:"Workbench"}],
      activeId: 'workbench',
    }
  }, 
  onChange(activeId) { 
    this.setState({activeId});
  },
  remove(tabId, e) {
    e.stopPropagation();
    AppActions.removeTab(tabId); 
  }, 
  render() { 
    return (
      <div className="Mytabs">
          <Tabs activeKey={this.state.activeId} onChange={this.onChange} destroyInactiveTabPane animation={null} contentStyle={{height:document.body.clientHeight-100,overflow: 'auto'}}>
                {
                  this.state.tablist.map((t, i)=> {
                    return i == 0 ?
                      <TabPane key="workbench" tab="工作台"> <Workbench  /></TabPane>
                      : <TabPane key={t.tabId}
                                 tab={<div>{t.tabName} <i className="anticon anticon-cross-circle"  onClick={this.remove.bind(this,t.tabId)}></i></div>}>
                        {t.tabContent} 
                    </TabPane>;
                  })
                }
          </Tabs>
      </div>);
  }
});

export default NavTab;
