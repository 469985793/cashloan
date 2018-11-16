var React = require('react');
import antd from 'antd'; 
var Table = antd.Table; 
var  Reflux = require('reflux');
var  OtherListStore = require('../../stores/OtherListStore'); 
var  Actions = require('../../actions/CustomerActions');
var columns = [{
      title: '序号',
      dataIndex: 'sort',
    }, {
      title: '标题',
      dataIndex: 'title'
    }, {
      title: '内容',
      dataIndex: 'content',
      width:'60%',
      render: function(value) {
           value = value.replace(/<[^>]+>/g,"");
           return value
         } 
    }, {
      title: '图片地址',
      width:'20%',
      dataIndex: 'picPath'
    }];
 
export default React.createClass({ 
    mixins: [
      Reflux.connect(OtherListStore, 'dataSource')
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
 
