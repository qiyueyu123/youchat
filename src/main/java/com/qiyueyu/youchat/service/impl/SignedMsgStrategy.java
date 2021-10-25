package com.qiyueyu.youchat.service.impl;

import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.service.MsgStrategy;

import io.netty.channel.Channel;

/**
 * 消息策略实现 消息类型：SIGNED_MSG 聊天信息签收
 */
public class SignedMsgStrategy implements MsgStrategy {
    @Override
    public void msgProcessing(DateContent content, Channel channel) {


    }
}
