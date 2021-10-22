package com.qiyueyu.youchat.netty.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *消息数据实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDate implements Serializable {

    //发送者Id
    private String senderId;
    //接收者Id
    private String receiveId;
    //消息Id
    private String msgId;
    //消息
    private String msg;
}
