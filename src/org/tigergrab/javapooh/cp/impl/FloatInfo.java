package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u4 bytes;
 */
public class FloatInfo implements ConstantInfo {

	protected final int BYTES_SIZE = 4;

	protected byte[] bt = new byte[BYTES_SIZE];
	protected float fbt = 0f;

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
		fbt = convertFloat(bt);
	}

	// TODO 要テスト・・
	/**
	 * See
	 * http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.4
	 */
	protected float convertFloat(final byte[] bytes) {
		String str = "";
		for (byte b : bytes) {
			str += Util.getHexString(b);
		}
		final int bits = Integer.parseInt(str, 16);

		if (bits == 0x7f800000) {
			return Float.POSITIVE_INFINITY;
		} else if (bits == 0xff800000) {
			return Float.NEGATIVE_INFINITY;
		} else if ((0x7f800001 <= bits && bits <= 0x7fffffff)
				|| (0xff800001 <= bits && bits <= 0xffffffff)) {
			return Float.NaN;
		}

		int s = ((bits >> 31) == 0) ? 1 : -1;
		int e = ((bits >> 23) & 0xff);
		int m = (e == 0) ? (bits & 0x7fffff) << 1
				: (bits & 0x7fffff) | 0x800000;

		return (s * m * (2 ^ (e - 150)));
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_Float;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u4", "bytes", bt));
		return result;
	}
}
