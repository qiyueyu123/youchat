package com.qiyueyu.youchat.netty.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据发送、接收实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateContent implements Serializable {

    //动作类型
    private Integer action;
    //传递的消息
    private ChatDate chatDate;
    //扩展字段
    private String extend;
}
