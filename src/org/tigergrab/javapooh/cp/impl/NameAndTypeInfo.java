package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

/**
 * u1 tag; u2 name_index; u2 descriptor_index;
 */
public class NameAndTypeInfo implements ConstantInfo {

	protected final PromptView view = new PromptView();

	protected final DefaultConstantInfo defaultInfo = new DefaultConstantInfo();

	@Override
	public Element getData(final byte[] byts, final int cursor,
			final Element ele) {
		return defaultInfo.getData(byts, cursor, ele);
	}

	@Override
	public int getContents(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element tagElement = getData(bytes, currentCursor, new Element(
				CpItem.tag));
		tagElement.setComment(CpInfoTag.Constant_NameAndType.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.name_index)));
		currentCursor += CpItem.name_index.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.descriptor_index)));
		currentCursor += CpItem.descriptor_index.size();

		return currentCursor;
	}
}
