var Reflux = require('reflux'); 
var Actions = require('../actions/CustomerActions');

    
export default  Reflux.createStore({
    listenables: [Actions],
    selectRecord: [], 
    formData:{
          siteId:"",
          title:"",
          content:""
    }, 
    isSelectRecord:false, 
    onSetFormData(title){
      var newData={};
      if(title=="修改"||title=="查看")
      { 
        this.formData = this.selectRecord;
      }
      else {
        for(let key in this.formData) {
             newData[key] = "";
          } 
         this.formData = newData;
      }
      this.trigger(this.formData); 
    },
    onSetSelectData(record,selected) {
        this.selectRecord = record;  
        this.isSelectRecord = selected; 
        this.trigger(this.selectRecord); 
    }
}); 
 

