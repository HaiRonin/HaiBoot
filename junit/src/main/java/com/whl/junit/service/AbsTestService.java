package com.whl.junit.service;

abstract class  AbsTestService implements TestHandle{

    @Override
    public void doWork(String str) {
        System.out.println(this.getClass().getName()+"doWork="+str);
        System.out.println(this.getClass().getName()+"结束抽象类方法="+str);
    }

    @Override
    public void getWorkList(String str) {
        System.out.println(this.getClass().getName()+"getWorkList by "+str);
        getMyWork();
    }

    abstract void getMyWork();
}
