var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 
var columns = [{
        title: '模板编号',
        dataIndex: 'id' 
      },
      {
        title: '模板名称',
        dataIndex: 'name' 
      },{
        title: '模板类型',
        dataIndex: "type",
        render: function (value) {
          return {
            1: '短信', 
            2: '邮件'
          }[value];
        }
      },{
        title: '内容',
        dataIndex: "content",
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
 
