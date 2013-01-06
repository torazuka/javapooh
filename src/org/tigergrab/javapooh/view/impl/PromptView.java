package org.tigergrab.javapooh.view.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.impl.Util;

public class PromptView {

	protected final Logger logger = LoggerFactory.getLogger("PromptView.class");

	public void printElement(Element element) {
		logger.info(shaping(element));
	}

	public void printElement(final List<Element> list) {
		for (Element element : list) {
			printElement(element);
		}
	}

	public void printElement(final int num, final List<Element> list) {
		logger.info("#" + num);
		printElement(list);
	}

	public void printBegin(final String str) {
		logger.info("[ " + str + " ] -----------------");
	}

	public void printEnd(final String str) {
		logger.info("-------- [ " + str + " ] (END)");

	}

	public void printCounter(final int n, final String str) {
		logger.info("#" + n + " of " + str);
	}

	protected String shaping(final Element element) {
		StringBuilder sb = new StringBuilder();
		sb.append(element.getType());
		sb.append(" ");
		sb.append(element.getItem());
		sb.append(" : ");
		sb.append(Util.byteToString(element.getBytes()));
		if (element.hasComment()) {
			sb.append("  #");
			sb.append(element.getComment());
		}
		return new String(sb);
	}
}
