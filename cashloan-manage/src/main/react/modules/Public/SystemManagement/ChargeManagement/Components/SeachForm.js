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
                url: '/modules/system/action/getBusinessDepartment.htm?type=2',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.datas.map((item)=>{
                      return (<Option key={item.id} value={String(item.id)}>{item.name}</Option>);
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
                 <label htmlFor="officeId">营业厅：</label>
                 <Select size="large" style={{width:200}} name="officeId" value={formData.officeId} onChange={this.selectChange.bind(this,'officeId')}>
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
 
