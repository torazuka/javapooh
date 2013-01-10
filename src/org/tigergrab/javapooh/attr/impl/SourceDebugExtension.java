package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class SourceDebugExtension extends DefaultAttribute {

	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element nameIndexElement = getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.SourceDebugExtension.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		Element attributeLengthElement = getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_length));
		view.printElement(attributeLengthElement);
		currentCursor += AttributeItem.attribute_length.size();

		currentCursor = getDebugExtension(bytes, Integer.parseInt(
				Util.byteToString(attributeLengthElement.getBytes()), 16),
				currentCursor);

		return currentCursor;
	}

	protected int getDebugExtension(final byte[] bytes, final int length,
			final int cursor) {
		Element element = new Element(AttributeItem.debug_extension);
		int currentCursor = cursor;
		byte[] bt = new byte[length];
		int index = 0;
		for (int i = cursor; i < cursor + length; i++) {
			bt[index++] = bytes[i];
			currentCursor += AttributeItem.debug_extension.size();
		}
		element.setBytes(bt);
		view.printElement(element);
		return currentCursor;
	}
}
