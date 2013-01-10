package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

public class RuntimeInvisibleParameterAnnotations extends RuntimeAnnotations {

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element nameIndexElement = getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_name_index));
		nameIndexElement
				.setComment(AttributeKind.RuntimeInvisibleParameterAnnotations
						.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		Element attributeLengthElement = getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_length));
		view.printElement(attributeLengthElement);
		currentCursor += AttributeItem.attribute_length.size();

		Element numParametersElement = getData(bytes, currentCursor,
				new Element(AttributeItem.num_parameters));
		view.printElement(numParametersElement);
		currentCursor += AttributeItem.num_parameters.size();

		currentCursor = getParameterAnnotations(
				bytes,
				Integer.parseInt(
						Util.byteToString(numParametersElement.getBytes()), 16),
				currentCursor);

		return currentCursor;
	}

	public int getParameterAnnotations(final byte[] bytes, final int num,
			final int cursor) {
		int currentCursor = cursor;
		for (int i = 0; i < num; i++) {
			Element numAnnotationsElement = getData(bytes, currentCursor,
					new Element(AttributeItem.num_annotations));
			view.printElement(numAnnotationsElement);
			currentCursor += AttributeItem.num_annotations.size();

			currentCursor = getAnnotations(bytes, Integer.parseInt(
					Util.byteToString(numAnnotationsElement.getBytes()), 16),
					currentCursor);
		}
		return currentCursor;
	}
}
