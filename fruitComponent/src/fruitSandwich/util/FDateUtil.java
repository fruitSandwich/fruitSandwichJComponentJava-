package fruitSandwich.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具
 * 
 * @author 杜佳恒 2014-9-23
 */
public class FDateUtil {
	private static final SimpleDateFormat CHINESE_SIMPLE_DATE_FORMAT = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm:ss");

	public static Date parseChineseDate(String dateString)
			throws ParseException {
		Date date = CHINESE_SIMPLE_DATE_FORMAT.parse(dateString);
		return date;
	}

	public static String fomateChineseDate(Date date) {
		return CHINESE_SIMPLE_DATE_FORMAT.format(date);
	}

	public static String nowChineseTime() {
		Calendar calendar = Calendar.getInstance();
		return CHINESE_SIMPLE_DATE_FORMAT.format(calendar.getTime());
	}
}