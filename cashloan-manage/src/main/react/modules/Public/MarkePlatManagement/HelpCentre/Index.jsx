/*抵押品入库*/
import React from 'react';
import antd from 'antd'; 
var Tabs = antd.Tabs;
var TabPane = Tabs.TabPane;
export default React.createClass({
	getInitialState() { 
    return { 
      activeTab:'常见问题',
      DoneComponent:'' 
    }
  },
  onTabchange(key) {
    var me = this;
    var component=''; 
    if(key=="贷款常识")   
    { 
      require.ensure([], function(require) { 
            var   Done = require('./views/Processed');
            component = <Done />;
             me.setState({
              DoneComponent:component
            });
      }, 'Done');  
    } 
  }, 
  render: function () { 
    var TodoComponent,   
    Todo = require('./views/Pending');/*加载默认页*/
    TodoComponent = <Todo addTab={this.props.addTab}/>; 
    return (  
        <Tabs  activeTab={this.state.activeTab} onChange={this.onTabchange} size="mini"> 
            <TabPane tab="常见问题" key="常见问题" > 
               {TodoComponent} 
            </TabPane>
            <TabPane tab="贷款常识" key="贷款常识">
                {this.state.DoneComponent} 
            </TabPane>
        </Tabs>
    )
  }
});