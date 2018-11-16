var React = require('react');
var Reflux = require('reflux'); 
import antd from 'antd';  
var Table = antd.Table;   
var reqwest = require('reqwest');
var IncreaseInspectionStore = require('../stores/IncreaseInspectionStore'); 
var  Actions = require('../actions/CustomerActions');
var columns = [{
        title: '序号',
        dataIndex: 'id' 
      }, {
        title: '字段名称',
        dataIndex: 'chineseName' 
      }, {
        title: '参数名',
        dataIndex: 'englishName'
      }, {
        title: '公式',
        dataIndex: 'formulaChinese' 
      }, {
        title: '备注',
        dataIndex: 'note' 
      } ];  
const rowSelection = {
  getCheckboxProps: function(record) {
    var dom = document.getElementById('formulaId');
    var formulaId=[];
    formulaId.push(dom.value);
    formulaId= formulaId[0].split(",");
    var judge=[];
    for (var i = 0; i < formulaId.length; i++) {
      judge = record.id == formulaId[i]
      if(judge)
      break
    }
    return {
      defaultChecked: judge
    };
  },
  onSelect: function(record, selected, selectedRows) {
    Actions.setSelectData(record,true,selectedRows);
  },
  onSelectAll: function(selected, selectedRows) {
    Actions.setSelectData(null,true,selectedRows);
  }
}; 

export default React.createClass({
    mixins: [
      Reflux.connect(IncreaseInspectionStore, 'dataSource'),
    ],
    getInitialState() {
      return {
        formData:{
          chineseName:'',
          englishName:''
        },
         dataSource: [] 
       };
    },   
    componentDidMount() { 
        Actions.initIncreaseInspectionStore(this.props.fileId);
    },
    componentWillReceiveProps(nextProps) {  
      if(nextProps.fileId!=this.props.fileId){
        Actions.initIncreaseInspectionStore(this.props.fileId);
      }
    },
    resetData (){
      var newValue = {
          chineseName:"",
          englishName:""
      };
      this.setState({
        formData:newValue,
      }); 
    }, 
    postMessage(){
      Actions.postMessage(this.props.selectRecord.id,this.state.formData);     
    }, 
    changeValue(e) { 
        var newValue = this.state.formData;
        var name = e.target.name;
        newValue[name] = e.target.value;
        this.setState({formData:newValue});
    },   
    selectChange(name,value) { 
      var newValue = this.state.formData;
       newValue[name] = value;
      this.setState({formData:newValue});
    }, 
    rowKey(record){
      return record.id;
    },
    render() {
        var state =this.state;
        var selectRecord=this.props.selectRecord;
        var formData = state.formData; 
        return ( <div>
              <form className="ant-form-inline">
                <div className="ant-form-item">
                  <label htmlFor="chineseName">字段名称：</label>
                  <input className="ant-input" type="text"  name="chineseName" value={formData.chineseName}  onChange={this.changeValue}  /> 
                </div>
                <input type="hidden"  id="formulaId" value={selectRecord?selectRecord.formulaId:null} />         
                <div className="ant-form-item">
                  <label htmlFor="englishName">参数名称：</label>
                  <input className="ant-input" type="text"  name="englishName" value={formData.englishName} onChange={this.changeValue}/>
                </div>               
                <div className="ant-form-item">
                   <input type="button" className="ant-btn ant-btn-primary" value="查 询" onClick={this.postMessage}/>
                </div>
                <div className="ant-form-item">
                   <input type="reset" className="ant-btn ant-btn-primary btn-reset" value="重 置" onClick={this.resetData}/>
                </div>
              </form>
              <div className="formulaWarp">
              <Table rowKey={this.rowKey} columns={columns} rowSelection={rowSelection} dataSource={this.state.dataSource} bordered={true} size="small"/>
              </div>
          </div> 
        );
    }
});
 
