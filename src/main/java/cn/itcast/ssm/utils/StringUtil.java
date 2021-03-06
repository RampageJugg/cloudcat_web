package cn.itcast.ssm.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 类描述  此类中封装一些常用的字符串操作。 所有方法都是静态方法，不需要生成此类的实例， 为避免生成此类的实例，构造方法被申明为private类型的。
 */
public class StringUtil {

	public static String LINE_SEPARATOR = "\n";

	private static final String PRINTF_CONVERSION_OPERATORS = "%cdiuoxXeEfgGspn";

	private static final int PRINTF_DEFAULT_PRECISION = 6;

	public StringUtil() {

	}

	public static String decodeSpTool(String _value){
		if(_value==null)
		{
			return "";
		}
        return _value.replace("&nbsp;"," ");
	}

	public static String getGUID(){
        UUID uuid = UUID.randomUUID();
        String guid = uuid.toString();
        return guid;
	}

	public static int getArrayIndex(Object arg, String[] annotations){
		int temp = -1;
		if(arg!=null && annotations!=null && annotations.length>0){
			for (int i = 0; i < annotations.length; i++) {
				if(arg.equals(annotations[i])){
					temp = i;
					break;
				}
			}
		}
		return temp;
	}

	public static BigDecimal getBuyerRows(int prodtype){
		if(prodtype==1){
			return new BigDecimal(200);
		}else if(prodtype==2){
			return new BigDecimal(365);
		}else if(prodtype==3){
			return new BigDecimal(500);
		}else {
			return new BigDecimal(0);
		}
	}

	public static BigDecimal getPrice(BigDecimal disc,int prodtype){
		if(prodtype==1){
			return new BigDecimal(1288).multiply(disc);
		}else if(prodtype==2){
			return new BigDecimal(2288).multiply(disc);
		}else if(prodtype==3){
			return new BigDecimal(3688).multiply(disc);
		}else {
			return new BigDecimal(2288).multiply(disc);
		}
	}

	public static boolean isNumeric(String str) {
		if(str!=null&&!"".equals(str.trim())){
			Pattern pattern = Pattern.compile("[0-9]*");
			return pattern.matcher(str).matches();
		}else{
			return false;
		}
	}

	public static String getCookieByName(HttpServletRequest request,String cname){
		Cookie[] cookies = request.getCookies();
		String cstr=null;
		if(cookies!=null){
		    for(Cookie ctemp: cookies){
		    	if(ctemp.getName().equals(cname)){
		    		cstr=ctemp.getValue();
		    		break;
		    	}
		    }
		  }
		return cstr;
	}

	public static Cookie WriteCookie(HttpServletResponse response,String value){
		Cookie queryCookie = new Cookie("protected-mark",value);
		queryCookie.setMaxAge(-1);
		response.addCookie(queryCookie);
		return queryCookie;
	}

	/**
	 * Double类型转为货币格式
	 * @param src
	 * @return
	 */
	public static String formatCurrency(Double src) {
		if (src == null)
			return null;
		DecimalFormat formater = new DecimalFormat("###0.00");
		String s = formater.format(src);
		return s;
	}

	/**
	 * Double类型转为货币格式
	 * @param src
	 * @param format
	 * @return
	 */
	public static String formatCurrency(Double src, String format) {
		if (src == null)
			return null;
		if (isEmpty(format))
			format = "###0.00";
		DecimalFormat formater = new DecimalFormat(format);
		String s = formater.format(src);
		return s;
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 *
	 * @param source 需要进行划分的原字符串
	 * @param delim 单词的分隔字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，如果delim为null则使用逗号作为分隔字符串
	 *
	 */
	public static synchronized String[] split(String source, String delim) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null) {
			delim = ",";
		}
		StringTokenizer st = new StringTokenizer(source, delim);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 *
	 * @param source 需要进行划分的原字符串
	 * @param delim 单词的分隔字符
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数.
	 */
	public static synchronized String[] split(String source, char delim) {
		return split(source, String.valueOf(delim));
	}

	/**
	 * 此方法将给出的字符串source使用逗号划分为单词数组。
	 *
	 * @param source 需要进行划分的原字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 *
	 */
	public static synchronized String[] split(String source) {
		return split(source, ",");
	}

	/**
	 * 循环打印字符串数组。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
	 *
	 * @param strings 字符串数组
	 * @param delim 分隔符
	 * @param out 打印到的输出流
	 */
	public static synchronized void printStrings(String[] strings,
			String delim, OutputStream out) {
		try {
			if (strings != null) {
				int length = strings.length - 1;
				for (int i = 0; i < length; i++) {
					if (strings[i] != null) {
						if (strings[i].indexOf(delim) > -1) {
							out.write(("\"" + strings[i] + "\"" + delim)
									.getBytes());
						} else {
							out.write((strings[i] + delim).getBytes());
						}
					} else {
						out.write("null".getBytes());
					}
				}
				if (strings[length] != null) {
					if (strings[length].indexOf(delim) > -1) {
						out.write(("\"" + strings[length] + "\"").getBytes());
					} else {
						out.write(strings[length].getBytes());
					}
				} else {
					out.write("null".getBytes());
				}
			} else {
				out.write("null".getBytes());
			}
			out.write(LINE_SEPARATOR.getBytes());
		} catch (IOException e) {

		}
	}

