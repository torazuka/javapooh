package org.tigergrab.javapooh.attr.impl;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.impl.Utf8Info;
import org.tigergrab.javapooh.impl.Item;
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
		view.printBegin(Item.attributes.name());
		int counter = 1;
		for (;;) {
			if (counter < attributeCount + 1) {
				view.printCounter(counter, Item.attributes.name());
				AttributeKind attrKind = getAttributeNameIndex(currentCursor);
				if (attrKind == null) {
					logger.error("attributeの判別に失敗しました。");
				}
				AttributeInfo info = selectAttributeKind(attrKind);
				currentCursor = info.getInfo(bytes, currentCursor);
				counter++;
				continue;
			}
			break;
		}
		view.printEnd(Item.attributes.name());
		return currentCursor;
	}

	protected AttributeInfo selectAttributeKind(final AttributeKind attrKind) {
		AttributeInfo result = null;
		if (attrKind.equals(AttributeKind.ConstantValue)) {
			result = new ConstantValue();
		} else if (attrKind.equals(AttributeKind.Synthetic)) {
			result = new Synthetic();
		} else if (attrKind.equals(AttributeKind.Deprecated)) {
			result = new Deprecated();
		} else if (attrKind.equals(AttributeKind.Code)) {
			result = new Code(constantPool);
		} else if (attrKind.equals(AttributeKind.Exceptions)) {
			result = new Exceptions();
		} else if (attrKind.equals(AttributeKind.LineNumberTable)) {
			result = new LineNumberTable();
		} else if (attrKind.equals(AttributeKind.LocalVariableTable)) {
			result = new LocalVariableTable();
		} else if (attrKind.equals(AttributeKind.InnerClasses)) {
			result = new InnerClasses();
		} else if (attrKind.equals(AttributeKind.SourceFile)) {
			result = new SourceFile();
		}
		return result;
	}

	protected AttributeKind getAttributeNameIndex(int cursor) {
		DefaultAttribute attribute = new DefaultAttribute();
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
		return null;
	}
}
