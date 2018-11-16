var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var  Actions = require('../actions/Actions'); 

export default React.createClass({ 
    mixins: [
      Reflux.connect(ListStore, 'dataSource')
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
      var me=this;
      var columns =[{
       title: 'ID',
       dataIndex: 'id' 
       },{
         title: '新闻类型',
         dataIndex: 'siteId',
         render: function(value) {
           var newsType = me.props.newsType;
           var name="";
           newsType.forEach((item,index)=>{
              if(value==item.id){
                name = item.name;
              }
           }); 
           return name;
         }  
       },{
         title: '标题',
         dataIndex: "title",
         className:'txt-nowrap',
       },{
         title: '发布时间',
         dataIndex: "updateTime",
         width:150,
       },{
         title: '发布人',
         dataIndex: "updateUserid"
       },{
         title: '文章来源',
         dataIndex: "source"
       },{
         title: '是否推荐',
         dataIndex: "isRecommend",
         render: function(value) {
           return {
                    1 : '是',
                    0 : '否'
           }[value];
         } 
       },{
         title: '是否置顶',
         dataIndex: "isTop",
         render: function(value) {
           return {
                    1 : '是',
                    0 : '否'
           }[value];
         } 
       },{
         title: '跳转链接',
         dataIndex: "url",
         className:'txt-nowrap',
       },{
         title: '状态',
         dataIndex: "status",
         render: function(value) {
           return {
                    1 : '显示',
                    0 : '隐藏'
           }[value];
         } 
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
 
