import React from 'react';
import antd from 'antd';
var Modal = antd.Modal;
var reqwest = require('reqwest');
module.exports = {
  BrushJSON(data, scope){
    var keys = [];
    var postData = {};
    var item = document.querySelectorAll(scope);
    for(var i = 0, len = item.length; i < len; i++) {
      ////console.log(document.getElementsByName(item[i].htmlFor))
      var label = item[i].innerText;
      var Million = /万元/.test(label);
      var percentage = /[%]/.test(label);
      var name = item[i].htmlFor;
      if (Million) {
        postData[name] = this.accMul(data[name], 10000)
      } else if (percentage) {
        postData[name] = this.accDiv(data[name], 100)
      }
      else {
        postData[name] = data[name]
      }
    }
    return postData
  },
  submit(scope, url, Actions) {
    var me = scope;
    var validation = me.refs.validation,
      status = 'create';
    var postData = [];
    validation.validate((valid) => {
      if (!valid) {
        //console.log('error in form');
        return;
      } else {
        if (me.props.title == "新增") {
          status = 'create';
        }
        else if (me.props.title == "修改") {
          status = 'update';
        }
        var selectRecord = me.props.selectRecord;
        var formData = me.state.formData;
        var postData = me.BrushJSON(formData, ".form-line .form-label");
        if (formData.officeOverId) {
          postData.office_ids = formData.officeOverId
        }
        ;
        if (selectRecord && selectRecord.id) {
          postData.id = selectRecord.id
        }
        reqwest({
          url: url,
          method: 'post',
          data: {
            json: JSON.stringify(postData),
          },
          type: 'json',
          success: (result) => {
            if (result.success == true || result.code == 200) {
              Modal.success({
                title: result.msg
              });
              Actions.initStore();
              me.props.hideAddModal();
            }
            else {
              Modal.error({
                title: result.msg
              });
              me.setState({
                loading: false
              });
            }
          }
        });
      }
    });
  },

  NumChangeStr(data){
    for(var i = 0, len = item.length; i < len; i++) {
      if (data[i].typeof === 'number') {
        data[i] = String(data[i])
      }
    }
    return data
  },
  //除法函数，用来得到精确的除法结果
  accDiv(arg1, arg2){
    var t1 = 0, t2 = 0, r1, r2;
    try {
      t1 = arg1.toString().split(".")[1].length
    } catch (e) {
    }
    try {
      t2 = arg2.toString().split(".")[1].length
    } catch (e) {
    }
    r1 = Number(arg1.toString().replace(".", ""))
    r2 = Number(arg2.toString().replace(".", ""))
    return (r1 / r2) * Math.pow(10, t2 - t1);
  },
  //乘法函数，用来得到精确的乘法结果
  accMul(arg1, arg2)
  {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
      m += s1.split(".")[1].length
    } catch (e) {
    }
    try {
      m += s2.split(".")[1].length
    } catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
  },
  //加法函数，用来得到精确的加法结果  
  accAdd(arg1, arg2){
    var r1, r2, m;
    try {
      r1 = arg1.toString().split(".")[1].length
    } catch (e) {
      r1 = 0
    }
    try {
      r2 = arg2.toString().split(".")[1].length
    } catch (e) {
      r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2))
    return (arg1 * m + arg2 * m) / m
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
