package fruitSandwich.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

import fruitSandwich.util.FDateUtil;

/**
 * 类型解析处理工具类
 * 
 * 1.将指定了类型的字符串转换为指定的类型
 * 
 * 2.将没有指定类型的值转换为标准化字符串
 * 
 * @author 杜佳恒 2014-9-23
 */
public class FParseTypeUtil {
	/**
	 * 将指定了类型的字符串转换为指定的类型
	 * 
	 * @param cls
	 * @param value
	 * @return
	 */
	public static Object parseString(Class<?> cls, String value) {
		if ((cls == Integer.class) || (cls == Integer.TYPE)) {
			return Integer.valueOf(Integer.parseInt(value));
		}

		if ((cls == Long.class) || (cls == Long.TYPE)) {
			return Long.valueOf(Long.parseLong(value));
		}

		if ((cls == Double.class) || (cls == Double.TYPE)) {
			return Double.valueOf(Double.parseDouble(value));
		}

		if ((cls == Float.class) || (cls == Float.TYPE)) {
			return Float.valueOf(Float.parseFloat(value));
		}

		if ((cls == Boolean.class) || (cls == Boolean.TYPE)) {
			return Boolean.valueOf(Boolean.parseBoolean(value));
		}

		if ((cls == Short.class) || (cls == Short.TYPE)) {
			return Short.valueOf(Short.parseShort(value));
		}

		if ((cls == Character.class) || (cls == Character.TYPE)) {
			return Character.valueOf(value.charAt(0));
		}

		if (cls.getSuperclass() == Enum.class) {
			try {
				Method valueOf = cls.getMethod("valueOf", String.class);
				Object object = valueOf.invoke(String.class, value);
				return object;
			} catch (NoSuchMethodException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}

		}

		if (cls == Date.class) {
			Date date = null;
			try {
				date = FDateUtil.parseChineseDate(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}

		return value;
	}

	/**
	 * 将没有指定类型的值转换为标准化字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String parseObject(Object object) {
		if (object.getClass() == Date.class) {
			return FDateUtil.fomateChineseDate((Date) object);
		}
		return object.toString();
	}

}