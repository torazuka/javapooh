package org.tigergrab.javapooh.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private final static Logger logger = LoggerFactory.getLogger("Main.class");

	protected static boolean fileNumCheck(String[] args) {
		if (args.length == 1) {
			return true;
		}
		logger.error("ファイルは1つだけ指定してください。");
		return false;
	}

	protected static File getFile(String[] args) {
		String fileName = args[0];
		File classFile = new File(fileName);
		if (classFile.exists()) {
			logger.info(fileName);
			return classFile;
		}
		return null;
	}

	public static void main(String[] args) {
		if (fileNumCheck(args) == false) {
			return;
		}
		File classFile = getFile(args);
		if (classFile == null) {
			return;
		}

		long fileSize = classFile.length();
		logger.info("file size: " + fileSize);

		byte[] bytes = new byte[(int) fileSize];
		try (BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(classFile));) {
			int readBytes = bis.read(bytes);
			if (readBytes != fileSize) {
				return;
			}
		} catch (IOException e1) {
			logger.error("読み取りに失敗しました。");
		}

		ClassFileControl control = new ClassFileControl();
		control.execute(bytes);
	}
}
