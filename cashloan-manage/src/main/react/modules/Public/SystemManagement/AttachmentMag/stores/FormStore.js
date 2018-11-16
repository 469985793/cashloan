var Reflux = require('reflux'); 
var CustomerActions = require('../actions/CustomerActions');

    
export default  Reflux.createStore({
    listenables: [CustomerActions],
    selectRecord: [], 
    data:{
      rd_remark:"",
      rd_ptype:"",
      rd_remark2:""
    }, 
    isSelectRecord:false, 
    onSetFormData(title){
      if(title=="查看详情"||title=="修改用户")
      {
        this.data = this.selectRecord;
      }
      else this.data ={};
      this.trigger(this.data); 
    },
    onSetSelectData(record,selected) { 
        this.selectRecord = record;  
        this.isSelectRecord = selected; 
        this.trigger(this.isSelectRecord); 
    } 
}); 
 

