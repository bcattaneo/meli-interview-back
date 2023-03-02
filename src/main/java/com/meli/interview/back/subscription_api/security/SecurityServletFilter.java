package com.meli.interview.back.subscription_api.security;

import com.meli.interview.back.subscription_api.session.UserSession;
import com.meli.interview.back.subscription_api.user.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

public class SecurityServletFilter extends GenericFilterBean {

  @Autowired private JwtToken jwtToken;
  @Autowired private UserSession userSession;
  @Autowired private UserRepository userRepository;

  private final List<RequestMatcher> ignoredPaths =
      Arrays.asList(
          new AntPathRequestMatcher("/api/v1/user/login"), new AntPathRequestMatcher("/error"));

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    var request = (HttpServletRequest) servletRequest;
    var response = (HttpServletResponse) servletResponse;
    var maybeCookies = request.getCookies();

    // Endpoints that bypass this filter
    for (var ignoredPath : this.ignoredPaths) {
      if (ignoredPath.matches(request)) {
        filterChain.doFilter(request, response);
        return;
      }
    }
    Optional.ofNullable(maybeCookies)
        .ifPresentOrElse(
            cookies ->
                Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(jwtToken.getCookie()))
                    .findFirst()
                    .ifPresentOrElse(
                        tokenCookie -> {
                          // Check JWT token and get username
                          try {
                            var token = tokenCookie.getValue();
                            var username = jwtToken.getUsernameFromToken(token);
                            System.out.println("USERNAME ES " + username);

                            // Get user from repository
                            var maybeUser = userRepository.findByUsername(username);

                            if (!maybeUser.isPresent()) {
                              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            } else {
                              // Set current user session
                              userSession.setToken(token);
                              userSession.setUser(maybeUser.get());
                              caughtDoFilter(filterChain, servletRequest, servletResponse);
                            }
                          } catch (MalformedJwtException | IllegalArgumentException e) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                          } catch (ExpiredJwtException e) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                          } catch (Exception ex) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                          }
                        },
                        () -> {
                          // Cookie not found, reject
                          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }),
            () -> {
              // Cookies not found, reject
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            });
  }

  public void caughtDoFilter(
      FilterChain filterChain, ServletRequest request, ServletResponse response) {
    try {
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      /**/
    }
  }
}
