/*客户咨询*/
import React from 'react'; 
  
var  CustomerActions = require('./actions/CustomerActions');

var  MyCustomerList = require('./Components/MyCustomerList'); 
var  ActionBtns = require('./Components/ActionBtns'); 
 
export default React.createClass({ 
    expandedAll(){
        this.refs.table.expandedAll();
    },
    collapseAll(){
        this.refs.table.collapseAll();
    },
    render() { 
        return <div>
           <ActionBtns expandedAll={this.expandedAll} collapseAll={this.collapseAll}/>
           <MyCustomerList ref="table"/>
        </div> 
    }
}); 