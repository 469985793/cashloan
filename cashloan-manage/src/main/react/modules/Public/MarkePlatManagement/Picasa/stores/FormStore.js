var Reflux = require('reflux'); 
var Actions = require('../actions/Actions'); 
    
export default  Reflux.createStore({
    listenables: [Actions],
    selectRecord: [], 
    formData:{  
          id:"",
          newFileName:"",
          url:"",
          filePath:"",
    }, 
    onSetFormData(selectRecord,title){ 
      
      var newData={};
      if(title=="查看")
      { 
        for(let key in this.formData) {
            newData[key] = selectRecord[key];
        } 
        this.formData = newData;
      }
      else  {
         for(let key in this.formData) {
             newData[key] = "";
          } 
         this.formData = newData;  
      }
      this.trigger(this.formData);  
    }  
}); 
 

