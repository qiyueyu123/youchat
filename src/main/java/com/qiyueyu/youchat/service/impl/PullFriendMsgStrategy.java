package com.qiyueyu.youchat.service.impl;

import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.service.MsgStrategy;

import io.netty.channel.Channel;

/**
 * 消息策略实现 消息类型：PULL_FRIEND 拉取好友信息
 */
public class PullFriendMsgStrategy implements MsgStrategy {
    @Override
    public void msgProcessing(DateContent content, Channel channel) {

    }
}
