package hu.fuz.bs.core.auth;

import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.core.model.ApplicationUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private ApplicationUserRepository applicationUserRepository;

  public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository){
    this.applicationUserRepository = applicationUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    ApplicationUser user =  applicationUserRepository.findByUserName(userName)
      .orElseThrow(() -> new UsernameNotFoundException(userName));
    return new User(user.getUserName(), user.getPassword(), Collections.emptyList());
  }
}
