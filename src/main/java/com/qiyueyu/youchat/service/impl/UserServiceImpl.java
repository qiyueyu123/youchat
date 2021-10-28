package com.qiyueyu.youchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiyueyu.youchat.entity.ChatMsg;
import com.qiyueyu.youchat.entity.User;
import com.qiyueyu.youchat.mapper.UserMapper;
import com.qiyueyu.youchat.netty.common.ChatDate;
import com.qiyueyu.youchat.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //private final Sid sid;

    @Override
    public String saveChatDate(ChatDate chatDate) {
        //填充信息
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setMsg(chatDate.getMsg());
        chatMsg.setAcceptUserId(chatDate.getReceiveId());
        chatMsg.setSendUserId(chatDate.getSenderId());
        chatMsg.setCreateTime(new Date());
        chatMsg.setSignFlag(false);
        return null;
    }
}
