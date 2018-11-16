var Reflux = require('reflux'); 
var Actions = require('../actions/Actions'); 
    
export default  Reflux.createStore({
    listenables: [Actions],
    selectRecord: [], 
    formData:{  
          id:"",
          customerName:"",
          certType:"",
          certNumber:"",
          remark:"",   
    }, 
    onSetFormData(selectRecord,title){ 
      
      var newData={};
      if(title=="修改"||title=="信息查看")
      { 
        for(let key in this.formData) {
          if(key=="certType"){
            newData[key] = JSON.stringify(selectRecord[key]);
          }
            else{
             newData[key] = selectRecord[key];
          }  
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
 

