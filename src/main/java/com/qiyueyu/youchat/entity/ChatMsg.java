package com.qiyueyu.youchat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author qiyueyu
 * @since 2021-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ChatMsg对象", description="")
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "发送人id")
    private String sendUserId;

    @ApiModelProperty(value = "接收人id")
    private String acceptUserId;

    @ApiModelProperty(value = "消息内容")
    private String msg;

    @ApiModelProperty(value = "是否已读")
    private Boolean signFlag;

    @ApiModelProperty(value = "消息创建时间")
    private Date createTime;


}
