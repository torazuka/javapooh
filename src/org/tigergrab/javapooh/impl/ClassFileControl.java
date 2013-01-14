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
		ClassFile cf = new ClassFile();
		int cursor = 0;

		Element magicElement = cf.getData(bytes, cursor,
				new Element(ClassItem.magic));
		if (checkMagic(magicElement.getBytes()) == false) {
			logger.error("Javaのクラスファイルを指定してください。");
			return;
		}
		view.printBegin("ClassFile");

		view.printElement(magicElement);
		cursor += ClassItem.magic.size();

		view.printElement(cf.getData(bytes, cursor, new Element(
				ClassItem.minor_version)));
		cursor += ClassItem.minor_version.size();

		view.printElement(cf.getData(bytes, cursor, new Element(
				ClassItem.major_version)));
		cursor += ClassItem.major_version.size();

		Element cpCountElement = cf.getData(bytes, cursor, new Element(
				ClassItem.constant_pool_count));
		view.printElement(cpCountElement);
		cursor += ClassItem.constant_pool_count.size();

		cursor = cf.getConstantPool(bytes, Integer.parseInt(
				Util.byteToString(cpCountElement.getBytes()), 16), cursor);

		view.printElement(cf.getData(bytes, cursor, new Element(
				ClassItem.access_flags)));
		cursor += ClassItem.access_flags.size();

		view.printElement(cf.getData(bytes, cursor,
				new Element(ClassItem.this_class)));
		cursor += ClassItem.this_class.size();

		view.printElement(cf.getData(bytes, cursor, new Element(
				ClassItem.super_class)));
		cursor += ClassItem.super_class.size();

		Element interfaceCountElement = cf.getData(bytes, cursor, new Element(
				ClassItem.interfaces_count));
		view.printElement(interfaceCountElement);
		cursor += ClassItem.interfaces_count.size();

		cursor = cf.getInterfaces(bytes, Integer.parseInt(
				Util.byteToString(interfaceCountElement.getBytes()), 16),
				cursor);

		Element fieldsCountElement = cf.getData(bytes, cursor, new Element(
				ClassItem.fields_count));
		view.printElement(fieldsCountElement);
		cursor += ClassItem.fields_count.size();

		cursor = cf.getFields(
				bytes,
				Integer.parseInt(
						Util.byteToString(fieldsCountElement.getBytes()), 16),
				cursor);

		Element methodsCountElement = cf.getData(bytes, cursor, new Element(
				ClassItem.methods_count));
		view.printElement(methodsCountElement);
		cursor += ClassItem.methods_count.size();

		cursor = cf.getMethods(
				bytes,
				Integer.parseInt(
						Util.byteToString(methodsCountElement.getBytes()), 16),
				cursor);

		Element attributeCountElement = cf.getData(bytes, cursor, new Element(
				ClassItem.attributes_count));
		view.printElement(attributeCountElement);
		cursor += ClassItem.attributes_count.size();

		cursor = cf.getAttributes(bytes, Integer.parseInt(
				Util.byteToString(attributeCountElement.getBytes()), 16),
				cursor);
	}
}
