package main.com.whl.common;

import org.springframework.context.ApplicationListener;

/**
 * 监听者：
 * Spring的监听者为ApplicationListener,继承至JDK提供的EventListener 接口。其实EventListener中没有任何方法定义，只是作为监听者标识。
 */
public class MyContextListener implements ApplicationListener<MyContextEvent> {
    @Override
    public void onApplicationEvent(MyContextEvent myContextEvent) {
        myContextEvent.getSource();
        System.out.println("listener this MyContextEvent...."+myContextEvent.getSource());
    }
}

