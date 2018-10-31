package com.bilin.redis.service;

import com.bilin.redis.bo.User;

public interface IUserService {

	public User selectByPrimaryKey(Long id);
	public User insertSelective(User user);
	public void deleteByPrimaryKey(Long id);
	
}
