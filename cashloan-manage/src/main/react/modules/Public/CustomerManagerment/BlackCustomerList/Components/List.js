var React = require('react');
import antd from 'antd';
var Table = antd.Table;
var Reflux = require('reflux');
var ListStore = require('../stores/ListStore');
var Actions = require('../actions/Actions');
var columns = [{
  title: '客户ID',
  dataIndex: 'id'
}, {
  title: '客户姓名',
  dataIndex: 'customerName'
},
  {
    title: '证件类型',
    dataIndex: 'certType',
    render: function(value) {
      if (value == 0)
        return "居民身份证";
      else return "";
    }
  }, {
    title: '证件号码',
    dataIndex: "certNumber"
  }, {
    title: '身份认证',
    dataIndex: "certIdentified",
    render: function(value) {
      if (value == 1)
        return "已认证";
      else return "未认证";
    }
  }, {
    title: '是否本系统录入',
    dataIndex: "isSystemEnter",
    render: function(value) {
      if (value == 1)
        return "是";
      else return "否";
    }
  }, {
    title: '备注',
    dataIndex: "remark"
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
  componentDidMount() {
    Actions.initStore();
  },
  onClickRow(record){
    Actions.setSelectData(record, true);
  },
  rowKey(record){
    return record.id;
  },
  render() {
    return (
      <div className="tableListStore">
        <Table rowKey={this.rowKey} columns={columns} dataSource={this.state.dataSource} bordered={true}
               onClickRow={this.onClickRow}/>
      </div>
    );
  }
});
 
