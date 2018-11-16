/*字典管理*/
import React from 'react';
import DicList from './Components/DicList';
import SeachForm from './Components/SeachForm';

export default React.createClass({

  getInitialState() {
    return {
      params: {
              pageSize: 5,
              current: 1
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
      <div className="block-panel">
        <SeachForm passParams={this.passParams}/>
      </div>
      <DicList params={this.state.params}/>
    </div>
  }
});

