package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u2 string_index;
 */
public class StringInfo implements ConstantInfo {

	protected final int STRING_INDEX_SIZE = 2;

	protected byte[] stringIndexByte = new byte[STRING_INDEX_SIZE];

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + STRING_INDEX_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getStringIndex(bytes, cursor);
	}

	/**
	 * u2 string_index
	 */
	protected void getStringIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + STRING_INDEX_SIZE; i++) {
			stringIndexByte[index++] = bytes[i];
		}
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_String;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u2", "string_index", stringIndexByte));
		return result;
	}
}
