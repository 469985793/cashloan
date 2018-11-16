import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab2 = React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
      pagination: {},
    };
  },
  rowKey(record) {
    return record.id;
  },
  componentWillReceiveProps(nextProps){
    if(nextProps.visible){
      //this.fetch();
    }
  },
  componentDidMount(){
    //this.fetch();
  },
  showModal(title, record, canEdit){
    this.props.showModal(title, record, canEdit);
  },
  changeStatus(title,record){
    this.props.changeStatus(title,record);
  },
  render() {
    var me = this;
    var columns = [{
      title: '参数编号',
      width:100,
      dataIndex: 'code'
    }, {
        title: '参数名称',
        width:100,
        dataIndex: "name"
      }, {
        title: '参数值',
        dataIndex: 'value'
      },{
        title: '参数类型',
        dataIndex: 'typeStr'
      },{
        title: '备注',
        width:100,
        dataIndex: "remark"
      }, {
        title: '状态',
        width:100,
        dataIndex: "status",
        render:(text) => {
          if(text == 1){
            return "启用"
          }else{
            return "禁用"
          }
        }
      }, {
        title: '操作',
        width:100,
        dataIndex: "",
        render(text, record) {
          return (
          <div style={{ textAlign: "left" }}>
            <a href="#" onClick={me.showModal.bind(me, '修改', record, true) }>修改 </a>
            {record.status == 1 ? <a href="#" onClick={me.changeStatus.bind(me, '禁用', record) }>禁用</a> : <a href="#" onClick={me.changeStatus.bind(me, '启用', record) }>启用</a>}
            </div>
            )
        }
      }];
    return (<div className="block-panel">
              <Table columns={columns} rowKey={this.rowKey}  size="middle"
              dataSource={this.props.record}
              pagination={this.props.pagination}
              loading={this.state.loading}
              onChange={this.props.handleTableChange}  />
          </div>
    );
  }
});
export default Tab2;