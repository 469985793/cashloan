var Reflux = require('reflux'); 
var Actions = require('../actions/Actions'); 
    
export default  Reflux.createStore({
    listenables: [Actions], 
    selectRecord: [], 
    formData:{  
              id:"",
              name:"", 
              nationality:"",
              sex:"",
              sesx:"",
              certType:"",
              certNumber:"",
              birthday:"",
              marryStatus:"",
              education:"",
              isLocalHouse:"",
              liveState:"",
              yearsIncome:"", 
              otherCase:"",
              areaId:"",
              liveAddress:"",
              liveZipCode:"",
              householdAddress:"",
              householdZipCode:"",
              fixedPhone:"",
              mobile:"",
              email:"",
              company:"",
              department:"",
              position:"",
              companyAddr:"",
              companyZipCode:"",
              companyPhone:"",
              inTime:"",
              companyPro:"",
              remark:"",
              isHaveChildren:"",
              emergencyContactNumber:"",
              spouseIskown:"",
              healthy:"",
              customerManager:"",
              isSelfSupport:""
    }, 
    onSetFormData(selectRecord,title){
      var newData={};
      if(title=="修改"||title=="信息查看")
      { 
        for(let key in this.formData) {
          if(key=="areaId"||key=="sex"||key=="isHaveChildren"||key=="marryStatus"||key=="education"||key=="isSelfSupport"||key=="liveState"||key=="spouseIskown"||key=="healthy"){
            newData[key] = JSON.stringify(selectRecord[key]);
          }
            else{
             newData[key] = selectRecord[key];
          }  
        }
        this.formData = newData;
      }
      else  {
         for(let key in this.formData) {
             newData[key] = undefined;
             if(key=='birthday'){
              newData[key] = null;
             }
          } 
         this.formData = newData;
      }
      this.trigger(this.formData);  
    }  
}); 
 

