package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

/**
 * u1 tag; u2 length; u1 bytes[length];
 */
public class Utf8Info implements ConstantInfo {

	protected final PromptView view = new PromptView();

	protected final int LENGTH_SIZE = 2;
	protected byte[] lengthByte = new byte[LENGTH_SIZE];
	protected byte[] stringByte;

	protected int length = -1;
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
		tagElement.setComment(CpInfoTag.Constant_Utf8.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		Element lengthElement = getData(bytes, currentCursor, new Element(
				CpItem.length));
		view.printElement(lengthElement);
		currentCursor += CpItem.length.size();

		currentCursor = getByte(bytes, Integer.parseInt(
				Util.byteToString(lengthElement.getBytes()), 16), currentCursor);

		return currentCursor;
	}

	protected int getByte(final byte[] bytes, final int length, final int cursor) {
		Element element = new Element(CpItem._byte);
		int currentCursor = cursor;
		byte[] bt = new byte[length];
		int index = 0;
		for (int i = 0; i < length; i++) {
			bt[index++] = bytes[currentCursor];
			currentCursor += CpItem._byte.size();
		}
		element.setBytes(bt);
		stringByte = bt;
		view.printElement(element);
		return currentCursor;
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

	/**
	 * for Attribute
	 */
	public byte[] getStringBytes() {
		return stringByte;
	}
}
