package org.tigergrab.javapooh.cp;

import org.tigergrab.javapooh.cp.impl.ClassInfo;
import org.tigergrab.javapooh.cp.impl.DoubleInfo;
import org.tigergrab.javapooh.cp.impl.FieldrefInfo;
import org.tigergrab.javapooh.cp.impl.FloatInfo;
import org.tigergrab.javapooh.cp.impl.IntegerInfo;
import org.tigergrab.javapooh.cp.impl.InterfaceMethodrefInfo;
import org.tigergrab.javapooh.cp.impl.InvokeDynamicInfo;
import org.tigergrab.javapooh.cp.impl.LongInfo;
import org.tigergrab.javapooh.cp.impl.MethodHandleInfo;
import org.tigergrab.javapooh.cp.impl.MethodTypeInfo;
import org.tigergrab.javapooh.cp.impl.MethodrefInfo;
import org.tigergrab.javapooh.cp.impl.NameAndTypeInfo;
import org.tigergrab.javapooh.cp.impl.StringInfo;
import org.tigergrab.javapooh.cp.impl.Utf8Info;

public enum CpInfoTag {

	Constant_Class(7, (byte) 0x07) {
		@Override
		public ConstantInfo make() {
			return new ClassInfo();
		}
	},
	Constant_Fieldref(9, (byte) 0x09) {
		@Override
		public ConstantInfo make() {
			return new FieldrefInfo();
		}
	},
	Constant_Methodref(10, (byte) 0x0a) {
		@Override
		public ConstantInfo make() {
			return new MethodrefInfo();
		}
	},
	Constant_InterfaceMethodref(11, (byte) 0x0b) {
		@Override
		public ConstantInfo make() {
			return new InterfaceMethodrefInfo();
		}
	},
	Constant_String(8, (byte) 0x08) {
		@Override
		public ConstantInfo make() {
			return new StringInfo();
		}
	},
	Constant_Integer(3, (byte) 0x03) {
		@Override
		public ConstantInfo make() {
			return new IntegerInfo();
		}
	},
	Constant_Float(4, (byte) 0x04) {
		@Override
		public ConstantInfo make() {
			return new FloatInfo();
		}
	},
	Constant_Long(5, (byte) 0x05) {
		@Override
		public ConstantInfo make() {
			return new LongInfo();
		}
	},
	Constant_Double(6, (byte) 0x06) {
		@Override
		public ConstantInfo make() {
			return new DoubleInfo();
		}
	},
	Constant_NameAndType(12, (byte) 0x0c) {
		@Override
		public ConstantInfo make() {
			return new NameAndTypeInfo();
		}
	},
	Constant_Utf8(1, (byte) 0x01) {
		@Override
		public ConstantInfo make() {
			return new Utf8Info();
		}
	},
	Constant_MethodHandle(15, (byte) 0x0f) {
		@Override
		public ConstantInfo make() {
			return new MethodHandleInfo();
		}
	},
	Constant_MethodType(16, (byte) 0x10) {
		@Override
		public ConstantInfo make() {
			return new MethodTypeInfo();
		}
	},
	Constant_InvokeDynamic(18, (byte) 0x12) {
		@Override
		public ConstantInfo make() {
			return new InvokeDynamicInfo();
		}
	};

	int tag;
	byte bytes;

	public abstract ConstantInfo make();

	private CpInfoTag(int i, byte b) {
		tag = i;
		bytes = b;
	};

	public int getTag() {
		return this.tag;
	}

	public byte getBytes() {
		return bytes;
	}

	@Override
	public String toString() {
		return String.valueOf(this.tag);
	}
}
