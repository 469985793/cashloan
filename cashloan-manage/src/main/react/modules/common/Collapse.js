import React from 'react';
import antd from 'antd';

var ClassNameMixin = require('../utils/ClassNameMixin');
const Collapse = React.createClass({
  mixins: [
    ClassNameMixin
  ],
  getDefalutProps(){
    return {
      title: '',
      children: null
    }
  },
  getInitialState() {
    return {
      fold: this.props.hide ? true : false
    }
  },

  toggleMenu(){
    var me = this;
    this.setState({
      fold: !me.state.fold
    });
    
  },
  render() {
    var fold = this.state.fold;
    var isSubCollapse = this.props.isSubCollapse;
    return (
      <div className={`collapseWrap ${fold?'collapse':''} ${isSubCollapse?'subCollapse':''}`}>
        <div className="collapse-header" onClick={this.toggleMenu}>
          <div className="title">
              <span className="collapse-header-title">
                <i className="leftArrow"></i><div>{this.props.title}</div><i className="rightArrow"></i>
              </span>
          </div>
          <i className={`iconfont ${ fold ? 'icon-iconfont39' : 'icon-iconfont40'} collapse-icon`}></i>
        </div>
        <div className="collapse-body">{this.props.children}</div>
      </div>
    )
  }
});

export default Collapse; 