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
            name:"",
            code: ""
          },
          officeIdlist:[],
          timeLimitlist:[]
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
    componentDidMount() { 
        var me = this ;
        reqwest({
                url: '/modules/common/action/ComboAction/queryOffices.htm?where.type=2',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   me.setState({officeIdlist:items});
                }
              });
        reqwest({
                url: '/modules/common/action/ComboAction/queryDic.htm?where.dic.type_code=LOAN_PERIOD_CAR',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   me.setState({timeLimitlist:items});
                }
              });
    },
    render() { 
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="name">字典类型：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="name"  value={this.state.name} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="code">类型代码：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="code" value={this.state.code} onChange={this.changeValue}/>
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
 
