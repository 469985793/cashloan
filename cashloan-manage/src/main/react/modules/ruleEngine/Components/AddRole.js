import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Select,
  Row,
  Col,
  Radio,
  Cascader,
  InputNumber
} from 'antd';
const RadioGroup = Radio.Group;
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const custType = [];
var AddRole = React.createClass({
  getInitialState() {
    return {
      keys: [],
      keys2:[],
      selectVlaue: 1,
      selectVlaue2:1,
      tableInfo: [],
      childInfo: [],
      realItem: [],
      num: 1,
      type:"20",
      itemOne:true,
      itemTwo:true
    };
  },
  handleCancel() {
    this.props.hideModal();
    this.setState({
      keys: [],
      selectVlaue: 1,
      selectVlaue2:1,
      type:"20",
      keys2:[],
      itemOne:true,
      itemTwo:true
    });
    this.formData = {};
    this.state.num = 1;
   // //console.log(this.state.num)
    ////console.log(this.formData)
    this.props.form.resetFields();
  },
  handleOk(e) {
    e.preventDefault();
    var me = this;
    var props = me.props;
    var record = props.record;
    var title = props.title;
    var tableInfo=this.state.tableInfo;
   // //console.log("tableInfo44444444444",tableInfo);
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      //console.log("values5555555555555",values);
      var data = values;
      // for(var i in data){
      //   if(i.indexOf('keys') == -1 && i.indexOf('id') == -1){
      //     if(data[i] !== '' && data[i] !== undefined && data[i] !== null){
      //       continue;
      //     }else{
      //       Modal.error({
      //         title: '信息不能为空'
      //       })
      //       return;
      //     }
      //   }
      // }
      for(var i in data){
        if(i.indexOf('ccolumn') > -1){
          if(!data[i]){
            Modal.error({
              title: '信息不能为空'
            })
            return;
          }
        }
      }
      var params = {};
      var req = [];
      var res=[];
      var url = '';
      var paramsData=''
      params.name = data.name;
      params.type=data.type;
     
      //req
      let obj = {};

      if(this.state.itemOne){
          obj.ctable = data['0ccolumn'][0];
          obj.ccolumn = data['0ccolumn'][1];
          obj.formula = data['0formula'];
          obj.cvalue = data['0cvalue'];
          obj.integral=data['0integral'];
          obj.result=data['0result'];
          obj.id = data['0id'];
          req.push(obj)
      }
 
     
      if (data.keys.length != 0) {
        data.keys.forEach(item => {
          let obj = {};
          obj.ctable = data[item + 'ccolumn'][0];
          obj.ccolumn = data[item + 'ccolumn'][1];
          obj.formula = data[item + 'formula'];
          obj.cvalue = data[item + 'cvalue'];
          obj.integral=data[item+'integral'];
          obj.result=data[item+'result'];
          obj.id = data[item+'id'];
          req.push(obj)
        });
      };
      for(var i = 0; i < req.length; i++){
        for (var item in req[i]) {
          if(item.indexOf('id')==-1 && item.indexOf('integral')==-1 && item.indexOf('result')==-1){
            if (req[i][item] !== '' && req[i][item] !== undefined && req[i][item] !== null) {
              continue;
            } else {
              Modal.error({
                title: '信息不能为空'
              })
              return;
            }
          }
        }
      }
      params.configList = req;

      //res
      if(this.state.type=="20"){
        data.keys2=[];
      }
      var obj2={};
      if(this.state.type=="10"){
          obj2.info_formula=data['0info_formula'];
          obj2.info_integral=data['0info_integral'];
          obj2.info_result=data['0info_result'];
          obj2.id = data['0id2'];
          res.push(obj2);
      }
     
      if(data.keys2.length!=0){
        data.keys2.forEach(item=>{
          let obj2={};
          obj2.info_formula=data[item+'info_formula'];
          obj2.info_integral=data[item+'info_integral'];
          obj2.info_result=data[item+'info_result'];
          obj2.id = data[item+'id2'];
          res.push(obj2)
        })
      }
      if(this.state.type=="10"){
        for(var i = 0; i < res.length; i++){
          for (var item in res[i]) {
            if(item.indexOf('id')==-1){
              if (res[i][item] !== '' && res[i][item] !== undefined && res[i][item] !== null) {
                continue;
              } else { 
                Modal.error({
                  title: '信息不能为空'
                })
                return;
              }
            }
          }
        }
            
      }
      params.infoList=res; 
      if (this.props.title == '编辑') {
        url = '/modules/manage/rule/saveRuleConfig.htm'; //+ '?name=' + params.name + '&id=' + this.props.ruleData.rule.id + '&configList=' + JSON.stringify(params.configList);
         paramsData={"name": params.name,"id":this.props.ruleData.rule.id,"configList": JSON.stringify(params.configList),"infoList":JSON.stringify(params.infoList),"type":params.type}
       // //console.log(url)
      } else {
       // //console.log(params.name)
        url = '/modules/manage/rule/saveRuleConfig.htm';  // + '?name=' + params.name + '&configList=' + JSON.stringify(params.configList);
         paramsData={"name":params.name,"configList":JSON.stringify(params.configList),"infoList":JSON.stringify(params.infoList),"type":params.type}
      };
      Utils.ajaxData({
        url: url,
        data:paramsData,
        method:"post",
        callback: (result) => {
          if (result.code == 200) {
            this.handleCancel();
          };
          let resType = result.code == 200 ? 'success' : 'warning';
          // Utils.openNotification({
          //   type: resType,
          //   message: result.msg,
          //   duration: 2
          // });
          Modal[resType]({
              title: result.msg,
            });
        }
      });
    });
  },
  remove(k) {
    const {
      form
    } = this.props;
    let keys = form.getFieldValue('keys');
    keys = keys.filter((key) => {
      return key !== k;
    });
   // //console.log(keys)
    this.state.keys = keys;
    form.setFieldsValue({
      keys,
    });
  },
  remove2(k){
    const {
      form
    } = this.props;
    let keys2 = form.getFieldValue('keys2');
    keys2 = keys2.filter((key) => {
      return key !== k;
    });
  //  //console.log(keys2)
    this.state.keys2 = keys2;
    form.setFieldsValue({
      keys2,
    });
  },
  removeFirst(){
      const {
      form
    } = this.props;
    let keys = form.getFieldValue('keys');
    if(keys.length>0){

      this.setState({
          itemOne:false
      })
    }
    
  },
  removeTwo(){
     const {
      form
    } = this.props;
    let keys2 = form.getFieldValue('keys2');
    if(keys2.length>0){

      this.setState({
          itemTwo:false
      })
    }
  },
  add() {
    const {
      form
    } = this.props;
    let keys = form.getFieldValue('keys');
    keys = keys.concat(this.state.selectVlaue++);
    this.state.keys = keys;
   // //console.log(keys)
    form.setFieldsValue({
      keys
    });
  },
  add2(){
    const {
      form
    } = this.props;
    let keys2 = form.getFieldValue('keys2');
    keys2 = keys2.concat(this.state.selectVlaue2++);
    this.state.keys2 = keys2;
   // //console.log(keys2)
    form.setFieldsValue({
      keys2
    });
  },
  handleTableChange(value) {
   // //console.log(value)
    this.setState({
      realItem: this.state.childInfo[value]
    });
  },
  formData: {},
  formData2:{},
  transformeData(data) {//console.log(data)
    var formData = {};
    var keys = [];
    data.forEach((item, index) => {
      let k = index;
      keys.push(k);
      formData[`${k}ccolumn`] = [item.ctable, item.ccolumn];
      formData[`${k}formula`] = item.formula;
      formData[`${k}cvalue`] = item.cvalue;
      formData[`${k}integral`] = item.integral;
      formData[`${k}result`] = item.result;
      formData[`${k}id`] = item.id;
    })
  
   //let keys1 = keys.splice(0, 1);
 
    formData["keys"] = keys;
   // //console.log("formData999999",formData);
    return formData;
  },
  transformeData2(data2){
       var formData2 = {};
       var keys2 = [];
    data2.forEach((item, index) => {
      let k = index;
      keys2.push(k);
      formData2[`${k}formula`] = item.formula;
      formData2[`${k}integral`] = item.integral;
      formData2[`${k}result`] = item.result;
      formData2[`${k}id2`] = item.id;
    })
    
   // let keys1 = keys2.splice(0, 1);
 
    formData2["keys2"] = keys2;
  //  //console.log("formData999999",formData2);
    return formData2;
  },
  componentWillReceiveProps(nextProps) {
  //  //console.log("mmmmmmmmmm",nextProps.ruleData);
    if (nextProps.visible) {

      if (nextProps.title == '编辑' && this.state.num == 1) {
        var data = nextProps.ruleData.configList;
        var data2= nextProps.ruleData.infoList
        var data3= nextProps.ruleData.rule
        this.formData = this.transformeData(data);
        this.formData2= this.transformeData2(data2);
        //console.log(this.formData)
        this.props.form.setFieldsValue({
          keys: this.formData.keys,
          keys2:this.formData2.keys2,
        });
        this.setState({
          selectVlaue: this.formData.keys.length + 1,
          selectVlaue2:this.formData2.keys2.length + 1,
          keys: this.formData.keys,
          keys2:this.formData2.keys2,
          type:data3.type,
          itemOne:false,
          itemTwo:false
        });
        this.state.num = 2;
      //  //console.log(333333);
      } else if (nextProps.title == '新增') {
        this.formData = {};
        this.formData2 = {};
    //    //console.log(55555555);
      }
      
    };
  },
  componentDidMount() {
    Utils.ajaxData({
      url: '/modules/manage/rule/findTable.htm',
      callback: (result) => {
        if (result.code == 200) {
          var tableInfo = [];
          var data = result.data;
        // console.log("data666666666",data);
          data.forEach((item) => {
            let col1 = {};
            var childInfo = [];
            col1.label = item.tableComment;
            col1.value = item.tableName;
            col1.children = [];
            item.children.forEach((item) => {
              let col2 = {};
              col2.label = item.columnComment;
              col2.value = item.columnName;
              col2.type=item.type;
              col1.children.push(col2);
            });
            tableInfo.push(col1);
          });
        //console.log("tableInfo77777777777",tableInfo);
          this.setState({
            tableInfo: tableInfo
          });
        }
      }
    });
  },
  change(value){ 
    this.setState({
      type:value,
      itemTwo: true
    }
  );
  },
  changeInt(e){
    return parseInt(e.target.value)
  },
  changeNUm(e){
    return parseInt(e.target.value)
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    getFieldProps('keys', {
      initialValue: [],
    });
    getFieldProps('keys2', {
      initialValue: [],
    });
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          提 交
      </button>
    ];
 //   //console.log("state.keys2",state.keys2);
    const formItem2=state.keys2.map((k)=>{
       return (<div key={k}>
            <Input type='hidden' {...getFieldProps(`${k}id2`,{initialValue:this.formData2?this.formData2[`${k}id2`]:0})}/>
             <Row>
              <Col span="8" className='col_h'>
                <FormItem  {...{
                  labelCol: {
                  span:6
                  },
                  wrapperCol: {
                  span: 14
                  },
                  }} label="结果评分:">
                   <Select style={{ width: '100%' }}  placeholder="请选择"
                     {...getFieldProps(`${k}info_formula`,{initialValue:this.formData2?this.formData2[`${k}formula`]:''})}>
                     <Option  value='>'>></Option>
                     <Option  value='<'>{'<'}</Option>
                     <Option  value='='>=</Option>
                     <Option  value='>='>>=</Option>
                     <Option  value='<='>{'<='}</Option>
                     <Option  value='!='>!=</Option>
                     <Option  value='<and<'>{'<and<'}</Option>
                     <Option  value='<=and<'>{'<=and<'}</Option>
                     <Option  value='<=and<='>{'<=and<='}</Option>
                     <Option  value='<and<='>{'<and<='}</Option>
                     {/*
                     <Option  value='include'>包含</Option>
                     <Option  value='exclude'>不包含</Option> 
                      */}
                   </Select>
                </FormItem> 
              </Col>
               <Col span="6" className="col_h">
                <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 15}}} label="分值:">
                 <Input  type="number"    {...getFieldProps(`${k}info_integral`,{onChange:this.changeInt,getValueFromEvent: this.changeNUm,initialValue:this.formData2?this.formData2[`${k}integral`]:'',rules: [{type:'number' ,max:2147483647,message:'输入过多'}]})} />
                </FormItem>
               </Col>
             <Col span="6" className="col_h">
                    <FormItem  {...{ labelCol: {},wrapperCol: {span:20}} }
                      label="">
                      <Select style={{ width: '100%' }}  placeholder="请选择"  {...getFieldProps(`${k}info_result`,{initialValue:this.formData2?this.formData2[`${k}result`]:''})}>
                           {/*<Option  value="">结果选择</Option>*/} 
                            <Option  value={"10"}>不通过</Option>
                            <Option  value={"20"}>需人工复审</Option>
                            <Option  value={"30"}>通过</Option>
                      </Select>
                    </FormItem>
              </Col>
              <Col span="2">
                <Button className="ant-btn" icon="plus" onClick={this.add2}></Button>
               <span style={{padding:"0 5px"}}></span>
              {this.state.keys2.length==1&&this.state.itemTwo==false?"":<Button className="ant-btn" shape="circle" icon="minus" onClick={this.remove2.bind(this,k)}></Button>}  
              </Col>
            </Row>
        </div>);
    })
    const formItems = state.keys.map((k) => {
      return (<div key={k}>
         <Input type='hidden' {...getFieldProps(`${k}id`,{initialValue:this.formData?this.formData[`${k}id`]:0})}/>
          <Row>  
           <Col span="8" className='col_h'>
            <FormItem  {...{
              labelCol: {
              span: 5
              },
              wrapperCol: {
              span: 18
              },
              }}
              label="表字段:">
               <Cascader options={this.state.tableInfo} {...getFieldProps(`${k}ccolumn`,{initialValue:this.formData?this.formData[`${k}ccolumn`]:''})} placeholder="请选择字段" />
            </FormItem> 
          </Col>
          <Col span="4" className='col_h'>
            <FormItem  {...{
              labelCol: {
              span: 10
              },
              wrapperCol: {
              span: 14
              },
              }} label="表达式:">
               <Select style={{ width: '100%' }}  placeholder="请选择"
                 {...getFieldProps(`${k}formula`,{initialValue:this.formData?this.formData[`${k}formula`]:''})}>
                 <Option  value='>'>></Option>
                 <Option  value='<'>{'<'}</Option>
                 <Option  value='='>=</Option>
                 <Option  value='>='>>=</Option>
                 <Option  value='<='>{'<='}</Option>
                 <Option  value='!='>!=</Option>
                 <Option  value='<and<'>{'<and<'}</Option>
                 <Option  value='<=and<'>{'<=and<'}</Option>
                 <Option  value='<=and<='>{'<=and<='}</Option>
                 <Option  value='<and<='>{'<and<='}</Option>
                 <Option  value='include'>包含</Option>
                 <Option  value='exclude'>不包含</Option>
               </Select>
            </FormItem> 
          </Col>
          <Col span="3" className='col_h'>
            <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 15}}}
              label="值:">
              <Input  {...getFieldProps(`${k}cvalue`,{initialValue:this.formData?this.formData[`${k}cvalue`]:'',rules: [{max:20,message:'输入过多'}]})} type="text" autoComplete="off" />
            </FormItem>
          </Col>
               {this.state.type=='10'?
                <Col span="3" className="col_h">
                <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 15}}} label="分数:">
                 <Input  type="number"  {...getFieldProps(`${k}integral`,{onChange:this.changeInt,getValueFromEvent: this.changeNUm,initialValue:this.formData?this.formData[`${k}integral`]:'',rules: [{type:'number',max:2147483647,message:'输入过多'}]})}/>
                </FormItem>
             </Col>
             :<Col span="3" className="col_h">
                    <FormItem  {...{ labelCol: {},wrapperCol: {span:20}} }
                      label="">
                      <Select style={{ width: '100%' }} placeholder="请选择"  {...getFieldProps(`${k}result`,{initialValue:this.formData?this.formData[`${k}result`]:''})} >
                            {/*<Option  value="">结果选择</Option>*/}
                            <Option  value={"10"}>不通过</Option>
                            <Option  value={"20"}>需人工复审</Option>
                            <Option  value={"30"}>通过</Option>
                      </Select>
                    </FormItem>
              </Col>
             }  
            <Col span="2" className='col_h'>
                <Button className="ant-btn" icon="plus" onClick={this.add}></Button>
                  <span style={{padding:"0 5px"}}></span>
           {this.state.keys.length==1&&this.state.itemOne==false?"":<Button className="ant-btn" shape="circle" icon="minus" onClick={this.remove.bind(this,k)}></Button>}  
            </Col>
          </Row>
        </div>);
    });
    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 15
      },
    };
    return (
      <Modal maskClosable={false} title={props.title} visible={props.visible} onCancel={this.handleCancel} width={1200}  footer={modalBtns} >
          <Form horizontal form={this.props.form} >
            <Row>
             <Col span='6' className='col_h'>
                <FormItem  {...{ labelCol: {span: 8},wrapperCol: {span: 16}}}        
                  label="规则名称：">
                  <Input  {...getFieldProps('name', {initialValue:"", rules: [{required:true,message: '请输入规则名称，最多20个字!',max:20}]})} type="text" autoComplete="off" />
                </FormItem>
              </Col>
             <Col span='6' className='col_h'>
                <FormItem  {...{ labelCol: {span:6},wrapperCol: {span: 14}}}
                  label="模式：">
                    <Select style={{ width: '100%' }} {...getFieldProps('type', {initialValue: '20',onChange:this.change})} >
                     <Option  value={'10'}>评分模式</Option>
                     <Option  value={'20'}>结果模式</Option>
                   </Select>
                </FormItem>
              </Col>  
      {/*-------------------------------------------------------------------------------------------------------------------------------------------------*/}
            </Row>
      {/*----------------------------------------------------------------------------------------------------------------------------------------------------*/}

             {
               this.state.type=="10"? 
              <div>
                <Input type='hidden' {...getFieldProps('0id2',{initialValue:this.formData2?this.formData2['0id2']:0})}/>
               <p style={{width:"100px",height:"20px",color:"#2db7f5",fontSize:"15px",textAlign:"center",borderLeft:"2px solid #2db7f5",lineHeight:"20px" ,marginBottom:"10px"}}>决策引擎</p>
               {this.state.itemTwo ?( <Row>
                 <Col span="8" className='col_h'>
                <FormItem  {...{
                  labelCol: {
                  span:6
                  },
                  wrapperCol: {
                  span: 14
                  },
                  }} label="结果评分:">
                   <Select style={{ width: '100%' }}  placeholder="请选择"
                     {...getFieldProps('0info_formula',{initialValue:this.formData2?this.formData2["0formula"]:''})}>
                     <Option  value='>'>></Option>
                     <Option  value='<'>{'<'}</Option>
                     <Option  value='='>=</Option>
                     <Option  value='>='>>=</Option>
                     <Option  value='<='>{'<='}</Option>
                     <Option  value='!='>!=</Option>
                     <Option  value='<and<'>{'<and<'}</Option>
                     <Option  value='<=and<'>{'<=and<'}</Option>
                     <Option  value='<=and<='>{'<=and<='}</Option>
                     <Option  value='<and<='>{'<and<='}</Option>
                     {/*
                       <Option  value='include'>包含</Option>
                       <Option  value='exclude'>不包含</Option>
                       */}
                   </Select>
                </FormItem> 
              </Col>
               <Col span="6" className="col_h">
                <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 15}}} label="分数:" >
                 <Input   type="number"   {...getFieldProps('0info_integral',{onChange:this.changeInt,getValueFromEvent: this.changeNUm,initialValue:this.formData2?this.formData2["0integral"]:'',rules: [{type:'number',max:2147483647,message:'输入过多'}]})} />
                </FormItem>
               </Col>
             <Col span="6" className="col_h">
                    <FormItem  {...{ labelCol: {},wrapperCol: {span:20}} }
                      label="">
                      <Select style={{ width: '100%' }}  placeholder="请选择" {...getFieldProps("0info_result",{initialValue:this.formData2?this.formData2["0result"]:''})} >
                           {/*<Option  value="">结果选择</Option>*/} 
                            <Option  value={"10"}>不通过</Option>
                            <Option  value={"20"}>需人工复审</Option>
                            <Option  value={"30"}>通过</Option>
                      </Select>
                    </FormItem>
              </Col>
              <Col span="2">
                <Button className="ant-btn" icon="plus" onClick={this.add2}></Button>
                 {this.state.keys2.length>0?<Button className="ant-btn" shape="circle" icon="minus" style={{marginLeft:'10px'}} onClick={this.removeTwo}></Button>:null} 
              </Col>
            </Row> ):""}
              
            </div> :""
             }
           
    {this.state.keys2.length && this.state.type=="10"? formItem2 :''}
  {/*-----------------------------------------------------------------------------------------------------------------------------------------*/}
            <p style={{width:"100px",height:"20px",color:"#2db7f5",fontSize:"15px",textAlign:"center",borderLeft:"2px solid #2db7f5",lineHeight:"20px",marginBottom:"10px"}}>规则配置</p>
         {this.state.itemOne?( <Row>
           <Input type='hidden' {...getFieldProps('0id',{initialValue:this.formData?this.formData['0id']:0})}/>
             <Col span="8" className='col_h'>
               <FormItem  {...{
                 labelCol: {
                 span: 5
                 },
                 wrapperCol: {
                 span: 18
                 },
                 }}
                 label="表字段:">
                  <Cascader options={this.state.tableInfo} {...getFieldProps('0ccolumn',{initialValue:this.formData?this.formData["0ccolumn"]:''})} placeholder="请选择字段" />
               </FormItem> 
             </Col>
              <Col span="4" className='col_h'>
                <FormItem  {...{
                  labelCol: {
                  span:10
                  },
                  wrapperCol: {
                  span: 14
                  },
                  }} label="表达式:">
                   <Select style={{ width: '100%' }}   placeholder="请选择"
                     {...getFieldProps('0formula',{initialValue:this.formData?this.formData["0formula"]:''},{ rules: [{required:true,message: '必填'}]})} >
                     <Option  value='>'>></Option>
                     <Option  value='<'>{'<'}</Option>
                     <Option  value='='>=</Option>
                     <Option  value='>='>>=</Option>
                     <Option  value='<='>{'<='}</Option>
                     <Option  value='!='>!=</Option>
                     <Option  value='<and<'>{'<and<'}</Option>
                     <Option  value='<=and<'>{'<=and<'}</Option>
                     <Option  value='<=and<='>{'<=and<='}</Option>
                     <Option  value='<and<='>{'<and<='}</Option>
                     <Option  value='include'>包含</Option>
                     <Option  value='exclude'>不包含</Option>
                   </Select>
                </FormItem> 
              </Col>
              <Col span="3" className='col_h'>
                <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 15}}}
                  label="值:">
                  <Input  {...getFieldProps('0cvalue',{initialValue:this.formData?this.formData["0cvalue"]:'',rules: [{max:20,message:'输入过多'}]})} type="text" autoComplete="off" />
                </FormItem>
              </Col>
           {this.state.type=='10'?
                <Col span="3" className="col_h">
                <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 15}}} label="分数:">
                 <Input   type="number"  {...getFieldProps('0integral',{onChange:this.changeInt,getValueFromEvent: this.changeNUm,initialValue:this.formData?this.formData["0integral"]:0,rules: [{type:'number',max:2147483647,message:'输入过多'}]})} />
                </FormItem>
             </Col>
             :<Col span="3" className="col_h">
                    <FormItem  {...{ labelCol: {},wrapperCol: {span:20}} }
                      label="">
                      <Select style={{ width: '100%' }}  placeholder="请选择" {...getFieldProps("0result",{initialValue:this.formData?this.formData["0result"]:''})} >
                            {/*<Option  value="">结果选择</Option>*/}
                            <Option  value={"10"}>不通过</Option>
                            <Option  value={"20"}>需人工复审</Option>
                            <Option  value={"30"}>通过</Option>
                      </Select>
                    </FormItem>
              </Col>
             }  
              <Col span="2">
                <Button className="ant-btn" icon="plus" onClick={this.add}></Button>
                {this.state.keys.length>0?<Button className="ant-btn" shape="circle" icon="minus" style={{marginLeft:'10px'}} onClick={this.removeFirst}></Button>:null} 
              </Col>
            </Row>):("")}
           
            
           {/* {this.state.keys2.length? formItem2 : null}*/} 
             {this.state.keys.length ? formItems : null}
          </Form>
      </Modal>
    );
  }
});
AddRole = createForm()(AddRole);
export default AddRole;