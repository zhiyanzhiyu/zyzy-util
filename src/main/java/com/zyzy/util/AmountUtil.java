package com.zyzy.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ 金额转换工具
 * 
 * @author BillZhao
 *
 */
public class AmountUtil {

	private static final BigDecimal MAG = BigDecimal.valueOf(100);
	public static final int DEFAULT_MONERY_SCALE_VALUE = 2;

	/**
	 * 四舍五入,默认保留两位小数
	 * 
	 * @param amount
	 * @return amount四舍五入结果
	 */
	public static double formatAmount(double amount) {
		BigDecimal b = new BigDecimal(amount);
		return b.setScale(DEFAULT_MONERY_SCALE_VALUE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入，保留指定位数
	 * 
	 * @param amount
	 * @return amount四舍五入结果
	 */
	public static double formatAmount(double amount, int scale) {
		BigDecimal b = new BigDecimal(amount);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 验证输入的字符串是否是金额格式(无非法字符)
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean checkAmount(String amountString) {
		if (null == amountString || amountString.trim().length() == 0)
			return false;
		amountString = amountString.trim();
		String str = "^(0(\\.\\d{0,2})?|([1-9]+[0]*)+(\\.\\d{0,2})?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(amountString);
		return m.find();
	}

	/**
	 * 格式化
	 * 
	 * @param amount
	 * @return
	 */
	public static String formatAmountStr(String amountString) {
		int pos = amountString.indexOf(".");
		if (pos != -1) {
			int tempInt = amountString.substring(pos + 1, amountString.length()).length();
			if (tempInt == 1)
				amountString += "0";
			if (tempInt > 2)
				amountString = amountString.substring(0, pos + 3);
		} else {
			amountString += ".00";
		}
		return amountString;
	}

	/**
	 * 除1000得到真实钱值，默认保留两位小数
	 * 
	 * @param money
	 * @return
	 */
	public static BigDecimal divide100(Long money) {
		if (money == null)
			return null;
		BigDecimal result = BigDecimal.valueOf(money);
		return result.divide(MAG, DEFAULT_MONERY_SCALE_VALUE, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除100得到真实钱值，保留指定位数小数
	 * 
	 * @param money
	 * @return
	 */
	public static BigDecimal divide100(Long money, int scale) {
		if (money == null)
			return null;
		BigDecimal result = BigDecimal.valueOf(money);
		return result.divide(MAG).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除100得到真实钱值
	 * 
	 * @param money
	 * @return
	 */
	public static BigDecimal divide100(BigDecimal money) {
		if (money == null)
			return null;
		return money.divide(MAG).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 乘以100得到计算用钱值
	 * 
	 * @param money
	 * @return
	 */
	public static Long multiply100(BigDecimal money) {
		if (money == null)
			return null;
		return money.multiply(MAG).setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
	}

	/**
	 * 乘以100得到计算用钱值
	 * 
	 * @param money
	 * @return
	 */
	public static Long multiply100(Double money) {
		if (money == null)
			return null;
		BigDecimal result = BigDecimal.valueOf(money);
		return result.multiply(MAG).setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
	}

	/**
	 * 乘以100得到计算用钱值
	 * 
	 * @param money
	 * @return
	 */
	public static Long multiply100(Long money) {
		if (money == null)
			return null;
		BigDecimal result = BigDecimal.valueOf(money);
		return result.multiply(MAG).setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
	}
	
	
	/**
	 * 格式化数字
	 * 
	 * @param number
	 * @param scale
	 *            小数点位数
	 * @return
	 */
	public static String formatNumber(double number, int scale) {
		return ""
				+ new java.math.BigDecimal(number).setScale(scale,
						java.math.BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 格式化数字为逗号分隔符格式
	 * 
	 * @param number
	 * @param scale
	 *            小数点位数
	 * @return
	 */
	public static String formatMoney(double number, int scale) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(scale);

		return nf.format(number);
	}

	/**
	 * 根据传入的数值保留两位有效数字，比如：
	 * 10.778 - 10.77
	 * 0.785 - 0.78
	 * 0.0785 - 0.078
	 * 0.00785 - 0.0078
	 * @param number
	 * @return
	 */
	public static String formatMoney2valid(double number){
		
		if(number >= 0.01)
			return String.format("%.2f",number-0.005);
		if(number < 0.01 && number >= 0.001)
			return String.format("%.3f",number-0.0005);
		if(number < 0.001)
			return String.format("%.4f", number-0.00005);
		
		return String.format("%.2f",number);
	}
	
	public static void main(String[] args) {
		// long id = 1234567890L;
		// BigDecimal bigDecimal = AmountUtil.divide100Scale2(id);
		// System.out.println(bigDecimal);
		//System.out.println(AmountUtil.formatAmountStr("121212.23"));
		//System.out.println(AmountUtil.divide100(999999l));
		//System.out.println(new BigDecimal(9999.99));
		System.out.println(AmountUtil.formatMoney2valid(10.8977));
		System.out.println(AmountUtil.formatMoney2valid(0.8977));
		System.out.println(AmountUtil.formatMoney2valid(0.0977));
		System.out.println(AmountUtil.formatMoney2valid(0.0097));
		System.out.println(AmountUtil.formatMoney2valid(0.0009));
		System.out.println(AmountUtil.formatMoney2valid(10.1111));
		System.out.println(AmountUtil.formatMoney2valid(0.1111));
		System.out.println(AmountUtil.formatMoney2valid(0.0111));
		System.out.println(AmountUtil.formatMoney2valid(0.0011));
		System.out.println(AmountUtil.formatMoney2valid(0.0001));
		
	}
}
