package com.ztl.sipder.pojos;
/**
 *  测试用途
 */

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class SystemControlerCrawler  extends JFrame {
	 public static final int ROWS=8;
     public static final int COLS=20;//行列数

     public SystemControlerCrawler()
     {
             JTextArea textArea=new JTextArea(ROWS,COLS);//设置文本区
             JScrollPane scrollPane=new JScrollPane(textArea);//设置滚动窗格
             add(scrollPane,BorderLayout.CENTER);
             JPanel panel=new JPanel();
             JButton enterButton=new JButton ("确认");//设置确认键
             panel.add(enterButton);
             EnterAction enterAction=new EnterAction(textArea);
             enterButton.addActionListener(enterAction);//添加监听器
             add(panel,BorderLayout.SOUTH);
             pack();
     }

}
