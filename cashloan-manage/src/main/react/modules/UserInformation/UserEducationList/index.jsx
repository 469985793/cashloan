/*客户管理-用户教育信息列表*/
import React from 'react';
import List from './Components/List';
import SeachForm from './Components/SeachForm';
export default React.createClass({

  getInitialState() {
    return {
      params: {}
    }
  },
  passParams(params) {
    this.setState({
      params: params
    });
  },
  render() {
    return <div>
      <div className="block-panel">
        <SeachForm passParams={this.passParams}/>
      </div>
      <List params={this.state.params}/>
    </div>
  }
});

