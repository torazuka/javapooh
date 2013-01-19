package org.tigergrab.javapooh.attr.verify.impl;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.verify.VerificationType;
import org.tigergrab.javapooh.attr.verify.VerificationTypeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;

public class VerificationTypeControl {

	protected final Logger logger = LoggerFactory
			.getLogger(VerificationTypeControl.class);

	public int getVerificationTypeInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;

		ByteParser parser = new ByteParser();
		Element tagElement = parser.getData(bytes, currentCursor, new Element(
				AttributeItem.tag));
		int tag = Util.byteToInt(tagElement.getBytes());

		EnumSet<VerificationTypeKind> allType = EnumSet
				.allOf(VerificationTypeKind.class);
		for (VerificationTypeKind each : allType) {
			if (each.getValue() == tag) {
				VerificationType vt = each.make();
				currentCursor = vt.execute(bytes, currentCursor);
			}
		}

		if (currentCursor == cursor) {
			logger.error("error");
		}
		return currentCursor;
	}
}
