package org.tigergrab.javapooh.attr.verify.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.verify.VerificationType;
import org.tigergrab.javapooh.attr.verify.VerificationTypeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class VerificationTypeDefault implements VerificationType {

	protected VerificationTypeKind type;

	public VerificationTypeDefault() {
	}

	public VerificationTypeDefault(VerificationTypeKind vt) {
		type = vt;
	}

	@Override
	public int execute(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		ByteParser parser = new ByteParser();
		Element tagElement = parser.getData(bytes, currentCursor, new Element(
				AttributeItem.tag));
		if (type != null) {
			tagElement.setComment(type.name());
		}

		PromptView view = new PromptView();
		view.printElement(tagElement);

		currentCursor += AttributeItem.tag.size();
		return currentCursor;
	}

}
