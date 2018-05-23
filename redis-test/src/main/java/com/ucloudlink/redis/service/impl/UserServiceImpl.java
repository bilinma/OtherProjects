package com.ucloudlink.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucloudlink.redis.bo.User;
import com.ucloudlink.redis.dao.IUserMapper;
import com.ucloudlink.redis.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserMapper userDao;

	@Cacheable(value = "common", key = "'id_'+#id")
	public User selectByPrimaryKey(Long id) {
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		return userDao.selectByPrimaryKey(id);
	}

	@CachePut(value = "common", key = "'id_'+#user.getId()")
	public User insertSelective(User user) {
		userDao.insertSelective(user);
		System.out.println("########################");
		System.out.println("########################");
		System.out.println("########################");
		return user; 
	}

	@CacheEvict(value = "common", key = "'id_'+#id")
	public void deleteByPrimaryKey(Long id) {
		userDao.deleteByPrimaryKey(id);
		System.out.println("******************************");
		System.out.println("******************************");
		System.out.println("******************************");
	}
}
