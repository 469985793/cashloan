/*黑名单客户管理*/
import React from 'react';
var List = require('./Components/List');
var SeachForm = require('./Components/SeachForm');
var ActionBtns = require('./Components/ActionBtns');

export default React.createClass({
  getSearchData() {
    var SeachData = this.refs.SeachForm.state.formData;
    window.location.href = "/modules/common/action/BlackCustomerAction/exportBlackCustomer.htm?data=" + JSON.stringify(SeachData);
  },
  render() {
    return <div>
      <SeachForm ref='SeachForm'/>
      <ActionBtns getSearchData={this.getSearchData}/>
      <List/>
    </div>}
}
  );