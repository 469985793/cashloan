var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');

    
export default  Reflux.createStore({
    listenables: [Actions],
    selectData:{
      isSelectRecord:false,
      record:null
    }, 
    formData:{          
          name:"",
          type:"",
          content:""
        } , 
    onSetFormData(title){
      var newData={};
      if(title=="修改"||title=="查看")
      { 
        var src = this.selectData.record;
        //console.log(src)
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
    },
    onSetSelectData(record,selected) {  
        this.selectData.record = record;  
        this.selectData.isSelectRecord = selected; 
        this.trigger(this.selectData); 
    } 
}); 
 