	/**
	 * 返回去除时间的日期（yyyy-MM-dd）
	 *
	 */
	public static synchronized Date getDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 循环打印字符串数组到标准输出。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
	 *
	 * @param strings 字符串数组
	 * @param delim 分隔符
	 */
	public static synchronized void printStrings(String[] strings, String delim) {
		printStrings(strings, delim, System.out);
	}

	/**
	 * 循环打印字符串数组。 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
	 *
	 * @param strings 字符串数组
	 * @param out 打印到的输出流
	 */
	public static synchronized void printStrings(String[] strings,
			OutputStream out) {
		printStrings(strings, ",", out);
	}

	/**
	 * 循环打印字符串数组到系统标准输出流System.out。
	 * 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
	 *
	 * @param strings
	 *            字符串数组
	 */
	public static synchronized void printStrings(String[] strings) {
		printStrings(strings, ",", System.out);
	}

	/**
	 * 将字符串中的变量使用values数组中的内容进行替换。 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 *
	 * @param prefix 变量前缀字符串
	 * @param source 带参数的原字符串
	 * @param values 替换用的字符串数组
	 * @return 替换后的字符串。 如果前缀为null则使用“%”作为前缀；
	 *                  如果source或者values为null或者values的长度为0则返回source；
	 *                  如果values的长度大于参数的个数，多余的值将被忽略；
	 *                  如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
	 */
	public static synchronized String getReplaceString(String prefix,
			String source, String[] values) {
		String result = source;
		if (source == null || values == null || values.length < 1) {
			return source;
		}
		if (prefix == null) {
			prefix = "%";
		}

		for (int i = 0; i < values.length; i++) {
			String argument = prefix + Integer.toString(i + 1);
			int index = result.indexOf(argument);
			if (index != -1) {
				String temp = result.substring(0, index);
				if (i < values.length) {
					temp += values[i];
				} else {
					temp += values[values.length - 1];
				}
				temp += result.substring(index + 2);
				result = temp;
			}
		}
		return result;
	}

	/**
	 * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
	 * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 *
	 * @param source 带参数的原字符串
	 * @param values 替换用的字符串数组
	 * @return 替换后的字符串
	 */
	public static synchronized String getReplaceString(String source,
			String[] values) {
		return getReplaceString("%", source, values);
	}

	/**
	 * 字符串数组中是否包含指定的字符串。
	 *
	 * @param strings 字符串数组
	 * @param string 字符串
	 * @param caseSensitive 是否大小写敏感
	 * @return 包含时返回true，否则返回false
	 */
	public static synchronized boolean contains(String[] strings,
			String string, boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive == true) {
				if (strings[i].equals(string)) {
					return true;
				}
			} else {
				if (strings[i].equalsIgnoreCase(string)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串数组中是否包含指定的字符串。大小写敏感。
	 *
	 * @param strings 字符串数组
	 * @param string 字符串
	 * @return 包含时返回true，否则返回false
	 */
	public static synchronized boolean contains(String[] strings, String string) {
		return contains(strings, string, true);
	}

	/**
	 * 不区分大小写判定字符串数组中是否包含指定的字符串。
	 *
	 * @param strings 字符串数组
	 * @param string 字符串
	 * @return 包含时返回true，否则返回false
	 */
	public static synchronized boolean containsIgnoreCase(String[] strings,
			String string) {
		return contains(strings, string, false);
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 *
	 * @param array 字符串数组
	 * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * @return 合并后的字符串
	 */
	public static synchronized String combineStringArray(String[] array,
			String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	/**
	 * 以指定的字符和长度生成一个该字符的指定长度的字符串。
	 *
	 * @param c 指定的字符
	 * @param length 指定的长度
	 * @return 最终生成的字符串
	 */
	public static synchronized String fillString(char c, int length) {
		String ret = "";
		for (int i = 0; i < length; i++) {
			ret += c;
		}
		return ret;
	}

	/**
	 * 去除左边多余的空格。
	 *
	 * @param value 待去左边空格的字符串
	 * @return 去掉左边空格后的字符串
	 */
	public static synchronized String trimLeft(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (Character.isWhitespace(ch[i])) {
				index = i;
			} else {
				break;
			}
		}
		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}

	/**
	 * 去除右边多余的空格。
	 *
	 * @param value 待去右边空格的字符串
	 * @return 去掉右边空格后的字符串
	 */
	public static synchronized String trimRight(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (Character.isWhitespace(ch[i])) {
				endIndex = i;
			} else {
				break;
			}
		}
		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}

	/**
	 * 替换双字节空格并去除首尾空格
	 *
	 * @param value
	 */
	public static synchronized String trim(String value) {
		if (value == null)
			return null;
		value = value.replace('　', ' ');
		return value.trim();
	}

	/**
	 * 根据转义列表对字符串进行转义。
	 *
	 * @param source 待转义的字符串
	 * @param escapeCharMap 转义列表
	 * @return 转义后的字符串
	 */
	public static synchronized String escapeCharacter(String source,
			HashMap<String, Object> escapeCharMap) {
		if (source == null || source.length() == 0)
			return source;
		if (escapeCharMap.size() == 0)
			return source;
		StringBuffer sb = new StringBuffer();
		StringCharacterIterator sci = new StringCharacterIterator(source);
		for (char c = sci.first(); c != StringCharacterIterator.DONE; c = sci
				.next()) {
			String character = String.valueOf(c);
			if (escapeCharMap.containsKey(character))
				character = (String) escapeCharMap.get(character);
			sb.append(character);
		}
		return sb.toString();
	}

	/**
	 * 得到字符串的字节长度。
	 *
	 * @param source 字符串
	 * @return 字符串的字节长度
	 */
	public static synchronized int getByteLength(String source) {
		int len = 0;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			int highByte = c >>> 8;
			len += highByte == 0 ? 1 : 2;
		}
		return len;
	}

	/**
	 * 得到字符串中的子串的个数。
	 *
	 * @param source 字符串
	 * @param sub 子串
	 * @return 字符串中的子串的个数
	 */
	public static synchronized int getSubtringCount(String source, String sub) {
		if (source == null || source.length() == 0) {
			return 0;
		}
		int count = 0;
		int index = source.indexOf(sub);
		while (index >= 0) {
			count++;
			index = source.indexOf(sub, index + 1);
		}
		return count;
	}

	/**
	 * Replace all occurences of a substring within a string with another
	 *
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static synchronized String replace(String inString,
			String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		/** output StringBuffer we'll build up */
		int pos = 0;
		/** our position in the old string */
		int index = inString.indexOf(oldPattern);
		/** the index of an occurrence we've found, or -1 */
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		/** remember to append any characters to the right of a match */
		return sbuf.toString();
	}

