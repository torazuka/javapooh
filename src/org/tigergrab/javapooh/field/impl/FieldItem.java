package org.tigergrab.javapooh.field.impl;

import org.tigergrab.javapooh.impl.ItemType;

public enum FieldItem {
	access_flags(ItemType.u2), name_index(ItemType.u2), descriptor_index(
			ItemType.u2), attributes_count(ItemType.u2), attributes(
			ItemType.array);

	protected ItemType type;

	private FieldItem(ItemType tp) {
		type = tp;
	}

	public String type() {
		return type.name();
	}

	public int size() {
		return type.size();
	}
}
