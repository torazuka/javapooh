package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class InnerClasses implements AttributeInfo {
	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element nameIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.InnerClasses.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.number_of_classes)));
		currentCursor += AttributeItem.number_of_classes.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.inner_class_info_index)));
		currentCursor += AttributeItem.inner_class_info_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.outer_class_info_index)));
		currentCursor += AttributeItem.outer_class_info_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.inner_name_index)));
		currentCursor += AttributeItem.inner_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.innner_class_access_flags)));
		currentCursor += AttributeItem.innner_class_access_flags.size();
		return currentCursor;
	}
}