	/**
	 * 获取引号字符
	 *
	 * @param source 字符串
	 * @return 加引号的字符串
	 */
	public static synchronized String getQuotedStr(String source) {
		return "'" + replace(source, "'", "''") + "'";

	}

	/**
	 * 获取HashCode
	 * @param source
	 */
	public static synchronized int getHashCode(String source) {
		return source == null ? 0 : source.hashCode();
	}

	/**
	 * 比较字符串相等
	 *
	 * @param str1
	 * @param str2
	 */
	public static synchronized boolean strEqual(String str1, String str2) {
		if (str1 == str2)
			return true;
		if (str1 == null && str2 == null)
			return true;
		if (str1 == null && str2 != null)
			return false;
		return str1.equals(str2);
	}

	/**
	 * 获取日期字符串
	 *
	 * @param date
	 */
	public static synchronized String getDateString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 获取指定格式的日期字符串
	 *
	 * @param date
	 * @param format
	 */
	public static synchronized String getDateString(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 把字符串里英文单引号替换成空
	 *
	 * @param value
	 * @param defaultValue
	 */
	public static synchronized long getAsLong(String value, long defaultValue) {
		try {
			value = value.replaceAll(",", "");
			return Long.valueOf(value).intValue();
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * 把字符串里英文单引号替换成空
	 *
	 * @param value
	 * @param defaultValue
	 */
	public static synchronized int getAsInt(String value, int defaultValue) {
		try {
			value = value.replaceAll(",", "");
			return Integer.valueOf(value).intValue();
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * 字符串转换
	 *
	 * @param value
	 * @param defaultValue
	 */
	public static synchronized Double getAsDouble(String value,
			Double defaultValue) {
		try {
			Double val = Double.valueOf(value);
			return val;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 获取日期及时间字符串
	 *
	 * @param date
	 */
	public static synchronized String getDateTimeString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 判断字符串是否为空
	 *
	 * @param source
	 */
	public static synchronized boolean isEmpty(String source) {
		if (source == null || source.trim().equals(""))
			return true;
		else
			return false;

	}

	/**
	 * 判断是否包含汉字
	 *
	 * @param  source
	 * @return
	 *  
	 */
	public static synchronized boolean isChinese(String source) {
		if (source == null)
			return false;
		for (int i = 0; i < source.length(); i++) {
			char chr = source.charAt(i);
			int value = (int) chr;
			if ((value >= 0x2E80 && value <= 0x9FFF)
					|| (value >= 0xE800 && value <= 0xE87F)
					|| (value >= 0xF900 && value <= 0xFAFF)
					// 全角符号
					|| (value >= 0xFF00 && value <= 0xFF5E)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否未合法的短日期格式yyyy-MM-dd
	 *
	 * @param  strDate
	 * @return
	 *  
	 */
	public static synchronized boolean validateDateString(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.parse(strDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否未合法的长日期格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param  strDate
	 * @return
	 *  
	 */
	public static synchronized boolean validateDateTimeString(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdf.parse(strDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 进制转换
	 *
	 * @param  n the integer to convert to hex form
	 * @return  a String in the form 0xNNNNNNNN where the value of N is in 8
	 *           digit hex
	 *  
	 */
	public static synchronized String toHexString(final int n) {
		final StringBuffer sb = new StringBuffer(10);
		final String s = toUpperCase(Integer.toHexString(n));
		sb.append("0x");
		for (int i = 0; i < 8 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * 进制转换
	 *
	 * @param  n the long to convert to hex form
	 * @return  a String in the form 0xNNNNNNNNNNNNNNNN where the value of N is
	 *           in 16 digit hex
	 *  
	 */
	public static synchronized String toHexString(final long n) {
		final StringBuffer sb = new StringBuffer(18);
		final String s = toUpperCase(Long.toHexString(n));
		sb.append("0x");
		for (int i = 0; i < 16 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * 进制转换
	 *
	 * @param  n the integer to convert to hex form
	 * @return  a String in the form 0xNNNN where the value of N is in 4 digit
	 *           hex
	 *  
	 */
	public static synchronized String toHexString(final short n) {
		final StringBuffer sb = new StringBuffer(10);
		final String s;
		if (n >= 0) {
			s = toUpperCase(Integer.toHexString(n));
		} else {
			s = toUpperCase(Integer.toHexString(n)).substring(4);
		}
		sb.append("0x");
		for (int i = 0; i < 4 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * 进制转换
	 *
	 * @param  n the integer to convert to hex form
	 * @return  a String in the form 0xNN where the value of N is in 2 digit hex
	 *  
	 */
	public static synchronized String toHexString(final byte n) {
		final StringBuffer sb = new StringBuffer(10);
		final String s;
		if (n >= 0) {
			s = toUpperCase(Integer.toHexString(n));
		} else {
			s = toUpperCase(Integer.toHexString(n)).substring(6);
		}
		sb.append("0x");
		for (int i = 0; i < 2 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * Converts a <tt>String</tt> to lower case. This method assumes that
	 * the string is in english. Specifically it only converts characters from A
	 * (0x41) through Z (0x5A) to lower case.
	 *
	 * @param  s the <tt>String</tt> to convert
	 * @return  the converted string
	 *  
	 */
	public static synchronized String toLowerCase(final String s) {
		final char[] ca = s.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			final char c = ca[i];
			if (c >= 'A' && c <= 'Z')
				ca[i] += 32;
		}
		return new String(ca);
	}

	/**
	 * Converts a <tt>String</tt> to upper case. This method assumes that
	 * the string is in english. Specifically it only converts characters from a
	 * (0x61) through z (0x7A) to upper case.
	 *
	 * @param  s the <tt>String</tt> to convert
	 * @return the converted string
	 *  
	 */
	public static synchronized String toUpperCase(final String s) {
		final char[] ca = s.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			final char c = ca[i];
			if (c >= 'a' && c <= 'z')
				ca[i] -= 32;
		}
		return new String(ca);
	}

	/**
	 * 查找字母数字
	 *
	 * @param  s
	 * @return  String
	 *  
	 */
	public static synchronized String toAlphaNumeric(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetterOrDigit(s.charAt(i)))
				sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 分割字符串
	 *
	 * @param  inputString the input String
	 * @param  setString the set of characters to include/exclude
	 * @param  includes whether the set is of includes
	 * @return  the stripped String
	 *  
	 */
	public static synchronized String strip(final String inputString,
			final String setString, final boolean includes) {
		final char[] input = inputString.toCharArray();
		final char[] set = setString.toCharArray();
		Arrays.sort(set);
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			if ((Arrays.binarySearch(set, input[i]) >= 0) == includes) {
				sb.append(input[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * See {@link StringUtil#sprintf}
	 *
	 * @param  s
	 * @param args
	 * @return an int representing the length of the output
	 *  :IllegalArgumentException
	 */
	public static synchronized final int printf(final String s,
			final Object[] args) throws IllegalArgumentException {
		final String out = sprintf(s, args);
		System.out.print(out);
		return out.length();
	}

	/**
	 * Docs from: <a
	 * href=http://www.ssec.wisc.edu/~dglo/c_class/printf.html>http
	 * ://www.ssec.wisc.edu/~dglo/c_class/printf.html</a>
	 * <p>
	 * <h2><code>int printf(const char *format, ...)</code></h2>
	 * <ul>
	 * <li>formats and prints its arguments as specified by the
	 * <code>format</code> string.
	 * <li>Plain characters in <code>format</code> are simply copied
	 * <li>Format specifications are made up of a the percent sign (
	 * <code>%</code>) followed by one of the following conversion operators,
	 * which determine what <code>printf</code> does with its arguments:
	 *
	 * <ul>
	 * <li><strong>%</strong> - print a single <code>%</code> character
	 * <li><strong>c</strong> - convert an <code>int</code> to an
	 * <code>unsigned character</code> and print the resulting character
	 * <li><strong>d</strong> or <strong>i</strong> - print an <code>int</code>
	 * as a signed decimal number
	 *
	 * <li><strong>u</strong> - print an <code>unsigned</code> as an unsigned
	 * decimal number
	 * <li><strong>o</strong> - print an <code>unsigned</code> as an unsigned
	 * octal number
	 * <li><strong>x</strong> or <strong>X</strong> - print an
	 *
	 * <code>unsigned</code> as an unsigned hexadecimal number (where the
	 * letters <code>abcdef</code> are used with <code>x</code> and
	 * <code>ABCDEF</code> are used with <code>X</code>
	 * <li><strong>e</strong> or <strong>E</strong> - print a
	 * <code>double</code>
	 *
	 * using an exponential format like:<br>
	 * <code>[-]<var>d</var>.<var>ddd</var>e[+-]<var>dd</var></code><br>
	 * (where the <code>e</code> is replaced by <code>E</code> if the uppercase
	 * <code>E</code> is specified)
	 *
	 * <li><strong>f</strong> - print a <code>double</code> using a decimal
	 * format like <code>[-]<var>ddd</var>.<var>ddd</var></code>
	 * <li><strong>g</strong> or <strong>G</strong> - print a
	 * <code>double</code>
	 *
	 * using the same decimal format used by the <code>f</code> specification
	 * unless the exponent is less than -4 or greater than or equal to the
	 * specified precision (as described below), in which case the
	 * <code>e</code> format is used (replacing the <code>e</code> with an
	 * <code>E</code> if the <code>G</code> format specification is used.)
	 * Trailing zeros are removed from the right side of the decimal point. If
	 * there would be no digits to the right of the decimal point, the decimal
	 * point is also omitted.
	 * <li><strong>s</strong> - print the string pointed to by a
	 * <code>char *</code>
	 *
	 * <li><strong>p</strong> - print a <code>void *</code> argument in
	 * hexadecimal <em>(ANSI C only)</em>
	 * <li><strong>n</strong> - store the number of characters printed at this
	 * point in the interger pointed to by the <code>int *</code> argument.
	 * Nothing is printed. <em>(ANSI C only)</em>
	 * </ul>
	 *
	 * <li>The conversion operator may be preceeded by zero or more of the
	 * following flag characters:
	 * <ul>
	 * <li><strong>#</strong> specifies that the value should be converted to an
	 * alternate form:
	 * <ul>
	 * <li>For <strong>o</strong>, the precision (described below) is increased
	 * so that the first digit printed is a <code>0</code>
	 * <li>For <strong>x</strong> or <strong>X</strong>, a non-zero value has a
	 * <code>0x</code> prepended (or <code>0X</code> for the <code>X</code>
	 *
	 * specification)
	 * <li>For <strong>e</strong>, <strong>E</strong>, <strong>f</strong>,
	 * <strong>g</strong> or <strong>G</strong>, the result will always contain
	 * a decimal point, even if no digits follow it. Additionally, trailing
	 * zeros are not removed from numbers formatted with <strong>g</strong> or
	 * <strong>G</strong>
	 *
	 * </ul>
	 * <li><strong>0</strong> specifies that the value printed should be padded
	 * on the left with zeros to the maximum width specified
	 * <li><strong>-</strong> specifies that the value should be left justified
	 * (and padded with spaces to the right). If both <strong>0</strong> and
	 * <strong>-</strong> flags are specified, the <strong>0</strong> flag is
	 * ignored.
	 * <li>A space character specifies that a blank should be left before a
	 * positive number in a <strong>d</strong>, <strong>e</strong>,
	 *
	 * <strong>E</strong>, <strong>f</strong>, <strong>g</strong>,
	 * <strong>G</strong> or <strong>i</strong> conversion
	 * <li><strong>+</strong> specifies that a plus sign should placed before a
	 * positive number in a <strong>d</strong>, <strong>e</strong>,
	 *
	 * <strong>E</strong>, <strong>f</strong>, <strong>g</strong>,
	 * <strong>G</strong> or <strong>i</strong> conversion. If both
	 * <strong>+</strong> and space character flags are specified, the space
	 * character flag is ignored.
	 * </ul>
	 * <li>The flag(s) (if any) may be followed by an optional minimum field
	 * width specification, written as a decimal integer. If the value to be
	 * printed is shorter than the field width, it is padded with spaces (or
	 * <code>0</code>s if the
	 *
	 * <code>0</code> flag was specified) to the left (or, if the <code>-</code>
	 * flag was specified, to the right with spaces)
	 * <li>Alternatively, the minimum field width specification may be a
	 * <code>*</code>, in which case the value to be printed is assumed to be
	 * preceeded by an <code>int</code> argument which is used to specify the
	 * minimum width.
	 * <li>The flags(s) and/or field width may be followed by a precision
	 * specification, written as a period followed by a decimal integer, which
	 * specifies:
	 * <ul>
	 * <li>the minimum number of digits to be printed for <strong>d</strong>,
	 *
	 * <strong>i</strong>, <strong>o</strong>, <strong>u</strong>,
	 * <strong>x</strong>, and <strong>X</strong> conversions
	 * <li>the number of digits to the right of the decimal-point for
	 * <strong>e</strong>, <strong>E</strong>, and <strong>f</strong>
	 * conversions
	 *
	 * <li>the number of significant digits for <strong>g</strong> and
	 * <strong>G</strong> conversions
	 * <li>the maximum number of characters to be printed from a string for
	 * <strong>s</strong> conversions
	 * </ul>
	 * <li>Alternatively, the precision specification may be a <code>*</code>,
	 * in which case the value to be printed is assumed to be preceeded by an
	 * <code>int</code> argument (following the <code>int</code> for the minimum
	 * field width, if <code>*</code> is specified for it as well) which is used
	 * to specify the precision.
	 *
	 * <li>Some conversion operators may also be preceeded by a size
	 * specification:
	 * <ul>
	 * <li><strong>h</strong> indicates that the argument associated with a
	 * <strong>d</strong>, <strong>i</strong>, <strong>o</strong>,
	 * <strong>u</strong>, <strong>x</strong> or <strong>X</strong> operator is
	 * a <code>short</code> or
	 *
	 * <code>unsigned short</code>, or that the argument to an
	 * <strong>n</strong> is a <code>short *</code> <em>(ANSI C only)</em>
	 * <li><strong>l</strong> indicates that the argument associated with a
	 * <strong>d</strong>, <strong>i</strong>, <strong>o</strong>,
	 * <strong>u</strong>, <strong>x</strong> or
	 *
	 * <strong>X</strong> operator is a <code>long</code> or
	 * <code>unsigned long</code>, or that the argument to an <strong>n</strong>
	 * is a <code>long *</code>
	 * <li><strong>L</strong> indicates that the argument associated with a
	 * <strong>e</strong>, <strong>E</strong>, <strong>f</strong>,
	 *
	 * <strong>g</strong> or <strong>G</strong> operator is a
	 * <code>long double</code> <em>(ANSI C only)</em>
	 * </ul>
	 * </ul>
	 *
	 *
	 * @param s
	 * @param args
	 * @return a String representing the output
	 *  :IllegalArgumentException
	 */
	public static synchronized final String sprintf(final String s,
			final Object[] args) throws IllegalArgumentException {
		int intValue, argc = 0;
		final StringBuffer ret = new StringBuffer(s.length());
		boolean addPrefix = false;
		boolean padWithZeros = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '%' && (i == 0 || s.charAt(i - 1) != '\\')) {
				final StringBuffer controlBuffer = new StringBuffer();
				/** ----- get control code */
				while (PRINTF_CONVERSION_OPERATORS.indexOf(s.charAt(++i)) == -1) {
					controlBuffer.append(s.charAt(i));
				}
				controlBuffer.append(s.charAt(i));
				/** ----- see if there are any formatting options */
				int width = -1;
				int precision = -1;
				final char objCode = controlBuffer.charAt(controlBuffer
						.length() - 1);
				controlBuffer.deleteCharAt(controlBuffer.length() - 1);
				if (controlBuffer.length() >= 1) {
					if (controlBuffer.indexOf(".") >= 0) {
						precision = Integer.parseInt(controlBuffer.substring(
								controlBuffer.indexOf(".") + 1,
								controlBuffer.length()));
						controlBuffer.delete(controlBuffer.indexOf("."),
								controlBuffer.length());
					}
					if (controlBuffer.indexOf("#") >= 0) {
						addPrefix = true;
						controlBuffer.deleteCharAt(controlBuffer.indexOf("#"));
					}
					if (controlBuffer.indexOf("0") >= 0)
						padWithZeros = true;
					if (controlBuffer.length() > 0)
						width = Integer.parseInt(controlBuffer.toString());
				}
				/** ----- get the associated argument */
				if (args == null || argc >= args.length)
					throw new IllegalArgumentException(
							"Not enough arguments supplied");
				switch (objCode) {
				case ('f'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					Float f = new Float(args[argc].toString());
					if (precision > -1)
						ret.append(roundToPrecision(f.toString(), precision));
					else
						ret.append(roundToPrecision(f.toString(),
								PRINTF_DEFAULT_PRECISION));
					break;
				case ('d'):
				case ('i'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					ret.append(args[argc]);
					break;
				case ('s'):
					ret.append(args[argc]);
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					break;
				case ('%'):
					ret.append(args[argc]);
					ret.append('%');
					break;
				case ('c'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					ret.append(args[argc]);
					break;
				case ('o'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					if (addPrefix)
						ret.append('0');
					intValue = new Integer(args[argc].toString()).intValue();
					ret.append(Integer.toString(intValue, 8));
					break;
				case ('x'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					if (addPrefix)
						ret.append("0x");
					ret.append(toLowerCase(Long.toHexString(new Long(args[argc]
							.toString()).longValue())));
					break;
				case ('X'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					if (addPrefix)
						ret.append("0X");
					ret.append(toUpperCase(Long.toHexString(new Long(args[argc]
							.toString()).longValue())));
					break;
				case ('e'):
					ret.append(toScientific(args[argc].toString(), 'e',
							precision, width, padWithZeros));
					break;
				case ('E'):
					ret.append(toScientific(args[argc].toString(), 'E',
							precision, width, padWithZeros));
					break;
				case ('g'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					ret.append(roundToPrecision(args[argc].toString(),
							PRINTF_DEFAULT_PRECISION));
					break;
				case ('G'):
					break;
				case ('p'):
				case ('n'):
				default:
					throw new IllegalArgumentException("Unsupported param: "
							+ objCode);
				}
				padWithZeros = false;
				addPrefix = false;
				argc++;
			} else {
				ret.append(s.charAt(i));
			}
		}
		return ret.toString();
	}

	/**
	 * 字符创处理
	 *
	 * @param  num
	 * @param  type
	 * @return
	 *  
	 */
	private static synchronized String toScientific(String num, char type,
			int precision, int fieldWidth, boolean padWithZeros) {
		double d = new Double(num).doubleValue();
		int e = 0;
		boolean absGreaterThanOne = false;
		/** the number is not between 1 and -1 */
		if (Math.abs(d) > 1) {
			while (d % 10.0 != d) {
				d = d / 10.0;
				e++;
			}
			absGreaterThanOne = true;
		}
		/** the number lies between 1 and -1 */
		else if (Math.abs(d) < 1 && Math.abs(d) > 0) {
			while (Math.abs(d) < 1) {
				d = d * 10.0;
				e++;
			}
		}

		String sign;
		if (absGreaterThanOne)
			sign = type + "+";
		else
			sign = type + "-";

		String exponent;
		if (e < 10)
			exponent = "0" + Integer.valueOf(e).toString();
		else
			exponent = Integer.valueOf(e).toString();

		String roundedResult = roundToPrecision(new Double(d).toString(),
				precision) + sign + exponent;

		if (padWithZeros && d < 0)
			roundedResult = "-"
					+ padOutput(
							roundedResult.substring(1, roundedResult.length()),
							fieldWidth - 1, type, padWithZeros)
					+ roundedResult.substring(1, roundedResult.length());
		else
			roundedResult = padOutput(roundedResult, fieldWidth, type,
					padWithZeros) + roundedResult;
		return roundedResult;
	}

	/**
	 * 字符创处理
	 *
	 * @param num
	 * @param precision
	 * @return
	 *  :
	 */
	private static synchronized String roundToPrecision(String num,
			int precision) {
		if (precision < 0)
			precision = PRINTF_DEFAULT_PRECISION;
		String fracPart = num.substring(num.indexOf(".") + 1, num.length());
		if (fracPart.length() > precision) {
			fracPart = fracPart.substring(0, precision + 1);
			double d = new Double(fracPart).doubleValue();
			fracPart = Integer.valueOf((int) Math.round(d / 10)).toString();
			return num.substring(0, num.indexOf(".") + 1) + fracPart;
		} else if (fracPart.length() < precision) {
			while (fracPart.length() < precision) {
				fracPart += "0";
			}
			return num.substring(0, num.indexOf(".") + 1) + fracPart;
		}
		return num;
	}

	/**
	 * 字符创处理
	 *
	 * @param input
	 * @param fieldSize
	 * @return
	 *  :
	 */
	private static synchronized String padOutput(String input, int fieldSize,
			char type, boolean padWithZeros) {
		String padding = "";
		for (int i = 0; i < fieldSize - input.length(); i++) {
			if (padWithZeros)
				padding += '0';
			else
				padding += ' ';
		}
		return padding;
	}

	/**
	 * 按指定日期格式取得日期值
	 *
	 * @param  date
	 * @param  dateFormat
	 * @return
	 *  
	 */
	public static synchronized Date getAsDate(String date, String dateFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			return format.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 按缺省日期格式取得日期值
	 *
	 * @param  date
	 * @return
	 *  
	 */
	public static synchronized Date getAsDate(String date) {
		return getAsDate(date, "yyyy-MM-dd");
	}

	/**
	 * 按字节长度截取字符串
	 *
	 * @param  str 将要截取的字符串参数
	 * @param  toCount 截取的字节长度
	 * @return  返回截取后的字符串
	 *  
	 */
	public static String substring(String str, int toCount) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		return reStr;
	}

	/**
	 * 字符串处理
	 *
	 * @param  s_value
	 * @param  delim
	 * @return
	 *  
	 */
	public static synchronized String[] splitByStr(String s_value, String delim) {
		int pos = 0;
		String s_list[];

		if (s_value != null && delim != null) {

			ArrayList<String> list = new ArrayList<String>();

			pos = s_value.indexOf(delim);
			int len = delim.length();

			while (pos >= 0) {
				if (pos > 0)
					list.add(s_value.substring(0, pos));
				if ((pos + len) < s_value.length())
					s_value = s_value.substring(len + pos);
				else
					s_value = null;
				if (s_value != null)
					pos = s_value.indexOf(delim);
				else
					pos = -1;
			}
			if (s_value != null)
				list.add(s_value);
			s_list = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				s_list[i] = (String) list.get(i);
			}
		} else {
			s_list = new String[0];
		}
		return s_list;
	}

	/**
	 * 字符串处理
	 *
	 * @param  val
	 * @return
	 *  
	 */
	public static synchronized boolean getAsBoolean(String val) {
		if ("true".equalsIgnoreCase(val))
			return true;
		else
			return false;
	}

	/**
	 * 验证密码强度，弱密码强度返回1，中等密码强度返回2，高密码强度返回3，错误返回-1
	 *
	 * @param :password
	 * @return  弱密码强度返回1，中等密码强度返回2，高密码强度返回3，错误返回-1
	 *
	 *                                            public static synchronized int
	 *                                            valedatePswStrong(String
	 *                                            password) { RegexUtil reg =
	 *                                            new RegexUtil(); if
	 *                                            (reg.isMatch(password,
	 *                                            Constants
	 *                                            .PSW_STRONG_HIGH_REG)) return
	 *                                            Constants.PSW_STRONG_HIGH;
	 *                                            else if (reg.isMatch(password,
	 *                                            Constants
	 *                                            .PSW_STRONG_LOWER_REG)) return
	 *                                            Constants.PSW_STRONG_LOWER;
	 *                                            else if (reg.isMatch(password,
	 *                                            Constants
	 *                                            .PSW_STRONG_MIDDLE_REG))
	 *                                            return
	 *                                            Constants.PSW_STRONG_MIDDLE;
	 *
	 *                                            return -1; }
	 *  
	 */

	public static String getdirid(final String string) {
		if (string == null)
			return null;
		String curstr = string;
		if (string.length() == 1)
			curstr = "00" + curstr;
		else if (string.length() == 2)
			curstr = "0" + string;
		else
			curstr = string;
		String result = curstr.substring(curstr.length() - 3);
		return result;
	}

	/**
	 * 时间的比较
	 *
	 * @param  date1
	 * @param date2
	 * @return
	 * 
	 */
	public static long getInterval(Date date1, Date date2) {
		long s1 = date1.getTime();
		long s2 = date2.getTime();
		if (s1 - s2 <= 0)
			return 0;

		long interlVal = (s1 - s2) / (1000 * 60 * 60);
		return interlVal;
	}

	/**
	 * 将一个以特殊符号分割的字符串组装成数组，空白的地方用""代替
	 *
	 * @param  str 要分割的字符串
	 * @param  delimiter 分隔符
	 * @return  String[]
	 *  
	 * */
	public String[] convertStrToArray(String str, String delimiter) {
		if (str == null || "".equals(str.trim())) {
			return null;
		}
		StringTokenizer st1 = new StringTokenizer(str, delimiter, true);
		StringTokenizer st2 = new StringTokenizer(str, delimiter);

		int lth = st1.countTokens() - st2.countTokens() + 1;
		String[] tmp = new String[lth];

		int i = 0;
		String last = null;
		while (st1.hasMoreTokens()) {
			String current = st1.nextToken();
			if (!delimiter.equals(current)) {
				tmp[i] = current;
				i++;
			} else if (last == null || delimiter.equals(last)
					|| !st1.hasMoreTokens()) {
				tmp[i] = "";
				i++;
				if (delimiter.equals(last) && !st1.hasMoreTokens()) {
					tmp[i] = "";
				}
			}
			last = current;
		}
		return tmp;
	}

	/**
	 * 过滤尖括号，双引号
	 *
	 * @param  string
	 * @return
	 *  
	 */
	public static String replace(String string) {
		if (string == null || "".equals(string)) {
			return string;
		}
		string = replace(string, "<", "&lt;");
		string = replace(string, ">", "&gt;");
		string = replace(string, "\"", "&quot;");
		return string;
	}

	/**
	 * 只处理< >
	 *
	 * @param  string
	 * @return
	 *  
	 */
	public static String replace1(String string) {
		if (string == null || "".equals(string)) {
			return string;
		}
		string = replace(string, "<", "&lt;");
		string = replace(string, ">", "&gt;");
		return string;
	}

	/**
	 * 从字符串的右边取N位
	 *
	 * @param str
	 * @param n
	 * @return
	 *
	 */
	public static String right(String str, int n) {
		int strLen = str.length();
		if (n > strLen) {// 取的长度大于字符串长度时,返回""
			return "";
		} else {
			int startFlag = strLen - n;
			return str.substring(startFlag, strLen);
		}
	}

	/**
	 * 从字符串的右边取N位
	 * @param str
	 * @return str
	 */
	public static String formatUserId(String str) {
		if ((str == null) || (str.length() == 0)) {
	        return " ";
	    }
		String _tmp="";
		_tmp=str.replace("0", "")
				.replace("1", "")
				.replace("2", "")
				.replace("3", "")
				.replace("4", "")
				.replace("5", "")
				.replace("6", "")
				.replace("7", "")
				.replace("8", "")
				.replace("9", "");
		return _tmp;
	}
	
	/**
	 * 
	 * 应用节点的IP地址
	 * 
	 * @return
	 */
	public static String getNodeIP(){
		StringBuilder IFCONFIG=new StringBuilder(); 
		   try {  
		       for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {  
		           NetworkInterface intf = en.nextElement();  
		           for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
		               InetAddress inetAddress = enumIpAddr.nextElement();  
		               if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {  
		            	   IFCONFIG.append(inetAddress.getHostAddress());  
		               }  
		  
		           }  
		       }  
		   } catch (SocketException ex) {  
			   ex.printStackTrace();
		   } 
		 return IFCONFIG.toString();  
	}
}
