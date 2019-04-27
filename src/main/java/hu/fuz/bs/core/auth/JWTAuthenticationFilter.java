package hu.fuz.bs.core.auth;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.fuz.bs.core.dto.UsernamePasswordCredentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      UsernamePasswordCredentials credentials =
        new ObjectMapper().readValue(request.getInputStream(),UsernamePasswordCredentials.class);

      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          credentials.getUsername(),
          credentials.getPassword()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    String username = ((User)authResult.getPrincipal()).getUsername();
    String jwt = JWTTokenGenerator.generate(username);;
    response.addHeader(
      SecurityConstants.TOKEN_HEADER,
      SecurityConstants.TOKEN_PREFIX + jwt);
  }
}
