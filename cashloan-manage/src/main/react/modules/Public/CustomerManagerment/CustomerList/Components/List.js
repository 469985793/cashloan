var React = require('react');
import { Table } from 'antd';
import reqwest from 'reqwest'; 
var columns = [{
        title: '客户ID',
        dataIndex: 'id' 
      },{
        title: '客户名称',
        dataIndex: 'name' 
      },
      {
        title: '证件号码',
        dataIndex: "certNumber"
      },{
        title: '手机号码',
        dataIndex: "mobile"
      },{
        title: '贷款次数',
        dataIndex: "loans"
      },{
        title: '客户状态',
        dataIndex: 'status',
        render: function(value) {
          if(value==0)
            return "正常用户";
          else return "黑名单用户";
        }
      },{
        title: '销售员',
        dataIndex: "customerManager"
      }];  
export default React.createClass({ 
    getInitialState() {
      return {
        data: [],
        pagination: {},
        loading: false,
      };
    },
    handleTableChange(pagination, filters, sorter) {
      const pager = this.state.pagination;
      pager.current = pagination.current;
      this.setState({
        pagination: pager,
      });
      this.fetch({
        pageSize: pagination.pageSize,
        currentPage: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    },
    fetch(params = {page:1,start:0,limit:10,pageSize:10,currentPage:1}) {
      //console.log('请求参数：', params);
      this.setState({ loading: true });
      reqwest({
        url: "/modules/common/action/CustomerAction/list.htm", 
        method: 'get',
        data: params,
        type: 'json',
        success: (result) => {
          const pagination = this.state.pagination;
          pagination.total = result.total;
          this.setState({
            loading: false,
            data: result.datas,
            pagination,
          });
        }
      });
    },
    componentDidMount() {
      this.fetch();
    },
    render() {
      return (
        <Table columns={columns}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />
      );
    }
});
 
