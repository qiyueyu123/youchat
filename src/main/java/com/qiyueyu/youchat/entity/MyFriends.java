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
@ApiModel(value="MyFriends对象", description="")
public class MyFriends implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前用户id")
    private String myUserId;

    @ApiModelProperty(value = "添加朋友的id")
    private String myFriendUserId;


}
