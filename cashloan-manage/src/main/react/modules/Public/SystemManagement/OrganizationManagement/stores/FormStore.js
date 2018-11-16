var Reflux = require('reflux'); 
var CustomerActions = require('../actions/CustomerActions');

    
export default  Reflux.createStore({
    listenables: [CustomerActions],
    selectRecord: [], 
    formData:{          
          name:"",
          parentId:"",
          sort:"",
          type:"",
          officeNumber:"",
          officeCard:"",
          address: "",
          area: "",
          phone: "",
          officeBank: "",
          officeBankCard: "",
          isDelete: "",
          remark: ""
        } , 
    isSelectRecord:false, 
    onSetFormData(title){
      var newData={};
      if(title=="修改机构"||title=="信息查看")
      {  
        this.formData = this.selectRecord;
      }
      else if(title=="增加机构"){
        for(let key in this.formData) {
             newData[key] = "";
          } 
        newData.parentId=this.selectRecord.id;
        this.formData = newData;
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
 

