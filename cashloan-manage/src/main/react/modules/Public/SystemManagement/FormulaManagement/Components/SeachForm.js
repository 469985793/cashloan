var React = require('react');
import antd from 'antd';  
var Select = antd.Select;
var Option = Select.Option; 
var reqwest = require('reqwest'); 
var  Actions = require('../actions/Actions');
export default React.createClass({ 
    getInitialState() {
        return { 
          formData:{
            englishName:"",
            chineseName: "",
            unit: "",
            source:"" 
          },
          roleList:[] 
        };
    },
    resetData (){
      var newValue = {
          chineseName:"",
          englishName: "",
          unit:undefined,
          source:undefined
      };
      this.setState({
        formData:newValue
      });
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
    postMessage() {  
        var searchData = this.state.formData ; 
        Actions.postMessage(searchData);  
    },
    componentDidMount() { 
        var me = this ;
        reqwest({
                url: '/modules/manage/system/role/list.htm',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.roles.map((item)=>{
                      return (<Option key={item.id} value={String(item.id)}>{item.name}</Option>);
                    });
                   me.setState({roleList:items});
                }
              });
    },
    render() { 
        var formData = this.state.formData;
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="chineseName">字段名称：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="chineseName"  value={formData.chineseName} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="englishName">参数名称：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="englishName" value={formData.englishName} onChange={this.changeValue}/>
               </div> 
               <div className="ant-form-item">
                 <label htmlFor="unit">计数单位：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="unit"  value={formData.unit} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                  <label htmlFor="source">数据来源：</label>
                  <Select size="large" style={{width:100}} name="source" value={formData.source} onChange={this.selectChange.bind(this,'source')}>
                    <Option value="1">系统计算</Option>
                    <Option value="2">人工录入</Option>  
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
 
