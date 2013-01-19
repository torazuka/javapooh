package org.tigergrab.javapooh.attr.verify.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.verify.VerificationType;
import org.tigergrab.javapooh.attr.verify.VerificationTypeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class ItemUninitialized implements VerificationType {

	@Override
	public int execute(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		PromptView view = new PromptView();
		ByteParser parser = new ByteParser();

		Element tagElement = parser.getData(bytes, currentCursor, new Element(
				AttributeItem.tag));
		tagElement.setComment(VerificationTypeKind.ITEM_Uninitialized.name());
		view.printElement(tagElement);
		currentCursor += AttributeItem.tag.size();

		Element cpoolIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.offset));
		view.printElement(cpoolIndexElement);
		currentCursor += AttributeItem.offset.size();

		return currentCursor;
	}
}
