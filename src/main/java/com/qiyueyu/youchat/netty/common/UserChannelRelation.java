package com.qiyueyu.youchat.netty.common;

import lombok.extern.slf4j.Slf4j;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Map;

/**
 *user-channel关系类，存储user和channel对应的关联
 */
@Slf4j
public class UserChannelRelation {

    public static HashMap<String, Channel> manager = new HashMap<>();

    /**
     *存储方法 存储 senderId 和 Channel
     * @param senderId 发送者Id
     * @param channel user 对应 channel
     */
    public static void put(String senderId, Channel channel) {
        manager.put(senderId, channel);
    }

    /**
     * 获取方法 根据 senderId 获取对应 channel
     * @param senderId 发送者Id
     * @return channel
     */
    public static Channel get(String senderId) {
        return manager.get(senderId);
    }

    /**
     * 获取所有 user 和 channel 对应关系
     */
    public static void getAll() {
        for (Map.Entry<String, Channel> channelEntry : manager.entrySet()) {
            log.info("user:{}", channelEntry.getKey(), " channelId:{}", channelEntry.getValue());
        }
    }
}
