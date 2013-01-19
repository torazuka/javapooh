package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.value.Value;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class ArrayValue implements Value {

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();
		PromptView view = new PromptView();

		Element numValueElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.num_value));
		view.printElement(numValueElement);
		currentCursor += AttributeItem.num_value.size();

		currentCursor = getValues(bytes,
				Util.byteToInt(numValueElement.getBytes()), currentCursor);

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
