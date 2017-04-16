package com.crystalcg.gamedev.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件工具类
 *
 * @author liuxueliang/2013-7-9/下午05:56:05
 */
public class PropertiesUtils {

	private static Logger logger = Logger.getLogger(PropertiesUtils.class.getName());

	private static Properties p = null;

	static {
		InputStream in = null;
		try {
			in = PropertiesUtils.class.getClassLoader().getResourceAsStream("main/resources/system.properties");
			p = new Properties();
			p.load(in);

		} catch (Exception e) {
			logger.error("system load user-auth.properties that is failed", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close inputStream have an exception", e);
				}
			}
		}
	}

	/**
	 * 读取属性文件中的值
	 *
	 * @param fileName
	 *            文件名
	 * @param name
	 *            key值
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author liuxueliang/2013-7-9/下午05:55:02
	 */
	public static String getValue(String fileName, String name, String defaultValue) {
		InputStream in = null;
		try {
			in = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
			Properties properties = new Properties();
			properties.load(in);
		} catch (Exception e) {
			logger.error("file " + fileName + " is not fund", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close inputStream have an exception", e);
				}
			}
		}
		return p.getProperty(name, defaultValue);
	}

	public static String getValue(String name, String defaultValue) {

		return p.getProperty(name, defaultValue);

	}

	public static String getValue(String name) {

		return p.getProperty(name);

	}

}
