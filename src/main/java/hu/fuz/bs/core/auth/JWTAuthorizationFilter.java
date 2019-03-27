package hu.fuz.bs.core.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    String tokenHeaderValue = request.getHeader(SecurityConstants.TOKEN_HEADER);
    if(tokenHeaderValue != null  && tokenHeaderValue.startsWith(SecurityConstants.TOKEN_PREFIX)){
      UsernamePasswordAuthenticationToken upaToken = getAuthenticationToken(request);
      SecurityContextHolder.getContext().setAuthentication(upaToken);
    }
    chain.doFilter(request,response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
    if(request.getHeader(SecurityConstants.TOKEN_HEADER) == null){
      return null;
    }
    String jwt = request.getHeader(SecurityConstants.TOKEN_HEADER).replace(SecurityConstants.TOKEN_PREFIX,"");
    DecodedJWT verifier = JWT.require(SecurityConstants.SIGN_ALGORITHM).build().verify(jwt);
    String userName = verifier.getSubject();
    if(userName != null && userName.length() > 0){
      return new UsernamePasswordAuthenticationToken(userName,null, Collections.emptyList());
    }else {
      return null;
    }
  }


}
