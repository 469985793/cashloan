import React from 'react'
import {
  Table,
  Modal,
  Form,
  Row,
  Col,
  Input,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const objectAssign = require('object-assign');
var AdjustRecord = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch();
  },
  hideModal() {
    this.setState({
      visible: false,
    });

  },
  handleCancel() {
    this.props.hideModal();
    
  },
  rowKey(record) {
    return record.id;
  },

  //分页
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },
  fetch(params = {}) {
    var record= this.props.record;
    this.setState({
      loading: true
    });

    if (!params.pageSize && record) {
      var searchdata = {};
      searchdata = {
        consumerNo: record.consumerNo
      }
      var params = {};
      params = {
        search : JSON.stringify(searchdata),
        pageSize: 5, 
        current: 1,
      }
    }
    if (record) {
      Utils.ajaxData({
      url: '/modules/manage/user/creditLog/page.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination
        });
      }
    });
    }
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var record= this.props.record;
    var searchdata = {};
      searchdata = {
        consumerNo: record.consumerNo
    }
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      search : JSON.stringify(searchdata),
    });
    this.fetch(params);
  },

  render() {
    var me = this;
    const {
            getFieldProps
        } = me.props.form;
    var props = me.props;
    var state = me.state;
    const {
      loading,
      selectedRowKeys
    } = state;
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '变更时间',
      dataIndex: 'modifyTime'
    }, {
      title: '变更类型',
      dataIndex: "type"
    }, {
      title: '变更额度(元)',
      dataIndex: 'modifyTotal'
    }, {
      title: '变更内容',
      dataIndex: 'remark'
    }, {
      title: '变更后可用额度(元)',
      dataIndex: 'now'
    }];
     const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
    var modalBtns  = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
    return (
      <Modal title={this.props.title} visible={this.props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
           <Form horizontal  form={this.props.form}>
                
            <Row>
              <Col span="11">
                <FormItem  {...formItemLayout} label="客户姓名：">
                  <Input disabled={true}  {...getFieldProps('realName')} type="text"  />
                </FormItem> 
              </Col>
              <Col span="11">
                <FormItem  {...formItemLayout} label="身份证号码：">
                  <Input disabled={true}  {...getFieldProps('idNo')} type="text"  />
                </FormItem> 
              </Col>
            </Row>
            <Row>
              <Col span="11">
                <FormItem  {...formItemLayout} label="额度类型：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('creditName',{ rules: [{required:true,message:'必填'}]})} type="text"  />
                </FormItem> 
              </Col>
              <Col span="11">
                <FormItem  {...formItemLayout} label="当前可用额度(元)：">
                  <Input disabled={true}  {...getFieldProps('unuse')} type="text"  />
                </FormItem> 
              </Col>
            </Row>   
                      
          </Form>
           <Table columns={columns} rowKey={this.rowKey}  size="middle" 
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />   
         
         </Modal>
        
    );
  }
});
AdjustRecord = createForm()(AdjustRecord);
export default AdjustRecord;