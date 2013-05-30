package com.hico.vish.dao.processor;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/resource/spring-app-global-config.xml"})
public class ArticleDaoTest{

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	private static Logger logger=Logger.getLogger(ArticleDaoTest.class.getName());
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
	
	private UserEntity user;
	
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
		Assert.assertNotNull(articleDao);
		user=saveUser();
	}

	@After
	public void tearDown() {
		user=null;
		helper.tearDown();
	}

	@Test
	public void testSaveOrUpdate() {
		Article first=getArticle("Title1","Content1");
		Assert.assertNotNull(first.getKey());
	}
	
//	@Test
	public void testSaveComment() {

	}

//	@Test
	public void testGet() {

	}

	@Test
	public void testGetArticleList() throws EntityNotFoundException {
		Article first=getArticle("Title1","Content1");
		List<Article> list1=articleDao.getArticleList(getUser("colin@gmail.com"));
		printList(list1);
		Assert.assertEquals(1, list1.size());
		Article second=getArticle("Title2","Content2");
		List<Article> list2=articleDao.getArticleList(getUser("colin@gmail.com"));
		printList(list2);
		Assert.assertEquals(2, list2.size());
	}
	
	private static void printList(List<?> list) {
		for (Object object : list) {
			System.out.println(object);
		}
	}

	private Article getArticle(String title,String content) {
		Article article=new Article(title,content,getUser("colin@gmail.com").getKey());
		Category cas=saveCategory();
		Category loaded=categoryDao.get(cas.getKey().getId());
		article.setCategory(loaded.getKey());
		articleDao.update(article);
		logger.severe(loaded.getKey().getId()+" <> "+ article.getCategory().getId());
		Assert.assertNotNull(article.getKey());
		Assert.assertEquals(cas.getKey().getId(), article.getCategory().getId());
		return article;
	}
	
	
	
	private UserEntity saveUser() {
		UserEntity user=new UserEntity();
		user.setEmail("colin@gmail.com");
		user.setUserName("colin");
		user.setLastLogin(new Date());
		userDao.save(user);
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
		category.setOwner(getUser("colin@gmail.com").getKey());
		category.setParent(saveParentCategory().getKey());
		categoryDao.save(category);
		Assert.assertNotNull(category.getKey());
		return category;
	}
	
	private Category saveParentCategory() {
		Category category=new Category();
		category.setName("Parent");
		category.setOwner(getUser("colin@gmail.com").getKey());
		categoryDao.save(category);
		Assert.assertNotNull(category.getKey());
		return category;
	}
}
