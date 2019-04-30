package com.orange.commons.utils;

import java.util.concurrent.*;

/**
 * 异步任务工具类 总共可并行执行的线程数是50个
 * 
 * @author wuyajun
 */
public class AsyncTaskUtil {

	private static final ExecutorService	executor	= new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS,
																new LinkedBlockingQueue<Runnable>(5000));

	public static <T> Future<T> ayncTask(Callable<T> c) {
//		return executor.submit(new Callable<T>() {
//
//			@Override
//			public T call() throws Exception {
//				System.out.println("hello world!");
//				return null;
//			}
//		});
		return executor.submit(c);
	}

}
