package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class BootstrapMethods extends DefaultAttribute {
	protected final PromptView view = new PromptView();

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		Element nameIndexElement = getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.BootstrapMethods.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		Element attributeLengthElement = getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_length));
		view.printElement(attributeLengthElement);
		currentCursor += AttributeItem.attribute_length.size();

		Element numBootstrapMethodsElement = getData(bytes, currentCursor,
				new Element(AttributeItem.num_bootstrap_methods));
		view.printElement(numBootstrapMethodsElement);
		currentCursor += AttributeItem.num_bootstrap_methods.size();

		currentCursor = getBootstrapMethods(bytes, Integer.parseInt(
				Util.byteToString(numBootstrapMethodsElement.getBytes()), 16),
				currentCursor);

		return currentCursor;
	}

	protected int getBootstrapMethods(final byte[] bytes, final int num,
			final int cursor) {
		int currentCursor = cursor;

		for (int i = 0; i < num; i++) {
			Element numBootstrapMethodRefElement = getData(bytes,
					currentCursor, new Element(
							AttributeItem.bootstrap_method_ref));
			view.printElement(numBootstrapMethodRefElement);
			currentCursor += AttributeItem.bootstrap_method_ref.size();

			Element numBootstrapArgmentsElement = getData(bytes, currentCursor,
					new Element(AttributeItem.num_bootstrap_arguments));
			view.printElement(numBootstrapArgmentsElement);
			currentCursor += AttributeItem.num_bootstrap_arguments.size();

			currentCursor = getBootstrapArguments(bytes, Integer.parseInt(
					Util.byteToString(numBootstrapArgmentsElement.getBytes()),
					16), currentCursor);
		}
		return currentCursor;
	}

	protected int getBootstrapArguments(final byte[] bytes, final int num,
			final int cursor) {
		int currentCursor = cursor;
		for (int i = 0; i < num; i++) {
			Element bootstrapArgumentElement = getData(bytes, currentCursor,
					new Element(AttributeItem.bootstrap_argument));
			view.printElement(bootstrapArgumentElement);
			currentCursor += AttributeItem.bootstrap_argument.size();
		}
		return currentCursor;
	}
}
