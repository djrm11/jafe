package com.jafe.comm.util;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @since 2017年5月26日
 * @remark Java等待子线程执行完毕 再执行后续逻辑
 */
class WorkThread extends Thread {
	@Override
	public void run() {
		try {
			System.out.println(getName() + "run start.");
			// 模拟完成子任务执行的时间
			sleep(1000);
			System.out.println(getName() + "run finished.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * @author yzf
 * @since 2017年5月26日
 * @remark 使用CountDownLatch
 */
class WorkThreadT extends Thread {
	private CountDownLatch countDownLatch;

	public WorkThreadT(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			System.out.println(getName() + "run start.");
			sleep(1000);
//			countDownLatch.countDown();
			System.out.println(getName() + "run finished.");
			// 执行子任务完毕之后，countDown减少一个点
			countDownLatch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * @author yzf
 * @since 2017年5月26日
 * @remark 第一种情况用join;第二种用CountDownLatch的wait方法
 */
public class CountDownLatchTest {


	public static void testAnother() {
	 	// 启动两个子线程执行子任务
		WorkThread workThread1 = new WorkThread();
		WorkThread workThread2 = new WorkThread();
		workThread1.start();
		workThread2.start();


		// 阻塞Main线程，执行子线程workThread1和workThread2，完毕后继续执行后续的逻辑

		try {
			workThread1.join();
			workThread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 // 运行后面的业务 System.out.println("run next process.");
	}

	public static void main(String[] args) {
		//创建2个点的CountDownLatch对象
		CountDownLatch countDownLatch = new CountDownLatch(2);

		//将countDownLatch对象的引用传递给子线程里
		WorkThreadT workThread1 = new WorkThreadT(countDownLatch);
		WorkThreadT workThread2 = new WorkThreadT(countDownLatch);
		workThread1.start();
		workThread2.start();

		try {
			//调用await方法阻塞当前线程，等待子线程完成后在继续执行
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("run next process.");
	}
}