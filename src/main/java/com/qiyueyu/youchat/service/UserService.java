package com.qiyueyu.youchat.service;

import com.qiyueyu.youchat.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiyueyu.youchat.netty.common.ChatDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiyueyu
 * @since 2021-10-25
 */
public interface UserService extends IService<User> {

    /**
     * 保存聊天数据
     * @param chatDate 聊天数据
     * @return
     */
    String saveChatDate(ChatDate chatDate);
}
