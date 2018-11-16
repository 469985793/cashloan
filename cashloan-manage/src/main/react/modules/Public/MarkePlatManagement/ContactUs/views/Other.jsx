/*待处理*/
import React from 'react'; 
 
var  MyCustomerList = require('./OtherComponents/MyCustomerList');
var  SeachForm = require('./OtherComponents/SeachForm');
var  ActionBtns = require('./OtherComponents/ActionBtns'); 

 
export default React.createClass({ 
  
    render() {  
      return <div>
          <ActionBtns/>
          <MyCustomerList/>
        </div>
    }
}); 

 