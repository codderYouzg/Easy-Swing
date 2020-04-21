package com.mec.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class PackageScanner {
	
	public PackageScanner() {
	}
	
	public abstract void dealClass(Class<?> klass);
	
	private void dealPackage(File cur, String packageName) {
		File[] files = cur.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				if (!fileName.endsWith(".class")) {
					continue;
				}
				fileName = fileName.replace(".class", "");
				String className = packageName + "." + fileName;
				try {
					Class<?> klass = Class.forName(className);
					dealClass(klass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				dealPackage(file, packageName + "." + file.getName());
			}
		}
	}
	
	private void dealJar(URL url) {
		try {
			JarURLConnection connection = (JarURLConnection) url.openConnection();
			JarFile jarFile = connection.getJarFile();
			Enumeration<JarEntry> entryList = jarFile.entries();
			while (entryList.hasMoreElements()) {
				JarEntry jarEntry = entryList.nextElement();
				if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
					continue;
				}
				String className = jarEntry.getName();
				className = className.replace(".class", "");
				className = className.replace("/", ".");
				try {
					Class<?> klass = Class.forName(className);
					dealClass(klass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void scanPackage(String packageName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String pathName = packageName.replace('.', '/');
		try {
			Enumeration<URL> urls = classLoader.getResources(pathName);
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url.getProtocol().equals("jar")) {	//–≠“È
					dealJar(url);
				} else {
					try {
						File curFile = new File(url.toURI());
						dealPackage(curFile, packageName);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
