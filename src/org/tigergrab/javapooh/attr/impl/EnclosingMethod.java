package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class EnclosingMethod implements AttributeInfo {

	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element nameIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.EnclosingMethod.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.class_index)));
		currentCursor += AttributeItem.class_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.method_index)));
		currentCursor += AttributeItem.method_index.size();
		return currentCursor;
	}
}
