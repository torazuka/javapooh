package org.tigergrab.javapooh.cp;

import java.util.List;

import org.tigergrab.javapooh.cp.impl.CpInfoTag;
import org.tigergrab.javapooh.view.impl.Element;

public interface ConstantInfo {

	public void getInfo(final byte[] bytes, final int cursor);

	public CpInfoTag getTag();

	public int getMovedCursor(final int cursor);

	public List<Element> getElements(final byte[] tagByte);
}
