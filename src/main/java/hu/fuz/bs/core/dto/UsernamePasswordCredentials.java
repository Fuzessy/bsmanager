package hu.fuz.bs.core.dto;

import lombok.Data;

@Data
public class UsernamePasswordCredentials {
  private String username;
  private String password;
}
