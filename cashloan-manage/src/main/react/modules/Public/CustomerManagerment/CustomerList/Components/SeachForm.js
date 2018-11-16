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
            },
            certTypeChildren:[], 
            statusChildren:[]  
        };
    },
    resetData (){
      var newValue = {
          id:"",
          name: "",
          certType:"",
          certNumber:"", 
          status:"",
          loans:""
      };
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
    componentDidMount() { 
        var me = this ;
        reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=ID_TYPE',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.ID_TYPE.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   me.setState({certTypeChildren:items});
                }
              });
        reqwest({
                url: '/modules/manage/system/dict/cache/list.htm?type=CUSTOMER_STATUS',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.CUSTOMER_STATUS.map((item)=>{
                      return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                   me.setState({statusChildren:items});
                }
              });
    },
    render() {
        var state =this.state;
        var formData = state.formData; 
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="id">客户ID：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="id"  value={formData.id} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="name">客户姓名：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="name" value={formData.name} onChange={this.changeValue}/>
               </div>              
               <div className="ant-form-item">
                 <label htmlFor="certNumber">证件号码：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="certNumber" value={formData.certNumber} onChange={this.changeValue}/>
               </div>
               <div className="ant-form-item">
                 <label htmlFor="loans">贷款次数：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="loans" value={formData.loans} onChange={this.changeValue}/>
               </div>  
               <div className="ant-form-item">
                  <label htmlFor="status">客户状态：</label>
                  <Select size="large" style={{width:100}} name="status" value={formData.status} onChange={this.selectChange.bind(this,'status')}>
                    {state.statusChildren}
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
 
