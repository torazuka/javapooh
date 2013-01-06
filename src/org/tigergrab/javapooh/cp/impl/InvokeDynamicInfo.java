package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u2 bootstrap_method_attr_index; u2 name_and_type_index;
 */
public class InvokeDynamicInfo implements ConstantInfo {

	protected final int BOOTSTRAP_INDEX_SIZE = 2;
	protected final int NAME_AND_TYPE_INDEX_SIZE = 2;
	protected byte[] bootstrapIndexByte = new byte[BOOTSTRAP_INDEX_SIZE];
	protected byte[] nameAndTypeIndexByte = new byte[NAME_AND_TYPE_INDEX_SIZE];

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + BOOTSTRAP_INDEX_SIZE + NAME_AND_TYPE_INDEX_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getBootstrapMethodAttrIndex(bytes, cursor);
		getNameAndTypeIndex(bytes, cursor + BOOTSTRAP_INDEX_SIZE);
	}

	/**
	 * u2 name_index
	 */
	protected void getBootstrapMethodAttrIndex(final byte[] bytes,
			final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + BOOTSTRAP_INDEX_SIZE; i++) {
			bootstrapIndexByte[index++] = bytes[i];
		}
	}

	/**
	 * u2 name_index
	 */
	protected void getNameAndTypeIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + NAME_AND_TYPE_INDEX_SIZE; i++) {
			nameAndTypeIndexByte[index++] = bytes[i];
		}
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_InvokeDynamic;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u2", "bootstrap_method_attr_index",
				bootstrapIndexByte));
		result.add(new Element("u2", "name_and_type_index",
				nameAndTypeIndexByte));
		return result;
	}
}
