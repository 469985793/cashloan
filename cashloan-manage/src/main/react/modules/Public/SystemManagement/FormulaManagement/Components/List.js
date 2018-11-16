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
        title: '字段名称',
        dataIndex: 'chineseName' 
      },
      {
        title: '参数名',
        dataIndex: 'englishName' 
      },{
        title: '单位',
        dataIndex: "unit"
      },{
        title: '数据来源',
        dataIndex: "dataSource",
        render: function (value) {
         if(value==1){
            return "系统计算"
          } 
          else if(value==2){
            return "人工录入"
          }
          else return <span></span>;
        }
      },{
        title: '公式',
        dataIndex: "formulaChinese",
      },{
        title: '备注',
        dataIndex: "note",
      },{
        title: '是否启用',
        dataIndex: "state",
        render: function (value) {
          if(value==0){
            return "否"
          } 
          else if(value==1){
            return "是"
          }
          else return <span></span>;
        }
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
 
