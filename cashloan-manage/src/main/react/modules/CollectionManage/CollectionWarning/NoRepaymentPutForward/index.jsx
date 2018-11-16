/*催收预警-未还款已出催*/
import React from 'react';
import List from './Components/List';
import SeachForm from './Components/SeachForm';
import './adjustCredit.css'
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

