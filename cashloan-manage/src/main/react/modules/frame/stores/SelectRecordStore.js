var Reflux = require('reflux'); 
import React from 'react'; 
var AppActions = require('../actions/AppActions');

export default Reflux.createStore({
    listenables: [AppActions],
    selectRecord: [],  
    onSelectRecord(selectRecord){
        this.selectRecord = selectRecord;//console.log(selectRecord);
        this.trigger(this.selectRecord);
    } 
});
 

