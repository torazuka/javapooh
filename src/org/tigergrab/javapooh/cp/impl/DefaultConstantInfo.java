package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.view.impl.Element;

public class DefaultConstantInfo implements ConstantInfo {

	@Override
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

	@Override
	public int getContents(byte[] bytes, int cursor) {
		return -1;
	}
}
