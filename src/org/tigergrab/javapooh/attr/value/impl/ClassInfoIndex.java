package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.value.Value;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class ClassInfoIndex implements Value {

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();
		PromptView view = new PromptView();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.class_info_index)));
		currentCursor += AttributeItem.class_info_index.size();

		return currentCursor;
	}
}
