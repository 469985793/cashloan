/*待处理*/
import React from 'react'; 
 
var  MyCustomerList = require('./PendingComponents/MyCustomerList');
var  SeachForm = require('./PendingComponents/SeachForm');
var  ActionBtns = require('./PendingComponents/ActionBtns'); 
 
export default React.createClass({ 
   
    render() {  
      return <div>
          <ActionBtns/>
          <MyCustomerList/>
        </div>
    }
}); 

 