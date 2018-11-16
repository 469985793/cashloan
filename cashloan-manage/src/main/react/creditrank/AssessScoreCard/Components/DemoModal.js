import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Select,
  Row,
  Col,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var DemoModal = React.createClass({
  getInitialState() {
    return {
     
    };
  },
  handleCancel() {
    this.props.hideModal();
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    getFieldProps('keys', {
      initialValue: [],
    });
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
    ];
    const formItemLayout = {
      labelCol: {
        span: 6
      },
      wrapperCol: {
        span: 22
      },
    }; 
    
    return (
      <Modal maskClosable={false} title={props.title} visible={props.visible} onCancel={this.handleCancel} width={900}  footer={modalBtns} >
          <Form  horizontal  form={this.props.form}>
              <div>
                  <Row>
                      <Col span="3" className='col_h'></Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Select style={{ width: '100%' }}  disabled={true} {...getFieldProps('a', { initialValue: '<and<' }) } >
                                  <Option  value='<and<'>{'<and<'}</Option>
                              </Select>
                          </FormItem>
                      </Col>

                      <Col span="5" className='col_h'>
                          <FormItem  {...formItemLayout}  >
                              <Input  {...getFieldProps('b', { initialValue: '20,30' }) }  disabled={true}/>
                          </FormItem>
                      </Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Input  {...getFieldProps('c', { initialValue: '7' }) } disabled={true}/>
                          </FormItem>
                      </Col>
                      <span>因子参数分值用逗号分割开</span>
                  </Row>
                  <Row>
                      <Col span="3" className='col_h'></Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Select style={{ width: '100%' }}  disabled={true} {...getFieldProps('a', { initialValue: '<=and<' }) } >
                                  <Option  value='<=and<'>{'<=and<'}</Option>
                              </Select>
                          </FormItem>
                      </Col>

                      <Col span="5" className='col_h'>
                          <FormItem  {...formItemLayout}  >
                              <Input  {...getFieldProps('b', { initialValue: '1000,2000' }) }  disabled={true}/>
                          </FormItem>
                      </Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Input  {...getFieldProps('c', { initialValue: '77' }) } disabled={true}/>
                          </FormItem>
                      </Col>
                  </Row>
                  <Row>
                      <Col span="3" className='col_h'></Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Select style={{ width: '100%' }}  disabled={true} {...getFieldProps('a', { initialValue: '<=and<=' }) } >
                                  <Option  value='<=and<='>{'<=and<='}</Option>
                              </Select>
                          </FormItem>
                      </Col>
                      <Col span="5" className='col_h'>
                          <FormItem  {...formItemLayout}  >
                              <Input  {...getFieldProps('b', { initialValue: '3,7' }) }  disabled={true}/>
                          </FormItem>
                      </Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Input  {...getFieldProps('c', { initialValue: '7' }) } disabled={true}/>
                          </FormItem>
                      </Col>
                  </Row>
                  <Row>
                      <Col span="3" className='col_h'></Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Select style={{ width: '100%' }}  disabled={true} {...getFieldProps('a', { initialValue: '<and<=' }) } >
                                  <Option  value='<and<='>{'<and<='}</Option>
                              </Select>
                          </FormItem>
                      </Col>

                      <Col span="5" className='col_h'>
                          <FormItem  {...formItemLayout}  >
                              <Input  {...getFieldProps('b', { initialValue: '10,100' }) }  disabled={true}/>
                          </FormItem>
                      </Col>
                      <Col span="5" className='col_h'>
                          <FormItem {...formItemLayout}>
                              <Input  {...getFieldProps('c', { initialValue: '7' }) } disabled={true}/>
                          </FormItem>
                      </Col>
                  </Row>
              </div>
          </Form>
        </Modal>
    );
  }
});
DemoModal = createForm()(DemoModal);
export default DemoModal;