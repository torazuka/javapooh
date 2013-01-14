package org.tigergrab.javapooh.attr.impl;

import java.util.HashMap;
import java.util.Map;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.method.impl.MethodInfo;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class Code extends DefaultAttribute {

	protected final PromptView view = new PromptView();
	protected Map<Integer, ConstantInfo> constantPool = new HashMap<>();

	public Code(Map<Integer, ConstantInfo> pool) {
		constantPool = pool;
	}

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element nameIndexElement = getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.Code.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.max_stack)));
		currentCursor += AttributeItem.max_stack.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				AttributeItem.max_locals)));
		currentCursor += AttributeItem.max_locals.size();

		Element codeLengthElement = getData(bytes, currentCursor, new Element(
				AttributeItem.code_length));
		view.printElement(codeLengthElement);
		currentCursor += AttributeItem.code_length.size();

		currentCursor = getCode(
				bytes,
				Integer.parseInt(
						Util.byteToString(codeLengthElement.getBytes()), 16),
				currentCursor);

		Element exceptionTableLengthElement = getData(bytes, currentCursor,
				new Element(AttributeItem.exception_table_length));
		view.printElement(exceptionTableLengthElement);
		currentCursor += AttributeItem.exception_table_length.size();

		currentCursor = getExceptionTable(bytes, Integer.parseInt(
				Util.byteToString(exceptionTableLengthElement.getBytes()), 16),
				currentCursor);

		Element attributesCountElement = getData(bytes, currentCursor,
				new Element(AttributeItem.attributes_count));
		view.printElement(attributesCountElement);
		currentCursor += AttributeItem.attributes_count.size();

		MethodInfo info = new MethodInfo();
		currentCursor = info.getAttributes(bytes, constantPool, Integer
				.parseInt(Util.byteToString(attributesCountElement.getBytes()),
						16), currentCursor);

		return currentCursor;
	}

	protected int getExceptionTable(byte[] bytes, int length, int cursor) {
		int currentCursor = cursor;
		for (int i = 0; i < length; i++) {
			view.printElement(getData(bytes, currentCursor, new Element(
					AttributeItem.start_pc)));
			currentCursor += AttributeItem.start_pc.size();
			view.printElement(getData(bytes, currentCursor, new Element(
					AttributeItem.end_pc)));
			currentCursor += AttributeItem.end_pc.size();
			view.printElement(getData(bytes, currentCursor, new Element(
					AttributeItem.handler_pc)));
			currentCursor += AttributeItem.handler_pc.size();
			view.printElement(getData(bytes, currentCursor, new Element(
					AttributeItem.catch_type)));
			currentCursor += AttributeItem.catch_type.size();
		}
		return currentCursor;
	}

	protected int getCode(final byte[] bytes, final int length, final int cursor) {
		Element element = new Element(AttributeItem.code);
		int currentCursor = cursor;
		byte[] bt = new byte[length];
		int index = 0;
		for (int i = cursor; i < cursor + length; i++) {
			bt[index++] = bytes[i];
			currentCursor += AttributeItem.code.size();
		}
		element.setBytes(bt);
		view.printElement(element);
		return currentCursor;
	}
}
