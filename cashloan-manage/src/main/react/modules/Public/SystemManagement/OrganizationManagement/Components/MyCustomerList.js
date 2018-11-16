var React = require('react');
import antd from 'antd';  
var TreeTable = antd.TreeTable; 
var  Reflux = require('reflux');
var reqwest = require('reqwest');
var  MycustomerListStore = require('../stores/MycustomerListStore'); 
var  CustomerActions = require('../actions/CustomerActions'); 
var columns = [{
      title: '机构名称',
      dataIndex: 'name' 
      },{
        title: '备注',
        dataIndex: 'remark' 
      },{
        title: '排序',
        dataIndex: "sort"
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
      var id = record.id;
      reqwest({
                url: '/modules/system/getOfficeInfoById.htm'
                , method: 'get'
                ,data: {
                     id:id,
                  }
                , type: 'json' 
                , success: (result) => { 
                if(result){
                     CustomerActions.setCitySelectRecord(result.data.city);
                } 
                } 
            });
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
                <TreeTable rowKey={this.rowKey} size="small" expandedRowKeys={this.state.expandedRowKeys} onExpandedRowsChange={this.onExpandedRowsChange}
                 columns={columns} dataSource={this.state.dataSource} bordered={true} onClickRow={this.onClickRow} pagination={false}/> 
              
            </div>  
        );
    }
});
 
