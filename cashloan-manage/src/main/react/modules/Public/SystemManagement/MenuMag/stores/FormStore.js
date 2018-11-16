var Reflux = require('reflux'); 
var CustomerActions = require('../actions/CustomerActions');

    
export default  Reflux.createStore({
    listenables: [CustomerActions],
    selectRecord: [], 
    formData:{          
          scriptid:"",
          text:"",
          remark:"",
          sort:"",
          isDelete:"",
          isMenu:"",
          leaf: "",
          level: "",
          parentId: ""
        } , 
    isSelectRecord:false, 
    onSetFormData(title){
      var newData={};
      if(title=="修改"||title=="信息查看")
      {  
        var src = this.selectRecord;
        src.isMenu=src.isMenu?String(src.isMenu):src.isMenu;
        src.leaf=src.leaf?String(src.leaf):src.leaf;
        src.level=src.level?String(src.level):src.level;
        this.formData = src;
      }
      else if(title=="添加"){
        var src={};
        src.scriptid=null;
        src.text=null;
        src.remark=null;
        src.sort=null;
        src.isMenu=null;
        src.leaf=null;
        src.level=null;
        src.parentId=this.selectRecord.id;
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
        this.selectRecord = record;  
        this.isSelectRecord = selected; 
        this.trigger(this.isSelectRecord); 
    }
});
 

