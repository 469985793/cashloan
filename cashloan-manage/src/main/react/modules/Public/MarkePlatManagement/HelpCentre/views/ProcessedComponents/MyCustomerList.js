var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ProcessedListStore = require('../../stores/ProcessedListStore'); 
var  Actions = require('../../actions/CustomerActions');
var columns = [{
      title: 'ID',
      dataIndex: 'id',
    }, {
      title: '问题',
      dataIndex: 'title'
    }, {
      title: '发布时间',
      dataIndex: 'updateTime'
    }, {
      title: '答案',
      dataIndex: 'content',
      width:'50%'
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
 
