package org.tigergrab.javapooh.attr.frame.impl;

import org.tigergrab.javapooh.attr.frame.FrameTypeKind;
import org.tigergrab.javapooh.attr.verify.impl.VerificationTypeControl;

public class SameLocals1StackItem extends FrameTypeDefault {
	@Override
	public int execute(byte[] bytes, int cursor) {
		type = FrameTypeKind.same_locals_1_stack_item_frame;
		int currentCursor = super.execute(bytes, cursor);

		VerificationTypeControl control = new VerificationTypeControl();
		currentCursor = control.getVerificationTypeInfo(bytes, currentCursor);
		return currentCursor;
	}
}
