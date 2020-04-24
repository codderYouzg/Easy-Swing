package com.mec.util;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MenuFactory {
	private static final Font menuFont = new Font("����", Font.PLAIN, 12);
	private JMenuBar bar;
	
	private Map<String, MenuActionDefinaton> menuActionPool;
	private Object object;
	private Class<?> klass;
	
	public MenuFactory() {
	}
	
	public void setObject(Object object) {
		this.object = object;
		this.klass = object.getClass();
	}

	public void setBar(JMenuBar bar) {
		this.bar = bar;
	}
	
	private void creatMenuItem(Element element, JMenu parentMenu) {
		String captionName = element.getAttribute("caption");
		String actionCommand = element.getAttribute("action");
		JMenuItem menuItem = new JMenuItem(captionName);
		menuItem.setActionCommand(actionCommand);
		parentMenu.add(menuItem);
		
		if (menuActionPool.get(actionCommand) == null) {
			try {
				
				Method method = klass.getDeclaredMethod(actionCommand, new Class<?>[] {});
				MenuActionDefinaton miad = new MenuActionDefinaton();
				miad.setObject(object);
				miad.setMethod(method);
				menuActionPool.put(actionCommand, miad);
			} catch (Exception e1) {
				// TODO �����쳣
			}
		}
		
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = menuItem.getActionCommand();
				MenuActionDefinaton miad = menuActionPool.get(command);
				if (miad == null) {
					System.out.println("�˵���û����[" + command+ "]��Ӧ�ķ���");
					return;
				}
				Object object = miad.getObject();
				Method method = miad.getMethod();
				try {
					method.invoke(object, new Object[] {});
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	private JMenu creatMenu(Element element, JMenu parentMenu) {
		String captionName = element.getAttribute("caption");
		JMenu menu = new JMenu(captionName);
		menu.setFont(menuFont);
		if (parentMenu == null) {
			bar.add(menu);
		} else {
			parentMenu.add(menu);
		}
		
		return menu;
	}
	
	private void dealMenu(Element element, JMenu parentMenu) {
		new XMLParser() {
			@Override
			public void dealElement(Element element, int index) {
				String tagName = element.getTagName();
				String captionName = element.getAttribute("caption");
				if (tagName.equalsIgnoreCase("menu")) {	// ����˵�
					JMenu menu = creatMenu(element, parentMenu);
					dealMenu(element, menu);	//����˵�/�˵���
					return;
				}
				if (tagName.equalsIgnoreCase("item")) {	// ����˵���
					creatMenuItem(element, parentMenu);
					return;
				}
				if (tagName.equalsIgnoreCase("separator")) {	// ����ָ���
					if (parentMenu == null) {
						return;
					}
					parentMenu.addSeparator();
				}
			}
		}.parseElement(element);
	}
	
	public void loadMenu(String menuConfigFile) {
		Document document = XMLParser.getDocument(menuConfigFile);
		if (document == null) {
			return;
			//TODO ���Ա�/ָ���쳣
		}
		
		menuActionPool = new Hashtable<String, MenuActionDefinaton>();
		new XMLParser() {
			
			@Override
			public void dealElement(Element element, int index) {
				dealMenu(element, null);
			}
		}.parseRoot(document);;
	}
	
}
