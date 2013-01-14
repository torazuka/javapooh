package org.tigergrab.javapooh.attr;

import java.util.Map;

import org.tigergrab.javapooh.attr.impl.AnnotationDefault;
import org.tigergrab.javapooh.attr.impl.BootstrapMethods;
import org.tigergrab.javapooh.attr.impl.Code;
import org.tigergrab.javapooh.attr.impl.ConstantValue;
import org.tigergrab.javapooh.attr.impl.Deprecated;
import org.tigergrab.javapooh.attr.impl.EnclosingMethod;
import org.tigergrab.javapooh.attr.impl.Exceptions;
import org.tigergrab.javapooh.attr.impl.InnerClasses;
import org.tigergrab.javapooh.attr.impl.LineNumberTable;
import org.tigergrab.javapooh.attr.impl.LocalVariableTable;
import org.tigergrab.javapooh.attr.impl.LocalVariableTypeTable;
import org.tigergrab.javapooh.attr.impl.RuntimeInvisibleAnnotations;
import org.tigergrab.javapooh.attr.impl.RuntimeInvisibleParameterAnnotations;
import org.tigergrab.javapooh.attr.impl.RuntimeVisibleAnnotations;
import org.tigergrab.javapooh.attr.impl.RuntimeVisibleParameterAnnotations;
import org.tigergrab.javapooh.attr.impl.Signature;
import org.tigergrab.javapooh.attr.impl.SourceDebugExtension;
import org.tigergrab.javapooh.attr.impl.SourceFile;
import org.tigergrab.javapooh.attr.impl.Synthetic;
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
