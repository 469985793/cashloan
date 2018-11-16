var React = require('react');
import { Select} from 'antd';  
var Option = Select.Option; 
var reqwest = require('reqwest'); 
var  Actions = require('../actions/Actions');
export default React.createClass({ 
    getInitialState() {
        return { 
          formData:{
            name:"",
            phone: "",
            email: "",
            certNumber:"",
            status:""
          },
          customerlist:[] 
        };
    },
    resetData (){
      var newValue = {
          name:"",
          phone: "",
          email:"",
          certNumber:"",
          status:""
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
                url: "/modules/manage/system/role/list.htm",
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.roles.map((item)=>{
                      return (<Option key={item.id} value={String(item.id)}>{item.name}</Option>);
                    });
                   me.setState({customerlist:items});
                }
              });
    },
    render() { 
        var formData = this.state.formData;
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="name">姓名：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="name"  value={formData.name} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="phone">手机号：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="phone" value={formData.phone} onChange={this.changeValue}/>
               </div> 
               <div className="ant-form-item">
                 <label htmlFor="email">邮箱：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="email" value={formData.email} onChange={this.changeValue}/>
               </div> 
               <div className="ant-form-item">
                 <label htmlFor="certNumber">身份证号：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="certNumber" value={formData.certNumber} onChange={this.changeValue}/>
               </div> 
               <div className="ant-form-item">
                  <label htmlFor="status">账户状态：</label>
                  <Select size="large" style={{width:100}} name="status" value={formData.status} onChange={this.selectChange.bind(this,'status')}>
                    <Option value="0">正常</Option>
                    <Option value="1">已冻结</Option>  
                  </Select>
               </div>               
               <div className="ant-form-item">
                   <label htmlFor="customerFrom">客户来源：</label>
                   <Select size="large"  style={{width:150}} name="customerFrom" value={formData.customerFrom} onChange={this.selectChange.bind(this, 'customerFrom')}>
                     {this.state.customerlist}
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
 
