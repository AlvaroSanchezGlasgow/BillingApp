package com.app.bill;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.app.bill.BillApplication;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(BillApplication.class)
public class BillApplicationTests {

	final String BASE_URL = "http://localhost:8018/";

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup() {
      //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

    @Test
    public void testHome() throws Exception {
  	 // mockMvc.perform(null);

    }

    @Test
    public void contextLoads() {
    }

}
