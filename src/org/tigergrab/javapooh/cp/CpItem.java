package org.tigergrab.javapooh.cp;

import org.tigergrab.javapooh.Item;
import org.tigergrab.javapooh.ItemType;

public enum CpItem implements Item {
	tag(ItemType.u1), name_index(ItemType.u2), high_bytes(ItemType.u4), low_bytes(
			ItemType.u4), bytes(ItemType.u4), class_index(ItemType.u2), name_and_type_index(
			ItemType.u2), bootstrap_method_attr_index(ItemType.u2), reference_kind(
			ItemType.u1), reference_index(ItemType.u2), descriptor_index(
			ItemType.u2), string_index(ItemType.u2), length(ItemType.u2), _byte(
			ItemType.u1);

	ItemType type;

	private CpItem(ItemType tp) {
		type = tp;
	}

	public String type() {
		return type.toString();
	}

	public int size() {
		return type.size();
	}
}
