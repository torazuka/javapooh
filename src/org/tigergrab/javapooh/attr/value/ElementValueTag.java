package org.tigergrab.javapooh.attr.value;

import org.tigergrab.javapooh.attr.value.impl.AnnotationValue;
import org.tigergrab.javapooh.attr.value.impl.ArrayValue;
import org.tigergrab.javapooh.attr.value.impl.ClassInfoIndex;
import org.tigergrab.javapooh.attr.value.impl.ConstValueIndex;
import org.tigergrab.javapooh.attr.value.impl.EnumConstValue;

public enum ElementValueTag {
	B("B") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	C("C") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	D("D") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	F("F") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	I("I") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	J("J") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	S("S") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	Z("Z") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	s("s") {
		@Override
		public Value make() {
			return new ConstValueIndex();
		}
	},
	e("e") {
		@Override
		public Value make() {
			return new EnumConstValue();
		}
	},
	c("c") {
		@Override
		public Value make() {
			return new ClassInfoIndex();
		}
	},
	a("@") {
		@Override
		public Value make() {
			return new AnnotationValue();
		}
	},
	r("[") {
		@Override
		public Value make() {
			return new ArrayValue();
		}
	};

	String tag;

	private ElementValueTag(String s) {
		tag = s;
	}

	public String tag() {
		return tag;
	}

	public abstract Value make();
}
