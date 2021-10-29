package com.qiyueyu.youchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiyueyu.youchat.entity.User;
import com.qiyueyu.youchat.mapper.UserMapper;
import com.qiyueyu.youchat.service.UserService;
import org.springframework.stereotype.Service;

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

}
