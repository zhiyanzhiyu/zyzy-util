/**
* file name    PropertiesUtil.java
* description  
* @author      xiehongdong
* @version     1.0
* create at    2012-2-28 上午10:12:18
* copyright 2012 Newcosoft 
*
* modification history  
* date			author		version		description
* ------------------------------------------------------------------  
* 2012-2-28		xiehongdong			1.0			
*/  
package com.zyzy.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 配置文件存取工具类
 * @author BillZhao
 *
 */
public class PropertiesUtil {
	private String filePath;
	Properties prop = new Properties();

	public PropertiesUtil(@SuppressWarnings("rawtypes") Class clazz, String filePath) throws IOException {

		InputStream input = null;
		try {
			input = clazz.getResourceAsStream(filePath);
			prop.load(input);
		} finally {
			if (null != input) {
					input.close();
			}
		}
	}

	/**
	 * 根据传来的字符串从配置文件中读取相对应的配置数据
	 */
	public String getSystemConfigByName(String name) {
		return prop.getProperty(name);
	}

	/**
	 * 根据传来的数据组，往配置文件中写相对应的配置数据
	 * 
	 * @param key
	 * @param keyValue
	 * @throws IOException
	 */
	public void wirteProperties(String key, String keyValue) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			prop.setProperty(key, keyValue);
				prop.store(out, "Update '" + key + "' value");
		} finally {
				if (null != out) {
					out.close();
			}
		}
	}

	/**
	 * 根据map传来的数据，向配置文件中添加新配置数据或者更新配置数据
	 * 
	 * @param map
	 * @throws IOException
	 */
	public void wiriteProperties(Map<String, String> map) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			Set<String> keySet = map.keySet();
			StringBuilder message = new StringBuilder();
			// 记录更新数据信息
			message.append("Update ");
			for (String key : keySet) {
				prop.setProperty(key, map.get(key));
				message.append(key);
				message.append("\\");
			}
			message.append("value");
				prop.store(out, message.toString());
		} finally {
				if (null != out) {
					out.close();
					out.flush();
				}
		}
	}
}
