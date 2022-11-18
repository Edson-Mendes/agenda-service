package br.com.emendes.agenda.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilterConfig extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//    TODO: Buscar no body em vez do parâmetro da url
    String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
    String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
    log.info("Username: {}, password: {}", username, password);
    log.info("url: {}", request.getRequestURL().toString());

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    log.info("Name: {}, authenticated: {}", authentication.getName(), authentication.isAuthenticated());
    return authentication;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
    Algorithm algorithm = Algorithm.HMAC256("123456"); //TODO Receber secret como env var.
    User user = (User) auth.getPrincipal();

    String accessToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
        .withIssuer(request.getRequestURL().toString())
        .sign(algorithm);

    Map<String, String> token = new HashMap<>();
    token.put("accessToken", accessToken);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    new ObjectMapper().writeValue(response.getOutputStream(), token);
  }
}
