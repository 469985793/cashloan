/*用户账户管理*/
import React from 'react';  

var  List = require('./Components/List');
var  SeachForm = require('./Components/SeachForm');
var  ActionBtns = require('./Components/ActionBtns'); 
 
export default React.createClass({ 
    render() { 
        return <div>
           <SeachForm />
           <ActionBtns/>
           <List/>
        </div> 
    }
}); 