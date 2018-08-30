package com.sparkcw.goodteam;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {

  @Autowired 
  DataSource dataSource;
  
  @Test
  public void testConnection() {
	  try {
		  Connection con = (Connection)dataSource.getConnection();
		  System.out.println(con);
	  }catch (Exception e) {
		e.printStackTrace();
	}
  }
}
