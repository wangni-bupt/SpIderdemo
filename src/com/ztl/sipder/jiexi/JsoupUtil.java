package com.ztl.sipder.jiexi;
/**
 *  jsoup������ķ�װ
 */

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	public static List<String> getAllHrefAddHost(String fromUrl,String host,String htmlSource) {
		try {
			Document doc=Jsoup.parse(htmlSource);
			Elements links=doc.getElementsByTag("a");
			String linkHref=null;
			List<String> urlList=new LinkedList<String>();
			
			for(Element link:links) {
				linkHref=link.attr("href");
				if(linkHref.startsWith("http://")) {
					//
				}else if(linkHref.startsWith("/")) {
					//����·��
					linkHref="http://"+host+linkHref;
				}else{
					//���·��
					int last_pos=fromUrl.lastIndexOf("/");
					String relative_path=fromUrl.substring(0,last_pos+1);
					linkHref=relative_path+linkHref;
				}
				if(linkHref.startsWith("http://")){
					urlList.add(linkHref);
				}
				return urlList;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
}
