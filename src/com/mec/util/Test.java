package com.mec.util;

public class Test {

	public static void main(String[] args) {
		new PackageScanner() {
			@Override
			public void dealClass(Class<?> klass) {
				if (klass.isPrimitive()
						|| klass.isInterface()
						|| klass.isAnnotation()
						|| klass.isArray()
						|| klass.isEnum()) {
					return;
				}
			}
			
		}.scanPackage("com.google.gson");;
	}

}
