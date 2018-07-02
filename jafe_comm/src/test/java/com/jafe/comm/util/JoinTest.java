package com.jafe.comm.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;  
  
class JoinTest0 implements Runnable{  
  
    @Override  
    public void run() {  
        System.out.printf("Beginning data sources loading: %s\n",new Date());  
        try {  
          TimeUnit.SECONDS.sleep(4);  
        } catch (InterruptedException e) {  
          e.printStackTrace();  
        }  
        System.out.printf("Data sources loading has finished: %s\n",new Date());  
    }  
  
}

public class JoinTest implements Runnable{  
	  
    @Override  
    public void run() {  
        System.out.printf("Beginning network connect loading: %s\n",new Date());  
        try {  
          TimeUnit.SECONDS.sleep(6);  
        } catch (InterruptedException e) {  
          e.printStackTrace();  
        }  
        System.out.printf("Network connect loading has finished: %s\n",new Date());  
          
    }  
      
    public static void main(String[] args){  
    	JoinTest0 dsLoader = new JoinTest0();  
        Thread thread1 = new Thread(dsLoader,"DataSourceThread");  
          
        JoinTest ncLoader = new JoinTest();  
        Thread thread2 = new Thread(ncLoader,"NetworkConnectionLoader");  
          
        thread1.start();  
        thread2.start();   
          
        try {  
            thread1.join();  
            thread2.join(3000);//1900 3000  
          } catch (InterruptedException e) {  
            e.printStackTrace();  
          }  
            
          System.out.printf("Main: Configuration has been loaded: %s\n",new Date());  
    }  
  
} 