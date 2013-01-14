package org.tigergrab.javapooh.field.impl;

import java.util.HashMap;
import java.util.Map;

import org.tigergrab.javapooh.ClassItem;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.field.FieldItem;
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
		for (;;) {
			if (counter < entryNum + 1) {
				view.printCounter(counter, ClassItem.fields.name());
				currentCursor = getNextField(fieldsBytes, currentCursor);
				counter++;
				continue;
			}
			break;
		}
		view.printEnd(ClassItem.fields.name());
		return currentCursor;
	}

	protected int getNextField(byte[] bytes, int currentCursor) {
		int cursor = currentCursor;
		FieldInfo info = new FieldInfo();

		view.printElement(info.getData(bytes, cursor, new Element(
				FieldItem.access_flags)));
		cursor += FieldItem.access_flags.size();

		view.printElement(info.getData(bytes, cursor, new Element(
				FieldItem.name_index)));
		cursor += FieldItem.name_index.size();

		view.printElement(info.getData(bytes, cursor, new Element(
				FieldItem.descriptor_index)));
		cursor += FieldItem.descriptor_index.size();

		Element attributesCountElement = info.getData(bytes, cursor,
				new Element(FieldItem.attributes_count));
		view.printElement(attributesCountElement);
		cursor += FieldItem.attributes_count.size();

		cursor = info.getAttributes(bytes, constantPool, Integer.parseInt(
				Util.byteToString(attributesCountElement.getBytes()), 16),
				cursor);
		return cursor;
	}
}
