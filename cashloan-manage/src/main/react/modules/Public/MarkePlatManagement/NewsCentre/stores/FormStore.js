var Reflux = require('reflux'); 
var Actions = require('../actions/Actions'); 
    
export default  Reflux.createStore({
    listenables: [Actions],
    selectRecord: [], 
    formData:{  
          id:"",
          siteId:"",
          title:"",
          updateTime:"",
          updateUserid:"",   
          source:"", 
          isRecommend:"", 
          isTop:"", 
          url:"", 
          status:"",
          picPath:"",
          seoKeyword:"",
          introduction:"",
          content:""
    }, 
    onSetFormData(selectRecord,title){ 
      
      var newData={};
      if(title=="编辑"||title=="查看")
      { 
        for(let key in this.formData) {
            newData[key] = selectRecord[key];
        }
        this.formData = newData;
      }
      else  {
         for(let key in this.formData) {
             newData[key] = "";
          } 
         this.formData = newData;  
      }
      this.trigger(this.formData);  
    }  
}); 
 

