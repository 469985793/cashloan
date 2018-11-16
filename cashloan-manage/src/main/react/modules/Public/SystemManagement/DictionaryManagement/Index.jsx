/*还款管理*/
import React from 'react'; 
 
var  CustomerActions = require('./actions/CustomerActions');

var  MasterList = require('./Components/MasterList');

var  SeachForm = require('./Components/SeachForm');
var  ActionBtns = require('./Components/ActionBtns'); 
 
export default React.createClass({ 
    postMessage(data) { 
        CustomerActions.postMessage(data); 
    }, 
    refresh(){

    },
    render() { 
        return <div>
           <SeachForm onClickSearch ={this.postMessage}/>
           <ActionBtns refresh={this.refresh}/>
           <div className="head-top">字典管理子项</div>
           <MasterList /> 
        </div> 
    }
}); 