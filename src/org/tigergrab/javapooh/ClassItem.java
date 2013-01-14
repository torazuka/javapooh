package org.tigergrab.javapooh;


public enum ClassItem implements Item {
	magic(ItemType.u4), minor_version(ItemType.u2), major_version(ItemType.u2), constant_pool_count(
			ItemType.u2), constant_pool(ItemType.array), access_flags(
			ItemType.u2), this_class(ItemType.u2), super_class(ItemType.u2), interfaces_count(
			ItemType.u2), interfaces(ItemType.array), fields_count(ItemType.u2), fields(
			ItemType.array), methods_count(ItemType.u2), methods(ItemType.array), attributes_count(
			ItemType.u2), attributes(ItemType.array);

	ItemType type;

	private ClassItem(ItemType tp) {
		type = tp;
	}

	public String type() {
		return type.toString();
	}

	public int size() {
		return type.size();
	}
}
