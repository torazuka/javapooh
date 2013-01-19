package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

/**
 * u1 tag; u2 descriptor_index;
 */
public class MethodTypeInfo implements ConstantInfo {

	protected final PromptView view = new PromptView();

	@Override
	public int getContents(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element tagElement = parser.getData(bytes, currentCursor, new Element(
				CpItem.tag));
		tagElement.setComment(CpInfoTag.Constant_MethodType.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				CpItem.descriptor_index)));
		currentCursor += CpItem.descriptor_index.size();

		return currentCursor;
	}
}
