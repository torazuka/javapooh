package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u4 bytes;
 */
public class IntegerInfo implements ConstantInfo {

	protected final int BYTES_SIZE = 4;

	protected byte[] bt = new byte[BYTES_SIZE];
	protected int ibt = 0x0;

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + BYTES_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getBytes(bytes, cursor);
	}

	protected void getBytes(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + BYTES_SIZE; i++) {
			bt[index++] = bytes[i];
		}
		ibt = convertBytes(bt);
	}

	// TODO 要テスト！！
	protected int convertBytes(final byte[] bytes) {
		String s = "";
		for (byte b : bt) {
			s += Util.getHexString(b);
		}
		return Integer.parseInt(s, 16);
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_Integer;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u4", "bytes", bt));
		return result;
	}
}
