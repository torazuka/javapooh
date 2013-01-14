package org.tigergrab.javapooh.field;

import org.tigergrab.javapooh.Item;
import org.tigergrab.javapooh.ItemType;

public enum FieldItem implements Item {
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
