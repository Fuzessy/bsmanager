package hu.fuz.bs.shopping.controller;

import hu.fuz.StartSpringBootApplication;
import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.shopping.dao.ShoppingListItemRepository;
import hu.fuz.bs.shopping.model.ItemCategory;
import hu.fuz.bs.shopping.model.ItemStatus;
import hu.fuz.bs.shopping.model.ShoppingListItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartSpringBootApplication.class,webEnvironment = RANDOM_PORT)
public class ShoppingControllerIntegrationTest2 {

  @Autowired
  private WebApplicationContext context;
  private MockMvc mvc;

  @Autowired
  private ShoppingListItemRepository shoppingListItemRepository;

  @Before
  public void setup() {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  @Test
  @WithMockUser("zsolti")
  public void getShoppingListItemsForShopping() throws Exception {
    // given
    shoppingListItemRepository.saveAll(Arrays.asList(
      ShoppingListItem.builder()
        .itemStatus(ItemStatus.CREATED)
        .quantity("1")
        .name("alma")
        .category(ItemCategory.builder().id(1L).build())
        .recordUser(ApplicationUser.builder().id(1L).build())
        .build()
    ));
    // when
    ResultActions result = mvc.perform(get("/shopping/shopping-list-item"));

    // then
    result.andExpect(status().isOk());
    System.out.println(result.andReturn().getResponse().getContentAsString());


  }
}
