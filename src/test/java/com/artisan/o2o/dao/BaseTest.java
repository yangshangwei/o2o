package com.artisan.o2o.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 
 * @ClassName: BaseTest
 * 
 * @Description: 测试类的基类,配置Spring和junit整合,junit启动时加载springIOC容器
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月14日 下午12:58:21
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class BaseTest {

}
