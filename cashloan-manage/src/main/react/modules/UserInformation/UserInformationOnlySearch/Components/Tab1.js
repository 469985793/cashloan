import React from 'react';
import {
  Modal,
  Form,
  Input,
  Row,
  Col,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const objectAssign = require('object-assign');
const userbaseTit = {
  color: '#2db7f5',
  textAlign: 'center',
  fontSize: '14px',
  marginBottom: '10px',
  display: 'block',
  width: '150px',
}
var Tab1 = React.createClass({
  getInitialState() {
    return {
    };
  },
  componentWillReceiveProps(nextProps){
    if(nextProps.recordSoure){
      this.setState({
        recordSoure: nextProps.recordSoure
      })
    }
  },
  
  render() {
    var props = this.props;
    var state = this.state;
    const {
        getFieldProps
    } = this.props.form;
    const formItemLayout = {
            labelCol: {
                span: 9
            },
            wrapperCol: {
                span: 14
            },
        };
    const formItemLayout2 = {
            labelCol: {
                span: 5
            },
            wrapperCol: {
                span: 19
            },
        };
        var aItem = [];
        if(state.recordSoure && state.recordSoure.workImgArr){
          aItem = [];
          for(var i = 0; i < state.recordSoure.workImgArr.length; i++){
            aItem.push(<a key={i} style={{ marginRight: '10px'}} href={state.recordSoure.workImgArr[i]} target="_blank"><img src={state.recordSoure.workImgArr[i]} style={{width:150,height:150}} /></a>);
          }
        }else{
          aItem.push(<span key={'none'}>暂无</span>)
        }
        
    return (
          <Form horizontal form={this.props.form} style={{marginTop:'20'}}>
            {state.recordSoure?
            <div>
            <div className="uploadFile" style={{ paddingLeft: '20' }}>
              <div className="img" style={{ height: '210' }}>
                <span style={userbaseTit}>人脸照片</span>
                {state.recordSoure.userbase.livingImg ? <a href={state.recordSoure.userbase.livingImg} target="_blank"><img src={state.recordSoure.userbase.livingImg} style={{ width: 150, height: 150 }} /></a> : <span>暂无</span>}
              </div>
              <div className="img" style={{ height: '210' }}>
                <span style={userbaseTit}>身份证正面</span>
                {state.recordSoure.userbase.frontImg ? <a href={state.recordSoure.userbase.frontImg} target="_blank"><img src={state.recordSoure.userbase.frontImg} style={{ width: 150, height: 150 }} /></a> : <span>暂无</span>}
              </div>
              <div className="img" style={{ height: '210' }}>
                <span style={userbaseTit}>身份证背面</span>
                {state.recordSoure.userbase.backImg ? <a href={state.recordSoure.userbase.backImg} target="_blank"><img src={state.recordSoure.userbase.backImg} style={{ width: 150, height: 150 }} /></a> : <span>暂无</span>}
              </div>
            </div>
             <div className="uploadFile" style={{paddingLeft:'20'}}>
               <div className="img1" style={{height:'210'}}>
                <span style={userbaseTit}>工作照</span>
                {aItem}
              </div>
              </div>
              </div>
            :null}
            <div className="navLine-wrap-left">
              <h2>基本信息</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="真实姓名：">
                    <Input {...getFieldProps('realName', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="性别：">
                    <Input {...getFieldProps('sex', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="年龄：">
                    <Input {...getFieldProps('age', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="身份证号码：">
                    <Input {...getFieldProps('idNo', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="银行卡号：">
                    <Input {...getFieldProps('cardNo', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="所属银行：">
                    <Input {...getFieldProps('bank', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="银行预留手机号：">
                    <Input {...getFieldProps('bankPhone', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="联系电话：">
                    <Input {...getFieldProps('phone', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="注册时间：">
                    <Input {...getFieldProps('registTime', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="14">
                  <FormItem {...formItemLayout2} label="居住地址：">
                    <Input {...getFieldProps('liveAddr', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="14">
                  <FormItem {...formItemLayout2} label="注册所在地：">
                    <Input {...getFieldProps('registerAddr', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="10">
                  <FormItem {...formItemLayout2} label="注册地经纬度：">
                    <Input {...getFieldProps('registerCoordinate', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="注册客户端：">
                    <Input {...getFieldProps('registerClient', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="注册渠道：">
                    <Input {...getFieldProps('channelName', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="芝麻分：">
                    <Input {...getFieldProps('score', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="学历：">
                    <Input {...getFieldProps('education', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="常用邮箱：">
                    <Input {...getFieldProps('email', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="淘宝账号：">
                    <Input {...getFieldProps('taobao', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="微信账号：">
                    <Input {...getFieldProps('wechat', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="QQ账号：">
                    <Input {...getFieldProps('qq', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <h2>单位信息</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="单位名称：">
                    <Input {...getFieldProps('companyName', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="单位电话：">
                    <Input {...getFieldProps('companyPhone', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
	                <FormItem {...formItemLayout} label="工作年限：">
	                  <Input {...getFieldProps('workingYears', { initialValue: '' }) } disabled={props.canEdit} />
	                </FormItem>
	              </Col>
               
              </Row>
              <Row>
              {/* <Col span="8">
                  <FormItem {...formItemLayout} label="月薪范围：">
                    <Input {...getFieldProps('salary', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              <Col span="8">
                  <FormItem {...formItemLayout} label="发薪日期：">
                    <Input {...getFieldProps('dd', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>*/}
              </Row>
              <Row>
                <Col span="14">
                  <FormItem {...formItemLayout2} label="单位地址：">
                    <Input {...getFieldProps('companyAddr', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <h2>联系人信息</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="紧急联系人姓名：">
                    <Input {...getFieldProps('urgentName', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="紧急联系人联系方式：">
                    <Input {...getFieldProps('urgentPhone', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="紧急联系人与本人关系：">
                    <Input {...getFieldProps('urgentRelation', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="其他联系人姓名：">
                    <Input {...getFieldProps('otherName', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="其他联系人联系方式：">
                    <Input {...getFieldProps('otherPhone', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="其他联系人与本人关系：">
                    <Input {...getFieldProps('otherRelation', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <h2>认证状态</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="银行卡认证状态：">
                    <Input {...getFieldProps('bankCardState', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="身份认证状态：">
                    <Input {...getFieldProps('idState', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="手机运营商认证状态：">
                    <Input {...getFieldProps('phoneState', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="芝麻授信状态：">
                    <Input {...getFieldProps('zhimaState', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
              </Row>
            </div>
          </Form>
    );
  }
});
Tab1 = createForm()(Tab1);
export default Tab1;