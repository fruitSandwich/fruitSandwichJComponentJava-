package fruitSandwich.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 数组的一些帮助方法
 * 
 * @author 杜佳恒 2014-9-26
 */
public class FArrays {

	/**
	 * 循环移步，默认步长为1
	 * 
	 * @param objects
	 * @return
	 */
	public static Object[] moveCyclic(Object[] objects) {
		return moveCyclic(objects, 1);
	}

	/**
	 * 数组循环移步, 新建一个数组把源数组循环移位后填入 ,使用翻转的方法
	 * 
	 * 1.翻转0~step的元素 2.翻转step~length的元素 3.翻转所有元素
	 * 
	 * @param array
	 * @param step
	 * @return
	 */
	public static <T> T[] moveCyclic(T[] array, int step) {
		step = step % array.length;// 确保移步数小于数组长度
		array = reverseArray(array, 0, step - 1);
		// System.out.println(Arrays.toString(array));
		array = reverseArray(array, step, array.length - 1);
		// System.out.println(Arrays.toString(array));
		array = reverseArray(array, 0, array.length - 1);
		// System.out.println(Arrays.toString(array));
		return array;
	}

	/**
	 * 翻转数值所有元素
	 * 
	 * @param array
	 * @param start
	 * @param end
	 * @return
	 */
	static <T> T[] reverseArray(T array[], int start, int end) {
		int low = start, high = end;

		while (low < high) {
			T tempDate = array[low];
			array[low] = array[high];
			array[high] = tempDate;
			++low;
			--high;
		}
		return array;
	}

	/**
	 * 
	 * 数组移位操作，往后移step位
	 * 
	 * @param array
	 * @param step
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] moveFillFirstBlank(T[] array, int step) {
		Class<?> tClass = array[0].getClass();
		Object fillArray = Array.newInstance(tClass, array.length + step);
		for (int i = 0; i < array.length; i++) {
			Array.set(fillArray, (i + step), array[i]);
		}
		return (T[]) fillArray;
	}

	/**
	 * 
	 * 数组扩展，往后扩展step位
	 * 
	 * @param array
	 * @param step
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] moveFillLastBlank(T[] array, int step) {
		Class<?> tClass = array[0].getClass();
		Object fillArray = Array.newInstance(tClass, array.length + step);
		for (int i = 0; i < array.length; i++) {
			Array.set(fillArray, i, array[i]);
		}
		return (T[]) fillArray;
	}

	/**
	 * 数组移位操作，往后移step位 使用Object数组
	 * 
	 * 
	 * @description Object[] objects = new String[];之后obects
	 *              就不能赋其他类型的值所以我们传Object进来新生成一个Object数组返回的新数组可以被赋值为其他类型。
	 * 
	 * @param array
	 * @param step
	 * @return
	 */
	public static Object[] moveFillFirstBlankObject(Object[] array, int step) {
		Object[] fillArray = new Object[array.length + step];
		for (int i = 0; i < array.length; i++) {
			fillArray[i + step] = array[i];
		}
		return fillArray;
	}

	public static Object[] moveFillLastBlankObject(Object[] array, int step) {
		Object[] fillArray = new Object[array.length + step];
		for (int i = 0; i < array.length; i++) {
			fillArray[i] = array[i];
		}
		return fillArray;
	}

	public static void main(String[] args) {
		Integer[] array = { 1, 2, 3, 4, 5, 6, 7 };

		// Integer[] change = moveCyclic(array, 4);
		Integer[] change = moveFillFirstBlank(array, 2);
		System.out.println(Arrays.toString(change));
	}
}
