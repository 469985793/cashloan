/*个人客户*/
import React from 'react';  
var  List = require('./Components/List');
var  SeachForm = require('./Components/SeachForm');
var  ActionBtns = require('./Components/ActionBtns'); 
 
export default React.createClass({   
    render() {
    	var roleName =this.props.getRoleName(); 
        return <div>
	        <div className="block-panel">
	        	<SeachForm />
	           <ActionBtns roleName={roleName}/>
	        </div>
            <div className="block-panel">
           		<List />
           	</div>
        </div> 
    }
}); 