var IdCardValidate = {
  Wi: [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1],    // 加权因子
  ValideCode: [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2],           // 身份证验证位值.10代表X
  IdCardValidate(idCard) {
    idCard = this.trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格
    if (idCard.length == 15) {
      return this.isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证
    } else if (idCard.length == 18) {
      var a_idCard = idCard.split("");                // 得到身份证数组
      if (this.isValidityBrithBy18IdCard(idCard) && this.isTrueValidateCodeBy18IdCard(a_idCard)) {   //进行18位身份证的基本验证和第18位的验证
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  },
  /**
   * 判断身份证号码为18位时最后的验证位是否正确
   * @param a_idCard 身份证号码数组
   * @return
   */
  isTrueValidateCodeBy18IdCard(a_idCard) {
    var sum = 0;                             // 声明加权求和变量
    if (a_idCard[17].toLowerCase() == 'x') {
      a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作
    }
    for(var i = 0; i < 17; i++) {
      sum += this.Wi[i] * a_idCard[i];            // 加权求和
    }
    var valCodePosition = sum % 11;                // 得到验证码所位置
    if (a_idCard[17] == this.ValideCode[valCodePosition]) {
      return true;
    } else {
      return false;
    }
  },
  /**
   * 验证18位数身份证号码中的生日是否是有效生日
   * @param idCard 18位书身份证字符串
   * @return
   */
  isValidityBrithBy18IdCard(idCard18){
    var year = idCard18.substring(6, 10);
    var month = idCard18.substring(10, 12);
    var day = idCard18.substring(12, 14);
    var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
    // 这里用getFullYear()获取年份，避免千年虫问题
    if (temp_date.getFullYear() != parseFloat(year)
      || temp_date.getMonth() != parseFloat(month) - 1
      || temp_date.getDate() != parseFloat(day)) {
      return false;
    } else {
      return true;
    }
  },
  /**
   * 验证15位数身份证号码中的生日是否是有效生日
   * @param idCard15 15位书身份证字符串
   * @return
   */
  isValidityBrithBy15IdCard(idCard15){
    var year = idCard15.substring(6, 8);
    var month = idCard15.substring(8, 10);
    var day = idCard15.substring(10, 12);
    var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
    // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
    if (temp_date.getYear() != parseFloat(year)
      || temp_date.getMonth() != parseFloat(month) - 1
      || temp_date.getDate() != parseFloat(day)) {
      return false;
    } else {
      return true;
    }
  },
  //去掉字符串头尾空格
  trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
  }
};
module.exports = IdCardValidate;