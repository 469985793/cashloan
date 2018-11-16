var React = require('react');
import antd from 'antd';  
var TreeTable = antd.TreeTable; 
var  Reflux = require('reflux');
var  MycustomerListStore = require('../stores/MycustomerListStore'); 
var  CustomerActions = require('../actions/CustomerActions'); 
var columns = [{
      title: '附件类型名称',
      dataIndex: 'rd_remark' 
      },{
        title: '附件类型编号',
        dataIndex: 'id' 
      },{
        title: '附件类型说明',
        dataIndex: 'rd_remark2' 
      },{
        title: '附件类型标示',
        dataIndex: "rd_ptype"
      },{
        title: '排序',
        dataIndex: 'rd_sort' 
      }];  
export default React.createClass({ 
    mixins: [
      Reflux.connect(MycustomerListStore, 'dataSource')
    ],
    getInitialState() {
      return {
         expandedRowKeys:[],
         dataSource: null 
       };
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
    
    getExpandeRowKeys(data){ 
      var me = this;
      var expandedRowKeys=[];

      data.length&&data.forEach(function (record, i) {  
        if(record.children){ 
          expandedRowKeys.push(me.rowKey(record));
          expandedRowKeys = expandedRowKeys.concat(me.getExpandeRowKeys(record.children));
        }
      });  

      return expandedRowKeys;
    },

    expandedAll(){ 
      var data = this.state.dataSource;
      var expandedRowKeys = this.getExpandeRowKeys(data);
      function sortNumber(a,b)
      {
        return a - b
      } 
      this.setState({expandedRowKeys:expandedRowKeys.sort(sortNumber).reverse()});//console.log(expandedRowKeys.sort(sortNumber).reverse());
    },
    collapseAll(){  
      this.setState({expandedRowKeys:[]});
    },
    onExpandedRowsChange(expandedRowKeys){
      this.setState({expandedRowKeys:expandedRowKeys});
    },
    render() { 
        if(!this.state.dataSource)
          return <div className="tableList"></div>  
        else return ( 
            <div className="tableList">
                <TreeTable rowKey={this.rowKey} size="small" 
                 expandedRowKeys={this.state.expandedRowKeys} 
                 onExpandedRowsChange={this.onExpandedRowsChange}
                 columns={columns} dataSource={this.state.dataSource} 
                 bordered={true} onClickRow={this.onClickRow} pagination={false}/> 
              
            </div>  
        );
    }
});
 
