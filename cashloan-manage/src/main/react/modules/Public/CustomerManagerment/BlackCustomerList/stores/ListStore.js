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
    this.dataSource = new Table.DataSource({
      url: "/modules/common/action/BlackCustomerAction/list.htm",
      resolve: function(result) {
        return result.datas;
      },
      data: {},
      // 和后台接口返回的分页数据进行适配
      getPagination: function(result) {
        //console.log(result);
        return {
          total: result.dataCount,
          pageSize: 10
        }
      },
      // 和后台接口接收的参数进行适配
      // 参数里提供了分页、筛选、排序的信息
      getParams: function(pagination, filters, sorter) {
        var params = {
          page: pagination.current,
          start: (pagination.current - 1) * 10,
          limit: 10,
          pageSize: 10,
          currentPage: pagination.current,
          sortField: sorter.field,
          sortOrder: sorter.order
        };
        for(var key in filters) {
          params[key] = filters[key];
        }
        if (data) {
          params["data"] = JSON.stringify(data);
        }
        //console.log('请求参数：', params);
        return params;
      }
    });
    this.trigger(this.dataSource);

  }
}); 
 

