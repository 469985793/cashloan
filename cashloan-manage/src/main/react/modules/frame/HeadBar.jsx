 
import React from 'react';
import {
	Tooltip
} from 'antd';
import {
	Badge
} from 'antd';
var reqwest = require('reqwest');
var AppActions = require('../frame/actions/AppActions');
const HeadBar = React.createClass({
	getInitialState() {
		return {
			warningData: {},
			taskData: {},
			userData: {}
		}
	},
	bisSmartDevice() {
		var sUserAgent = navigator.userAgent.toLowerCase();
		var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
		var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
		var bIsMidp = sUserAgent.match(/midp/i) == "midp";
		var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
		var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
		var bIsAndroid = sUserAgent.match(/android/i) == "android";
		var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
		var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
		if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
			return true
		} else return false;
	},
	handleClick(tabName) {
		//console.log(tabName);
		AppActions.setTabActiveKey(tabName);
	},
	handleClick2(e) {
		reqwest({
			url: '/system/user/switch/role.htm',
			method: 'get',
			data: {
				role: e
			},
			type: 'json',
			success: (result) => {
				location.reload()
			}
		});
	},
	componentDidMount() {
		var me = this;
		/* reqwest({
                url: '/modules/system/sysAlarmSettingAction/getWarning.htm',
                method: 'post',  
                type: 'json',
                success: (result) => {
                  if(result.code==200){
                    var data = result.data;
                    me.setState({
                      warningData:data
                    })
                  } 
                }
	    }); 
	    reqwest({
                url: '/modules/workflow/controller/ProcessTaskController/queryMyTask.htm?vouchType=2',
                method: 'post',  
                type: 'json',
                success: (result) => {
                  if(result.code==200){
                    var data = result.data;
                    me.setState({
                      taskData:data
                    })
                  } 
                }
	    }); 
	    setInterval(function () {
            	    reqwest({
                            url: '/modules/workflow/controller/ProcessTaskController/queryMyTask.htm?vouchType=2',
                            method: 'post',  
                            type: 'json',
                            success: (result) => {
                              if(result.code==200){
                                var data = result.data;
                                me.setState({
                                  taskData:data
                                })
                              } 
                            }
            	    })
          }.bind(this), 900000);
	    reqwest({
                url: '/modules/manage/system/user/find.htm',
                method: 'post',  
                type: 'json',
                success: (result) => {
                    var data = result;
                    me.setState({
                      userData:data
                    })
                }
	    }); */
	},
	render() {
		var warningData = this.state.warningData;
		var taskData = this.state.taskData;
		var userData = this.state.userData;
		var trigger = 'hover';
		var li;
		if (userData.rolename == "系统管理员") {
			li = (
				<li> 
			  	<Tooltip placement="bottom" title='系统设置' trigger={trigger}>
			  		 <a href="javascript:;" onClick={this.handleClick2.bind(this,1)}><i className="iconfont icon-shezhi"></i></a>
			  	</Tooltip>
			  </li>
			);
		}
		var bisSmartDevice = this.bisSmartDevice();
		if (bisSmartDevice) {
			trigger = 'click';
		}
		return (
			<div className="right rightIcons">
				    <ul>
				        <li>
				            <Tooltip placement="bottom" title='预警' trigger={trigger}>
					            <Badge count={warningData.sum}>
					                <a href="javascript:;" onClick={this.handleClick.bind(null,"预警与提醒")}><i className="iconfont icon-svg31"></i></a>
					            </Badge>
				            </Tooltip>
				        </li>

				        <li>
				            <Tooltip placement="bottom" title='任务' trigger={trigger}>
					            <Badge count={taskData.undone}>
					                <a href="javascript:;" onClick={this.handleClick.bind(null,"车贷 - 我的任务")}><i className="iconfont icon-jifenrenwu"></i></a>
					            </Badge>  
				            </Tooltip>
				        </li>
				        
				        {li}
				    </ul>
				</div>
		)

	}
});
export default HeadBar;