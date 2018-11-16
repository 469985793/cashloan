var React = require('react');
import { Table } from 'antd';
import reqwest from 'reqwest';  
 
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
      var me = this;
      this.setState({ loading: true });
      reqwest({
        url: me.props.url, 
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
        <Table columns={this.props.columns} rowKey={this.props.rowKey}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />
      );
    }
});
 
