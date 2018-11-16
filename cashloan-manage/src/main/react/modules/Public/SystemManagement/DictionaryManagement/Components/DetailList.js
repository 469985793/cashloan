var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  DetailStore = require('../stores/DetailStore'); 
var  CustomerActions = require('../actions/CustomerActions');
var columns = [{
     title: '父节点',
      dataIndex: 'parentId' 
      },{
        title: '名称',
        dataIndex: 'itemValue' 
      },{
       title: '代码',
        dataIndex: 'itemCode' 
      }];  
export default React.createClass({ 
    mixins: [
      Reflux.connect(DetailStore, 'detailDataSource') 
    ],
    getInitialState() {
      return {
         detailDataSource: null 
       };
    }, /* 
    shouldComponentUpdate(nextProps, nextState) { 
      return nextProps.detailDataSource !== this.props.detailDataSource;
    },*/ 
    componentWillMount() { 
      /*  var processInstanceId=this.props.processInstanceId;
        CustomerActions.initDetailStore(processInstanceId);*/
    },
    onClickRow(record){
      CustomerActions.setSelectData2(record,true);
    },
    rowKey(record){
      return record.id;
    },
    render() { 
        return (  
                <Table rowKey={this.rowKey} columns={columns} dataSource={this.state.detailDataSource} bordered={true} onClickRow={this.onClickRow}/>
            );
    }
});
