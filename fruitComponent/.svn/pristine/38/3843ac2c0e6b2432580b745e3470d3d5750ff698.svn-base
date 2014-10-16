package fruitSandwich.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import fruitSandwich.util.FCurrentRunPath;

/**
 * 配置帮助类
 * 
 * @author 杜佳恒 2014-9-23
 */
public class FConfigHelper {

	private Document document;

	private File configFile = new File(
			FCurrentRunPath.getCurrentRootPath(FConfigHelper.class)
					+ File.separator + "fconfig.xml", "");

	/**
	 * 默认配置名为fconfig.xml
	 */
	public FConfigHelper() {
		initializeConfig();
	}

	/**
	 * 通过传入的配置名设定当前配置文件
	 * 
	 * @param configName
	 */
	public FConfigHelper(String configName) {
		configFile = new File(
				FCurrentRunPath.getCurrentRootPath(FConfigHelper.class)
						+ File.separator + configName + ".xml");
		initializeConfig();
	}

	/**
	 * 添加时如果属性不存在直接添加，存在的话覆盖原有的
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void putProperty(String name, Object value) {
		Element propertyElement = document.getRootElement().element(name);
		if (propertyElement == null) {
			Element addNode = document.getRootElement().addElement(name);
			addNode.setText(FParseTypeUtil.parseObject(value));
		} else {
			propertyElement.setText(FParseTypeUtil.parseObject(value));
		}
		synchronizationConfig();
	}

	/**
	 * 
	 * 
	 * @param name
	 * @return
	 */
	/**
	 * 通过属性名和属性类型获取属性值，如不存在则返回null
	 * 
	 * @param name
	 *            属性名
	 * @param typeClass
	 *            传入获取属性值的类型
	 * @return
	 */
	public Object getProperty(String name, Class<?> typeClass) {
		Object value = null;
		Element propertyElement = document.getRootElement().element(name);
		if (propertyElement != null) {
			String text = propertyElement.getText();
			value = FParseTypeUtil.parseString(typeClass, text);
		}
		return value;
	}

	/**
	 * 通过属性名获取属性值，默认获得字符串，如不存在则返回null
	 * 
	 * @param name
	 *            属性名
	 * @return
	 */
	public String getProperty(String name) {
		String value = null;
		Element propertyElement = document.getRootElement().element(name);
		if (propertyElement != null) {
			value = propertyElement.getText();
		}
		return value;
	}

	/**
	 * 获取文件中实时的属性值
	 * 
	 * @param name
	 * @return
	 */
	public String getRightNowProperty(String name) {
		initializeConfig();
		return getProperty(name);
	}

	/**
	 * 以Map的方式获取所有配置
	 * 
	 * @return
	 */
	public Map<String, String> allPropertiesMap() {
		@SuppressWarnings("unchecked")
		List<Element> list = document.getRootElement().elements();

		Map<String, String> map = new HashMap<>();

		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}
		return map;
	}

	/**
	 * 以XML的方式获取所有配置
	 * 
	 * @return
	 */
	public String allPropertiesXML() {
		return document.asXML();
	}

	/**
	 * 通过配置文件初始化配置,先检测配置文件是否存在，如果存在那么直接读出来，没有的话新建一个配置文件
	 */
	private void initializeConfig() {
		if (configFile.exists()) {
			try {
				document = new SAXReader().read(configFile);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			document = DocumentFactory.getInstance().createDocument("utf8");
			document.addElement("fconfigRoot");
			synchronizationConfig();
		}
	}

	/**
	 * 同步到配置文件
	 */
	private void synchronizationConfig() {
		// try {
		// OutputFormat format = OutputFormat.createCompactFormat();
		// format.setEncoding("utf8");
		// XMLWriter xmlWriter = new XMLWriter(new FileWriter(configFile),
		// format);
		// xmlWriter.write(document);
		// xmlWriter.close();
		// } catch (IOException ioException) {
		// ioException.printStackTrace();
		// }

		Writer writer;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(configFile),
					"UTF-8");
			writer.write(document.getRootElement().asXML());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
