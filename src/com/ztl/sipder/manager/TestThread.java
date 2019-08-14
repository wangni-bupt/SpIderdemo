package com.ztl.sipder.manager;
/**
 * 测试用途（无用）
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TestThread { 
    public ExecutorService pool = Executors.newFixedThreadPool(5);
	public TestThread() {
		 while(true)
		  {
			  pool.submit(new Runnable() {
				  public void run() {  for(int i = 0;i < 100;i++){ 
				      System.out.println(Thread.currentThread().getName() + "i的值为：" + i); 
				    } 
			  }
			  });
		  }

	  
	}

	    
	  public static void main(String[] args) { 
	    //创建一个具有固定线程数的线程池 
	    //向线程池中提交三个线程 
		  TestThread th=new TestThread();
	    //关闭线程池 

	  }


}