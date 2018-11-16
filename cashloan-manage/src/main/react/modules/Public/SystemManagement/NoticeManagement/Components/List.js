var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 
var columns = [{
      title: '产品编号',
      dataIndex: 'id' 
      },{
        title: '公告标题',
        dataIndex: 'title' 
      },
      {
        title: '公告内容',
        dataIndex: 'content' 
      },{
        title: '创建日期',
        dataIndex: "add_time"
      },{
        title: '接收单位',
        dataIndex: "officeNames"
      },{
        title: '发布时间',
        dataIndex: "send_time",
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
    onClickRow(record){//console.log(record);
      Actions.setSelectData(record,true);
    },
    rowKey(record){
      return record.id;
    }, 
    render() { 
        if(!this.state.dataSource)
          return <div className="tableList"></div>  
        else return ( 
            <div className="tableList">
                <Table rowKey={this.rowKey} rowSelection={rowSelection} columns={columns} dataSource={this.state.dataSource} bordered={true} />
            </div>  
        );
    }
});
 
