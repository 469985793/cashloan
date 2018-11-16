var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 
var columns = [{
      title: '主键',
      dataIndex: 'id' 
      },{
        title: '参数编号',
        dataIndex: 'code' 
      },
      {
        title: '参数名称',
        dataIndex: 'name' 
      },{
        title: '参数值',
        dataIndex: "value"
      },{
        title: '备注',
        dataIndex: "remark",
      },{
        title: '创建人',
        dataIndex: "addUser",
      }];  
const rowSelection = {
  onSelect: function(record, selected, selectedRows) {
    //console.log(record, selected, selectedRows);
  },
  onSelectAll: function(selected, selectedRows) {
    //console.log(selected, selectedRows);
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
                <Table rowKey={this.rowKey} columns={columns} dataSource={this.state.dataSource} bordered={true} onClickRow={this.onClickRow}/>
            </div>  
        );
    }
});
 
