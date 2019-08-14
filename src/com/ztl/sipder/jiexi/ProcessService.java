package com.ztl.sipder.jiexi;
/**
 *  ����������
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ztl.sipder.iface.HttpClientCrawlerImpl;
import com.ztl.sipder.impl.ProcessCrawler;
import com.ztl.sipder.pojos.CrawResultPojo;
import com.ztl.sipder.pojos.Savetype;
import com.ztl.sipder.pojos.UrlPojo;




public class ProcessService implements ProcessCrawler {
	
	
	public void process(CrawResultPojo resultPojo,UrlPojo urlPojo,List<UrlPojo> waiturl,int count,Object signal) {
		
		String content=resultPojo.toString();
		Document doc = Jsoup.parse(content,urlPojo.getUrl());
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements titles = doc.select("a[title]");
		StringBuilder sb=new StringBuilder();
		
/*			System.out.println(title.attr("abs:title"));
        }
*/

		for (Element src : media) {
            if (src.tagName().equals("img")) {
            	 // System.out.println(src.tagName()+":"+src.attr("abs:src")+src.attr("width")+src.attr("height"));
            	Download(src.attr("abs:src").toString());
            	;
            }
            	else  	  
            	//System.out.println(src.tagName()+src.attr("abs:src"));
            		;
        }
     
		for (Element link : links) {
			//System.out.println(link.attr("abs:href"));
			UrlPojo url=new UrlPojo(link.attr("abs:href"));
			int depth=urlPojo.getDepth()+1;
			url.setDepth(depth);
			waiturl.add(url);
			
        }
		
		
	}
	
	public void Download(String url) {
        try {
            //��ʼʱ��
            Date begindate = new Date();
                //��ʼʱ��
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File(Savetype.getSavepath()+imageName));//�ļ������
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("��ʼ����:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                //�ر���
                in.close();
                fo.close();
                System.out.println(imageName + "�������");
                //����ʱ��
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("��ʱ��" + time / 1000 + "s");
            Date overdate = new Date();
            double time1 = overdate.getTime() - begindate.getTime();
            System.out.println("�ܺ�ʱ��" + time1 / 1000 + "s");
        } catch (Exception e) {
            e.printStackTrace();
   }
	}
    
	
	
 /*   public static void main(String args[]) {
    	List<UrlPojo> waiturl=new ArrayList<>();
    	String url1="http://www.baidu.com";
    	String url2="http://www.baidu.com";
    	UrlPojo urlPojo=new UrlPojo(url1);
    	UrlPojo urlPojo2=new UrlPojo(url2);
    	waiturl.add(urlPojo);
    	waiturl.add(urlPojo2);
    	HttpClientCrawlerImpl crawlerImpl = new HttpClientCrawlerImpl();

			CrawResultPojo cr=crawlerImpl.crawl(waiturl.get(0));
			ProcessService pocess=new ProcessService();
			pocess.process(cr, urlPojo, waiturl);
			waiturl.remove(0);
			System.out.println(waiturl.size());

		}*/
}


