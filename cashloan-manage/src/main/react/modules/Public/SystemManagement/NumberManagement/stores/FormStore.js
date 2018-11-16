var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');

    
export default  Reflux.createStore({
    listenables: [Actions],
      
    formData:{          
          name:"",
          type:"",
          content:""
        } , 
    onSetFormData(selectRecord ,title){
      var newData={};
      if(title=="修改"||title=="查看")
      { 
        var src = selectRecord; 
        src.type= String(src.type);
        this.formData = src;

      }
      else 
      {
         for(let key in this.formData) {
             newData[key] = "";
          } 
         this.formData = newData;
      }
        
      this.trigger(this.formData); 
    } 
}); 
 

