package org.tigergrab.javapooh.attr.impl;

import org.tigergrab.javapooh.impl.ItemType;

public enum AttributeItem {
	attribute_name_index(ItemType.u2), attribute_length(ItemType.u4), constantvalue_index(
			ItemType.u2), max_stack(ItemType.u2), max_locals(ItemType.u2), code_length(
			ItemType.u4), code(ItemType.u1), exception_table_length(ItemType.u2), exception_table(
			ItemType.array), start_pc(ItemType.u2), end_pc(ItemType.u2), handler_pc(
			ItemType.u2), catch_type(ItemType.u2), attributes_count(ItemType.u2), attributes(
			ItemType.array), number_of_exceptions(ItemType.u2), exception_index_table(
			ItemType.array), exception_index(ItemType.u2), line_number_table_length(
			ItemType.u2), line_number(ItemType.u2), local_variable_table_length(
			ItemType.u2), length(ItemType.u2), name_index(ItemType.u2), descriptor_index(
			ItemType.u2), index(ItemType.u2), number_of_classes(ItemType.u2), inner_class_info_index(
			ItemType.u2), outer_class_info_index(ItemType.u2), inner_name_index(
			ItemType.u2), innner_class_access_flags(ItemType.u2), source_file_index(
			ItemType.u2);

	protected ItemType type;

	private AttributeItem(ItemType tp) {
		type = tp;
	}

	public String type() {
		return type.name();
	}

	public int size() {
		return type.size();
	}
}
