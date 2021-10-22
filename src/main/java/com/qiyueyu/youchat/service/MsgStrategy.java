package com.qiyueyu.youchat.service;

import com.qiyueyu.youchat.netty.common.DateContent;
import io.netty.channel.Channel;

/**
 * 消息策略接口
 */
public interface MsgStrategy {
    /**
     * 根据对应入参对方法进行处理
     * @param content
     * @param channel
     */
    void msgProcessing(DateContent content, Channel channel);
}

