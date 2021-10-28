package com.qiyueyu.youchat.strategy;

import com.qiyueyu.youchat.netty.common.DateContent;
import io.netty.channel.Channel;

/**
 * 消息策略实现 消息类型：SIGNED_MSG 聊天信息签收
 */
public class MsgSignedStrategy implements MsgStrategy {
    @Override
    public void msgProcessing(DateContent content, Channel channel) {


    }
}
