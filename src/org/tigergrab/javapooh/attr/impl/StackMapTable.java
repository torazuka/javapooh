package org.tigergrab.javapooh.attr.impl;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.attr.AttributeItem;
import org.tigergrab.javapooh.attr.AttributeKind;
import org.tigergrab.javapooh.attr.frame.FrameType;
import org.tigergrab.javapooh.attr.frame.FrameTypeKind;
import org.tigergrab.javapooh.impl.ByteParser;
import org.tigergrab.javapooh.impl.Util;
import org.tigergrab.javapooh.view.impl.Element;
import org.tigergrab.javapooh.view.impl.PromptView;

public class StackMapTable implements AttributeInfo {

	protected final PromptView view = new PromptView();
	protected final Logger logger = LoggerFactory
			.getLogger(StackMapTable.class);

	@Override
	public int getInfo(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element nameIndexElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.attribute_name_index));
		nameIndexElement.setComment(AttributeKind.Signature.name());
		view.printElement(nameIndexElement);
		currentCursor += AttributeItem.attribute_name_index.size();

		view.printElement(parser.getData(bytes, currentCursor, new Element(
				AttributeItem.attribute_length)));
		currentCursor += AttributeItem.attribute_length.size();

		Element numberElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.number_of_entries));
		view.printElement(numberElement);
		currentCursor += AttributeItem.number_of_entries.size();

		currentCursor = getEntries(bytes,
				Util.byteToInt(numberElement.getBytes()), currentCursor);

		return currentCursor;
	}

	protected int getEntries(final byte[] bytes, final int entryNum,
			final int cursor) {
		int currentCursor = cursor;
		for (int i = 0; i < entryNum; i++) {
			currentCursor = getStackMapFrame(bytes, currentCursor);
		}
		return currentCursor;
	}

	protected int getStackMapFrame(final byte[] bytes, final int cursor) {
		int currentCursor = cursor;
		ByteParser parser = new ByteParser();

		Element frameTypeElement = parser.getData(bytes, currentCursor,
				new Element(AttributeItem.frame_type));
		int frameType = Util.byteToInt(frameTypeElement.getBytes());

		int frameId = -1;
		if (frameType < 64) {
			frameId = FrameTypeKind.same_frame.getId();
		} else if (frameType < 128) {
			frameId = FrameTypeKind.same_locals_1_stack_item_frame.getId();
		} else if (frameType == 247) {
			frameId = FrameTypeKind.same_locals_1_stack_item_frame_extended
					.getId();
		} else if (248 <= frameType && frameType <= 250) {
			frameId = FrameTypeKind.chop_frame.getId();
		} else if (frameType == 251) {
			frameId = FrameTypeKind.same_frame_extended.getId();
		} else if (252 <= frameType && frameType <= 254) {
			frameId = FrameTypeKind.append_frame.getId();
		} else if (frameType == 255) {
			frameId = FrameTypeKind.full_frame.getId();
		} else {
			logger.error("error");
		}

		EnumSet<FrameTypeKind> allType = EnumSet.allOf(FrameTypeKind.class);
		for (FrameTypeKind each : allType) {
			if (each.getId() == frameId) {
				FrameType ft = each.make();
				currentCursor = ft.execute(bytes, currentCursor);
			}
		}
		return currentCursor;
	}
}
