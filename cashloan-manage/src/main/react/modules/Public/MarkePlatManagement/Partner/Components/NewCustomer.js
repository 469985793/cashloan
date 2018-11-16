import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');
var FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
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
          formData:{},
          status:{ 
            url:{}
          }, 
          certTypeChildren:[]
      };
  },   
 changeValue(e) { 
      var newValue = this.state.formData;
      var name = e.target.name;
      newValue[name] = e.target.value;
      this.setState({formData:newValue});
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
            //console.log(title)
            var selectRecord = this.props.selectRecord;
            var serviceVariables={
                  id:formData.id, 
                  status:1,
                  sort:formData.sort,
                  title:formData.title, 
                  siteId:'20',
                  url:formData.url,
                  picPath:formData.picPath
              }
            reqwest({
              url:title=='新增'?'/cms/manager/updateArticleById.htm':'/cms/manager/addArticle.htm',
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
    this.refs.validation.reset();
    this.setState(this.getInitialState());
  },
  componentDidUpdate(prevProps, prevStat) {  
    var validation = this.refs.validation;
    setTimeout(() => {
      if(prevProps.visible){
            validation&&validation.validate((valid) => {
               if (!valid) {
                 //console.log('error in form');
                 
               } else {
                 //console.log('submit');
               } 
                
             });
          } 
    }, 400);    
  },
  render() {  
    var me = this; 
   
    var state = this. state; 
    var status = state.status;
    var formData = state.formData;
    var props = this.props;
    var isDisabletwo = (props.title=="新增用户"?false:true);
    var isDisable = !props.canEdit||(props.title=="修改"?true:false); 
    var modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          提 交
        </button>
    ];
    if(this.props.title == '信息查看')
    {
       modalBtns = [
          <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button> 
        ]
    }
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="800"  
        footer={modalBtns} >  
      <form className="ant-form-horizontal" ref="myform">   
        <Validation ref="validation" onValidate={this.handleValidate}> 
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="sort" required>序号：</label> 
                  <div className="col-10">
                      <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="sort" value={formData.sort} onChange={this.changeValue}/> 
                  </div> 
            </div>
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="title" required>新闻标题：</label> 
                  <div className="col-10">
                      <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="title" value={formData.title} onChange={this.changeValue}/> 
                  </div> 
            </div>
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="picPath" required>图片链接：</label> 
                  <div className="col-19">
                      <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="picPath" value={formData.picPath} onChange={this.changeValue}/> 
                  </div> 
            </div>
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="url" required>跳转链接：</label> 
                  <div className="col-19">
                    <div className={this.renderValidateStyle('url')}>
                      <Validator rules={[{required: true}]}>  
                       <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="url" value={formData.url} onChange={this.changeValue}/> 
                      </Validator>    
                    </div> 
                  </div> 
            </div>   
        </Validation>
      </form>  
    </Modal> 
    )
  }
});