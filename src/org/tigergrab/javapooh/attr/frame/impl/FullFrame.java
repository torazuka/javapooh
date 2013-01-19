package org.tigergrab.javapooh.attr.frame.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.frame.FrameType;
import org.tigergrab.javapooh.attr.frame.FrameTypeKind;
import org.tigergrab.javapooh.attr.verify.impl.VerificationTypeControl;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class FullFrame implements FrameType {
	@Override
	public int execute(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		ByteParser parser = new ByteParser();
		PromptView view = new PromptView();

		Element frameTypeElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.frame_type));
		frameTypeElement.setComment(FrameTypeKind.full_frame.name());
		view.printElement(frameTypeElement);
		currentCursor += AttributeItem.frame_type.size();

		Element offsetDeltaElemet = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.offset_delta));
		view.printElement(offsetDeltaElemet);
		currentCursor += AttributeItem.offset_delta.size();

		Element numberOfLocalsElemet = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.number_of_locals));
		view.printElement(numberOfLocalsElemet);
		currentCursor += AttributeItem.number_of_locals.size();
		int numLocals = Util.byteToInt(numberOfLocalsElemet.getBytes());

		VerificationTypeControl control = new VerificationTypeControl();
		for (int i = 0; i < numLocals; i++) {
			currentCursor = control.getVerificationTypeInfo(bytes,
					currentCursor);
		}

		Element numberOfStackItemsElemet = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.number_of_stack_items));
		view.printElement(numberOfStackItemsElemet);
		currentCursor += AttributeItem.number_of_stack_items.size();
		int numStack = Util.byteToInt(numberOfStackItemsElemet.getBytes());

		for (int i = 0; i < numStack; i++) {
			currentCursor = control.getVerificationTypeInfo(bytes,
					currentCursor);
		}
		return currentCursor;
	}
}