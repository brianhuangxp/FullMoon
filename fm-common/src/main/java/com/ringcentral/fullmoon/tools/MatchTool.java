package com.ringcentral.fullmoon.tools;


/**
 * 正则表达式检验类
 * @author huangking
 *
 */
public class MatchTool {

	/**
	 * double类型数字正则表达式
	 */
	private final static String NUMBER_MATCHES = "(^[\\d]*(\\.)?[\\d]*$)|(^-[\\d]*(\\.)?[\\d]*$)|(^0(\\.)?[\\d]*$)";

	/**
	 * integer类型数字正则表达式
	 */
	private final static String INTEGER_MATCHES = "(^[1-9][0-9]*$)|(^-[1-9][0-9]*$)|(0)";

	/**
	 * 判断是否可以转化给整数的正则表达式
	 */
	private final static String CHANGE_INTEGER_MATCHES = "(^[1-9][0-9]*(\\.)?[0]*$)|(^-[1-9][0-9]*(\\.)?[0]*$)|(^0(\\.)?[0]*$)";

	private static boolean match(String str, String matches) {
		if (str == null) {
			return false;
		} else {
			return str.matches(matches);
		}
	}

	/**
	 * 采用正则表达式判断输入是否为double类型数字
	 *
	 * @param str
	 * @return true：符合
	 */
	public static boolean matchNumber(String str) {
		return match(str, NUMBER_MATCHES);
	}

	/**
	 * 判断是否为整数
	 *
	 * @param str
	 * @return
	 */
	public static boolean matchInteger(String str) {
		boolean matchFlag = match(str, INTEGER_MATCHES);
		if (matchFlag && str.length() < String.valueOf(Long.MAX_VALUE).length()
				&& Long.valueOf(str) <= Integer.MAX_VALUE
				&& Long.valueOf(str) >= Integer.MIN_VALUE) {
			return true;
		}
		return false;
	}

    public static boolean matchLong(String str) {
   		boolean matchFlag = match(str, INTEGER_MATCHES);
   		if (matchFlag && str.length() < String.valueOf(Long.MAX_VALUE).length()) {
   			return true;
   		}
   		return false;
   	}

	/**
	 * 判断是否可以转化为整数
	 *
	 * @param d
	 * @return
	 */
	public static boolean matchDoubleChangeInteger(double d) {
		boolean matchFlag = match(String.valueOf(d), CHANGE_INTEGER_MATCHES);
		if (matchFlag && d <= Integer.MAX_VALUE) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否可以转化为整数
	 *
	 * @param str
	 * @return
	 */
	public static boolean matchDoubleChangeInteger(String str) {
		boolean matchFlag = match(str, CHANGE_INTEGER_MATCHES);
		if (matchFlag && str.length() < String.valueOf(Long.MAX_VALUE).length()
				&& Long.valueOf(str) <= Integer.MAX_VALUE
				&& Long.valueOf(str) >= Integer.MIN_VALUE) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否可以转化为短整型
	 *
	 * @param d
	 * @return
	 */
	public static boolean matchDoubleChangeShort(double d) {
		boolean matchFlag = match(String.valueOf(d), CHANGE_INTEGER_MATCHES);
		if (matchFlag && d <= Short.MAX_VALUE) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否可以转化为短整型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean matchDoubleChangeShort(String str) {
		boolean matchFlag = match(str, CHANGE_INTEGER_MATCHES);
		if (matchFlag && str.length() < String.valueOf(Long.MAX_VALUE).length()
				&& Long.valueOf(str) <= Short.MAX_VALUE
				&& Long.valueOf(str) >= Short.MIN_VALUE) {
			return true;
		}
		return false;
	}
}
