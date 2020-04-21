package com.mec.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ViewTool {
	public static final String TITLE = "ÓÒ×ª¸çÎÂÜ°ÌáÊ¾";
	
	public ViewTool() {
	}
	
	public static void showMessage(JFrame jfame, String message) {
		JOptionPane.showMessageDialog(jfame, message, TITLE, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void showWarnning(JFrame jfame, String message) {
		JOptionPane.showMessageDialog(jfame, message, TITLE, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showError(JFrame jfame, String message) {
		JOptionPane.showMessageDialog(jfame, message, TITLE, JOptionPane.ERROR_MESSAGE);
	}
	
	public static int getChoice(JFrame jfame, String message, int optionType) {
		return JOptionPane.showConfirmDialog(jfame, message, TITLE, optionType);
	}
	
}
