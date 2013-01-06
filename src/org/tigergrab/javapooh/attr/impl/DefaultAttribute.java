package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.view.impl.Element;

public class DefaultAttribute implements AttributeInfo {

	public Element getAttributeNameIndex(final byte[] bytes, final int cursor) {
		Element result = new Element(AttributeItem.attribute_name_index);
		byte[] attrNameBytes = new byte[AttributeItem.attribute_name_index
				.size()];
		int index = 0;
		for (int i = cursor; i < cursor
				+ AttributeItem.attribute_name_index.size(); i++) {
			attrNameBytes[index++] = bytes[i];
		}
		result.setBytes(attrNameBytes);
		return result;
	}

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
	public int getInfo(byte[] bytes, int cursor) {
		return -1;
	}

}
