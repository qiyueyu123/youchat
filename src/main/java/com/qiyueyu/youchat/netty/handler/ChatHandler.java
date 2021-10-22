package com.qiyueyu.youchat.netty.handler;

import com.qiyueyu.youchat.enums.MsgActionEnum;
import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.service.impl.ChatMsgStrategy;
import com.qiyueyu.youchat.service.impl.ConnectMsgStrategy;
import com.qiyueyu.youchat.service.impl.PullFriendMsgStrategy;
import com.qiyueyu.youchat.service.impl.SignedMsgStrategy;
import com.qiyueyu.youchat.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *消息处理器
 */

@Component
@Slf4j
@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //定义channel集合，用于管理channel
    public static ChannelGroup userGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {

        //获取客户端传来的消息
        String msg = textWebSocketFrame.text();
        log.info("接收的数据：{}", msg);

        //json 转换成实体类
        DateContent dateContent = JsonUtils.jsonToPojo(msg, DateContent.class);
        //获取消息动作类型
        assert dateContent != null;
        Integer msgAction = dateContent.getAction();
        log.info("收到消息的动作类型：{}", msgAction);
        //获取channel
        Channel channel = ctx.channel();

        //根据消息动作类型进行逻辑处理
        if (MsgActionEnum.CONNECT.getType().equals(msgAction)) {
            ConnectMsgStrategy connectMsgStrategy = new ConnectMsgStrategy();
            connectMsgStrategy.msgProcessing(dateContent, channel);
        }
        if (MsgActionEnum.CHAT_MSG.getType().equals(msgAction)) {
            ChatMsgStrategy chatMsgStrategy = new ChatMsgStrategy();
            chatMsgStrategy.msgProcessing(dateContent, channel);
        }
        if (MsgActionEnum.SIGNED_MSG.getType().equals(msgAction)) {
            SignedMsgStrategy signedMsgStrategy = new SignedMsgStrategy();
            signedMsgStrategy.msgProcessing(dateContent, channel);
        }
        if (MsgActionEnum.PULL_FRIEND.getType().equals(msgAction)) {
            PullFriendMsgStrategy pullFriendMsgStrategy = new PullFriendMsgStrategy();
            pullFriendMsgStrategy.msgProcessing(dateContent, channel);
        }
    }
}
