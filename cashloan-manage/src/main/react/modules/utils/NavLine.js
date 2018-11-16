import React from 'react';
import antd from 'antd';

const NavLine = React.createClass({
  getInitialState() {
    return {
      activeKey: this.props.activeItem ? this.props.activeItem : "#1"
    }
  },
  componentWillReceiveProps(nextProps) {
    this.setState({
      activeKey: nextProps.activeItem
    });
  },
  handleClickItem(key){
    var me = this;
    this.setState({
      activeKey: key
    });
  },
  render() {
    var items = [];
    var props = this.props;
    var height = props.height ? props.height - 35 : "";
    var activeKey = this.state.activeKey;
    var navLineData = props.navLineData;
    if (navLineData) {
      let item;
      for(let key in navLineData) {
        item = (<li className={`ant-timeline-item navLine ${activeKey==navLineData[key]?"activeNavLineItem":""}`}
                    onClick={this.handleClickItem.bind(this,navLineData[key])} key={navLineData[key]}>

          <div className="ant-timeline-item-head ant-timeline-item-head-blue"></div>
          <div className="ant-timeline-item-content"><a href={navLineData[key]}>{key}</a></div>
        </li>);
        items.push(item);
      }
    }
    
    return (
      <ul className="ant-timeline" style={{height:height}}>
        <li className="ant-timeline-item navLine" style={{"paddingBottom":"30px"}}>
          <div className="ant-timeline-item-head ant-timeline-item-head-blue  first-dot"></div>
          <div className="ant-timeline-item-content"></div>
        </li>
        {items}
        <li className="ant-timeline-item navLine" style={{"position":"absolute","bottom":"-3px"}}>
          <div className="ant-timeline-item-head ant-timeline-item-head-blue  last-dot"></div>
          <div className="ant-timeline-item-content"></div>
        </li>
      </ul>
    )
  }
});

export default NavLine; 