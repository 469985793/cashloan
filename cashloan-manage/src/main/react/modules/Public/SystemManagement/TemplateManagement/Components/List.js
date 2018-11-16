var React = require('react');
import antd from 'antd';  
var Table = antd.Table; 
var Modal = antd.Modal;
var  Reflux = require('reflux');
var  ListStore = require('../stores/ListStore'); 
var reqwest = require('reqwest');
var  Actions = require('../actions/Actions');
   
const rowSelection = {
  onSelect: function(record, selected, selectedRows) {
    Actions.setSelectData(record,true,selectedRows);
  },
  onSelectAll: function(selected, selectedRows) {
    Actions.setSelectData(null,true,selectedRows);
  }
};      
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
    download(url){
        if(url){ 
        var url = url.replace(/^\/[^\/]+\//, '');
        url = '/modules/common/download/' + url + '.htm';
        //console.log(url)
                reqwest({
                          url: '/modules/common/download/isFileExists.htm',
                          method: 'post',
                          data: {
                          filename: url.replace(/.*[\\/](.*\/.*?)(?:\.htm)?$/, '$1')
                       },
                          success: (result) => {
                            if(result.exists==true){
                                location.href = url
                            }else{
                                Modal.error({
                                title: result.msg
                             }); 
                                this.setState({
                                   loading:false
                                });
                              }
                            }
                      })
       }else{
          Modal.error({
              title: "请上传模板"
          }); 
       }                    
    },

    render() { 
      var me = this;
      var columns = [{
      title: '编号',
      dataIndex: 'id' 
      },{
        title: '模板名称',
        dataIndex: 'name' 
      },
      {
        title: '模板类型',
        dataIndex: 'type_text' 
      },{
        title: '模板说明',
        dataIndex: "remark"
      },{
        title: '模板下载',
        dataIndex: "url",
        render(url) {
          return <a href="javascript:;" onClick={me.download.bind(me,url)}>模板文件下载</a>;;
      }    
      },{
        title: '上传时间',
        dataIndex: "upload_time",
      }];
        if(!this.state.dataSource)
          return <div className="tableListStore"></div>  
        else return ( 
            <div className="tableListStore">
                <Table rowKey={this.rowKey} rowSelection={rowSelection} columns={columns} dataSource={this.state.dataSource} bordered={true} />
            </div>  
        );
    }
});
 
