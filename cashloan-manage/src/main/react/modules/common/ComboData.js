import reqwest from 'reqwest';
import React from 'react';
import antd from 'antd';
var Select = antd.Select;
var Option = Select.Option;
const ComboData = {
  dic: {},
  initDic() {
    reqwest({
      url: '/modules/manage/system/dict/list.htm',
      success: (data) => {
        if (data.code == 200) {
          this.dic = data
        } else {
          Modal.error({
            title: "字典查询错误"
          });
        }
      }
    });
  },
  /**
   * 获取dicName对应字典，返回字典对象
   */
  getDic(dicName) {
    return this.dic[dicName];
  },
  /**
   * 获取dicName对应的下拉列表，返回React Option数组对象
   * @param dicName 字典名字
   * @returns combo React Option数组对象
   */
  getCombo(dicName) {
    let need = this.dic[dicName];
    let combo = [];
    for (var dicValue in need) {
      if (need.hasOwnProperty(dicValue)) {
        var element = need[dicValue];
        combo.push(<Option key={dicValue} value={dicValue}>{element}</Option>)
      }
    }
    return combo;
  },
  /**
   * 获取dicName对应的Integer型下拉列表，返回React Option数组对象
   * @param dicName 字典名字
   * @returns combo React Option数组对象
   */
  getIntCombo(dicName) {
    let need = this.dic[dicName];
    let combo = [];
    for (var dicValue in need) {
      if (need.hasOwnProperty(dicValue)) {
        if (!isNaN(dicValue)) {
          dicValue = parseInt(dicValue);
        }
        var element = need[dicValue];
        combo.push(<Option key={dicValue} value={dicValue}>{element}</Option>)
      }
    }
    return combo;
  }
}
export default ComboData;