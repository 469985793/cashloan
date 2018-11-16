import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import App from './modules/App';
import Login from './modules/Login'
import './modules/utils/Utils.js';
/*登录状态*/
var Home;
if (localStorage.isLogin) {
	Home = App;
} else {
	Home = Login;
}

ReactDOM.render(<Home />, document.getElementById('app'));