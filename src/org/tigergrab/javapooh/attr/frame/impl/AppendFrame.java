package org.tigergrab.javapooh.attr.frame.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.frame.FrameType;
import org.tigergrab.javapooh.attr.frame.FrameTypeKind;
import org.tigergrab.javapooh.attr.verify.impl.VerificationTypeControl;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class AppendFrame implements FrameType {

	@Override
	public int execute(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		ByteParser parser = new ByteParser();
		PromptView view = new PromptView();

		Element frameTypeElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.frame_type));
		frameTypeElement.setComment(FrameTypeKind.append_frame.name());
		view.printElement(frameTypeElement);
		currentCursor += AttributeItem.frame_type.size();
		int len = Util.byteToInt(frameTypeElement.getBytes());

		Element offsetDeltaElemet = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.offset_delta));
		view.printElement(offsetDeltaElemet);
		currentCursor += AttributeItem.offset_delta.size();

		VerificationTypeControl control = new VerificationTypeControl();
		for (int i = 0; i < len - 251; i++) {
			currentCursor = control.getVerificationTypeInfo(bytes,
					currentCursor);
		}
		return currentCursor;
	}
}
