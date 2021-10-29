package com.qiyueyu.youchat.strategy;

import com.qiyueyu.youchat.entity.User;
import com.qiyueyu.youchat.mapper.UserMapper;
import com.qiyueyu.youchat.netty.common.ChatDate;
import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.netty.common.UserChannelRelation;
import com.qiyueyu.youchat.netty.handler.ChatHandler;
import com.qiyueyu.youchat.service.ChatMsgService;
import com.qiyueyu.youchat.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息策略实现 消息类型：CHAT_MSG 聊天信息
 */
@Component
@Slf4j
public class MsgChatStrategy implements MsgStrategy {

    @Autowired
    private ChatMsgService chatMsgService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void msgProcessing(DateContent content, Channel channel) {
        DateContent dateContent = new DateContent();
        //获取聊天记录
        ChatDate chatDate = content.getChatDate();
        String msg = chatDate.getMsg();
        String senderId = chatDate.getSenderId();
        String receiveId = chatDate.getReceiveId();
        String msgId = chatMsgService.saveChatDate(chatDate);
        //填充信息
        chatDate.setMsgId(msgId);
        dateContent.setChatDate(chatDate);
        //获取发送者和接收者信息
        User sender = userMapper.selectById(senderId);
        User receiver = userMapper.selectById(receiveId);
        //获取receiver对应的channel，根据channel进行消息推送
        Channel receiveChannel = UserChannelRelation.get(receiveId);
        Channel findChannel = ChatHandler.userGroup.find(receiveChannel.id());
        //用户离线：推送消息  用户在线：发送消息
        if (findChannel != null) {
            receiveChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dateContent)));
        } else {
            //App推送消息 用户不在线
            log.info("用户离线");
        }



    }
}
