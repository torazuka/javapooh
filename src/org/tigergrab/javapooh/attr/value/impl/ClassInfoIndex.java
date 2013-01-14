package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.view.impl.Element;

public class ClassInfoIndex extends DefaultValue {

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.class_info_index)));
		currentCursor += AttributeItem.class_info_index.size();

		return currentCursor;
	}
}
