package com.qiyueyu.youchat.strategy;

import com.qiyueyu.youchat.mapper.ChatMsgMapper;
import com.qiyueyu.youchat.netty.common.DateContent;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息策略实现 消息类型：SIGNED_MSG 聊天信息签收
 */
@Component
@Slf4j
public class MsgSignedStrategy implements MsgStrategy {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    @Override
    public void msgProcessing(DateContent content, Channel channel) {
        //获取msgId信息并进行分割
        String msgIdStr = content.getExtend();
        String[] msgIds = msgIdStr.split(",");

        //去除list中为空的值
        List<String> msgIdList = Arrays.asList(msgIds);
        List<String> msgIdCheckList = msgIdList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        //批量更新消息状态
        chatMsgMapper.batchUpdateSignFlagByMsgIds(msgIdCheckList);



    }
}
