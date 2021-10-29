package com.qiyueyu.youchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiyueyu.workerId.utils.Sid;
import com.qiyueyu.youchat.entity.ChatMsg;
import com.qiyueyu.youchat.mapper.ChatMsgMapper;
import com.qiyueyu.youchat.netty.common.ChatDate;
import com.qiyueyu.youchat.service.ChatMsgService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiyueyu
 * @since 2021-10-25
 */
@Service
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsg> implements ChatMsgService {

    private final Sid sid;
    private final ChatMsgMapper chatMsgMapper;

    public ChatMsgServiceImpl(Sid sid, ChatMsgMapper chatMsgMapper) {
        this.sid = sid;
        this.chatMsgMapper = chatMsgMapper;
    }

    @Override
    public String saveChatDate(ChatDate chatDate) {
        //填充信息
        ChatMsg chatMsg = new ChatMsg();
        String msgId = sid.nextShort();
        chatMsg.setMsg(chatDate.getMsg());
        chatMsg.setAcceptUserId(chatDate.getReceiveId());
        chatMsg.setSendUserId(chatDate.getSenderId());
        chatMsg.setCreateTime(new Date());
        chatMsg.setSignFlag(false);
        chatMsg.setId(msgId);

        chatMsgMapper.insert(chatMsg);

        return msgId;
    }

}
