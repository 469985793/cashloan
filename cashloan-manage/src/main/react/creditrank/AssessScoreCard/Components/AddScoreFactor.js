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
  InputNumber,
  Icon
} from 'antd';
const RadioGroup = Radio.Group;
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
import DemoModal from './DemoModal'
const objectAssign = require('object-assign');
var AddScoreFactor = React.createClass({
  getInitialState() {
    return {
      keys: [],
      selectVlaue: 1,
      tableInfo: [],
      num: 1,
      nnid:20,
      factorNameTxt:'',
      passwordDirty: false,
      itemOne:true,
      visible: false,
      number:[]
    };
  },
  handleCancel() {
   
    this.props.hideModal();
    this.setState({
      keys: [],
      selectVlaue: 1,
      number:[],
      itemOne:true

  });
    this.formData = {};
    this.state.num = 1;
    this.props.form.resetFields();
  },
  hideModal(){
    this.setState({
      visible: false,
    });
  },
  showModal(title){
    this.setState({
      visible: true,
      title:title
    })
  },
  handleOk(e) {
    e.preventDefault();
    var me = this;
    var props = me.props;
    var record = props.record;
    var title = props.title;
    var tableInfo=this.state.tableInfo;
    var cardRecord=JSON.parse(localStorage.record);
   // //console.log("tableInfo44444444444",tableInfo);
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      var data = values;
      var params = {};
      var req = [];
      var res=[];
      var url = '';
      var paramsData=''
      if(data['table']){
        data.ctable=data['table'][0];
        data.ccolumn=data['table'][1];
      }
      var corresData=this.state.corresData;
      for(var i=0;i<corresData.length;i++){
          if(corresData[i].tbNid==data.ctable) {
            data.ctableName=corresData[i].tbName;
          }
          for(var j=0;j<corresData[i].children.length;j++){
            if(corresData[i].children[j].nid==data.ccolumn) {
               data.ccolumnName=corresData[i].children[j].name;
               data.ctype= corresData[i].children[j].type;
            }
         }
      }
      var itemIdData=this.props.itemIdData;
      var itemIdTxt='';
      itemIdData.map((item,index)=>{
          if(item.itemName==data.itemName){
            return itemIdTxt=item.id
          }
      })   
      //req
      let obj = {};
      if(data['01formula']){
          obj.formula = data['01formula'];
          obj.cvalue = data['0cvalue'];
          obj.paramScore=data['0paramScore'];
          obj.id=data['0id'];
          obj.paramName=data['0paramName'];
          obj.factorId=data['0factorId'];
          obj.factorName=data['0factorName'];
          req.push(obj)
      if (data.keys.length != 0) {
        data.keys.forEach(item => {
          let obj = {};
          obj.formula = data[item + '1formula'];
          obj.cvalue = data[item + 'cvalue'];
          obj.paramScore=data[item+'paramScore'];
          obj.id=data[item+'id'];
          obj.paramName=data[item+'paramName'];
          obj.factorId=data[item+'factorId'];
          obj.factorName=data[item+'factorName'];
          req.push(obj)
        });
      };
      }else if(data['0formula']){
            obj.formula = data['0formula'];
            obj.cvalue = data['0cvalue'];
            obj.paramScore=data['0paramScore'];
            obj.id=data['0id'];
            obj.paramName=data['0paramName'];
            obj.factorId=data['0factorId'];
            obj.factorName=data['0factorName'];
            req.push(obj)
        if (data.keys.length != 0) {
          data.keys.forEach(item => {
            let obj = {};
            obj.formula = data[item + 'formula'];
            obj.cvalue = data[item + 'cvalue'];
            obj.paramScore=data[item+'paramScore'];
            obj.id=data[item+'id'];
            obj.paramName=data[item+'paramName'];
            obj.factorId=data[item+'factorId'];
            obj.factorName=data[item+'factorName'];
            req.push(obj)
          });
        };
      }else{
         if (data.keys.length != 0) {
          data.keys.forEach(item => {
            let obj = {};
            obj.formula = data[item + 'formula'];
            obj.cvalue = data[item + 'cvalue'];
            obj.paramScore=data[item+'paramScore'];
            obj.id=data[item+'id'];
            obj.paramName=data[item+'paramName'];
            obj.factorId=data[item+'factorId'];
            obj.factorName=data[item+'factorName'];
            req.push(obj)
          });
        };
      }
      
      params.search =req;
      var factorModel ={
           "itemName":data.itemName,
           "factorName":data.factorName,
           "type":data.type,
           "factorScore":data.factorScore?(data.factorScore):"",
           "cardId":cardRecord.id,
           "itemId":itemIdTxt?(itemIdTxt):"",
           "nnid":20,
           "cardName":cardRecord.cardName?(cardRecord.cardName):"",
           "ccolumnName":data.ccolumnName?(data.ccolumnName):"",
           "ccolumn":data.ccolumn?(data.ccolumn):"",
           "ctable":data.ctable?(data.ctable):"",
           "ctableName":data.ctableName?(data.ctableName):"",
           "ctype":data.ctype?(data.ctype):"",
           "cardId":data.cardname?(data.cardname):0
          }
        url = '/modules/manage/cr/factor/save.htm';  
         paramsData={
           "factorModel":JSON.stringify(factorModel), 
           "secreditrankh":JSON.stringify(params.search)
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
          Modal.success({
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
    this.state.keys = keys;
    form.setFieldsValue({
      keys,
    });

    //console.log("uuuuuuuuuuuuuuu",form.getFieldValue([k + 'paramScore']))
    var max=this.state.number.filter((key) => {
      return key !== form.getFieldValue([k + 'paramScore']);
    });
    this.setState({
      number:max
    })
     //console.log("iiiiiiiiiii",max);
     var maxnumber=Math.max.apply(Math, max)
     this.props.form.setFieldsValue({
          factorScore: maxnumber,
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

    //console.log("uuuuuuuuuuuuuuu",form.getFieldValue([0 + 'paramScore']))
     var max=this.state.number.filter((key) => {
      return key !== form.getFieldValue([0 + 'paramScore']);
    });
    this.setState({
      number:max
    })
     //console.log("iiiiiiiiiii",max);
     var maxnumber=Math.max.apply(Math, max)
     this.props.form.setFieldsValue({
          factorScore: maxnumber,
        });
      
  },

  add() {
    const {
      form
    } = this.props;
    let keys = form.getFieldValue('keys');
    keys = keys.concat(this.state.selectVlaue++);
    this.state.keys = keys;
    form.setFieldsValue({
      keys
    });
  },

  formData: {},

  transformeData(data) {
    var formData = {};
    var keys = [];
    if (data['01formula']) {
      data.forEach((item, index) => {
        let k = index;
        keys.push(k);

        formData[`${k}1formula`] = item.formula;
        formData[`${k}cvalue`] = item.cvalue;
        formData[`${k}paramScore`] = item.paramScore;
        formData[`${k}id`] = item.id;
        formData[`${k}factorId`] = item.factorId;
        formData[`${k}factorName`] = item.factorName;
        formData[`${k}paramName`] = item.paramName;
      })
    } else {
      data.forEach((item, index) => {
        let k = index;
        keys.push(k);

        formData[`${k}formula`] = item.formula;
        formData[`${k}cvalue`] = item.cvalue;
        formData[`${k}paramScore`] = item.paramScore;
        formData[`${k}id`] = item.id;
        formData[`${k}factorId`] = item.factorId;
        formData[`${k}factorName`] = item.factorName;
        formData[`${k}paramName`] = item.paramName;
      })
    }

    let keys1 = keys.splice(0, 1);
    formData["keys"] = keys;
    return formData;
  },

  componentWillReceiveProps(nextProps) {
    if (nextProps.visible) {


        this.formData = {};
    }
  },
  componentDidMount() {
    var search={
      state:'10'
    }
    Utils.ajaxData({
       url: '/modules/manage/cr/card/findAll.htm',
       callback: (result) => {
         //console.log("ooooo",result.data);
         var data=result.data;
         var cardName=[];
         cardName=data.map((item)=>{
             return(<Option key={item.id} value={String(item.id)}>{item.cardName}</Option>)
         })
         this.setState({
           cardName:cardName
         })

       }
    });
    Utils.ajaxData({
      url: '/modules/manage/cr/info/page.htm',
      data: {
        pageSize: 10,
        current: 1,
        search:JSON.stringify(search)
      },
      callback: (result) => {
        if (result.code == 200) {
          var tableInfo = [];
          var corresData=[];
          var data = result.data;
          data.forEach((item) => {
            let col1 = {};
            col1.label = item.tbName;
            col1.value = item.tbNid;
            col1.children = [];
            item.children.forEach((item) => {
              let col2 = {};
              col2.label = item.name;
              col2.value = item.nid;
              // col2.type=item.type;
              col1.children.push(col2);
            });
            tableInfo.push(col1);
          });
          this.setState({
            tableInfo: tableInfo,
            corresData:result.data
          });
        }

      }
    });
  },
  onChange(value){
    this.setState({
       nnid:value,
    })
  },
  onFactorNameChange(e){
   this.setState({
       factorNameTxt:e.target.value,
    })
  },
  onTypeChange(value){
   this.setState({
       type:value,
    })
  },

  handleFactorScoreBlur(e) {
    const value = e.target.value;
    this.setState({ passwordDirty: this.state.passwordDirty || !!value });
  },
  checkFactorScore(rule, value, callback) {

    const form = this.props.form;
    var keyAry = this.state.keys;
    // //console.log("pppppp",value);
    // //console.log("oooooo",form.getFieldValue('factorScore'));
    // if (form.getFieldValue('factorScore')) {

    //   // if (value && value > form.getFieldValue('factorScore')) {
    //   //   callback('参数分值小于等于因子分值!');
    //   // } else {
    //   //   callback();
    //   // }
    // }
    // // else if (value) {
    // //   for (var k = 0; k <= keyAry.length; k++) {
    // //     form.resetFields([k + 'paramScore']);
    // //   }
    // //   // Modal.warning({
    // //   //   title: '请先输入评分因子分值',
    // //   // });
    // // }
    //  else {
    //   callback();
    // }
  
     this.state.number.push(value);
    // //console.log("999999999999999",this.state.number);
     var maxnumber=Math.max.apply(Math, this.state.number)
     this.props.form.setFieldsValue({
          factorScore: maxnumber,
        });
        callback();
  },
  checkParamScore(rule, value, callback) {
    const form = this.props.form;
    var keyAry=this.state.keys;   
    if (value && this.state.passwordDirty) {
      if(keyAry.length==0){
        form.validateFields(['0paramScore'], { force: true });
      }else{
        for(var k=0;k<=keyAry.length;k++){
          form.validateFields([k+'paramScore'], { force: true });
         }      
      }   
    }
    callback();
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    getFieldProps('keys', {
      initialValue: [],
    });
    //console.log("898989",this.state)
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          提 交
      </button>
    ];
    const formItemLayout = {
      labelCol: {
        span: 6
      },
      wrapperCol: {
        span: 22
      },
    }; 
    const formItems = state.keys.map((k) => {
      return (<div key={k}>
          <Row>  
          <Col span="3" className='col_h'></Col>
            {this.state.nnid=='20'?
              <Col span="5" className='col_h'>
                <FormItem  {...formItemLayout}>
                  <Select style={{ width: '100%' }}  placeholder="请选择因子参数描述"  disabled={props.canEdit}
                    {...getFieldProps(`${k}1formula`,{initialValue:this.formData?this.formData[`${k}formula`]:'',rules: [{required:true,message: '必填'}]})}>
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
                    :
              <Col span="5" className='col_h'>
                  <FormItem {...formItemLayout}>
                  <Input  {...getFieldProps(`${k}formula`,{initialValue:this.formData?this.formData[`${k}formula`]:'',rules: [{required:true,message: '必填'}]})} type="text" autoComplete="off" placeholder="请输入因子参数描述" disabled={props.canEdit}/>
                  </FormItem> 
                </Col>}
           <Col span="5" className='col_h'>
              <FormItem  {...formItemLayout}> 
                <Input  {...getFieldProps(`${k}cvalue`,{initialValue:this.formData?this.formData[`${k}cvalue`]:'',rules: [{required:true,message: '必填'}]})} type="text" disabled={props.canEdit} autoComplete="off" placeholder="请输入因子参数数值"/>
              </FormItem>
           </Col>
            <Col span="5" className="col_h">
                <FormItem  {...formItemLayout}>
                 <Input   type="text"  {...getFieldProps(`${k}paramScore`,{initialValue:this.formData?this.formData[`${k}paramScore`]:'',rules: [{required:true,message: '必填'},
                   { validator: this.checkFactorScore}]})} placeholder="分值" />
                </FormItem>
             </Col>
             
            <Col span="2" className='col_h'>
                <Button className="ant-btn" icon="plus" onClick={this.add}></Button>
                  <span style={{padding:"0 5px"}}></span>
               {this.state.keys.length==1&&this.state.itemOne==false?"":(<Button className="ant-btn" shape="circle" icon="minus"  onClick={this.remove.bind(this,k)}></Button>)} 
            </Col>
          </Row>
              <Input  {...getFieldProps(`${k}factorId`,{initialValue:0})} type="hidden" />
              <Input  {...getFieldProps(`${k}paramName`,{initialValue:this.state.factorNameTxt})} type="hidden" />
              <Input  {...getFieldProps(`${k}factorName`,{initialValue:this.state.factorNameTxt})} type="hidden" />
              <Input  {...getFieldProps(`${k}id`,{initialValue:0})} type="hidden" />
        </div>);
    });

    return (
      <Modal maskClosable={false} title={props.title} visible={props.visible} onCancel={this.handleCancel} width={900}  footer={modalBtns} >
          <Form  horizontal  form={this.props.form}>
              <Row>
                  <Col span="12" className='col_h'>
                      <FormItem  {...{ labelCol: {span:6},wrapperCol: {span: 14}}} 
                        label="所属评分项目：" >
                         <Select style={{ width: '100%' }} {...getFieldProps('itemName', {rules: [{required:true,message: '请选择所属评分项目'}]})}  placeholder="请选择所属评分项目">
                           {this.props.projectList}
                         </Select>                             
                      </FormItem>
                  </Col>  
                  <Col span="12" className='col_h'>
                      <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 14}}}
                        label="评分因子名称：">
                        <Input  {...getFieldProps('factorName', { onChange:this.onFactorNameChange,rules: [{required:true,message: '请填入因子名称'}]})} placeholder="请填入因子名称" type="text" autoComplete="off" />
                      </FormItem>
                  </Col>
              </Row>
              <Row>
                  <Col span="12" className='col_h'>
                    <FormItem  {...{ labelCol: {span:6},wrapperCol: {span: 14}}}
                      label="维护类型：">
                        <Select style={{ width: '100%' }} {...getFieldProps('type', {initialValue: '20',onChange:this.onTypeChange,rules: [{required:true,message: '请选择维护类型'}]})} placeholder="请选择维护类型">
                        <Option  value={'10'}>系统读取</Option>
                        <Option  value={'20'}>人工维护</Option>
                        <Option  value={'30'}>关联评分卡</Option>
                      </Select>
                    </FormItem>
                  </Col>  
                  {/**<Col span="12" className='col_h'>
                    <FormItem  {...{ labelCol: {span:6},wrapperCol: {span: 14}}}label="信息类型：">
                        <Select style={{ width: '100%' }} {...getFieldProps('nnid', {initialValue: '20',onChange:this.onChange,rules: [{required:true,message: '请选择信息类型'}]})} placeholder="请选择信息类型">
                        <Option  value={'10'}>定性</Option>
                        <Option  value={'20'}>定量</Option>
                      </Select>
                    </FormItem>
                  </Col>   */}
                  <Input  {...getFieldProps('nnid', { initialValue: '20' }) } type="hidden"   />
              </Row>
           
              <Row>  
                {this.state.type=='10'?
                <Col span="12" className='col_h'>
                  <FormItem  {...{ labelCol: {span:6},wrapperCol: {span: 14}}} label="系统参数：">            
                    <Cascader options={this.state.tableInfo} {...getFieldProps('table', {rules: [{required:true,message: '请选择系统参数',type:'array'}]})}  placeholder="请选择系统参数" />
                  </FormItem>
                </Col>:null
                  
               }
                {this.state.type=='30'?
                <Col span="12" className='col_h'>
                  <FormItem  {...{ labelCol: {span:6},wrapperCol: {span: 14}}} label="关联评分卡:">            
                    <Select  {...getFieldProps('cardname', { rules: [{required:true,message: '请填入评分卡',type:"string"}]})}  placeholder="请选择评分卡" >
                      {this.state.cardName}
                    </Select>
                  </FormItem>
                </Col>:null
                  
               }
                <Col span="12" className='col_h'>
                      <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 14}}} 
                        label="因子分值：">
                        <InputNumber  {...getFieldProps('factorScore')} style={{width:'253px'}} disabled={true} onBlur={this.handleFactorScoreBlur} />
                      </FormItem>
                  </Col>
              </Row> 

             
             
                <div>
                  <p style={{width:"100%",borderBottom:"1px solid #d9d9d9",marginBottom:'15px'}}></p>
                  <Row>
                  <Col span="3" className='col_h'></Col>
                    <Col span="5" className='col_h'>
                        <FormItem  {...{ labelCol: {span: 8},wrapperCol: {span: 0}}}
                        label="表达式："></FormItem>
                    </Col>
                    <Col span="5" className='col_h'>
                        <FormItem  {...{ labelCol: {span: 2},wrapperCol: {span: 0}}}
                        label="值："></FormItem>
                    </Col>
                    <Col span="5" className='col_h'>
                        <FormItem  {...{ labelCol: {span: 6},wrapperCol: {span: 0}}}
                        label="分值："></FormItem>
                    </Col>
                  </Row>
                  {this.state.itemOne?
                  <Row>
                    <Col span="3" className='col_h'></Col>
                     {this.state.nnid=='20'? 
                    <Col span="5" className='col_h'>
                      <FormItem {...formItemLayout}>
                        <Select style={{ width: '100%' }}   placeholder="请选择因子参数描述" disabled={props.canEdit}
                          {...getFieldProps('01formula',{initialValue:this.formData?this.formData["0formula"]:'',rules: [{required:true,message: '必填'}]})} >
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
                    :
                     <Col span="5" className='col_h'>
                      <FormItem {...formItemLayout}>
                       <Input  {...getFieldProps('0formula',{initialValue:this.formData?this.formData["0formula"]:'',rules: [{required:true,message: '必填'}]})} type="text" autoComplete="off" placeholder="请输入因子参数描述" disabled={props.canEdit}/>
                      </FormItem> 
                    </Col>
       
                     }
                    <Col span="5" className='col_h'>
                      <FormItem  {...formItemLayout}  >
                        <Input  {...getFieldProps('0cvalue',{initialValue:this.formData?this.formData["0cvalue"]:'',rules: [{required:true,message: '必填'}]})} type="text" autoComplete="off" placeholder="请输入因子参数数值" disabled={props.canEdit}/>
                      </FormItem>
                    </Col>
                    <Col span="5" className='col_h'>
                      <FormItem {...formItemLayout}>
                        <Input  {...getFieldProps('0paramScore',{initialValue:this.formData?this.formData["0paramScore"]:'',rules: [{required:true,message: '必填'},
                          { validator: this.checkFactorScore}]})} type="text" autoComplete="off"  placeholder="分值" />
                      </FormItem>
                    </Col>                    
                        <Input  {...getFieldProps('0id',{initialValue:0})} type="hidden" />
                        <Input  {...getFieldProps('0paramName',{initialValue:this.state.factorNameTxt})} type="hidden" />
                        <Input  {...getFieldProps('0factorId',{initialValue: 0})} type="hidden" />
                        <Input  {...getFieldProps('0factorName',{initialValue:this.state.factorNameTxt})} type="hidden" />
                    <Col span="2">
                      <Button className="ant-btn" icon="plus" onClick={this.add}></Button> 
                     {this.state.keys.length>0?<Button className="ant-btn" shape="circle" icon="minus" style={{marginLeft:'10px'}} onClick={this.removeFirst}></Button>:null} 
                    </Col>
                  </Row>:null}
                  <span style={{'color': '#2db7f5', 'position': 'absolute','right': '89px', 'top': '231px', 'cursor': 'pointer'}} 
                   onClick={this.showModal.bind(this,'因子参数描述区间值填写示例',false)}>填写示例</span>
                  {this.state.keys.length ? formItems : null}
                </div>
              
              
          </Form>
          <DemoModal ref="DemoModal"  visible={state.visible}  title={state.title} hideModal={this.hideModal}  />
              
      </Modal>
    );
  }
});
AddScoreFactor = createForm()(AddScoreFactor);
export default AddScoreFactor;