/*首页广告图片管理*/
import React from 'react';  

var  List = require('./Components/List');
var  SeachForm = require('./Components/SeachForm');
var  ActionBtns = require('./Components/ActionBtns'); 
 var reqwest = require('reqwest');
export default React.createClass({ 
	getInitialState() {
        return {
            newsType:{},
            certTypeChildren:[],  
        };
    },
	componentDidMount() { 
        var me = this ;
        reqwest({
                url: '/cms/manager/getCmsSiteByParentId.htm?node=4',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.map((item)=>{
                      return (<Option key={item.id} value={item.id}>{item.name}</Option>);
                    });
                   me.setState({
                   	newsType:result,
                   	certTypeChildren:items
                   });
                }
              });
    },
    render() { 
        return <div>
           <ActionBtns certTypeChildren={this.state.certTypeChildren}/>
           <List newsType={this.state.newsType}/>
        </div> 
    }
}); 