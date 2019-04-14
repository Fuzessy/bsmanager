package hu.fuz.bs.core.service;

import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.core.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserService {

  @Autowired private ApplicationUserRepository applicationUserRepository;

  @GetMapping("")
  public ApplicationUser getUser(Principal principal){
    return applicationUserRepository.findByUserName(principal.getName()).get();
  }
}
