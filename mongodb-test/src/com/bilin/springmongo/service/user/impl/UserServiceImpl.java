package com.bilin.springmongo.service.user.impl;

import org.springframework.stereotype.Service;

import com.bilin.springmongo.model.User;
import com.bilin.springmongo.service.common.impl.CommonServiceImpl;
import com.bilin.springmongo.service.user.IUserService;

@Service
public class UserServiceImpl extends CommonServiceImpl<User, String> implements IUserService{

}
