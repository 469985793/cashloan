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
        var me = this ;
        reqwest({
                url: '/modules/manage/system/user/list.htm',
                method: 'get', 
                type: 'json',
                data:{
                  start : 0, 
                  limit: 9999
                },
                success: (result) => {
                  var items  = result.datas.map((item)=>{
                      return (<Option key={item.id} value={item.id}>{item.name}</Option>);
                    });
                   me.setState({
                    userType:result.datas,
                   });
                }
              });
    },
    shouldComponentUpdate(nextProps, nextState) { 
      return nextState.dataSource !== this.state.dataSource;
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
       title: '序号',
       dataIndex: 'sort' 
       },{
         title: '名称',
         dataIndex: "title"
       },{
         title: '图片链接',
         dataIndex: "picPath"
       },{
         title: '发布时间',
         dataIndex: "addTime"
       },{
         title: '发布人',
         dataIndex: "addUserid",
         render: function(value) { 
           var userType = me.state.userType;
           var name="";
           userType.forEach((item,index)=>{
              if(value==item.id){
                name = item.name;
              }
           }); 
           return name;
         }  
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
 
