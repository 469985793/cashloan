var React = require('react');
import { Table} from 'antd';   
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 
var columns = [{
        title: '手机',
        dataIndex: 'phone' 
      },
      {
        title: '手机绑定',
        dataIndex: 'phoneBind',
        render: function(value) {
          if(value == true )
            return "绑定";
          else return "未绑定";
        } 
      },{
        title: '邮箱',
        dataIndex: "email"
      },{
        title: '邮箱绑定',
        dataIndex: "emailBind",
        render: function(value) {
          if(value == true)
            return "绑定";
          else return "未绑定";
        }
      },{
        title: '真实姓名',
        dataIndex: "name"
      },
      {
        title: '身份证号',
        dataIndex: 'certNumber'
      },
      {
        title: '注册时间',
        dataIndex: 'registerTime'
      },{
        title: '客户来源',
        dataIndex: 'customerFrom'
      },
      {
        title: '账户状态',
        dataIndex: "status",
        render: function(value) {
          if(value == true)
            return "已冻结";
          else return "正常";
        }
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
      return record.uuid;
    }, 
    render() { 
        if(!this.state.dataSource)
          return <div className="tableList"></div>  
        else return ( 
            <div className="tableList">
                <Table rowKey={this.rowKey}   columns={columns} dataSource={this.state.dataSource} bordered={true} onClickRow={this.onClickRow}/>
            </div>  
        );
    }
});
 
