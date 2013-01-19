package org.tigergrab.javapooh.attr.impl;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.ClassItem;
import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.impl.Utf8Info;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class AttributeControl {

	protected final Logger logger = LoggerFactory
			.getLogger("AttributeControl.class");

	protected final PromptView view = new PromptView();

	protected Map<Integer, ConstantInfo> constantPool = new HashMap<>();
	protected int attributeCount = 0;
	protected byte[] bytes;

	public AttributeControl(final byte[] bt, final int attrCount,
			final Map<Integer, ConstantInfo> cp) {
		bytes = bt;
		attributeCount = attrCount;
		constantPool = cp;
	}

	public int getInfo(final int cursor) {
		int currentCursor = cursor;
		view.printBegin(ClassItem.attributes.name());
		int counter = 1;
		for (; counter < attributeCount + 1; counter++) {
			view.printCounter(counter, ClassItem.attributes.name());
			AttributeKind attrKind = getAttributeNameIndex(currentCursor);
			if (attrKind == null) {
				logger.error("attributeの判別に失敗しました。");
			}
			AttributeInfo info = selectAttributeKind(attrKind);
			currentCursor = info.getInfo(bytes, currentCursor);
		}
		view.printEnd(ClassItem.attributes.name());
		return currentCursor;
	}

	protected AttributeInfo selectAttributeKind(final AttributeKind attrKind) {
		AttributeInfo result = null;
		if (attrKind == null) {
			return new Unknown();
		}

		EnumSet<AttributeKind> allAttribute = EnumSet
				.allOf(AttributeKind.class);
		for (AttributeKind attr : allAttribute) {
			if (attrKind.equals(attr)) {
				result = attr.make(constantPool);
			}
		}
		return result;
	}

	protected AttributeKind getAttributeNameIndex(int cursor) {
		AttributeNameUtil attribute = new AttributeNameUtil();
		Element attributeNameIndex = attribute.getAttributeNameIndex(bytes,
				cursor);

		String name = Util.byteToString(attributeNameIndex.getBytes());
		ConstantInfo constantInfo = constantPool
				.get(Integer.parseInt(name, 16));

		String string = "";
		if (constantInfo instanceof Utf8Info) {
			Utf8Info info = (Utf8Info) constantInfo;
			string = new String(info.getStringBytes());
		}

		EnumSet<AttributeKind> allTag = EnumSet.allOf(AttributeKind.class);
		for (AttributeKind attr : allTag) {
			if (attr.name().equals(string)) {
				return attr;
			}
		}

		logger.debug("debug: " + string);
		return null;
	}
}
