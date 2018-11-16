var Reflux = require('reflux'); 
import antd from 'antd'; 
var Table = antd.Table; 
var Actions = require('../actions/Actions'); 
export default  Reflux.createStore({
    listenables: [Actions],
    dataSource: [], 
    onInitStore() {
        this.queryMessages();
    }, 
    onPostMessage(data) { 
        this.queryMessages(data); 
    },
    queryMessages(data) {  
        this.dataSource =  new Table.DataSource({
          url: "/modules/credit/action/NoticeAction/query.htm", 
          resolve: function(result) {
            return result.rows;
          },
          data: {},
          // 和后台接口返回的分页数据进行适配
          getPagination: function(result) { 
            return {
              total: result.total,
              pageSize:10
            }
          },
          // 和后台接口接收的参数进行适配
          // 参数里提供了分页、筛选、排序的信息
          getParams: function(pagination, filters, sorter) { 
            var params = {
              page:pagination.current,
              start:(pagination.current-1)*10,
              limit:10, 
              pageSize: 10,
              currentPage: pagination.current,
              sortField: sorter.field,
              sortOrder: sorter.order
            };
            for (var key in filters) {
              params[key] = filters[key];
            }
            if(data){
              params["where.n.title"] = data.title;
              params["where.n.content_like"] = data.content_like;
              params["is_send"] = data.is_send;
            } 
            return params;
          }
        }); 
        this.trigger(this.dataSource); 
        
    }
}); 
 

