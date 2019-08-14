package com.ztl.sipder.manager;
/**
 *  ���̹߳���
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ����
 */
import com.ztl.sipder.iface.CrawlerImpl;
import com.ztl.sipder.iface.HttpClientCrawlerImpl;
import com.ztl.sipder.impl.ICrawler;
import com.ztl.sipder.jiexi.ProcessService;
import com.ztl.sipder.pojos.CrawResultPojo;
import com.ztl.sipder.pojos.MyCallable;
import com.ztl.sipder.pojos.UrlPojo;


public class CrawlerManager {
	
	ArrayList<UrlPojo> allurlSet = new ArrayList<UrlPojo>();
	ArrayList<UrlPojo> notCrawlurlSet = new ArrayList<UrlPojo>();
	

	int crawDepth  = 2; 
	int threadCount = 5; 
	int count = 0; 

	public static final Object signal = new Object();   //�̼߳�ͨ�ű���

	

/*	public static void main(String[] args) {

		final CrawlerManager wc = new CrawlerManager();
		wc.addUrl("https://www.cnblogs.com", 1);
		long start= System.currentTimeMillis();
		System.out.println("��ʼ����.........................................");
		wc.begin();
		while(true){
			if(Thread.activeCount() == 1||wc.count==wc.threadCount){
				long end = System.currentTimeMillis();
				System.out.println("�ܹ�����"+wc.allurlSet.size()+"����ҳ");
				System.out.println("�ܹ���ʱ"+(end-start)/1000+"��");
				System.exit(1);
//				break;
			}
		}

	}
*/
	public void manager(String urlpojo)
	{
		CrawlerManager wc = new CrawlerManager();
		wc.addUrl(urlpojo, 1);
		long start= System.currentTimeMillis();
		System.out.println("��ʼ����.........................................");
		wc.begin();
		while(true){
			if(Thread.activeCount() == 1||wc.count==wc.threadCount){
				long end = System.currentTimeMillis();
				System.out.println("�ܹ�����"+wc.allurlSet.size()+"����ҳ");
				System.out.println("�ܹ���ʱ"+(end-start)/1000+"��");
				System.exit(1);
//				break;
			}
		}
	}
	
	public void begin() {
		for(int i=1;i<threadCount+1;i++){
			new Thread(new Runnable(){
				public void run() {
					while (true) { 
						UrlPojo tmp = getAUrl();
						if(tmp!=null&&tmp.getDepth()<crawDepth){
							HttpClientCrawlerImpl crawlerImpl = new HttpClientCrawlerImpl();
							CrawResultPojo cr=crawlerImpl.crawl(tmp);
							ProcessService pocess=new ProcessService();
							pocess.process(cr,tmp,notCrawlurlSet,count,signal);
							while(notCrawlurlSet.size()>0) {
							if(count>0)
							{
								synchronized(signal) {
									count--;
									signal.notify();
								}
							}
							}
						}else{
							synchronized(signal) {
								try {
									count++;
									System.out.println("��ǰ��"+count+"���߳��ڵȴ�");
									signal.wait();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}

					}

				}

			},"thread-"+i).start();

		}

	}

	public synchronized  UrlPojo getAUrl() {
		if(notCrawlurlSet.isEmpty())
			return null;
		UrlPojo tmpAUrl;
			tmpAUrl= notCrawlurlSet.get(0);
			notCrawlurlSet.remove(0);
		return tmpAUrl;
	}


	public synchronized void  addUrl(String url,int d){
		UrlPojo urlpojo=new UrlPojo(url);
		urlpojo.setDepth(d);
			notCrawlurlSet.add(urlpojo);
			allurlSet.add(urlpojo);
	}

	//��context��ȡurl��ַ
/*
	public  void parseContext(String context,int dep) {

	    String regex = "<a href.*?/a>";

//		String regex = "<title>.*?</title>";

		String s = "fdfd<title>�� ��</title><a href=\"http://www.iteye.com/blogs/tag/Google\">Google</a>fdfd<>";

		// String regex ="http://.*?>";

		Pattern pt = Pattern.compile(regex);

		Matcher mt = pt.matcher(context);

		while (mt.find()) {

//			System.out.println(mt.group());

			Matcher myurl = Pattern.compile("href=\".*?\"").matcher(

					mt.group());

			while(myurl.find()){

				String str = myurl.group().replaceAll("href=\"|\"", "");

//				System.out.println("��ַ��:"+ str);

				if(str.contains("http:")){ //ȡ��һЩ����url�ĵ�ַ

					if(!allurlSet.contains(str)){

						addUrl(str, dep);//����һ���µ�url

						if(count>0){ //����еȴ����̣߳�����

							synchronized(signal) {  

								count--;

								signal.notify();

							}

						}

						

					}

				}

			}

		}

	}

}	

	*/
	/*public void crawl2()
	{
		for(int i=0;i<5;i++)
		{
			pool.submit(new Runnable() {
				@Override
				public void run() {
				if(waiturl.size()>0) {
					UrlPojo url=waiturl.get(0);
					waiturl.remove(0);
					if(url.getDepth()<maxdepth||overurl.contains(url)) {
						HttpClientCrawlerImpl crawlerImpl = new HttpClientCrawlerImpl();
						CrawResultPojo cr=crawlerImpl.crawl(url);
						ProcessService pocess=new ProcessService();
						pocess.process(cr,url,waiturl);
						overurl.add(url);
					}
					}
				}
			});
		}
	}*/

    
	
  /*  public static void main(String args[]) {
    	String url1="http://www.baidu.com";
    	UrlPojo urlPojo=new UrlPojo(url1);
    	urlPojo.setDepth(0);
    	CrawlerManager cm=new CrawlerManager(urlPojo);
    	
    		cm.crawl2();
    		
    		//for(UrlPojo url:cm.Getoverurl()) {
    		//	if(cm.Getoverurl())
    		//}
    	//ExecutorService es=cm.GetPool();
        //while(es.isTerminated());
    	//System.out.println(es.isTerminated());
    }
*/
}
