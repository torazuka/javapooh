package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.view.impl.Element;

public class EnumConstValue extends DefaultValue {

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.type_name_index)));
		currentCursor += AttributeItem.type_name_index.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.const_name_index)));
		currentCursor += AttributeItem.const_name_index.size();

		return currentCursor;
	}
}
