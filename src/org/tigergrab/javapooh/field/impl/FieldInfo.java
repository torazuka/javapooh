package org.tigergrab.javapooh.field.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.attr.impl.AttributeControl;
import org.tigergrab.javapooh.cp.ConstantInfo;

/**
 * u2 access_flags; u2 name_index; u2 descriptor_index; u2 attributes_count;
 * attribute_info attributes[attributes_count]
 */
public class FieldInfo {

	protected final Logger logger = LoggerFactory.getLogger("FieldInfo.class");

	public FieldInfo() {
	}

	protected int getAttributes(final byte[] bytes,
			final Map<Integer, ConstantInfo> constantPool,
			final int attributesCount, final int cursor) {
		AttributeControl control = new AttributeControl(bytes, attributesCount,
				constantPool);
		return control.getInfo(cursor);
	}
}
