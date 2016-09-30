//******************************************************************************
// Copyright (C) 2012 Sanko, All Rights Reserved.
//******************************************************************************
package com.orange.util;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 共通Util
 *
 * @author lbs
 */
public final class StringUtil {
	/**
	 * 空文字
	 */
	public static final String EMPTY = "";
	/**
	 * 日期的format
	 * yyyyMMdd
	 */
	public static final String DATE_FORMAT_YYYYMMDD_1 = "yyyyMMdd";
	/**
	 * 日期的format
	 * yyyy/MM/dd
	 */
	public static final String DATE_FORMAT_YYYYMMDD_2 = "yyyy/MM/dd";
	/**
	 * 日期的format
	 * yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_YYYYMMDD_3 = "yyyy-MM-dd";
	/**
	 * 日期的format
	 * yyyyMMdd HH:mm:ss
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS_1 = "yyyyMMdd HH:mm:ss";
	/**
	 * 日期的format
	 * yyyyMMdd H:mm:ss
	 */
	public static final String DATE_FORMAT_YYYYMMDDHMMSS = "yyyy/MM/dd H:mm:ss";
	/**
	 * 日期的format
	 * yyyyMMddHHmmss
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS_0 = "yyyyMMddHHmmss";
	/**
	 * 日期的format
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS_2 = "yyyy/MM/dd HH:mm:ss";
	/**
	 * 日期的format
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS_3 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期的format
	 * yyyyMMdd HH:mm:ss SSS
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS_1 = "yyyyMMdd HH:mm:ss SSS";
	/**
	 * 日期的format
	 * yyyy/MM/dd HH:mm:ss SSS
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS_2 = "yyyy/MM/dd HH:mm:ss SSS";
	/**
	 * 日期的format
	 * yyyyMMddHHmmssSSS
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS_5 = "yyyyMMddHHmmssSSS";
	/**
	 * 日期的format
	 * yyyy/MM/dd HH:mm:ss SSS
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS_4 = "yyyy/MM/dd HH:mm:ss.SSS";
	/**
	 * 日期的format
	 * yyyy/MM/dd HH:mm
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
	/**
	 * 日期的format
	 * HH:mm:ss
	 */
	public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";
	/**
	 * 日期的format
	 * H:mm:ss
	 */
	public static final String DATE_FORMAT_HMMSS = "H:mm:ss";
	/**
	 * 日期的format
	 * yyyy-MM-dd HH:mm:ss SSS
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS_3 = "yyyy-MM-dd HH:mm:ss SSS";
	/**
	 * 日期的format
	 * yyyy/MM/dd a
	 */
	public static final String DATE_FORMAT_YYYYMMDD_A = "yyyy/MM/dd a";
	/**
	 * 日期的format
	 * yyyy/MM/dd HH:mm a
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMA = "yyyy/MM/dd HH:mm a";
	/**
	 * 日期的format
	 * yyyy/M/d
	 */
	public static final String DATE_FORMAT_YYYYMD = "yyyy/M/d";
	/**
	 * 日期的format
	 * yyyyMM
	 */
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";
	/**
	 * 日期的format
	 * yyyy/MM
	 */
	public static final String DATE_FORMAT_YYYYMM_1 = "yyyy/MM";
	/**
	 * 全角空格
	 */
	public static final String ZENKAKU_SPACE = "　";
	/**
	 * 半角空格
	 */
	public static final String HANKAKU_SPACE = " ";
	/**
	 * 日期的format
	 */
	public static final String PERCENT = "%";
	/** 最多空格 */
	private static final int MAXSPACES = 2560;
	/**
	 * 最多0
	 */
	private static final int MAXZEROS = 2560;
	/**
	 * 空格
	 */
	private static final String SPACES = "                                        ";
	/**
	 * 空格长度
	 */
	private static final int SPACESLEN = 40;
	/**
	 * 3
	 */
	public static final int NUMBER_3 = 3;
	/**
	 * 4
	 */
	public static final int NUMBER_4 = 4;
	/**
	 * 5
	 */
	public static final int NUMBER_5 = 5;
	/**
	 * 12
	 */
	public static final int NUMBER_12 = 12;
	/**
	 * 7
	 */
	public static final int NUMBER_7 = 7;
	/**
	 * 1024
	 */
	public static final int NUMBER_1024 = 1024;
	/**
	 * 4096
	 */
	public static final int NUMBER_4096 = 4096;
	/**
	 * String: OR 
	 */
	public static final String SEP_OR_SEARCH = "\\s+([oO][rR]){1}\\s+";
	/**
	 * String: or 
	 */
	public static final String SEP_OR_SEARCH2 = "\\s+(or){1}\\s+";
	/**
	 * 1000
	 */
	public static final double CHANGE_DATE_USE_NO = 1000;
	/**
	 * 0
	 */
	private static final String ZEROS = "0000000000000000000000000000000000000000";
	/**
	 * 0长度
	 */
	private static final int ZEROSLEN = 40;
	/**
	 * JP
	 */
	private static final String LOCALE_JP = "JP";
	/**
	 * lock
	 */
	private static Object lock = new Object();
	
	/**
	 * image type of Thumb
	 */
	public static final String IMAGE_TYPE_THUMB = "s";
	
	/**
	 * image type of NORMAL
	 */
	public static final String IMAGE_TYPE_NORMAL = "n";
	
	/**
	 * image type of DISC
	 */
	public static final String IMAGE_TYPE_DISC = "d";
	
	/**
	 * 半角英数字
	 */
	private static final char[] HANKAKU_3 = {'1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z'};
	/**
	 * 全角英数字
	 */
	private static final char[] ZENKAKU_3 = {'１', '２', '３', '４', '５', '６', '７',
			'８', '９', '０', 'ａ', 'ｂ', 'ｃ', 'ｄ', 'ｅ', 'ｆ', 'ｇ', 'ｈ', 'ｉ', 'ｊ',
			'ｋ', 'ｌ', 'ｍ', 'ｎ', 'ｏ', 'ｐ', 'ｑ', 'ｒ', 'ｓ', 'ｔ', 'ｕ', 'ｖ', 'ｗ',
			'ｘ', 'ｙ', 'ｚ', 'Ａ', 'Ｂ', 'Ｃ', 'Ｄ', 'Ｅ', 'Ｆ', 'Ｇ', 'Ｈ', 'Ｉ', 'Ｊ',
			'Ｋ', 'Ｌ', 'Ｍ', 'Ｎ', 'Ｏ', 'Ｐ', 'Ｑ', 'Ｒ', 'Ｓ', 'Ｔ', 'Ｕ', 'Ｖ', 'Ｗ',
			'Ｘ', 'Ｙ', 'Ｚ'};
	/**
	 * 半角数字
	 */
	private static final char[] HANKAKU_5 = {'1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0'};
	/**
	 * 全角空格
	 */
	private static final char C_KANJI_SPACE = '　';

	/**
	 * 减号
	 */
	private static final String STR_MINUS = "-";
	
	/**
	 * 双引用，引证
	 */
	public static final String DOUBLE_QUOTE = "\"";
	
	@SuppressWarnings("unused")
	private static Pattern pattern;

	@SuppressWarnings("unused")
	private static Matcher matcher;

	/**
	 * 构造函数，初期化处理
	 */
	private StringUtil() {
	}

	/**
	 * Returns an empty String (i.e. a String which consists of only blanks)
	 * of the specified length.
	 *
	 * @param       len length of String to return.
	 * @return      Blank String of the given length.
	 *              MAXSPACES (currently 2560).
	 */
	private static String getSpaces(final int len) {
		final StringBuilder tmpSpace = new StringBuilder(SPACES);
		int intSpaceLen = SPACESLEN;
		if (len > intSpaceLen && intSpaceLen < MAXSPACES) {
			synchronized (lock) {
				while (len > intSpaceLen && intSpaceLen < MAXSPACES) {
					tmpSpace.append(tmpSpace);
					intSpaceLen += intSpaceLen;
				}
			}
		}
		return tmpSpace.substring(0, len);
	}

	/**
	 * Returns a String of the specified length which consists of only
	 * Zeros (ASCII 48).
	 *
	 * @param       len length of String to return.
	 * @return      Zero-filled String of the given length.
	 *              MAXZEROS (currently 2560).
	 */
	private static String getZeros(final int len) {
		final StringBuilder strZeros = new StringBuilder(ZEROS);
		int intZeroLen = ZEROSLEN;
		if (len > intZeroLen && intZeroLen < MAXZEROS) {
			synchronized (lock) {
				while (len > intZeroLen && intZeroLen < MAXZEROS) {
					strZeros.append(strZeros);
					intZeroLen += intZeroLen;
				}
			}
		}
		return strZeros.substring(0, len);
	}

	/**
	 * Pads the String s to the given length by inserting blanks at the left
	 * side. If s is longer than len, the String remains unchanged.
	 *
	 * @param       s String to pad
	 * @param       len length of resulting String
	 * @return      The padded String.
	 */
	public static String padLeft(final String s, final int len) {
		return padLeft(s, len, false);
	}

	/**
	 * Pads the String s to the given length by inserting blanks at the left
	 * side. If s is longer than len and trim is set to true, the result is
	 * truncated to the given length.
	 *
	 * @param       s String to pad
	 * @param       len length of resulting String
	 * @param       trim truncates s if longer then len
	 * @return      The padded String.
	 */
	public static String padLeft(final String s, final int len,
			final boolean trim) {
		final String tmpS = StringUtil.nvl(s);
		final int slen = tmpS.getBytes().length;
		String ret;
		if (slen < len) {
			ret = getSpaces(len - slen) + tmpS;
		} else if (slen > len && trim) {
			final StringBuilder tempRet = new StringBuilder();
			for (int n = 0; n < tmpS.length(); n++) {
				if ((tempRet.append(tmpS.charAt(n)).toString()).getBytes().length > len) {
					break;
				}
				tempRet.append(tmpS.charAt(n));
			}
			ret = tempRet.toString();
		} else {
			ret = tmpS;
		}
		return ret;
	}

	/**
	 * Pads the String s to the given length by inserting blanks at the right
	 * end. If s is longer than len, the String remains unchanged.
	 *
	 * @param       s String to pad
	 * @param       len length of resulting String
	 * @return      The padded String.
	 */
	public static String padRight(final String s, final int len) {
		return padRight(s, len, false);
	}

	/**
	 * Pads the String s to the given length by inserting blanks at the right
	 * end. If s is longer than len and trim is true, the result is
	 * truncated to the given length.
	 *
	 * @param       s String to pad
	 * @param       len length of resulting String
	 * @param       trim truncates s if longer then len
	 * @return      The padded String.
	 */
	public static String padRight(final String s, final int len,
			final boolean trim) {
		final String tmpS = StringUtil.nvl(s);
		final int slen = tmpS.getBytes().length;
		String ret;
		if (slen < len) {
			ret = tmpS + getSpaces(len - slen);
		} else if (slen > len && trim) {
			final StringBuilder tempRet = new StringBuilder();
			for (int n = 0; n < tmpS.length(); n++) {
				if (tempRet.append(tmpS.charAt(n)).toString().getBytes().length > len) {
					break;
				}
				tempRet.append(tmpS.charAt(n));
			}
			ret = tempRet.toString();
		} else {
			ret = tmpS;
		}
		return ret;
	}

	/**
	 * Left-pads the String with zeros to the given length.
	 *
	 * @param       s String to pad
	 * @param       len length of resulting String
	 * @return      The padded value as a String.
	 */
	public static String padZero(final String s, final int len) {
		final String tmpS = StringUtil.nvl(s);
		final int slen = tmpS.getBytes().length;
		String ret;
		if (slen < len) {
			ret = getZeros(len - slen) + tmpS;
		} else {
			ret = tmpS;
		}
		return ret;
	}

	/**
	 * Converts the integer value into a String which is left-padded with
	 * zeros to the given length.
	 *
	 * @param       value integer value to pad
	 * @param       len length of resulting String
	 * @return      The padded value as a String.
	 */
	public static String padZero(final int value, final int len) {
		String s = EMPTY + value;
		final int slen = s.length();
		String ret;
		if (slen < len) {
			ret = getZeros(len - slen) + s;
		} else {
			ret = s;
		}
		return ret;
	}

	/**
	 * replace sub string
	 *
	 * @param src 元値
	 * @param sFnd パラメータ
	 * @param sRep 替える値
	 * @return String 返し値
	 */
	public static String
			replaceStr(final String src, final String sFnd, final String sRep) {
		final StringBuilder sTemp = new StringBuilder();
		int endIndex = 0;
		int beginIndex = 0;
		do {
			endIndex = src.indexOf(sFnd, beginIndex);
			if (endIndex >= 0) { // mean found it.
				sTemp.append(src.substring(beginIndex, endIndex));
				sTemp.append(sRep);
				beginIndex = endIndex + sFnd.length();
			} else if (beginIndex >= 0) {
				sTemp.append(src.substring(beginIndex));
				break;
			}
		} while (endIndex >= 0);
		return sTemp.toString();
	}

