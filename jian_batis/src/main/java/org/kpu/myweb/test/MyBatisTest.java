package org.kpu.myweb.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MyBatisTest {
	@Inject
	private SqlSessionFactory sqlFactory;
	@Test
	public void testFactory() throws Exception {
		try(SqlSession session = sqlFactory.openSession()) {
			System.out.println(session);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

