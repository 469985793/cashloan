import React from 'react';
import {
    Button,
    Form,
    Input,
    InputNumber,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col,
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            loading: false,
            formData: {},
            record:"",
            tableNameList:"",
            tableCommentList:"",
            data:"",
            dataArray:[],
            tableName:"",
            canEdit:true,
            dataArr:[],
            dataRecord:"",
            dataName:[],
            values:"",
            tableComment:""
        };
    },
  handleCancel() {
        //console.log(11111111111)
        this.props.form.resetFields();
        this.props.hideModal();
          this.setState({
            tableName:"",
            canEdit:true,
            dataArr:[],
            dataRecord:"",
            dataName:[]   
        })  
    },
    componentWillReceiveProps(nextProps, nextState) {

      if(nextProps.title=="编辑"){
          //console.log("999999",this.state.data);
       var dataArray=[];
        if(this.state.data){
           this.state.data.forEach(item=>{
              if(item.tableName==nextProps.dataRecord.tbNid){
                item.children.forEach((item1,i)=>{
                     //console.log("88888888888",item1.columnName);
                     dataArray.push(<Option key={i} value={item1.columnComment} disabled={item1.checked}>{item1.columnComment}</Option>) ;
                  })    
              }
          })
        }
        this.setState({
            dataRecord:nextProps.dataRecord,
            dataName:nextProps.dataName,
            dataArray:dataArray,
        })
      }
       
  },

  handleOk() {
        var me = this;
        var columndata=this.state.dataArr;
        var validation = this.props.form.validateFields;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            values.tbName=this.state.tableComment;
          //console.log("99999",values);
          if(this.props.title=="新增"){       
              var res=[];
            columndata.forEach(item=>{
                let obj={}
                obj.name=item.columnComment;
                obj.nid=item.columnName;
                obj.type=item.type
                res.push(obj)
            })
            values.detail=res;
            values.detail=JSON.stringify(values.detail);
            var url= '/modules/manage/cr/info/save.htm';
            var params=values;
          }   
         if(this.props.title=="编辑"){
             var paramsData=this.props.form.getFieldsValue();
             var paramsArr=[];
             var req=[];
             //console.log("---------",paramsData);
             paramsData.detail.forEach(item=>{
                 this.state.data.forEach(item1=>{
                     if(item1.tableName==this.state.dataRecord.tbNid){
                     item1.children.forEach(item2=>{
                         if(item==item2.columnComment){
                             paramsArr.push(item2);
                         }
                     })
                     }
                    
                 })
             })
            paramsArr.forEach(item=>{
                let obj2={}
                obj2.name=item.columnComment;
                obj2.nid=item.columnName;
                obj2.type=item.type
                req.push(obj2)
            })
             paramsData.detail=req;
             paramsData.detail=JSON.stringify(paramsData.detail);
            var url= '/modules/manage/cr/info/update.htm';
            var params=paramsData;
         }  
            Utils.ajaxData({
              url: url,
              data:params,
              callback: (result) => {
                 if(result.code=="200"){
                    Modal.success({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }else{
                     Modal.error({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }
            }
          });     
        })
    },
    componentDidMount(){
          Utils.getData({
                  url: '/modules/manage/cr/info/findAllDataTable.htm',
                callback: (result) => {
                   var tableCommentList=result.data.map((item)=>{
                       return (<Option key={item.tableName} value={String(item.tableName)} disabled={item.checked}>{item.tableComment}</Option>)
                   })
                    this.setState({
                        tableCommentList:tableCommentList,
                        data:result.data
                    });
                }
            });
      },
      change(value){
       //  //console.log("00000000000",value);
          var dataArray=[];
          this.props.form.resetFields();
          this.state.data.forEach((item)=>{
              if(item.tableName==value){
                   this.setState({
                    tableName:item.tableName,
                    tableComment:item.tableComment,
                })
                item.children.forEach((item,i)=>{
                     dataArray.push(<Option key={i} value={item.columnName} disabled={item.checked}>{item.columnComment}</Option>) ;
                  })    
              }
          })

        this.setState({
            dataArray:dataArray,
            canEdit:false,
            values:value
        })
      },
    select(value){    
        
         this.state.data.forEach(item=>{
             if(item.tableName==this.state.values){
                 item.children.forEach(item=>{
                 if(item.columnName==value){
                    this.state.dataArr.push(item);
                 }
             })
             }  
         }) 
      },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading}  onClick={this.handleOk}>
                提 交
            </Button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 4
            },
            wrapperCol: {
                span: 20
            },
        };
         const formItemLayoutone = {
            labelCol: {
                span:8
            },
            wrapperCol: {
                span: 16
            },
        };
        let data=this.state.data;
        const tProps={
           data,
        }
        return (
             <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900" footer={modalBtns} maskClosable={false} >
                <div style={{ position: "relative" }}>
                    <Form horizontal form={this.props.form} style={{ marginTop: "20px" }}>
                       <Input  {...getFieldProps('id', { initialValue: this.state.dataRecord.id }) } type="hidden" />
                            <Row>
                                <Col span="10">
                                    <FormItem  {...formItemLayout} label="表:">
                                    {this.props.title=="编辑"?(
                                        <Input type="text" disabled={true} {...getFieldProps('tbName', {initialValue:this.state.dataRecord.tbName,})} />
                                    ):(
                                          <Select style={{ width: 100 }}   placeholder="请选择" {...getFieldProps('tbName', {initialValue:"",onChange:this.change}) } style={{width:"200px"}}>
                                            {this.state.tableCommentList}
                                         </Select>
                                    )}
                                      
                                    </FormItem>
                                </Col>
                                        <Input disabled={true}  type="hidden" {...getFieldProps('tbNid', { initialValue: this.state.tableName==""?this.state.dataRecord.tbNid:this.state.tableName}) }/>
                            
                               <Col span="12">
                                    <FormItem  {...formItemLayoutone} label="字段:">
                                     <Select disabled={props.title=="编辑"?props.canEdit:this.state.canEdit}  multiple onSelect={this.select} {...getFieldProps('detail', {  initialValue:this.state.dataName ,rules: [{required:true,message:'必填',type:'array'}]})} > 
                                           {state.dataArray}
                                     </Select> 
                                    </FormItem>
                              </Col>
                            </Row>
                    </Form>
                </div>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
