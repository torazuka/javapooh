package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

public class RuntimeVisibleParameterAnnotations extends RuntimeAnnotations {

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element nameIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_name_index));
		nameIndexElement
				.setComment(AttributeKind.RuntimeVisibleParameterAnnotations
						.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		Element attributeLengthElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_length));
		view.printElement(attributeLengthElement);
		currentCursor += AttributeItem.attribute_length.size();

		Element numParametersElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.num_parameters));
		view.printElement(numParametersElement);
		currentCursor += AttributeItem.num_parameters.size();

		currentCursor = getParameterAnnotations(bytes,
				Util.byteToInt(numParametersElement.getBytes()), currentCursor);

		return currentCursor;
	}

	public int getParameterAnnotations(final byte[] bytes, final int num,
			final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		for (int i = 0; i < num; i++) {
			Element numAnnotationsElement = parser.getData(bytes,
					currentCursor, new Element(AttributeItem.num_annotations));
			view.printElement(numAnnotationsElement);
			currentCursor += AttributeItem.num_annotations.size();

			currentCursor = getAnnotations(bytes,
					Util.byteToInt(numAnnotationsElement.getBytes()),
					currentCursor);
		}
		return currentCursor;
	}
}
