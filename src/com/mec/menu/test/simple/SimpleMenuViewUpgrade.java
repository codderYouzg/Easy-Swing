package com.mec.menu.test.simple;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import com.mec.util.FrameIsNullException;
import com.mec.util.IMecView;
import com.mec.util.update.MenuFactory;

public class SimpleMenuViewUpgrade implements IMecView{
	private JFrame jf;
	private JMenuBar jmnbBar;
	
	public SimpleMenuViewUpgrade() {
		initView();
	}
	
	@Override
	public void init() {
		jf = new JFrame("测试窗口");
        jf.setSize(400, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jf.setLayout(new BorderLayout());
        
        jmnbBar = new JMenuBar();
        jf.setJMenuBar(jmnbBar);
        
        MenuFactory menuFactory = new MenuFactory();
        menuFactory.setBar(jmnbBar);
        menuFactory.loadMenu("/menu.cfg.update.xml");
	}

	@Override
	public void reinit() {}

	@Override
	public void dealEvent() {
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					exitView();
				} catch (FrameIsNullException e1) {
					new FrameIsNullException("无法正常关闭主界面！！!");
				}
			}
			
		});
		
		
		
	}

	@Override
	public JFrame getFrame() {
		return jf;
	}
}
