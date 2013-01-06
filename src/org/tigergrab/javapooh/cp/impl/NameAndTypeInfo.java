package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u2 name_index; u2 descriptor_index;
 */
public class NameAndTypeInfo implements ConstantInfo {

	protected final int NAME_INDEX_SIZE = 2;
	protected final int DESCRIPTOR_INDEX_SIZE = 2;
	protected byte[] nameIndexByte = new byte[NAME_INDEX_SIZE];
	protected byte[] descriptorIndexByte = new byte[DESCRIPTOR_INDEX_SIZE];

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + NAME_INDEX_SIZE + DESCRIPTOR_INDEX_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getNameIndex(bytes, cursor);
		getDescriptorIndex(bytes, cursor + NAME_INDEX_SIZE);
	}

	protected void getNameIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + NAME_INDEX_SIZE; i++) {
			nameIndexByte[index++] = bytes[i];
		}
	}

	protected void getDescriptorIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + DESCRIPTOR_INDEX_SIZE; i++) {
			descriptorIndexByte[index++] = bytes[i];
		}
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_NameAndType;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u2", "name_index", nameIndexByte));
		result.add(new Element("u2", "descriptor_index", descriptorIndexByte));
		return result;
	}
}
