package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

/**
 * u1 tag; u4 bytes;
 */
public class FloatInfo implements ConstantInfo {

	protected final PromptView view = new PromptView();

	protected final DefaultConstantInfo defaultInfo = new DefaultConstantInfo();

	@Override
	public Element getData(final byte[] byts, final int cursor,
			final Element ele) {
		return defaultInfo.getData(byts, cursor, ele);
	}

	@Override
	public int getContents(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element tagElement = getData(bytes, currentCursor, new Element(
				CpItem.tag));
		tagElement.setComment(CpInfoTag.Constant_Float.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.bytes)));
		currentCursor += CpItem.bytes.size();

		return currentCursor;
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
}
