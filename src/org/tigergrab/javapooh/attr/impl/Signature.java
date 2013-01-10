package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class Signature extends DefaultAttribute {

	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element nameIndexElement = getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.Signature.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.signature_index)));
		currentCursor += AttributeItem.signature_index.size();

		return currentCursor;
	}
}
