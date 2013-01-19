package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class Exceptions implements AttributeInfo {

	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element nameIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.Exceptions.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		Element numberOfExceptionsElement = parser.getData(bytes,
				currentCursor, new Element(AttributeItem.number_of_exceptions));
		view.printElement(numberOfExceptionsElement);
		currentCursor += AttributeItem.number_of_exceptions.size();

		currentCursor = getExceptionIndexTable(bytes,
				Util.byteToInt(numberOfExceptionsElement.getBytes()), cursor);
		return currentCursor;
	}

	protected int getExceptionIndexTable(final byte[] bytes,
			final int numException, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();
		int count = 1;
		view.printBegin(AttributeItem.exception_index_table.name());
		for (; count < numException + 1; count++) {
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.exception_index)));
			currentCursor += AttributeItem.exception_index.size();
		}
		view.printEnd(AttributeItem.exception_index_table.name());
		return currentCursor;
	}
}
