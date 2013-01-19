package org.tigergrab.javapooh.method.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.attr.impl.AttributeControl;
import org.tigergrab.javapooh.cp.ConstantInfo;

/**
 * u2 access_flags; u2 name_index; u2 descriptor_index; u2 attributes_count;
 * attribute_info attributes[attributes_count];
 */
public class MethodInfo {

	protected final Logger logger = LoggerFactory.getLogger("MethodInfo.class");

	public MethodInfo() {
	}

	public int getAttributes(final byte[] bytes,
			final Map<Integer, ConstantInfo> constantPool,
			final int attributesCount, final int cursor) {
		AttributeControl control = new AttributeControl(bytes, attributesCount,
				constantPool);
		return control.getInfo(cursor);
	}
}
