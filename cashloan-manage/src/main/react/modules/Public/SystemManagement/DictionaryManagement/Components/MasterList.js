var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  DetailList = require('./DetailList');
var  MasterStore = require('../stores/MasterStore'); 
var  ActionBtns2 = require('./ActionBtns2');
var  CustomerActions = require('../actions/CustomerActions');
var columns = [{
        title: '字典类型',
        dataIndex: 'name' 
      }, {
        title: '类型代码',
        dataIndex: 'code' 
      },{
        title: '备注',
        dataIndex: "remark" 
      }, {
        title: '排序',
        dataIndex: "sort"
      }];  
export default React.createClass({ 
    mixins: [
      Reflux.connect(MasterStore, 'masterDataSource') 
    ],
    getInitialState() {
      return {
         masterDataSource: null 
       };
    },    
    componentDidMount() { 
        CustomerActions.initStore();
    },
    onClickRow(record){ 
      CustomerActions.setSelectData(record,true);
      CustomerActions.setSelectData2({
            isSelectRecord:false,
            record:null
        });
      CustomerActions.initDetailStore(record.id);
    },
    rowKey(record){
      return record.id;
    },
    render() { 
        if(!this.state.masterDataSource) 
          return <div className="tableList"></div>  
        else return (  
            <div className="tableList">
                <Table  selectDefaultRow={0}
                        rowKey={this.rowKey}
                        columns={columns} 
                        dataSource={this.state.masterDataSource}
                        bordered={true}
                        onClickRow={this.onClickRow}/>
                <div className="head-top">字典管理详细项</div> 
                <ActionBtns2 />
                <DetailList />  
            </div>  
        );
    }
});
 
