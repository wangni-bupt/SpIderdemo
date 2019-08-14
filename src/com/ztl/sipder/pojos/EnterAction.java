package com.ztl.sipder.pojos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;

import com.ztl.sipder.manager.CrawlerManager;

class EnterAction implements ActionListener//监听器
{
       private static int Max=5;//所需线程
       JTextArea JA;
	   String s;//将文本区的内容导入s

       public EnterAction(JTextArea x) {  
                JA=x;		 
       }
       public void actionPerformed(ActionEvent event) {		         
		         String LINE="";
		         s=JA.getText();
				 BufferedReader br=new BufferedReader(new StringReader(s));
				 try
				 {
					LINE=br.readLine();
					while(LINE!=null)
                    {
					    	CrawlerManager cm=new CrawlerManager();
					        cm.manager(LINE);
					        LINE=br.readLine();
					 }
                    }catch (Exception e){
				 }   
		}  
 }