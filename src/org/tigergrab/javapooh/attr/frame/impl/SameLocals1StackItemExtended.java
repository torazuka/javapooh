package org.tigergrab.javapooh.attr.frame.impl;

import org.tigergrab.javapooh.attr.frame.FrameTypeKind;
import org.tigergrab.javapooh.attr.verify.impl.VerificationTypeControl;

public class SameLocals1StackItemExtended extends FrameTypeOffset {
	@Override
	public int execute(byte[] bytes, int cursor) {
		type = FrameTypeKind.same_locals_1_stack_item_frame_extended;
		int currentCursor = super.execute(bytes, cursor);

		VerificationTypeControl control = new VerificationTypeControl();
		currentCursor = control.getVerificationTypeInfo(bytes, currentCursor);
		return currentCursor;
	}
}
