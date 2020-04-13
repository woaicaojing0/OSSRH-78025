package com.deepbluebi.basic.service.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 描述：
 *
 * @author zhanghao
 * @create 2018-11-13-15:10
 */
public class ThreadPoolService {

    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("attendance-pool-%d").build();

    private static final ThreadPoolExecutor attendanceThreadPool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<Runnable>(5000), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


    private static final ThreadPoolExecutor attendanceDailyThreadPool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<Runnable>(5000), new ThreadFactoryBuilder().setNameFormat("attendance-daily-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    private static final ThreadPoolExecutor attendanceAddThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public enum PoolType {
        ATTENDANCE,
        ATTENDANCE_DAILY,
        ATTENDANCE_ADD
    }

    private ThreadPoolService() {
    }

    /**
     * 向线程池加入一个新的任务
     *
     * @param type
     * @param task
     */
    public static void addTask(PoolType type, Runnable task) {
        ThreadPoolExecutor pool = getPool(type);
        pool.execute(task);
    }

    /**
     * 向线程池加入一个新的任务
     *
     * @param type
     * @param task
     */
    public static <T> Future<T> addTask(PoolType type, Callable<T> task) {
        ThreadPoolExecutor pool = getPool(type);
        return pool.submit(task);
    }

    /**
     * 获取线程池当前活动线程数
     *
     * @param type
     * @return
     */
    public static int getActiveThreadCount(PoolType type) {
        return getPool(type).getPoolSize();
    }

    /**
     * 获取指定类型的线程池
     *
     * @param type
     * @return
     */
    public static ThreadPoolExecutor getPool(PoolType type) {
        ThreadPoolExecutor pool = null;

        switch (type) {
            case ATTENDANCE: {
                pool = attendanceThreadPool;
                break;
            }
            case ATTENDANCE_DAILY: {
                pool = attendanceDailyThreadPool;
                break;
            }
            case ATTENDANCE_ADD: {
                pool = attendanceAddThreadPool;
                break;
            }
            default: {
                throw new IllegalArgumentException("非法的线程池类型");
            }
        }

        return pool;
    }
}