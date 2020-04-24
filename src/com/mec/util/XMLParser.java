package com.mec.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class XMLParser {
	private static volatile DocumentBuilder db;
	
	public XMLParser() {
	}
	
	private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		if (db == null) {
			synchronized (XMLParser.class) {
				if (db == null) {
					db = DocumentBuilderFactory
							.newInstance()
							.newDocumentBuilder();
				}
			}
		}
		return db;
	}
	
	public static Document getDocument(String xmlPath) {
		InputStream is = XMLParser.class.getResourceAsStream(xmlPath);
		if (is == null) {
			System.out.println("xmlPath[" + xmlPath + "]�����ڣ�");
			return null;
		}
		
		return getDocument(is);
	}
	
	public static Document getDocument(InputStream is) {
		Document document = null;
		try {
			document = getDocumentBuilder().parse(is);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
	public abstract void dealElement(Element element, int index);
	
	public void parseTag(Document document, String tagName) {
		NodeList nodeList = document.getElementsByTagName(tagName);
		for (int index = 0; index < nodeList.getLength(); index++) {
			Element ele = (Element) nodeList.item(index);
			dealElement(ele, index);
		}
	}
	
	public void parseTag(Element element, String tagName) {
		NodeList nodeList = element.getElementsByTagName(tagName);
		for (int index = 0; index < nodeList.getLength(); index++) {
			Element ele = (Element) nodeList.item(index);
			dealElement(ele, index);
		}
	}
	
	public void parseRoot(Document document) {
		Element root = (Element) document.getChildNodes().item(0);
		dealElement(root, 0);
	}
	
	public void parseElement(Element element) {
		NodeList nodeList = element.getChildNodes();
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node node = nodeList.item(index);
			if (node instanceof Element) {
				dealElement((Element) node, index);
			}
		}
	}
	
}
