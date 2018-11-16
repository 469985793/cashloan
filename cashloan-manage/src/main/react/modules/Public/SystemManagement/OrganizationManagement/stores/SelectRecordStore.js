var Reflux = require('reflux'); 
var Actions = require('../actions/CustomerActions'); 
    
export default  Reflux.createStore({
    listenables: [Actions], 
    selectData:{
      isSelectRecord:false,
      record:null
    }, 
    onSetSelectData(record,selected) {  
        this.selectData.record = record;  
        this.selectData.isSelectRecord = selected; 
        this.trigger(this.selectData); 
    } 
});
 

