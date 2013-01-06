package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u4 high_bytes; u4 low_bytes;
 */
public class DoubleInfo implements ConstantInfo {

	protected final int HIGH_BYTES_SIZE = 4;
	protected final int LOW_BYTES_SIZE = 4;

	protected byte[] highBytes = new byte[HIGH_BYTES_SIZE];
	protected byte[] lowBytes = new byte[LOW_BYTES_SIZE];
	protected double doubleBytes = 0L;

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + HIGH_BYTES_SIZE + LOW_BYTES_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getHighBytes(bytes, cursor);
		getLowBytes(bytes, cursor + HIGH_BYTES_SIZE);
		doubleBytes = convertBytes(highBytes, lowBytes);
	}

	protected void getHighBytes(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + HIGH_BYTES_SIZE; i++) {
			highBytes[index++] = bytes[i];
		}
	}

	protected void getLowBytes(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + LOW_BYTES_SIZE; i++) {
			lowBytes[index++] = bytes[i];
		}
	}

	// TODO 要テスト・・
	protected double convertBytes(final byte[] hbytes, final byte[] lbytes) {
		String hstr = "";
		for (byte b : hbytes) {
			hstr += Util.getHexString(b);
		}
		long hhigh = Long.parseLong(hstr, 16);

		String lstr = "";
		for (byte b : lbytes) {
			lstr += Util.getHexString(b);
		}
		long lhigh = Long.parseLong(lstr, 16);

		final long bits = ((long) hhigh << 32) + lhigh;

		if (bits == 0x7ff0000000000000L) {
			return Double.POSITIVE_INFINITY;
		} else if (bits == 0xfff0000000000000L) {
			return Double.NEGATIVE_INFINITY;
		} else if ((0x7ff0000000000001L <= bits && bits <= 0x7fffffffffffffffL)
				|| (0xfff0000000000001L <= bits && bits <= 0xffffffffffffffffL)) {
			return Double.NaN;
		}

		int s = ((bits >> 63) == 0) ? 1 : -1;
		int e = (int) ((bits >> 52) & 0x7ffL);
		long m = (e == 0) ? (bits & 0xfffffffffffffL) << 1
				: (bits & 0xfffffffffffffL) | 0x10000000000000L;
		return (s * m * (2 ^ (2 - 1075)));
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_Double;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u4", "high_bytes", highBytes));
		result.add(new Element("u4", "low_bytes", lowBytes));
		return result;
	}
}
