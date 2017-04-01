/**
 * 
 */
package com.zyzy.util;

/**
 * 
 * @author BillZhao
 *
 */
public class StringUtil {

	/**
	 * 中文转换成unicode
	 * 
	 * @param gbString
	 * @return
	 */
	public static String gbEncoding(String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

	/**
	 * unicode转换成中文
	 * 
	 * @param dataStr
	 * @return
	 */
	public static String decodeUnicode(String dataStr) {
		if (null == dataStr || dataStr.indexOf("\\u") < 0) {
			return dataStr;
		}
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	public static boolean isEmpty(String str) {
		return null == str || null == str.trim() || str.trim().length() == 0;
	}

	public static boolean strIsNull(String s) {
		if (s == null || "".equals(s.trim())) {
			return true;
		}
		return false;
	}


	/**
	 * 把一个参数变成固定程度，长度不足左用0补充. 如果原长度超过要求长度，将原长度直接返回.
	 * 
	 * @param value
	 *            原参数值
	 * @param len
	 *            参数需要的长度.
	 * @return String
	 */
	public static String fillLeftZero(final String value, int len) {
		if (StringUtil.isEmpty(value) || len <= 0) {
			throw new IllegalArgumentException("error param");
		}
		StringBuffer zero = new StringBuffer();
		StringBuffer sb = new StringBuffer(len);
		int paramLen = value.trim().length();
		if (paramLen < len) {
			for (int i = 0; i < len - paramLen; i++) {
				zero.append("0");
			}
		}
		return sb.append(zero).append(value).toString();
	}

	

	/**
	 * String转换成UTF-8编码字符数组
	 * 
	 * @param str
	 * @return byte[]
	 */
	public static byte[] getUtf8Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (Exception uee) {
			return str.getBytes();
		}
	}

	/**
	 * UTF-8格式字符数组转换成String
	 * 
	 * @param utf8
	 * @return String
	 */
	public static String getStringFromUtf8(byte[] utf8) {
		try {
			return new String(utf8, "UTF-8");
		} catch (Exception uee) {
			return new String(utf8);
		}
	}

	
	/**
	 * 数子组转换成String，以逗号分割
	 * 
	 * @param utf8
	 * @return String
	 */
	public static String getStringFromIntArr(int[] intArr) {
		String intStr = "";
		try {
			for(int i:intArr){
				intStr =  intStr + "," + i;
			}
			return intStr.substring(1);
		} catch (Exception uee) {
			return null;
		}
	}


	/**
	 * 替换null为""
	 * 
	 * @param c
	 * @return
	 */
	public static String replaceNull(String c) {
		if (c == null) {
			c = "";
		}
		return c;
	}



	/**
	 * 全角转半角
	 * 
	 * @param input
	 * @return
	 */
	public static String toBanJiao(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375) {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public String lowerCaseFirstChar(String str) {
		if (str == null || str.trim().equals("")) {
			return "";
		}
		StringBuffer sb = new StringBuffer(str);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public String upperCaseFirstChar(String str) {
		if (str == null || str.trim().equals("")) {
			return "";
		}
		StringBuffer sb = new StringBuffer(str);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	
	public static void main(String[] args){
		//String urlStr = "http://10.1.17.11:8080/ucp-appsvr/tokenError";
		//System.out.println(urlStr.split("ucp-appsvr")[0] + "ucp-appsvr/")	
		
		
	}
}
