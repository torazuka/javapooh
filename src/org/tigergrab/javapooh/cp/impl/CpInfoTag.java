package org.tigergrab.javapooh.cp.impl;

public enum CpInfoTag {

	Constant_Class(7, (byte) 0x07), Constant_Fieldref(9, (byte) 0x09), Constant_Methodref(
			10, (byte) 0x0a), Constant_InterfaceMethodref(11, (byte) 0x0b), Constant_String(
			8, (byte) 0x08), Constant_Integer(3, (byte) 0x03), Constant_Float(
			4, (byte) 0x04), Constant_Long(5, (byte) 0x05), Constant_Double(6,
			(byte) 0x06), Constant_NameAndType(12, (byte) 0x0c), Constant_Utf8(
			1, (byte) 0x01), Constant_MethodHandle(15, (byte) 0x0f), Constant_MethodType(
			16, (byte) 0x10), Constant_InvokeDynamic(18, (byte) 0x12);

	int tag;
	byte bytes;

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
