package com.mamahao.actsys.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   11:43
 * Description    :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class TestRunner {
	@Autowired
	WebApplicationContext webApplicationContext;
	@Autowired
	MockHttpSession session;
	@Autowired
	MockHttpServletRequest request;

	MockMvc mockMvc;
	@Before
	public void before() throws Exception {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		mockMvc.perform(get("/demo/index"))
//				.andExpect(status().isOk());
		System.out.println("before");
	}

	@After
	public void after(){
		mockMvc = null;
		System.out.println("after");

	}

	@Test
	public void test(){
		System.out.println("runner");
	}
}
