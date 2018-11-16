var React = require('react');
import antd from 'antd'; 
var DatePicker = antd.Datepicker;  
var Select = antd.Select;
var Option = Select.Option; 

var  Actions = require('../actions/Actions');
export default React.createClass({
     
    getInitialState() {
        return {
            formData:{
            title:"",
            siteId: "",
            certNumber: "",
            startTime:"",
            endTime:"",
            status:""
            },
            certTypeChildren:[],  
        };
    },
    resetData (){
      var newValue={
            title:"",
            siteId: "",
            certNumber: "",
            startTime:"",
            endTime:"",
            status:""
      };
      var formData = this.state.formData;
      Object.keys(formData).forEach((name)=>{
        newValue[name]='';
      });
      this.setState({
        formData:newValue
      }); 
    },   
    postMessage() {  
        var searchData = this.state.formData ; 
        Actions.postMessage(searchData); 
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
    disabledStartDate(startValue) {
      if (!startValue || !this.state.formData.endTime) {
        return false;
      }
      return startValue.getTime() >= this.state.formData.endTime.getTime();
    },
    disabledEndDate(endValue) {
      if (!endValue || !this.state.formData.startTime) {
        return false;
      }
      return endValue.getTime() <= this.state.formData.startTime.getTime();
    },
    render() { 
        var state =this.state;
        var formData = state.formData; 
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="title">标题：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="title"  value={formData.title} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                  <label htmlFor="siteId">新闻类型：</label>
                  <Select size="large" style={{width:100}} name="siteId" value={formData.siteId} onChange={this.selectChange.bind(this,'siteId')}>
                    {this.props.certTypeChildren}
                  </Select>
               </div>               
               <div className="ant-form-item">
                 <label htmlFor="certNumber">发布人：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="certNumber" value={formData.certNumber} onChange={this.changeValue}/>
               </div>
               <div className="ant-form-item">
                 <label htmlFor="startTime">发布时间：</label>
               </div>
               <div className="ant-form-item">
                 <DatePicker disabledDate={this.disabledStartDate}  value={formData.startTime} placeholder="开始日期" onChange={this.selectChange.bind(this, 'startTime')} />
               </div>
               <div className="ant-form-item">
                 <label htmlFor="endTime">至：</label>
               </div>
               <div className="ant-form-item">
                 <DatePicker disabledDate={this.disabledEndDate}  value={formData.endTime}  placeholder="结束日期" onChange={this.selectChange.bind(this, 'endTime')} />
               </div> 
               <div className="ant-form-item">
                 <label htmlFor="status">发布状态：</label>
                 <Select size="large" style={{width:100}} name="status" value={formData.status} onChange={this.selectChange.bind(this,'status')}>
                    <Option value="1">已发布</Option>
                    <Option value="0">待发布</Option>
                 </Select>
               </div>              
               <div className="ant-form-item">
                  <input type="button" className="ant-btn ant-btn-primary" value="查 询" onClick={this.postMessage}/>
               </div>
               <div className="ant-form-item">
                  <input type="reset" className="ant-btn ant-btn-primary btn-reset" value="重 置" onClick={this.resetData}/>
               </div>
             </form>
        );
    }
});
 
