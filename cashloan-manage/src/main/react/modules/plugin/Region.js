/**
 * 省市区级联
 */ 
var data = require('./chinaRegion')
import React from 'react';
import antd from 'antd';
var Select = antd.Select;
var Option = Select.Option;   
 
var Region = React.createClass({
  getDefaultProps() {
    return { 
      //默认显示北京(省)
      selectedProvince: '110000',
      //默认显示北京(市)
      selectedCity: '110100',
      //默认显示(区)
      selectedArea: '110101', 
    }
  }, 
  /**
   * 初始化状态
   */
  getInitialState() {
    return { 
      //省
      province: [],
      //市
      city: [],
      //地区
      area: [],
      //选中的省
      selectedProvince: this.props.selectedProvince,
      //选中的市
      selectedCity: this.props.selectedCity,
      //选中的地区
      selectedArea: this.props.selectedArea, 
    }
  },
  
   /**
   * 改变新属性
   */
  componentWillReceiveProps(nextProps) {
    var selectedArea = nextProps.value;
    if(selectedArea && selectedArea!=this.state.selectedArea){
        selectedArea = String(selectedArea);
        var selectedProvince = selectedArea.substr(0,2)+"0000";
        var selectedCity = selectedArea.substr(0,4)+"00";
        var city = this._filter( selectedProvince);
        var area = this._filter( selectedCity);

        this.setState({
          selectedProvince:selectedProvince,
          selectedCity:selectedCity,
          selectedArea:selectedArea,
          city:city,
          area:area,
        })
    } 

  }, 
  componentDidMount() {
     
        //cache it.
        this._data = data;

        //过滤省
        var province = this._filter('0');
        this._selectedProvinceName = this._data[this.state.selectedProvince][0];

        //过滤省对于的市
        var city = this._filter(this.state.selectedProvince);

        //市的名字
        this._selectedCityName = '';
        if (this.state.selectedCity) {
          this._selectedCityName = this._data[this.state.selectedCity][0];
        }

        //过滤第一个市对应的区
        var area = [];
        if (this.state.selectedCity) {
          area = this._filter(this.state.selectedCity);

          this._selectAreaName = '';
          if (this.state.selectedArea) {
            this._selectAreaName = this._data[this.state.selectedArea][0];
          }
        }

        this.setState({
          province: province,
          city: city,
          area: area
        }); 
  },


  render() {
    var citydisabe = this.props.disabled;
    var state = this.state;  
    return (
      <div>     
          <Select size="large" style={{width:"140px",marginRight: "3px"}} disabled={citydisabe} name="Level0id"
           value={state.selectedProvince}  onChange={this._handleProvinceChange} >
             {this.state.province.map((v, k) => {
                  return  <Option key={k} value={String(v[0])}>{v[1]}</Option>  
                })}
          </Select>  
        <Select size="large" style={{width:"140px",marginRight: "3px"}} disabled={citydisabe} name="Level1id"
         value={state.selectedCity} onChange={this._handleCityChange }>
           {this.state.city.map((v, k) => {
                return <Option key={k} value={String(v[0])}>{v[1]}</Option> 
              })}
        </Select>   
        <Select size="large" style={{width:"140px",marginRight: "3px"}} disabled={citydisabe} name="areaId"
         value={state.selectedArea} onChange={this._handleAreaChange}>
             {this.state.area.map((v, k) => {
                return <Option key={k} value={String(v[0])}>{v[1]}</Option> 
              })}
        </Select> 
      </div> 
    );
  },


  /**
   * 处理省的改变
   */
  _handleProvinceChange(province) {
    //设置选中的省的名称
    this._selectedProvinceName = this._data[province][0];
 
      //console.log('省发生改变:', province, this._selectedProvinceName);
    

    //过滤出改变后，省对应的市
    var city = this._filter(province);
    //省下面没有市，包括台湾，香港，澳门
    if (city.length === 0) {
      this._selectAreaName = '';
      this._selectedCityName = '';

      this.setState({
        selectedProvince: province,
        selectedCity: '',
        selectedArea: '',
        city: [],
        area: []
      });
    } else {
      this._selectedCityName = city[0][1];
      //过滤区域
      var area = this._filter(city[0][0]);
      //区域名称
      this._selectAreaName = area[0][1];

      this.setState({
        selectedProvince: province,
        selectedCity: city[0][0],
        selectedArea: area[0][0],
        city: city,
        area: area,
      });
      this.props.onChange(area[0][0]);
    }
  },


  /**
   * 处理市改变
   */
  _handleCityChange(city) {
    this._selectedCityName = this._data[city][0];
 
      //console.log('市发生改变:', city, this._selectedCityName);
    

    //过滤出市变化后，区
    var area = this._filter(city);
    if (area.length === 0) {
      this._selectAreaName = '';
      this.setState({
        selectedCity: city,
        selectedArea: '',
        area: []
      });
    } else {
      this._selectAreaName = area[0][1];

      this.setState({
        selectedCity: city,
        selectedArea: area[0][0],
        area: area
      });
      this.props.onChange(area[0][0]);
    }
  },


  /**
   * 处理区域改变
   */
  _handleAreaChange(area) {
    this._selectAreaName = this._data[area][0];
 
      //console.log('区域发生改变:', area, this._selectAreaName);
    
    this.setState({
      selectedArea: area
    })
    this.props.onChange(area);
  },


  /**
   * 处理取消
   */
  _handleCancel() {
    this.props.onCancel()
  },


  /**
   * 处理确定
   */
  _handleSubmit() {
    this.props.onSubmit({
      province: this.state.selectedProvince,
      city: this.state.selectedCity,
      area: this.state.selectedArea,
      provinceName: this._selectedProvinceName,
      cityName: this._selectedCityName,
      areaName: this._selectAreaName
    })
  },


  /**
   * 根据pid查询子节点
   */
  _filter(pid) {
    var result = [];

    for (var code in this._data) {
      if (this._data.hasOwnProperty(code)
          && this._data[code][1] === pid) {
        result.push([code, this._data[code][0]]);
      }
    }

    return result;
  }
});
 


module.exports = Region;
