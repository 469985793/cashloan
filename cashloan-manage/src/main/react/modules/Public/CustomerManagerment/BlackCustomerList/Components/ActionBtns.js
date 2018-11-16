var React = require('react');
import antd from 'antd';
var Modal = antd.Modal;
var confirm = antd.Modal.confirm;
var Reflux = require('reflux');
import Import from './Import';
import NewCustomer from './NewCustomer';
var reqwest = require('reqwest');
var Actions = require('../actions/Actions');
var SelectRecordStore = require('../stores/SelectRecordStore');

export default React.createClass({
  mixins: [
    Reflux.connect(SelectRecordStore, 'selectData')
  ],
  getInitialState() {
    return {
      selectData: {
        isSelectRecord: false,
        record: null
      },
      visible: false
    };
  },
  hideAddModal() {
    this.setState({
      visible: false
    });
  },
  showAddModal(title, canEdit){
    Actions.setFormData(this.state.selectData.record, title);
    this.setState({
      visible: true,
      title: title,
      canEdit: canEdit
    });
  },
  exports(){
    this.props.getSearchData();
  },
  delete(){
    var me = this,
      selectRecord = this.state.selectData.record;
    //console.log(this.state.selectData);
    confirm({
      title: '您是否确认要移除黑名单',
      onOk: function() {
        reqwest({
          url: '/modules/common/action/BlackCustomerAction/updatBlack.htm',
          method: 'post',
          data: {
            certType: selectRecord.certType,
            certNumber: selectRecord.certNumber,
            id: selectRecord.id,
          },
          type: 'json',
          success: (result) => {
            if (result.code == 200) {
              Modal.success({
                title: result.msg
              });
              Actions.initStore();
            }
            else if (result.code == 500) {
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
      onCancel: function() {
      }
    });
  },

  render() {
    var Buttons;
    if (this.state.selectData.isSelectRecord) {
      Buttons = (<span>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'修改',true)}>
                  <i className="anticon anticon-edit"></i>修改
                </button>
                <button className="ant-btn" onClick={this.showAddModal.bind(this,'信息查看',false)}>
                  <i className="anticon anticon-search"></i>
                  信息查看
                </button>
                <button className="ant-btn" onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  移除黑名单
                </button>  
                </span>
      )
    }
    else {
      Buttons = (<span>
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'修改',true)}>
                  <i className="anticon anticon-edit"></i>
                  修改
                </button>
                <button className="ant-btn" disabled onClick={this.showAddModal.bind(this,'信息查看',false)}>
                  <i className="anticon anticon-search"></i>
                  信息查看
                </button> 
                <button className="ant-btn" disabled onClick={this.delete}>
                  <i className="anticon anticon-delete"></i>
                  移除黑名单
                </button>
                </span>
      )
    }
    var NewMode;
    if (this.state.title == "导入黑名单") {
      NewMode = (<Import visible={this.state.visible} title={this.state.title} hideAddModal={this.hideAddModal}
                         canEdit={this.state.canEdit}/>)
    } else {
      NewMode = (<NewCustomer visible={this.state.visible} title={this.state.title} hideAddModal={this.hideAddModal}
                              canEdit={this.state.canEdit}/>)
    }
    return (
      <div className="actionBtns">
        <button className="ant-btn" onClick={this.showAddModal.bind(this,'新增用户',true)}>
          <i className="anticon anticon-plus"></i>
          新增
        </button>
        {Buttons}
        <button className="ant-btn" onClick={this.showAddModal.bind(this,'导入黑名单',true)}>
          <i className="anticon anticon-upload"></i>
          导入黑名单
        </button>
        <button className="ant-btn" onClick={this.exports}>
          <i className="anticon anticon-download-line"></i>
          导出黑名单
        </button>
        {NewMode}
      </div>
    )
  }
});
 
