package com.qiyueyu.youchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiyueyu.youchat.entity.ChatMsg;
import com.qiyueyu.youchat.netty.common.ChatDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiyueyu
 * @since 2021-10-25
 */
public interface ChatMsgService extends IService<ChatMsg> {

    /**
     * 保存聊天数据
     * @param chatDate 聊天数据
     * @return
     */
    String saveChatDate(ChatDate chatDate);

}
