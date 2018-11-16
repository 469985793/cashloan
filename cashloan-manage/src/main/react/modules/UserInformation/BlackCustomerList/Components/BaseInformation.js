import React from 'react';
import {
 Table
} from 'antd';

const objectAssign = require('object-assign');


var BaseInformation = React.createClass({
  getInitialState() {
    return {
      zf: '0',
    };
  },
  componentWillReceiveProps(nextProps) {
   if(nextProps.selectRecord&&nextProps.selectRecord!=this.props.selectRecord){
      this.getSelectUser(nextProps.selectRecord);
   }
  },
  getSelectUser(selectRecord){
   
    Utils.ajaxData({
      url: '/modules/manage/user/findById.htm',
      data: {id:selectRecord.id},
      callback: (result) => {   
        this.setState({
          data: result.data,  
        });
      }
    });
  },
  componentDidMount() {
    var selectRecord=this.props.selectRecord;
    this.getSelectUser(selectRecord);
  },
  render() {
    var data=this.state.data;
     var selectRecord=this.props.selectRecord;
    return (
      <section className="markdown">
        <table>
          <thead>
            <tr>
              <th>标题</th>
              <th>内容</th>
             {/*  <th>分值</th>*/}
     
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>手机号码</td>
              <td>{data?data.phone[0]:null}</td>
              {/*<td>{data?data.phone[1]:null}</td>*/}
            
            </tr>
            <tr>
              <td>姓名</td>
              <td>{data?data.realName[0]:null}</td>
             {/* <td>{data?data.realName[1]:null}</td>*/}
           
            </tr>
            <tr>
              <td>身份证号码</td>
              <td>{data?data.idNo[0]:null}</td>
              {/*<td>{data?data.idNo[1]:null}</td>*/}
            
            </tr>
            <tr>
              <td>授信额度(元)</td>
              <td>{Utils.number(selectRecord.total)}</td>
            
            </tr>
            <tr>
              <td>授信额度已使用(元)</td>
              <td>{Utils.number(selectRecord.used)}</td>
          
            </tr>
            <tr>
              <td>剩余可使用授信额度(元)</td>
              <td>{Utils.number(selectRecord.unuse)}</td>
             
            </tr>
            <tr>
              <td>支付宝账户</td>
              <td>{data?data.alipayNo[0]:null}</td>
              {/*<td>{data?data.alipayNo[1]:null}</td>*/}
            </tr>
            <tr>
              <td>邮箱地址</td>
              <td>{data?data.email[0]:null}</td>
              {/*<td>{data?data.email[1]:null}</td>*/}
         
            </tr>
            <tr>
              <td>所在地址</td>
              <td>{data?data.address[0]:null}</td>
              {/*<td>{data?data.address[1]:null}</td>*/}
        
            </tr>
            {/*
              <tr>
              <td>总计分值</td>
              <td></td>
              <td>{this.state.zf}</td>
            </tr>  
             */}
            
          </tbody>
        </table>
      </section>
    );
  }
});
export default BaseInformation;