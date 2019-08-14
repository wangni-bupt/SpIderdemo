package com.ztl.sipder.iface;
/**
 *  socket м╗пе
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.ztl.sipder.impl.ICrawler;
import com.ztl.sipder.jiexi.ProcessService;
import com.ztl.sipder.pojos.CrawResultPojo;
import com.ztl.sipder.pojos.UrlPojo;

public class CrawlerImpl implements ICrawler {

	@Override
	public CrawResultPojo crawl(UrlPojo urlPojo) {
		CrawResultPojo crawResultPojos=new CrawResultPojo();
		if(urlPojo==null||urlPojo.getUrl()==null) {
			crawResultPojos.setSuccess(false);
			crawResultPojos.setPageContent(null);
			return crawResultPojos;
		}
		try {
			String host=urlPojo.getHost();
			if(host==null) {
				crawResultPojos.setSuccess(false);
				crawResultPojos.setPageContent(null);
				return crawResultPojos;
			}
			BufferedWriter bw=null;
			BufferedReader br=null;
			try {
				Socket socket=new Socket(host,80);
				bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				bw.write("GET "+urlPojo.getUrl()+" HTTP/1.0\r\n");
				bw.write("HOST:"+host+"\r\n");
				bw.write("\r\n");
				bw.flush();
						
			    br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			    String line=null;
			    StringBuilder stringBuilder = new StringBuilder();
			    
			    while((line=br.readLine())!=null) {
			    	stringBuilder.append(line + "\n");
			    }
			    
			    crawResultPojos.setSuccess(true);
			    crawResultPojos.setPageContent(stringBuilder.toString());
	            br.close(); 
	            socket.close();
	            return crawResultPojos;
			   
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(bw!=null) {
					bw.close();
				}
				if(br!=null) {
					br.close();
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
}

public static void main(String[] args) {
	CrawlerImpl crawlerImpl = new CrawlerImpl();
	UrlPojo urlPojo=new UrlPojo("http://www.baidu.com");
	CrawResultPojo cr=crawlerImpl.crawl(urlPojo);
}
}
