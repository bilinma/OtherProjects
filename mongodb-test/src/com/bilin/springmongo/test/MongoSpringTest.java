package com.bilin.springmongo.test;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bilin.springmongo.model.User;
import com.bilin.springmongo.service.user.IUserService;

public class MongoSpringTest {


	// 使用spring整合的话, 就直接注入就可以了
    public static void main(String[] args) { 
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mongodb.xml");
        
        //Spring 封装处理方式
        MongoTemplate mongoTemplate = (MongoTemplate) context.getBean("mongoTemplate");
        MongoSpringTest mongoSpringTest  =  new MongoSpringTest();
        //mongoSpringTest.testAddUser(mongoTemplate);
        mongoSpringTest.testQueryUser(mongoTemplate);
        
        //个人结合Spring封装
        /*IUserService userService = (IUserService) context.getBean("userServiceImpl");
        User zhanggc = new User();
        zhanggc.setName("李刚");
        zhanggc.setAge(30);
        userService.insert(zhanggc);
        
        List<User> userList1 =  userService.findByCondition(null);
        printList(userList1);*/
        
    }

    /**
     * 插入用户信息
     */
    @Test
    public void testAddUser(MongoTemplate mongoTemplate) {
        User zhanggc = new User();
        zhanggc.setName("zhangguochen");
        zhanggc.setAge(23);
        List<String> interests = new ArrayList<String>();
        interests.add("stuty");
        interests.add("hadoop");
       /* zhanggc.setInterest(interests);
        Home home = new Home("henan");
        zhanggc.setHome(home);*/

        // 插入数据
        mongoTemplate.insert(zhanggc);
    }

    /**
     * 查询用户信息
     */
    @Test
    public void testQueryUser(MongoTemplate mongoTemplate) {
        // 查询主要用到Query和Criteria两个对象
        Query query = new Query();
        Criteria criteria = new Criteria();
        //criteria.where("age").gt(22);    // 大于

		/* criteria.and("name").is("cuichongfei");//等于
		 List<String> interests = new ArrayList<String>();
		 interests.add("study");
		 interests.add("linux");
		 criteria.and("interest").in(interests); //in查询
		 criteria.and("home.address").is("henan"); //内嵌文档查询
		 criteria.and("").exists(false); //列存在
		 criteria.and("").lte(""); ///小于等于
		 criteria.and("").regex(""); //正则表达式
		 criteria.and("").ne(""); //不等于*/
		 
		//多条件查询
		//criteria.orOperator(Criteria.where("age").lt(20),Criteria.where("age").gt(26));
		//criteria.andOperator(Criteria.where("name").is("zhangguochen")); 
        query.addCriteria(Criteria.where("age").gt(26));
        List<User> userList1 = mongoTemplate.find(query, User.class);
        printList(userList1);

    }

    /**
     * 更新用户数据
     */
    @Test
    public void testUpdateUser(MongoTemplate mongoTemplate) {
        // update(query,update,class)
        // Query query:需要更新哪些用户,查询参数
        // Update update:操作符,需要对数据做什么更新
        // Class class:实体类

        // 更新age大于24的用户信息
        Query query = new Query();
        query.addCriteria(where("age").gt(24));

        Update update = new Update();
        // age值加2
        update.inc("age", 2);
        // update.set("name", "zhangsan"); 直接赋值
        // update.unset("name"); 删去字段
        // update.push("interest", "java"); 把java追加到interest里面,interest一定得是数组
        // update.pushAll("interest", new String[]{".net","mq"})
        // 用法同push,只是pushAll一定可以追加多个值到一个数组字段内
        // update.pull("interest", "study"); 作用和push相反,从interest字段中删除一个等于value的值
        // update.pullAll("interest", new String[]{"sing","dota"})作用和pushAll相反
        // update.addToSet("interest", "study") 把一个值添加到数组字段中,而且只有当这个值不在数组内的时候才增加
        // update.rename("oldName", "newName") 字段重命名

        // 只更新第一条记录,age加2,name值更新为zhangsan
        mongoTemplate.updateFirst(query, new Update().inc("age", 2).set("name", "zhangsan"), User.class);

        // 批量更新,更新所有查询到的数据
        mongoTemplate.updateMulti(query, update, User.class);

    }
    
    /**
     * 测试删除数据
     */
    @Ignore
    @Test
    public void testRemoveUser(MongoTemplate mongoTemplate) {
        Query query = new Query();
        // query.addCriteria(where("age").gt(22));
        Criteria criteria = where("age").gt(22);
        // 删除年龄大于22岁的用户
        query.addCriteria(criteria);
        mongoTemplate.remove(query, User.class);
    }

    public static void printList(List<User> userList) {
        for (User user : userList) {
            System.out.println(user);
        }
    }
    

    
}