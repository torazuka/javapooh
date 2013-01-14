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
public class IntegerInfo implements ConstantInfo {

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
		tagElement.setComment(CpInfoTag.Constant_Integer.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.bytes)));
		currentCursor += CpItem.bytes.size();

		return currentCursor;
	}

	// TODO 要テスト！！
	protected int convertBytes(final byte[] bytes) {
		String s = "";
		for (byte b : bytes) {
			s += Util.getHexString(b);
		}
		return Integer.parseInt(s, 16);
	}
}
