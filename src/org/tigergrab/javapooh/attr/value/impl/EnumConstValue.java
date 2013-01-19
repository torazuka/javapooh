package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.value.Value;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class EnumConstValue implements Value {

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();
		PromptView view = new PromptView();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.type_name_index)));
		currentCursor += AttributeItem.type_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.const_name_index)));
		currentCursor += AttributeItem.const_name_index.size();

		return currentCursor;
	}
}
