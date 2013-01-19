package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.value.impl.ElementValue;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class RuntimeAnnotations implements AttributeInfo {

	protected final PromptView view = new PromptView();

	public int getAnnotations(final byte[] bytes, final int num,
			final int cursor) {
		int currentCursor = cursor;
		for (int i = 0; i < num; i++) {
			currentCursor = getAnnotation(bytes, currentCursor);
		}
		return currentCursor;
	}

	public int getAnnotation(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element typeIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.type_index));
		view.printElement(typeIndexElement);
		currentCursor += AttributeItem.type_index.size();

		Element numElementValuePairsElement = parser.getData(bytes,
				currentCursor, new Element(
						AttributeItem.num_element_value_pairs));
		view.printElement(numElementValuePairsElement);
		currentCursor += AttributeItem.num_element_value_pairs.size();

		currentCursor = getElementValuePairs(bytes,
				Util.byteToInt(numElementValuePairsElement.getBytes()),
				currentCursor);

		return currentCursor;
	}

	protected int getElementValuePairs(final byte[] bytes, final int num,
			final int cursor) {
		// num <= 65535
		int currentCursor = cursor;

		ElementValue ev = new ElementValue();
		currentCursor = ev.getInfo(bytes, currentCursor);

		return currentCursor;
	}

	@Override
	public int getInfo(byte[] bytes, int cursor) {
		return -1;
	}

}
