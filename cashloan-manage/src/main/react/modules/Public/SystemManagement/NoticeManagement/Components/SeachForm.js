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
            number:"",
            name: "",
            status: "",
            roleId:"" 
          },
          roleList:[] 
        };
    },
    resetData (){
      var newValue = {
          number:"",
          name: "",
          status:undefined,
          roleId:undefined
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
                 <label htmlFor="title">公告标题：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="title"  value={formData.title} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="content_like">公告内容：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="content_like" value={formData.content_like} onChange={this.changeValue}/>
               </div> 
               <div className="ant-form-item">
                  <label htmlFor="is_send">是否发布：</label>
                  <Select size="large" style={{width:100}} name="is_send" value={formData.is_send} onChange={this.selectChange.bind(this,'is_send')}>
                    <Option value={true}>是</Option>
                    <Option value={false}>否</Option>  
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
 
