package org.tigergrab.javapooh.attr.frame.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.frame.FrameType;
import org.tigergrab.javapooh.attr.frame.FrameTypeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class FrameTypeOffset implements FrameType {

	protected FrameTypeKind type;

	public FrameTypeOffset() {
	}

	public FrameTypeOffset(FrameTypeKind ft) {
		type = ft;
	}

	@Override
	public int execute(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		ByteParser parser = new ByteParser();
		PromptView view = new PromptView();

		Element frameTypeElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.frame_type));
		if (type != null) {
			frameTypeElement.setComment(type.name());
		}
		view.printElement(frameTypeElement);
		currentCursor += AttributeItem.frame_type.size();

		Element offsetDeltaElemet = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.offset_delta));
		view.printElement(offsetDeltaElemet);
		currentCursor += AttributeItem.offset_delta.size();

		return currentCursor;
	}
}
