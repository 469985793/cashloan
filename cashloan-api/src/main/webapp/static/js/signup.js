$(function() {
  var close = function(e) {
    $(this).parents('.popup').hide();
    return false;
  };
  $('.popup .close').click(close);
  $('.popup a.yes').click(close);
  //$('.popup .overlay').click(close);
  $('.other label i').show();

  //show函数
  var show = function(msg) {
    $('.tips').show().find('h2').text(msg);
  };

  //show_download函数
  var show_download = function(msg) {
    $('.tips').show().find('h2').text(msg);
    $('.yes').on('click', function() {
      window.location.href = "https://www.pgyer.com/hBvX";
    });
  };

  //pop函数
  var pop = function(msg, type) {
    $('.pop').show().find('h2').text(msg);
    //type true 新注册用户弹层点击下载
    if(type){
      $('.pop').find('a').click(function(){
      });
    }
  };

  //表单验证
  var verifyPhone = function() {
    var reg = /^1(3|4|5|7|8)\d{9}$/;
    var tel = $('input[name=phone]').val();
    if (tel === '') {
      show('请输入手机号');
      return false;
    }

    if (!reg.test(tel)) {
      show('手机号不正确');
      return false;
    }
    return tel;
  };

  var verify = function() {
    var pwd = $('input[name=password]').val();
    //MD5加密
    var pwd_md5 = $.md5(pwd);    
    var re = /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,16})$/;
    var code = $('input[name=vcode]').val();
    var inviteCode = $('input[name=invitationCode]').val();
    var channelCode = $('input[name=channelCode]').val();
    var tel = verifyPhone();
    if (!tel) {
      return false;
    }

    if (!re.test(pwd)) {
      show('必须输入6到16位字母与数字组合的密码');
      return false;
    }
    if (code === '') {
      show('验证码不能为空');
      return false;
    }

    if(!($('.other label i').is(":visible"))){
      show('请选择同意《用户使用协议》');
      return false;
    }

    return {
      'loginName': tel,
      'loginPwd': pwd_md5,
      'type': 'register ',
      'invitationCode': inviteCode,
      'channelCode': channelCode,
      'vcode': code
    };
  };
  //点击获取验证码
  var yes = true;
  $('#btn').click(function() {
    var tel = verifyPhone();
    var code = $('input[name=code]').val();
    if(tel && yes){
    	$.ajax({
    	      url: checkurl,
    	      data: {
    	    	  phone: tel,
    	    	  code:code,
    	    	  type: 'register'
    	      },
    	      success: function(data) {
              var time = data.countDown == 0 ? 59 : data.countDown;
              if (data.code == 1000) {
                pop("您已经注册请下载登录");
              } 
              else if (data.code == 200) {
                  show(data.msg);
                  timing = setInterval(function () {
                    $('#btn').html(time + 's');
                    if (time < 1) {
                      clearInterval(timing);
                      $('#btn').removeAttr('disabled').html('获取验证码');
                      yes = true;
                    } else {
                      yes = false;
                    }
                    time--;
                  }, 1000);
              } 
              else {
                show(data.msg);
                changeImg();
              }
    	      }
    	    })
    }
    
    return false;
  });

  //点击注册按钮
  $('#btn-reg').click(function(e) {
    var params = verify();
    if (!params) {
      return false;
    }else{
      $.ajax({
        url: reg,
        data: params,
        dataType: 'json', 
        success: function(data) {
          if (data.code == 200) {
            //注册成功
            show_download('恭喜您注册成功,快去下载app登录吧!');
          }else {
            //报错
            show(data.msg);
          }
        },
        error: function(data){
          show("网络连接错误!");
        }
      });
    }
    return false;
  });   
});