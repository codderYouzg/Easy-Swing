package com.mec.menu.test.simple;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import com.mec.util.FrameIsNullException;
import com.mec.util.IMecView;
import com.mec.util.MenuFactory;
import com.mec.util.ViewTool;

public class SimpleMenuView implements IMecView {
	private JFrame jf;
	private JMenuBar jmnbBar;
	
	public SimpleMenuView() {
		initView();
	}
	
	@Override
	public void init() {
		jf = new JFrame("���Դ���");
        jf.setSize(400, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jf.setLayout(new BorderLayout());
        
        jmnbBar = new JMenuBar();
        jf.setJMenuBar(jmnbBar);
        
        
        jf.setJMenuBar(jmnbBar);
        MenuFactory menuFactory = new MenuFactory();
        menuFactory.setBar(jmnbBar);
        menuFactory.setObject(this);
        menuFactory.loadMenu("/menu.cfg.xml");

	}
	
	protected void doShow(String actionCommand) {
		System.out.println(actionCommand);
	}

	public void doProject() {
		ViewTool.showWarnning(jf, "��δ���Ŵ˹���");
	}
	
	public void doPackage() {
		ViewTool.showError(jf, "��δ���Ŵ˹���");
	}
	
	public void doClass() {
		ViewTool.showMessage(jf, "��δ���Ŵ˹���");
	}
	
	public void doOpen() {
		ViewTool.showMessage(jf, "��δ���Ŵ˹���");
	}
	
	public void exitApp() {
		closeView();
	}
	
	private void closeView() {
		try {
			exitView();
		} catch (FrameIsNullException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reinit() {}

	@Override
	public void dealEvent() {
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
			
		});
	}

	@Override
	public JFrame getFrame() {
		return jf;
	}

}
