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
var Drop = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      canEdit: true,
      visible: false,
    };
  },
  // componentWillReceiveProps(nextProps, nextState) {
  //   console.log(nextProps.record);
  //   this.fetch(nextProps.record);
  // },
  componentDidMount() {
    if(this.props.record){
      this.fetch(this.props.record);
    }
        
    },
  fetch(record){
     this.setState({
            loading: true
        });
        var params = {
          id: record.id
        }
        Utils.ajaxData({
            url: '/modules/manage/rule/getRuleConfig.htm',
            data: params,
            callback: (result) => {
                this.setState({
                    loading: false,
                    data1: result.data.configList,
                    data: result.data.infoList,
                    record: result.data.rule
                });
            }
        });
  },
  rowKey() {
    return this.state.data.id;
  }, 
  rowKey1() {
    return this.state.data1.id;
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
      title: '结果评分',
      dataIndex: 'formula',
    }, {
      title: '分数',
      dataIndex: 'integral'
    },{
      title:'结果',
      dataIndex:'result',
    },{
      title:'权重',
      dataIndex:'sort',
    }];
    var columns1 = [{
      title: '表字段',
      dataIndex: 'columnComment',
    }, {
      title: '表达式',
      dataIndex: 'formula'
    },{
      title:'值',
      dataIndex:'cvalue',
    }, {
      title: '分数',
      dataIndex: 'integral',
    }, {
      title: '权重',
      dataIndex: 'sort',
    }];
    var columns2 = [{
      title: '表字段',
      dataIndex: 'columnComment',
    }, {
      title: '表达式',
      dataIndex: 'formula'
    },{
      title:'值',
      dataIndex:'cvalue',
    }, {
      title: '结果',
      dataIndex: 'result',
    }, {
      title: '权重',
      dataIndex: 'sort',
    }];
    return (
          <div>
          {state.record ? 
            <div>
           {state.data[0] ?
           <div>
           <p style={{width:"100px",height:"20px",color:"#2db7f5",fontSize:"15px",textAlign:"center",borderLeft:"2px solid #2db7f5",lineHeight:"20px" ,marginBottom:"10px"}}>决策引擎</p>
           <Table columns={columns} rowKey={this.rowKey} 
             dataSource={this.state.data}
             loading={this.state.loading} bordered />
             </div>
             :''}
           <p style={{width:"100px",height:"20px",color:"#2db7f5",fontSize:"15px",textAlign:"center",borderLeft:"2px solid #2db7f5",lineHeight:"20px" ,marginBottom:"10px"}}>规则配置</p>
           <Table columns={state.record.type == '10' ? columns1 : columns2} rowKey={this.rowKey1} 
             dataSource={this.state.data1}
             loading={this.state.loading} bordered />
             </div>
             : ""  }
             </div> 
        
        
    );
  }
});
Drop = createForm()(Drop);
export default Drop;