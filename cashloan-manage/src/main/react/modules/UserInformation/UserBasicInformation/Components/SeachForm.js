import React from 'react';
import {
  Button,
  Form,
  Input,
  Select,
  DatePicker,
  Upload,
  message,
  Icon,
  Modal,
  Row,
  Col,
  Checkbox,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RangePicker = DatePicker.RangePicker;
const confirm = Modal.confirm;
const objectAssign = require('object-assign');
const Dragger = Upload.Dragger;
const props = {
  name: 'Excle',
  action: '/modules/manage/cl/cluser/saveUsers.htm',
  headers: {
    authorization: 'authorization-text',
  },
  onChange(info) {
    if (info.file.status !== 'uploading') {
      console.log(info.file, info.fileList);
    }
    if (info.file.status === 'done') {
      message.success(`${info.file.name} file uploaded successfully`);
    } else if (info.file.status === 'error') {
      message.error(`${info.file.name} file upload failed.`);
    }
  },
};
let SeachForm = React.createClass({
  getInitialState() {
        return {
            
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    var json = {endTime:'',startTime:'',realName:params.realName,loginName:params.loginName,idNo:params.idNo,channelId: params.channelId,registerClient: params.registerClient};
    if(params.registTime){
      json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
      json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
    }
    if(json.realName){
      json.realName = json.realName.replace(/\s+/g, "") 
    }
    this.props.passParams({
      searchParams : JSON.stringify(json),
      pageSize: 10,
      current: 1,
    });
  },
  handleReset() {
    this.props.form.resetFields();
    this.props.passParams({
      pageSize: 10,
      current: 1,
    });
  },
  componentDidMount() {
    this.fetch();
  },
  fetch(){
    Utils.ajaxData({
      url: '/modules/manage/promotion/channel/listChannel.htm',
      callback: (result) => {
        this.setState({
          data: result.data,
        });
      }
    });
  },
  disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var channelList = [];
    if(this.state.data){
      channelList.push(<Option key={'全部'} value= {''} >全部</Option>);
      this.state.data.map(item => {
        channelList.push(<Option key={item.name} value= {item.id} >{item.name}</Option>)
      })
    }
    return (
      <div>
      <Form inline >
        <FormItem label="LastName：">
          <Input  {...getFieldProps('realName') } />
        </FormItem>
        <FormItem label="Phone：">
          <Input  {...getFieldProps('loginName') } />
        </FormItem>
        <FormItem label="RegistrationClient：">
          <Select style={{ width: 170 }} {...getFieldProps('registerClient',{initialValue: ''})}>
            <Option value= {''} >All</Option>{/*全部*/}
            <Option value= {'20000'} >pc</Option>
            <Option value= {'10000'} >android</Option>
          </Select>
        </FormItem>
        <FormItem label="RegistrationTime：">
            <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('registTime', { initialValue: '' }) } />
        </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>Search</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>Reset</Button></FormItem>
        <Upload {...props}>
        <Button>
          <Icon type="upload" /> Click to Upload
        </Button>
      </Upload>
      </Form>
      </div>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;