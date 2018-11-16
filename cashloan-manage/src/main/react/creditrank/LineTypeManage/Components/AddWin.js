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
    Table,
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
            record: "",
            tableNameList: "",
            tableCommentList: "",
            borrowIdList: "",
            borrowIdAddList:"",
            rankIdList: "",
            data: "",
            dataArray: [],
            tableName: "",
            canEdit: true,
            dataRecord: "",
            dataEdit:"",
            dataName: [],
            values: "",
            selectedRowKeys: [],
            defaultTitle:'',
            borrowIdDefList:[],
            rankArray:[],
            rankmateArray:[],
            checkDefault:[],
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
        this.setState({
            tableName: "",
            canEdit: true,
            dataRecord: "",
            dataEdit:"",
            dataName: [],
            defaultTitle:"",
            selectedRowKeys: [],
            borrowIdDefList:[],
            checkDefault:[],
            rankArray:[],
            rankmateArray:[],
        })
    },
    componentWillReceiveProps(nextProps, nextState) {
        this.setState({
            rankArray:nextProps.rankArray,
            rankmateArray:nextProps.rankmateArray,
        })
        if (nextProps.title == "编辑" || nextProps.title == "查看") {
            var dataArray = [];
            this.setState({
                dataRecord: nextProps.dataRecord,
                dataName: nextProps.dataName,
                dataEdit:nextProps.dataEdit,
                dataArray: dataArray,
                defaultTitle:nextProps.defaultTitle,
                selectedRowKeys:nextProps.checkDefault,
                
                borrowIdAddList:nextProps.borrowIdAddList,

            })
        }

    },
    handleOk() {
        var me = this;
        var validation = this.props.form.validateFields;
        var borrowTypeId = this.state.selectedRowKeys.join(",");
        this.props.form.validateFields((errors, values) => {
            var params = values;
            this.state.rankmateArray.forEach(item=>{
                  if(item.id==values.creditTypeId){
                      params.name = item.itemValue
                  }
                })
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            if (this.props.title == "新增额度类型") {
                var res = [];
                var url = '/modules/manage/cr/creditType/saveCreditType.htm';
                params.borrowTypeId=borrowTypeId;
            }
            if (this.props.title == "编辑") {
                var paramsData = this.props.form.getFieldsValue();
                var url = '/modules/manage/cr/creditType/updateCreditType.htm';
                params.borrowTypeId=borrowTypeId;
                
            }
            Utils.ajaxData({
                url: url,
                data: params,
                callback: (result) => {
                    if (result.code == "200") {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                    } else {
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
    componentDidMount() {
        Utils.ajaxData({
            url: '/modules/manage/cr/card/findAll.htm',
            callback: (result) => {
                let tableCommentList = [];
                result.data.map((item) => {
                    tableCommentList.push(<Option key={item.id} value={item.id}>{item.cardName}</Option>)
                    return tableCommentList
                })
                this.setState({
                    tableCommentList: tableCommentList,
                    data: result.data
                });
            }
        });
       
        Utils.ajaxData({
            url: '/modules/manage/cr/rank/findAll.htm',
            callback: (result) => {
                let rankIdList = [];
                result.data.map((item) => {
                    rankIdList.push(<Option key={item.id} value={item.id} >{item.rankName}</Option>)
                    return rankIdList
                })
                this.setState({
                    rankIdList: rankIdList
                });
            }
        });
        Utils.ajaxData({
            url: '/modules/manage/cr/creditType/editBorrowType.htm',
            data: {
                id: this.state.dataRecord.id
            },
            callback: (result) => {
                let borrowIdList = [];
                var defaultTitle = this.state.defaultTitle;
                result.data.map((item) => {
                    borrowIdList.push({ id: Number(item.borrowTypeId), "borrowId": item.borrowTypeName,title:defaultTitle});
                    return borrowIdList
                })
                this.setState({
                    borrowIdList: borrowIdList
                });
            }
        });
    },

    rowKey(record) {
        return record.id;
    },

    onRowClick(record) {
        var id = record.id;
        var selectedRowKeys = this.state.selectedRowKeys;
        if (selectedRowKeys.indexOf(id) < 0) {
            selectedRowKeys.push(id);
        } else {
            selectedRowKeys.remove(id)
        }
        this.setState({
            selectedRowKeys: selectedRowKeys
        });
    },
    //选择
    onSelectAll(selected, selectedRowKeys, selectedRows, changeRows) {
        if (selected) {
            this.setState({
                selectedRowKeys
            })
        } else {
            this.setState({
                selectedRowKeys: []
            })
        }
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
                提 交
            </Button>
        ];
        var modalBtnsLook = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>
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
                span: 8
            },
            wrapperCol: {
                span: 16
            },
        };
        let data = this.state.data;
        const tProps = {
            data,
        }

        var me = this;
        const {
            loading,
            selectedRowKeys
        } = this.state;
        const rowSelection = {
            type: props.title=="查看"?'':'checkbox',
            selectedRowKeys,
            onSelectAll: this.onSelectAll,
        };
        const hasSelected = selectedRowKeys.length > 0;

        var columns = [{
            title: "借款类型",
            dataIndex: "borrowId"
        }];
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="800" footer={props.title =="查看"?modalBtnsLook:modalBtns} maskClosable={false} >
                <div style={{ position: "relative" }}>
                    <Form horizontal form={this.props.form} style={{ marginTop: "20px" }}>
                        <Input  {...getFieldProps('id') } type="hidden" />
                        <Row>
                            <Col span="18">
                                <FormItem  {...formItemLayout} label="额度类型名称:">
                                    {this.props.title == "新增额度类型" ? (
                                        <div>
                                            <Select disabled={!props.canEdit} onSelect={this.select} {...getFieldProps('creditTypeId') } >
                                                {this.state.rankArray}
                                            </Select>
                                        </div>
                                    ) : (
                                            <div>
                                                <Select disabled={!props.canEdit} onSelect={this.select} {...getFieldProps('creditTypeId') } >
                                                    {this.state.rankArray}
                                                </Select>
                                        </div>
                                        )}
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="18">
                                <FormItem  {...formItemLayout} label="关联评分卡:">
                                    <Select disabled={!props.canEdit} onSelect={this.select} {...getFieldProps('cardId') } >
                                        {this.state.tableCommentList}
                                    </Select>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="18">
                                <FormItem  {...formItemLayout} label="关联额度等级:">
                                    <Select disabled={!props.canEdit} onSelect={this.select} {...getFieldProps('rankId') } >
                                        {this.state.rankIdList}
                                    </Select>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="18">
                                <FormItem  {...formItemLayout} label="关联借款类型:">
                                    <Table columns={columns} rowKey={this.rowKey} size="middle"
                                        rowSelection={rowSelection}
                                        onRowClick={this.onRowClick}
                                        dataSource={props.title =="新增额度类型"?this.props.borrowIdAddList:this.state.dataEdit}
                                        loading={this.state.loading}
                                        onChange={this.handleTableChange} />
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
