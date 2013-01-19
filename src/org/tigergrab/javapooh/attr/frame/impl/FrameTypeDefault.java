package org.tigergrab.javapooh.attr.frame.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.frame.FrameType;
import org.tigergrab.javapooh.attr.frame.FrameTypeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class FrameTypeDefault implements FrameType {

	protected FrameTypeKind type;

	public FrameTypeDefault() {
	}

	public FrameTypeDefault(FrameTypeKind ft) {
		type = ft;
	}

	@Override
	public int execute(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		ByteParser parser = new ByteParser();
		Element frameTypeElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.frame_type));
		if (type != null) {
			frameTypeElement.setComment(type.name());
		}

		PromptView view = new PromptView();
		view.printElement(frameTypeElement);

		currentCursor += AttributeItem.frame_type.size();
		return currentCursor;
	}
}
