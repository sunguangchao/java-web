package com.introduce;

import com.gcsun.PerformanceMonitor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by 11981 on 2017/12/3.
 */
public class ControllablePerformaceMonitor extends DelegatingIntroductionInterceptor
        implements Monitorable{
    //保存性能开关状态
    private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<Boolean>();
    public void setMonitorActive(boolean active){
        MonitorStatusMap.set(active);
    }

    //用于拦截目标方法的调用
    public Object invoke(MethodInvocation mi) throws Throwable{
        Object obj = null;
        //对于支持性能监控可控代理，通过判断其状态决定是否开启性能监控功能
        if (MonitorStatusMap.get() != null && MonitorStatusMap.get()){
            PerformanceMonitor.begin(mi.getClass().getName() + "."
                    + mi.getMethod().getName());
            obj = super.invoke(mi);
            PerformanceMonitor.end();
        }else{
            obj = super.invoke(mi);
        }
        return obj;
    }
    public void test(){
        System.out.println("dd");
    }

}
