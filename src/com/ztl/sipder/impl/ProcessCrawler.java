package com.ztl.sipder.impl;

import java.util.List;

import com.ztl.sipder.pojos.CrawResultPojo;
import com.ztl.sipder.pojos.UrlPojo;

public interface ProcessCrawler {
	public void process(CrawResultPojo resultPojo,UrlPojo urlPojo,List<UrlPojo> waiturl,int count,Object signal);
}
