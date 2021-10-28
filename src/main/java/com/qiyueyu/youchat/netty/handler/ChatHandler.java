package com.qiyueyu.youchat.netty.handler;

import com.qiyueyu.youchat.enums.MsgActionEnum;
import com.qiyueyu.youchat.netty.common.DateContent;
import com.qiyueyu.youchat.strategy.MsgChatStrategy;
import com.qiyueyu.youchat.strategy.MsgConnectStrategy;
import com.qiyueyu.youchat.strategy.MsgPullFriendStrategy;
import com.qiyueyu.youchat.strategy.MsgSignedStrategy;
import com.qiyueyu.youchat.utils.JsonUtils;
import io.netty.channel.*;
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
            MsgConnectStrategy connectMsgStrategy = new MsgConnectStrategy();
            connectMsgStrategy.msgProcessing(dateContent, channel);
        }
        if (MsgActionEnum.CHAT_MSG.getType().equals(msgAction)) {
            MsgChatStrategy chatMsgStrategy = new MsgChatStrategy();
            chatMsgStrategy.msgProcessing(dateContent, channel);
        }
        if (MsgActionEnum.SIGNED_MSG.getType().equals(msgAction)) {
            MsgSignedStrategy msgSignedStrategy = new MsgSignedStrategy();
            msgSignedStrategy.msgProcessing(dateContent, channel);
        }
        if (MsgActionEnum.PULL_FRIEND.getType().equals(msgAction)) {
            MsgPullFriendStrategy msgPullFriendStrategy = new MsgPullFriendStrategy();
            msgPullFriendStrategy.msgProcessing(dateContent, channel);
        }
    }


    /**
     * 获取客户端 channel，放到 ChannelGroup 集中管理
     * @param ctx 管道信息
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        userGroup.add(ctx.channel());
    }

    /**
     *handler 移除时，删除 ChannelGroup 中的 channel
     * @param ctx 管道信息
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        userGroup.remove(ctx.channel());
    }

    /**
     * 出错时，关闭连接，并删除 channelGroup 中的 channel
     * @param ctx 管道信息
     * @param cause 报错信息
     * @throws Exception
     */
    @Override
    @Deprecated
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("出错原因：{}", cause.getMessage());
        ctx.channel().close();
        userGroup.remove(ctx.channel());
    }
}
