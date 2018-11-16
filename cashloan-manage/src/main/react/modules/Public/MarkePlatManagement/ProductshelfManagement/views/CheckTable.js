import React from 'react';
import ReactDOM from 'react-dom';
import antd from 'antd';   
 
var Modal = antd.Modal;
var Table = antd.Table;
var reqwest = require('reqwest');

var CheckTable = React.createClass({ 
 
  getInitialState() { 
    return {  
        dataSource:[] 
      };
  }, 
  serchData(nextProps){
    if(nextProps){
      var url='/modules/cms/manager/action/ProductPutawayAction/selectProduct.htm';
      if(!nextProps.canEdit&&nextProps.selectRecord){
        url=url+'?searchParams='+JSON.stringify({
          id:nextProps.selectRecord.productId
        })
      }
      var me = this;
      var ptype = nextProps.ptype;
      reqwest({
                   url: url
                   , method: 'get'
                   ,data: {
                        start:0,
                        limit:10,
                        searchParams: JSON.stringify({"ptype":ptype}),
                     }
                   , type: 'json' 
                   , success: (result) => { 
                      var data = result.data;  
                      var dataSource = [];  
                     if(data.length)
                     {
                       dataSource = data.map((item,index)=>{
                          item.key = index; 
                          return item;
                       });   
                     } 
                      this.setState({dataSource});
                     
                   } 
               }) ; 
    }  
  }, 
  componentDidMount(){  
    this.serchData(this.props);
  },         
  componentWillReceiveProps(nextProps) {
    if(nextProps.selectRecord!=this.props.selectRecord||nextProps.ptype!=this.props.ptype)  
    {
      this.serchData(nextProps);
    }
  },
  onSelectRow(record, selected, selectedRows) {
    var pids= selectedRows.map(item =>{
      return item.id;
    });    
    this.props.setProductID(pids.join());
  },
  onSelectAllRow(selected, selectedRows) {
    var pids= selectedRows.map(item =>{
      return item.id;
    });    
    this.props.setProductID(pids.join()); 
  },
  render() {  
    var me = this;
    var state = this.state;    
    var props = this.props; 
    var columns = [{
          title: '产品编号',
          dataIndex: 'id' 
          },{
            title: '产品名称',
            dataIndex: 'name' 
          },{
            title: '年利率',
            dataIndex: 'apr' 
          },{
            title: '借款期限',
            dataIndex: "loanDeadline"
          },{
            title:'业务城市',
            dataIndex:"businessCity"
          },{
            title: '贷款类型',
            dataIndex: "ptype"
          }  
         ];
    
    var rowSelection = { 
      type:"radio",
      onSelect: this.onSelectRow,
      onSelectAll: this.onSelectAllRow
    };

    var data =state.dataSource;
    var table="";
     if(data.length)
         table = <Table rowSelection={props.canEdit?rowSelection:null} columns={columns} dataSource={data} bordered={true} size="small" pagination={true} />
    return <div >  
           {table} 
          </div>
  }
});
export default CheckTable;
