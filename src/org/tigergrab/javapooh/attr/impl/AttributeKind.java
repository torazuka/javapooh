package org.tigergrab.javapooh.attr.impl;

import java.util.Map;

import org.tigergrab.javapooh.attr.AttributeInfo;
import org.tigergrab.javapooh.cp.ConstantInfo;

public enum AttributeKind {
	ConstantValue {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new ConstantValue();
		}
	},
	Code {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new Code(pool);
		}
	},
	/*
	 * TODO: StackMapTable {
	 * 
	 * @Override public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
	 * return new StackMapTable(); } },
	 */
	Exceptions {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new Exceptions();
		}
	},
	InnerClasses {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new InnerClasses();
		}
	},
	EnclosingMethod {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new EnclosingMethod();
		}
	},
	Synthetic {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new Synthetic();
		}
	},
	Signature {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new Signature();
		}
	},
	SourceFile {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new SourceFile();
		}
	},
	SourceDebugExtension {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new SourceDebugExtension();
		}
	},
	LineNumberTable {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new LineNumberTable();
		}
	},
	LocalVariableTable {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new LocalVariableTable();
		}
	},
	LocalVariableTypeTable {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new LocalVariableTypeTable();
		}
	},
	Deprecated {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new Deprecated();
		}
	},
	RuntimeVisibleAnnotations {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new RuntimeVisibleAnnotations();
		}
	},
	RuntimeInvisibleAnnotations {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new RuntimeInvisibleAnnotations();
		}
	},
	RuntimeVisibleParameterAnnotations {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new RuntimeVisibleParameterAnnotations();
		}
	},
	RuntimeInvisibleParameterAnnotations {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new RuntimeInvisibleParameterAnnotations();
		}
	},
	AnnotationDefault {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new AnnotationDefault();
		}
	},
	BootstrapMethods {
		@Override
		public AttributeInfo make(Map<Integer, ConstantInfo> pool) {
			return new BootstrapMethods();
		}
	};
	public abstract AttributeInfo make(Map<Integer, ConstantInfo> pool);
}
