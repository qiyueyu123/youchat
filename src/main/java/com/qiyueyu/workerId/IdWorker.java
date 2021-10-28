package com.qiyueyu.workerId;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

/**
 * workerId生成
 */
@Slf4j
public class IdWorker {
    private long workerId;
    private long datacenterId;
    private long sequence;

    /**
     * @param workerId 工作机器id
     */
    public IdWorker(long workerId){
            this.workerId = checkWorkerId(workerId);
            log.info("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                    timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
    }

    private long checkWorkerId(long workerId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            int rand = new SecureRandom().nextInt((int) maxWorkerId + 1);
            log.warn("worker Id can't be greater than {} or less than 0, use a random {}", maxWorkerId, rand);
            return rand;
        }
        return workerId;
    }

    //时间起始标记点，作为基准
    private long twepoch = 1288834974657L;
    //机器标识位数
    private long workerIdBits = 5L;
    //数据中心标识位数
    private long datacenterIdBits = 5L;
    //机器 id 最大值
    private long maxWorkerId = ~(-1L << workerIdBits);
    //数据中心 id 最大值
    private long maxDatacenterId = ~(-1L << datacenterIdBits);
    //毫秒内自增位
    private long sequenceBits = 12L;
    //机器 id 左移12位
    private long workerIdShift = sequenceBits;
    //数据中心 id 左移17位
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //毫秒内左移22位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = ~(-1L << sequenceBits);
    //上次生成 id 时间戳
    private long lastTimestamp = -1L;

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    public long getTwepoch() {
        return twepoch;
    }

    /**
     * 获取下一个id
     * @return nextId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }


    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }


}
