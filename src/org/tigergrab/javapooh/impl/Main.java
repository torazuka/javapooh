package org.tigergrab.javapooh.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;

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
			logger.info("Classfile " + fileName);
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
		logger.info("  Last modified " + getLastModified(classFile) + "; size "
				+ fileSize + " bytes");
		getMessageDigest(classFile);

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

	protected static void getMessageDigest(File classFile) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// nop
			return;
		}
		try (DigestInputStream dis = new DigestInputStream(
				new BufferedInputStream(new FileInputStream(classFile)), md);) {
			while (dis.read() != -1) {
			}
			byte[] digest = md.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				sb.append(String.format("%02x", digest[i]));
			}
			logger.info("  MD5 checksum " + new String(sb));
		} catch (IOException e) {
			// nop
			return;
		}
	}

	protected static String getLastModified(File classFile) {
		long lastModified = classFile.lastModified();
		Date date = new Date(lastModified);
		DateFormat df = DateFormat.getDateInstance();
		return df.format(date);
	}
}
