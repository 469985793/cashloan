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
  componentWillReceiveProps(nextProps) {
    if (nextProps.activeKey == '2') {
      this.setState({
        recordSoure: nextProps.recordSoure
      })
    }
  },
  componentDidMount() {
    //this.props.form.resetFields();
    this.props.form.setFieldsValue(this.props.dataForm);
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
    if (props.recordSoure && props.recordSoure.workImgArr) {
      aItem = [];
      for (var i = 0; i < props.recordSoure.workImgArr.length; i++) {
        aItem.push(<a key={i} style={{ marginRight: '10px' }} href={props.recordSoure.workImgArr[i]} target="_blank"><img src={props.recordSoure.workImgArr[i]} style={{ width: 150, height: 150 }} /></a>);
      }
    } else {
      aItem.push(<span key={'none'}>暂无</span>)
    }
    return (
      <Form horizontal form={this.props.form} style={{ marginTop: '20' }}>
        {props.recordSoure ?
          <div>
            <div className="uploadFile" style={{ paddingLeft: '20' }}>
              <div className="img" style={{ height: '210' }}>
                <span style={userbaseTit}>Face Image</span>
                {props.recordSoure.userbase.livingImg ? <a href={props.recordSoure.userbase.livingImg} target="_blank"><img src={props.recordSoure.userbase.livingImg} style={{ width: 150, height: 150 }} /></a> : <span>暂无</span>}
              </div>
        {/*<div className="img" style={{ height: '210' }}>
                <span style={userbaseTit}>身份证正面</span>
                {props.recordSoure.userbase.frontImg ? <a href={props.recordSoure.userbase.frontImg} target="_blank"><img src={props.recordSoure.userbase.frontImg} style={{ width: 150, height: 150 }} /></a> : <span>暂无</span>}
              </div>
              <div className="img" style={{ height: '210' }}>
                <span style={userbaseTit}>身份证背面</span>
                {props.recordSoure.userbase.backImg ? <a href={props.recordSoure.userbase.backImg} target="_blank"><img src={props.recordSoure.userbase.backImg} style={{ width: 150, height: 150 }} /></a> : <span>暂无</span>}
              </div>*/}
            </div>
        {/*<div className="uploadFile" style={{ paddingLeft: '20' }}>
              <div className="img1" style={{ height: '210' }}>
                <span style={userbaseTit}>工作照</span>
                {aItem}
              </div>
            </div>*/}
          </div>
          : null}
        <div className="navLine-wrap-left">
              <h2>Message</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="RealName：">
                    <Input {...getFieldProps('realName', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Gender：">
                    <Input {...getFieldProps('sex', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                 <Col span="8">
                  <FormItem {...formItemLayout} label="birthday：">
                    <Input {...getFieldProps('birthday', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="IDCard：">
                    <Input {...getFieldProps('cardNo', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="childrenNumber：">
                    <Input {...getFieldProps('childrenNumber', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="marryStatus：">
                    <Input {...getFieldProps('marryStatus', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                {/*<Col span="8">
                  <FormItem {...formItemLayout} label="银行预留手机号：">
                    <Input {...getFieldProps('bankPhone', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>*/}
                <Col span="8">
                  <FormItem {...formItemLayout} label="Phone：">
                    <Input {...getFieldProps('phone', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="RegistTime：">
                    <Input {...getFieldProps('registTime', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="14">
                  <FormItem {...formItemLayout2} label="LiveAddress：">
                    <Input {...getFieldProps('liveAddr', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
    {/*<Row>
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
              </Row>*/}
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="registerClient：">
                    <Input {...getFieldProps('channelName', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                {/* <Col span="8">
                  <FormItem {...formItemLayout} label="注册渠道：">
                    <Input {...getFieldProps('channelName', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="芝麻分：">
                    <Input {...getFieldProps('score', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>*/}
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Education：">
                    <Input {...getFieldProps('education', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Email：">
                    <Input {...getFieldProps('email', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                  {/* <Col span="8">
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
                </Col>*/}
              </Row>
              <h2>Company Message</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="CompanyName：">
                    <Input {...getFieldProps('companyName', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
            {/* <Col span="8">
                  <FormItem {...formItemLayout} label="单位电话：">
                    <Input {...getFieldProps('companyPhone', { initialValue: '' }) } disabled={props.canEdit} />
                  </FormItem>
                </Col>*/}
                <Col span="8">
                    <FormItem {...formItemLayout} label="CompanyEmail：">
                    <Input {...getFieldProps('companyPhone', { initialValue: '' }) } disabled={true} />
              </FormItem>
                </Col>
                <Col span="8">
	                <FormItem {...formItemLayout} label="workingYears：">
	                  <Input {...getFieldProps('workingYears', { initialValue: '' }) } disabled={true} />
	                </FormItem>
	              </Col>
               
              </Row>
              <Row>
               <Col span="8">
                  <FormItem {...formItemLayout} label="jobStatus：">
                    <Input {...getFieldProps('jobStatus', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              <Col span="8">
                  <FormItem {...formItemLayout} label="Wage Range：">
                    <Input {...getFieldProps('jobMonthIncome', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="14">
                  <FormItem {...formItemLayout2} label="CompanyAddress：">
                    <Input {...getFieldProps('companyAddr', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
              <h2>Contacts Message</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Family Member Name：">
                    <Input {...getFieldProps('urgentName', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Family Member Phone：">
                    <Input {...getFieldProps('urgentPhone', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Family Member Relation：">
                    <Input {...getFieldProps('urgentRelation', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Other Name：">
                    <Input {...getFieldProps('otherName', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Other Phone：">
                    <Input {...getFieldProps('otherPhone', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Other Relation：">
                    <Input {...getFieldProps('otherRelation', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
			  <h2>Live Message</h2>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Live Address：">
                    <Input {...getFieldProps('liveAddress', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Live City：">
                    <Input {...getFieldProps('liveCity', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Rent Year：">
                    <Input {...getFieldProps('rentYear', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
                <Col span="8">
                  <FormItem {...formItemLayout} label="Live Time(YEAR,MONTH)：">
                    <Input {...getFieldProps('liveTime', { initialValue: '' }) } disabled={true} />
                  </FormItem>
                </Col>
              </Row>
        {/*<h2>认证状态</h2>
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
              </Row>*/}
            </div>
      </Form>
    );
  }
});
Tab1 = createForm()(Tab1);
export default Tab1;