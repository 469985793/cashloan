var Reflux = require('reflux'); 
import antd from 'antd'; 
var Table = antd.Table; 
var Actions = require('../actions/CustomerActions');
    
export default  Reflux.createStore({
    listenables: [Actions],
    dataSource: [], 
    onInitStore() {
        this.queryMessages();
    }, 
    onPostMessage(data) {
        //console.log(data);
        this.queryMessages(data); 
    },
    queryMessages(data) {  
        this.dataSource =  new Table.DataSource({
          url: "/cms/manager/getCmsArticleListBySiteId.htm", 
          resolve: function(result) {
            return result.data;
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
            var postData={};
            postData.siteId='18';
            postData.orderStr='sort';
            postData.order='ASC';
            var params = {
              page:pagination.current,
              param:JSON.stringify(postData),
              start:(pagination.current-1)*10,
              limit:10, 
              pageSize: 10,
              currentPage: pagination.current,
              //sortField: sorter.field,
              //sortOrder: sorter.order
            };
            for (var key in filters) {
              params[key] = filters[key];
            }
            if(data){
              params["searchParams"] = JSON.stringify(data);
            }
            
            return params;
          }
        }); 
        this.trigger(this.dataSource); 
        
    }
}); 
 

