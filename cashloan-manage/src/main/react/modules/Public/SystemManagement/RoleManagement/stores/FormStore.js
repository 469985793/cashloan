var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');

    
export default  Reflux.createStore({
    listenables: [Actions],
    selectRecord: [], 
    formData:{          
          id:"",
          nid:"",
          roleId:"",
          isDelete:"",
          phone:"",
          email:"",
          mobile: "",
          name: "",
          number: "",
          officeId: "",
          officeOver: "",
          position:"",
          remark:""
        } , 
    isSelectRecord:false, 
    onSetFormData(title){
      var newData={};
      if(title=="修改"||title=="信息查看")
      {  
        this.selectRecord.isDelete=String(this.selectRecord.isDelete);
        this.formData = this.selectRecord;
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
        this.selectRecord = record;  
        this.isSelectRecord = selected; 
        this.trigger(this.isSelectRecord); 
    }
}); 
 

