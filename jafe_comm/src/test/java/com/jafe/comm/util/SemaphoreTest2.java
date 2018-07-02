import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest2 {

	private static final int PERMITS = 3;

	private static final int THREAD_NUM = 8;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 信号量的数目，也可理解为资源的数目
		Semaphore semaphore = new Semaphore(PERMITS);

		// 创建线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);

		for (int i = 0; i < THREAD_NUM; i++) {
			threadPool.execute(new Worker(semaphore, i));
		}

		// 关闭线程池
		threadPool.shutdown();
	}

	public static class Worker implements Runnable {

		private Semaphore semaphore;
		private int id;

		public Worker(Semaphore semaphore, int id) {
			this.id = id;
			this.semaphore = semaphore;
		}

		@Override
		public void run() {

			try {
				
				if (semaphore.availablePermits() > 0) {
					System.out.println("顾客[" + this.id + "]进入厕所，有空位");
				} else {
					System.out.println("顾客[" + this.id + "]进入厕所，没空位，排队");
				}
				
				

				semaphore.acquire();
				System.out.println("Worker_" + id + " acquire...");
				Thread.sleep(2000);

				System.out.println("Worker_" + id + " run...");

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();
			}
		}

	}

}