package com.qiyueyu.youchat.service.impl;

import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.netty.common.UserChannelRelation;
import com.qiyueyu.youchat.service.MsgStrategy;
import io.netty.channel.Channel;


/**
 * 消息策略实现 ： 消息类型为：CONNECT 初始化连接(第一次或重连)
 */
public class ConnectMsgStrategy implements MsgStrategy {

    @Override
    public void msgProcessing(DateContent content, Channel channel) {
        UserChannelRelation.put(content.getChatDate().getSenderId(), channel);
    }
}
