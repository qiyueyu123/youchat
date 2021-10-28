package com.qiyueyu.workerId.strategy;

public interface WorkerIdStrategy {

    void initialize();

    long availableWorkerId();

    void release();
}
