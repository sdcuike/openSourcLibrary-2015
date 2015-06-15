package com.doctor.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

/**
 * Nio2 DirectoryStream.Filter
 * 
 * @author doctor
 *
 * @see http://www.javacodegeeks.com/2012/10/java-7-file-filtering-using-nio-2-part-1.html
 * 
 * @time 2015年6月15日 上午8:58:50
 */
public class Java7FileFilteringUsingNo2 {

	/**
	 * @throws IOException
	 * @see http://www.javacodegeeks.com/2012/10/java-7-file-filtering-using-nio-2-part-1.html
	 */
	@Test
	public void test_home_hidden_files() throws IOException {

		String folder = System.getProperty("user.home");
		Path path = Paths.get(folder);
		if (!Files.exists(path) || !Files.isDirectory(path)) {
			System.out.println(path + " is not a irectory");
		}

		Files.newDirectoryStream(path, ".*").forEach(p -> System.out.println(p.getFileName()));

		List<Path> list = FileUtil.getDirectoryFiles(Paths.get(System.getProperty("user.home")), ".b*");
		System.out.println(list);
	}

	@Test
	public void test_() throws IOException {
		List<Path> list = FileUtil.directoryFiles(Paths.get(System.getProperty("user.home")), (Path entry) -> Files.exists(entry) && Files.isRegularFile(entry) && Files.isHidden(entry));
		System.out.println(list);
	}
}
