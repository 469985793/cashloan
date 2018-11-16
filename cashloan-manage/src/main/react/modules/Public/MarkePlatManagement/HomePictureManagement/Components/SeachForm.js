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
            },
            certTypeChildren:[],  
        };
    },
    resetData (){
      var newValue={};
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

    render() { 
        var state =this.state;
        var formData = state.formData; 
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="id">标题：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="id"  value={formData.id} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                  <label htmlFor="certType">新闻类型：</label>
                  <Select size="large" style={{width:100}} name="certType" value={formData.certType} onChange={this.selectChange.bind(this,'certType')}>
                    {this.props.certTypeChildren}
                  </Select>
               </div>               
               <div className="ant-form-item">
                 <label htmlFor="certNumber">发布人：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="certNumber" value={formData.certNumber} onChange={this.changeValue}/>
               </div>
               <div className="ant-form-item">
                 <label htmlFor="projectCode">发布时间：</label>
               </div>
               <div className="ant-form-item">
                 <DatePicker disabledDate={this.disabledStartDate}  value={formData.startTimeFrom} placeholder="开始日期" onChange={this.selectChange.bind(this, 'startTimeFrom')} />
               </div>
               <div className="ant-form-item">
                 <label htmlFor="carMessageId">至：</label>
               </div>
               <div className="ant-form-item">
                 <DatePicker disabledDate={this.disabledEndDate}  value={formData.startTimeTo}  placeholder="结束日期" onChange={this.selectChange.bind(this, 'startTimeTo')} />
               </div> 
               <div className="ant-form-item">
                 <label htmlFor="isSystemEnter">发布状态：</label>
                 <Select size="large" style={{width:100}} name="isSystemEnter" value={formData.isSystemEnter} onChange={this.selectChange.bind(this,'isSystemEnter')}>
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
 
