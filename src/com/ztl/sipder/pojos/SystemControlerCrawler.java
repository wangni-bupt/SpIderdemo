package com.ztl.sipder.pojos;
/**
 *  ������;
 */

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class SystemControlerCrawler  extends JFrame {
	 public static final int ROWS=8;
     public static final int COLS=20;//������

     public SystemControlerCrawler()
     {
             JTextArea textArea=new JTextArea(ROWS,COLS);//�����ı���
             JScrollPane scrollPane=new JScrollPane(textArea);//���ù�������
             add(scrollPane,BorderLayout.CENTER);
             JPanel panel=new JPanel();
             JButton enterButton=new JButton ("ȷ��");//����ȷ�ϼ�
             panel.add(enterButton);
             EnterAction enterAction=new EnterAction(textArea);
             enterButton.addActionListener(enterAction);//��Ӽ�����
             add(panel,BorderLayout.SOUTH);
             pack();
     }

}
