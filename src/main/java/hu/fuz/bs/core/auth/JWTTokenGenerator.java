package hu.fuz.bs.core.auth;

import com.auth0.jwt.JWT;

import java.util.Date;

public class JWTTokenGenerator {
  public static String generate(String username) {
    return JWT.create().withSubject(username)
      .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
      .sign(SecurityConstants.SIGN_ALGORITHM);
  }
}
