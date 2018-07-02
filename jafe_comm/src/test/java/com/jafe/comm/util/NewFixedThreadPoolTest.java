package com.jafe.comm.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolTest extends Thread {

	private int index;

	public NewFixedThreadPoolTest(int i) {

		this.index = i;

	}

	public void run() {

		try {

			System.out.println("[" + this.index + "] start....");

			Thread.sleep((int) (Math.random() * 10000));

			System.out.println("		[" + this.index + "] end.");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void main(String args[]) {

		ExecutorService service = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 10; i++) {

			service.execute(new NewFixedThreadPoolTest(i));
			System.out.println("zf print thread index: " +i);
			// service.submit(new MyExecutor(i));

		}

		System.out.println("submit finish");

		service.shutdown();
		System.out.println("zf print thread finish");	
	}

}