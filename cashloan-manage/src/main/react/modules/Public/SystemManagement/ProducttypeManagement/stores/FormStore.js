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
          productType:"",
          ptype:"",
          state:"",
          note:""      
        } , 
    onSetFormData(title){
      var newData={};
      if(title=="修改")
      { 
        this.formData = this.selectData2.record;
      }else{
           for(let key in this.formData) {
               newData[key] = "";
            } 
           this.formData = newData;
      }
        
      this.trigger(this.formData); 
    },
    onSetSelectData(record,selected,selectedRows) {  
        this.selectData.record = record;  
        this.selectData.selectedRows = selectedRows;
        this.selectData.isSelectRecord = selected; 
        this.trigger(this.selectData); 
    }, 
    onSetSelectData2(record,selected) {  
      //console.log(record)
        this.selectData2.record = record;  
        this.selectData2.isSelectRecord = selected; 
        this.trigger(this.selectData2); 
    } 
}); 
 

