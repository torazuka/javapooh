package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class LineNumberTable implements AttributeInfo {

	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element nameIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.LineNumberTable.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		Element lineNumberTableLengthElement = parser.getData(bytes,
				currentCursor, new Element(
						AttributeItem.line_number_table_length));
		view.printElement(lineNumberTableLengthElement);
		currentCursor += AttributeItem.line_number_table_length.size();

		currentCursor = getLineNumberTable(bytes,
				Util.byteToInt(lineNumberTableLengthElement.getBytes()),
				currentCursor);

		return currentCursor;
	}

	protected int getLineNumberTable(byte[] bytes, int length, int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		for (int i = 0; i < length; i++) {
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.start_pc)));
			currentCursor += AttributeItem.start_pc.size();
			view.printElement(parser.getData(bytes, currentCursor, new Element(
					AttributeItem.line_number)));
			currentCursor += AttributeItem.line_number.size();
		}
		return currentCursor;
	}
}
