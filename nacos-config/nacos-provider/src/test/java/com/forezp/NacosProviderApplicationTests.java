package com.forezp;

import com.forezp.Mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NacosProviderApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		for (UserPO userPO : userMapper.selectList(null)){
			System.out.println(userPO.toString());
		}
	}
}
