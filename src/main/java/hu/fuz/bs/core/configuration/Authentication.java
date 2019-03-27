package hu.fuz.bs.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Authentication {

  @Bean
  public BCryptPasswordEncoder getBCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
