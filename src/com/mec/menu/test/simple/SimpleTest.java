package com.mec.menu.test.simple;

import javax.swing.JMenuBar;

import org.w3c.dom.Element;

import com.mec.about_xmlparse.core.XMLParser;
import com.mec.util.FrameIsNullException;
import com.mec.util.MenuFactory;


public class SimpleTest {

	public static void main(String[] args) {
		SimpleMenuView testView = new SimpleMenuView();
		try {
			testView.showView();
		} catch (FrameIsNullException e) {
			e.printStackTrace();
		}
	}

}