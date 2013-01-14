package org.tigergrab.javapooh.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.attr.impl.AttributeControl;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.impl.CpInfoControl;
import org.tigergrab.javapooh.field.impl.FieldInfoControl;
import org.tigergrab.javapooh.method.impl.MethodInfoControl;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class ClassFile {

	protected final Logger logger = LoggerFactory.getLogger("ClassFile.class");

	protected Map<Integer, ConstantInfo> constantPool = new HashMap<>();;

	protected PromptView view = new PromptView();

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

	public int getConstantPool(final byte[] bytes, final int constantPoolCount,
			final int cursor) {
		CpInfoControl control = new CpInfoControl(bytes, constantPoolCount - 1);
		constantPool = control.getConstantPool(cursor);

		int currentCursor = control.getLastCursor();
		if (currentCursor == -1) {
			logger.error("コンスタントプールのサイズが取れませんでした。");
		}
		return currentCursor;
	}

	public int getInterfaces(final byte[] bytes, final int interfaceCount,
			final int cursor) {
		int index = cursor;
		for (int i = 0; i < interfaceCount; i++) {
			String str = "";
			for (int k = 0; k < 2; k++) {
				str += Util.getHexString(bytes[index]);
				index++;
			}
			logger.info("u2 interface:\t" + str);
		}
		return index;
	}

	public int getFields(final byte[] bytes, final int fieldsCount,
			final int cursor) {
		int currentCursor = cursor;
		FieldInfoControl control = new FieldInfoControl(bytes, fieldsCount,
				constantPool);
		currentCursor = control.getFieldInfo(currentCursor);
		return currentCursor;
	}

	public int getMethods(final byte[] bytes, final int fieldsCount,
			final int cursor) {
		int currentCursor = cursor;
		MethodInfoControl control = new MethodInfoControl(bytes, fieldsCount,
				constantPool);
		currentCursor = control.getMethodInfo(currentCursor);
		return currentCursor;
	}

	public int getAttributes(final byte[] bytes, final int fieldsCount,
			final int cursor) {
		int currentCursor = cursor;
		AttributeControl control = new AttributeControl(bytes, fieldsCount,
				constantPool);
		currentCursor = control.getInfo(currentCursor);
		return currentCursor;
	}
}
