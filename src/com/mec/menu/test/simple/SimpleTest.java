package com.mec.menu.test.simple;

import com.mec.util.FrameIsNullException;

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