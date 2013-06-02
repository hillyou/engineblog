package com.hico.vish.dao.processor;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/resource/spring-app-global-config.xml" })
public class CategoryDaoTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Resource(name = "categoryDao")
	private CategoryDao categoryDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	private UserEntity user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		helper.setUp();
		Assert.assertNotNull(userDao);
		Assert.assertNotNull(categoryDao);
		user=saveUser();
	}

	@After
	public void tearDown() throws Exception {
		user=null;
		helper.tearDown();
	}

	@Test
	public void testGetById() {
		Category category=saveCategory();
		Assert.assertNotNull(category.getKey());
		Assert.assertNotNull(category.getParent());
		Category loadCategory=categoryDao.get(category.getParent().getId());
		Assert.assertNull(loadCategory.getParent());
		Assert.assertEquals("Parent", loadCategory.getName());
	}

	@Test
	public void testGetUserCategory() {
		saveCategory();
		List<Category> category=categoryDao.getUserCategory(getUser("colin@gmail.com"));
		Assert.assertEquals(2, category.size());
	}

	@Test
	public void testSaveCategory() {
		saveCategory();
	}

	private UserEntity saveUser() {
		UserEntity user=new UserEntity();
		user.setEmail("colin@gmail.com");
		user.setUserName("colin");
		user.setLastLogin(new Date());
		userDao.save(user);
		Assert.assertNotNull(user.getKey());
		return user;
	}
	
	private UserEntity getUser(String email) {
		UserEntity en=userDao.getUserByEmail(email);
		Assert.assertNotNull(en);
		return en;
	}
	
	private Category saveCategory() {
		Category category=new Category();
		category.setName("Test");
		Key parentKey=saveParentCategory().getKey();
		category.setParent(saveParentCategory());
		categoryDao.save(category);
		Assert.assertNotNull(category.getKey());
		Assert.assertEquals(parentKey, category.getParent());
		return category;
	}
	
	private Category saveParentCategory() {
		Category category=new Category();
		category.setName("Parent");
		categoryDao.save(category);
		Assert.assertNotNull(category.getKey());
		return category;
	}
}
