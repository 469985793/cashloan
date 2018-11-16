import React from 'react';
import antd from 'antd';  
var Dragger = antd.Upload.Dragger ; 
var Modal = antd.Modal;    
var reqwest = require('reqwest');    
export default React.createClass({
 
  getInitialState() { 
     return {
         fileList:[],
         formData:{}
       };
   },   
  queryFormData(selectRecord){
    var me = this;   
    if(!selectRecord)
      return 
    var type= this.props.type;
    var projectId = selectRecord.projectId; 
    reqwest({
                url: '/modules/common/Attachment/query3.htm'
                , method: 'get'
                ,data: {
                   "where.attach.project_id":projectId,
                   "where.attach.rd_btype_in":type
                  }
                , type: 'json' 
                ,success: (result) => { 
                  var data = result.rows; 
                  var length= data.length; 
                  var fileList=[];
                  for(let i=0;i<length;i++){
                    let row =data[i];
                    let file ={};
                    file.url = row.uri;
                    file.id = row.id;
                    fileList.push(file); 
                  } 
                  me.setState({
                    fileList:fileList
                  });
                } 
            }); 
      
  },
  componentWillReceiveProps(nextProps) {  
    if(this.props.selectRecord&&this.props.selectRecord!=nextProps.selectRecord){ 
     this.queryFormData(nextProps.selectRecord);
    }
  },
  componentDidMount() {  
    if(this.props.selectRecord)
      this.queryFormData(this.props.selectRecord);
  }, 
  handleChange(info) {
     this.queryFormData(this.props.selectRecord);
  },
  Delete(id){ 
    var me = this;
      reqwest({
                        url: '/modules/common/deletes3.htm',
                        method: 'post', 
                        data:{
                          ids:id
                        },
                        type: 'json',
                        success: (result) => {                        
                          if(result.success)
                          {
                            var fileList = me.state.fileList;
                            fileList = fileList.filter((file)=>{ 
                              if(file.id!=id)
                                return true;
                            });  
                            me.setState({
                              fileList
                            });
                          }
                          else 
                          {
                            Modal.error({
                              title: result.msg
                            }); 
                          }
                        }
                      });
  }, 
  render() {    
    var formData= this.state.formData;
    var props = this.props; 
    if(!props.selectRecord)
      return null
    var selectRecord = props.selectRecord;
    var type= this.props.type;
    var images="";
     images = this.state.fileList.map((file,index )=>{
      return <div className="img" key ={index} >
          <img src={file.url} /> 
          {props.canEdit?<button key="delete" type="button" className="ant-btn" onClick={this.Delete.bind(this,file.id)}><i className="anticon anticon-delete"></i>删除</button>:null} 
      </div>
     }); 
    var postDate={
                    rd_operatorid :1,
                    processInstanceId:selectRecord.processInstanceId,
                    project_id:selectRecord.projectId,
                    rd_btype:type
                  };
    var uploadProps = {
       name:"Filedata",
       data:{
         data:JSON.stringify(postDate)
       },
       action: '/modules/credit/action/SysFileTemplateAction/saveOrUpdate.htm',
       onChange: this.handleChange,
       multiple: true
     }; 
     var buttons={

     }
    return( <div className="uploadFile">
                {images}
                <div className="img">
                  <div style={{width:'190px',height:'230px',display:'inline-block',verticalAlign: "top"}}>
                    {props.canEdit?<Dragger {...uploadProps}> 
                         <i className="anticon anticon-plus"></i>
                    </Dragger>:null}
                </div> 
               </div>   
            </div> 

    )
  }
});
