package org.tigergrab.javapooh.impl;

import org.tigergrab.javapooh.view.impl.Element;

public class ByteParser {
	public Element getData(final byte[] sourceBytes, final int cursor,
			Element ele) {
		Element result = new Element(ele);
		int index = 0;
		byte[] bt = new byte[ele.getSize()];
		for (int i = cursor; i < cursor + ele.getSize(); i++) {
			bt[index++] = sourceBytes[i];
		}
		result.setBytes(bt);
		return result;
	}
}