	/**
	 * replace sub string
	 *
	 * @param src 元値
	 * @return String 返し値
	 */
	public static String replaceSearchStr(final String src) {
		String sTemp = src;
		sTemp = replaceStr(sTemp, ZENKAKU_SPACE, PERCENT);
		sTemp = replaceStr(sTemp, HANKAKU_SPACE, PERCENT);
		return sTemp;
	}

	/**
	 * 文字は全角かを判定する
	 * @param argCh 判定要文字
	 * @return 判定結果
	 */
	public static boolean isZenkaku(final char argCh) {
		return !(argCh <= '\u007e'   // 英数字
				|| argCh == '\u00a5' // \記号
				|| argCh == '\u203e' // ~記号
				|| (argCh >= '\uff61' && argCh <= '\uff9f')); // 半角カナ
	}

	/**
	 * 文字列に数字をチェック
	 *
	 * @param str パラメータ
	 * @return boolean チェック結果
	 */
	public static boolean isNumber(final String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		int i;
		final String str1 = str.replace("+", EMPTY).replace("-", EMPTY);
		for (i = 0; i < str1.length(); i++) {
			if (!java.lang.Character.isDigit(str1.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 文字列に小数数字をチェック
	 *
	 * @param str パラメータ
	 * @return boolean チェック結果
	 */
	public static boolean isDecimal(final String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		boolean hasPoint = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.') {
				if (i == 0 || hasPoint) {
					return false;
				} else {
					hasPoint = true;
				}
			} else if (!java.lang.Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 西暦の日付をチェックする
	 * 
	 * @param dStr
	 *            日付文字列
	 * @return true, false
	 */
	public static boolean isDate(String dStr) {
		if (dStr == null) {
			return false;
		}
		RE r;
		try {
			switch (dStr.length()) {
				case 8:
					r = new RE("([0-9]{4})([0-9]{2})([0-9]{2})");
					if (!r.match(dStr)) {
						return false;
					}
					break;
				case 10:
					r = new RE("([0-9]{4})/([0-9]{2})/([0-9]{2})");
					if (!r.match(dStr)) {
						return false;
					}
					break;
				default:
					return false;
			}
			int year = Integer.parseInt(r.getParen(1));
			int month = Integer.parseInt(r.getParen(2));
			int day = Integer.parseInt(r.getParen(NUMBER_3));
			if (!checkYear(year) || !checkMonth(month)
					|| !checkDay(year, month, day))
			{
				return false;
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 西暦の日付をチェックする
	 * 
	 * @param dStr
	 *            日付文字列
	 * @return true, false
	 */
	public static boolean isYYYYDate(String dStr) {
		if (dStr == null) {
			return false;
		}
		RE r;
		try {
			switch (dStr.length()) {
				case 4:
					r = new RE("([0-9]{4})");
					if (!r.match(dStr)) {
						return false;
					}
					break;
				default:
					return false;
			}
			int year = Integer.parseInt(r.getParen(1));
			if (!checkYear(year)) {
				return false;
			}
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}

	/**
	 * 西暦の日付を和暦へ変換
	 * 
	 * @param date
	 *            西暦の日付
	 * @return 和暦の日付
	 */
	public static String changeDateToJpLocale(Date date) {
		// 和暦で表示
		Locale locale = new Locale("ja", LOCALE_JP, LOCALE_JP);

		DateFormat japaneseFormat = 
				new SimpleDateFormat("GGGGyyyy年MM月", locale);
	    return japaneseFormat.format(date);
	}

	/**
	 * 西暦の日付を和暦へ変換
	 * 
	 * @param date
	 *            西暦の日付
	 * @return 和暦の日付
	 */
	public static String changeDateToJpLocaleG(Date date) {
		// 和暦で表示
		Locale locale = new Locale("ja", LOCALE_JP, LOCALE_JP);

		DateFormat japaneseFormat = 
				new SimpleDateFormat("Gyy.MM.dd", locale);
	    return japaneseFormat.format(date);
	}
	


	/**
	 * 西暦の日付を変換
	 * 
	 * @param date
	 *            日付
	 * @param format
	 *            日付のフォーマット
	 * @return 日付文字列
	 */
	public static String changeDateToEnLocale(final Date date, final String format) {
		DateFormat japaneseFormat = 
				new SimpleDateFormat(format, Locale.ENGLISH);
	    return japaneseFormat.format(date);
	}

	/**
	 * Check validity of year
	 * 
	 * @param year チェック要年 
	 *            <b>year</b>
	 * @return true , false
	 */
	private static boolean checkYear(int year) {
		return (year > 999 && year < 10000) ? true : false;
	}

	/**
	 * Check validity of month
	 * 
	 * @param month
	 *            <b>month</b>
	 * @return true , false
	 */
	private static boolean checkMonth(int month) {
		return (month > 0 && month < 13) ? true : false;
	}

	/**
	 * Check validity of day
	 * 
	 * @param year
	 *            <b>year</b>
	 * @param month
	 *            <b>month</b>
	 * @param day
	 *            <b>day</b>
	 * @return true , false
	 */
	private static boolean checkDay(int year, int month, int day) {
		switch (month) {
			case 1:
			case NUMBER_3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if (day >= 1 && day <= 31) {
					return true;
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				if (day >= 1 && day <= 30) {
					return true;
				}
				break;
			case 2:
				if (isLeapYear(year)) {
					if (day >= 1 && day <= 29) {
						return true;
					}
				} else {
					if (day >= 1 && day <= 28) {
						return true;
					}
				}
				break;
			default:
				return false;
		}
		return false;
	}

	/**
	 * 時刻をチェックする
	 * 
	 * @param tStr
	 *            時刻文字列
	 * @return true, false
	 */
	public static boolean isTime(String tStr) {
		if (tStr == null) {
			return false;
		}
		RE r;
		try {
			switch (tStr.length()) {
				case 6:
					r = new RE("([0-9]{2})([0-9]{2})([0-9]{2})");
					if (!r.match(tStr)) {
						return false;
					}
					break;
				case 8:
					r = new RE("([0-9]{2}):([0-9]{2}):([0-9]{2})");
					if (!r.match(tStr)) {
						return false;
					}
					break;
				default:
					return false;
			}
			int hour = Integer.parseInt(r.getParen(1));
			int minute = Integer.parseInt(r.getParen(2));
			int second = Integer.parseInt(r.getParen(NUMBER_3));
			if (hour < 0 || hour > 23 || minute < 0 || minute > 59
					|| second < 0 || second > 59)
			{
				return false;
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 文字列に全角数字英文が含まれているかチェック
	 *
	 * @param str パラメータ
	 * @return boolean チェック結果
	 */
	public static boolean containsZenNum(String str) {
		int i;
		String zenNum = "０１２３４５６７８９";
		zenNum += "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ";
		zenNum += "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ";
		for (i = 0; i < str.length(); i++) {
			if (zenNum.indexOf(str.substring(i, i + 1)) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * NULLの判断
	 *
	 * @param sIn パラメータ
	 * @return String 結果
	 */
	public static String nvl(String sIn) {
		return (sIn == null) ? EMPTY : sIn;
	}

	/**
	 * NULLの判断
	 *
	 * @param sIn パラメータ
	 * @return String 結果
	 */
	public static String nvl(Object sIn) {
		return (sIn == null) ? EMPTY : (String) sIn;
	}

	/**
	 * NumberのNull変換
	 *
	 * @param iIn 
	 * @return 変換結果
	 */
	public static String nvl(Number iIn) {
		return (iIn == null) ? EMPTY : iIn.toString();
	}

	/**
	 * NULLの判断
	 *
	 * @param sIn パラメータ
	 * @param sDef パラメータ
	 * @return String 結果
	 */
	public static String nvl(String sIn, String sDef) {
		return (sIn == null) ? sDef : sIn;
	}

	/**
	 * 期日のフォマートをする
	 *
	 * @param sYear 年
	 * @param sMonth 月
	 * @param sDay 日
	 * @return String 結果
	 */
	public static String fmtDate(String sYear, String sMonth, String sDay) {
		String tmpMonth = sMonth;
		if (tmpMonth.length() == 1) {
			tmpMonth = "0" + tmpMonth;
		}
		String tmpDay = sDay;
		if (tmpDay.length() == 1) {
			tmpDay = "0" + tmpDay;
		}
		return sYear + tmpMonth + tmpDay;
	}

	/**
	 * 期日を換える
	 *
	 * @param sDate 期日
	 * @param sSep パラメータ
	 * @return String 結果
	 */
	public static String convDate(String sDate, String sSep) {
		int pos = 0;
		String str = sDate;
		int len = str.length();
		if ((len < 8) || (len > 10)) {
			return str;
		} else if (str.indexOf(sSep) == 4) {
			pos = str.indexOf(sSep, 5);
			if (pos == 6) {
				if (len == 8) {
					return str.substring(0, 4) + "0" + str.substring(5, 6)
							+ "0" + str.substring(7, 8);
				} else {
					return str.substring(0, 4) + "0" + str.substring(5, 6)
							+ str.substring(7, 9);
				}
			} else if (pos == 7) {
				if (len == 9) {
					return str.substring(0, 4) + str.substring(5, 7) + "0"
							+ str.substring(8, 9);
				} else {
					return str.substring(0, 4) + str.substring(5, 7)
							+ str.substring(8, 10);
				}
			} else {
				return str;
			}
		} else {
			return str;
		}
	}

	/**
	 * 期日をチェック
	 *
	 * @param date 期日
	 * @return boolean チェック結果
	 */
	public static boolean chkDate(String date) {
		int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int[] leapdays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int year;
		int month;
		int day;
		int i;
		String tmpDate = date;
		if (tmpDate == null) {
			return false;
		}
		try {
			if (tmpDate.length() == 10) {
				tmpDate = formatDate(tmpDate);
			}
			if (tmpDate.length() != 8 && tmpDate.length() != 6) {
				return false;
			}
			if (tmpDate.length() == 8) {
				year = java.lang.Integer.parseInt(tmpDate.substring(0, 4));
				month = java.lang.Integer.parseInt(tmpDate.substring(4, 6));
				day = java.lang.Integer.parseInt(tmpDate.substring(6, 8));
				if (month < 1 || month > 12 || day < 1) {
					return false;
				}
				if (isLeapYear(year)) {
					for (i = 0; i < months.length; i++) {
						if (months[i] == month && day > leapdays[i]) {
							return false;
						}
					}
				} else {
					for (i = 0; i < months.length; i++) {
						if (months[i] == month && day > days[i]) {
							return false;
						}
					}
				}
			} else if (tmpDate.length() == 6) {
				year = java.lang.Integer.parseInt(tmpDate.substring(0, 4));
				month = java.lang.Integer.parseInt(tmpDate.substring(4, 6));
				if (month < 0 || month > 12) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 閏年の判断
	 *
	 * @param year 年
	 * @return boolean 判断結果
	 */
	public static boolean isLeapYear(int year) {
		if (year % 100 != 0 && year % 4 == 0) {
			return true;
		} else if (year % 400 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * メールを判断する。
	 *
	 * @param mail メール
	 * @return boolean 判断する結果
	 */
	public static boolean chkMail(String mail) {
		int i;
		int len = mail.length();
		int aPos = mail.indexOf("@");
		int dPos = mail.indexOf(".");
		int aaPos = mail.indexOf("@@");
		int adPos = mail.indexOf("@.");
		int ddPos = mail.indexOf("..");
		int daPos = mail.indexOf(".@");
		String lastChar = mail.substring(len - 1, len);
		String chkStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "1234567890-_@.";
		if ((aPos <= 0) || (aPos == len - 1) || (dPos <= 0)
				|| (dPos == len - 1) || (adPos > 0) || (daPos > 0)
				|| (lastChar.equals("@")) || (lastChar.equals("."))
				|| (aaPos > 0) || (ddPos > 0))
		{
			return false;
		}
		if (mail.indexOf("@", aPos + 1) > 0) {
			return false;
		}
		while (aPos > dPos) {
			dPos = mail.indexOf(".", dPos + 1);
			if (dPos < 0) {
				return false;
			}
		}
		for (i = 0; i < len; i++) {
			if (chkStr.indexOf(mail.charAt(i)) < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * キャラクターを削除する。
	 *
	 * @param str 文字列
	 * @param rc 削除される
	 * @return String 結果
	 */
	public static String removeChar(String str, String rc) {
		String tmpStr = str;
		if (tmpStr == null) {
			return null;
		}
		int i = tmpStr.indexOf(rc);
		while (i >= 0) {
			tmpStr = tmpStr.substring(0, i)
					+ tmpStr.substring(i + 1, tmpStr.length());
			i = tmpStr.indexOf(rc);
		}
		return tmpStr;
	}

	/**
	 * 電話をチェック
	 *
	 * @param phone 電話
	 * @return boolean チェック結果
	 */
	public static boolean chkPhone(String phone) {
		String tmpPhone = phone;
		if (tmpPhone == null || tmpPhone.trim().equals(EMPTY)) {
			return false;
		}
		int i = tmpPhone.indexOf("--");
		int len = tmpPhone.length();
		if (i >= 0) {
			return false;
		}
		i = tmpPhone.indexOf("-");
		if ((i == 0) || (i == len - 1)) {
			return false;
		} else if (i > 0) {
			i = tmpPhone.lastIndexOf("-");
			if (i == len - 1) {
				return false;
			}
			tmpPhone = removeChar(tmpPhone, "-");
		}
		if (tmpPhone.indexOf("##") >= 0 || tmpPhone.indexOf("#") == 0
				|| tmpPhone.lastIndexOf("#") == tmpPhone.length() - 1)
		{
			return false;
		}
		tmpPhone = removeChar(tmpPhone, "#");
		if (!isNumber(tmpPhone)) {
			return false;
		}
		return true;
	}

	/**
	 * 期日フォマート
	 *
	 * @param sDate 期日
	 * @return String 結果
	 */
	public static String formatDate(String sDate) {
		return formatDate(sDate, null, null);
	}

	/**
	 * 期日フォマート
	 *
	 * @param sDataLongDate 期日
	 * @param sFmtTo パラメータ
	 * @return String 結果
	 */
	public static String formatDataBaseDate(String sDataLongDate, String sFmtTo) {
		String sFormatTo = sFmtTo;
		if (null == sFormatTo) {
			sFormatTo = DATE_FORMAT_YYYYMMDD_2;
		}
		final DateFormat format = new SimpleDateFormat(sFormatTo);
		return format.format(new Date(Long.parseLong(sDataLongDate)));
	}

	/**
	 * 期日フォマート
	 *
	 * @param sDataDate 期日
	 * @param sFmtTo パラメータ
	 * @return String 結果
	 */
	public static String formatDataBaseDate(Date sDataDate, String sFmtTo) {
		String sFormatTo = sFmtTo;
		if (null == sFormatTo) {
			sFormatTo = DATE_FORMAT_YYYYMMDD_2;
		}
		final DateFormat format = new SimpleDateFormat(sFormatTo);
		return format.format(sDataDate);
	}
	
	/**
	 * 期日フォマート
	 *
	 * @param sDate 期日
	 * @param sFmtTo パラメータ
	 * @return String 結果
	 */
	public static String formatDate(String sDate, String sFmtTo) {
		return formatDate(sDate, null, sFmtTo);
	}

	/**
	 * 期日フォマート
	 *
	 * @param sDate 期日
	 * @param sFmtFrom パラメータ
	 * @param sFmtTo パラメータ
	 * @return String 結果
	 */
	public static String formatDate(String sDate, String sFmtFrom, String sFmtTo) {
		SimpleDateFormat sdfFrom = null;
		SimpleDateFormat sdfTo = null;
		java.util.Date dt = null;
		int nLen = 0;
		if (sDate == null) {
			return sDate;
		}
		try {
			nLen = sDate.length();
			String tmpFmtFrom = sFmtFrom;
			if (tmpFmtFrom == null) {
				if (nLen == 8) {
					tmpFmtFrom = DATE_FORMAT_YYYYMMDD_1;
				} else if (nLen == 10) {
					tmpFmtFrom = DATE_FORMAT_YYYYMMDD_2;
				}
			}
			String tmpFmtTo = sFmtTo;
			if (tmpFmtTo == null) {
				if (nLen == 8) {
					tmpFmtTo = DATE_FORMAT_YYYYMMDD_2;
				} else if (nLen == 10) {
					tmpFmtTo = DATE_FORMAT_YYYYMMDD_1;
				}
			}
			sdfFrom = new SimpleDateFormat(tmpFmtFrom);
			dt = sdfFrom.parse(sDate);
			sdfTo = new SimpleDateFormat(tmpFmtTo);
			return sdfTo.format(dt);
		} catch (Exception e) {
			return sDate;
		}
	}

	/**
	 * 期日を比較する。
	 *
	 * @param date 期日１
	 * @param dt2 期日２
	 * @return int 比較する結果
	 */
	public static int compareDate(String date, java.util.Date dt2) {
		SimpleDateFormat sdf = null;
		SimpleDateFormat sdfNormalized = new SimpleDateFormat(
				DATE_FORMAT_YYYYMMDD_1);
		String sFmt = EMPTY;
		java.util.Date dt1 = null;
		int nLen = 0;
		String tmpDate = date;
		if (tmpDate == null || dt2 == null) {
			return 0;
		}
		try {
			if (tmpDate.length() == 6) {
				tmpDate = tmpDate + "01";
			}
			nLen = tmpDate.length();
			if (nLen == 8) {
				sFmt = DATE_FORMAT_YYYYMMDD_1;
			} else if (nLen == 10) {
				if (tmpDate.contains("/")) {
					sFmt = DATE_FORMAT_YYYYMMDD_2;
				} else if (tmpDate.contains("-")) {
					sFmt = DATE_FORMAT_YYYYMMDD_3;
				}
			}
			sdf = new SimpleDateFormat(sFmt);
			dt1 = sdf.parse(tmpDate);
			String sDt1 = sdfNormalized.format(dt1);
			String sDt2 = sdfNormalized.format(dt2);
			return sDt1.compareTo(sDt2);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 期日を比較する。
	 *
	 * @param sDate1 期日１
	 * @param date 期日２
	 * @return int 比較する結果
	 */
	public static int compareDate(String sDate1, String date) {
		SimpleDateFormat sdf = null;
		String sFmt = EMPTY;
		java.util.Date dt2 = null;
		int nLen = 0;
		String tmpDate = date;
		if (sDate1 == null || tmpDate == null) {
			return 0;
		}
		try {
			if (tmpDate.length() == 6) {
				tmpDate = tmpDate + "01";
			}
			nLen = tmpDate.length();
			if (nLen == 8) {
				sFmt = DATE_FORMAT_YYYYMMDD_1;
			} else if (nLen == 10) {
				if (tmpDate.contains("/")) {
					sFmt = DATE_FORMAT_YYYYMMDD_2;
				} else if (tmpDate.contains("-")) {
					sFmt = DATE_FORMAT_YYYYMMDD_3;
				}
			}
			sdf = new SimpleDateFormat(sFmt);
			dt2 = sdf.parse(tmpDate);
			return compareDate(sDate1, dt2);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 今日を判断する。
	 *
	 * @param sDate 期日
	 * @return boolean 判断する結果
	 */
	public static boolean isToday(String sDate) {
		return compareDate(sDate, new java.util.Date()) == 0;
	}

	/**
	 * 今日前に判断
	 *
	 * @param sDate 期日
	 * @return boolean 判断する結果
	 */
	public static boolean beforeToday(String sDate) {
		return compareDate(sDate, new Date()) < 0;
	}

	/**
	 * 今日後で判断
	 *
	 * @param sDate 期日
	 * @return boolean 判断する結果
	 */
	public static boolean afterToday(String sDate) {
		return compareDate(sDate, new Date()) > 0;
	}

	/**
	 * スペースを判断する。
	 *
	 * @param c キャラクター
	 * @return boolean 結果
	 */
	public static boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == C_KANJI_SPACE;
	}

	/**
	 * スペースを判断する。
	 *
	 * @param s 文字列
	 * @return boolean 結果
	 */
	public static boolean isSpace(String s) {
		if (s == null) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!isSpace(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * スペースを削除する。
	 * 
	 * @param s 文字列
	 * @return String 結果
	 */
	public static String trim(String s) {
		if (s == null) {
			return null;
		}
		int begin;
		int end;
		for (begin = 0; (begin < s.length()) && isSpace(s.charAt(begin));) {
			begin++;
		}
		for (end = s.length() - 1; (end >= 0) && isSpace(s.charAt(end));) {
			end--;
		}
		if (end < begin) {
			return EMPTY;
		}
		return s.substring(begin, end + 1);
	}

	/**
	 * Convert a string like "243,434,343" to long like 243434343
	 *
	 * @param input 入力パラメータ
	 * @return long 結果
	 */
	public static long unFormatNum(String input) {
		if (input == null || input.equals(EMPTY)) {
			return 0;
		}
		String tmp = removeChar(input, ",");
		if (isNumber(tmp)) {
			long output;
			try {
				output = java.lang.Long.parseLong(tmp);
			} catch (Exception e) {
				return -1;
			}
			return output;
		} else {
			return -1;
		}
	}

	/**
	 * Convert a long number like 123456789 to a format String "123,456,789"
	 *
	 * @param input 入力パラメータ
	 * @return String 結果
	 */
	public static String formatNum(int input) {
		return formatNum(java.lang.String.valueOf(input));
	}
	
	/**
	 * Convert a long number of String like "123456789" to a format String "123,456,789"
	 *
	 * @param str 入力パラメータ
	 * @return String 結果
	 */
	public static String formatNumberOfString(String str) {
		StringBuilder result = new StringBuilder();
		String s = String.valueOf(str);
		String suffix = EMPTY;
		if (s.indexOf(".") != -1) {
			suffix = s.substring(str.indexOf("."));
			s = s.substring(0, str.indexOf("."));
		}
		
		int j = 0;
		int k = s.length();
		for (int i = k - 1; i >= 0; i--) {
			if ((j + 1) % NUMBER_3 == 0 && j + 1 < k) {
				result.append(s.charAt(i) + ",");
			} else {
				result.append(s.charAt(i));
			}
			j++;
		}
		result = result.reverse();
		return result + suffix;
	}
	
	/**
	 * Convert a long number of String like "-123456789" to a format String "-123,456,789"
	 *
	 * @param str 入力パラメータ
	 * @return String 結果
	 */
	public static String formatNumberOfStringAll(String str) {
		String ret = str;
		if ('-' == str.charAt(0)) {
			ret = "-" + formatNumberOfString(str.substring(1));
		} else {
			ret = formatNumberOfString(str);
		}
		return ret;
	}

	/**
	 * 数をフォマートする。
	 *
	 * @param input 入力パラメータ
	 * @return String 結果
	 */
	public static String formatNum(long input) {
		return formatNum(java.lang.String.valueOf(input));
	}

	/**
	 * 数をフォマートする。
	 *
	 * @param input 入力数
	 * @return String 結果
	 */
	public static String formatNum(String input) {
		if (input == null) {
			return null;
		}
		if (input.trim().length() == 0) {
			return null;
		}
		String tmp = EMPTY;
		if (input.startsWith(STR_MINUS)) {
			tmp = input.substring(1);
		} else {
			tmp = input;
		}
		String output = EMPTY;
		int len = tmp.length();
		int i = len;
		while (i > 0) {
			if (i < 3) {
				output = tmp.substring(0, i) + output;
				break;
			} else {
				i = i - 3;
				output = tmp.substring(i, i + 3) + output;
				if (i > 0) {
					output = "," + output;
				}
			}
		}
		if (input.startsWith(STR_MINUS)) {
			output = STR_MINUS + output;
		}
		return output;
	}

	/**
	 * 数をフォマートする。
	 * <pre>
	 * roundingMode 捨入モードの可能値：
	 *    BigDecimal.ROUND_CEILING
	 *    BigDecimal.ROUND_UP
	 *    BigDecimal.ROUND_DOWN
	 *    BigDecimal.ROUND_FLOOR
	 *    BigDecimal.ROUND_HALF_DOWN
	 *    BigDecimal.ROUND_HALF_EVEN
	 *    BigDecimal.ROUND_HALF_UP
	 *    BigDecimal.ROUND_UNNECESSARY
	 * endWithZero:{input}の小数点以下位数が{precision}より小さい時、有効
	 * </pre>
	 * @param input 入力数
	 * @param argPprecision 精度
	 * @param roundingMode 捨入モード
	 * @param endWithZero true:0を末尾に填充する/false:0を末尾に填充しない
	 * @return フォマートした数
	 */
	public static String formatNumWithScale(final double input, final int argPprecision,
			final int roundingMode, final boolean endWithZero) {
		int precision = argPprecision;
		if (precision < 0) {
			precision = 0;
		}
		DecimalFormat df = getDecimalFormat(precision, endWithZero);
		if (roundingMode == BigDecimal.ROUND_HALF_UP) {
			return df.format(input);
		} else {
			BigDecimal b = new BigDecimal(input);
			return df.format(b.setScale(precision, roundingMode));
		}
	}
	/**
	 * 数をフォマートする。
	 * <pre>
	 * roundingMode 捨入モードの可能値：
	 *    BigDecimal.ROUND_CEILING
	 *    BigDecimal.ROUND_UP
	 *    BigDecimal.ROUND_DOWN
	 *    BigDecimal.ROUND_FLOOR
	 *    BigDecimal.ROUND_HALF_DOWN
	 *    BigDecimal.ROUND_HALF_EVEN
	 *    BigDecimal.ROUND_HALF_UP
	 *    BigDecimal.ROUND_UNNECESSARY
	 * endWithZero:{input}の小数点以下位数が{precision}より小さい時、有効
	 * </pre>
	 * @param input 入力数
	 * @param argPprecision 精度
	 * @param roundingMode 捨入モード
	 * @param endWithZero true:0を末尾に填充する/false:0を末尾に填充しない
	 * @return フォマートした数
	 */
	public static String formatNumWithScaleNo(final double input, final int argPprecision,
			final int roundingMode, final boolean endWithZero) {
		int precision = argPprecision;
		if (precision < 0) {
			precision = 0;
		}
		DecimalFormat df = getDecimalFormatNo(precision, endWithZero);
		if (roundingMode == BigDecimal.ROUND_HALF_UP) {
			df.setRoundingMode(RoundingMode.HALF_UP);
			return df.format(input);
		} else {
			BigDecimal b = new BigDecimal(input);
			return df.format(b.setScale(precision, roundingMode));
		}
	}

	/**
	 * 数をフォマートする。
	 * <pre>
	 *  roundingMode 捨入モードの可能値：formatNumWithScale(double input,...)を参考してください
	 *  endWithZero:{input}の小数点以下位数が{precision}より小さい時、有効
	 * </pre>
	 * @param input 入力数
	 * @param argPprecision 精度
	 * @param roundingMode 捨入モード
	 * @param endWithZero true:0を末尾に填充する/false:0を末尾に填充しない
	 * @return フォマートした数
	 */
	public static String formatNumWithScale(final BigDecimal input, final int argPprecision,
			final int roundingMode, final boolean endWithZero) {
		int precision = argPprecision;
		if (precision < 0) {
			precision = 0;
		}
		DecimalFormat df = getDecimalFormat(precision, endWithZero);
		return df.format(input.setScale(precision, roundingMode));
	}

	/**
	 * 数をフォマートする。
	 * <pre>
	 *  roundingMode 捨入モードの可能値：formatNumWithScale(double input,...)を参考してください
	 *  endWithZero:{input}の小数点以下位数が{precision}より小さい時、有効
	 * </pre>
	 * @param input 入力数
	 * @param argPprecision 精度
	 * @param roundingMode 捨入モード
	 * @param endWithZero true:0を末尾に填充する/false:0を末尾に填充しない
	 * @return フォマートした数
	 */
	public static String formatNumWithScale(final String input, final int argPprecision,
			final int roundingMode, final boolean endWithZero) {
		int precision = argPprecision;
		if (precision < 0) {
			precision = 0;
		}
		DecimalFormat df = getDecimalFormat(precision, endWithZero);
		return df.format(new BigDecimal(input)
				.setScale(precision, roundingMode));
	}

	/**
	 * DecimalFormatを取得
	 * 
	 * @param precision 精度
	 * @param endWithZero 填充フラグ
	 * @return DecimalFormat
	 */
	private static DecimalFormat getDecimalFormat(int precision, boolean endWithZero) {
		DecimalFormat df = new DecimalFormat();
		String style = "#,##0";
		String padStr = EMPTY;
		if (endWithZero) {
			padStr = "0";
		} else {
			padStr = "#";
		}
		if (precision > 0) {
			style = style + ".";
			style = padStrRight(style, padStr, precision);
		}
		df.applyPattern(style);
		return df;
	}
	
	/**
	 * DecimalFormatを取得
	 * 
	 * @param precision 精度
	 * @param endWithZero 填充フラグ
	 * @return DecimalFormat
	 */
	private static DecimalFormat getDecimalFormatNo(int precision, boolean endWithZero) {
		DecimalFormat df = new DecimalFormat();
		String style = "###0";
		String padStr = EMPTY;
		if (endWithZero) {
			padStr = "0";
		} else {
			padStr = "#";
		}
		if (precision > 0) {
			style = style + ".";
			style = padStrRight(style, padStr, precision);
		}
		df.applyPattern(style);
		return df;
	}

	/**
	 * 半角数値判定
	 * @param str チェックする文字列
	 * @return チェック結果(戻り値： true:半角数値を完全である、false:半角数値よりも外の数値を含有する )
	 */
	public static boolean isHanNumCheck(final String str) {
		boolean checkResult = true;
		char tmpChar;
		if (str != null && !EMPTY.equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				tmpChar = str.charAt(i);
				if (!isHanNum(tmpChar)) {
					checkResult = false;
					break;
				}
			}
		}
		return checkResult;
	}

	/**
	 * 单个半角数値かをチェックする
	 * @param tmpChar チェック文字
	 * @return チェック結果
	 */
	public static boolean isHanNum(final char tmpChar) {
		for (int i = 0; i < HANKAKU_5.length; i++) {
			if (tmpChar == HANKAKU_5[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 全角英字は半角英字に変える。
	 *
	 * @param str 文字列
	 * @return String 結果
	 */
	public static String zenEngNum2HanEngNum(String str) {
		if (isNullString(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < ZENKAKU_3.length; j++) {
					if (c == ZENKAKU_3[j]) {
						break;
					}
				}
				if (j < ZENKAKU_3.length) {
					res.append(HANKAKU_3[j]);
					continue;
				}				
				res.append(c);
			}
			return res.toString();
		}
	}
	/**
	 * 半角英数判定
	 * @param str チェックする文字列
	 * @return チェック結果(戻り値： true:半角英数を完全である、false:半角英数よりも外の数値を含有する )
	 */
	public static boolean isHanNumOrHanEnCheck(final String str) {
		boolean checkResult = true;
		char tmpChar;
		if (str != null && !EMPTY.equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				tmpChar = str.charAt(i);
				if (!isHanNumOrHanEn(tmpChar)) {
					checkResult = false;
					break;
				}
			}
		}
		return checkResult;
	}

	/**
	 * 单半角英数かをチェックする
	 * @param tmpChar チェック文字
	 * @return チェック結果
	 */
	private static boolean isHanNumOrHanEn(final char tmpChar) {
		for (int i = 0; i < HANKAKU_3.length; i++) {
			if (tmpChar == HANKAKU_3[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * NULLを判断する。
	 *
	 * @param str パラメータ
	 * @return boolean 結果
	 */
	public static boolean isNullString(final String str) {
		return str == null || str.trim().equals(EMPTY);
	}

	/**
	 * NULLを判断する。
	 *
	 * @param str パラメータ
	 * @return boolean 結果
	 */
	public static boolean isNotEmptyString(final String str) {
		return !isNullString(str);
	}

	/**
	 * NULLを判断する。
	 *
	 * @param obj パラメータ
	 * @return boolean 結果
	 */
	public static boolean isNotEmptyObject(final Object obj) {
		return obj != null && !isNullString(String.valueOf(obj));
	}
	
	/**
	 * リストNULLを判断する。
	 *
	 * @param lst パラメータ
	 * @return boolean 結果
	 */
	public static boolean isEmptyList(final List<?> lst) {
		return lst == null || lst.isEmpty() 
				|| (lst.size() == 1 && lst.get(0) == null);
	}

	/**
	 * リストNULLを判断する。
	 *
	 * @param lst パラメータ
	 * @return boolean 結果
	 */
	public static boolean isNotEmptyList(final List<?> lst) {
		return !isEmptyList(lst);
	}

	/**
	 * バイト長さ取得
	 *
	 * @param newStr 文字列
	 * @return int バイト長さ
	 */
	public static int length(String newStr) {
		if (newStr == null) {
			return 0;
		}
		return newStr.getBytes().length;
	}

	/**
	 * 文字列０は前に設定
	 *
	 * @param str パラメータ
	 * @param len 長さ
	 * @return String 結果
	 */
	public static String padZeroLeft(String str, int len) {
		if (str == null || str.equals(EMPTY)) {
			return EMPTY;
		}
		if (str.length() >= len) {
			return str;
		}
		StringBuilder sb = new StringBuilder(EMPTY);
		for (int i = 0; i < len - str.length(); i++) {
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}

	/**
	 * 文字列０は前に設定
	 *
	 * @param intValue パラメータ
	 * @param len 長さ
	 * @return String 結果
	 */
	public static String padZeroLeft(int intValue, int len) {
		return padZeroLeft(String.valueOf(intValue), len);
	}

	/**
	 * 文字列０は前に設定
	 *
	 * @param longValue パラメータ
	 * @param len 長さ
	 * @return String 結果
	 */
	public static String padZeroLeft(long longValue, int len) {
		return padZeroLeft(String.valueOf(longValue), len);
	}

	/**
	 * 前にゼロ削除
	 *
	 * @param str 文字列
	 * @return String 結果
	 */
	public static String trimZeroLeft(String str) {
		if (str == null) {
			return EMPTY;
		}
		int i = 0;
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '0') {
				break;
			}
		}
		return str.substring(i);
	}

	/**
	 * 指定長い文字列取得
	 *
	 * @param str 文字列、
	 * @param len 長さ
	 * @return String 文字列
	 */
	public static String getSubString(String str, int len) {
		if (str == null) {
			return EMPTY;
		}
		if (len <= 0) {
			return EMPTY;
		}
		if (str.getBytes().length < len) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		sb.setLength(len);
		while (sb.toString().getBytes().length > len) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * アレイのインデックス
	 *
	 * @param c キャレクタ
	 * @param aC キャレクタアレイ
	 * @return int 結果
	 */
	public static int indexInArr(char c, char[] aC) {
		int i;
		int j = aC.length;
		int iRet = -1;
		for (i = 0; i < j; i++) {
			if (c == aC[i]) {
				iRet = i;
				break;
			}
		}
		return iRet;
	}

	/**
	 * 判定用文字列は、指定文字配列に存在するかどうかの判定を行う
	 *
	 * @param value 判定用文字列
	 * @param str 指定文字配列
	 * @return boolean 存在するかどうかフラグ
	 */
	public static boolean isExist(String str, String value) {
		String tmpStr = nvl(str);
		String tmpValue = nvl(value);
		if (tmpStr.length() < tmpValue.length()) {
			return false;
		}
		for (int i = 0; i < tmpStr.length(); i++) {
			if (tmpValue.substring(0, 1).equals(tmpStr.substring(i, i + 1))) {
				if (tmpStr.length() >= i + tmpValue.length()) {
					String tempStr = tmpStr.substring(i, i + tmpValue.length());
					if (tmpValue.equals(tempStr)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * NULLまたは半角ブランクかどうかを判断する
	 *
	 * @param argStr ストリング
	 * @return ブランクフラグ
	 */
	public static boolean isNullOrHaifuBlank(String argStr) {
		return argStr == null || argStr.trim().length() < 1;
	}

	/**
	 * NULLまたはブランクかどうかを判断する
	 *
	 * @param argStr ストリング
	 * @return ブランクフラグ
	 */
	public static boolean isNullOrBlank(String argStr) {
		return argStr == null || isSpace(argStr);
	}

	/**
	 * NULLをブランクに変換する
	 *
	 * @param argStr ストリング
	 * @return ブランクフラグ
	 */
	public static String changeNullToString(String argStr) {
		String result = EMPTY;
		if (argStr != null) {
			result = argStr;
		}
		return result;
	}

	/**
	 * NULLまたはブランクかどうかを判断する
	 *
	 * @param argStr ストリング
	 * @return ブランクフラグ
	 */
	public static boolean isNullOrBlankOrNbsp(String argStr) {
		return argStr == null || isSpace(argStr) || "&nbsp;".equals(argStr);
	}

	/**
	 * 郵便番号をチェックする
	 * 
	 * @param newPostCode
	 *            郵便番号文字列
	 * @return true, false
	 */
	public static boolean isPostCode(String newPostCode) {
		if (newPostCode == null) {
			return false;
		}
		RE r;
		try {
			r = new RE("^[0-9]{3}-[0-9]{4}$");
			return r.match(newPostCode);
		} catch (Exception re) {
			re.printStackTrace();
		}
		return false;
	}

	/**
	 * 数字は郵便番号にConvert
	 * 
	 * @param newCode 0009999
	 * @return 000-9999
	 * 
	 */
	public static String toPostCode(String newCode) {
		String symbol = "-";
		if (isPostCode(newCode)) {
			return newCode;
		}
		if (newCode == null || !isNumber(newCode) || newCode.length() != 7) {
			return EMPTY;
		} else {
			return newCode.substring(0, 3) + symbol + newCode.substring(3, 7);
		}
	}

	/**
	 * 電話番号をチェックする
	 * 
	 * @param newTel
	 *            電話番号文字列
	 * @return true, false
	 */
	public static boolean isTelNumber(String newTel) {
		if (newTel == null) {
			return false;
		}
		RE r;
		try {
			r = new RE("^[0-9]+[-|0-9][0-9]+[-|0-9][0-9]+$");
			return r.match(newTel);
		} catch (Exception re) {
			re.printStackTrace();
		}
		return false;
	}

	/**
	 * 郵便番号をチェックする
	 * 
	 * @param newEmail
	 *            郵便番号文字列
	 * @return true, false
	 */
	public static boolean isEmail(String newEmail) {
		if (newEmail == null) {
			return false;
		}
		RE r;
		try {
			r = new RE(
					"^[a-z|A-Z|0-9|\\.|\\-|\\_]+@[a-z|A-Z|0-9|\\.|\\-|\\_]+$");
			return r.match(newEmail);
		} catch (Exception re) {
			re.printStackTrace();
		}
		return false;
	}

	/**
	 * 電話番号半角ハイフン削除
	 * 
	 * @param telNo 電話番号
	 * @return 結果
	 */
	public static String parseTelNo(String telNo) {
		if (telNo == null) {
			return EMPTY;
		}
		StringBuilder newString = new StringBuilder(EMPTY);
		for (int i = 0; i < telNo.length(); i++) {
			if (telNo.charAt(i) != '-') {
				newString.append(telNo.charAt(i));
			}
		}
		return newString.toString();
	}

	/**
	 * 整数を固定長文字列に変更する
	 * 
	 * @param iToParse
	 *            変更前の整数
	 * @param len
	 *            固定長
	 * @return 変更後の文字列
	 */
	public static String parseString(int iToParse, int len) {
		String str = Integer.toString(iToParse);
		if (str.length() >= len) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		sb.setLength(0);
		for (int i = 0; i < len - str.length(); i++) {
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}

	/**
	 * 日期を取得する
	 *
	 * @param date 日期
	 * @return String 日期
	 * @exception Exception
	 */
	public static String convertDate(String date) {
		String tmpDate = date;
		if (tmpDate == null
				|| (tmpDate.length() != 4 && tmpDate.length() != 6 && tmpDate
						.length() != 8))
		{
			return EMPTY;
		} else {
			if (tmpDate.length() == 8) {
				tmpDate = tmpDate.substring(0, 4) + "/"
						+ tmpDate.substring(4, 6) + "/" + tmpDate.substring(6);
			} else if (tmpDate.length() == 6) {
				tmpDate = tmpDate.substring(0, 4) + "/" + tmpDate.substring(4);
			}
		}
		return tmpDate;
	}

	/**
	 * 文字列を日付になる
	 * 
	 * @param strDate 日付文字列
	 * @param strFormat 日付フォーマット
	 * @return Date 日付
	 */
	public static Date changeStrToDate(String strDate, String strFormat) {
		if (strDate == null) {
			return new Date();
		}
		SimpleDateFormat format = new SimpleDateFormat(strFormat);
		Date date;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}
	
	/**
	 * 文字列を日付になる(空白の場合、そのままNULLにする)報告書メンテナンス用
	 * 
	 * @param strDate 日付文字列
	 * @param strFormat 日付フォーマット
	 * @return Date 日付
	 */
	public static Date changeStrToDateGC02010901(String strDate, String strFormat) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(strFormat);
		Date date;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * 日付を文字列になる
	 * 
	 * @param date 日付
	 * @param strFormat 日付フォーマット
	 * @return strDate 日付文字列
	 */
	public static String changeDateToStr(Date date, String strFormat) {
		SimpleDateFormat format = new SimpleDateFormat(strFormat);
		return format.format(date);
	}

	/**
	 * 文字列をlongに変換
	 * 
	 * @param strLong 変換前文字列
	 * @param defaultLong longのdefault値
	 * @return long 変換後long
	 */
	public static long changeStrToLong(String strLong, long defaultLong) {
		long l = defaultLong;
		if (isNumber(strLong)) {
			l = Long.parseLong(strLong);
		}
		return l;
	}

	/**
	 * Longを文字列に変換
	 * 
	 * @param l 変換前Long
	 * @return String 変換後文字列
	 */
	public static String changeLongToStr(Long l) {
		if (l == null) {
			return null;
		}
		return String.valueOf(l);
	}

	/**
	 * 文字列をintに変換
	 * 
	 * @param strInt 変換前文字列
	 * @param defaultInt intのdefault値
	 * @return int 変換後int
	 */
	public static Integer changeStrToInt(String strInt, Integer defaultInt) {
		String result = strInt;
		Integer i = defaultInt;
		if (result == null) {
			return i;
		} else {
			result = result.trim();
		}
		if (isNumber(result.replace("+", EMPTY).replace("-", EMPTY))) {
			i = Integer.parseInt(result);
		}
		return i;
	}

	/**
	 * 文字列をShortに変換
	 * 
	 * @param strShort 変換前文字列
	 * @param defaultShort Shortのdefault値
	 * @return Short 変換後Short
	 */
	public static Short changeStrToShort(String strShort, Short defaultShort) {
		Short i = defaultShort;
		if (strShort != null 
				&& isNumber(strShort.replace("+", EMPTY).replace("-", EMPTY))) 
		{
			i = Short.parseShort(strShort);
		}
		return i;
	}

	/**
	 * 文字列をLongに変換
	 * 
	 * @param strLong 変換前文字列
	 * @param defaultLong Longのdefault値
	 * @return Long 変換後Long
	 */
	public static Long changeStrToLong(String strLong, Long defaultLong) {
		Long i = defaultLong;
		if (isNumber(strLong)) {
			i = Long.parseLong(strLong);
		}
		return i;
	}

	/**
	 * Integerを文字列に変換
	 * 
	 * @param i 変換前Integer
	 * @return String 変換後文字列
	 */
	public static String changeIntToStr(Integer i) {
		if (i == null) {
			return null;
		}
		return String.valueOf(i);
	}

	/**
	 * 文字列をDoubleに変換
	 * 
	 * @param strDouble 変換前文字列
	 * @param defaultDouble doubleのdefault値
	 * @return Double 変換後Double
	 */
	public static Double changeStrToDouble(String strDouble,
			Double defaultDouble) {
		Double i = defaultDouble;
		if (!isNullString(strDouble)) {
			i = Double.parseDouble(strDouble);
		}
		return i;
	}
	
	/**
	 * 文字列をDoubleに変換
	 * 
	 * @param strDouble 変換前文字列
	 * @param defaultDouble doubleのdefault値
	 * @return Double 変換後Double
	 */
	public static Double changeStrToDoubleWithDecimalFormate(String strDouble,
			DecimalFormat defaultDecimalFormat) {
		Double dl = new Double(0d);
		if (!isNullString(strDouble)) {
			String strDl = defaultDecimalFormat.format(Double.valueOf(strDouble));
			dl = Double.valueOf(strDl);
		}
		return dl;
	}

	/**
	 * 文字列をBigDecimalに変換
	 * 
	 * @param strBig 変換前文字列
	 * @param defaultBig BigDecimalのdefault値
	 * @return BigDecimal 変換後BigDecimal
	 */
	public static BigDecimal changeStrToBigDecimal(String strBig,
			BigDecimal defaultBig) {
		BigDecimal i = defaultBig;
		if (!isNullString(strBig)) {
			i = new BigDecimal(strBig);
		}
		return i;
	}

	/**
	 * Doubleを文字列に変換
	 * 
	 * @param d 変換前Double
	 * @param precision 精度
	 * @return String 変換後文字列
	 */
	public static String changeDoubleToStr(Double d, int precision) {
		if (d == null) {
			return null;
		}
		if (d >= 10000000 || d < 0.001) {
			String format = EMPTY;
			if (precision == 0) {
				format = "###0";
			} else {
				format = padStrRight("###0.", "#", precision);
			}
			DecimalFormat df = new DecimalFormat(format);
			return df.format(d);
		}
		return String.valueOf(d);
	}

	/**
	 * 文字列をdouble precision型(日付)に変換
	 * 
	 * @param strDate 変換前文字列
	 * @param format 日付フォーマット
	 * @param defaultDate double precision型(日付)のdefault値
	 * @return double 変換後double precision型(日付)
	 */
	public static double changeStrToDoublePrecision(String strDate,
			String format, double defaultDate) {
		if (strDate == null) {
			return defaultDate;
		}
		double d = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			long time = sdf.parse(strDate).getTime();
			d = time / CHANGE_DATE_USE_NO;
		} catch (ParseException e) {
			return defaultDate;
		}
		return d;
	}

	/**
	 * double precision型(日付)を文字列に変換
	 * 
	 * @param d 変換前double precision型(日付)
	 * @param format 日付フォーマット
	 * @param defaultDate defalut日付
	 * @return String 変換後文字列
	 */
	public static String changeDoublePrecisionToStr(Double d, String format,
			String defaultDate) {
		if (d == null) {
			return defaultDate;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date((long) (d * 1000)));
	}

	/**
	 * 日付の月数差を取得
	 * 
	 * @param d1 開始日付
	 * @param d2 終了日付
	 * @return int 日付の月数差
	 */
	public static int getMonthsDiff(Date d1, Date d2) {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		if (d1.before(d2)) {
			startDate.setTime(d1);
			endDate.setTime(d2);
		} else {
			startDate.setTime(d2);
			endDate.setTime(d1);
		}
		int diffMonth = 0;
		int year1 = startDate.get(Calendar.YEAR);
		int year2 = endDate.get(Calendar.YEAR);
		int month1 = startDate.get(Calendar.MONTH);
		int month2 = endDate.get(Calendar.MONTH);
		if (year1 == year2) {
			diffMonth = month2 - month1;
		} else {
			diffMonth = 12 * (year2 - year1) + month2 - month1;
		}
		return diffMonth;
	}

	/**
	 * 日付の日数差を取得
	 * 
	 * @param d1 開始日付
	 * @param d2 終了日付
	 * @return long 日付の日数差
	 */
	public static long getDaysDiff(Date d1, Date d2) {
		long returnValue = 0;
		// yyyyMMddに変換、時分秒を削除
		String date1 = changeDateToStr(d1, DATE_FORMAT_YYYYMMDD_1);
		String date2 = changeDateToStr(d2, DATE_FORMAT_YYYYMMDD_1);
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		if (d1.before(d2)) {
			startDate.setTime(changeStrToDate(date1, DATE_FORMAT_YYYYMMDD_1));
			endDate.setTime(changeStrToDate(date2, DATE_FORMAT_YYYYMMDD_1));
		} else {
			startDate.setTime(changeStrToDate(date2, DATE_FORMAT_YYYYMMDD_1));
			endDate.setTime(changeStrToDate(date1, DATE_FORMAT_YYYYMMDD_1));
		}
		returnValue = ((endDate.getTimeInMillis()) - (startDate
				.getTimeInMillis())) / (1000 * 24 * 60 * 60);
		return returnValue;
	}

	/**
	 * 文字列の右側に指定数量の追加文字列を追加
	 * 
	 * @param strOld 追加前文字列
	 * @param padStr 追加文字列
	 * @param count 指定数量
	 * @return String 追加後文字列
	 */
	public static String padStrRight(String strOld, String padStr, int count) {
		StringBuilder sb = new StringBuilder();
		sb.append(strOld);
		for (int i = 0; i < count; i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}

	/**
	 * 文字列の左側に指定数量の追加文字列を追加
	 * 
	 * @param strOld 追加前文字列
	 * @param padStr 追加文字列
	 * @param count 指定数量
	 * @return String 追加後文字列
	 */
	public static String padStrLeft(String strOld, String padStr, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(padStr);
		}
		sb.append(strOld);
		return sb.toString();
	}
	
	/**
	 * JSONデータをListに変化する
	 * 
	 * @param json JSONデータ
	 * @return List
	 */
	public static List<Map<String, String>> parseJson(final String json) {
		final List<Map<String, String>> lst = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		// レコード開始のフラグ
		boolean recordStart = false;
		// フィールド開始のフラグ
		boolean fieldStart = false;
		String k = EMPTY;
		String v = EMPTY;
		char c;
		final String unescapeJson = StringEscapeUtils.unescapeJava(json);
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < unescapeJson.length(); i++) {
			c = unescapeJson.charAt(i);
			if ('{' == c && !recordStart) {
				// レコード開始
				recordStart = true;
			} else if ('"' == c) {
				if (fieldStart && unescapeJson.charAt(i + 1) == ':') {
					// key : value
					k = sb.toString();
					sb.delete(0, sb.length());
					fieldStart = false;
				} else if (fieldStart
						&& (unescapeJson.charAt(i + 1) == ',' || unescapeJson.charAt(i + 1) == '}'))
				{
					// フィールド結束
					v = sb.toString();
					sb.delete(0, sb.length());
					map.put(k, v);
					fieldStart = false;
				} else if (!fieldStart) {
					// フィールド開始
					fieldStart = true;
				}
			} else if ('}' == c && !fieldStart) {
				// レコード結束
				lst.add(map);
				map = new HashMap<String, String>();
			} else if ('\\' == c) {
				sb.append(unescapeJson.charAt(i + 1));
				i++;
			} else {
				if (fieldStart) {
					sb.append(unescapeJson.charAt(i));
				}
			}
		}
		return lst;
	}

	/**
	 * JSONデータをListに変化する
	 * 
	 * @param json JSONデータ
	 * @return List
	 */
	public static List<Map<String, String>> parseComplexJson(final String json) {
		final List<Map<String, String>> lst = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		// レコード開始のフラグ
		boolean recordStart = false;
		// フィールド開始のフラグ
		boolean fieldStart = false;
		String k = EMPTY;
		String v = EMPTY;
		char c;
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < json.length(); i++) {
			c = json.charAt(i);
			if ('{' == c && !recordStart) {
				// レコード開始
				recordStart = true;
			} else if ('"' == c) {
				if (fieldStart && json.charAt(i + 1) == ':') {
					// key : value
					k = sb.toString();
					sb.delete(0, sb.length());
					fieldStart = false;
				} else if (fieldStart
						&& (json.charAt(i + 1) == ',' || json.charAt(i + 1) == '}'))
				{
					// フィールド結束
					v = sb.toString();
					sb.delete(0, sb.length());
					map.put(k, v);
					fieldStart = false;
				} else if (!fieldStart) {
					// フィールド開始
					fieldStart = true;
				}
			} else if ('}' == c && !fieldStart) {
				// レコード結束
				recordStart = false;
				lst.add(map);
				map = new HashMap<String, String>();
				
			} else {
				if (fieldStart && '\\' != c) {
					sb.append(json.charAt(i));
				}
			}
		}
		return lst;
	}

	/**
	 * paser Data
	 * @param s param
	 * @return List
	 */
	public static List<Map<String, Object>> paserString(final String s) {
		final String[] sb = s.split("\\}\\,\\{");
		final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (String ls : sb) {
			map = new HashMap<String, Object>();
			ls = ls.replaceAll("\"", EMPTY).replaceAll("\\}", EMPTY)
					.replaceAll("\\[", EMPTY).replaceAll("\\]", EMPTY)
					.replaceAll("\\{", EMPTY).replaceAll("\\\\", EMPTY);
			String[] lls = ls.split("\\,");
			for (String llls : lls) {
				String[] llss = llls.split("\\:", -1);
				map.put(llss[0], llss[1]);
			}
			result.add(map);
		}
		return result;
	}

	/**
	 * 年月(YYYY/MM)　－　月数
	 * 
	 * @param ttlMay 年月(YYYY/MM)
	 * @param mnthNo 月数
	 * @return result 年月(YYYY/MM)
	 */
	public static String getMayMonthDiff(final String ttlMay,
			final String mnthNo) {
		String resultMay = ttlMay;
		if (StringUtils.hasText(ttlMay) && StringUtils.hasText(mnthNo)) {
			final Integer totalMonth = Integer.valueOf(ttlMay.substring(0,
					NUMBER_4))
					* NUMBER_12
					+ Integer.valueOf(ttlMay.substring(NUMBER_5, NUMBER_7))
					- Integer.valueOf(mnthNo);
			final String year = String.valueOf((totalMonth - 1) / NUMBER_12);
			final String month = String.valueOf((totalMonth - 1) % NUMBER_12
					+ 1);
			if (month.length() < 2) {
				resultMay = year + "/0" + month;
			} else {
				resultMay = year + "/" + month;
			}
		}
		return resultMay;
	}

	/**
	 * change String to list with length
	 * @param value 
	 * @param perLength 
	 * @return List<String>
	 */
	public static List<String> splitStringWithByteLength(final String value,
			final int perLength) {
		List<String> result = new ArrayList<String>();
		if (value == null) {
			return null;
		} else if (EMPTY.equals(value) || perLength <= 0
				|| (value != null && byteLengthOfString(value) <= perLength))
		{
			result.add(value);
			return result;
		}
		final StringBuilder sb = new StringBuilder();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sb.append(chars[i]);
			if (byteLengthOfString(sb.toString()) == perLength
					|| (byteLengthOfString(sb.toString()) == perLength - 1 
					&& (isZenkaku(chars[i + 1])))
					|| (byteLengthOfString(sb.toString()) <= perLength && i == chars.length - 1))
			{
				result.add(sb.toString());
				sb.delete(0, sb.length());
			}
		}
		return result;
	}

	/**
	 * 計算String の length of byte
	 * @param str 
	 * @return length of byte
	 */
	private static int byteLengthOfString(String str) {
		int byteLength = 0;
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (isZenkaku(chars[i])) {
				byteLength += 2;
			} else {
				byteLength++;
			}
		}
		return byteLength;
	}

	/**
	 * AND検索OR検索チェック
	 * @param str 
	 * @return boolean　TRUEは併用
	 */
	public static boolean checkAndOrSearch(String str) {
		String tempString = str;
		if (isNotEmptyString(str)) {
			tempString = str.trim();
			tempString = tempString.replaceAll("　", HANKAKU_SPACE)
				.replaceAll(SEP_OR_SEARCH, " or ")
				.replaceAll("\\s+", HANKAKU_SPACE);
		}
		boolean ret = false;
		if (isNotEmptyString(tempString)
				&& (tempString.indexOf(" or ") >= 0))
		{
			if (tempString.replaceAll(" or ", EMPTY)
					.indexOf(HANKAKU_SPACE) != -1)
			{
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * YYYY/MM/DD-MM AND DD is illegal
	 * 
	 * @param ttlMay
	 *            date
	 * @param mnthNo
	 *            month
	 * @return サブフォーム用データ
	 */
	public static String getBassdate(final String ttlMay,
			final String mnthNo)
	   {		
		Calendar cl = Calendar.getInstance();
		cl.setTime(changeStrToDate(ttlMay, DATE_FORMAT_YYYYMMDD_2));
		cl.add(Calendar.MONTH, -Integer.parseInt(mnthNo));		
		return changeDateToStr(cl.getTime(), DATE_FORMAT_YYYYMMDD_2);
	}
	
	/**
	 * Stringのtrim and blank to null
	 * 
	 * @param sIn パラメータ
	 * @return String 結果
	 */
	public static String trimStrToNull(String sIn) {
		String result = null;
		if (sIn != null) {
			char[] sInCahr = sIn.toCharArray();
			for (int i = 0; i < sInCahr.length; i++) {
				if (sInCahr[i] == '　' || sInCahr[i] == ' ' || sInCahr[i] == ' ') {
					sInCahr[i] = ' ';
				} else {
					break;
				}
			}
			for (int i = sInCahr.length - 1; i >= 0; i--) {
				if (sInCahr[i] == ' ' || sInCahr[i] == '　' || sInCahr[i] == ' ') {
					sInCahr[i] = ' ';
				} else {
					break;
				}
			}
			if (StringUtil.isNotEmptyString(new String(sInCahr).trim())) {
				result = new String(sInCahr).trim();
			}
		}
		return result;
	}

	/**
	 * オブジェクトをBigIntegerオブジェクトに変換する。<br>
	 * BigIntegerがNULLの場合、nullを返す。<br>
	 * @param bd bd
	 * @return BigDecimalオブジェクト
	 */
	public static BigInteger toBigInteger(final Object bd) {
		return (bd == null || EMPTY.equals(bd)) ? null : new BigInteger(bd.toString());
	}

	/**
	 * BigIntegerのcompare
	 * 
	 * @param bd1
	 *            数値１
	 * @param bd2
	 *            数値２
	 * @return -1:bd1<bd2 0:bd1=bd2 1:bd1>bd2
	 */
	public static Integer compareBigInteger(BigInteger bd1, BigInteger bd2) {
		if (bd1 == null && bd2 == null) {
			return 0;
		}
		if (bd1 == null) {
			return -1;
		}
		if (bd2 == null) {
			return 1;
		}
		return bd1.compareTo(bd2);
	}

	/**
	 * BigDecimalのcompare
	 * 
	 * @param bd1
	 *            数値１
	 * @param bd2
	 *            数値２
	 * @return -1:bd1<bd2 0:bd1=bd2 1:bd1>bd2
	 */
	public static Integer compareBigdecimal(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null && bd2 == null) {
			return 0;
		}
		if (bd1 == null) {
			return -1;
		}
		if (bd2 == null) {
			return 1;
		}
		return bd1.compareTo(bd2);
	}

	/**
	 * オブジェクトをBigDecimalオブジェクトに変換する。<br>
	 * BigDecimalがNULLの場合、nullを返す。<br>
	 * 
	 * @param bd bd
	 * @return BigDecimalオブジェクト
	 */
	public static BigDecimal toBigDecimal(Object bd) {
		return (bd == null || EMPTY.equals(bd)) ? null
				: new BigDecimal(bd.toString());
	}

	/**
	 * BigDecimalの「＋」<br>
	 * @param a a
	 * @param b b
	 * @return BigDecimal
	 */
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		BigDecimal ret = null;
		if (a == null && b == null) {
			ret = BigDecimal.ZERO;			
		} else if (a == null) {
			ret = b;
		} else if (b == null) {
			ret = a;			
		} else {
			ret = a.add(b);			
		}
		return ret;
	}
	
	/**
	 * 半角片仮名check
	 * @param str 
	 * @return str is all 半角片仮名
	 */
	public static boolean checkHankakuKataKana(final String str) {

		char[] chars = str.toCharArray();
		for (Character chr : chars) {
			if (!hankakuKatakanaList.contains(chr)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 半角　片仮名
	 */
	private static final char[] HANKAKU_KATAKANA_OTHER = {
		'ｱ', 'ｲ', 'ｳ', 'ｴ', 'ｵ',
		'ｶ', 'ｷ', 'ｸ', 'ｹ', 'ｺ',
		'ｻ', 'ｼ', 'ｽ', 'ｾ', 'ｿ',
		'ﾀ', 'ﾁ', 'ﾂ', 'ﾃ', 'ﾄ',
		'ﾅ', 'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ',
		'ﾊ', 'ﾋ', 'ﾌ', 'ﾍ', 'ﾎ',
		'ﾏ', 'ﾐ', 'ﾑ', 'ﾒ', 'ﾓ',
		'ﾔ', 'ﾕ', 'ﾖ',
		'ﾗ', 'ﾘ', 'ﾙ', 'ﾚ', 'ﾛ',
		'ﾜ', 'ｦ', 'ﾝ', 'ｰ',
		'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ',
		'ｬ', 'ｭ', 'ｮ', 'ｯ',
		'1', '2', '3', '4', '5',
		'6', '7', '8', '9', '0',
		'ﾞ', 'ﾟ', '(', ')', ' ' 
	};
	
	/**
	 * HANKAKU_KATAKANA_LIST
	 */
	private static List<Character> hankakuKatakanaList = null;
	static {
		hankakuKatakanaList = new ArrayList<Character>();
		for (Character chr : HANKAKU_KATAKANA_OTHER) {
			hankakuKatakanaList.add(chr);
		}
	}
	
	/* 曖昧検索start  */
	
	/**
	 * 半角　片仮名
	 */
	private static final char[] HANKAKU_KATAKANA_1 = {
		'ｱ', 'ｲ', 'ｳ', 'ｴ', 'ｵ', 'ｶ', 'ｷ', 'ｸ', 'ｹ', 'ｺ', 'ｻ', 'ｼ',
		'ｽ', 'ｾ', 'ｿ', 'ﾀ', 'ﾁ', 'ﾂ', 'ﾃ', 'ﾄ', 'ﾅ', 'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ',
		'ﾊ', 'ﾋ', 'ﾌ', 'ﾍ', 'ﾎ', 'ﾏ', 'ﾐ', 'ﾑ', 'ﾒ', 'ﾓ', 'ﾔ', 'ﾕ', 'ﾖ',
		'ﾗ', 'ﾘ', 'ﾙ', 'ﾚ', 'ﾛ', 'ﾜ', 'ﾝ', 'ｦ', 'ｰ', 'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ',
		'ｮ', 'ｯ'
	};

	
	/**
	 * 全角　片仮名
	 */
	private static final char[] ZENKAKU_KATAKANA_1 = {
		'ア', 'イ', 'ウ', 'エ', 'オ', 'カ', 'キ', 'ク', 'ケ', 'コ', 'サ', 'シ',
		'ス', 'セ', 'ソ', 'タ', 'チ', 'ツ', 'テ', 'ト', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ',
		'ハ', 'ヒ', 'フ', 'ヘ', 'ホ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ヤ', 'ユ', 'ヨ',
		'ラ', 'リ', 'ル', 'レ', 'ロ', 'ワ', 'ン', 'ヲ', 'ー', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ',
		'ョ', 'ッ'
	};
	
	/**
	 * 全角　平仮名
	 */
	private static final char[] ZENKAKU_HIRAKANA_1 = {
		'あ', 'い', 'う', 'え', 'お', 'か', 'き', 'く', 'け', 'こ', 'さ', 'し',
		'す', 'せ', 'そ', 'た', 'ち', 'つ', 'て', 'と', 'な', 'に', 'ぬ', 'ね', 'の',
		'は', 'ひ', 'ふ', 'へ', 'ほ', 'ま', 'み', 'む', 'め', 'も', 'や', 'ゆ', 'よ',
		'ら', 'り', 'る', 'れ', 'ろ', 'わ', 'ん', 'を', 'ー', 'ぁ', 'ぃ', 'ぅ', 'ぇ', 'ぉ', 'ゃ', 'ゅ',
		'ょ', 'っ'
	};
	/**
	 * 全角　平仮名
	 */
	private static final char[] ZENKAKU_HIRAKANA_2 = {'が', 'ぎ', 'ぐ', 'げ', 'ご',
		'ざ', 'じ', 'ず', 'ぜ', 'ぞ', 'だ', 'ぢ', 'づ', 'で', 'ど', 'ば',
		'び', 'ぶ', 'べ', 'ぼ', 'ぱ', 'ぴ', 'ぷ', 'ぺ', 'ぽ'
	};

	/**
	 * 半角　平仮名
	 */
	private static final String[] HANKAKU_KATAKANA_2 = {"ｶﾞ", "ｷﾞ", "ｸﾞ", "ｹﾞ", "ｺﾞ",
			"ｻﾞ", "ｼﾞ", "ｽﾞ", "ｾﾞ", "ｿﾞ", "ﾀﾞ", "ﾁﾞ", "ﾂﾞ", "ﾃﾞ", "ﾄﾞ", "ﾊﾞ",
			"ﾋﾞ", "ﾌﾞ", "ﾍﾞ", "ﾎﾞ", "ﾊﾟ", "ﾋﾟ", "ﾌﾟ", "ﾍﾟ", "ﾎﾟ"};
	
	/**
	 * 全角平仮名
	 */
	private static final char[] ZENKAKU_KATAKANA_2 = {'ガ', 'ギ', 'グ', 'ゲ', 'ゴ',
			'ザ', 'ジ', 'ズ', 'ゼ', 'ゾ', 'ダ', 'ヂ', 'ヅ', 'デ', 'ド', 'バ',
			'ビ', 'ブ', 'ベ', 'ボ', 'パ', 'ピ', 'プ', 'ペ', 'ポ'};
	
	/**
	 * 半角　小　片仮名
	 */
	private static final char[] HANKAKU_KATAKANA_L = {
		'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ', 'ｮ', 'ｯ'
		};
	
	/**
	 * 半角　大　片仮名
	 */
	private static final char[] HANKAKU_KATAKANA_B = {
		'ｱ', 'ｲ', 'ｳ', 'ｴ', 'ｵ', 'ﾔ', 'ﾕ', 'ﾖ', 'ﾂ'
		};

	/**
	 * 全角　小　片仮名
	 */
	private static final char[] ZENKAKU_KATAKANA_L = {
		'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ', 'ョ', 'ッ', 'ヶ', 'ヮ', 'ヵ'
		};
	
	/**
	 * 全角　大　片仮名
	 */
	private static final char[] ZENKAKU_KATAKANA_B = {
		'ア', 'イ', 'ウ', 'エ', 'オ', 'ヤ', 'ユ', 'ヨ', 'ツ', 'ケ', 'ワ', 'カ'
		};
	
	/**
	 * 全角　小　平仮名
	 */
	private static final char[] ZENKAKU_HIRAGATA_L = {
		'ぁ', 'ぃ', 'ぅ', 'ぇ', 'ぉ', 'ゃ', 'ゅ', 'ょ', 'っ', 'ゎ'
		};
	
	/**
	 * 全角　大　平仮名
	 */
	private static final char[] ZENKAKU_HIRAGATA_B = {
		'あ', 'い', 'う', 'え', 'お', 'や', 'ゆ', 'よ', 'つ', 'わ'
		};
	
	/**
	 * 半角英数字記号
	 */
	private static final char[] HANKAKU_NUMBER_FUGOU = {'1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '=', '-', '\\', '|',
			'`', '@', ';', '+', ':', '*', '[', ']',
			'{', '}', ',', '.', '/', '<', '>', '?', '_'};
	
	/**
	 * 全角英数字記号
	 */
	private static final char[] ZENKAKU_NUMBER_FUGOU = {'１', '２', '３', '４', '５', '６', '７',
			'８', '９', '０', '！', '”', '＃', '＄', '％', '＆', '’', '（', '）', '＝', '－', '￥', '｜',
			'‘', '＠', '；', '＋', '：', '＊', '［', '］',
			'｛', '｝', '，', '．', '／', '＜', '＞', '？', '＿'};
	/**
	 * 全角　英字　小文字
	 */
	private static final char[] ZENKAKU_ALPHA_L = {
		'ａ', 'ｂ', 'ｃ', 'ｄ', 'ｅ', 'ｆ', 'ｇ', 'ｈ', 'ｉ', 'ｊ',
		'ｋ', 'ｌ', 'ｍ', 'ｎ', 'ｏ', 'ｐ', 'ｑ', 'ｒ', 'ｓ', 'ｔ', 'ｕ', 'ｖ', 'ｗ',
		'ｘ', 'ｙ', 'ｚ'
	};
	
	/**
	 * 全角　英字　大文字
	 */
	private static final char[] ZENKAKU_ALPHA_B = {
		'Ａ', 'Ｂ', 'Ｃ', 'Ｄ', 'Ｅ', 'Ｆ', 'Ｇ', 'Ｈ', 'Ｉ', 'Ｊ',
		'Ｋ', 'Ｌ', 'Ｍ', 'Ｎ', 'Ｏ', 'Ｐ', 'Ｑ', 'Ｒ', 'Ｓ', 'Ｔ', 'Ｕ', 'Ｖ', 'Ｗ',
		'Ｘ', 'Ｙ', 'Ｚ'
	};
	
	/**
	 * '、'
	 */
	private static final String DOT1 = "、";
	
	/**
	 * '，'
	 */
	private static final String DOT2 = "，";
	
	/**
	 * "ﾞ"
	 */
	private static final String DOT4 = "ﾞ";
	
	/**
	 * ','
	 */
	private static final String DOT3 = ",";
	
	/**
	 * 検索タイプ　半角カナ
	 */
	public static final int SEARCH_TYPE_HANKAKU_KATAKANA = 1;
	
	/**
	 * 検索タイプ　全角カナ
	 */
	public static final int SEARCH_TYPE_ZENKAKU_KATAKANA = 2;
	
	/**
	 * 検索タイプ　かな
	 */
	public static final int SEARCH_TYPE_HIRAKANA = 3;
	
	/**
	 * 検索タイプ　半角英数、半角英字
	 */
	public static final int SEARCH_TYPE_HANKAKU_ALPHA_NUMBER = 4;
	
	/**
	 * 検索タイプ　半角数値
	 */
	public static final int SEARCH_TYPE_HANKAKU_NUMBER = 5;
	
	/**
	 * 検索タイプ　全角英数、全角英字
	 */
	public static final int SEARCH_TYPE_ZENKAKU_ALPHA_NUMBER = 6;
	
	/**
	 * 検索タイプ　全角数値
	 */
	public static final int SEARCH_TYPE_ZENKAKU_NUMBER = 7;
	
	/**
	 * ANDとOR検索の併用
	 */
	public static final String HAS_ERROR = "HAS_ERROR";
	
	/**
	 * AND検索
	 */
	public static final String AND_SEARCH = "AND_SEARCH";
	
	/**
	 * 検索　キー　リスト
	 */
	public static final String SEARCH_LIST = "SEARCH_LIST";

	/**
	 * 曖昧検索 共通方法
	 * @param str 変換しよう文字
	 * @param searchType 検索タイプ
	 * @param excldngChrctr システムコントロールマスタ(0966)に設定された文字
	 * @return 変換した後の文字Map,該当Mapには３ヶキイがあり、<br/>
	 * HAS_ERROR:true　ANDとOR検索を併用する、該当検索は続けられない<br/>
	 * AND_SEARCH:true　AND検索を実装する、false　OR検索を実装する<br/>
	 * SEARCH_LIST: 変換した文字リスト<br/>
	 * searchTypeは<b>「かな」</b>,<b>「全角英数、全角英字」</b>,<b>「全角数値」</b>の時
	 * SEARCH_LIST_DOT: 変換した文字リスト,「,」を「，」「、」に変換するため
	 */
	public static Map<String, Object> aimaiSearch(final String str, 
			final int searchType, final List<String> excldngChrctr) {
		String tempStr = str;
		if (isNotEmptyString(tempStr)) {
			tempStr = tempStr.trim();
			tempStr = tempStr.replaceAll("　", HANKAKU_SPACE) 	//全角スペースは半角スペースへ置き換える
					.replaceAll(SEP_OR_SEARCH, " or ")			// or Or OR oR は or へ置き換える
					.replaceAll("\\s+", HANKAKU_SPACE);			//半角スペースが連なっている場合は１個に置き換える
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put(HAS_ERROR, false);
		// AND検索
		result.put(AND_SEARCH, true);
		// ANDとOR検索の併用は不可とする。併用の検索条件が指定された場合はエラーとする。
		if (checkAndOrSearch(tempStr) || checkEmptyInput(tempStr)) {
			result.put(HAS_ERROR, true);
			return result;
		}
		
		String splitStr = " or ";
		
		// searchKey List
		final List<String> searchKeys = new ArrayList<String>();
		if (!isNullString(tempStr)) {
			if (tempStr.indexOf(" or ") >= 0) {
				// OR検索
				result.put(AND_SEARCH, false);
			} else {
				splitStr = HANKAKU_SPACE;
			}
			
			final String[] keys = tempStr.split(splitStr);
			String changedString = EMPTY;
			for (String temStr : keys) {
				if (isNotEmptyString(temStr)) {
					switch (searchType) {
						case SEARCH_TYPE_HANKAKU_KATAKANA : 
							changedString = hankakukataChange(temStr);
							searchKeys.add(changedString);
							break;
						case SEARCH_TYPE_ZENKAKU_KATAKANA : 
							changedString = zenkakukataChange(temStr, excldngChrctr);
							searchKeys.add(changedString);
							break;
						case SEARCH_TYPE_HIRAKANA : 
							changedString = hirakanaChange(temStr, excldngChrctr);
							searchKeys.add(changedString);
							break;
						case SEARCH_TYPE_HANKAKU_ALPHA_NUMBER : 
							changedString = hankakuA9Change(temStr);
							searchKeys.add(changedString);
							break;
						case SEARCH_TYPE_HANKAKU_NUMBER : 
							changedString = hankaku9Change(temStr);
							searchKeys.add(changedString);
							break;
						case SEARCH_TYPE_ZENKAKU_ALPHA_NUMBER : 
							changedString = zenkakuA9Change(temStr, excldngChrctr);
							searchKeys.add(changedString);
							break;
						case SEARCH_TYPE_ZENKAKU_NUMBER : 
							changedString = zenkaku9Change(temStr);
							searchKeys.add(changedString);
							break;
						default : break;
					}
				}
			}
			result.put(SEARCH_LIST, searchKeys);
		}
		return result;
	}
	
	/**
	 * check　ORまたは空白文字だけかをチェックする。
	 * @param str 
	 * @return true
	 */
	private static boolean checkEmptyInput(final String str) {
		boolean ret = false;
			if (isNullOrBlank(str.replaceAll(SEP_OR_SEARCH, EMPTY)
					.replaceAll(SEP_OR_SEARCH, EMPTY)))
			{
				ret = true;
			}
		return ret;
	}

	/**
	 * 画面設計書(項目定義）タイプ 
	 * <b>半角カナ</b>
	 * 検索
	 * @param str 変換しよう文字
	 * @return 半角カナ 変換した後の文字
	 */
	private static String hankakukataChange(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			return zenkakuhira2hankakukata(
					zenkakukata2hankakukata(
							lkana2bkana(str)));
		}
	}
	
	/**
	 * 画面設計書(項目定義）タイプ 
	 * <b>全角カナ</b>
	 * 検索
	 * @param str 変換しよう文字
	 * @param excldngChrctr システムコントロールマスタ(0966)に設定された文字
	 * @return 変換した後の文字
	 */
	private static String zenkakukataChange(final String str, final List<String> excldngChrctr) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			String temp = hankakukata2zenkakukata(
					lkana2bkana(str));
			if (temp != null && isNotEmptyList(excldngChrctr)) {
				for (String excldStr : excldngChrctr) {
					temp = temp.replaceAll(excldStr, EMPTY);
				}
			}
			return temp;
		}
	}

	/**
	 * 画面設計書(項目定義）タイプ 
	 * <b>かな</b>
	 * 検索 
	 * @param str 変換しよう文字
	 * @param excldngChrctr システムコントロールマスタ(0966)に設定された文字
	 * @return 変換した後の文字
	 */
	private static String hirakanaChange(final String str, List<String> excldngChrctr) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			String temp = lkana2bkana(litteAlpha2BigAlpha(hankakuAlpha2zenkakuAlpha(
					hankakukata2zenkakukata(str))));
			if (temp != null && isNotEmptyList(excldngChrctr)) {
				for (String excldStr : excldngChrctr) {
					temp = temp.replaceAll(excldStr, EMPTY);
				}
			}
			return temp;
		}
	}
	
	/**
	 * 画面設計書(項目定義）タイプ 
	 * <b>半角英数、半角英字</b>
	 * 検索
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String hankakuA9Change(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			return litteAlpha2BigAlpha(zenkakuAlpha2hankakuAlpha(str))
					.replaceAll(DOT1, DOT3).replaceAll(DOT2, DOT3);
		}
	}
	
	/**
	 * 画面設計書(項目定義）タイプ 
	 * <b>半角数値</b>
	 * 検索
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String hankaku9Change(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			return zenkakuAlpha2hankakuAlpha(str)
					.replaceAll(DOT1, DOT3).replaceAll(DOT2, DOT3);
		}
	}
	
	/**
	 * 画面設計書(項目定義）タイプ 
	 * <b>全角英数、全角英字</b>
	 * 検索
	 * @param str 変換しよう文字
	 * @param excldngChrctr システムコントロールマスタ(0966)に設定された文字
	 * 
	 * @return 変換した後の文字
	 */
	private static String zenkakuA9Change(final String str, final List<String> excldngChrctr) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			String temp = litteAlpha2BigAlpha(hankakuAlpha2zenkakuAlpha(str));
			if (temp != null && isNotEmptyList(excldngChrctr)) {
				for (String excldStr : excldngChrctr) {
					temp = temp.replaceAll(excldStr, EMPTY);
				}
			}
			return temp;
		}
	}
	
	/**
	 * 画面設計書(項目定義）タイプ 
	 * <b>全角数値</b>
	 * 検索
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String zenkaku9Change(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			return hankakuAlpha2zenkakuAlpha(str);
		}
	}
	
	/**
	 * 小文字　→　大文字
	 * ッ　→　ツ、　ｯ　→　ﾂ、っ　→　つ
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String lkana2bkana(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < HANKAKU_KATAKANA_L.length; j++) {
					if (c == HANKAKU_KATAKANA_L[j]) {
						break;
					}
				}
				if (j < HANKAKU_KATAKANA_L.length) {
					res.append(HANKAKU_KATAKANA_B[j]);
					continue;
				}
				for (j = 0; j < ZENKAKU_KATAKANA_L.length; j++) {
					if (c == ZENKAKU_KATAKANA_L[j]) {
						break;
					}
				}
				if (j < ZENKAKU_KATAKANA_L.length) {
					res.append(ZENKAKU_KATAKANA_B[j]);
					continue;
				}
				for (j = 0; j < ZENKAKU_HIRAGATA_L.length; j++) {
					if (c == ZENKAKU_HIRAGATA_L[j]) {
						break;
					}
				}
				if (j < ZENKAKU_HIRAGATA_L.length) {
					res.append(ZENKAKU_HIRAGATA_B[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString();
		}
	}
	
	/**
	 * 全角カタカナ　→　半角カタカナ
	 * ア　→　ｱ     ガ→ｶ
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String zenkakukata2hankakukata(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < ZENKAKU_KATAKANA_1.length; j++) {
					if (c == ZENKAKU_KATAKANA_1[j]) {
						break;
					}
				}
				if (j < ZENKAKU_KATAKANA_1.length) {
					res.append(HANKAKU_KATAKANA_1[j]);
					continue;
				}
				for (j = 0; j < ZENKAKU_KATAKANA_2.length; j++) {
					if (c == ZENKAKU_KATAKANA_2[j]) {
						break;
					}
				}
				if (j < ZENKAKU_KATAKANA_2.length) {
					res.append(HANKAKU_KATAKANA_2[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString();
		}
	}

	/**
	 * 全角ひらがな　→　半カタカナ
	 * あ　→　ｱ
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String zenkakuhira2hankakukata(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < ZENKAKU_HIRAKANA_1.length; j++) {
					if (c == ZENKAKU_HIRAKANA_1[j]) {
						break;
					}
				}
				if (j < ZENKAKU_HIRAKANA_1.length) {
					res.append(HANKAKU_KATAKANA_1[j]);
					continue;
				}
				for (j = 0; j < ZENKAKU_HIRAKANA_2.length; j++) {
					if (c == ZENKAKU_HIRAKANA_2[j]) {
						break;
					}
				}
				if (j < ZENKAKU_HIRAKANA_2.length) {
					res.append(HANKAKU_KATAKANA_2[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString();
		}
	}
	
	/**
	 * 半角カタカナ　→　全角カタカナ
	 * ｱ　→　ア
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String hankakukata2zenkakukata(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			String c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = String.valueOf(sb.charAt(i));
				if (DOT4.equals(c)) {
					continue;
				}
				if (i + 1 < len && DOT4.equals(String.valueOf(sb.charAt(i + 1)))) {
					c = c + DOT4;
				}
				for (j = 0; j < HANKAKU_KATAKANA_2.length; j++) {
					if (HANKAKU_KATAKANA_2[j].equals(String.valueOf(c))) {
						break;
					}
				}
				if (j < HANKAKU_KATAKANA_2.length) {
					res.append(ZENKAKU_KATAKANA_2[j]);
					continue;
				}
				for (j = 0; j < HANKAKU_KATAKANA_1.length; j++) {
					if (String.valueOf(HANKAKU_KATAKANA_1[j]).equals(c)) {
						break;
					}
				}
				if (j < HANKAKU_KATAKANA_1.length) {
					res.append(ZENKAKU_KATAKANA_1[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString();
		}
	}
	
	/**
	 * 半角カタカナ　→　全角ひらがな
	 * ｱ　→　あ
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	@SuppressWarnings("unused")
	private static String hankakukata2hira(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < HANKAKU_KATAKANA_1.length; j++) {
					if (c == HANKAKU_KATAKANA_1[j]) {
						break;
					}
				}
				if (j < HANKAKU_KATAKANA_1.length) {
					res.append(ZENKAKU_HIRAKANA_1[j]);
					continue;
				}
				for (j = 0; j < HANKAKU_KATAKANA_2.length; j++) {
					if (HANKAKU_KATAKANA_2[j].equals(String.valueOf(c))) {
						break;
					}
				}
				if (j < HANKAKU_KATAKANA_2.length) {
					res.append(ZENKAKU_HIRAKANA_2[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString();
		}
	}
	
	/**
	 * 半角英数字記号　→　全角英数字記号
	 * #9　→　＃９
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String hankakuAlpha2zenkakuAlpha(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < HANKAKU_NUMBER_FUGOU.length; j++) {
					if (c == HANKAKU_NUMBER_FUGOU[j]) {
						break;
					}
				}
				if (j < HANKAKU_NUMBER_FUGOU.length) {
					res.append(ZENKAKU_NUMBER_FUGOU[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString();
		}
	}

	/**
	 * 全角英数字記号　→　半角英数字記号
	 * ＃９ →　#9　
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String zenkakuAlpha2hankakuAlpha(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < ZENKAKU_NUMBER_FUGOU.length; j++) {
					if (c == ZENKAKU_NUMBER_FUGOU[j]) {
						break;
					}
				}
				if (j < ZENKAKU_NUMBER_FUGOU.length) {
					res.append(HANKAKU_NUMBER_FUGOU[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString();
		}
	}

	/**
	 * 全角アルファベット小文字　→　全角アルファベット大文字
	 * Ａｂｃ → ＡＢＣ
	 * @param str 変換しよう文字
	 * @return 変換した後の文字
	 */
	private static String litteAlpha2BigAlpha(final String str) {
		if (str == null || EMPTY.equals(str)) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			StringBuilder res = new StringBuilder();
			char c;
			int j = 0;
			int len = sb.length();
			for (int i = 0; i < len; i++) {
				c = sb.charAt(i);
				for (j = 0; j < ZENKAKU_ALPHA_L.length; j++) {
					if (c == ZENKAKU_ALPHA_L[j]) {
						break;
					}
				}
				if (j < ZENKAKU_ALPHA_L.length) {
					res.append(ZENKAKU_ALPHA_B[j]);
					continue;
				}
				res.append(c);
			}
			return res.toString().toUpperCase();
		}
	}
	
	/* 曖昧検索end  */
	
	/**
	 * EntityのFieldからMapに変換する
	 * @param o 　Entity
	 * @return 変換した後のMap
	 */
	public static Map<String, Object> convertEntityToMap(Object o) {
		if (o == null) {
			return null;
		}
		final Map<String, Object> mp = new HashMap<String, Object>();
		for (Field field : o.getClass().getDeclaredFields()) {
			try {
				mp.put(field.getName(), field.get(o));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
		return mp;
	}
	
	/**
	 * MapからEntityに変換する
	 * @param mp Map
	 * @param o 　Entity
	 */
	public static void convertMapToEntity(Map<String, Object> mp, Object o) {
		if (mp == null || o == null) {
			return;
		}
		Iterator<Entry<String, Object>> it = mp.entrySet().iterator();
		Entry<String, Object> entry = null;
		while (it.hasNext()) {
			entry = it.next();
			try {
				o.getClass().getDeclaredField(entry.getKey()).set(o, entry.getValue());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return;
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				return;
			} catch (SecurityException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	/**
	 * 文字数のcount
	 * @param srcStr 
	 * @param subStr 
	 * @return int
	 */
	public static int getSubCount(String srcStr, String subStr) {
		int count = 0;
		Matcher m = Pattern.compile(subStr).matcher(srcStr);
		while (m.find()) {
			count++;
		}
		return count;
	} 
	
	/**
	 * Escape した文字列を変換
	 * @param srcStr 
	 * @return String
	 */
	public static String replaceEscapeStr(final String srcStr) {
		return StringEscapeUtils.unescapeJava(srcStr).replace("\\\\", "\\").replace("\\\"", "\"");
	}

	/**
	 * 文字列の両端のダブルクォート削除
	 * @param s 対象文字列
	 * @return 編集後文字列
	 */
	public static String removeDoubleQuote(final String s) {
		if (s == null || s.isEmpty() || s.length() < 2) {
			return s;
		}
		// 文字列の両サイドが"の場合
		if (s.substring(0, 1).equals(DOUBLE_QUOTE)
			&& s.substring(s.length() - 1).equals(DOUBLE_QUOTE))
		{
			// "" の場合は空文字を返す
			if (s.length() == 2) {
				return EMPTY;
			}
			return s.substring(1, s.length() - 1);
		}
		return s;
	}
	
	/**
	 * 找到对应数组的索引的值
	 * 
	 * @param strArr
	 * @param str
	 * @return
	 * @author haol 
	 * @createDate 2013-11-7
	 * @lastAuthor haol
	 * @lastDate 2013-11-7
	 */
	public static int findIndex(final String[] strArr, final String str) {
		int res = 0;
		int i = 0;
		for (i = 0; i < strArr.length; i++) {
			if (StringUtil.isNotEmptyString(str) && strArr[i].equals(str)) {
				res = i;
				break;
			}
		}
		if (i == strArr.length) {
			res = 0;
		}
		return res;
	}
	
	/**
	 * 格式化文件路径
	 * 
	 * @param input
	 * @return
	 * @author lbs 
	 * @createDate 2013-12-16
	 * @lastAuthor lbs
	 * @lastDate 2013-12-16
	 */
	public static String formatPath(String input) {
		String out = input;
        if (StringUtil.isNotEmptyString(input)) {
        	File file = new File(input);
        	out = file.toString();
//        	out = out.replaceAll("/", "\\");
//        	out = out.replaceAll("\\\\", "\\");
        }
        return out;
    }
	
	/**
	 * html 代码转换
	 * 
	 * @param s
	 * @return
	 */
	public static final String htmlToCode(String s) {
		if (s == null) {
			return "";
		} else {
			s = s.replace("\r\n", "<br>&nbsp;&nbsp;");// 这才是正确的！
			s = s.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
//			s = s.replace(" ", "&nbsp;");
			s = s.replace("\"", "\\" + "\"");// 如果原文含有双引号，这一句最关键！！！！！！
			return s;
		}
	}
	
	public static final String TextToHtml(String sourcestr) {
		int strlen;
		String restring = "", destr = "";
		strlen = sourcestr.length();
		for (int i = 0; i < strlen; i++) {
			char ch = sourcestr.charAt(i);
			switch (ch) {
			case '<':
				destr = "<";
				break;
			case '>':
				destr = ">";
				break;
			case '\"':
				destr = "\"";
				break;
			case '&':
				destr = "&";
				break;
			case 13:
				destr = "<br>";
				break;
			case 32:
				destr = " ";
				break;
			default:
				destr = "" + ch;
				break;
			}
			restring = restring + destr;
		}
		return "" + restring;
	}
	
	/**
     * 提取每个汉字的首字母
     * 
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
	            // 提取汉字的首字母
	           String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
	            if (pinyinArray != null) {
	                convert += Character.toUpperCase(pinyinArray[0].charAt(0));
	            } else {
	                convert += Character.toUpperCase(word);
	            }
        }
        return convert;
    }
    
    /**
     * 判断是否为汉字
     * @param character
     * @return
     */
    public static Boolean isHanZi(char character){
    	int code = (int)character; 
    	return (code >=19968 && code <= 171941); 
    }
    
    public static String formatDouble(Double value, int bit) {//bit小数点后的尾数
    	String data = value + "";
    	if(data.indexOf(".") != -1) {
    		String subData = data.substring(data.indexOf("."));
    		if(subData.length() > bit + 1) {
    			data = data.substring(0, data.indexOf(".") + bit + 1);
    		}
    	}
    	return data;
    }
    /**
     * 将空字符串转换
     * 为空时返回"0"
     * @param i
     * @return
     */
	public static String changeStrToStr(String i) {
		 if(i != null && i.length() != 0) { 
			 return i;
		 }
		return "0";
	}
}
