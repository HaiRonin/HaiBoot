package com.whl.junit.service;

public class TestImp extends AbsTestService {
    @Override
    public void doWork(String str) {
        System.out.println(this.getClass().getName()+"开始进入实现类方法="+str);
        getWorkList(str);
        System.out.println(this.getClass().getName()+"结束实现类方法="+str);
        super.doWork(str);
    }

    @Override
    void getMyWork() {
        System.out.println(this.getClass().getName()+"this is my work List=");
    }

}
