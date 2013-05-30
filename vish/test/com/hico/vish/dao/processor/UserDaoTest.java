package com.hico.vish.dao.processor;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hico.vish.dao.table.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/resource/spring-app-global-config.xml"})
public class UserDaoTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	@Resource(name="userDao")
	private UserDao userDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@Before
	public void setUp() {
		helper.setUp();
		Assert.assertNotNull(userDao);
	}

	@Test
	public void testGetUserByEmail() {
		UserEntity user=saveUser();
		UserEntity saveduser=userDao.getUserByEmail("colin@gmail.com");
		Assert.assertNotNull(""+user, saveduser.getKey());
		Assert.assertEquals(user.getUserName(), saveduser.getUserName());
	}

	@Test
	public void testGetUserByIdLong() {
		UserEntity user=saveUser();
		UserEntity loadEn=userDao.get(user.getId());
		Assert.assertEquals(user.getUserName(), loadEn.getUserName());
	}


	@Test
	public void testSaveUser() {
		UserEntity user=saveUser();
		Assert.assertNotNull(""+user, user.getKey());
	}
	
	private UserEntity saveUser() {
		UserEntity user=new UserEntity();
		user.setEmail("colin@gmail.com");
		user.setUserName("colin");
		user.setLastLogin(new Date());
		userDao.save(user);
		return user;
	}

}
