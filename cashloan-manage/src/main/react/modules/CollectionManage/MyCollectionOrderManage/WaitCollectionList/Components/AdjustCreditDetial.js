import React from 'react'
import {
  Table,
  Modal,
  Form,
  Row,
  Col,
  Input,
  Popover,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const objectAssign = require('object-assign');
var AdjustCreditDetial = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.setState({
                dataRecord: nextProps.dataRecord
            })
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
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
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
      title: '催收方式',
      dataIndex: 'way',
       render(text,record){
         if(text == '10'){
            return <span>电话</span>;
         }else if(text == '20'){
            return <span>邮件</span>;
        }else if(text == '30'){
            return <span>短信</span>;
        }else if(text == '40'){
            return <span>现场沟通</span>;
        }else if(text == '50'){
            return <span>其他</span>;
        }
      }
    }, {
      title: '催收时间',
      dataIndex: 'createTime'
    }, {
      title: '催收内容',
      dataIndex: 'remark',
      render: (text, record) => {
                if (text.length >= 15) {
                    return <Popover content={text} overlayStyle={{width:"200px"}}>
                                <span>{text.substring(0,15)}...</span>
                           </Popover>
                } else {
                    return <span>{text}</span>
                }
            }
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
      <Modal title={this.props.title} visible={this.props.visible} onCancel={this.handleCancel} width="800"  footer={modalBtns} >
           <Table columns={columns} rowKey={this.rowKey}  size="middle" 
             dataSource={this.state.dataRecord}
             loading={this.state.loading}
             onChange={this.handleTableChange} bordered />   
         </Modal>
        
    );
  }
});
AdjustCreditDetial = createForm()(AdjustCreditDetial);
export default AdjustCreditDetial;