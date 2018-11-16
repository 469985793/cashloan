var Reflux = require('reflux'); 
var  CustomerActions = require('../actions/CustomerActions');

    
export default  Reflux.createStore({
    listenables: [CustomerActions],
    selectData:{
      isSelectRecord:false,
      record:null
    }, 
    selectData2:{
      isSelectRecord:false,
      record:null
    },
    formData:{          
          name:"",
          code:"",
          sort:"",
          remark:""
        } , 
    onSetFormData(title){
      var newData={};
      if(title=="修改")
      { 
        this.formData = this.selectData.record;
      }
      else if(title=="字项修改"){
        this.formData = this.selectData2.record;
      }
      else 
      {
         for(let key in this.formData) {
             newData[key] = "";
          } 
          if(title=="字项新增"){
            newData.parentId=this.selectData2.record.parentId;
          }
         this.formData = newData;
      }
        
      this.trigger(this.formData); 
    },
    onSetSelectData(record,selected) {  
        this.selectData.record = record;  
        this.selectData.isSelectRecord = selected; 
        this.trigger(this.selectData); 
    }, 
    onSetSelectData2(record,selected) {  
        this.selectData2.record = record;  
        this.selectData2.isSelectRecord = selected; 
        this.trigger(this.selectData2); 
    } 
}); 
 

