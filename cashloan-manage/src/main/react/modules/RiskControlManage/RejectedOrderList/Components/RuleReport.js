//规则报告
import React from 'react'
import {
  Table,
  Modal
} from 'antd';
const NavLine = require("../../../utils/NavLine");
 require("./style.css");
var RuleReport = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      resultList: [],
      ruleList: [],
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    if (nextProps.visible) {
      this.fetch();
    }
  },

  fetch() {
    var record = this.props.record;
    this.setState({
      loading: true
    });

    var record = this.props.record;


    Utils.ajaxData({
      url: '/modules/manage/review/findResult.htm',
      data: { borrowId: record.id },
      callback: (result) => {
        var data = result.data;
        for(var i = 0; i < data.ruleList.length; i++){
          data.resultList[0][i].ruleName = data.ruleList[i].ruleName;
        }
        this.setState({
          loading: false,
          resultList: data.resultList[0],
          ruleList: data.ruleList
        });
      }
    });
  },

  renderTitle(item) {
    return
  },
  rowKey(record) {
    return record.id;
  },
  render() {
    var me = this;
    var state = this.state;
    var pagination = false;
    var columns = [{
      title: '字段描述',
      dataIndex: 'colName',
      render: (text,render) => {
        return render.tbName + '/' + text
      }
    }, {
      title: '运算符',
      dataIndex: 'rule'
    }, {
      title: '值',
      dataIndex: 'value'
    }, {
      title: '结果',
      dataIndex: 'resultType',
      render: (text,record) => {
        if(record.result == 'Y'){
          switch(text){
            case '通过': return <span style={{display:'block',background:'green',width:'100%',height:'100%',color:'white'}} >{text}</span>
            case '不通过': return <span style={{display:'block',background:'red',width:'100%',height:'100%',color:'white'}} >{text}</span>  
            case '人工复审': return <span style={{display:'block',background:'yellow',width:'100%',height:'100%'}} >{text}</span>      
          }
        }else{
          return <span>{text}</span> 
        }
      }
    }, {
      title: '当前值',
      dataIndex: 'matching'
    }, {
      title: '命中结果',
      dataIndex: 'result',
      render: (text) => { return text == 'Y' ? <span style={{display:'block',background:'yellow',width:'100%',height:'100%'}} >命中</span> : <span>未命中</span> }
    }];
    let navLineData = {};
    let index = 0;
    if (state.ruleList) {
      for (let item of state.ruleList) { 
        navLineData[item.ruleName] = '#rule' + index;
        index++;
      };
    }
    return (
      
      <div style={{ position: "relative" }}>
        {state.resultList[0] ? <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, navLineData)}>
          <div className="col-20 navLine-wrap-left ruleReport"  >
            {
               state.resultList.map((item, index) => {
                return (<div id={`rule${index}`} key={index}>
                  <div className={`tbTitle ${index==0?'':'border-top'}`}>
                    <div>
                      <span style={{ marginRight: 15 }}>规则名称：{item.ruleName}</span>
                      <span >运行结果：{item.resultType}</span>
                    </div>
                    <div>
                      <span style={{ marginRight: 15 }}>规则条数：{item.total}条</span>
                      <span style={{ marginRight: 15 }}>通过条数：{item.pass}条</span>
                      <span style={{ marginRight: 15 }}>不通过条数：{item.noPass}条</span>
                      <span>待人工复审：{item.review}条</span>
                    </div>
                  </div >
                  <Table columns={columns} size='middle'
                    rowKey={this.rowKey}
                    dataSource={item.infoList}
                    bordered 
                    pagination={pagination}
                    />
                </div>)
              })
            }
          </div>
          {/*<div className="navLine-wrap-right" >
            <NavLine navLineData={navLineData} activeItem="#rule0" ref="NavLine" />
          </div>*/}
        </div>
        :<div style={{height: '200px'}}></div>}
      </div>
      

    );
  }
});
export default RuleReport;