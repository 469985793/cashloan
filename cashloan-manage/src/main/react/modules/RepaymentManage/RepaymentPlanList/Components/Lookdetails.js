import React from 'react';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  Row,
  Col,
  Select,
  Checkbox,
  Radio,
  message,
  DatePicker,
  Switch,
  Table
} from 'antd';
import './button.css';
const CheckboxGroup = Checkbox.Group
const createForm = Form.create;
const FormItem = Form.Item;
var confirm = Modal.confirm;
const Option = Select.Option;
const RadioGroup = Radio.Group;
const objectAssign = require('object-assign');
var Lookdetails = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      value: 1,
      startValue: null,
      data: [],
      pagination: {
        pageSize: 3,
        current: 1
      },
      record: "",
      loading: false
    };
  },
  handleCancel() {
    this.state.value = 1;
    this.state.startValue = null;
    this.props.form.resetFields();
    this.props.hideModal();
  },
  componentWillReceiveProps(nextProps) {

    this.fetch(nextProps.params);
    this.setState({
      record: nextProps.record
    })
    // console.log(nextProps);

  },
  componentDidMount() {
    // console.log(this.props);
    // console.log(nextProps)
    this.fetch();
    []
  },
  fetch(params = {}) {
    this.setState({
      loading: true
    });

    if (!params.pageSize) {
      var params = {};
      params = {
        pageSize: 4,
        current: 1,
        id: this.props.record.id
      }

    }
    params.id = this.props.record.id;
    // console.log(params);
    Utils.ajaxData({
      url: '/modules/manage/borrow/repay/RepayFlow.htm',
      method: "post",
      data: params,
      callback: (result) => {
        console.log(result);
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (result.data) {
          // for (var i = 0; i < result.data.length; i++) {
          //   if (result.data[i].overdueFee) {
          //     result.data[i].overdueFee = result.data[i].overdueFee / 100;
          //   } else {
          //     result.data[i].overdueFee == "";
          //   }
          //   if (result.data[i].balance) {
          //     result.data[i].balance = result.data[i].balance / 100;
          //   } else {
          //     result.data[i].balance = "";
          //   }
          //   if (result.data[i].repayAmount) {
          //     result.data[i].repayAmount = result.data[i].repayAmount / 100
          //   } else {
          //     result.data[i].repayAmount = "";
          //   }
          //   if (result.data[i].repayTotal) {
          //     result.data[i].repayTotal = result.data[i].repayTotal / 100
          //   } else {
          //     result.data[i].repayTotal = "";
          //   }
          //   if (result.data[i].actualRepayment) {
          //     result.data[i].actualRepayment = result.data[i].actualRepayment / 100
          //   } else {
          //     result.data[i].actualRepayment = "";
          //   }
          //   if (result.data[i].actualBalance) {
          //     result.data[i].actualBalance = result.data[i].actualBalance / 100
          //   } else {
          //     result.data[i].actualBalance = "";
          //   }
          //   if (result.data[i].actualbackAmt) {
          //     result.data[i].actualbackAmt = result.data[i].actualbackAmt / 100
          //   } else {
          //     result.data[i].actualbackAmt = "";
          //   }
          // }
        }
        if (!pagination.current) {
          pagination.current = 1
        }
        ;
        // console.log(pagination);
        this.setState({
          loading: false,
          data: result.data,
          pagination
        });
      }
    });
  },
  //选择
  onSelectChange(selectedRowKeys) {
    // console.log(selectedRowKeys);
    this.setState({
      selectedRowKeys
    });
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    // console.log(pager);
    // console.log(pagination);
    pager.current = pagination.current;
    // pager.total = pagination.total;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },

  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize
    });
    this.fetch(params);
  },

  disabledStartDate(startValue) {
    var today = new Date();
    var loanDay = new Date(this.props.record.borrowTime);
    return startValue.getTime() > today.getTime() || startValue.getTime() < loanDay.getTime();
  },
  handleOk() {
    var me = this;
    var re = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var re1 = /^(\d|[1-9]\d+)(\.\d+)?$/;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      if (values.amount > this.props.record.repayAmount) {
        Modal.error({
          title: "Too much repayment amount",//还款金额过多
        });
        return;
      }
      if (values.amount && !re1.test(values.amount)) {
        Modal.error({
          title: "The repayment amount must be a number greater than or equal to zero",//还款金额必须是大于等于零的数
        });
        return;
      }
      if (values.penaltyAmout1 && !re1.test(values.penaltyAmout1)) {
        Modal.error({
          title: "Overdue fines must be greater than or equal to zero",//逾期罚金必须是大于等于零的数
        });
        return;
      }
      if (values.penaltyAmout1 > this.props.record.penaltyAmout) {
        Modal.error({
          title: "Overdue fines",//逾期罚金过多
        });
        return;
      }
      if (re.test(values.repayAccount) && values.repayWay != '30') {
        Modal.error({
          title: "The account number does not match the method. Please check",//账号与方式不符,请核对
        });
        return;
      }
      var tips = "Are you sure to submit";//您是否确定提交
      if (this.props.title == "Manual Transfer") {//手动划款
        // console.log("commit:" + values);
        if (values.repayMoney <= 1000) {
          Modal.error({
            title: "Batch repayment does not take manual payment",//分批还款不走手动划款
          });
          return;
        }
        confirm({
          title: tips,
          onOk: function () {
            Utils.ajaxData({
              url: "/arsenal/repayment.htm?borrowIdIn=" + values.borrowId + "&amountIn=" + values.repayMoney,
            });
            me.handleCancel();
            let resType = 'success';
            Modal[resType]({
              title: "The operation is successful, please go to the loan progress inquiry result!",//操作成功,请到借款进度查询结果!
            });
          },
          onCancel: function () { }
        })
      } else {
        confirm({
          title: tips,
          onOk: function () {

            Utils.ajaxData({
              url: "/modules/manage/borrow/repay/confirmRepay.htm",
              data: {
                id: values.id,
                repayTime: DateFormat.formatDate(values.repayTimes),
                repayAccount: values.repayAccount,
                repayWay: values.repayWay,
                actualbackAmt: values.actualbackAmt,
                serialNumber: values.serialNumber,
                amount: me.state.value == 1 ? me.props.record.balance : values.amount,
                penaltyAmout: me.state.value == 1 ? me.props.record.overdueFee : values.penaltyAmout1,
                state: me.state.value == 1 ? values.status == 5 ? 6 : 21 : 22,
              },
              callback: (result) => {
                if (result.code == 200) {
                  me.handleCancel();
                };
                let resType = result.code == 200 ? 'success' : 'warning';
                Modal[resType]({
                  title: result.msg,
                });
              }
            });
          },
          onCancel: function () { }
        })
      }
    })
  },
  onChange1(e) {
    this.setState({
      value: e.target.value,
    });
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    // console.log('props',props);
    var columns = [{
      title: 'Way Code',//支付渠道
      dataIndex: 'wayCode',
      width: 25 + '%',
    }, {
      title: 'Created Time',//日期
      dataIndex: "createdTime",
      width: 25 + '%',

    }, {
      title: 'Amount',//订单号
      dataIndex: 'amount',
      width: 25 + '%',
    }, {
      title: 'Status',//状态
      dataIndex: 'status',
      width: 25 + '%',
      render:(text, record)=>{
        if (record.status == '1') {
          return '发起支付'
        }
        else if (record.status == '2') {
          return '受理中'
        }
        else if (record.status == '3') {
          return '支付成功'
        }
        else if (record.status == '4') {
          return '支付失败'
        }
      }


    }]
    const formItemLayout = {
      labelCol: {
        span: 0
      },
      wrapperCol: {
        span: 24
      },
    };
    var modalBtns = props.title == "View" ? [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>Back{/*返 回*/}</Button>
    ]
    :
    [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>Back{/*返 回*/}</Button>,
      <Button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk} >
        {/*提 交*/}Submit
            </Button>
    ]
    ;
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="800" footer={modalBtns} maskClosable={false} >{/*手动划款*/}
        {props.title == "Manual Transfer" ? (
          <Form horizontal form={this.props.form}>
            <Input  {...getFieldProps('id', { initialValue: '' })} type="hidden" />
            <Input  {...getFieldProps('borrowId', { initialValue: '' })} type="hidden" />
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Amount Of Payment:">{/*划款金额*/}
                  <Input type="text" placeholder="Please enter the amount of the transfer"{...getFieldProps('repayMoney', { rules: [{ required: true, message: "Too much input or not lost", max: 20 }] })} /> {/*请输入划款金额     输入过多或者未输*/}
                </FormItem>
              </Col>
            </Row></Form>)
          :
          (<Form horizontal form={this.props.form}>
            <Input  {...getFieldProps('id', { initialValue: '' })} type="hidden" />
            <Input  {...getFieldProps('status', { initialValue: '' })} type="hidden" />
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Repayment Option:">{/*还款选择*/}
                  <RadioGroup onChange={this.onChange1} value={this.state.value}>
                    <Radio key="a" value={1}>Normal repayment{/*正常还款*/}</Radio>
                    <Radio key="b" value={2}>Amount reduction{/*金额减免*/}</Radio>
                  </RadioGroup>
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Repayment Account:">{/*还款账号*/}
                  <Input type="text" placeholder="Please enter your bank card number or Alipay account number" {...getFieldProps('repayAccount', { rules: [{ required: true, message: "Too much input or not lost", max: 20 }] })} />{/*请输入银行卡号或者支付宝账号   输入过多或者未输*/}
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Repayment method:">{/*还款方式*/}
                  <Select placeholder="Please Choose" {...getFieldProps('repayWay', { rules: [{ required: true, message: '必填', type: 'string' }] })} >{/*请选择*/}
                    <Option value="20">Bank card transfer</Option>{/*银行卡转账*/}
                  </Select>
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Actual amount of repayment:">{/*实际还款金额*/}
                  <Input type="text" placeholder="Please enter the actual amount of repayment" {...getFieldProps('actualbackAmt', { rules: [{ required: true, message: "Too much input or not lost", max: 50 }] })} />{/*请输入实际还款金额    输入过多或者未输*/}
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Serial Number:">{/*流水号*/}
                  <Input type="text" placeholder="Please enter the serial number" {...getFieldProps('serialNumber', { rules: [{ required: true, message: "Too much input or not lost", max: 50 }] })} />{/*请输入流水号    输入过多或者未输*/}
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Repayment Time:">{/*还款时间*/}
                  <DatePicker format="yyyy-MM-dd HH:mm:ss" disabledDate={this.disabledStartDate} value={this.state.startValue} {...getFieldProps('repayTimes', { rules: [{ required: true, message: 'Required', type: 'date' }], onChange: (value) => { this.setState({ startValue: value }) } })} />{/*必填*/}
                </FormItem>
              </Col>
            </Row>
            {state.value == 1 ? "" :
              <div>
                <Row>
                  <Col span="24">
                    <FormItem {...formItemLayout} label="Repayment Amount:">{/*还款金额*/}
                      <Input type="text" placeholder="Please enter the repayment amount" {...getFieldProps('amount', { rules: [{ required: true, message: 'Required' }] })} />{/*请输入还款金额   必填*/}
                    </FormItem>
                  </Col>
                </Row>
                {this.state.startValue && DateFormat.formatDate(this.state.startValue).substring(0, 10) > this.props.record.repayTime.substring(0, 10) ? <Row>
                  <Col span="24">
                    <FormItem {...formItemLayout} label="Overdue fine:">{/*逾期罚金*/}
                      <Input type="text" placeholder="Please enter an overdue penalty" {...getFieldProps('penaltyAmout1')} />{/*请输入逾期罚金*/}
                    </FormItem>
                  </Col>
                </Row> : ''}
              </div>
            }
            <Row>
              <Col span="24">
                <FormItem {...formItemLayout} label="Repayment Record:">{/*还款记录流水*/}
                  <Table size="small"
                    rowKey={this.rowKey}
                    columns={columns}
                    dataSource={this.state.data}
                    // rowClassName={this.rowClassName}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                  />
                </FormItem>
              </Col>
            </Row>
          </Form>
          )
        }

      </Modal>
    );
  }
});
Lookdetails = createForm()(Lookdetails);
export default Lookdetails;