package org.tigergrab.javapooh.attr;

/**
 * u2 attribute_name_index; u4 attribute_lenght; u1 info[attribute_length]
 */
public interface AttributeInfo {

	public int getInfo(final byte[] bytes, final int cursor);

}
