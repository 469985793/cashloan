var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ProcessedListStore = require('../../stores/ProcessedListStore'); 
var  Actions = require('../../actions/CustomerActions');
var columns = [{
      title: '上架编号',
      dataIndex: 'id',
    }, {
      title: '上架名称',
      dataIndex: 'name'
    }, {
      title: '贷款类型',
      dataIndex: 'ptype'
    }, {
      title: '业务城市',
      dataIndex: 'businessCity' 
    }, {
      title: '下架时间',
      dataIndex: 'soldoutTime'  
    }];
 
export default React.createClass({ 
    mixins: [
      Reflux.connect(ProcessedListStore, 'dataSource')
    ],
    getInitialState() {
      return {
         dataSource: null 
       };
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
                <Table rowKey={this.rowKey}   columns={columns} dataSource={this.state.dataSource} bordered={true} onClickRow={this.onClickRow}/>
            </div>  
        );
    }
});
 
