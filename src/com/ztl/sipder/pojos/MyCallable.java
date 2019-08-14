package com.ztl.sipder.pojos;
/**
 *  callable子类（无用）
 */
import java.util.concurrent.Callable;

import com.ztl.sipder.iface.HttpClientCrawlerImpl;

public class MyCallable implements Callable<Object> {
    public UrlPojo urlPojo;
	public MyCallable(UrlPojo urlPojo) {
		this.urlPojo=urlPojo;
	}
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		HttpClientCrawlerImpl crawlerImpl = new HttpClientCrawlerImpl();
		CrawResultPojo cr=crawlerImpl.crawl(urlPojo);
		return null;
	}

}
