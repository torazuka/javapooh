package org.tigergrab.javapooh.view.impl;

import org.tigergrab.javapooh.Item;

public class Element {
	protected String type;
	protected String item;
	protected byte[] bytes;
	protected int size = -1;
	protected String comment = "";

	public Element(final String typeName, final String itemName,
			final byte[] bt, final String commentStr) {
		this(typeName, itemName, bt);
		comment = commentStr;
	}

	public Element(final Item it) {
		type = it.type();
		item = it.name();
		size = it.size();
	}

	public Element(final String typeName, final String itemName, final byte[] bt) {
		type = typeName;
		item = itemName;
		bytes = bt;
	}

	public Element(final String typeName, final String itemName, final int sz) {
		type = typeName;
		item = itemName;
		size = sz;
	}

	public Element(Element element) {
		type = element.getType();
		item = element.getItem();
		size = element.getSize();
		bytes = element.getBytes();
		comment = element.getComment();
	}

	public String getType() {
		return type;
	}

	public String getItem() {
		return item;
	}

	public int getSize() {
		return size;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(final byte[] bt) {
		bytes = bt;
	}

	public boolean hasComment() {
		return 0 < comment.length() ? true : false;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String str) {
		comment = str;
	}

}
