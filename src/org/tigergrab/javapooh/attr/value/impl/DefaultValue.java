package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.value.Value;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class DefaultValue implements Value {

	protected final PromptView view = new PromptView();

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

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		return -1;
	}
}
