import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import InterfaceDetail from './InterfaceDetail';
const objectAssign = require('object-assign');
var Lookdetails = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      statisticsId:''
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.setState({
      statisticsId:nextProps.record.id
    })
    var params = {
      statisticsId: nextProps.record.id
    }
    this.fetch(params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    var params = {
        statisticsId: this.props.record.id
      }
    this.fetch(params);
  },
  handleCancel() {
    this.props.hideModal();
    
  },
  rowKey(record) {
    return record.id;
  },
  fetch(params) {
    var record= this.props.record;
    this.setState({
      loading: true
    });
      Utils.ajaxData({
      url: ' /modules/manage/rcdata/databusiness/listByDataId.htm',
      data: params,
      callback: (result) => {
        this.setState({
          loading: false,
          data: result.data,
        } );
        }
    });
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
 
  componentDidMount() {  
    //this.fetch();
  },

  showModal(title,record,canEdit){
    record.statisticsId = this.props.record.id
    this.setState({
      visible:true,
      canEdit:canEdit,
      record:record,
      title:title,
    },()=>{
      this.refs.InterfaceDetail.setFieldsValue(record);
    })
  },

  changeStatus(record,title) {
    var me = this;
        var params = {
                id: record.id
            }
            var url = title == "启用"?"/modules/manage/rcdata/databusiness/enable.htm":"/modules/manage/rcdata/databusiness/disable.htm";
            Utils.getData({
                url: url,
                data: params,
                callback: (result) => {
                    if(result.code==200){
                         Modal.success({
                             title: result.msg,
                            });
                    }else{
                         Modal.error({
                                title: result.msg,
                            });
                    }  
                    var p = {
                              statisticsId: me.state.statisticsId
                            }
                        this.fetch(p);
                }
            });
    },
 
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    var props = this.props;
    var state = this.state;
   var modalBtns= [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
    ];
    const hasSelected = selectedRowKeys.length > 0;
     var columns = [{
            title: '接口名称',
            dataIndex: 'name'
        }, {
            title: '简称',
            dataIndex: 'nid'
        }, {
            title: '状态',
            dataIndex:"state",
            render:(text)=>{
              return text == '10' ?'启用' : '禁用'
            }
        }, {
            title: '添加时间',
            dataIndex: 'addTime'
        }, {
          title:'操作',
          dataIndex:'',
          render:function(text,record){
            return <div style={{textAlign:"left"}}>
                      <a href="#" onClick={me.showModal.bind(me,'编辑',record,true)}>编辑</a>
                      <span className="ant-divider"></span>
                      {record.state =="20"?(<a href="#" onClick={me.changeStatus.bind(me,record,"启用")}>启用</a>):(<a href="#" onClick={me.changeStatus.bind(me,record,'禁用')}>禁用</a>)}
                   </div>
          }
        }];

    return (
        <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} > 
            <div className="block-panel">
              <div className="actionBtns" style={{ marginBottom: 16 }}>
                <button className="ant-btn" onClick={me.showModal.bind(me,'新增')}> 
                  新增
                </button>
              </div>
              <Table columns={columns} rowKey={this.rowKey}  size="middle" 
                dataSource={this.state.data}
                loading={this.state.loading}
                onChange={this.handleTableChange}  /> 
            </div> 
            <InterfaceDetail ref="InterfaceDetail" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
                canEdit={state.canEdit} />
        </Modal>
    );
  }
});
export default Lookdetails;