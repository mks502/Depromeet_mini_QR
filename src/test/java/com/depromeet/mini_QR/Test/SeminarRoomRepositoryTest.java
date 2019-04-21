package com.depromeet.mini_QR.Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
// Todo: @ContextConfiguration locations 설정 맞는지 확인 
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/dispatcher-servlet.xml" })
public class SeminarRoomRepositoryTest {

	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
	mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//@Test
	
}
