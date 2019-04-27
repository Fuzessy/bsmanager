package hu.fuz.bs.shopping.controller;

import com.auth0.jwt.JWT;
import hu.fuz.StartSpringBootApplication;
import hu.fuz.bs.core.auth.JWTTokenGenerator;
import hu.fuz.bs.core.auth.SecurityConstants;
import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.shopping.model.ItemCategory;
import hu.fuz.bs.shopping.model.ShoppingListItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartSpringBootApplication.class,webEnvironment = RANDOM_PORT)
public class ShoppingControllerIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Before
  public void setUp(){
    restTemplate.getRestTemplate().getInterceptors().add(
      (request, body, execution) -> {
      request.getHeaders().add(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX+ JWTTokenGenerator.generate("barbi"));
      return execution.execute(request,body);
    });
  }

  @Test
  public void getItemCategories() {
    //act
    ResponseEntity<ItemCategory[]> responseItemCagegories = restTemplate.getForEntity("/shopping/item-category", ItemCategory[].class);

    // assert
    assertThat(responseItemCagegories.getStatusCode()).isEqualTo(HttpStatus.OK);
    //assertThat(responseItemCagegories.getBody().length).isGreaterThan(1);
  }
}
