var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  DetailList = require('./DetailList');
var  MasterStore = require('../stores/MasterStore'); 
var  ActionBtns2 = require('./ActionBtns2');
var  CustomerActions = require('../actions/CustomerActions');
var columns = [{
       title: '产品类别名称',
        dataIndex: 'productType' 
      },{
        title: '贷款类型',
        dataIndex: 'ptype',
        render:function(value){
          return {
                    1:'信贷',
                    2:'车贷',
                    3:"房贷"
                 }[value];
        } 
      },{
        title: '是否启用',
        dataIndex: "state",
        render: function (value) {
          if(value==0){
            return "是"
          } 
          else if(value==1){
            return "否"
          }
          else return <span></span>;
        } 
      }, {
        title: '创建时间',
        dataIndex: 'createDate'
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
      CustomerActions.setSelectData2(record,true);
      CustomerActions.initDetailStore(record.formulaId);
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
                <div className="head-top">公式配置  |  流程配置</div> 
                <ActionBtns2 />
                <DetailList />  
            </div>  
        );
    }
});
 
