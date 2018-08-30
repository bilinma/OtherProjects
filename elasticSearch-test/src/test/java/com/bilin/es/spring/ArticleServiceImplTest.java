package com.bilin.es.spring;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class ArticleServiceImplTest {
	
	@Resource
	private ArticleService articleService;

	/**
	 * TODO 测试部成功 spring 不能整合6.x 以上版本
	 */
	@Test
	public void testInsertOrUpdateNewsInfo() {
		ArticleInf articleInf = new ArticleInf();
		articleInf.setArticleInfId(1);
		articleInf.setArticleTitle("马小斌");
		articleInf.setReleaseTime(new Date());
		articleService.insertOrUpdateNewsInfo(articleInf);
	}

}
