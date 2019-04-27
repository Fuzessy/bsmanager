package hu.fuz.bs.shopping.controller;

import hu.fuz.StartSpringBootApplication;
import hu.fuz.bs.configuration.WebSecurityConfiguration;
import hu.fuz.bs.core.auth.JWTAuthenticationFilter;
import hu.fuz.bs.shopping.model.ItemTarget;
import hu.fuz.bs.shopping.service.ShoppingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartSpringBootApplication.class)
@AutoConfigureMockMvc
public class ShoppingControllerTest2 {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ShoppingService shoppingService;

  @Test
  @WithMockUser(value = "zsolti")
  public void test1() throws Exception {
    given(shoppingService.getItemTargets()).willReturn(
      Arrays.asList(
        ItemTarget.builder().name("Barbi").build(),
        ItemTarget.builder().name("Zsolti").build()
        ));

    MvcResult result = mockMvc.perform(get("/shopping/item-target")).andReturn();
    System.out.println(result.getResponse().getStatus());
    System.out.println(result.getResponse().getContentAsString());
  }


}
