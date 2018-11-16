import React from 'react'
import {
    Button,
    Form,
    Input,
    Select,
    Checkbox,
    Modal
} from 'antd';

import './login.css';
const FormItem = Form.Item;
const Option = Select.Option;
let Login = React.createClass({
    getInitialState() {
        return {
            ischecked: 0,
            height:window.innerHeight
        }
    },
    handleSubmit(e) {
        e.preventDefault();
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            //console.log(values);
            var params = values;
            this.login(params);
        });
    },
    login(params) {
        var me = this;
        Utils.ajaxData({
            url: '/system/user/login.htm',
            contentType: 'application/x-www-form-urlencoded',
            data: params,
            callback: function(result) {
                if (result.code == 200) {
                    localStorage.isLogin = true;
                    localStorage.Token = result.Token;
                    location.reload();
                } else {
                    me.changeImg();
                    Modal.error({
                        title: result.msg,
                        onOk: ()=>{
                            me.props.form.resetFields(['code']);
                        }
                    });
                    
                }
            }
        });
    },
    onWindowResize(){
        this.setState({
            height: window.innerHeight
        })
    },
    componentDidMount() {
        var username = Cookies.get("username"); 
        var password = Cookies.get("password"); 
        if (username) {
            this.props.form.setFieldsValue({ username,password });
        }
        window.addEventListener('resize', this.onWindowResize);
    },
    changeImg(){
        var imgSrc = document.getElementsByClassName("imgCode")[0];  
        var times = (new Date()).getTime(); 
        imgSrc.setAttribute("src", '/system/user/imgCode/generate.htm?timestamp='+times); 
    },
    componentWillUnmount() {
        window.removeEventListener('resize', this.onWindowResize)
    },
    render() {
     
        const {
            getFieldProps
        } = this.props.form;
        return (
            <div>
                <div className="g-loginbox">
                    <div className="g-bd">
                        <div className="m-loginbg" style={{height:this.state.height}}>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
})

Login = Form.create()(Login);
export default Login;