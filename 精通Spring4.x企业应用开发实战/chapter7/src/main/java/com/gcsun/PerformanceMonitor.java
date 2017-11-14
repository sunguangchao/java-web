package com.gcsun;

/**
 * Created by 11981 on 2017/11/13.
 */
public class PerformanceMonitor {
    //通过一个ThreadLocal保存与调用线程相关的性能监控信息
    private static ThreadLocal<MethodPerformance> performanceRecord =
            new ThreadLocal<MethodPerformance>();

    /**
     * 启动对某一目标方法的性能监视
     */
    public static void begin(String method){
        System.out.println("begin monitor...");
        MethodPerformance mp = performanceRecord.get();
        if (mp == null){
            mp = new MethodPerformance(method);
            performanceRecord.set(mp);
        }else{
            mp.reset(method);
        }
    }

    public static void end(){
        System.out.println("end monitor...");
        MethodPerformance mp = performanceRecord.get();
        mp.printPerformance();
    }
}
