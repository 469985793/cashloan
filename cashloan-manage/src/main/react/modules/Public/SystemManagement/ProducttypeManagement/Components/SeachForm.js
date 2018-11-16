var React = require('react');
import antd from 'antd';  
var Select = antd.Select;
var Option = Select.Option; 
var reqwest = require('reqwest');
var Actions = require('../actions/CustomerActions');
export default React.createClass({
    propTypes:{
      onClickSearch:React.PropTypes.func.isRequired
    },
    getInitialState() {
        return {
          formData:{ 
            id:""
          },
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
       //console.log(searchData)
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
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="id">产品类型名称：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="id"  value={this.state.id} onChange={this.changeValue}/> 
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
 
