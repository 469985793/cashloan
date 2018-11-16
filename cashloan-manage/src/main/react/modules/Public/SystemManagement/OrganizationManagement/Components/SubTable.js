var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var SubTable = require('./SubTable');
var columns = [{
      title: '菜单名称',
      dataIndex: 'text' 
      },{
        title: '图标',
        dataIndex: 'iconCls' 
      },{
        title: '是否子节点',
        dataIndex: 'leaf',
        render: function(value) {
          if(value)
            return "是";
          else return "否";
        }
      },{
        title: '备注',
        dataIndex: "remark"
      },{
        title: '是否是菜单',
        dataIndex: 'isMenu',
        render: function(value) {
          if(value)
            return "是";
          else return "否";
        }
      }];  
export default React.createClass({ 
    shouldComponentUpdate(nextProps, nextState) { 
      return nextState.dataSource !== this.state.dataSource;
    }, 
    componentDidMount() { 
        CustomerActions.initStore();
    },
    onClickRow(record){
      CustomerActions.setSelectData(record,true);
    },
    rowKey(record){
      return record.id;
    },
    expandedRowRender(record,i){ 
      var dataSource =  new Table.DataSource({
                url: "/modules/manage/system/menu/findMenuTrees.htm", 
                resolve: function(result) {
                  return result;
                },
                data: {},
                // 和后台接口返回的分页数据进行适配
                getPagination: function(result) {
                  //console.log(result);
                  return {
                    total: result.total,
                    pageSize:25,
                    showSizeChanger: true
                  }
                },
                // 和后台接口接收的参数进行适配
                // 参数里提供了分页、筛选、排序的信息
                getParams: function(pagination, filters, sorter) { 
                  var params = {
                    parentId:0,
                    node:record.id, 
                    pageSize: pagination.pageSize,
                    currentPage: pagination.current,
                    sortField: sorter.field,
                    sortOrder: sorter.order
                  };
                  for (var key in filters) {
                    params[key] = filters[key];
                  }
                   
                  //console.log('请求参数：', params);
                  return params;
                }
              }); 
      return <SubTable record={record} dataSource={dataSource} />;
    },
    render() { 
      var props = this.props;
      var expandedRowRender = this.expandedRowRender(props.record);
      if(props.record.leaf)
        expandedRowRender = null;
      return (
        <Table rowKey={this.rowKey}  expandIconAsCell={false} expandedRowRender={expandedRowRender} columns={columns} dataSource={props.dataSource} onClickRow={this.onClickRow}/>
         ) 
    }
});
 
