import React from 'react';
import antd from 'antd';    
var Modal = antd.Modal;  
var Reflux = require('reflux'); 
var Actions = require('../actions/Actions');
var FormStore = require('../stores/FormStore');
var reqwest = require('reqwest');
function filefujianChange() { 
  var oFiles = document.querySelector("#fileId"); 
  document.querySelector("#imgPath").value = oFiles.value;
}
export default React.createClass({
  mixins: [ 
      Reflux.connect(FormStore, 'formData') 
    ], 
 getInitialState() {
    return { 
          formData:{},
          status:{ 
            filePath:{}
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
  selectChange(name,value) { 
    var newValue = this.state.formData;
      newValue[name] = value;
    this.setState({formData:newValue});
  }, 
  handleOk() {
    
            var me = this;  
            var oFiles = document.querySelector("#fileId"); 
            var formData = new FormData();   
            formData.append("newFileName", this.state.formData["newFileName"]);
            formData.append("url", this.state.formData["url"]);
            formData.append("picture", oFiles.files[0]); 

            reqwest({
              url : '/modules/cms/manager/action/ImageControlAction/uploadImageControl.htm',  
              method: 'post', 
              contentType:false,  
              processData:false,
              data:formData,
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
       
  },
  handleCancel() {
    this.props.hideAddModal(); 
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
    var inputFile = (<div className="ant-form-item">
                      <label className="col-4"  htmlFor="filePath">图片：</label> 
                      <div className="col-10"><input id="imgPath" className="ant-input" type="text" /></div>
                      <div className="col-5">  
                            <a href="javascript:;" className="a-upload ant-btn-primary" >
                              <input id="fileId" type="file" name="filePath" onChange={filefujianChange}/>浏览
                            </a>
                      </div> 
                </div>); 
    if(this.props.title == '查看')
    {
       modalBtns = [
          <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button> 
        ]
      inputFile=<div>
                  <div className="ant-form-item">
                        <label className="col-4"  htmlFor="filePath">图片链接：</label> 
                        <div className="col-19"> 
                               <input className="ant-input" type="text" name="filePath" value={formData.filePath} disabled={!this.props.canEdit} /> 
                        </div> 
                  </div>
                  <img src={formData.filePath} />
                </div>
    }
    return(
    <Modal title={this.props.title} visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel} width="800"  
        footer={modalBtns} >  
      <form className="ant-form-horizontal" ref="myform" method="post" >   
        <div className="ant-form-item">
                  <label className="col-4"  htmlFor="newFileName">名称：</label> 
                  <div className="col-10">
                      <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="newFileName" value={formData.newFileName} onChange={this.changeValue}/> 
                  </div> 
            </div>
            <div className="ant-form-item">
                  <label className="col-4"  htmlFor="url" >跳转链接：</label> 
                  <div className="col-19">
                      <input className="ant-input" type="text" disabled={!this.props.canEdit}  name="url" value={formData.url} onChange={this.changeValue}/> 
                  </div> 
            </div> 
            {inputFile}  
      </form>  
    </Modal> 
    )
  }
});