package org.tigergrab.javapooh.attr.value.impl;

import java.util.EnumSet;

import org.tigergrab.javapooh.attr.impl.AttributeItem;
import org.tigergrab.javapooh.attr.value.ElementValueTag;
import org.tigergrab.javapooh.attr.value.Value;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class ElementValue {

	protected final PromptView view = new PromptView();

	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element tagElement = getData(bytes, currentCursor, new Element(
				AttributeItem.tag));
		view.printElement(tagElement);
		currentCursor += AttributeItem.tag.size();

		String tagStr = Util.byteToString(tagElement.getBytes());

		Value val = null;
		EnumSet<ElementValueTag> allTag = EnumSet.allOf(ElementValueTag.class);
		for (ElementValueTag evl : allTag) {
			if (tagStr.equals(evl.tag())) {
				val = evl.make();
			}
		}

		currentCursor = val.getValue(bytes, currentCursor);

		return currentCursor;
	}

	public Element getData(final byte[] sourceBytes, final int cursor,
			final Element ele) {
		Element result = new Element(ele);
		int index = 0;
		byte[] bt = new byte[ele.getSize()];
		for (int i = cursor; i < cursor + ele.getSize(); i++) {
			bt[index++] = sourceBytes[i];
		}
		result.setBytes(bt);
		return result;
	}
}
