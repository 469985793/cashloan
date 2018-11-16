/*抵押品入库*/
import React from 'react';
import antd from 'antd'; 
var Tabs = antd.Tabs;
var TabPane = Tabs.TabPane;
export default React.createClass({
	getInitialState() { 
    return { 
      activeTab:'平台介绍',
      DoneComponent:'',
      ContactUs:'' 
    }
  },
  onTabchange(key) {
    var me = this;
    var component=''; 
    if(key=="核心团队")   
    { 
      require.ensure([], function(require) { 
            var   Done = require('./views/Processed');
            component = <Done />;
             me.setState({
              DoneComponent:component
            });
      }, 'Done');  
    } 
    else if(key=="联系我们")   
    { 
      require.ensure([], function(require) { 
            var   Done = require('./views/Other');
            component = <Done />;
             me.setState({
              ContactUs:component
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
            <TabPane tab="平台介绍" key="平台介绍" > 
               {TodoComponent} 
            </TabPane>
            <TabPane tab="核心团队" key="核心团队">
                {this.state.DoneComponent} 
            </TabPane>
            <TabPane tab="联系我们" key="联系我们">
                {this.state.ContactUs} 
            </TabPane>
        </Tabs>
    )
  }
});