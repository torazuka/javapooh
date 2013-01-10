package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.value.impl.ElementValue;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class RuntimeAnnotations extends DefaultAttribute {

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

		Element typeIndexElement = getData(bytes, currentCursor, new Element(
				AttributeItem.type_index));
		view.printElement(typeIndexElement);
		currentCursor += AttributeItem.type_index.size();

		Element numElementValuePairsElement = getData(bytes, currentCursor,
				new Element(AttributeItem.num_element_value_pairs));
		view.printElement(numElementValuePairsElement);
		currentCursor += AttributeItem.num_element_value_pairs.size();

		currentCursor = getElementValuePairs(bytes, Integer.parseInt(
				Util.byteToString(numElementValuePairsElement.getBytes()), 16),
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
}
