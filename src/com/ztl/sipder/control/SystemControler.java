package com.ztl.sipder.control;
import com.ztl.sipder.pojos.SystemControlerCrawler;
/**
 *  Èë¿ÚÀà
 */
import com.ztl.sipder.pojos.UrlPojo;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.ztl.sipder.iface.CrawlerImpl;

class SystemControler {
	 public static void main(String[] args)
     {
            EventQueue.invokeLater(()->
                {
                      JFrame frame=new SystemControlerCrawler();
                      frame.setTitle("WebCrawler");
                      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                      frame.setVisible(true);
                });
     }

}
