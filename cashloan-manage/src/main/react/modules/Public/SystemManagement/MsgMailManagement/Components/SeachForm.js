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
          typeList:[] 
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
                url: '/modules/manage/system/dict/cache/list.htm?type=MSG_TEMPLATE_TYPE',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.MSG_TEMPLATE_TYPE.map((item)=>{
                      return (<Option key={item.value} value={String(item.value)}>{item.text}</Option>);
                    });
                   me.setState({typeList:items});
                }
              });
    },
    render() { 
        var state =this.state;
        var formData = state.formData;
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="id">模板编号：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="id"  value={formData.id} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="name">模板名称：</label>
                 <input className="ant-input" type="text"  placeholder="请输入..." name="name" value={formData.name} onChange={this.changeValue}/>
               </div> 
               <div className="ant-form-item">
                 <label htmlFor="type">模板类型：</label>
                 <Select size="large" style={{width:100}} name="type" value={formData.type} onChange={this.selectChange.bind(this,'type')}>
                      {state.typeList}
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
 
