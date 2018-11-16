var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');

    
export default  Reflux.createStore({
    listenables: [Actions],
    selectData:{
      isSelectRecord:false,
      selectedRows:null,
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
        var src = this.selectData.selectedRows[0];
        src.isSelectRecord={true};
        src.selectedRows={length:1};
        this.formData = src;

      }
      else 
      {
         for(let key in this.formData) {
             newData[key] = "";
          } 
          newData.isSelectRecord={true};
          newData.selectedRows={length:1};
         this.formData = newData;
      }
        
      this.trigger(this.formData); 
    },
    onSetSelectData(record,selected,selectedRows) {  
        this.selectData.record = record; 
        this.selectData.selectedRows = selectedRows; 
        this.selectData.isSelectRecord = selected; 
        this.trigger(this.selectData); 
    } 
}); 
 

