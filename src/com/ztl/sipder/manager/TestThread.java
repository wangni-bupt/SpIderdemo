package com.ztl.sipder.manager;
/**
 * ������;�����ã�
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
				      System.out.println(Thread.currentThread().getName() + "i��ֵΪ��" + i); 
				    } 
			  }
			  });
		  }

	  
	}

	    
	  public static void main(String[] args) { 
	    //����һ�����й̶��߳������̳߳� 
	    //���̳߳����ύ�����߳� 
		  TestThread th=new TestThread();
	    //�ر��̳߳� 

	  }


}