import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;
var Datepicker = antd.Datepicker; 
var reqwest = require('reqwest'); 
var Reflux = require('reflux'); 
var FormStore = require('../../stores/FormStore');
var Actions = require('../../actions/CustomerActions'); 
var siteIdChildren=[];
     reqwest({
                url: '/cms/manager/getCmsSiteByParentId.htm?node=3',
                method: 'get', 
                type: 'json',
                success: (result) => {
                  var items  = result.map((item)=>{
                      return (<Option key={item.id} value={item.id}>{item.name}</Option>);
                    });
                   siteIdChildren=items
                }
              }); 
function cx(classNames) {
  if (typeof classNames === 'object') {
    return Object.keys(classNames).filter(function(className) {
      return classNames[className];
    }).join(' ');
  } else {
    return Array.prototype.join.call(arguments, ' ');
  }
}
export default React.createClass({
  mixins: [ 
      Reflux.connect(FormStore, 'formData'),
      Validation.FieldMixin
    ], 
  getInitialState() {
    return {
        status: {
          siteId:{}
        }, 
        formData:{} 
      };
  },
  getDefaultProps(){
    return 
    {
      title:this.props.title;
    }
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
  handleOk() {
    var validation = this.refs.validation;
    validation.validate((valid) => {
    if (!valid) {
            return;
          } 
    else {
            var me = this; 
            var state = this.state; 
            var formData = state.formData;
            var title=this.props.title;
            var selectRecord = this.props.selectRecord;
            var serviceVariables={
                  id:formData.id, 
                  status:1,
                  siteId:formData.siteId,
                  title:formData.title, 
                  content:formData.content
              }
            reqwest({
              url: title=='新增'?'/cms/manager/addArticle.htm':'/cms/manager/updateArticleById.htm',
              method: 'post',
              type: 'json',
              data: {
                serviceVariables:JSON.stringify(serviceVariables)
              },  
                success: (result) => {
                  if(result.code==200)
                  {
                    Modal.success({
                      title: result.msg
                    });
                   Actions.initStore();
                   this.props.hideAddModal();
                  }else{
                            Modal.error({
                              title: result.msg
                            });
                            me.setState({
                              loading:false
                            });
                          }
                }
              }); 
             } 
        }); 
  },
  handleCancel() {
    this.props.hideAddModal();
  },
  handleReset() {
    this.refs.validation.reset();
    this.setState(this.getInitialState()); 
  },
  renderValidateStyle(item, hasFeedback=true) {
    var formData = this.state.formData;
    var status = this.state.status;
   
    var classes = cx({
      'has-feedback': hasFeedback,
      'has-error': status[item].errors,
      'is-validating': status[item].isValidating,
      'has-success': formData[item] && !status[item].errors && !status[item].isValidating
    });

    return classes;
  },
   
  componentDidUpdate(prevProps, prevStat) {  
    var validation = this.refs.validation;
    setTimeout(() => {
      if(prevProps.visible){
            validation&&validation.validate((valid) => {
               if (!valid) {
                 //console.log('error in form');
                 
               } 
             });
          } 
    }, 400);    
  },
  componentDidMount(){
    var validation = this.refs.validation;
       validation&&validation.validate((valid) => {
         if (!valid) {
           //console.log('error in form'); 
         }  
         
       });
  },
  render() { 
    var me = this; 
    var state = this.state; 
    var status = state.status;
    var formData = state.formData;
    var props = this.props;
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
          提 交
        </button>
    ];
    if(props.title=='查看')
       modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>关 闭</button> 
    ];
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}  
        footer={modalBtns} >   
          <form className="ant-form-horizontal" ref="myform">
          <Validation ref="validation" onValidate={this.handleValidate}> 


                  <div className="form-line" style={{display:"block"}}> 
                      <label className="form-label"  htmlFor="siteId" required >问题类型：</label>
                      <div className="form-input">
                          <div className={this.renderValidateStyle('siteId')}>
                          <Validator rules={[{required: true,type:'number'}]}>
                            <Select size="large" style={{width:"100%"}}  disabled={!props.canEdit}  name="siteId" value={formData.siteId}  onChange={this.selectChange.bind(this, 'siteId')}>
                               {siteIdChildren}
                            </Select>
                          </Validator>
                          </div>   
                      </div>
                  </div> 

                  <div  style={{marginRight:"20px"}}> 
                    <div className="ant-form-item"> 
                      <label className="form-label" htmlFor="title">新增问题：</label> 
                      <div className="col-21"> 
                          <textarea className="ant-input" disabled={!props.canEdit} style={{height:150,resize:"none"}} name="title" value={formData.title} onChange={this.changeValue}/>
                      </div>
                    </div> 
                  </div>
             
                  <div  style={{marginRight:"20px"}}> 
                    <div className="ant-form-item"> 
                      <label className="form-label" htmlFor="content">问题答案：</label> 
                      <div className="col-21"> 
                          <textarea className="ant-input" disabled={!props.canEdit} style={{height:150,resize:"none"}} name="content" value={formData.content} onChange={this.changeValue}/> 
                      </div>
                    </div> 
                  </div>
          </Validation>
        </form>  
    </Modal> 
    )
  }
});