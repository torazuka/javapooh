package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u2 descriptor_index;
 */
public class MethodTypeInfo implements ConstantInfo {

	protected final int DESCRIPTOR_INDEX_SIZE = 2;
	protected byte[] descriptorIndexByte = new byte[DESCRIPTOR_INDEX_SIZE];

	protected int descriptorIndex = 0;

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + DESCRIPTOR_INDEX_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getDescriptorIndex(bytes, cursor);
		descriptorIndex = convertDescriptorIndex(descriptorIndexByte);
	}

	protected void getDescriptorIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + DESCRIPTOR_INDEX_SIZE; i++) {
			descriptorIndexByte[index++] = bytes[i];
		}
	}

	protected int convertDescriptorIndex(final byte[] bytes) {
		String str = "";
		for (byte b : bytes) {
			str += Util.getHexString(b);
		}
		return Integer.parseInt(str, 16);
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_MethodType;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u2", "descriptor_index", descriptorIndexByte));
		return result;
	}
}
