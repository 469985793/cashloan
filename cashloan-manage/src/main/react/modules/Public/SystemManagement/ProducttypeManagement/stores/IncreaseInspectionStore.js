var Reflux = require('reflux'); 
import antd from 'antd'; 
var Table = antd.Table; 
var  Actions = require('../actions/CustomerActions');
export default  Reflux.createStore({
    listenables: [Actions],
    dataSource: [], 
    initIncreaseInspectionStore(fileId) {
        this.queryMessages(fileId,undefined);
    }, 
    onPostMessage(fileId,data) { 
      //console.log(data)
        this.queryMessages(fileId,data); 
    },
    queryMessages(fileId,data) {  
        this.dataSource =  new Table.DataSource({
          url:"/modules/fel/FelformulaAction/Select.htm", 
          resolve: function(result) {
            return result.data;
          },
          data: {},
          // 和后台接口返回的分页数据进行适配
          getPagination: function(result) { 
            return {
              total: result.total,
              pageSize:9999999
            }
          },
          // 和后台接口接收的参数进行适配
          // 参数里提供了分页、筛选、排序的信息
          getParams: function(pagination, filters, sorter) { 
            var params = {
              page:pagination.current,
              start:(pagination.current-1)*9999999,
              limit:9999999, 
              pageSize: 9999999,
              currentPage: pagination.current,
              sortField: sorter.field,
              sortOrder: sorter.order
            };
            for (var key in filters) {
              params[key] = filters[key];
            }
            if(data){
              params["searchParams"] = JSON.stringify(data);
            }
            //console.log('请求参数：', params);
            return params;
          }
        }); 
        this.trigger(this.dataSource); 
        
    }
}); 
 

