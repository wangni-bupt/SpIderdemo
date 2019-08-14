package com.ztl.sipder.iface;
/**
 *  ÏÂÔØÆ÷
 */
import com.ztl.sipder.impl.ICrawler;
import com.ztl.sipder.jiexi.ProcessService;
import com.ztl.sipder.pojos.CrawResultPojo;
import com.ztl.sipder.pojos.UrlPojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientCrawlerImpl implements ICrawler{

	@Override
	public CrawResultPojo crawl(UrlPojo urlPojo) {
			CloseableHttpClient httpclient = HttpClients.custom()
	                .build();
	        if (urlPojo == null) {
	            return null;
	        }
	        CrawResultPojo crawlResultPojo = new CrawResultPojo();
	        CloseableHttpResponse response1 = null;
	        BufferedReader br = null;
	        try {
	            HttpGet httpget = new HttpGet(urlPojo.getUrl());
	            response1 = httpclient.execute(httpget);
	            HttpEntity entity = response1.getEntity();
	            InputStreamReader isr = new InputStreamReader(entity.getContent(),
	                    "utf-8");
	            br = new BufferedReader(isr);

	            String line = null;
	            StringBuilder stringBuilder = new StringBuilder();
	            while ((line = br.readLine()) != null) {
	                stringBuilder.append(line + "\n");
	            }
	            crawlResultPojo.setSuccess(true);
	            crawlResultPojo.setPageContent(stringBuilder.toString());
	            br.close();
	            return crawlResultPojo;
	        } catch (Exception e) {
	            e.printStackTrace();
	            crawlResultPojo.setSuccess(false);
	        } finally {
	            if (response1 != null) {
	                    try {
							response1.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            }
	            }
	        
	        return crawlResultPojo;
	}
	public static void main(String[] args) {
		
		HttpClientCrawlerImpl crawlerImpl = new HttpClientCrawlerImpl();
		UrlPojo urlPojo=new UrlPojo("https://list.youku.com/category/show/c_100_a_ÈÕ±¾_ag__s_1_d_2_r__u__pt_.html?spm=a2ha1.12523958.m_4396.d_keyword_0&scm=20140719.manual.4396.url_in_blank_https%3A%2F%2Flist.youku.com%2Fcategory%2Fshow%2Fc_100_a_%25E6%2597%25A5%25E6%259C%25AC_ag__s_1_d_2_r__u__pt_.html%3Fspm%3Da2ha1.12701310.app.5~5!2~5~5~5~DL!7~DD~A");
		CrawResultPojo cr=crawlerImpl.crawl(urlPojo);
		ProcessService pocess=new ProcessService();
		System.out.println("sdf");
	
		
		
	}

	
}
