package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u2 length; u1 bytes[length];
 */
public class Utf8Info implements ConstantInfo {

	protected final int LENGTH_SIZE = 2;
	protected byte[] lengthByte = new byte[LENGTH_SIZE];
	protected byte[] stringByte;

	protected int length = -1;

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + LENGTH_SIZE + length;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getLength(bytes, cursor);
		length = convertNameIndex(lengthByte);
		getBytes(bytes, cursor + LENGTH_SIZE, length);
	}

	protected void getLength(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + LENGTH_SIZE; i++) {
			lengthByte[index++] = bytes[i];
		}
	}

	protected void getBytes(final byte[] bytes, final int cursor, final int len) {
		stringByte = new byte[len];
		int index = 0;
		// TODO 要テスト
		for (int i = cursor; i < cursor + len; i++) {
			stringByte[index++] = bytes[i];
		}
	}

	protected int convertNameIndex(final byte[] bytes) {
		String str = "";
		for (byte b : bytes) {
			str += Util.getHexString(b);
		}
		return Integer.parseInt(str, 16);
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_Utf8;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u2", "length", lengthByte));
		result.add(new Element("u1", "bytes[length]", stringByte));
		return result;
	}

	public byte[] getStringBytes() {
		return stringByte;
	}
}
