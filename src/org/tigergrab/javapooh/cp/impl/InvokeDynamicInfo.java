package org.tigergrab.javapooh.cp.impl;

import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

/**
 * u1 tag; u2 bootstrap_method_attr_index; u2 name_and_type_index;
 */
public class InvokeDynamicInfo implements ConstantInfo {

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
		tagElement.setComment(CpInfoTag.Constant_InvokeDynamic.name());
		view.printElement(tagElement);
		currentCursor += CpItem.tag.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.bootstrap_method_attr_index)));
		currentCursor += CpItem.bootstrap_method_attr_index.size();

		view.printElement(getData(bytes, currentCursor, new Element(
				CpItem.name_and_type_index)));
		currentCursor += CpItem.name_and_type_index.size();

		return currentCursor;
	}
}
