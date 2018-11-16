var Reflux = require('reflux'); 
import antd from 'antd';  
var Actions = require('../actions/Actions'); 
var reqwest = require('reqwest');
export default  Reflux.createStore({
    listenables: [Actions],
    cityData: {
      areaIdleve1:"",
      areaIdleve2:"",
      areaIdleve3:"",
      selectData:""
    }, 
    onInitProvinceData(){
       this.areaIdleveData('areaIdleve1',0);
    }, 
    onSetCitySelectRecord(record) {  
        var me = this;
        var areaId = record.areaId;  
        reqwest({
                url: '/modules/common/action/PlAreaAction/getAllAreaInfoByLeaveThreeId.htm'
                , method: 'get'
                ,data: {id: areaId}
                , type: 'json' 
                ,success: (result) => {
                  var data = result.data; 
                  me.cityData.selectData = data;
                  me.areaIdleveData('areaIdleve1',0);
                  me.areaIdleveData('areaIdleve2',data.Level0id);
                  me.areaIdleveData('areaIdleve3',data.Level1id);
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
 

