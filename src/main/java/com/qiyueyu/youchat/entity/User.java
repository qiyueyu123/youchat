package com.qiyueyu.youchat.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码，已加密")
    private String password;

    @ApiModelProperty(value = "用户头像")
    private String faceImage;

    @ApiModelProperty(value = "用户大头像")
    private String faceImageBig;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户二维码")
    private String qrcode;

    @ApiModelProperty(value = "用户客户端id")
    private String cid;


}
