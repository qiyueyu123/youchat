package com.qiyueyu.youchat.service.impl;

import com.qiyueyu.youchat.netty.common.ChatDate;
import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.service.MsgStrategy;
import io.netty.channel.Channel;

/**
 * 消息策略实现 消息类型：CHAT_MSG 聊天信息
 */
public class ChatMsgStrategy implements MsgStrategy {

    @Override
    public void msgProcessing(DateContent content, Channel channel) {
        //获取聊天记录
        ChatDate chatDate = content.getChatDate();
        String msg = chatDate.getMsg();
        String senderId = chatDate.getSenderId();
        String receiveId = chatDate.getReceiveId();
    }
}
