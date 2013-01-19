package org.tigergrab.javapooh.cp.impl;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.ClassItem;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.CpInfoTag;
import org.tigergrab.javapooh.cp.CpItem;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class CpInfoControl {

	protected final Logger logger = LoggerFactory
			.getLogger("CpInfoControl.class");

	PromptView view = new PromptView();

	protected byte[] constantPoolByte;
	protected int cpCount = 0;
	protected int lastCursor = -1;
	protected CpInfoTag lastTag;

	protected byte[] tagByte = new byte[1];

	public CpInfoControl(final byte[] bytes, int constantPoolCount) {
		constantPoolByte = bytes;
		cpCount = constantPoolCount;
	}

	public Map<Integer, ConstantInfo> getConstantPool(final int cursor) {
		Map<Integer, ConstantInfo> result = new HashMap<>();
		view.printBegin(ClassItem.constant_pool.name());

		int currentCursor = cursor;
		for (int count = 1; count < cpCount + 1;) {
			view.printCounter(count, ClassItem.constant_pool.name());
			ConstantInfo info = getNextCpInfo(currentCursor);
			result.put(count, info);

			currentCursor = lastCursor;
			count = getNextEntryNum(info, count);
		}
		view.printEnd(ClassItem.constant_pool.name());
		lastCursor = currentCursor;
		return result;
	}

	public int getLastCursor() {
		return lastCursor;
	}

	public int getNextEntryNum(final ConstantInfo info, final int current) {
		int result = current;
		result++;
		CpInfoTag tag = lastTag;

		if (tag.equals(CpInfoTag.Constant_Long)
				|| tag.equals(CpInfoTag.Constant_Double)) {
			result++;
		}
		return result;
	}

	public ConstantInfo getNextCpInfo(final int cursor) {
		int currentCursor = cursor;
		CpInfoTag tag = getTag(constantPoolByte, currentCursor);
		ConstantInfo info = getInfo(tag);
		if (info != null) {
			currentCursor = info.getContents(constantPoolByte, currentCursor);
		}
		lastCursor = currentCursor;
		lastTag = tag;
		return info;
	}

	public CpInfoTag getTag(final byte[] bytes, final int cursor) {
		CpInfoTag result = null;

		ByteParser parser = new ByteParser();
		Element tagEelement = parser.getData(bytes, cursor, new Element(
				CpItem.tag));

		byte[] tmpBytes = tagEelement.getBytes();
		String tag = "";
		for (int i = 0; i < CpItem.tag.size(); i++) {
			tag += Util.getHexString(tmpBytes[i]);
			tagByte[i] = tmpBytes[i];
		}
		int tmp = Integer.parseInt(tag, 16);

		EnumSet<CpInfoTag> allTag = EnumSet.allOf(CpInfoTag.class);
		for (CpInfoTag cpInfoTag : allTag) {
			if (cpInfoTag.getTag() == tmp) {
				result = cpInfoTag;
				break;
			}
		}
		return result;
	}

	public ConstantInfo getInfo(final CpInfoTag tag) {
		ConstantInfo result = tag.make();
		if (result == null) {
			logger.error("tagが仕様に対して誤っています。");
		}
		return result;
	}
}
