package com.qiyueyu.youchat.strategy;

import com.qiyueyu.youchat.mapper.UserMapper;
import com.qiyueyu.youchat.netty.common.ChatDate;
import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.service.UserService;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息策略实现 消息类型：CHAT_MSG 聊天信息
 */
public class MsgChatStrategy implements MsgStrategy {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;


    @Override
    public void msgProcessing(DateContent content, Channel channel) {
        //获取聊天记录
        ChatDate chatDate = content.getChatDate();
        String msg = chatDate.getMsg();
        String senderId = chatDate.getSenderId();
        String receiveId = chatDate.getReceiveId();

        String msgId = userService.saveChatDate(chatDate);
    }
}
