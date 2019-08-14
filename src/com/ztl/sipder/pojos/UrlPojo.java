package com.ztl.sipder.pojos;
/**
 *  url工具类
 */
import java.net.URL;

/**
 * 入口类
 * @author Administrator
 *
 */
public class UrlPojo {
	private String url;
	private int depth;
	public UrlPojo(String url) {
		this.url=url;
	}
	
    public String getUrl() {
    	return url;
    }
    
    public String getHost() {
    	URL url;
		try {
			url = new URL(this.url);
			return url.getHost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public void plusDepth() {
		this.depth++;
	}
 
}
