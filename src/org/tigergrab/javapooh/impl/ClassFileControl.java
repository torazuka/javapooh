package org.tigergrab.javapooh.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class ClassFileControl {

	protected final Logger logger = LoggerFactory
			.getLogger("ClassFileControl.class");

	protected final PromptView view = new PromptView();

	public void execute(final byte[] bytes) {
		ClassFile cf = new ClassFile();
		if (cf.checkMagic(bytes) == false) {
			logger.error("Javaのクラスファイルを指定してください。");
			return;
		}
		view.printBegin("ClassFile");

		int cursor = 0;
		view.printElement(cf.getData(bytes, cursor, new Element(Item.magic)));
		cursor += Item.magic.size();
		view.printElement(cf.getData(bytes, cursor, new Element(
				Item.minor_version)));
		cursor += Item.minor_version.size();
		view.printElement(cf.getData(bytes, cursor, new Element(
				Item.major_version)));
		cursor += Item.major_version.size();

		Element cpCountElement = cf.getData(bytes, cursor, new Element(
				Item.constant_pool_count));
		view.printElement(cpCountElement);
		cursor += Item.constant_pool_count.size();
		cursor = cf.getConstantPool(bytes, Integer.parseInt(
				Util.byteToString(cpCountElement.getBytes()), 16), cursor);

		view.printElement(cf.getData(bytes, cursor, new Element(
				Item.access_flags)));
		cursor += Item.access_flags.size();
		view.printElement(cf.getData(bytes, cursor,
				new Element(Item.this_class)));
		cursor += Item.this_class.size();
		view.printElement(cf.getData(bytes, cursor, new Element(
				Item.super_class)));
		cursor += Item.super_class.size();

		Element interfaceCountElement = cf.getData(bytes, cursor, new Element(
				Item.interfaces_count));
		view.printElement(interfaceCountElement);
		cursor += Item.interfaces_count.size();
		cursor = cf.getInterfaces(bytes, Integer.parseInt(
				Util.byteToString(interfaceCountElement.getBytes()), 16),
				cursor);

		Element fieldsCountElement = cf.getData(bytes, cursor, new Element(
				Item.fields_count));
		view.printElement(fieldsCountElement);
		cursor += Item.fields_count.size();

		cursor = cf.getFields(
				bytes,
				Integer.parseInt(
						Util.byteToString(fieldsCountElement.getBytes()), 16),
				cursor);

		Element methodsCountElement = cf.getData(bytes, cursor, new Element(
				Item.methods_count));
		view.printElement(methodsCountElement);
		cursor += Item.methods_count.size();

		cursor = cf.getMethods(
				bytes,
				Integer.parseInt(
						Util.byteToString(methodsCountElement.getBytes()), 16),
				cursor);

		Element attributeCountElement = cf.getData(bytes, cursor, new Element(
				Item.attributes_count));
		view.printElement(attributeCountElement);
		cursor += Item.attributes_count.size();

		cursor = cf.getAttributes(bytes, Integer.parseInt(
				Util.byteToString(attributeCountElement.getBytes()), 16),
				cursor);
	}
}
