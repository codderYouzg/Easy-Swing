package com.mec.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JFrame;

public interface IMecView {
	Font topicFont = new Font("΢���ź�", Font.BOLD, 32);
	Font normalFont = new Font("����", Font.PLAIN, 16);
	Font smallFont = new Font("����", Font.PLAIN, 14);
	Font tinyFont = new Font("����", Font.PLAIN, 12);
	
	int topicFontSize = topicFont.getSize();
	int normalFontSize = normalFont.getSize();
	
	Color topicColor = new Color(3, 31, 203);
	
	int PADDING = 5;
	
	Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	
	default void initView() {
		init();
		dealEvent();
	}
	
	void init();
	void reinit();
	void dealEvent();
	JFrame getFrame();
	
	default void showView() throws FrameIsNullException {
		JFrame frame = getFrame();
		if (frame == null) {
			throw new FrameIsNullException("����ΪNULL��");
		}
		frame.setVisible(true);
		reinit();
	}
	
	default void exitView() throws FrameIsNullException {
		JFrame frame = getFrame();
		if (frame == null) {
			throw new FrameIsNullException("����ΪNULL��");
		}
		frame.dispose();
	}
	
}
