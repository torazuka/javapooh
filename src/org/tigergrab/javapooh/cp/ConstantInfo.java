package org.tigergrab.javapooh.cp;

import org.tigergrab.javapooh.view.impl.Element;

public interface ConstantInfo {
	public int getContents(final byte[] bytes, final int cursor);

	public Element getData(final byte[] byts, final int cursor,
			final Element ele);

}
