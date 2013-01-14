package org.tigergrab.javapooh;

public enum ItemType {
	u1(1), u2(2), u4(4), array(0);

	private int size;

	private ItemType(int n) {
		size = n;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return name();
	}

}
