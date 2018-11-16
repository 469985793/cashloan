var React = require('react');
import antd from 'antd';  
var Select = antd.Select;
var Option = Select.Option; 
var reqwest = require('reqwest');
var  Actions = require('../../actions/CustomerActions');
export default React.createClass({
    getInitialState() {
        return { 
          formData:{
            projectCode:"",
            carMessageId: "",
            collateralType:"",
            roleId:""
          },
          roleList:[]
        };
    },
    resetData (){
      var newValue = {
            projectCode:"",
            carMessageId: "",
            collateralType:"",
            roleId:""
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
                url: '/modules/common/action/ComboAction/queryOffices.htm?where.type=2',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.map((item)=>{
                      return (<Option key={item.value} value={String(item.value)}>{item.text}</Option>);
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
                 <label htmlFor="projectCode">项目编号：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="projectCode"  value={formData.projectCode} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="carMessageId">押品编号：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="carMessageId" value={formData.carMessageId} onChange={this.changeValue}/>
               </div>  
               <div className="ant-form-item">
                  <label htmlFor="collateralType">押品类型：</label>
                  <Select size="large" placeholder="请输入..." style={{width:160}} name="collateralType" value={formData.collateralType} onChange={this.selectChange.bind(this,'collateralType')}>
                    <Option value="1">车</Option> 
                  </Select>
               </div>
               <div className="ant-form-item">
                   <label htmlFor="roleId">营业厅：</label>
                   <Select size="large"  style={{width:150}} name="roleId" value={formData.roleId} onChange={this.selectChange.bind(this, 'roleId')}>
                     {this.state.roleList}
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
 
