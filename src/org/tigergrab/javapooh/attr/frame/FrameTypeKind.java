package org.tigergrab.javapooh.attr.frame;

import org.tigergrab.javapooh.Item;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.frame.impl.AppendFrame;
import org.tigergrab.javapooh.attr.frame.impl.FrameTypeDefault;
import org.tigergrab.javapooh.attr.frame.impl.FrameTypeOffset;
import org.tigergrab.javapooh.attr.frame.impl.FullFrame;
import org.tigergrab.javapooh.attr.frame.impl.SameLocals1StackItem;
import org.tigergrab.javapooh.attr.frame.impl.SameLocals1StackItemExtended;

public enum FrameTypeKind implements Item {
	same_frame(0, "SAME") {
		@Override
		public FrameType make() {
			return new FrameTypeDefault(same_frame);
		}
	},
	same_locals_1_stack_item_frame(1, "SAME_LOCALS_1_STACK_ITEM") {
		@Override
		public FrameType make() {
			return new SameLocals1StackItem();
		}
	},
	same_locals_1_stack_item_frame_extended(2,
			"SAME_LOCALS_1_STACK_ITEM_EXTENDED") {
		@Override
		public FrameType make() {
			return new SameLocals1StackItemExtended();
		}
	},
	chop_frame(3, "CHOP") {
		@Override
		public FrameType make() {
			return new FrameTypeOffset(chop_frame);
		}
	},
	same_frame_extended(4, "SAME_FRAME_EXTENDED") {
		@Override
		public FrameType make() {
			return new FrameTypeOffset(same_frame_extended);
		}
	},
	append_frame(5, "APPEND") {
		@Override
		public FrameType make() {
			return new AppendFrame();
		}
	},
	full_frame(6, "FULL_FRAME") {
		@Override
		public FrameType make() {
			return new FullFrame();
		}
	};

	private int id;
	private String comment;

	public abstract FrameType make();

	private FrameTypeKind(int n, String str) {
		id = n;
		comment = str;
	}

	public int getId() {
		return id;
	}

	public String comment() {
		return comment;
	}

	@Override
	public String type() {
		return AttributeItem.frame_type.type();
	}

	@Override
	public int size() {
		return AttributeItem.frame_type.size();
	}
}
