var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 
var columns = [{
      title: '开户行',
      dataIndex: 'bank_code_text' 
      },{
        title: '户名',
        dataIndex: 'open_name' 
      },
      {
        title: '账户',
        dataIndex: 'account' 
      },{
        title: '开户网点',
        dataIndex: "open_net"
      },{
        title: '是否放款使用',
        dataIndex: "is_loan",
        render: function(value) {
          if(value==1)
            return "是";
          else return "否";
        } 
      },{
        title: '是否回款使用',
        dataIndex: "is_repayment",
        render: function(value) {
          if(value==1)
            return "是";
          else return "否";
        } 
      },{
        title: '创建者',
        dataIndex: "creator_text"
      },
      {
        title: '创建时间',
        dataIndex: 'create_time',
      }];  
const rowSelection = {
  onSelect: function(record, selected, selectedRows) {
    Actions.setSelectData(record,true,selectedRows);
  },
  onSelectAll: function(selected, selectedRows) {
    Actions.setSelectData(null,true,selectedRows);
  }
};      
export default React.createClass({ 
    mixins: [
      Reflux.connect(ListStore, 'dataSource')
    ],
    getInitialState() {
      return {
         dataSource: null 
       };
    },  
    shouldComponentUpdate(nextProps, nextState) { 
      return nextState.dataSource !== this.state.dataSource;
    }, 
    componentDidMount() { 
        Actions.initStore();
    },
    onClickRow(record){
      Actions.setSelectData(record,true);
    },
    rowKey(record){
      return record.id;
    }, 
    render() { 
        if(!this.state.dataSource)
          return <div className="tableListStore"></div>  
        else return ( 
            <div className="tableListStore">
                <Table rowKey={this.rowKey} rowSelection={rowSelection}  columns={columns} dataSource={this.state.dataSource} bordered={true} />
            </div>  
        );
    }
});
 
