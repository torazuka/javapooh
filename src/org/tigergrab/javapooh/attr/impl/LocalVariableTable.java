package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class LocalVariableTable implements AttributeInfo {

	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element nameIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.LocalVariableTable.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		Element localVariableTableLengthElement = parser.getData(bytes,
				currentCursor, new Element(
						AttributeItem.local_variable_table_length));
		view.printElement(localVariableTableLengthElement);
		currentCursor += AttributeItem.local_variable_table_length.size();

		currentCursor = getLocalVariableTable(bytes,
				Util.byteToInt(localVariableTableLengthElement.getBytes()),
				currentCursor);

		return currentCursor;
	}

	protected int getLocalVariableTable(byte[] bytes, int length, int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		for (int i = 0; i < length; i++) {
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.start_pc)));
			currentCursor += AttributeItem.start_pc.size();
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.length)));
			currentCursor += AttributeItem.length.size();
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.name_index)));
			currentCursor += AttributeItem.name_index.size();
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.descriptor_index)));
			currentCursor += AttributeItem.descriptor_index.size();
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.index)));
			currentCursor += AttributeItem.index.size();
		}
		return currentCursor;
	}
}
