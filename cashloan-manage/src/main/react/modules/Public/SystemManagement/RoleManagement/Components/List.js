var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 
var columns = [{
        title: '角色名称',
        dataIndex: 'name' 
      },{
        title: '角色唯一标示',
        dataIndex: 'nid' 
      },{
        title: '是否禁用',
        dataIndex: 'isDelete',
        render: function(value) {
          if(value==0)
            return "否";
          else return "是";
        }  
      },{
        title: '创建时间',
        dataIndex: "addTime"
      },{
        title: '创建人',
        dataIndex: "addUser"
      },{
        title: '修改时间',
        dataIndex: "updateTime"
      },{
        title: '修改人',
        dataIndex: 'updateUser',
      },{
        title: '备注',
        dataIndex: 'remark', 
      }];  
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
          return <div className="tableList"></div>  
        else return ( 
            <div className="tableList">
                <Table rowKey={this.rowKey}   columns={columns} dataSource={this.state.dataSource} bordered={true} onClickRow={this.onClickRow}/>
            </div>  
        );
    }
});
 
