package com.qiyueyu.workerId.strategy;

/**
 * workerId 策略类
 */
public interface WorkerIdStrategy {

    void initialize();

    long availableWorkerId();

    void release();
}
