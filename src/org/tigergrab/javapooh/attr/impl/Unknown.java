package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class Unknown extends DefaultAttribute {
	private static class ParseResult {
		final Element element;
		final int next;

		ParseResult(final Element element, final int next) {
			this.element = element;
			this.next = next;
		}
	}

	private final PromptView view = new PromptView();

	private ParseResult parse(final byte[] bytes, final int position,
			final AttributeItem item) {
		final Element element = getData(bytes, position, new Element(item));
		return new ParseResult(element, position + item.size());
	}

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		final ParseResult attrNameIndex = parse(bytes, cursor,
				AttributeItem.attribute_name_index);
		attrNameIndex.element.setComment("UNKNOWN");
		view.printElement(attrNameIndex.element);

		final ParseResult attrLength = parse(bytes, attrNameIndex.next,
				AttributeItem.attribute_length);
		view.printElement(attrLength.element);

		final int skipLength = Integer.parseInt(
				Util.byteToString(attrLength.element.getBytes()), 16);

		return attrLength.next + skipLength;
	}
}
