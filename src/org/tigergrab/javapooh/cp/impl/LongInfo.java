package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

/**
 * u1 tag; u4 high_bytes; u4 low_bytes;
 */
public class LongInfo implements ConstantInfo {

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
		tagElement.setComment(CpInfoTag.Constant_Long.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.high_bytes)));
		currentCursor += CpItem.high_bytes.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.low_bytes)));
		currentCursor += CpItem.low_bytes.size();

		return currentCursor;
	}

	// TODO 要テスト・・
	protected long convertBytes(final byte[] hbytes, final byte[] lbytes) {
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

		return ((long) hhigh << 32) + lhigh;
	}
}
