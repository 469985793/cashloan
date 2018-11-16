var Reflux = require('reflux'); 
import antd from 'antd'; 
var Table = antd.Table; 
var CustomerActions = require('../actions/CustomerActions'); 
export default  Reflux.createStore({
    listenables: [CustomerActions],
    masterDataSource: [], 
    id:'',
    onInitStore() {
        this.queryMessages();
    }, 
    onPostMessage(data) {
        
        this.queryMessages(data); 
    },
    queryMessages(filtersData) {
        var me = this;  
        me.masterDataSource =  new Table.DataSource({
          url: "/modules/manage/system/dict/page.htm", 
          resolve: function(result) { 
            return result.datas;
          },
          data: {},
          // 和后台接口返回的分页数据进行适配
          getPagination: function(result) { 
            return {
              total: result.dataCount,
              pageSize:10
            }
          },
          // 和后台接口接收的参数进行适配
          // 参数里提供了分页、筛选、排序的信息
          getParams: function(pagination, filters, sorter) { 
            var params = {
              pageNum:pagination.current,
              start:(pagination.current-1)*10, 
              pageSize: 10, 
              limit:10, 
              currentPage: pagination.current,
              sortField: sorter.field,
              sortOrder: sorter.order
            };
            for (var key in filters) {
              params[key] = filters[key];
            }
            if(filtersData){
              params["searchform"] = JSON.stringify(filtersData);
            }
            return params;
          }
        });  
        this.trigger(this.masterDataSource); 
        
    }
}); 