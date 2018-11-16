var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 
var reqwest = require('reqwest');
export default React.createClass({ 
    mixins: [
      Reflux.connect(ListStore, 'dataSource')
    ],
    getInitialState() {
      return {
         userType:{},
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
      var me=this;
      var columns =[{
       title: 'ID',
       dataIndex: 'id' 
       },{
         title: '名称',
         dataIndex: "newFileName"
       },{
         title: '图片链接',
         dataIndex: "filePath"
       },{
         title: '发布人',
         dataIndex: "creatorEmpId" 
       },{
         title: '发布时间',
         dataIndex: "createTime"
       },{
         title: '跳转链接',
         dataIndex: "url"
       }];
        if(!this.state.dataSource)
          return <div className="tableListStore"></div>  
        else return ( 
            <div className="tableListStore">
                <Table rowKey={this.rowKey}   columns={columns} dataSource={this.state.dataSource} bordered={true} onClickRow={this.onClickRow}/>
            </div>  
        );
    }
});
 
