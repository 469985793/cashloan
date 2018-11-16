import React from 'react';
import antd from 'antd';
var Validation = antd.Validation;
var Validator = Validation.Validator;   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var Actions = require('../../actions/CustomerActions');
var FormStore = require('../../stores/FormStore');
var reqwest = require('reqwest');
var Ueditor = require('../../../../../common/Ueditor');
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
            siteId:{},
            isTop:{},
            isRecommend:{}
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
    var me = this; 
    var validation = this.refs.validation,
        status = 'create'; 
        validation.validate((valid) => {
          if (!valid) {
            return;
          } else {
              var newFormData = this.state.formData;
                newFormData.content = UE.getEditor("content").getContent();
                newFormData.status = 1;
              var url ='/cms/manager/addArticle.htm';
              if(this.props.title=="编辑")
              { 
                url = '/cms/manager/updateArticleById.htm';
              } 
              
               reqwest({
                        url: url,
                        method: 'post', 
                        data:{
                          serviceVariables:JSON.stringify(newFormData) 
                        },
                        type: 'json',
                        success: (result) => {
                          if(result.code==200)
                          {
                            Modal.success({
                              title: result.msg,
                              onOk: function () {
                                    me.handleCancel();
                                  }
                            }); 
                          }
                          else if(result.code==500)
                          {
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
    this.onDestroy();
    var dom = document.getElementById('content');
    if (dom) {
        dom.parentNode.removeChild(dom);
    } 
    this.props.hideAddModal(); 
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
  onDestroy(){
    UE.getEditor("content").destroy();
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
    if(this.props.title == '查看')
    {
       modalBtns = [
          <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button> 
        ]
    } 
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="1000"  
        footer={modalBtns} >  
      <form className="ant-form-horizontal" ref="myform">   
        <Validation ref="validation" onValidate={this.handleValidate}> 
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="siteId" required>所属：</label>
                  <div className="col-10">
                    <div className={this.renderValidateStyle('siteId')}>
                      <Validator rules={[{required: true,type:'number'}]}>
                      <Select size="large" style={{width:"100%"}} disabled={!this.props.canEdit} name="siteId" value={formData.siteId} onChange={this.selectChange.bind(this,'siteId')}>
                         <Option value={17}>平台介绍</Option>
                         <Option value={18}>核心团队</Option>
                         <Option value={19}>联系我们</Option>
                      </Select>
                      </Validator>    
                      </div>
                  </div>
            </div>    
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="sort" >序号：</label> 
                  <div className="col-10">
                      <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="sort" value={formData.sort} onChange={this.changeValue}/> 
                  </div> 
            </div>
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="picPath" >图片链接：</label> 
                  <div className="col-19">
                      <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="picPath" value={formData.picPath} onChange={this.changeValue}/> 
                  </div> 
            </div>
            <div className="ant-form-item"> 
                  <label className="col-4"  htmlFor="title">标题：</label>
                  <div className="col-19">
                      <textarea className="ant-input" disabled={!this.props.canEdit} style={{height:80,resize:"none"}} name="title" value={formData.title} onChange={this.changeValue}/>
                  </div>  
            </div> 
            <div className="ant-form-item"> 
                  <label className="col-4"  htmlFor="content">内容：</label>
                  <div className="col-19">
                       <Ueditor value={formData.content} id="content" height="200" disabled={!this.props.canEdit}/> 
                  </div>  
            </div>   
        </Validation>
      </form>  
    </Modal> 
    )
  }
});