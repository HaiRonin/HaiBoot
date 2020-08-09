package com.whl.junit.service;

public class Main {
    public static void main(String[] args) {
        TestHandle testHandle = new TestbImp();
        testHandle.doWork("whl");

        testHandle.getWorkList("whl");
    }
}
