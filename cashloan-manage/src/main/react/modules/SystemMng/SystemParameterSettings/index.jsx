/*系统参数设置*/
import React from 'react';
import List from './Components/List';
import SeachForm from './Components/SeachForm';

export default React.createClass({

  getInitialState() {
    return {
      params: {
              pageSize: 10,
              current: 1
            }
    }
  },
  passParams(params) {
    this.setState({
      params: params
    });
  },
  btnParamsFun(btnParams){
    this.setState({
      btnParams: btnParams
    });
  },
  render() {
    return <div>
      <div className="block-panel">
        <SeachForm passParams={this.passParams} btnParamsFun={this.btnParamsFun}/>
      </div>
      <List params={this.state.params} btnParams={this.state.btnParams} btnParamsFun={this.btnParamsFun}/>
    </div>
  }
});


