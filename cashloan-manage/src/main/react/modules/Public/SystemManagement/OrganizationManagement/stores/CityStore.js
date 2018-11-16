var Reflux = require('reflux'); 
import antd from 'antd';  
var CustomerActions = require('../actions/CustomerActions'); 
var reqwest = require('reqwest');
export default  Reflux.createStore({
    listenables: [CustomerActions],
    cityData: {
      areaIdleve1:"",
      areaIdleve2:"",
      areaIdleve3:"",
      selectData:"",
      Id:""
    }, 
    onInitProvinceData(){
       this.areaIdleveData('areaIdleve1',0);
    }, 
    onSetCitySelectRecord(areaid) {  
        var me = this;  
           
           reqwest({
                   url: '/modules/common/action/PlAreaAction/getAllAreaInfoByLeaveThreeId.htm'
                   , method: 'get'
                   ,data: {id: areaid}
                   , type: 'json' 
                   ,success: (result) => {
                     var data = result.data; 
                       me.cityData.selectData = data;
                       me.areaIdleveData('areaIdleve1',0);
                       if(areaid){
                       me.areaIdleveData('areaIdleve2',data.Level0id);
                       me.areaIdleveData('areaIdleve3',data.Level1id);
                     }
                   } 
               }).then(function (resp) {
                  me.trigger(me.cityData);
            });  
          

    }, 
    areaIdleveData(areaIdleve,Levelid){
        var me = this;
        reqwest({
                      url: '/modules/common/action/PlAreaAction/getAreaListByParentLeave.htm',
                      method: 'get', 
                      data: {parentid:Levelid},
                      type: 'json',
                      success: (result) => { 
                        var newValue = {};
                        me.cityData[areaIdleve] = result.data;
                      }
                   });
    }
}); 
 

