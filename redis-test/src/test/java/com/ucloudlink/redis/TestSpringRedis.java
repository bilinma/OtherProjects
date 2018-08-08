package com.ucloudlink.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ucloudlink.redis.bo.User;
import com.ucloudlink.redis.service.IUserService;
import com.ucloudlink.redis.utils.RedisTemplateUtil;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class TestSpringRedis {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private RedisTemplateUtil  redisTemplateUtil;

	@Test
	public void testRedis() {
		
		User user = new User();
		user.setId(1L);
		user.setName("马小斌");
		redisTemplateUtil.set("user_"+1, user);
		System.out.println(redisTemplateUtil.get("user_"+1));
		
		// stringRedisTemplate的操作
		// String读写
		redisTemplate.delete("myStr");
		redisTemplate.opsForValue().set("myStr", "skyLine");
		System.out.println(redisTemplate.opsForValue().get("myStr"));
		System.out.println("---------------");

		// List读写
		redisTemplate.delete("myList");
		redisTemplate.opsForList().rightPush("myList", "T");
		redisTemplate.opsForList().rightPush("myList", "L");
		redisTemplate.opsForList().leftPush("myList", "A");
		List<String> listCache = redisTemplate.opsForList().range("myList", 0, -1);
		for (String s : listCache) {
			System.out.println(s);
		}
		System.out.println("---------------");

		// Set读写
		redisTemplate.delete("mySet");
		redisTemplate.opsForSet().add("mySet", "A");
		redisTemplate.opsForSet().add("mySet", "B");
		redisTemplate.opsForSet().add("mySet", "C");
		Set<String> setCache = redisTemplate.opsForSet().members("mySet");
		for (String s : setCache) {
			System.out.println(s);
		}
		System.out.println("---------------");

		// Hash读写
		redisTemplate.delete("myHash");
		redisTemplate.opsForHash().put("myHash", "BJ", "北京");
		redisTemplate.opsForHash().put("myHash", "SH", "上海");
		redisTemplate.opsForHash().put("myHash", "HN", "河南");
		Map<String, String> hashCache = redisTemplate.opsForHash().entries("myHash");
		for (Map.Entry entry : hashCache.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		System.out.println("---------------");
	}

	@Autowired
	private IUserService userService;

	@Test
	public void add() {
		User user = new User();
		user.setName("wen");
		userService.insertSelective(user);
	}

	@Test
	public void query() {
		User user = userService.selectByPrimaryKey(8L);
		System.out.println(user.toString());
	}
}
