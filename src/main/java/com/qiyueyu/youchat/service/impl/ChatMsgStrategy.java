package com.qiyueyu.youchat.service.impl;

import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.service.MsgStrategy;

import java.nio.channels.Channel;

/**
 * 消息策略实现 消息类型：CHAT_MSG 聊天信息
 */
public class ChatMsgStrategy implements MsgStrategy {

    @Override
    public void msgProcessing(DateContent content, Channel channel) {
    }
}
