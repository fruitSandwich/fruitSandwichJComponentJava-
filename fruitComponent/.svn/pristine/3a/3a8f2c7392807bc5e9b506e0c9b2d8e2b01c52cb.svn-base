package fruitSandwich.config;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import fruitSandwich.util.FDateUtil;

public class ConfigTest {
	public static void main(String[] args) {
		FConfigHelper configHelper = new FConfigHelper("hello");
		configHelper.putProperty("refreshTime", FDateUtil.nowChineseTime());

		Map<String, String> map = configHelper.allPropertiesMap();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		System.out.println(configHelper.allPropertiesXML());
	}
}
