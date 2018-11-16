var Reflux = require('reflux'); 
var CustomerActions = require('../actions/CustomerActions'); 
    
export default  Reflux.createStore({
    listenables: [CustomerActions], 
    selectData2:{
      isSelectRecord:false,
      record:null
    }, 
    onSetSelectData2(record,selected) {  
        this.selectData2.record = record;  
        this.selectData2.isSelectRecord = selected; 
        this.trigger(this.selectData2); 
    } 
});
 

