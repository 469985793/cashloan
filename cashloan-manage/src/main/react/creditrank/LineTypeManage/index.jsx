/*额度类型管理*/
import React from 'react';
import List from './Components/List';

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
      <List params={this.state.params}/>
    </div>
  }
});

