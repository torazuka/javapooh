package org.tigergrab.javapooh.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.ClassItem;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class ClassFileControl {

	protected final Logger logger = LoggerFactory
			.getLogger("ClassFileControl.class");

	protected final PromptView view = new PromptView();

	public boolean checkMagic(final byte[] bytes) {
		String magic = "";
		for (int i = 0; i < bytes.length; i++) {
			magic += Util.getHexString(bytes[i]);
		}
		if (magic.equals("cafebabe")) {
			return true;
		}
		return false;
	}

	public void execute(final byte[] bytes) {
		ByteParser parser = new ByteParser();
		ClassFile cf = new ClassFile();
		int cursor = 0;

		Element magicElement = parser.getData(bytes, cursor, new Element(
				ClassItem.magic));
		if (checkMagic(magicElement.getBytes()) == false) {
			logger.error("Javaのクラスファイルを指定してください。");
			return;
		}
		view.printBegin("ClassFile");

		view.printElement(magicElement);
		cursor += ClassItem.magic.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				ClassItem.minor_version)));
		cursor += ClassItem.minor_version.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				ClassItem.major_version)));
		cursor += ClassItem.major_version.size();

		Element cpCountElement = parser.getData(bytes, cursor, new Element(
				ClassItem.constant_pool_count));
		view.printElement(cpCountElement);
		cursor += ClassItem.constant_pool_count.size();

		cursor = cf.getConstantPool(bytes,
				Util.byteToInt(cpCountElement.getBytes()), cursor);

		view.printElement(parser.getData(bytes, cursor, new Element(
				ClassItem.access_flags)));
		cursor += ClassItem.access_flags.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				ClassItem.this_class)));
		cursor += ClassItem.this_class.size();

		view.printElement(parser.getData(bytes, cursor, new Element(
				ClassItem.super_class)));
		cursor += ClassItem.super_class.size();

		Element interfaceCountElement = parser.getData(bytes, cursor,
				new Element(ClassItem.interfaces_count));
		view.printElement(interfaceCountElement);
		cursor += ClassItem.interfaces_count.size();

		cursor = cf.getInterfaces(bytes,
				Util.byteToInt(interfaceCountElement.getBytes()), cursor);

		Element fieldsCountElement = parser.getData(bytes, cursor, new Element(
				ClassItem.fields_count));
		view.printElement(fieldsCountElement);
		cursor += ClassItem.fields_count.size();

		cursor = cf.getFields(bytes,
				Util.byteToInt(fieldsCountElement.getBytes()), cursor);

		Element methodsCountElement = parser.getData(bytes, cursor,
				new Element(ClassItem.methods_count));
		view.printElement(methodsCountElement);
		cursor += ClassItem.methods_count.size();

		cursor = cf.getMethods(bytes,
				Util.byteToInt(methodsCountElement.getBytes()), cursor);

		Element attributeCountElement = parser.getData(bytes, cursor,
				new Element(ClassItem.attributes_count));
		view.printElement(attributeCountElement);
		cursor += ClassItem.attributes_count.size();

		cursor = cf.getAttributes(bytes,
				Util.byteToInt(attributeCountElement.getBytes()), cursor);
	}
}
