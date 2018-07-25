package com.ucloudlink.springmongo.service.user.impl;

import org.springframework.stereotype.Service;

import com.ucloudlink.springmongo.model.User;
import com.ucloudlink.springmongo.service.common.impl.CommonServiceImpl;
import com.ucloudlink.springmongo.service.user.IUserService;

@Service
public class UserServiceImpl extends CommonServiceImpl<User, String> implements IUserService{

}
