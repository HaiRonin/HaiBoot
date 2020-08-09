package main.com.whl.common;

import org.springframework.context.ApplicationEvent;

/***
 * 事件源：
 * Spring的事件源为ApplicationEvent,继承至JDK提供的EventObject 基类。
 */
public class MyContextEvent extends ApplicationEvent {
    public MyContextEvent(Object source) {
        super(source);
        System.out.println("source message->"+source.toString());
    }
}
