/*待处理*/
import React from 'react'; 
 
var  MyCustomerList = require('./ProcessedComponents/MyCustomerList');
var  SeachForm = require('./PendingComponents/SeachForm');
var  ActionBtns = require('./ProcessedComponents/ActionBtns'); 

 
export default React.createClass({ 
  
    render() {  
      return <div>
          <SeachForm/>
          <ActionBtns/>
          <MyCustomerList/>
        </div>
    }
}); 

 