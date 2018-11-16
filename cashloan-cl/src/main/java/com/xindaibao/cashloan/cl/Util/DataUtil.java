package com.xindaibao.cashloan.cl.Util;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {
	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	/**
	 * 校验邮箱
	 *
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		if(StringUtils.isBlank(email)){
			return false;
		}
		return Pattern.matches(REGEX_EMAIL, email);
	}

	public static boolean isNull(Object... objs) {
		if (objs==null || objs.length==0) {
			return true;
		}
		for (Object obj : objs) {
			if (null == obj)
				return true;
			if (obj instanceof String && (obj.toString().trim().length() == 0 || "null".equalsIgnoreCase(obj.toString().trim())))
				return true;
			if (obj instanceof Collection && ((Collection<?>) obj).isEmpty())
				return true;
			if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty())
				return true;
			if (obj instanceof Object[] && ((Object[]) obj).length == 0)
				return true;
		}
		return false;
	}

	public static boolean isPositNum(Number... nums) {
		if (nums==null || nums.length==0) {
			return false;
		}
		for (Number num : nums) {
			if (null == num)
				return false;
			if (num instanceof Integer && num.intValue() <= 0)
				return false;
			if (num instanceof Long && num.longValue() <= 0)
				return false;
			if (num instanceof Double && num.doubleValue() <= 0)
				return false;
			if (num instanceof Float && num.floatValue() <= 0)
				return false;
		}
		return true;
	}

	/**
	 * 将空字符串 或者 "N/A" 转成 ""
	 * @param str
	 * @return
	 */
	public static String dbNullToEmpty(String str){
		if (DataUtil.isNull(str) || "N/A".equals(str.trim())) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 高位补零
	 * @Title: DataUtil
	 * @Description: 前面补零，对没有超过maxLen长度的数字，进行高位补零
	 * @param number 数字
	 * @param maxLen 返回最大长度
	 * @return 
	 * @throws
	 */
	public static String zeroFillAtHead(String number, int maxLen){
		int len = maxLen - number.length();
		if (len <= 0) {
			return number;
		}
		while (len > 0) {
			number = "0" + number;
			len--;
		}
		return number;
	}
	
	/**
	 * 是否为日期格式 yyyyMMdd
	 * 
	 * @return
	 */
	public static boolean isDate(String str) {
		try {
			DateFormat df = DateUtil.getDateFormat(DateUtil.YMD);
			Date date = df.parse(str);
			if (date != null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * 判断字符串是否只有字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLetter(String str) {
		String regex = "^[a-zA-Z]+$";
		return str.matches(regex);
	}

	/**
	 * 判断字符串是否只有字母 和 /
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLetter2(String str) {
		String regex = "^[a-zA-Z/]+$";
		return str.matches(regex);
	}

	/**
	 * 判断字符串是否只有数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNubmer(String str) {
		// Pattern pattern = Pattern.compile("^[-\\+]?[\\d+\\.\\d]*$");
		// return pattern.matcher(str).matches();
		String regex = "^[0-9]*$";
		return str.matches(regex);
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNubmeric(String str) {
		// Pattern pattern = Pattern.compile("^[-\\+]?[\\d+\\.\\d]*$");
		// return pattern.matcher(str).matches();
		String regex = "^[-\\+]?[\\d+\\.\\d]*$";
		return str.matches(regex);
	}

	/**
	 * 判断字符串范围
	 * 
	 * @param minLen
	 * @param maxLen
	 * @return
	 */
	public static boolean isInLenLimit(String str, int minLen, int maxLen) {
		if (str.length() < minLen) {
			return false;
		}
		if (str.length() > maxLen) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是特殊字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isSpeStr(String str) {
		int len = str.length();
		str = str.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "");
		if (len == str.length()) {
			return true;
		}
		return false;
	}

	/**
	 * 功能：判断一个字符串是否包含特殊字符
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return true 提供的参数string不包含特殊字符
	 * @return false 提供的参数string包含特殊字符
	 */
	public static boolean isConSpeCharacters(String str) {
		if (str.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
			// 如果不包含特殊字符
			return true;
		}
		return false;
	}

	/**
	 * 该方法主要使用正则表达式来判断字符串中是否包含数字
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean judgeNumberStr(String str) {
		String regex = ".*[0-9]+.*";
		Matcher m = Pattern.compile(regex).matcher(str);
		return m.matches();
	}

	/**
	 * 该方法主要使用正则表达式来判断字符串中是否包含字母
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean judgeContainsStr(String str) {
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(str);
		return m.matches();
	}

	// x字节定长的字母字符
	public static boolean isAx(String str, int x) {
		if (str.length() == x && isLetter(str))
			return true;
		return false;
	}

	// 长度为x-y字节的变长字母字符
	public static boolean isAxy(String str, int x, int y) {
		if (str.length() > x && str.length() < y && isLetter(str))
			return true;
		return false;
	}

	// x字节定长的字母和/或数字字符
	public static boolean isANx(String str, int x) {
		if (str.length() == x && isLetter2(str))
			return true;
		return false;
	}

	// 长度为x-y字节的变长字母和/或数字字符
	public static boolean isANxy(String str, int x, int y) {
		if (str.length() > x && str.length() < y && isLetter2(str))
			return true;
		return false;
	}

	// x字节定长的字母、数字和/或特殊符号字符
	public static boolean isANSx(String str, int x) {
		if (str.length() == x)
			return true;
		return false;
	}

	// 长度为x-y字节的变长字母、数字和/或特殊符号字符
	public static boolean isANSxy(String str, int x, int y) {
		if (str.length() > x && str.length() < y)
			return true;
		return false;
	}

	// x字节定长的字母和/或特殊符号字符
	public static boolean isASx(String str, int x) {
		if (str.length() == x && !judgeNumberStr(str))
			return true;
		return false;
	}

	// 长度为x-y字节的变长字母和/或特殊符号字符
	public static boolean isASxy(String str, int x, int y) {
		if (str.length() > x && str.length() < y && !judgeNumberStr(str))
			return true;
		return false;
	}

	// x字节定长的整型数值
	public static boolean isNx(String str, int x) {
		if (str.length() == x && isNubmer(str))
			return true;
		return false;
	}

	// 长度为x-y字节的整型数值
	public static boolean isNxy(String str, int x, int y) {
		if (str.length() > x && str.length() < y && isNubmer(str))
			return true;
		return false;
	}

	// x字节定长的数字符和/或特殊字符
	public static boolean isNSx(String str, int x) {
		if (str.length() == x && !judgeContainsStr(str))
			return true;
		return false;
	}

	// 长度为x-y 数字符和/或特殊字符
	public static boolean isNSxy(String str, int x, int y) {
		if (str.length() > x && str.length() < y && !judgeContainsStr(str))
			return true;
		return false;
	}

	// x字节定长的特殊符号字符
	public static boolean isSx(String str, int x) {
		if (str.length() == x && isNubmer(str))
			return true;
		return false;
	}

	// 长度为x-y字节的变长特殊符号字符
	public static boolean isSxy(String str, int x, int y) {
		if (str.length() > x && str.length() < y && isNubmer(str))
			return true;
		return false;
	}
	
	/**
	 * hex转成 byte[]
	 * @Title: DataUtil
	 * @Description: TODO
	 * @param hex
	 * @return 
	 * @throws
	 */
    public static byte[] hexToByte(String hex) {
        if (hex.length() % 2 == 1)
            hex = "0" + hex;
        byte[] res = new byte[hex.length() / 2];
        for (int i = 0, n = hex.length(); i < n; i += 2)
            res[i / 2] = (byte) (Integer.parseInt(hex.substring(i, i + 2), 16));
        return res;
    }

	/**
	 * 获取 min~max之间的随机数子
	 * min必须大于0，否则min=0
	 * max 必须大于 min；否则返回min
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomNum(int min, int max) {
		if (min < 0) {
			min = 0;
		}
		if (max < min) {
			return min;
		}
		Random random = new Random();
		int number = random.nextInt(min);
		return number + (max- min);
	}
	
	/**
	 * 获取随机字符串；
	 * @param minLen 最小长度
	 * @param maxLen 最大长度
	 * @return
	 */
	public static String randomStr(int minLen, int maxLen) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		int length = randomNum(minLen, maxLen);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	//判断南非手机号格式
	public static boolean isKenayMobile(String str) {
		String regex = "^\\d{14}$";
		return str.matches(regex);
	}

	//银行卡号的判定
	public static boolean isBankacc(String str) {
		String regex = "^\\d{10}$";
		return str.matches(regex);
	}

	/*
	 *   去掉字符串中的表情符号的方法
	 * */
	public static String deletestr(String str) {
		if (str.trim().isEmpty()) {
			return str;
		}
		String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
		String reStr = "";
		Pattern emoji = Pattern.compile(pattern);
		Matcher emojiMatcher = emoji.matcher(str);
		str = emojiMatcher.replaceAll(reStr);
		return str;
	}
}
