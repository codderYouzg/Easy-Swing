package com.mec.menu.test.simple;

import com.mec.util.FrameIsNullException;

public class Test {

	public static void main(String[] args) {
		SimpleMenuViewUpgrade testView = new SimpleMenuViewUpgrade();
		try {
			testView.showView();
		} catch (FrameIsNullException e) {
			e.printStackTrace();
		}
	}

}
