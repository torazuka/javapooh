package org.tigergrab.javapooh.field.impl;

import java.util.HashMap;
import java.util.Map;

import org.tigergrab.javapooh.ClassItem;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.field.FieldItem;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class FieldInfoControl {

	protected Map<Integer, ConstantInfo> constantPool = new HashMap<>();

	protected final PromptView view = new PromptView();
	protected byte[] fieldsBytes;
	protected int entryNum;

	public FieldInfoControl(final byte[] bytes, final int fieldsCount,
			final Map<Integer, ConstantInfo> map) {
		fieldsBytes = bytes;
		entryNum = fieldsCount;
		constantPool = map;
	}

	public int getFieldInfo(final int cursor) {
		view.printBegin(ClassItem.fields.name());
		int counter = 1;
		int currentCursor = cursor;
		for (; counter < entryNum + 1; counter++) {
			view.printCounter(counter, ClassItem.fields.name());
			currentCursor = getNextField(fieldsBytes, currentCursor);
		}
		view.printEnd(ClassItem.fields.name());
		return currentCursor;
	}

	protected int getNextField(byte[] bytes, int currentCursor) {
		int cursor = currentCursor;
		ByteParser parser = new ByteParser();

		view.printElement(parser.getData(bytes, cursor, new Element(
				FieldItem.access_flags)));
		cursor += FieldItem.access_flags.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				FieldItem.name_index)));
		cursor += FieldItem.name_index.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				FieldItem.descriptor_index)));
		cursor += FieldItem.descriptor_index.size();

		Element attributesCountElement = parser.getData(bytes, cursor,
				new Element(FieldItem.attributes_count));
		view.printElement(attributesCountElement);
		cursor += FieldItem.attributes_count.size();

		FieldInfo info = new FieldInfo();
		cursor = info.getAttributes(bytes, constantPool,
				Util.byteToInt(attributesCountElement.getBytes()), cursor);
		return cursor;
	}
}
