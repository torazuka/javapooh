package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.impl.AttributeItem;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

public class ArrayValue extends DefaultValue {

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element numValueElement = getData(bytes, currentCursor, new Element(
				AttributeItem.num_value));
		view.printElement(numValueElement);
		currentCursor += AttributeItem.num_value.size();

		currentCursor = getValues(bytes, Integer.parseInt(
				Util.byteToString(numValueElement.getBytes()), 16),
				currentCursor);

		return currentCursor;
	}

	protected int getValues(final byte[] bytes, final int num, final int cursor) {
		int currentCursor = cursor;
		for (int i = 0; i < num; i++) {
			ElementValue ev = new ElementValue();
			currentCursor = ev.getInfo(bytes, currentCursor);
		}
		return currentCursor;
	}
}
