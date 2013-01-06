package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u2 class_index; u2 name_and_type_index;
 */
public class MethodrefInfo implements ConstantInfo {

	protected final int CLASS_INDEX_SIZE = 2;
	protected final int NAME_AND_TYPE_INDEX_SIZE = 2;

	protected byte[] classIndexByte = new byte[CLASS_INDEX_SIZE];
	protected byte[] nameAndTypeIndexByte = new byte[NAME_AND_TYPE_INDEX_SIZE];

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + CLASS_INDEX_SIZE + NAME_AND_TYPE_INDEX_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getClassIndex(bytes, cursor);
		getNameAndTypeIndex(bytes, cursor + CLASS_INDEX_SIZE);

	}

	protected void getClassIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + CLASS_INDEX_SIZE; i++) {
			classIndexByte[index++] = bytes[i];
		}
	}

	protected void getNameAndTypeIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + NAME_AND_TYPE_INDEX_SIZE; i++) {
			nameAndTypeIndexByte[index++] = bytes[i];
		}
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_Methodref;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u2", "class_index", classIndexByte));
		result.add(new Element("u2", "name_and_type_index",
				nameAndTypeIndexByte));
		return result;
	}
}
