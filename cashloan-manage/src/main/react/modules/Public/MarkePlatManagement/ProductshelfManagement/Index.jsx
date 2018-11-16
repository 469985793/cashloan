/*产品上下架管理*/
import React from 'react';
import antd from 'antd'; 
var Tabs = antd.Tabs;
var TabPane = Tabs.TabPane;
export default React.createClass({
	getInitialState() { 
    return { 
      activeTab:'已上架',
      DoneComponent:'' 
    }
  },
  onTabchange(key) {
    var me = this;
    var component=''; 
    if(key=="已下架")   
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
    TodoComponent = <Todo />; 
    return (  
        <Tabs  activeTab={this.state.activeTab} onChange={this.onTabchange} size="mini"> 
            <TabPane tab="已上架" key="已上架" > 
               {TodoComponent} 
            </TabPane>
            <TabPane tab="已下架" key="已下架">
                {this.state.DoneComponent} 
            </TabPane>
        </Tabs>
    )
  }
});