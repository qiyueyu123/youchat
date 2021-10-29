package com.qiyueyu.workerId.strategy;

/**
 * workerId 策略实现类
 */
public class DefaultWorkerIdStrategy implements WorkerIdStrategy {

    public static final WorkerIdStrategy instance = new DefaultWorkerIdStrategy();

    @Override
    public void initialize() {

    }

    @Override
    public long availableWorkerId() {
        return 0;
    }

    @Override
    public void release() {

    }
}
