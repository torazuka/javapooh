package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.view.impl.Element;

public class AttributeNameUtil {

	// to check the kind of attributes
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
}
