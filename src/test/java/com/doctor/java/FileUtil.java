package com.doctor.java;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

/**
 * @author doctor
 *
 * @time 2015年6月15日 上午9:12:29
 */
public enum FileUtil {
	;
	public static List<Path> getDirectoryFiles(final Path path, final String globPattern) throws IOException {
		Preconditions.checkArgument(Files.exists(path) && Files.isDirectory(path), path + " is not a directory");
		Preconditions.checkArgument(StringUtils.isNotBlank(globPattern));
		List<Path> list = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, globPattern)) {
			directoryStream.forEach(list::add);
		}

		return Arrays.asList(list.toArray(new Path[0]));
	}

	public static List<Path> directoryFiles(final Path path, final DirectoryStream.Filter<Path> filter) throws IOException {
		Preconditions.checkArgument(Files.exists(path) && Files.isDirectory(path), path + " is not a directory");
		Preconditions.checkNotNull(filter);
		List<Path> list = new ArrayList<>();

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, filter)) {
			directoryStream.forEach(list::add);
		}

		return Arrays.asList(list.toArray(new Path[0]));
	}
}
