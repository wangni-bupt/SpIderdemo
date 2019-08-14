package com.ztl.sipder.pojos;
/**
 * 抓取结果的封装
 * @author Administrator
 *
 */
public class CrawResultPojo {
	private boolean isSuccess;
	@SuppressWarnings("unused")
	private String pageContent;
	private int httpStatusCode;
	
	public CrawResultPojo(){
		
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	
	public void setSuccess(boolean isSuccess) {
		this.isSuccess=isSuccess;
	}
	
	public void setPageContent(String pageContent) {
		this.pageContent=pageContent;
	}
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	@Override
	public String toString() {
		return "CrawResultPojo [isSuccess=" + isSuccess + ", pageContent=" + pageContent + ", httpStatusCode="
				+ httpStatusCode + "]";
	}
	
	

}
