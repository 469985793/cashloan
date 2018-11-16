var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');

    
export default  Reflux.createStore({
    listenables: [Actions],
    selectData:{
      isSelectRecord:false,
      record:null
    }, 
    formData:{          
          bank_code:"",
          open_net:"",
          open_name:"",
          username:"",
          is_loan:"1",
          is_repayment:"1", 
          name: "",
          number: "",
          officeId: "",
          officeOver: "",
          position:"",
          remark:""
        } , 
    onSetFormData(title){
      var newData={};
      if(title=="修改"||title=="信息查看")
      { 
        var src = this.selectData.record;
        src.is_loan= String(src.is_loan);
        src.is_repayment= String(src.is_repayment);
        this.formData = src;

      }
      else 
      {
         for(let key in this.formData) {
             newData[key] = "";
          } 
          newData.is_loan="1";
          newData.is_repayment="1";
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
 

