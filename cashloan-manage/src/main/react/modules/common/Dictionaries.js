import React from 'react';
import antd from 'antd';
var Modal = antd.Modal;  
var reqwest = require('reqwest'); 
module.exports = {
  item(){
    var roleList = [];
    reqwest({
      url: '/modules/manage/system/dict/cache/list.htm?type=OFFICE_TYPE',
      method: 'get',
      type: 'json',
      success: (result) => {
        roleList = result.OFFICE_TYPE.map((item) => {
          return (<Option key={item.value} value={item.value}>{item.text}</Option>);
        });
      }
    });
    var provinceList = [];
    reqwest({
      url: '/modules/common/action/PlAreaAction/getAreaListByParentLeave.htm?parentid=0',
      method: 'get',
      type: 'json',
      success: (result) => {
          provinceList = result.data.map((item) => {
          return (<Option key={item.id} value={String(item.id)}>{item.areaname}</Option>);
        });
      }
    });
  }
};


















    /*var postData={};
    for(let key in keys){
      var name =keys[key];
      if(Million){
        postData[name]= data[name]*10000 
      }else if(percentage){
        postData[name]= data[name]/100 
      }
      else{
        postData[name]= data[name]
      }
    }
    postData = JSON.stringify(postData);
    return postData;
  },*/
  /*NextFunction(data){
    var data={}; 
    var item = document.querySelectorAll(".form-input input");
    for(var i=0, len = item.length; i < len; i++){
      var name =item[i].name;
      data[name]=item[i].value
    };
    var item2 = document.querySelectorAll(".form-line span.ant-select-selection__rendered"); 
    var item3 = document.querySelectorAll(".form-line .form-label");
    for(var i=0, len = item2.length; i < len; i++){
      var name =item3[i].htmlFor;
      var item2 = item3[i].nextSibling.querySelectorAll(".form-line span.ant-select-selection__rendered");
      //console.log(item2[0])
      data[name]=item2[0].textContent
    };
    return data;
  }*/
