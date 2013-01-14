package org.tigergrab.javapooh.attr;

import org.tigergrab.javapooh.Item;
import org.tigergrab.javapooh.ItemType;

public enum AttributeItem implements Item {
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
			ItemType.u2), class_index(ItemType.u2), method_index(ItemType.u2), signature_index(
			ItemType.u2), debug_extension(ItemType.u1), num_annotations(
			ItemType.u2), type_index(ItemType.u2), num_element_value_pairs(
			ItemType.u2), element_name_index(ItemType.u2), tag(ItemType.u1), const_value_index(
			ItemType.u2), type_name_index(ItemType.u2), const_name_index(
			ItemType.u2), class_info_index(ItemType.u2), num_value(ItemType.u2), num_parameters(
			ItemType.u1), num_bootstrap_methods(ItemType.u2), bootstrap_method_ref(
			ItemType.u2), num_bootstrap_arguments(ItemType.u2), bootstrap_argument(
			ItemType.u2), local_variable_type_table_length(ItemType.u2);

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
