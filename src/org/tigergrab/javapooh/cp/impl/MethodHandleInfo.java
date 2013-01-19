package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

/**
 * u1 tag; u1 reference_kind; u2 reference_index;
 */
public class MethodHandleInfo implements ConstantInfo {

	protected final PromptView view = new PromptView();

	@Override
	public int getContents(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element tagElement = parser.getData(bytes, currentCursor, new Element(
				CpItem.tag));
		tagElement.setComment(CpInfoTag.Constant_MethodHandle.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				CpItem.reference_kind)));
		currentCursor += CpItem.reference_kind.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				CpItem.reference_index)));
		currentCursor += CpItem.reference_index.size();

		return currentCursor;
	}
}
