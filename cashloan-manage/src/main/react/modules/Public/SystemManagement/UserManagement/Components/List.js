var React = require('react');
import ReactDOM from 'react-dom';
import { Table,Icon,Popover,Button,Modal } from 'antd';
var confirm = Modal.confirm;
import reqwest from 'reqwest';  
import UserWin from './UserWin';  
export default React.createClass({ 
    getInitialState() {
      return {
        selectedRowKeys: [],  // 这里配置默认勾选列
        loading: false,
        data: [],
        pagination: {}, 
        canEdit:true,
        visible: false,
      };
    },
    componentWillReceiveProps(nextProps, nextState) {
      //console.log(nextProps.params);  
      this.fetch(nextProps.params);
    },
    handleTableChange(pagination, filters, sorter) {
      const pager = this.state.pagination;
      pager.current = pagination.current;
      this.setState({
        pagination: pager,
      });
      this.fetch({
        pageSize: pagination.pageSize,
        currentPage: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    },
    fetch(params = {page:1,start:0,limit:10,pageSize:10,currentPage:1}) {
      //console.log('请求参数：', params);
      this.setState({ loading: true });
      reqwest({
        url: "/modules/manage/system/user/list.htm", 
        method: 'get',
        data: params,
        type: 'json',
        success: (result) => {
          const pagination = this.state.pagination;
          pagination.total = result.total;
          this.setState({
            loading: false,
            data: result.datas,
            pagination,
          });
        }
      });
    },
    rowKey(record) {
      return record.id;
    },
    hideAddModal() {
      this.setState({
        visible: false
      });
      this.fetch(); 
    },
    showAddModal(title,record,canEdit) {
      this.setState({ 
        canEdit: canEdit, 
        visible: true,
        title: title,
        record:record
      });
      
      if(title!='新增'){
         var newRecord = record;
         newRecord.roleId = [Number(record.roleId)]; 
         this.refs.UserWin.setFieldsValue(newRecord);
      }
      else {
        this.refs.UserWin.resetFields();
      }
    },
    handleClick(title) {
      var me = this;
      var selectRecord = this.state.selectData.record;
      var status = "";
      var msg = "";
      var tips = "";
      if (title == "锁定") {
        status = 'lock';
        msg = '用户锁定成功';

        tips = '您是否锁定此客户'
      } else if (title == "解锁") {
        status = 'unlock';
        msg = '用户解锁成功';
        tips = '您是否解锁此客户'
      } else if (title == "密码重置") {
        status = 'editpassword';
        msg = '用户密码重置成功';
        tips = '是否将用户密码改成【123456】？'
      }
      confirm({
        title: tips,
        onOk: function() {

          reqwest({
            url: '/modules/manage/system/user/update.htm',
            method: 'post',
            data: {
              id: selectRecord.id,
              status: status
            },
            type: 'json',
            success: (result) => {
              //console.log(result);
              if (result) {
                Modal.success({
                  title: msg
                });
                Actions.initStore();
              } else {
                Modal.error({
                  title: result.msg
                });
                me.setState({
                  loading: false
                });
              }
            }
          });
        },
        onCancel: function() {}
      });
    },
    onSelectChange(selectedRowKeys) {
      //console.log('selectedRowKeys changed: ', selectedRowKeys);
      this.setState({ selectedRowKeys });
    },
    componentDidMount() {
      this.fetch();
    },
    render() {
      var me = this;
      const { loading, selectedRowKeys } = this.state;
      const rowSelection = {
       type:"radio", 
       selectedRowKeys,
       onChange: this.onSelectChange,
      };
      const hasSelected = selectedRowKeys.length > 0;
      var columns = [{
            title: '主键',
            dataIndex: 'id' 
            },{
              title: '真实姓名',
              dataIndex: 'name' 
            },
            {
              title: '用户名称',
              dataIndex: 'userName' 
            },{
              title: '工号',
              dataIndex: "number"
            },{
              title: '所属部门',
              dataIndex: "officeName"
            },{
              title: '用户角色',
              dataIndex: "roleName",
              width:200,
              render:function(value){
                return <div style={{width: '200px',textOverflow: 'ellipsis',overflow: 'hidden',whiteSpace: 'nowrap',zoom: 1}}>{value}</div>
              }
            },
            {
              title: '状态',
              dataIndex: 'status',
              render: function(value) {
                if(value==1)
                  return "锁定";
                else return "正常";
              } 
            },
            {
              title: '操作',
              key: 'operation',
              render(text, record) {
                var content = <div style={{textAlign: "center"}}><button className="ant-btn" style={{marginRight: 16}}  onClick={me.showAddModal.bind(me,'修改',record,true)}>修改 </button><button className="ant-btn"  onClick={me.showAddModal.bind(me,'查看',record,false)}>查看 </button></div> ;
                return ( 
                    <Popover overlay={content}  trigger="hover" >
                       <Icon type="edit" />
                    </Popover> 
                );
              }
            }];
      var state = this.state;
      return (
        <div className="block-panel">
          <div className="actionBtns" style={{ marginBottom: 16 }}>
            <button className="ant-btn"  onClick={this.showAddModal.bind(this,'新增',null,true)}> 
              新增
            </button>
            <button className="ant-btn" disabled={!hasSelected}   onClick={this.handleClick.bind(this,'锁定',false)}> 
              锁定
            </button> 
            <button className="ant-btn" disabled={!hasSelected}   onClick={this.handleClick.bind(this,'解锁',false)}> 
              解锁
            </button> 
            <button className="ant-btn" disabled={!hasSelected}   onClick={this.handleClick.bind(this,'密码重置',false)}> 
              密码重置
            </button>
          </div>
          <Table columns={columns} rowKey={this.rowKey}  params ={this.props.params}  rowSelection={rowSelection}
            dataSource={this.state.data}
            pagination={this.state.pagination}
            loading={this.state.loading}
            onChange={this.handleTableChange}  /> 
          <UserWin ref='UserWin' visible={state.visible} title={state.title} hideAddModal={me.hideAddModal} record={state.record} canEdit={state.canEdit}/>
        </div>
      ) 
    }
});
 
