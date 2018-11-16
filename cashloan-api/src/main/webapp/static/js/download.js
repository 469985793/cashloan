$(function () {
  var u = navigator.userAgent
  if (u.indexOf('KDLC') > -1) {
    return
  }

  var getUrlParam = function (name) {
    var requestParameters = new Object()

    var url = window.location.href
    var urlArr = url.split('?')
    if (urlArr[1]) {
      var urlParameters = urlArr[1].split('#')[0]
      if (urlParameters.indexOf('?') == -1) {
        var parameters = decodeURI(urlParameters)
        parameterArray = parameters.split('&')
        for (var i = 0; i < parameterArray.length; i++) {
          requestParameters[parameterArray[i].split('=')[0]] = (parameterArray[i].split('=')[1])
        }
      }
    }
    return requestParameters
  }

  var url = (function () {
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1,
      isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
      isWeixin = (/micromessenger/i).test(u),
      isQQ = (/QQBrowser/i).test(u),
      sourceTag = getUrlParam().source_tag

    if (isAndroid) {
      if (!isWeixin) {
        if (sourceTag) {
          return 'http://www.koudaikj.com/attachment/download/koudailicai_' + sourceTag + '.apk'
        } else {
          return 'http://www.koudaikj.com/attachment/download/koudailicai.apk'
        }
      } else {
        return 'http://a.app.qq.com/o/simple.jsp?pkgname=com.kdkj.koudailicai'
      }
    }
    if (isiOS) {
      if (isWeixin) {
        return 'http://a.app.qq.com/o/simple.jsp?pkgname=com.kdkj.koudailicai'
      } else {
        return 'https://itunes.apple.com/cn/app/id953061503?mt=8'
      }
    }
  }())

  var download = function (e) {
    e.preventDefault()
    if (url) {
      window.location.href = url
    }
  }

  $.fn.extend({
    downloadApp: function (u) {
      if (url) {
        $(this).on('click', function (e) {
          e.preventDefault()
          window.location.href = u
        })
      } else {
        $(this).on('click', download)
      }
    }
  })

  var src = $('#download-show').attr('data-src') || false

  if (src) {
    var $tmp = $('<div class="shareDownload" style="box-sizing:border-box;position:fixed;left:0;bottom:0;width:100%;padding:0 15px;background-color:rgba(0,0,0,.75);z-index:999;font-size:20px;"><a href="javascript:void(0);" class="downloadImg"><img src="' + src + '" style="display:block;padding:0.75em 0;max-width:80%;height:auto;"></a><a href="javascript:void(0)" style="display:block;position: absolute;top:0.5em;right:0.5em;width:0.4em;height:0.4em;background:url(../image/page/close_small.png) no-repeat;background-size:cover;" class="downloadClose"></a></div>')
    $tmp.find('.downloadImg').on('click', download)
    $tmp.find('.downloadClose').on('click', function (e) {
      e.preventDefault()
      $tmp.hide()
    })
    $tmp.appendTo('body')
  } else {
    $('.download-button').downloadApp()
  }
})