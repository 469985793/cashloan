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
            id:"",
            name: "",
            officeIds:"",
            ptype:"",
            publishStatus:""
          },
          officeIdsList:[]
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
                method: 'post', 
                type: 'json',
                success: (result) => {
                  var items  = result.datas.map((item)=>{
                      return (<Option key={item.id} value={item.id}>{item.name}</Option>);
                    });
                   me.setState({officeIdsList:items});
                } 
              });
    },

    render() { 
      var formData = this.state.formData;
        return ( 
             <form className="ant-form-inline">
               <div className="ant-form-item">
                 <label htmlFor="id">产品编号：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="id"  value={formData.id} onChange={this.changeValue}/> 
               </div>
               <div className="ant-form-item">
                 <label htmlFor="name">产品名称：</label>
                 <input className="ant-input" type="text" placeholder="请输入..." name="name" value={formData.name} onChange={this.changeValue}/>
               </div>  
               <div className="ant-form-item">
                  <label htmlFor="officeIds">营业厅：</label>
                  <Select size="large" placeholder="请输入..." style={{width:160}} name="officeIds" value={formData.officeIds} onChange={this.selectChange.bind(this,'officeIds')}>
                    {this.state.officeIdsList}
                  </Select>
               </div>
               <div className="ant-form-item">
                   <label htmlFor="ptype">贷款类型：</label>
                   <Select size="large"  style={{width:150}} name="ptype" value={formData.ptype} onChange={this.selectChange.bind(this, 'ptype')}>
                     <Option value="1">信贷</Option>
                     <Option value="2">已冻结</Option>
                   </Select>
               </div>
               <div className="ant-form-item">
                   <label htmlFor="publishStatus">发布状态：</label>
                   <Select size="large"  style={{width:150}} name="publishStatus" value={formData.publishStatus} onChange={this.selectChange.bind(this, 'publishStatus')}>
                     <Option value="0">已上架</Option>
                     <Option value="1">待上架</Option>
                     <Option value="2">已下架</Option>
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
 
