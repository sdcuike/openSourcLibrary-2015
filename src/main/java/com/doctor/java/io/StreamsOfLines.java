package com.doctor.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.doctor.java.util.FileUtil;

/**
 * @see http://howtodoinjava.com/2014/05/04/read-file-line-by-line-in-java-8-streams-of-lines-example/
 * 
 * @author doctor
 *
 * @time 2015年3月30日 下午2:11:50
 */
public class StreamsOfLines {
	private static String file = FileUtil.getJavaFilePath(StreamsOfLines.class);

	public static void main(String[] args) {
		String content = readLinesUsingFileReade();
		System.out.println(content);
		System.out.println("===========================");
		content = "";
		content = readStreamOfLinesUsingFilesWithTryBlock();
		System.out.println(content);
	}

	private static String readLinesUsingFileReade() {

		StringBuilder stringBuilder = new StringBuilder(512);
		String line;

		try (FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);) {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();

	}

	private static String readStreamOfLinesUsingFilesWithTryBlock() {
		StringBuilder stringBuilder = new StringBuilder(512);
		Path path = Paths.get(file);
		try {
			Files.lines(path, StandardCharsets.UTF_8).forEach(t -> stringBuilder.append(t).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}

}
