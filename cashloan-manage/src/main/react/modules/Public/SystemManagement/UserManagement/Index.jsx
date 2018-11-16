/*用户管理*/
import React from 'react';  

var  List = require('./Components/List');
var  SeachForm = require('./Components/SeachForm'); 
export default React.createClass({ 
	getInitialState(){
		return {
			params:{}
		}		
	},
	passParams(params){
		this.setState({
			params:params
		});
	},
    render() {  
        return <div>
          	        <div className="block-panel">
          	           <SeachForm passParams={this.passParams} /> 
          	        </div>
                    
                   	<List params={this.state.params}/>
                   	
        </div> 
    }
}); 