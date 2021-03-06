package com.jafe.comm.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author yzf
 * @since 2017年5月26日
 * @remark
 */

public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
//		Callable<String> call = new Callable<String>() {
//			public String call() throws Exception {
//				System.out.println("Callable.call() start...");
//				Thread.sleep(1000 * 10);// 休眠指定的时间，此处表示该操作比较耗时
//				System.out.println("Callable.call() end...");
//				return "Other less important but longtime things.";
//			}
//		};
        Callable<String> call = ()-> {
				System.out.println("Callable.call() start...");
				Thread.sleep(1000 * 10);// 休眠指定的时间，此处表示该操作比较耗时
				System.out.println("Callable.call() end...");
				return "Other less important but longtime things.";
        };
		Future<String> task = exec.submit(call);
		// 重要的事情
		System.out.println("Let's do important things. start");
		Thread.sleep(1000 * 3);
		System.out.println("Let's do important things. end");

		// 不重要的事情
		while (!task.isDone()) {
			System.out.println("still waiting....");
			Thread.sleep(1000 * 1);
		}
		System.out.println("get sth....");
		String obj = task.get();
		System.out.println(obj);
		// 关闭线程池
		exec.shutdown();

		while(true){
			if(exec.isTerminated()){
//				logger.info("CmtPointGetDetailFromCommentJob.doCmtCommentToPointDetail, run page: "+ page+" end. In total page: " +500);
				System.out.println("wait for ExecutorService end!");
				break;
			}
			Thread.sleep(500);
		}
	}
}
