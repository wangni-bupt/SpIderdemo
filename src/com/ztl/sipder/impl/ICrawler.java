package com.ztl.sipder.impl;

import com.ztl.sipder.pojos.CrawResultPojo;
import com.ztl.sipder.pojos.UrlPojo;

public interface ICrawler {
	public CrawResultPojo crawl(UrlPojo urlPojo);
}
