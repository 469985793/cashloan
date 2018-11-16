var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');

    
export default  Reflux.createStore({
    listenables: [Actions],
    selectRecord: [], 
    formData:{          
          id:"",
          rid:"",
          roleId:undefined,
          username:"",
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
    onSetFormData(selectRecord,title){
      var newData={};
      if(title=="修改"||title=="信息查看")
      {  
        this.formData = selectRecord;
      }
      else 
      {
         for(let key in this.formData) {
            if(key=="roleId")
              newData[key] = undefined;
            else  newData[key] = "";
          } 
         this.formData = newData;
      }
        
      this.trigger(this.formData); 
    } 
}); 
 

