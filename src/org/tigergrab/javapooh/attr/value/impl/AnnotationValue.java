package org.tigergrab.javapooh.attr.value.impl;

import org.tigergrab.javapooh.attr.impl.RuntimeAnnotations;
import org.tigergrab.javapooh.attr.value.Value;

public class AnnotationValue implements Value {

	@Override
	public int getValue(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		RuntimeAnnotations ra = new RuntimeAnnotations();
		currentCursor = ra.getAnnotation(bytes, currentCursor);
		return currentCursor;
	}
}
