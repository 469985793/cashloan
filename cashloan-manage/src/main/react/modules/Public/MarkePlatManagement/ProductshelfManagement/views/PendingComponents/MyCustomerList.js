var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  PendingListStore = require('../../stores/PendingListStore'); 
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
      dataIndex: 'businessCity',
    }, {
      title: '上架时间',
      dataIndex: 'putawayTime'
    }];

  
export default React.createClass({ 
    mixins: [
      Reflux.connect(PendingListStore, 'dataSource')
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
                <Table rowKey={this.rowKey}   columns={columns} dataSource={this.state.dataSource} bordered={true} onClickRow={this.onClickRow}/>
            </div>  
        );
    }
});
 
