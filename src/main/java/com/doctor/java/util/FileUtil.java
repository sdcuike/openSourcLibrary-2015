package com.doctor.java.util;

import java.io.File;

/**
 * 
 * @author doctor
 *
 * @time 2015年3月30日
 */
public final class FileUtil {

	public static String getJavaFilePath(Class<?> classT) {
		StringBuilder stringBuilder = new StringBuilder(256);
		stringBuilder.append(new File("").getAbsolutePath()).append(File.separator);
		stringBuilder.append("src").append(File.separator).append("main").append(File.separator).append("java").append(File.separator);
		stringBuilder.append(classT.getPackage().getName().replace(".", File.separator));
		stringBuilder.append(File.separator);
		stringBuilder.append(classT.getSimpleName()).append(".java");
		return stringBuilder.toString();
	}
}
