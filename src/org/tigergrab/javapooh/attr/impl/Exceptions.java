package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class Exceptions extends DefaultAttribute {

	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element nameIndexElement = getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.Exceptions.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		Element numberOfExceptionsElement = getData(bytes, currentCursor,
				new Element(AttributeItem.number_of_exceptions));
		view.printElement(numberOfExceptionsElement);
		currentCursor += AttributeItem.number_of_exceptions.size();

		currentCursor = getExceptionIndexTable(bytes, Integer.parseInt(
				Util.byteToString(numberOfExceptionsElement.getBytes()), 16),
				cursor);
		return currentCursor;
	}

	protected int getExceptionIndexTable(final byte[] bytes,
			final int numException, final int cursor) {
		int currentCursor = cursor;
		int count = 1;
		view.printBegin(AttributeItem.exception_index_table.name());
		for (;;) {
			if (count < numException + 1) {
				view.printElement(getData(bytes, currentCursor, new Element(
						AttributeItem.exception_index)));
				currentCursor += AttributeItem.exception_index.size();
				continue;
			}
			break;
		}
		view.printEnd(AttributeItem.exception_index_table.name());
		return currentCursor;
	}
}
