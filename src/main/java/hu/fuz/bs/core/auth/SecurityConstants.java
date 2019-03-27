package hu.fuz.bs.core.auth;

import com.auth0.jwt.algorithms.Algorithm;
import net.bytebuddy.dynamic.DynamicType;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class SecurityConstants {
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String TOKEN_HEADER = "Authorization";
  public static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1h
  public static final String SECRET = "very_secret_security_key"
      .replaceAll("e","alma")
      .replaceAll("y","barack");
  public static final Algorithm SIGN_ALGORITHM = HMAC512(SECRET.getBytes());
}
