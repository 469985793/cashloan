/*规则类型绑定*/
import React from 'react';
import List from './Components/List';
// import SeachForm from "./Components/SeachForm";
export default React.createClass({

  getInitialState() {
   return {
      params: {
        pageSize: 10,
        current: 1,
      }
    }
  },
  passParams(params) {
    this.setState({
      params: params
    });
  },
  render() {
    return <div>
      {/**
        <div className="block-panel">
          <SeachForm passParams={this.passParams}/>
        </div>
      */}
      <List params={this.state.params}/>
    </div>
  }
});

