package org.tigergrab.javapooh.method.impl;

import java.util.HashMap;
import java.util.Map;

import org.tigergrab.javapooh.ClassItem;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.method.MethodItem;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class MethodInfoControl {

	protected Map<Integer, ConstantInfo> constantPool = new HashMap<>();;

	protected final PromptView view = new PromptView();
	protected byte[] fieldsBytes;
	protected int entryNum;

	public MethodInfoControl(final byte[] bytes, final int fieldsCount,
			final Map<Integer, ConstantInfo> map) {
		fieldsBytes = bytes;
		entryNum = fieldsCount;
		constantPool = map;
	}

	public int getMethodInfo(final int cursor) {
		view.printBegin(ClassItem.methods.name());
		int counter = 1;
		int currentCursor = cursor;
		for (; counter < entryNum + 1; counter++) {
			view.printCounter(counter, ClassItem.methods.name());
			currentCursor = getNextMethod(fieldsBytes, currentCursor);
		}
		view.printEnd(ClassItem.methods.name());
		return currentCursor;
	}

	protected int getNextMethod(byte[] bytes, int currentCursor) {
		int cursor = currentCursor;
		ByteParser parser = new ByteParser();

		view.printElement(parser.getData(bytes, cursor, new Element(
				MethodItem.access_flags)));
		cursor += MethodItem.access_flags.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				MethodItem.name_index)));
		cursor += MethodItem.name_index.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				MethodItem.descriptor_index)));
		cursor += MethodItem.descriptor_index.size();

		Element attributesCountElement = parser.getData(bytes, cursor,
				new Element(MethodItem.attributes_count));
		view.printElement(attributesCountElement);
		cursor += MethodItem.attributes_count.size();

		MethodInfo info = new MethodInfo();
		cursor = info.getAttributes(bytes, constantPool,
				Util.byteToInt(attributesCountElement.getBytes()), cursor);
		return cursor;
	}
}
