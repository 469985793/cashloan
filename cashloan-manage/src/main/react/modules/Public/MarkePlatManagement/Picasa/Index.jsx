/*黑名单客户管理*/
import React from 'react';  

var  List = require('./Components/List'); 
var  ActionBtns = require('./Components/ActionBtns');  
export default React.createClass({ 
	 
    render() { 
        return <div>
           <ActionBtns />
           <List />
        </div> 
    }
}); 