package hu.fuz.bs.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.fuz.StartSpringBootApplication;
import hu.fuz.bs.core.auth.JWTTokenGenerator;
import hu.fuz.bs.core.auth.SecurityConstants;
import hu.fuz.bs.core.auth.UserDetailsServiceImpl;
import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.shopping.model.ItemCategory;
import hu.fuz.bs.shopping.service.ShoppingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ShoppingService shoppingService;

  @InjectMocks
  private ShoppingController shoppingController;

  private JacksonTester<ItemCategory> jacksonTester;

  @Before
  public void setup(){
    JacksonTester.initFields(this, new ObjectMapper());
    mockMvc = MockMvcBuilders.standaloneSetup(shoppingController)
      .build();
  }


  @Test
  public void test1() throws Exception {
    given(shoppingController.getItemCategories(anyBoolean())).willReturn(Collections.emptyList());
    MockHttpServletResponse response = mockMvc.perform(get("/shopping/item-category")).andReturn().getResponse();
    System.out.println(response.getContentAsString());
  }
}
