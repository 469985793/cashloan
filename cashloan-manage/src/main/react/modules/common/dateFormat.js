module.exports = {
  formatTen(num) {
    return num > 9 ? (num + "") : ("0" + num);
  },

  formatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    return year + "-" + this.formatTen(month) + "-" + this.formatTen(day);
  },
  formatTime (format, value) {
    var o = {
      "M+": value.getMonth() + 1,
      "d+": value.getDate(),
      "h+": value.getHours(),
      "m+": value.getMinutes(),
      "s+": value.getSeconds(),
      "q+": Math.floor((value.getMonth() + 3) / 3),
      "S": value.getMilliseconds()
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (value.getFullYear() + "").substr(4 - RegExp.$1.length));
    for(var k in o) {
      if (new RegExp("(" + k + ")").test(format)) {
        format = format.replace(RegExp.$1,
          RegExp.$1.length == 1 ? o[k] :
            ("00" + o[k]).substr(("" + o[k]).length));
      }
    }
    return format;
  },
};
 
