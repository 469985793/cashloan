var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  DetailStore = require('../stores/DetailStore'); 
var  CustomerActions = require('../actions/CustomerActions');
var columns = [{
        title: '名称',
        dataIndex: 'chineseName' 
      },{
        title: '参数名',
        dataIndex: 'englishName' 
      },{
       title: '单位',
        dataIndex: 'unit' 
      }, {
        title: '数据来源',
        dataIndex: 'dataSource',
        render: function(value) {
          if(value == 1)
            return "人工录入";
          else return "系统计算";
        }
      },{
      title: '公式',
        dataIndex: 'formulaChinese' 
      },{
        title: '备注',
        dataIndex: 'note' 
      },{
       title: '创建时间',
        dataIndex: 'totalAccount' 
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
      CustomerActions.setSelectData(record,true);
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
