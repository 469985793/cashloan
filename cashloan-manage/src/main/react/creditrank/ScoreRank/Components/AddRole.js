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
//const RadioGroup = Radio.Group;
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const custType = [];
var AddRole = React.createClass({
  getInitialState() {
    return {
      keys: [],
      selectVlaue: 1,
      tableInfo: [],
      childInfo: [],
      realItem: [],
      num: 1,
      rank:[],
      itemValue:"",
      rankArray:[],
      
    };
  },
 
  componentDidMount() {
      var params = {
                pageSize: 10,
                current: 1,
                code:"RANK_TYPE"
            }
        Utils.ajaxData({
            url: '/modules/manage/system/dict/findByTypeCode.htm',
            data: params,
            callback: (result) => {
                var data=result.data;
              ////console.log("0000000000",data);
                var rankArray=[];
                data.forEach(item=>{
                  rankArray.push(<Option key={item.id} value={item.itemValue}>{item.itemValue}</Option>)
                })
                this.setState({    
                    data: result.data,
                    rankArray:rankArray
                });
            }
        });
  },

  handleCancel() {
   // this.props.form.resetFields();
    this.props.hideModal();
    this.setState({
      num: 1,
      keys: [],
      selectVlaue: 1
    });
    this.formData = {};
  },
  handleOk(e) {
    e.preventDefault();
    var me = this;
    var props = me.props;
    var record = props.record;
    var title = props.title;

    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      //console.log("values0000000",values)
      
     
      if (this.props.title == 'Amount maintenance') {
        var data = values;
        var params = {};
        var req = [];
        var url = '';
        params.name = data.name;
        if (data.keys.length != 0) {
          data.keys.forEach((item) => {
            let obj = {};
            if(this.state[item+'rtype']=="10"){
               obj.amountMax = data[item + 'amountMax'];
               obj.amountMin = data[item + 'amountMin'];
            }else{
               obj.amountMax = data[item + 'amount'];
               obj.amountMin = data[item + 'amount'];
            }
           // obj.rankName = data["rankName"];
            obj.id =data[item+'id']==undefined? 0:data[item+'id'];
            obj.rank = data[item+'rank'];
            obj.rtype=data[item+"rtype"]
            obj.scoreMax = data[item+'scoreMax'];
            obj.scoreMin = data[item+'scoreMin'];
            obj.state=data[item+"state"]
            req.push(obj)
          });
      };
        params.search = req;
        url = '/modules/manage/cr/rank/save.htm';
        var paramsData={"id":this.props.idData,"rankName":data.rankName,"search":JSON.stringify(params.search)}
      } else {
       
        var data = values;
        var params = {};
        var req = [];
        var url = '';
        params.name = data.name;
        //req
        let obj = {};
       
        if (data.keys.length != 0) {
          data.keys.forEach((item) => {
            let obj = {};
            if(this.state[item+'rtype']=="10"){
               obj.amountMax = data[item + 'amountMax'];
               obj.amountMin = data[item + 'amountMin'];
            }else{
               obj.amountMax = data[item + 'amount'];
               obj.amountMin = data[item + 'amount'];
            }
            obj.rank = data[item+'rank'];
            obj.rtype=data[item+"rtype"];
            obj.id=0;
            obj.scoreMax = data[item+'scoreMax'];
            obj.scoreMin = data[item+'scoreMin'];
            obj.state=data[item+"state"]
            req.push(obj)
          });
      };
        params.search = req;
        // params.id=0;
        // params.rankName=data.rankName;
        url = '/modules/manage/cr/rank/save.htm';
        var paramsData={"id":"0","rankName":data.rankName,"search":JSON.stringify(params.search)}
      };
      console.log(paramsData);
      Utils.ajaxData({
        url: url,
        data:paramsData,
        callback: (result) => {
          if (result.code == 200) {
            this.handleCancel();
          };
          let resType = result.code == 200 ? 'success' : 'warning';
         Modal.success({
              title: "success",//成功
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
    console.log(keys)
    this.state.keys = keys;
    form.setFieldsValue({
      keys,
    });
  },
  add() {
  const { form } = this.props;
        let keys = form.getFieldValue('keys');
        keys = keys.concat("a"+this.selectVlaue++);
        form.setFieldsValue({
            keys
        });
  },
  selectVlaue:0,
  handleTableChange(value) {
    //console.log(value)
    this.setState({
      realItem: this.state.childInfo[value]
    });
  },
  formData: {},
  transformeData(data) {
    var formData = {};
    var keys = [];
    data.forEach((item, index) => {
      let k = index+1;
      keys.push(k);
      formData[`${k}scoreMin`] = item.scoreMin;
      formData[`${k}scoreMax`] = item.scoreMax;
      formData[`${k}amountMin`] = item.amountMin;
      formData[`${k}amountMax`] = item.amountMax;
      formData[`${k}amount`]=item.amountMin;
      formData[`${k}amount`]=item.amountMax;
      formData[`${k}id`]=item.id;
      formData[`${k}rtype`] = item.rtype;
      formData[`${k}rank`] = item.rank;
      formData[`${k}state`] = item.state;
      this.setState({
         [k+'rtype']:item.rtype
      })
    })
    //let keys1 = keys.splice(0, 1);
    formData["keys"] = keys;
    return formData;
  },
   componentWillReceiveProps(nextProps) {
   
    if(nextProps.visible){
    if (nextProps.title == 'Amount maintenance'  && this.state.num == 1) {//额度维护
       var data = nextProps.rankData
       //console.log("-----------",data);
       this.formData = this.transformeData(data);
       //console.log("oooooooo",this.formData)
      this.props.form.setFieldsValue({
        keys: this.formData.keys
      });
      // this.setState({
      //   selectVlaue: this.formData.keys.length + 1,
      //   keys: this.formData.keys,
      // });
     this.state.num = 2;
      this.setState({
        rank:nextProps.rank,
      })
    }
     else if (nextProps.title == 'Add rating') {//新增评分等级
      this.formData = {};
      this.setState({
        rank:nextProps.rank,
        itemValue:nextProps.itemValue
      })
    }else if(nextProps.title == 'View'  && this.state.num == 1){//查看

       var data = nextProps.rankData
       
       this.formData = this.transformeData(data);
       //console.log("oooooooo",this.formData)
      this.props.form.setFieldsValue({
        keys: this.formData.keys
      });
      this.setState({
        selectVlaue: this.formData.keys.length + 1,
        keys: this.formData.keys,
      });
     this.state.num = 2;
      this.setState({
        rank:nextProps.rank,
      })
    }
    }
  },
  //改变额度类别区间值
  change(k,value){
     //console.log(k,value)
     this.setState({
     [k+'rtype']:value
   })
   
  },
  render() {
   const { getFieldProps, getFieldValue } = this.props.form;
        getFieldProps('keys', {
            initialValue: [1],
        });

    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 15
      },
    };
    var props = this.props;
    var state = this.state;
    var lookIcon = props.canEdit ? true:false;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>{/*返 回*/}Back</button>,
      <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          {/*提 交*/}Submit
      </button>
    ];
  
    const formItems =getFieldValue('keys').map((k) => {
      return (<tr key={k}>
                <td key={`k1`}>
                <FormItem>
                    <InputNumber type="number" {...getFieldProps( `${k}scoreMin`, { initialValue:this.formData?this.formData[`${k}scoreMin`]:"" }) } disabled={!props.canEdit}/>{/*至*/} - <InputNumber type="number" {...getFieldProps(`${k}scoreMax`, { initialValue: this.formData?this.formData[`${k}scoreMax`]:"" }) } disabled={!props.canEdit} />
                </FormItem>
                </td>
                <td key={`k2`}>
                    <FormItem>
                        <Select style={{ width: 80, border:"1px solid #D9D9D9",borderRadius:"3px" }}  placeholder="it's usable or not" {...getFieldProps( `${k}state`, { initialValue: this.formData?this.formData[`${k}state`]:"" }) } disabled={!props.canEdit} >{/*是否可用*/}
                          <Option value={"10"}>{/*是*/}Yes</Option>
                          <Option value={"20"}>{/*否*/}No</Option>
                        </Select>
                    </FormItem>
                </td>
                <td key={`k3`}>
                    <FormItem>
                      <Select style={{ width: 80, border:"1px solid #D9D9D9",borderRadius:"3px" }}  placeholder="Amount category" {...getFieldProps( `${k}rtype`, { initialValue:this.formData?this.formData[`${k}rtype`]:"" ,onChange:this.change.bind(this,k)}) }  disabled={!props.canEdit}>{/*额度类别*/}
                        <Option value={"10"}>{/*区间*/}Interval</Option>
                        <Option value={"20"}>{/*固定值*/}Fixed value</Option>
                      </Select>
                    </FormItem>
                </td>
                <td key={`k4`}>
                  {this.state[k+'rtype']=="10"?(
                        <FormItem>
                          <InputNumber type="number" disabled={!props.canEdit} {...getFieldProps( `${k}amountMin`, { initialValue: this.formData?this.formData[`${k}amountMin`]:""}) } />{/*至*/} - <InputNumber type="number" disabled={!props.canEdit} {...getFieldProps( `${k}amountMax`, { initialValue: this.formData?this.formData[`${k}amountMax`]:"" }) }/>
                           <InputNumber type='hidden' disabled={!props.canEdit} {...getFieldProps( `${k}id`, { initialValue:this.formData?this.formData[`${k}id`]:"" }) } style={{width:"0px",diaplay:"none",border:"none"}}/>
                        </FormItem>
                    ):(
                        <FormItem>
                          <InputNumber type="number" disabled={!props.canEdit} {...getFieldProps( `${k}amount`, { initialValue:this.formData?this.formData[`${k}amount`]:"" }) } style={{width:"120px","textAlign":"center"}}   />
                          <InputNumber type='hidden' disabled={!props.canEdit} {...getFieldProps( `${k}id`, { initialValue:this.formData?this.formData[`${k}id`]:"" }) } style={{width:"0px",diaplay:"none",border:"none"}} />
                        </FormItem>
                      )}
               </td>
               <td key={`k5`}>
                  <FormItem>
                      <Select style={{ width: 80, border:"1px solid #D9D9D9",borderRadius:"3px" }} placeholder="Credit rating" disabled={!props.canEdit} {...getFieldProps( `${k}rank`, { initialValue:this.formData?this.formData[`${k}rank`]:"" }) }  >{/*授信等级*/}
                         {this.state.rankArray}
                      </Select>
                    </FormItem>
                </td>
                {this.props.title=="View"?"":(
                  <td key={`k6`}>
                     <a onClick={() => this.add()}>{/*增加*/}Add</a>
                     <span className="ant-divider"></span>
                     <a onClick={() => this.remove(k)}>{/*删除*/}Delete</a>
                  </td>
                )}{/*查看*/}
              
            </tr >);
    });
    return (
      <Modal maskClosable={false} title={props.title} visible={props.visible} onCancel={this.handleCancel} width={1050}  footer={this.props.title=="View"?[<button key="back" className="ant-btn" onClick={this.handleCancel}>{/*返 回*/}Back</button>]:modalBtns} >{/*查看*/}
          <Form horizontal form={this.props.form}>
           <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
              <Row> 
                <Col span="8">
                  <FormItem {...formItemLayout}  label="Rating level name:">{/*评分等级名称*/}
                    <Input style={{ width: '100%' }} placeholder="Please enter a rating name" {...getFieldProps('rankName', { initialValue: this.formData?this.formData['rankName']:"",rules: [{required:lookIcon,message: 'Required'}]}) } disabled={!props.canEdit}/>{/*请输入评分等级名称 必填*/}
                  </FormItem>
                </Col>
              </Row>
              <table style={{ width:960, marginLeft: 25 }} border="1" className="addTable">
                <tbody>
                    <tr style={{ width: '16%'}}>
                        <td>
                            {/*分值区间*/}Score interval
                        </td>
                        <td>
                            {/*是否可用*/}It's usable or not
                        </td>
                        <td>
                            {/*额度类别*/}Amount category
                        </td>
                         <td>
                            {/*授信额度*/}Credit line
                        </td>
                        <td >
                            {/*授信等级*/}Credit rating
                        </td>
                        {this.props.title=="View"?"":
                        <td >
                            Operating
                        </td>}
                    </tr>
                     {getFieldValue('keys').length ? formItems :<tr className="noData"><td colSpan='6'>No data</td></tr> }{/*查看 操作 暂无数据*/}
                </tbody>
             </table>
        </Form>
      </Modal>
    );
  }
});
AddRole = createForm()(AddRole);
export default AddRole;