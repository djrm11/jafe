package com.jafe.comm.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest extends Thread {
	AliasLock lock;
	private int id;

	public ReentrantLockTest(int i, AliasLock test) {
		this.id = i;
		this.lock = test;
	}

	public void run() {
		lock.print(id);
	}

	public static void main(String args[]) {
		ExecutorService service = Executors.newCachedThreadPool();
		AliasLock lock = new AliasLock();
		for (int i = 0; i < 10; i++) {
			service.submit(new ReentrantLockTest(i, lock));
		}
		service.shutdown();
	}
}

class AliasLock {
	private ReentrantLock lock = new ReentrantLock();

	public void print(int str) {
		try {
			lock.lock();
			System.out.println(str + "获得");
			Thread.sleep((int) (Math.random() * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("		"+str + "释放");
			lock.unlock();
		}
	}
}