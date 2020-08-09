package com.whl.serviceImpl;

import com.whl.entity.User;
import com.whl.mapper.UserMapper;
import com.whl.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author whl
 * @since 2020-05-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
