var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');

    
export default  Reflux.createStore({
    listenables: [Actions],
    selectData:{
      isSelectRecord:false,
      record:null
    }, 
    formData:{          
          id:"",
          enabled:"",
          name:"",
          fieldName:"",
          unit:"",
          source:"",
          expression: "",
          remark:""
        } , 
    onSetFormData(title){
      var newData={};
      if(title=="修改"||title=="查看")
      { 
        var src = this.selectData.record;
        src.enabled= String(src.enabled);
        src.source= String(src.source);
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
    },
    onSetSelectData(record,selected) {  
        this.selectData.record = record;  
        this.selectData.isSelectRecord = selected; 
        this.trigger(this.selectData); 
    } 
}); 
 

