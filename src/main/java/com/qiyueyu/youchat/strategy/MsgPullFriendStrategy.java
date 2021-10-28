package com.qiyueyu.youchat.strategy;

import com.qiyueyu.youchat.netty.common.DateContent;
import io.netty.channel.Channel;

/**
 * 消息策略实现 消息类型：PULL_FRIEND 拉取好友信息
 */
public class MsgPullFriendStrategy implements MsgStrategy {
    @Override
    public void msgProcessing(DateContent content, Channel channel) {

    }
}
