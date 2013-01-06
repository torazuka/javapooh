package org.tigergrab.javapooh.cp.impl;

import java.util.ArrayList;
import java.util.List;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u1 tag; u1 reference_kind; u2 reference_index;
 */
public class MethodHandleInfo implements ConstantInfo {

	protected final int REFERENCE_KIND_SIZE = 1;
	protected final int REFERENCE_INDEX_SIZE = 2;

	protected byte[] referenceKindByte = new byte[REFERENCE_KIND_SIZE];
	protected byte[] referenceIndexByte = new byte[REFERENCE_INDEX_SIZE];

	/**
	 * 1-9; See
	 * http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-5.html#jvms
	 * -5.4.3.5
	 */
	protected int referenceKind = 0;
	protected int referenceIndex = 0;

	@Override
	public int getMovedCursor(final int cursor) {
		return cursor + REFERENCE_KIND_SIZE + REFERENCE_INDEX_SIZE;
	}

	@Override
	public void getInfo(final byte[] bytes, final int cursor) {
		getReferenceKind(bytes, cursor);
		getReferenceKind(bytes, cursor + REFERENCE_KIND_SIZE);

		referenceIndex = convertReferenceKind(referenceKindByte);
		referenceKind = convertReferenceKind(referenceIndexByte);
	}

	protected void getReferenceKind(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + REFERENCE_KIND_SIZE; i++) {
			referenceKindByte[index++] = bytes[i];
		}
	}

	protected void getReferenceIndex(final byte[] bytes, final int cursor) {
		int index = 0;
		for (int i = cursor; i < cursor + REFERENCE_INDEX_SIZE; i++) {
			referenceIndexByte[index++] = bytes[i];
		}
	}

	protected int convertReferenceKind(final byte[] bytes) {
		String str = "";
		for (byte b : bytes) {
			str += Util.getHexString(b);
		}
		return Integer.parseInt(str, 16);
	}

	protected int convertReferenceIndex(final byte[] bytes) {
		String str = "";
		for (byte b : bytes) {
			str += Util.getHexString(b);
		}
		return Integer.parseInt(str, 16);
	}

	@Override
	public CpInfoTag getTag() {
		return CpInfoTag.Constant_MethodHandle;
	}

	@Override
	public List<Element> getElements(byte[] tagByte) {
		List<Element> result = new ArrayList<>();
		result.add(new Element("u1", "tag", tagByte, getTag().name()));
		result.add(new Element("u1", "reference_kind", referenceKindByte));
		result.add(new Element("u2", "reference_index", referenceIndexByte));
		return result;
	}
}
