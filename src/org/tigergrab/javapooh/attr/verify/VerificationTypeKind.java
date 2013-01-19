package org.tigergrab.javapooh.attr.verify;

import org.tigergrab.javapooh.Item;
import org.tigergrab.javapooh.ItemType;
import org.tigergrab.javapooh.attr.verify.impl.ItemObject;
import org.tigergrab.javapooh.attr.verify.impl.ItemUninitialized;
import org.tigergrab.javapooh.attr.verify.impl.VerificationTypeDefault;

public enum VerificationTypeKind implements Item {
	ITEM_Top(0) {
		@Override
		public VerificationType make() {
			return new VerificationTypeDefault(ITEM_Top);
		}
	},
	ITEM_Integer(1) {
		@Override
		public VerificationType make() {
			return new VerificationTypeDefault(ITEM_Integer);
		}
	},
	ITEM_Float(2) {
		@Override
		public VerificationType make() {
			return new VerificationTypeDefault(ITEM_Float);
		}
	},
	ITEM_Long(3) {
		@Override
		public VerificationType make() {
			return new VerificationTypeDefault(ITEM_Long);
		}
	},
	ITEM_Double(4) {
		@Override
		public VerificationType make() {
			return new VerificationTypeDefault(ITEM_Double);
		}
	},
	ITEM_Null(5) {
		@Override
		public VerificationType make() {
			return new VerificationTypeDefault(ITEM_Null);
		}
	},
	ITEM_UninitializedThis(6) {
		@Override
		public VerificationType make() {
			return new VerificationTypeDefault(ITEM_UninitializedThis);
		}
	},
	ITEM_Object(7) {
		@Override
		public VerificationType make() {
			return new ItemObject();
		}
	},
	ITEM_Uninitialized(8) {
		@Override
		public VerificationType make() {
			return new ItemUninitialized();
		}
	};

	final int value;

	private VerificationTypeKind(int n) {
		value = n;
	}

	public int getValue() {
		return value;
	}

	public abstract VerificationType make();

	@Override
	public String type() {
		return name();
	}

	@Override
	public int size() {
		return ItemType.u1.size();
	}

}
