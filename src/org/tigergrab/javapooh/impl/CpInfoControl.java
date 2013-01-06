package org.tigergrab.javapooh.impl;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.cp.impl.ClassInfo;
import org.tigergrab.javapooh.cp.impl.CpInfoTag;
import org.tigergrab.javapooh.cp.impl.DoubleInfo;
import org.tigergrab.javapooh.cp.impl.FieldrefInfo;
import org.tigergrab.javapooh.cp.impl.FloatInfo;
import org.tigergrab.javapooh.cp.impl.IntegerInfo;
import org.tigergrab.javapooh.cp.impl.InterfaceMethodrefInfo;
import org.tigergrab.javapooh.cp.impl.LongInfo;
import org.tigergrab.javapooh.cp.impl.MethodHandleInfo;
import org.tigergrab.javapooh.cp.impl.MethodTypeInfo;
import org.tigergrab.javapooh.cp.impl.MethodrefInfo;
import org.tigergrab.javapooh.cp.impl.NameAndTypeInfo;
import org.tigergrab.javapooh.cp.impl.StringInfo;
import org.tigergrab.javapooh.cp.impl.Utf8Info;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class CpInfoControl {

	protected final Logger logger = LoggerFactory
			.getLogger("CpInfoControl.class");

	PromptView view = new PromptView();

	protected static final int TAG_SIZE = 1;

	protected byte[] constantPoolByte;
	protected int entryNum = 0;
	protected int lastCursor = -1;

	protected byte[] tagByte = new byte[1];

	public CpInfoControl(final byte[] bytes, int constantPoolCount) {
		constantPoolByte = bytes;
		entryNum = constantPoolCount;
	}

	public Map<Integer, ConstantInfo> getConstantPool(final int cursor) {
		Map<Integer, ConstantInfo> result = new HashMap<>();
		view.printBegin(Item.constant_pool.name());

		int currentEntryNum = 1;
		int currentCursor = cursor;
		for (;;) {
			if (currentEntryNum < entryNum + 1) {
				ConstantInfo info = getNextCpInfo(currentCursor,
						currentEntryNum);

				List<Element> elements = info.getElements(tagByte);
				view.printElement(currentEntryNum, elements);

				result.put(currentEntryNum, info);
				currentCursor = info.getMovedCursor(currentCursor + TAG_SIZE);
				currentEntryNum = getNextEntryNum(info, currentEntryNum);
				continue;
			}
			break;
		}
		view.printEnd(Item.constant_pool.name());
		lastCursor = currentCursor;
		return result;
	}

	public int getLastCursor() {
		return lastCursor;
	}

	public int getNextEntryNum(final ConstantInfo info, final int current) {
		int result = current;
		result++;
		CpInfoTag tag = info.getTag();
		if (tag.equals(CpInfoTag.Constant_Long)
				|| tag.equals(CpInfoTag.Constant_Double)) {
			result++;
		}
		return result;
	}

	public ConstantInfo getNextCpInfo(final int currentCursor,
			final int currentEntryNum) {
		ConstantInfo result = getInfo(getTag(constantPoolByte, currentCursor));
		if (result != null) {
			result.getInfo(constantPoolByte, currentCursor + TAG_SIZE);
		}
		return result;
	}

	public CpInfoTag getTag(final byte[] bytes, final int cursor) {
		CpInfoTag result = null;

		int index = 0;
		String tag = "";
		for (int i = cursor; i < cursor + TAG_SIZE; i++) {
			tag += Util.getHexString(bytes[i]);
			tagByte[index++] = bytes[i];
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
		ConstantInfo ci = null;
		if (tag == CpInfoTag.Constant_Class) {
			ci = new ClassInfo();
		} else if (tag == CpInfoTag.Constant_Fieldref) {
			ci = new FieldrefInfo();
		} else if (tag == CpInfoTag.Constant_Methodref) {
			ci = new MethodrefInfo();
		} else if (tag == CpInfoTag.Constant_InterfaceMethodref) {
			ci = new InterfaceMethodrefInfo();
		} else if (tag == CpInfoTag.Constant_String) {
			ci = new StringInfo();
		} else if (tag == CpInfoTag.Constant_Integer) {
			ci = new IntegerInfo();
		} else if (tag == CpInfoTag.Constant_Float) {
			ci = new FloatInfo();
		} else if (tag == CpInfoTag.Constant_Long) {
			ci = new LongInfo();
		} else if (tag == CpInfoTag.Constant_Double) {
			ci = new DoubleInfo();
		} else if (tag == CpInfoTag.Constant_NameAndType) {
			ci = new NameAndTypeInfo();
		} else if (tag == CpInfoTag.Constant_Utf8) {
			ci = new Utf8Info();
		} else if (tag == CpInfoTag.Constant_MethodHandle) {
			ci = new MethodHandleInfo();
		} else if (tag == CpInfoTag.Constant_MethodType) {
			ci = new MethodTypeInfo();
		} else if (tag == CpInfoTag.Constant_InvokeDynamic) {
			ci = new MethodTypeInfo();
		} else {
			logger.error("tagが仕様に対して誤っています。");
		}
		return ci;
	}
}
