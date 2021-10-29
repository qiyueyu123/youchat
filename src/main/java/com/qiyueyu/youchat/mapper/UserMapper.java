package com.qiyueyu.youchat.mapper;

import com.qiyueyu.youchat.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiyueyu
 * @since 2021-10-25
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
